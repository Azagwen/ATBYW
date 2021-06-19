package net.azagwen.atbyw.main;

import com.google.common.collect.Maps;
import net.azagwen.atbyw.client.ItemOperationDecoder;
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

public class AtbywDataResourceListener implements SimpleSynchronousResourceReloadListener {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Main");
    public static Map<Block, Block> AXE_STRIPPING = Maps.newHashMap();
    public static Map<Pair<Block, Block>, Item> AXE_STRIPPING_WITH_LOOT = Maps.newHashMap();

    @Override
    public void reload(ResourceManager manager) {
        //Item operation data
        for(Identifier id : manager.findResources("atbyw/item_operations/", path -> path.endsWith(".json"))) {
            try {
                InputStream stream = manager.getResource(id).getInputStream();
                LOGGER.info(id);

                //Axe Stripping
                if (id.getPath().equals("atbyw/item_operations/axe_stripping")) {
                    ItemOperationDecoder.readBlockToBlockOp(stream, AXE_STRIPPING);
                }

                //Axe Stripping with Loot
                if (id.getPath().equals("atbyw/item_operations/axe_stripping_with_loot")) {
                    ItemOperationDecoder.readBlockToBlockWithLootOp(stream, AXE_STRIPPING_WITH_LOOT);
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

