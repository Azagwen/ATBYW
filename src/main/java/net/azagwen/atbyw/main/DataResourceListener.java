package net.azagwen.atbyw.main;

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
import java.util.Map;

import static net.azagwen.atbyw.main.AtbywMain.NewAtbywID;

public class DataResourceListener implements SimpleSynchronousResourceReloadListener {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Main");
    public static Map<Block, Block> AXE_REPLACE = Maps.newHashMap();
    public static Map<Block, Block> HOE_REPLACE = Maps.newHashMap();
    public static Map<Block, Block> SHOVEL_REPLACE = Maps.newHashMap();
    public static Map<Block, Pair<Block, Item>> AXE_REPLACE_WITH_LOOT = Maps.newHashMap();
    public static Map<Block, Pair<Block, Item>> HOE_REPLACE_WITH_LOOT = Maps.newHashMap();
    public static Map<Block, Pair<Block, Item>> SHOVEL_REPLACE_WITH_LOOT = Maps.newHashMap();

    @Override
    public void reload(ResourceManager manager) {
        //Item operation data
        for(Identifier id : manager.findResources("atbyw/item_operations/", path -> path.endsWith(".json"))) {
            try {
                InputStream stream = manager.getResource(id).getInputStream();

                //Axe Stripping
                if (id.getPath().equals("atbyw/item_operations/axe_replace.json")) {
                    ItemOperationDecoder.readBlockToBlockOp(stream, AXE_REPLACE);
                }

                //Axe Stripping with Loot
                if (id.getPath().equals("atbyw/item_operations/axe_replace_with_loot.json")) {
                    ItemOperationDecoder.readBlockToBlockWithLootOp(stream, AXE_REPLACE_WITH_LOOT);
                }

                //Hoe Tilling
                if (id.getPath().equals("atbyw/item_operations/hoe_replace.json")) {
                    ItemOperationDecoder.readBlockToBlockOp(stream, HOE_REPLACE);
                }

                //Hoe Tilling with Loot
                if (id.getPath().equals("atbyw/item_operations/hoe_replace_with_loot.json")) {
                    ItemOperationDecoder.readBlockToBlockWithLootOp(stream, HOE_REPLACE_WITH_LOOT);
                }

                //Shovel Flattening
                if (id.getPath().equals("atbyw/item_operations/shovel_replace.json")) {
                    ItemOperationDecoder.readBlockToBlockOp(stream, SHOVEL_REPLACE);
                }

                //Shovel Flattening with Loot
                if (id.getPath().equals("atbyw/item_operations/shovel_replace_with_loot.json")) {
                    ItemOperationDecoder.readBlockToBlockWithLootOp(stream, SHOVEL_REPLACE_WITH_LOOT);
                }

            } catch (IOException e) {
                LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
    }

    @Override
    public Identifier getFabricId() {
        return NewAtbywID("data/custom/strip_log_slabs/strip_log_slabs");
    }
}

