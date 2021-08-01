package net.azagwen.atbyw.client;

import net.azagwen.dp_item_ops.DpItemOpsIdentifier;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientResourceListener implements SimpleSynchronousResourceReloadListener {
    public static final Logger LOGGER = LogManager.getLogger("Atbyw Resource Listener");

    public ClientResourceListener() {
    }

    @Override
    public void reload(ResourceManager manager) {
    }

    public Identifier getFabricId() {
        return new DpItemOpsIdentifier("data");
    }
}
