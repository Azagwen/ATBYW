package net.azagwen.atbyw.main;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.List;

public class ItemOperationDecoder {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Item Operation Decoder");
    private static final boolean enableDebug = true;

    public ItemOperationDecoder() {
    }

    public static void readBlockToBlock(InputStream location, List<BlockToBlockOperation> operations) {
        var reader = new JsonReader(new InputStreamReader(location));
        var parser = new JsonParser();
        var json = parser.parse(reader).getAsJsonObject();
        var expectedID = new Identifier("atbyw", "block_to_block");
        var returnValue = new BlockToBlockOperation();

        if (JsonHelper.getBoolean(json, "replace", false)) {
//            map.clear();
        }

        if (JsonHelper.getString(json, "data_type").equals(expectedID.toString())) {
            var states = JsonHelper.getObject(json, "states").entrySet();
            var itemUsed = JsonHelper.getObject(json, "item_used");

            for (var stateEntry : states) {
                var originalID = new Identifier(stateEntry.getKey());
                var resultID = new Identifier(stateEntry.getValue().getAsString());

                returnValue.setOriginal(Registry.BLOCK.get(originalID));
                returnValue.setResult(Registry.BLOCK.get(resultID));

                if (itemUsed.has("tag")) {
                    var tag = JsonHelper.getString(itemUsed, "tag");

                    returnValue.setUsedItem(new Identifier(tag));
                } else if (itemUsed.has("item")) {
                    var item = JsonHelper.getString(itemUsed, "item");

                    returnValue.setUsedItem(Registry.ITEM.get(new Identifier(item)));
                }

                returnValue.setUsedItemDamage(itemUsed.has("damage") ? JsonHelper.getInt(itemUsed, "damage") : 0);
                returnValue.setUsedItemDecrement(itemUsed.has("decrement") ? JsonHelper.getInt(itemUsed, "decrement") : 0);

                if (json.has("loot")) {
                    var loot = JsonHelper.getObject(json, "loot").entrySet();

                    for (var lootEntry : loot) {
                        var lootOwnerID = new Identifier(lootEntry.getKey());
                        var lootID = new Identifier(lootEntry.getValue().getAsString());

                        if (lootOwnerID.equals(originalID)) {
                            returnValue.setLoot(Registry.ITEM.get(lootID));
                        }
                    }
                }

                if (json.has("sounds")) {
                    var sounds = JsonHelper.getObject(json, "sounds").entrySet();
                    for (var soundEntry : sounds) {
                        var soundOwnerID = new Identifier(soundEntry.getKey());
                        var soundID = new Identifier(soundEntry.getValue().getAsString());

                        if (soundOwnerID.equals(originalID)) {
                            returnValue.setOperationSound(Registry.SOUND_EVENT.get(soundID));
                        }
                    }
                }

                if (enableDebug) {
                    LOGGER.info(returnValue.toString());
                }
                operations.add(returnValue);
            }
        }
    }
}
