package net.azagwen.atbyw.client;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Map;

public class ItemOperationDecoder {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Item Operation Decoder");

    public ItemOperationDecoder() {
    }

    public static void readBlockToBlockOp(InputStream location, Map<Block, Block> map) {
        var reader = new JsonReader(new InputStreamReader(location));
        var parser = new JsonParser();
        var json = parser.parse(reader).getAsJsonObject();
        var expectedID = new Identifier("atbyw", "block_to_block");

        if (JsonHelper.getBoolean(json, "replace", false)) {
            map.clear();
        }

        if (JsonHelper.getString(json, "data_type").equals(expectedID.toString())) {
            var states = JsonHelper.getObject(json, "states").entrySet();
            states.forEach((entry) -> {
                var originalStringId = entry.getKey().split(":");
                var resultStringId = entry.getValue().getAsString().split(":");
                var originalId = new Identifier(originalStringId[0], originalStringId[1]);
                var resultId = new Identifier(resultStringId[0], resultStringId[1]);

                map.put(AtbywUtils.getBlockFromID(originalId), AtbywUtils.getBlockFromID(resultId));
                LOGGER.info("[ " + originalId + ", " + resultId + " ]");

            });
        }
    }

    public static void readBlockToBlockWithLootOp(InputStream location, Map<Pair<Block, Block>, Item> map) {
        var reader = new JsonReader(new InputStreamReader(location));
        var parser = new JsonParser();
        var json = parser.parse(reader).getAsJsonObject();
        var expectedID = new Identifier("atbyw", "block_to_block");

        if (JsonHelper.getBoolean(json, "replace", false)) {
            map.clear();
        }

        if (JsonHelper.getString(json, "data_type").equals(expectedID.toString())) {
            var states = JsonHelper.getObject(json, "states").entrySet();
            var loots = JsonHelper.getObject(json, "loots").entrySet();

            states.forEach((stateEntry) -> {
                var original = stateEntry.getKey().split(":");
                var result = stateEntry.getValue().getAsString().split(":");
                var originalID = new Identifier(original[0], original[1]);
                var resultID = new Identifier(result[0], result[1]);

                loots.forEach((lootEntry) -> {
                    var lootOwner = lootEntry.getKey().split(":");
                    var loot = lootEntry.getValue().getAsString().split(":");
                    var lootOwnerID = new Identifier(lootOwner[0], lootOwner[1]);
                    var lootID = new Identifier(loot[0], loot[1]);

                    if (lootOwnerID.equals(originalID)) {
                        map.put(new Pair<>(AtbywUtils.getBlockFromID(originalID), AtbywUtils.getBlockFromID(resultID)), AtbywUtils.getItemFromID(lootID));
                        LOGGER.info("[ " + originalID + ", " + resultID + " ]");
                    }
                });
            });
        }
    }
}
