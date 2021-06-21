package net.azagwen.atbyw.main;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ItemOperationDecoder {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Item Operation Decoder");
    private static final boolean enableDebug = true;

    public ItemOperationDecoder() {
    }

    //TODO: rewrite using maps and following axe_replace_slab.json

    //New decoder method
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

                    if (itemObject.has("damage")) {
                        damage = JsonHelper.getInt(itemObject, "damage");
                    }
                    if (itemObject.has("decrement")) {
                        decrement = JsonHelper.getInt(itemObject, "decrement");
                    }

                    item = Registry.ITEM.get(itemIdentifier);
                } else if (json.has("tag_used")) {
                    var tagObject = JsonHelper.getObject(json, "tag_used");
                    var tagIdentifier = new Identifier(tagObject.get("tag").getAsString());

                    if (tagObject.has("damage")) {
                        damage = JsonHelper.getInt(tagObject, "damage");
                    }
                    if (tagObject.has("decrement")) {
                        decrement = JsonHelper.getInt(tagObject, "decrement");
                    }

                    tag = ServerTagManagerHolder.getTagManager().getTag(Registry.ITEM_KEY, tagIdentifier, (identifier) -> {
                        return new JsonSyntaxException("Unknown item tag '" + identifier + "'");
                    });
                }
            }

            //For each entry in the "targets" object, add a row to the Table based on the previously defined fields.
            for (var entry : targets) {
                var entryContent = entry.getValue().getAsJsonObject();
                var resultIdentifier = new Identifier(JsonHelper.getString(entryContent, "result"));
                var original = Registry.BLOCK.get(new Identifier(entry.getKey()));
                var result = Registry.BLOCK.get(resultIdentifier);
                var sound = (SoundEvent) null;
                var loot = (Item) null;

                //check if "sound" is there
                if (entryContent.has("sound")) {
                    var soundIdentifier = new Identifier(JsonHelper.getString(entryContent, "sound"));
                    sound = Registry.SOUND_EVENT.get(soundIdentifier);
                }
                //check if "loot" is there
                if (entryContent.has("loot")) {
                    var lootIdentifier = new Identifier(JsonHelper.getString(entryContent, "loot"));
                    loot = Registry.ITEM.get(lootIdentifier);
                }

                populateTable(operation, item, tag, original, result, loot, damage, decrement, sound);
            }
        }
    }

    private static void populateTable(HashBasedTable<Item, Block, ItemOperationResult> operation, Item item, Tag<Item> tag, Block original, Block result, Item loot, int damage, int decrement, SoundEvent sound) {
        var operationResult = new ItemOperationResult(result, loot, damage, decrement, sound);

        if (item != null) {
            operation.put(item, original, operationResult);
        } else if (tag != null) {
            for (var value : tag.values()) {
                operation.put(value, original, operationResult);
            }
        }
    }
}
