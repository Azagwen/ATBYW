package net.azagwen.atbyw.main;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static net.azagwen.atbyw.main.AtbywMain.NewAtbywID;

public class DataResourceListener implements SimpleSynchronousResourceReloadListener {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Main");
    public static Map<Block, Pair<Block, Item>> AXE_REPLACE = Maps.newHashMap();
    public static Map<Block, Pair<Block, Item>> PICKAXE_REPLACE = Maps.newHashMap();
    public static Map<Block, Pair<Block, Item>> SHOVEL_REPLACE = Maps.newHashMap();
    public static Map<Block, Pair<Block, Item>> HOE_REPLACE = Maps.newHashMap();
    public static List<BlockToBlockOperation> BLOCK_TO_BLOCK_OPERATIONS = Lists.newArrayList();

    @Override
    public void reload(ResourceManager manager) {
        //Item operation data
        for(Identifier id : manager.findResources("atbyw/item_operations/", path -> path.endsWith(".json"))) {
            try {
                InputStream stream = manager.getResource(id).getInputStream();
                ItemOperationDecoder.readBlockToBlock(stream, BLOCK_TO_BLOCK_OPERATIONS);
            } catch (IOException e) {
                LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
    }

    @Override
    public Identifier getFabricId() {
        return NewAtbywID("data");
    }
}

