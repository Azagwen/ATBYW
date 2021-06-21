package net.azagwen.atbyw.main;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Tables;
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
    public static List<BlockToBlockOperation> BLOCK_TO_BLOCK_OPERATIONS = Lists.newArrayList();
    public static HashBasedTable<Item, Block, ItemOperationResult> BTB_OPS = HashBasedTable.create();

    @Override
    public void reload(ResourceManager manager) {
        //Item operation data
        for(Identifier id : manager.findResources("atbyw/item_operations/", path -> path.endsWith(".json"))) {
            try {
                InputStream stream = manager.getResource(id).getInputStream();
                ItemOperationDecoder.readBlockToBlock(stream, BTB_OPS);
            } catch (IOException e) {
                LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
        for (var cell: BTB_OPS.cellSet()){
            LOGGER.info(cell.getRowKey() + " " + cell.getColumnKey() + " " + cell.getValue());
        }
    }

    @Override
    public Identifier getFabricId() {
        return NewAtbywID("data");
    }
}

