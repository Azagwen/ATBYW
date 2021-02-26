package net.azagwen.atbyw.main;

import net.azagwen.atbyw.items.EssenceItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

import static net.azagwen.atbyw.blocks.AtbywBlocks.*;
import static net.azagwen.atbyw.blocks.statues.StatueRegistry.*;
import static net.azagwen.atbyw.items.AtbywItems.*;
import static net.azagwen.atbyw.main.AtbywMain.newID;

public class AtbywClient implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final Identifier PacketID = newID("spawn_packet");

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
        EntityRendererRegistry.INSTANCE.register(AtbywEntityType.SHROOMSTICK, (dispatcher, context) ->
                new FlyingItemEntityRenderer(dispatcher, context.getItemRenderer())
        );
        receiveEntityPacket();

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getColor(0.5D, 1.0D), GRASS_BLOCK_STAIRS, GRASS_BLOCK_SLAB);

        for (Item item : ESSENCE_BOTTLES)
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((EssenceItem) item).getColor(), item);

        BlockRenderLayerMap.INSTANCE.putBlock(REDSTONE_LANTERN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                BEE_STATUE,
                SILVERFISH_STATUE,
                SHULKER_STATUE,
                CHICKEN_STATUE,
                COD_STATUE,
                SALMON_STATUE,
                PUFFER_FISH_STATUE,
                SLIME_STATUE,
                WAXED_CLEAN_BEE_STATUE,
                WAXED_CLEAN_SILVERFISH_STATUE,
                WAXED_CLEAN_SHULKER_STATUE,
                WAXED_CLEAN_CHICKEN_STATUE,
                WAXED_CLEAN_COD_STATUE,
                WAXED_CLEAN_SALMON_STATUE,
                WAXED_CLEAN_PUFFER_FISH_STATUE,
                WAXED_CLEAN_SLIME_STATUE,
                WAXED_EXPOSED_BEE_STATUE,
                WAXED_EXPOSED_SILVERFISH_STATUE,
                WAXED_EXPOSED_SHULKER_STATUE,
                WAXED_EXPOSED_CHICKEN_STATUE,
                WAXED_EXPOSED_COD_STATUE,
                WAXED_EXPOSED_SALMON_STATUE,
                WAXED_EXPOSED_PUFFER_FISH_STATUE,
                WAXED_EXPOSED_SLIME_STATUE,
                WAXED_DIRTY_BEE_STATUE,
                WAXED_DIRTY_SILVERFISH_STATUE,
                WAXED_DIRTY_SHULKER_STATUE,
                WAXED_DIRTY_CHICKEN_STATUE,
                WAXED_DIRTY_COD_STATUE,
                WAXED_DIRTY_SALMON_STATUE,
                WAXED_DIRTY_PUFFER_FISH_STATUE,
                WAXED_DIRTY_SLIME_STATUE,
                WAXED_MOSSY_BEE_STATUE,
                WAXED_MOSSY_SILVERFISH_STATUE,
                WAXED_MOSSY_SHULKER_STATUE,
                WAXED_MOSSY_CHICKEN_STATUE,
                WAXED_MOSSY_COD_STATUE,
                WAXED_MOSSY_SALMON_STATUE,
                WAXED_MOSSY_PUFFER_FISH_STATUE,
                WAXED_MOSSY_SLIME_STATUE,
                WAXED_VERY_MOSSY_BEE_STATUE,
                WAXED_VERY_MOSSY_SILVERFISH_STATUE,
                WAXED_VERY_MOSSY_SHULKER_STATUE,
                WAXED_VERY_MOSSY_CHICKEN_STATUE,
                WAXED_VERY_MOSSY_COD_STATUE,
                WAXED_VERY_MOSSY_SALMON_STATUE,
                WAXED_VERY_MOSSY_PUFFER_FISH_STATUE,
                WAXED_VERY_MOSSY_SLIME_STATUE
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutoutMipped(),
                GRASS_BLOCK_STAIRS,
                GRASS_BLOCK_SLAB
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                DANDELION_PULL_SWITCH,
                POPPY_PULL_SWITCH,
                BLUE_ORCHID_PULL_SWITCH,
                ALLIUM_PULL_SWITCH,
                AZURE_BLUET_PULL_SWITCH,
                RED_TULIP_PULL_SWITCH,
                ORANGE_TULIP_PULL_SWITCH,
                WHITE_TULIP_PULL_SWITCH,
                PINK_TULIP_PULL_SWITCH,
                OXEYE_DAISY_PULL_SWITCH,
                CORNFLOWER_PULL_SWITCH,
                WITHER_ROSE_PULL_SWITCH,
                LILY_OF_THE_VALLEY_PULL_SWITCH
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                OAK_FENCE_DOOR,
                SPRUCE_FENCE_DOOR,
                BIRCH_FENCE_DOOR,
                JUNGLE_FENCE_DOOR,
                ACACIA_FENCE_DOOR,
                DARK_OAK_FENCE_DOOR,
                CRIMSON_FENCE_DOOR,
                WARPED_FENCE_DOOR,
                IRON_FENCE_DOOR
        );

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                SPRUCE_LADDER,
                BIRCH_LADDER,
                JUNGLE_LADDER,
                ACACIA_LADDER,
                DARK_OAK_LADDER,
                CRIMSON_LADDER,
                WARPED_LADDER,
                BAMBOO_LADDER
        );
    }
}