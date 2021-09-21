package net.azagwen.atbyw.client.render;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.block.registry.DecorationBlockRegistry;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.List;

public class BlockRenderLayers {

    public static void init() {
        var cutoutBlocks = Lists.<Block>newArrayList();
        var translucentBlocks = Lists.<Block>newArrayList();

        cutoutLayerBlocks(cutoutBlocks);
        translucentLayerBlocks(translucentBlocks);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), cutoutBlocks.toArray(Block[]::new));
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), translucentBlocks.toArray(Block[]::new));
    }

    public static void addEmissiveBlock(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }

    private static void cutoutLayerBlocks(List<Block> blocks) {
        blocks.add(BuildingBlockRegistry.GRASS_BLOCK_STAIRS);
        blocks.add(BuildingBlockRegistry.GRASS_BLOCK_SLAB);
        blocks.add(RedstoneBlockRegistry.IRON_SPIKE_TRAP_SPIKES);
        blocks.add(RedstoneBlockRegistry.GOLD_SPIKE_TRAP_SPIKES);
        blocks.add(RedstoneBlockRegistry.DIAMOND_SPIKE_TRAP_SPIKES);
        blocks.add(RedstoneBlockRegistry.NETHERITE_SPIKE_TRAP_SPIKES);
        blocks.add(RedstoneBlockRegistry.REDSTONE_LANTERN);
        blocks.add(RedstoneBlockRegistry.DANDELION_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.POPPY_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.BLUE_ORCHID_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.ALLIUM_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.AZURE_BLUET_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.RED_TULIP_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.ORANGE_TULIP_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.WHITE_TULIP_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.PINK_TULIP_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.OXEYE_DAISY_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.CORNFLOWER_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.WITHER_ROSE_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.LILY_OF_THE_VALLEY_PULL_SWITCH);
        blocks.add(RedstoneBlockRegistry.OAK_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.SPRUCE_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.BIRCH_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.JUNGLE_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.ACACIA_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.DARK_OAK_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.CRIMSON_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.WARPED_FENCE_DOOR);
        blocks.add(RedstoneBlockRegistry.IRON_FENCE_DOOR);
        blocks.add(DecorationBlockRegistry.SPRUCE_LADDER);
        blocks.add(DecorationBlockRegistry.BIRCH_LADDER);
        blocks.add(DecorationBlockRegistry.JUNGLE_LADDER);
        blocks.add(DecorationBlockRegistry.ACACIA_LADDER);
        blocks.add(DecorationBlockRegistry.DARK_OAK_LADDER);
        blocks.add(DecorationBlockRegistry.CRIMSON_LADDER);
        blocks.add(DecorationBlockRegistry.WARPED_LADDER);
        blocks.add(DecorationBlockRegistry.BAMBOO_LADDER);
        blocks.add(StatueRegistry.BEE_STATUE);
        blocks.add(StatueRegistry.SILVERFISH_STATUE);
        blocks.add(StatueRegistry.SHULKER_STATUE);
        blocks.add(StatueRegistry.CHICKEN_STATUE);
        blocks.add(StatueRegistry.COD_STATUE);
        blocks.add(StatueRegistry.SALMON_STATUE);
        blocks.add(StatueRegistry.PUFFER_FISH_STATUE);
        blocks.add(StatueRegistry.SLIME_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_BEE_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_SILVERFISH_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_SHULKER_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_CHICKEN_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_COD_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_SALMON_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_PUFFER_FISH_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_SLIME_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_BEE_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_SILVERFISH_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_SHULKER_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_CHICKEN_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_COD_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_SALMON_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_PUFFER_FISH_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_SLIME_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_BEE_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_SILVERFISH_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_SHULKER_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_CHICKEN_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_COD_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_SALMON_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_PUFFER_FISH_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_SLIME_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_BEE_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_SILVERFISH_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_SHULKER_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_CHICKEN_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_COD_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_SALMON_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_PUFFER_FISH_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_SLIME_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_BEE_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_SILVERFISH_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_SHULKER_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_CHICKEN_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_COD_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_SALMON_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_PUFFER_FISH_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_SLIME_STATUE);
        blocks.add(StatueRegistry.AXOLOTL_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_AXOLOTL_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_AXOLOTL_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_AXOLOTL_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_AXOLOTL_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_AXOLOTL_STATUE);
        blocks.add(StatueRegistry.BAT_STATUE);
        blocks.add(StatueRegistry.WAXED_CLEAN_BAT_STATUE);
        blocks.add(StatueRegistry.WAXED_EXPOSED_BAT_STATUE);
        blocks.add(StatueRegistry.WAXED_DIRTY_BAT_STATUE);
        blocks.add(StatueRegistry.WAXED_MOSSY_BAT_STATUE);
        blocks.add(StatueRegistry.WAXED_VERY_MOSSY_BAT_STATUE);
        blocks.add(RedstoneBlockRegistry.TIMER_REPEATER);
        blocks.add(DecorationBlockRegistry.LARGE_CHAIN);
        blocks.add(BuildingBlockRegistry.SHATTERED_GLASS);
        blocks.add(RedstoneBlockRegistry.REDSTONE_PIPE);
        blocks.add(RedstoneBlockRegistry.REDSTONE_PIPE_REPEATER);
        blocks.add(DecorationBlockRegistry.TINTING_TABLE);
        blocks.add(DecorationBlockRegistry.CACTUS_LADDER);
        blocks.add(DecorationBlockRegistry.GLOWING_CANVAS_BLOCK);
    }

    private static void translucentLayerBlocks(List<Block> blocks) {
        blocks.add(BuildingBlockRegistry.WHITE_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.ORANGE_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.MAGENTA_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.LIGHT_BLUE_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.YELLOW_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.LIME_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.PINK_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.GRAY_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.LIGHT_GRAY_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.CYAN_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.PURPLE_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.BLUE_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.BROWN_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.GREEN_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.RED_STAINED_SHATTERED_GLASS);
        blocks.add(BuildingBlockRegistry.BLACK_STAINED_SHATTERED_GLASS);
    }
}
