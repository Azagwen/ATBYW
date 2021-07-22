package net.azagwen.atbyw.client.render;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;

import java.util.List;

public class AtbywBlockRenderLayers {

    public static void init() {
        var cutoutBlocks = Lists.<Block>newArrayList();
        var translucentBlocks = Lists.<Block>newArrayList();

        cutoutLayerBlocks(cutoutBlocks);
        translucentLayerBlocks(translucentBlocks);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), cutoutBlocks.toArray(Block[]::new));
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), translucentBlocks.toArray(Block[]::new));
    }

    private static void cutoutLayerBlocks(List<Block> blocks) {
        blocks.add(AtbywBlocks.GRASS_BLOCK_STAIRS);
        blocks.add(AtbywBlocks.GRASS_BLOCK_SLAB);
        blocks.add(AtbywBlocks.IRON_SPIKE_TRAP_SPIKES);
        blocks.add(AtbywBlocks.GOLD_SPIKE_TRAP_SPIKES);
        blocks.add(AtbywBlocks.DIAMOND_SPIKE_TRAP_SPIKES);
        blocks.add(AtbywBlocks.NETHERITE_SPIKE_TRAP_SPIKES);
        blocks.add(AtbywBlocks.REDSTONE_LANTERN);
        blocks.add(AtbywBlocks.DANDELION_PULL_SWITCH);
        blocks.add(AtbywBlocks.POPPY_PULL_SWITCH);
        blocks.add(AtbywBlocks.BLUE_ORCHID_PULL_SWITCH);
        blocks.add(AtbywBlocks.ALLIUM_PULL_SWITCH);
        blocks.add(AtbywBlocks.AZURE_BLUET_PULL_SWITCH);
        blocks.add(AtbywBlocks.RED_TULIP_PULL_SWITCH);
        blocks.add(AtbywBlocks.ORANGE_TULIP_PULL_SWITCH);
        blocks.add(AtbywBlocks.WHITE_TULIP_PULL_SWITCH);
        blocks.add(AtbywBlocks.PINK_TULIP_PULL_SWITCH);
        blocks.add(AtbywBlocks.OXEYE_DAISY_PULL_SWITCH);
        blocks.add(AtbywBlocks.CORNFLOWER_PULL_SWITCH);
        blocks.add(AtbywBlocks.WITHER_ROSE_PULL_SWITCH);
        blocks.add(AtbywBlocks.LILY_OF_THE_VALLEY_PULL_SWITCH);
        blocks.add(AtbywBlocks.OAK_FENCE_DOOR);
        blocks.add(AtbywBlocks.SPRUCE_FENCE_DOOR);
        blocks.add(AtbywBlocks.BIRCH_FENCE_DOOR);
        blocks.add(AtbywBlocks.JUNGLE_FENCE_DOOR);
        blocks.add(AtbywBlocks.ACACIA_FENCE_DOOR);
        blocks.add(AtbywBlocks.DARK_OAK_FENCE_DOOR);
        blocks.add(AtbywBlocks.CRIMSON_FENCE_DOOR);
        blocks.add(AtbywBlocks.WARPED_FENCE_DOOR);
        blocks.add(AtbywBlocks.IRON_FENCE_DOOR);
        blocks.add(AtbywBlocks.SPRUCE_LADDER);
        blocks.add(AtbywBlocks.BIRCH_LADDER);
        blocks.add(AtbywBlocks.JUNGLE_LADDER);
        blocks.add(AtbywBlocks.ACACIA_LADDER);
        blocks.add(AtbywBlocks.DARK_OAK_LADDER);
        blocks.add(AtbywBlocks.CRIMSON_LADDER);
        blocks.add(AtbywBlocks.WARPED_LADDER);
        blocks.add(AtbywBlocks.BAMBOO_LADDER);
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
        blocks.add(AtbywBlocks.TIMER_REPEATER);
        blocks.add(AtbywBlocks.LARGE_CHAIN);
        blocks.add(AtbywBlocks.SHATTERED_GLASS);
        blocks.add(AtbywBlocks.REDSTONE_PIPE);
        blocks.add(AtbywBlocks.REDSTONE_PIPE_REPEATER);
    }

    private static void translucentLayerBlocks(List<Block> blocks) {
        blocks.add(AtbywBlocks.WHITE_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.ORANGE_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.MAGENTA_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.LIGHT_BLUE_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.YELLOW_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.LIME_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.PINK_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.GRAY_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.LIGHT_GRAY_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.CYAN_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.PURPLE_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.BLUE_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.BROWN_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.GREEN_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.RED_STAINED_SHATTERED_GLASS);
        blocks.add(AtbywBlocks.BLACK_STAINED_SHATTERED_GLASS);
    }
}
