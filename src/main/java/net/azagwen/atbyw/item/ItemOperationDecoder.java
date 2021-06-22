package net.azagwen.atbyw.item;

import com.google.common.collect.HashBasedTable;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class ItemOperationDecoder {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Item Operation Decoder");

    public ItemOperationDecoder() {
    }

    public static void readBlockToBlock(InputStream location, HashBasedTable<Item, Block, ItemOperationResult> operation) {
        var reader = new JsonReader(new InputStreamReader(location));
        var parser = new JsonParser();
        var json = parser.parse(reader).getAsJsonObject();
        var expectedID = new Identifier("atbyw", "block_to_block");

        if (JsonHelper.getString(json, "data_type").equals(expectedID.toString())) {
            var targets = JsonHelper.getObject(json, "targets").entrySet();
            var item = (Item) null;
            var tag = (Tag<Item>) null;
            var damage = 0;
            var decrement = 0;

            //Check if the Table should be cleared
            if (JsonHelper.getBoolean(json, "replace", false)) {
                operation.clear();
            }

            //Check if the user didn't try to add both "tag_used" and "item_used" in the JSON, for simplicity's sake (for now).
            if (json.has("item_used") && json.has("tag_used")) {
                throw new JsonSyntaxException("Item Operations may only have either a \"tag_use\" or \"item_used\" parameter, both have been found in " + location);
            } else {
                //Gather the data in either of the objects if present.
                if (json.has("item_used")) {
                    var itemObject = JsonHelper.getObject(json, "item_used");
                    var itemIdentifier = new Identifier(itemObject.get("item").getAsString());

                    damage = checkDamageAndDecrement(itemObject).getLeft();
                    decrement = checkDamageAndDecrement(itemObject).getRight();

                    item = Registry.ITEM.get(itemIdentifier);
                } else if (json.has("tag_used")) {
                    var tagObject = JsonHelper.getObject(json, "tag_used");
                    var tagIdentifier = new Identifier(tagObject.get("tag").getAsString());

                    damage = checkDamageAndDecrement(tagObject).getLeft();
                    decrement = checkDamageAndDecrement(tagObject).getRight();

                    tag = ServerTagManagerHolder.getTagManager().getTag(Registry.ITEM_KEY, tagIdentifier, (identifier) -> {
                        return new JsonSyntaxException("Unknown item tag '" + identifier + "'");
                    });
                }
            }

            //For each entry in the "targets" object, add a row to the Table based on the previously defined fields.
            for (var target : targets) {
                var targetContent = target.getValue().getAsJsonObject();
                var resultIdentifier = new Identifier(JsonHelper.getString(targetContent, "result"));
                var original = Registry.BLOCK.get(new Identifier(target.getKey()));
                var result = Registry.BLOCK.get(resultIdentifier);
                var sound = (Identifier) null;
                var loot = (Item) null;

                //check if "sound" is there
                if (targetContent.has("sound")) {
                    sound = new Identifier(JsonHelper.getString(targetContent, "sound"));
                }
                //check if "loot" is there
                if (targetContent.has("loot")) {
                    var lootIdentifier = new Identifier(JsonHelper.getString(targetContent, "loot"));
                    loot = Registry.ITEM.get(lootIdentifier);
                }

                populateTable(operation, item, tag, original, result, loot, damage, decrement, sound);
            }
        }
    }

    private static void populateTable(HashBasedTable<Item, Block, ItemOperationResult> operation, Item item, Tag<Item> tag, Block original, Block result, Item loot, int damage, int decrement, Identifier sound) {
        var operationResult = new ItemOperationResult(result, loot, damage, decrement, sound);

        if (item != null) {
            operation.put(item, original, operationResult);
        } else if (tag != null) {
            for (var value : tag.values()) {
                operation.put(value, original, operationResult);
            }
        }
    }

    private static Pair<Integer, Integer> checkDamageAndDecrement(JsonObject object) {
        var damage = 0;
        var decrement = 0;

        if (object.has("damage")) {
            damage = JsonHelper.getInt(object, "damage");
        }
        if (object.has("decrement")) {
            decrement = JsonHelper.getInt(object, "decrement");
        }

        return new Pair<>(damage, decrement);
    }
}
