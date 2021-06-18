package net.azagwen.atbyw.client;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Map;

public class ItemOperationDecoder {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Item Operation Decoder");
    private final InputStream dataFile;

    public ItemOperationDecoder(InputStream location, String path) {
        this.dataFile = location;

    }

    public Map<Block, Block> readBlockToBlockOp() {
        var map = Maps.<Block, Block>newHashMap();

        var reader = new JsonReader(new InputStreamReader(dataFile));
        var parser = new JsonParser();
        var json = parser.parse(reader).getAsJsonObject();
        var btbID = new Identifier("atbyw", "block_to_block");

        if (json.has("data_type")) {
            if (json.get("data_type").getAsString().equals(btbID.toString())) {
                if (json.has("states")) {
                    var states = json.get("states").getAsJsonObject().entrySet();
                    states.forEach((entry) -> {
                        var originalStringId = entry.getKey();
                        var resultStringId = entry.getValue().getAsString();
                        var originalId = new Identifier(originalStringId.split(":")[0], originalStringId.split(":")[1]);
                        var resultId = new Identifier(resultStringId.split(":")[0], resultStringId.split(":")[1]);

                        map.put(AtbywUtils.getBlockFromID(originalId), AtbywUtils.getBlockFromID(resultId));
                    });
                } else {
                    LOGGER.info("Couldn't read " + dataFile + " of \"atbyw:block_to_block\" data type, but is missing a \"states\" key.");
                }
            } else {
                LOGGER.info("Couldn't read " + dataFile + ", should be \"atbyw:block_to_block\", wrong data type found.");
            }
        } else {
            LOGGER.info("Couldn't read " + dataFile + " \"data_type\" key not found.");
        }

        return map;
    }
}
