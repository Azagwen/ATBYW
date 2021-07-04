package net.azagwen.atbyw.block.statues;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;

import java.util.List;

import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;
import static net.azagwen.atbyw.util.BlockUtils.registerBlock;

public class StatueRegistry {
    private static final FabricBlockSettings statueSettings = FabricBlockSettings.copyOf(Blocks.STONE).breakByTool(FabricToolTags.PICKAXES).ticksRandomly();
    private static final FabricBlockSettings waxedStatueSettings = FabricBlockSettings.copyOf(Blocks.STONE).breakByTool(FabricToolTags.PICKAXES);

    public static final List<Block> BAT_STATUES = Lists.newArrayList();
    public static final Block BAT_STATUE = new StatueBlock(BAT_STATUES, StatueBlockMobTypes.BAT, statueSettings);
    public static final Block WAXED_CLEAN_BAT_STATUE = new WaxedStatueBlock(0, BAT_STATUES, StatueBlockMobTypes.BAT, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_BAT_STATUE = new WaxedStatueBlock(1, BAT_STATUES, StatueBlockMobTypes.BAT, waxedStatueSettings);
    public static final Block WAXED_DIRTY_BAT_STATUE = new WaxedStatueBlock(2, BAT_STATUES, StatueBlockMobTypes.BAT, waxedStatueSettings);
    public static final Block WAXED_MOSSY_BAT_STATUE = new WaxedStatueBlock(3, BAT_STATUES, StatueBlockMobTypes.BAT, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_BAT_STATUE = new WaxedStatueBlock(4, BAT_STATUES, StatueBlockMobTypes.BAT, waxedStatueSettings);

    public static final List<Block> BEE_STATUES = Lists.newArrayList();
    public static final Block BEE_STATUE = new StatueBlock(BEE_STATUES, StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_CLEAN_BEE_STATUE = new WaxedStatueBlock(0, BEE_STATUES, StatueBlockMobTypes.BEE, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_BEE_STATUE = new WaxedStatueBlock(1, BEE_STATUES, StatueBlockMobTypes.BEE, waxedStatueSettings);
    public static final Block WAXED_DIRTY_BEE_STATUE = new WaxedStatueBlock(2, BEE_STATUES, StatueBlockMobTypes.BEE, waxedStatueSettings);
    public static final Block WAXED_MOSSY_BEE_STATUE = new WaxedStatueBlock(3, BEE_STATUES, StatueBlockMobTypes.BEE, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_BEE_STATUE = new WaxedStatueBlock(4, BEE_STATUES, StatueBlockMobTypes.BEE, waxedStatueSettings);

    public static final List<Block> SILVERFISH_STATUES = Lists.newArrayList();
    public static final Block SILVERFISH_STATUE = new StatueBlock(SILVERFISH_STATUES, StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_CLEAN_SILVERFISH_STATUE = new WaxedStatueBlock(0, SILVERFISH_STATUES, StatueBlockMobTypes.SILVERFISH, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_SILVERFISH_STATUE = new WaxedStatueBlock(1, SILVERFISH_STATUES, StatueBlockMobTypes.SILVERFISH, waxedStatueSettings);
    public static final Block WAXED_DIRTY_SILVERFISH_STATUE = new WaxedStatueBlock(2, SILVERFISH_STATUES, StatueBlockMobTypes.SILVERFISH, waxedStatueSettings);
    public static final Block WAXED_MOSSY_SILVERFISH_STATUE = new WaxedStatueBlock(3, SILVERFISH_STATUES, StatueBlockMobTypes.SILVERFISH, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_SILVERFISH_STATUE = new WaxedStatueBlock(4, SILVERFISH_STATUES, StatueBlockMobTypes.SILVERFISH, waxedStatueSettings);

    public static final List<Block> ENDERMITE_STATUES = Lists.newArrayList();
    public static final Block ENDERMITE_STATUE = new StatueBlock(ENDERMITE_STATUES, StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_CLEAN_ENDERMITE_STATUE = new WaxedStatueBlock(0, ENDERMITE_STATUES, StatueBlockMobTypes.ENDERMITE, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_ENDERMITE_STATUE = new WaxedStatueBlock(1, ENDERMITE_STATUES, StatueBlockMobTypes.ENDERMITE, waxedStatueSettings);
    public static final Block WAXED_DIRTY_ENDERMITE_STATUE = new WaxedStatueBlock(2, ENDERMITE_STATUES, StatueBlockMobTypes.ENDERMITE, waxedStatueSettings);
    public static final Block WAXED_MOSSY_ENDERMITE_STATUE = new WaxedStatueBlock(3, ENDERMITE_STATUES, StatueBlockMobTypes.ENDERMITE, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_ENDERMITE_STATUE = new WaxedStatueBlock(4, ENDERMITE_STATUES, StatueBlockMobTypes.ENDERMITE, waxedStatueSettings);

    public static final List<Block> SHULKER_STATUES = Lists.newArrayList();
    public static final Block WAXED_CLEAN_SHULKER_STATUE = new WaxedShulkerStatueBlock(0, SHULKER_STATUES, StatueBlockMobTypes.SHULKER, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_SHULKER_STATUE = new WaxedShulkerStatueBlock(1, SHULKER_STATUES, StatueBlockMobTypes.SHULKER, waxedStatueSettings);
    public static final Block WAXED_DIRTY_SHULKER_STATUE = new WaxedShulkerStatueBlock(2, SHULKER_STATUES, StatueBlockMobTypes.SHULKER, waxedStatueSettings);
    public static final Block WAXED_MOSSY_SHULKER_STATUE = new WaxedShulkerStatueBlock(3, SHULKER_STATUES, StatueBlockMobTypes.SHULKER, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_SHULKER_STATUE = new WaxedShulkerStatueBlock(4, SHULKER_STATUES, StatueBlockMobTypes.SHULKER, waxedStatueSettings);
    public static final Block SHULKER_STATUE = new ShulkerStatueBlock(SHULKER_STATUES, StatueBlockMobTypes.SHULKER, statueSettings);

    public static final List<Block> AXOLOTL_STATUES = Lists.newArrayList();
    public static final Block AXOLOTL_STATUE = new StatueBlock(AXOLOTL_STATUES, StatueBlockMobTypes.AXOLOTL, statueSettings);
    public static final Block WAXED_CLEAN_AXOLOTL_STATUE = new WaxedStatueBlock(0, AXOLOTL_STATUES, StatueBlockMobTypes.AXOLOTL, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_AXOLOTL_STATUE = new WaxedStatueBlock(1, AXOLOTL_STATUES, StatueBlockMobTypes.AXOLOTL, waxedStatueSettings);
    public static final Block WAXED_DIRTY_AXOLOTL_STATUE = new WaxedStatueBlock(2, AXOLOTL_STATUES, StatueBlockMobTypes.AXOLOTL, waxedStatueSettings);
    public static final Block WAXED_MOSSY_AXOLOTL_STATUE = new WaxedStatueBlock(3, AXOLOTL_STATUES, StatueBlockMobTypes.AXOLOTL, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_AXOLOTL_STATUE = new WaxedStatueBlock(4, AXOLOTL_STATUES, StatueBlockMobTypes.AXOLOTL, waxedStatueSettings);

    public static final List<Block> WOLF_STATUES = Lists.newArrayList();
    public static final Block WOLF_STATUE = new StatueBlock(WOLF_STATUES, StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_CLEAN_WOLF_STATUE = new WaxedStatueBlock(0, WOLF_STATUES, StatueBlockMobTypes.WOLF, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_WOLF_STATUE = new WaxedStatueBlock(1, WOLF_STATUES, StatueBlockMobTypes.WOLF, waxedStatueSettings);
    public static final Block WAXED_DIRTY_WOLF_STATUE = new WaxedStatueBlock(2, WOLF_STATUES, StatueBlockMobTypes.WOLF, waxedStatueSettings);
    public static final Block WAXED_MOSSY_WOLF_STATUE = new WaxedStatueBlock(3, WOLF_STATUES, StatueBlockMobTypes.WOLF, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_WOLF_STATUE = new WaxedStatueBlock(4, WOLF_STATUES, StatueBlockMobTypes.WOLF, waxedStatueSettings);

    public static final List<Block> CAT_STATUES = Lists.newArrayList();
    public static final Block CAT_STATUE = new StatueBlock(CAT_STATUES, StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_CLEAN_CAT_STATUE = new WaxedStatueBlock(0, CAT_STATUES, StatueBlockMobTypes.CAT, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_CAT_STATUE = new WaxedStatueBlock(1, CAT_STATUES, StatueBlockMobTypes.CAT, waxedStatueSettings);
    public static final Block WAXED_DIRTY_CAT_STATUE = new WaxedStatueBlock(2, CAT_STATUES, StatueBlockMobTypes.CAT, waxedStatueSettings);
    public static final Block WAXED_MOSSY_CAT_STATUE = new WaxedStatueBlock(3, CAT_STATUES, StatueBlockMobTypes.CAT, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_CAT_STATUE = new WaxedStatueBlock(4, CAT_STATUES, StatueBlockMobTypes.CAT, waxedStatueSettings);

    public static final List<Block> CHICKEN_STATUES = Lists.newArrayList();
    public static final Block CHICKEN_STATUE = new StatueBlock(CHICKEN_STATUES, StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_CLEAN_CHICKEN_STATUE = new WaxedStatueBlock(0, CHICKEN_STATUES, StatueBlockMobTypes.CHICKEN, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_CHICKEN_STATUE = new WaxedStatueBlock(1, CHICKEN_STATUES, StatueBlockMobTypes.CHICKEN, waxedStatueSettings);
    public static final Block WAXED_DIRTY_CHICKEN_STATUE = new WaxedStatueBlock(2, CHICKEN_STATUES, StatueBlockMobTypes.CHICKEN, waxedStatueSettings);
    public static final Block WAXED_MOSSY_CHICKEN_STATUE = new WaxedStatueBlock(3, CHICKEN_STATUES, StatueBlockMobTypes.CHICKEN, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_CHICKEN_STATUE = new WaxedStatueBlock(4, CHICKEN_STATUES, StatueBlockMobTypes.CHICKEN, waxedStatueSettings);

    public static final List<Block> RABBIT_STATUES = Lists.newArrayList();
    public static final Block RABBIT_STATUE = new StatueBlock(RABBIT_STATUES, StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_CLEAN_RABBIT_STATUE = new WaxedStatueBlock(0, RABBIT_STATUES, StatueBlockMobTypes.RABBIT, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_RABBIT_STATUE = new WaxedStatueBlock(1, RABBIT_STATUES, StatueBlockMobTypes.RABBIT, waxedStatueSettings);
    public static final Block WAXED_DIRTY_RABBIT_STATUE = new WaxedStatueBlock(2, RABBIT_STATUES, StatueBlockMobTypes.RABBIT, waxedStatueSettings);
    public static final Block WAXED_MOSSY_RABBIT_STATUE = new WaxedStatueBlock(3, RABBIT_STATUES, StatueBlockMobTypes.RABBIT, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_RABBIT_STATUE = new WaxedStatueBlock(4, RABBIT_STATUES, StatueBlockMobTypes.RABBIT, waxedStatueSettings);

    public static final List<Block> FOX_STATUES = Lists.newArrayList();
    public static final Block FOX_STATUE = new StatueBlock(FOX_STATUES, StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_CLEAN_FOX_STATUE = new WaxedStatueBlock(0, FOX_STATUES, StatueBlockMobTypes.FOX, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_FOX_STATUE = new WaxedStatueBlock(1, FOX_STATUES, StatueBlockMobTypes.FOX, waxedStatueSettings);
    public static final Block WAXED_DIRTY_FOX_STATUE = new WaxedStatueBlock(2, FOX_STATUES, StatueBlockMobTypes.FOX, waxedStatueSettings);
    public static final Block WAXED_MOSSY_FOX_STATUE = new WaxedStatueBlock(3, FOX_STATUES, StatueBlockMobTypes.FOX, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_FOX_STATUE = new WaxedStatueBlock(4, FOX_STATUES, StatueBlockMobTypes.FOX, waxedStatueSettings);

    public static final List<Block> COD_STATUES = Lists.newArrayList();
    public static final Block COD_STATUE = new StatueBlock(COD_STATUES, StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_CLEAN_COD_STATUE = new WaxedStatueBlock(0, COD_STATUES, StatueBlockMobTypes.COD, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_COD_STATUE = new WaxedStatueBlock(1, COD_STATUES, StatueBlockMobTypes.COD, waxedStatueSettings);
    public static final Block WAXED_DIRTY_COD_STATUE = new WaxedStatueBlock(2, COD_STATUES, StatueBlockMobTypes.COD, waxedStatueSettings);
    public static final Block WAXED_MOSSY_COD_STATUE = new WaxedStatueBlock(3, COD_STATUES, StatueBlockMobTypes.COD, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_COD_STATUE = new WaxedStatueBlock(4, COD_STATUES, StatueBlockMobTypes.COD, waxedStatueSettings);

    public static final List<Block> SALMON_STATUES = Lists.newArrayList();
    public static final Block SALMON_STATUE = new StatueBlock(SALMON_STATUES, StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_CLEAN_SALMON_STATUE = new WaxedStatueBlock(0, SALMON_STATUES, StatueBlockMobTypes.SALMON, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_SALMON_STATUE = new WaxedStatueBlock(1, SALMON_STATUES, StatueBlockMobTypes.SALMON, waxedStatueSettings);
    public static final Block WAXED_DIRTY_SALMON_STATUE = new WaxedStatueBlock(2, SALMON_STATUES, StatueBlockMobTypes.SALMON, waxedStatueSettings);
    public static final Block WAXED_MOSSY_SALMON_STATUE = new WaxedStatueBlock(3, SALMON_STATUES, StatueBlockMobTypes.SALMON, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_SALMON_STATUE = new WaxedStatueBlock(4, SALMON_STATUES, StatueBlockMobTypes.SALMON, waxedStatueSettings);

    public static final List<Block> PUFFER_FISH_STATUES = Lists.newArrayList();
    public static final Block PUFFER_FISH_STATUE = new StatueBlock(PUFFER_FISH_STATUES, StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_CLEAN_PUFFER_FISH_STATUE = new WaxedStatueBlock(0, PUFFER_FISH_STATUES, StatueBlockMobTypes.PUFFER_FISH, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_PUFFER_FISH_STATUE = new WaxedStatueBlock(1, PUFFER_FISH_STATUES, StatueBlockMobTypes.PUFFER_FISH, waxedStatueSettings);
    public static final Block WAXED_DIRTY_PUFFER_FISH_STATUE = new WaxedStatueBlock(2, PUFFER_FISH_STATUES, StatueBlockMobTypes.PUFFER_FISH, waxedStatueSettings);
    public static final Block WAXED_MOSSY_PUFFER_FISH_STATUE = new WaxedStatueBlock(3, PUFFER_FISH_STATUES, StatueBlockMobTypes.PUFFER_FISH, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_PUFFER_FISH_STATUE = new WaxedStatueBlock(4, PUFFER_FISH_STATUES, StatueBlockMobTypes.PUFFER_FISH, waxedStatueSettings);

    public static final List<Block> SLIME_STATUES = Lists.newArrayList();
    public static final Block SLIME_STATUE = new StatueBlock(SLIME_STATUES, StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_CLEAN_SLIME_STATUE = new WaxedStatueBlock(0, SLIME_STATUES, StatueBlockMobTypes.SLIME, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_SLIME_STATUE = new WaxedStatueBlock(1, SLIME_STATUES, StatueBlockMobTypes.SLIME, waxedStatueSettings);
    public static final Block WAXED_DIRTY_SLIME_STATUE = new WaxedStatueBlock(2, SLIME_STATUES, StatueBlockMobTypes.SLIME, waxedStatueSettings);
    public static final Block WAXED_MOSSY_SLIME_STATUE = new WaxedStatueBlock(3, SLIME_STATUES, StatueBlockMobTypes.SLIME, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_SLIME_STATUE = new WaxedStatueBlock(4, SLIME_STATUES, StatueBlockMobTypes.SLIME, waxedStatueSettings);

    public static final List<Block> MAGMA_CUBE_STATUES = Lists.newArrayList();
    public static final Block MAGMA_CUBE_STATUE = new StatueBlock(MAGMA_CUBE_STATUES, StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_CLEAN_MAGMA_CUBE_STATUE = new WaxedStatueBlock(0, MAGMA_CUBE_STATUES, StatueBlockMobTypes.MAGMA_CUBE, waxedStatueSettings);
    public static final Block WAXED_EXPOSED_MAGMA_CUBE_STATUE = new WaxedStatueBlock(1, MAGMA_CUBE_STATUES, StatueBlockMobTypes.MAGMA_CUBE, waxedStatueSettings);
    public static final Block WAXED_DIRTY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(2, MAGMA_CUBE_STATUES, StatueBlockMobTypes.MAGMA_CUBE, waxedStatueSettings);
    public static final Block WAXED_MOSSY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(3, MAGMA_CUBE_STATUES, StatueBlockMobTypes.MAGMA_CUBE, waxedStatueSettings);
    public static final Block WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(4, MAGMA_CUBE_STATUES, StatueBlockMobTypes.MAGMA_CUBE, waxedStatueSettings);

    /** Registers a set number of statues
     *  determined by the prefix (statuePrefixes) array length
     *  and a name corresponding with the input animal String.
     *
     *  @param animal   Name of the animal the statues represent.
     *  @param statues  Ordered List of the statues to register (cleanWaxed, exposedWaxed, dirtyWaxed, mossyWaxed, veryMossyWaxed, normalStatue)
     */
    private static void registerStatues(String animal, List<Block> statues) {
        var normalStatue = (Block) null;
        var cleanWaxed = (Block) null;
        var exposedWaxed = (Block) null;
        var dirtyWaxed = (Block) null;
        var mossyWaxed = (Block) null;
        var veryMossyWaxed = (Block) null;

        for (var statue : statues) {
            if (statue instanceof StatueBlock) {
                normalStatue = statue;
            }
            if (statue instanceof WaxedStatueBlock) {
                switch(((WaxedStatueBlock) statue).getMossLevel()) {
                    case 0 -> cleanWaxed = statue;
                    case 1 -> exposedWaxed = statue;
                    case 2 -> dirtyWaxed = statue;
                    case 3 -> mossyWaxed = statue;
                    case 4 -> veryMossyWaxed = statue;
                }
            }
        }

        registerBlock(false, DECO_TAB, String.join("_", animal, "statue"), normalStatue);
        registerBlock(false, DECO_TAB, String.join("_", "waxed_clean", animal, "statue"), cleanWaxed);
        registerBlock(false, DECO_TAB, String.join("_", "waxed_exposed", animal, "statue"), exposedWaxed);
        registerBlock(false, DECO_TAB, String.join("_", "waxed_dirty", animal, "statue"), dirtyWaxed);
        registerBlock(false, DECO_TAB, String.join("_", "waxed_mossy", animal, "statue"), mossyWaxed);
        registerBlock(false, DECO_TAB, String.join("_", "waxed_very_mossy", animal, "statue"), veryMossyWaxed);
    }

    public static void initStatues() {
        registerStatues("axolotl", AXOLOTL_STATUES);
        registerStatues("bat", BAT_STATUES);
        registerStatues("bee", BEE_STATUES);
        registerStatues("cat", CAT_STATUES);
        registerStatues("chicken", CHICKEN_STATUES);
        registerStatues("cod", COD_STATUES);
        registerStatues("endermite", ENDERMITE_STATUES);
        registerStatues("fox", FOX_STATUES);
        registerStatues("magma_cube", MAGMA_CUBE_STATUES);
        registerStatues("puffer_fish", PUFFER_FISH_STATUES);
        registerStatues("rabbit", RABBIT_STATUES);
        registerStatues("salmon", SALMON_STATUES);
        registerStatues("shulker", SHULKER_STATUES);
        registerStatues("silverfish", SILVERFISH_STATUES);
        registerStatues("slime", SLIME_STATUES);
        registerStatues("wolf", WOLF_STATUES);
    }
}
