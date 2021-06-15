package net.azagwen.atbyw.client;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityType;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.client.render.AtbywBlockRenderLayers;
import net.azagwen.atbyw.client.render.AtbywEntityModelLayers;
import net.azagwen.atbyw.client.render.TimerRepeaterBlockEntityRenderer;
import net.azagwen.atbyw.items.EssenceItem;
import net.azagwen.atbyw.main.AtbywEntityType;
import net.azagwen.atbyw.main.EntitySpawnPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

import static net.azagwen.atbyw.items.AtbywItems.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

@SuppressWarnings("deprecation")
public class AtbywClient implements ClientModInitializer {

    public static final Identifier PacketID = NewAtbywID("spawn_packet");

    @Environment(EnvType.CLIENT)
    public void receiveEntityPacket() {
        ClientSidePacketRegistryImpl.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setPitch(pitch);
                e.setYaw(yaw);
                e.setId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        //noinspection UnstableApiUsage
        EntityModelLayerRegistry.registerModelLayer(AtbywEntityModelLayers.TIMER_REPEATER, TimerRepeaterBlockEntityRenderer::getTexturedModelData);
        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntityType.TIMER_REPEATER_ENTITY, TimerRepeaterBlockEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(AtbywEntityType.SHROOMSTICK, FlyingItemEntityRenderer::new);
        receiveEntityPacket();

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        for (Item item : ESSENCE_BOTTLES) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                return tintIndex > 0 ? -1 : ((EssenceItem) item).getColor();
            }, item);
        }

        AtbywBlockRenderLayers.setCutout();
    }
}