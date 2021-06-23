package net.azagwen.atbyw.main;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import net.azagwen.atbyw.item.ItemOperationDecoder;
import net.azagwen.atbyw.item.ItemOperationResult;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static net.azagwen.atbyw.main.AtbywMain.NewAtbywID;

public class DataResourceListener implements SimpleSynchronousResourceReloadListener {
    public static final Logger LOGGER  = LogManager.getLogger("Atbyw Data Listener");
    public static HashBasedTable<Item, Block, ItemOperationResult> BTB_OPS = HashBasedTable.create();
    final boolean debug = false;

    @Override
    public void reload(ResourceManager manager) {
        //Item operation data
        //Clear the Table before re-filling it.
        BTB_OPS.clear();
        for(Identifier id : manager.findResources("atbyw/item_operations", path -> path.endsWith(".json"))) {
            //Try to fill the table for each id in "*/data/atbyw/item_operations/..."
            try {
                InputStream stream = manager.getResource(id).getInputStream();
                ItemOperationDecoder.readBlockToBlock(stream, BTB_OPS);
            } catch (IOException e) {
                LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
        if (debug) {
            for (var cell : BTB_OPS.cellSet()) {
                LOGGER.info(cell.getRowKey() + " " + cell.getColumnKey() + " " + cell.getValue());
            }
        }
        LOGGER.info("Item Opration Data loaded !");
    }

    @Override
    public Identifier getFabricId() {
        return NewAtbywID("data");
    }
}

