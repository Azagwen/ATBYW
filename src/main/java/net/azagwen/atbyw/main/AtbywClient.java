package net.azagwen.atbyw.main;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityType;
import net.azagwen.atbyw.block.entity.TimerRepeaterBlockEntityRenderer;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.items.EssenceItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
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

public class AtbywClient implements ClientModInitializer {

    public static final Identifier PacketID = AtbywID("spawn_packet");

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
                e.pitch = pitch;
                e.yaw = yaw;
                e.setEntityId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {

        BlockEntityRendererRegistry.INSTANCE.register(AtbywBlockEntityType.TIMER_REPEATER_ENTITY, TimerRepeaterBlockEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(AtbywEntityType.SHROOMSTICK, (dispatcher, context) ->
                new FlyingItemEntityRenderer(dispatcher, context.getItemRenderer())
        );
        receiveEntityPacket();

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getColor(0.5D, 1.0D), AtbywBlocks.GRASS_BLOCK_STAIRS, AtbywBlocks.GRASS_BLOCK_SLAB);

        for (Item item : ESSENCE_BOTTLES)
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((EssenceItem) item).getColor(), item);

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                StatueRegistry.BEE_STATUE,
                StatueRegistry.SILVERFISH_STATUE,
                StatueRegistry.SHULKER_STATUE,
                StatueRegistry.CHICKEN_STATUE,
                StatueRegistry.COD_STATUE,
                StatueRegistry.SALMON_STATUE,
                StatueRegistry.PUFFER_FISH_STATUE,
                StatueRegistry.SLIME_STATUE,
                StatueRegistry.WAXED_CLEAN_BEE_STATUE,
                StatueRegistry.WAXED_CLEAN_SILVERFISH_STATUE,
                StatueRegistry.WAXED_CLEAN_SHULKER_STATUE,
                StatueRegistry.WAXED_CLEAN_CHICKEN_STATUE,
                StatueRegistry.WAXED_CLEAN_COD_STATUE,
                StatueRegistry.WAXED_CLEAN_SALMON_STATUE,
                StatueRegistry.WAXED_CLEAN_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_CLEAN_SLIME_STATUE,
                StatueRegistry.WAXED_EXPOSED_BEE_STATUE,
                StatueRegistry.WAXED_EXPOSED_SILVERFISH_STATUE,
                StatueRegistry.WAXED_EXPOSED_SHULKER_STATUE,
                StatueRegistry.WAXED_EXPOSED_CHICKEN_STATUE,
                StatueRegistry.WAXED_EXPOSED_COD_STATUE,
                StatueRegistry.WAXED_EXPOSED_SALMON_STATUE,
                StatueRegistry.WAXED_EXPOSED_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_EXPOSED_SLIME_STATUE,
                StatueRegistry.WAXED_DIRTY_BEE_STATUE,
                StatueRegistry.WAXED_DIRTY_SILVERFISH_STATUE,
                StatueRegistry.WAXED_DIRTY_SHULKER_STATUE,
                StatueRegistry.WAXED_DIRTY_CHICKEN_STATUE,
                StatueRegistry.WAXED_DIRTY_COD_STATUE,
                StatueRegistry.WAXED_DIRTY_SALMON_STATUE,
                StatueRegistry.WAXED_DIRTY_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_DIRTY_SLIME_STATUE,
                StatueRegistry.WAXED_MOSSY_BEE_STATUE,
                StatueRegistry.WAXED_MOSSY_SILVERFISH_STATUE,
                StatueRegistry.WAXED_MOSSY_SHULKER_STATUE,
                StatueRegistry.WAXED_MOSSY_CHICKEN_STATUE,
                StatueRegistry.WAXED_MOSSY_COD_STATUE,
                StatueRegistry.WAXED_MOSSY_SALMON_STATUE,
                StatueRegistry.WAXED_MOSSY_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_MOSSY_SLIME_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_BEE_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SILVERFISH_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SHULKER_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_CHICKEN_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_COD_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SALMON_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SLIME_STATUE
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                AtbywBlocks.GRASS_BLOCK_STAIRS,
                AtbywBlocks.GRASS_BLOCK_SLAB,
                AtbywBlocks.IRON_SPIKE_TRAP_SPIKES,
                AtbywBlocks.GOLD_SPIKE_TRAP_SPIKES,
                AtbywBlocks.DIAMOND_SPIKE_TRAP_SPIKES,
                AtbywBlocks.NETHERITE_SPIKE_TRAP_SPIKES,
                AtbywBlocks.REDSTONE_LANTERN,
                AtbywBlocks.DANDELION_PULL_SWITCH,
                AtbywBlocks.POPPY_PULL_SWITCH,
                AtbywBlocks.BLUE_ORCHID_PULL_SWITCH,
                AtbywBlocks.ALLIUM_PULL_SWITCH,
                AtbywBlocks.AZURE_BLUET_PULL_SWITCH,
                AtbywBlocks.RED_TULIP_PULL_SWITCH,
                AtbywBlocks.ORANGE_TULIP_PULL_SWITCH,
                AtbywBlocks.WHITE_TULIP_PULL_SWITCH,
                AtbywBlocks.PINK_TULIP_PULL_SWITCH,
                AtbywBlocks.OXEYE_DAISY_PULL_SWITCH,
                AtbywBlocks.CORNFLOWER_PULL_SWITCH,
                AtbywBlocks.WITHER_ROSE_PULL_SWITCH,
                AtbywBlocks.LILY_OF_THE_VALLEY_PULL_SWITCH,
                AtbywBlocks.OAK_FENCE_DOOR,
                AtbywBlocks.SPRUCE_FENCE_DOOR,
                AtbywBlocks.BIRCH_FENCE_DOOR,
                AtbywBlocks.JUNGLE_FENCE_DOOR,
                AtbywBlocks.ACACIA_FENCE_DOOR,
                AtbywBlocks.DARK_OAK_FENCE_DOOR,
                AtbywBlocks.CRIMSON_FENCE_DOOR,
                AtbywBlocks.WARPED_FENCE_DOOR,
                AtbywBlocks.IRON_FENCE_DOOR,
                AtbywBlocks.SPRUCE_LADDER,
                AtbywBlocks.BIRCH_LADDER,
                AtbywBlocks.JUNGLE_LADDER,
                AtbywBlocks.ACACIA_LADDER,
                AtbywBlocks.DARK_OAK_LADDER,
                AtbywBlocks.CRIMSON_LADDER,
                AtbywBlocks.WARPED_LADDER,
                AtbywBlocks.BAMBOO_LADDER,
                AtbywBlocks.TIMER_REPEATER
        );
    }
}