package net.azagwen.atbyw.client;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityType;
import net.azagwen.atbyw.client.render.AtbywBlockRenderLayers;
import net.azagwen.atbyw.client.render.AtbywEntityModelLayers;
import net.azagwen.atbyw.client.render.TimerRepeaterBlockEntityRenderer;
import net.azagwen.atbyw.main.AtbywEntityTypes;
import net.azagwen.atbyw.main.EntitySpawnPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.item.AtbywItems.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

@SuppressWarnings("deprecation")
public class AtbywClient implements ClientModInitializer {

    public static final Identifier PacketID = NewAtbywID("spawn_packet");

    @Environment(EnvType.CLIENT)
    public void receiveEntityPacket() {
        ClientSidePacketRegistryImpl.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
            var entityType = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            var uuid = byteBuf.readUuid();
            var entityId = byteBuf.readVarInt();
            var pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            var pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            var yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                var entity = entityType.create(MinecraftClient.getInstance().world);
                if (entity == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(entityType) + "\"!");
                entity.updateTrackedPosition(pos);
                entity.setPos(pos.x, pos.y, pos.z);
                entity.setPitch(pitch);
                entity.setYaw(yaw);
                entity.setId(entityId);
                entity.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, entity);
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        //noinspection UnstableApiUsage
        EntityModelLayerRegistry.registerModelLayer(AtbywEntityModelLayers.TIMER_REPEATER, TimerRepeaterBlockEntityRenderer::getTexturedModelData);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntityType.TIMER_REPEATER_ENTITY, TimerRepeaterBlockEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(AtbywEntityTypes.SHROOMSTICK, FlyingItemEntityRenderer::new);
        receiveEntityPacket();

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        for (var item : ESSENCE_BOTTLES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                return tintIndex > 0 ? -1 : item.getColor();
            }, item);
        }

        AtbywBlockRenderLayers.setCutout();
    }
}