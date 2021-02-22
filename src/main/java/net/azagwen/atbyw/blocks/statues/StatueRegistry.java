package net.azagwen.atbyw.blocks.statues;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import static net.azagwen.atbyw.blocks.AtbywBlockUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class StatueRegistry {
    private static final FabricBlockSettings statueSettings = FabricBlockSettings.copyOf(Blocks.STONE).breakByTool(FabricToolTags.PICKAXES).ticksRandomly();

    public static final Block WAXED_CLEAN_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_EXPOSED_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_DIRTY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_MOSSY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_VERY_MOSSY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block BEE_STATUE = new StatueBlock(true, StatueBlockMobTypes.BEE, statueSettings, WAXED_CLEAN_BEE_STATUE, WAXED_EXPOSED_BEE_STATUE, WAXED_DIRTY_BEE_STATUE, WAXED_MOSSY_BEE_STATUE, WAXED_VERY_MOSSY_BEE_STATUE);

    public static final Block WAXED_CLEAN_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_EXPOSED_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_DIRTY_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_MOSSY_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block SILVERFISH_STATUE = new StatueBlock(false, StatueBlockMobTypes.SILVERFISH, statueSettings, WAXED_CLEAN_SILVERFISH_STATUE, WAXED_EXPOSED_SILVERFISH_STATUE, WAXED_DIRTY_SILVERFISH_STATUE, WAXED_MOSSY_SILVERFISH_STATUE, WAXED_VERY_MOSSY_SILVERFISH_STATUE);

    public static final Block WAXED_CLEAN_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_EXPOSED_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_DIRTY_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_MOSSY_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_VERY_MOSSY_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block ENDERMITE_STATUE = new StatueBlock(false, StatueBlockMobTypes.ENDERMITE, statueSettings, WAXED_CLEAN_ENDERMITE_STATUE, WAXED_EXPOSED_ENDERMITE_STATUE, WAXED_DIRTY_ENDERMITE_STATUE, WAXED_MOSSY_ENDERMITE_STATUE, WAXED_VERY_MOSSY_ENDERMITE_STATUE);

    public static final Block WAXED_CLEAN_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_EXPOSED_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_DIRTY_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_MOSSY_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block SHULKER_STATUE = new ShulkerStatueBlock(true, StatueBlockMobTypes.SHULKER, statueSettings, WAXED_CLEAN_SHULKER_STATUE, WAXED_EXPOSED_SHULKER_STATUE, WAXED_DIRTY_SHULKER_STATUE, WAXED_MOSSY_SHULKER_STATUE, WAXED_VERY_MOSSY_SHULKER_STATUE);

    public static final Block WAXED_CLEAN_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_EXPOSED_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_DIRTY_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_MOSSY_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_VERY_MOSSY_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WOLF_STATUE = new StatueBlock(false, StatueBlockMobTypes.WOLF, statueSettings, WAXED_CLEAN_WOLF_STATUE, WAXED_EXPOSED_WOLF_STATUE, WAXED_DIRTY_WOLF_STATUE, WAXED_MOSSY_WOLF_STATUE, WAXED_VERY_MOSSY_WOLF_STATUE);

    public static final Block WAXED_CLEAN_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_EXPOSED_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_DIRTY_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_MOSSY_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_VERY_MOSSY_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block CAT_STATUE = new StatueBlock(true, StatueBlockMobTypes.CAT, statueSettings, WAXED_CLEAN_CAT_STATUE, WAXED_EXPOSED_CAT_STATUE, WAXED_DIRTY_CAT_STATUE, WAXED_MOSSY_CAT_STATUE, WAXED_VERY_MOSSY_CAT_STATUE);

    public static final Block WAXED_CLEAN_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_EXPOSED_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_DIRTY_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_MOSSY_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_VERY_MOSSY_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block CHICKEN_STATUE = new StatueBlock(true, StatueBlockMobTypes.CHICKEN, statueSettings, WAXED_CLEAN_CHICKEN_STATUE, WAXED_EXPOSED_CHICKEN_STATUE, WAXED_DIRTY_CHICKEN_STATUE, WAXED_MOSSY_CHICKEN_STATUE, WAXED_VERY_MOSSY_CHICKEN_STATUE);

    public static final Block WAXED_CLEAN_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_EXPOSED_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_DIRTY_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_MOSSY_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_VERY_MOSSY_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block RABBIT_STATUE = new StatueBlock(true, StatueBlockMobTypes.RABBIT, statueSettings, WAXED_CLEAN_RABBIT_STATUE, WAXED_EXPOSED_RABBIT_STATUE, WAXED_DIRTY_RABBIT_STATUE, WAXED_MOSSY_RABBIT_STATUE, WAXED_VERY_MOSSY_RABBIT_STATUE);

    public static final Block WAXED_CLEAN_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_EXPOSED_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_DIRTY_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_MOSSY_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_VERY_MOSSY_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block FOX_STATUE = new StatueBlock(false, StatueBlockMobTypes.FOX, statueSettings, WAXED_CLEAN_FOX_STATUE, WAXED_EXPOSED_FOX_STATUE, WAXED_DIRTY_FOX_STATUE, WAXED_MOSSY_FOX_STATUE, WAXED_VERY_MOSSY_FOX_STATUE);

    public static final Block WAXED_CLEAN_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_EXPOSED_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_DIRTY_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_MOSSY_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_VERY_MOSSY_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block COD_STATUE = new StatueBlock(true, StatueBlockMobTypes.COD, statueSettings, WAXED_CLEAN_COD_STATUE, WAXED_EXPOSED_COD_STATUE, WAXED_DIRTY_COD_STATUE, WAXED_MOSSY_COD_STATUE, WAXED_VERY_MOSSY_COD_STATUE);

    public static final Block WAXED_CLEAN_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_EXPOSED_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_DIRTY_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_MOSSY_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block SALMON_STATUE = new StatueBlock(true, StatueBlockMobTypes.SALMON, statueSettings, WAXED_CLEAN_SALMON_STATUE, WAXED_EXPOSED_SALMON_STATUE, WAXED_DIRTY_SALMON_STATUE, WAXED_MOSSY_SALMON_STATUE, WAXED_VERY_MOSSY_SALMON_STATUE);

    public static final Block WAXED_CLEAN_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_EXPOSED_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_DIRTY_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_MOSSY_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_VERY_MOSSY_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block PUFFER_FISH_STATUE = new StatueBlock(true, StatueBlockMobTypes.PUFFER_FISH, statueSettings, WAXED_CLEAN_PUFFER_FISH_STATUE, WAXED_EXPOSED_PUFFER_FISH_STATUE, WAXED_DIRTY_PUFFER_FISH_STATUE, WAXED_MOSSY_PUFFER_FISH_STATUE, WAXED_VERY_MOSSY_PUFFER_FISH_STATUE);

    public static final Block WAXED_CLEAN_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_EXPOSED_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_DIRTY_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_MOSSY_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block SLIME_STATUE = new StatueBlock(true, StatueBlockMobTypes.SLIME, statueSettings, WAXED_CLEAN_SLIME_STATUE, WAXED_EXPOSED_SLIME_STATUE, WAXED_DIRTY_SLIME_STATUE, WAXED_MOSSY_SLIME_STATUE, WAXED_VERY_MOSSY_SLIME_STATUE);

    public static final Block WAXED_CLEAN_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_EXPOSED_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_DIRTY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_MOSSY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block MAGMA_CUBE_STATUE = new StatueBlock(true, StatueBlockMobTypes.MAGMA_CUBE, statueSettings, WAXED_CLEAN_MAGMA_CUBE_STATUE, WAXED_EXPOSED_MAGMA_CUBE_STATUE, WAXED_DIRTY_MAGMA_CUBE_STATUE, WAXED_MOSSY_MAGMA_CUBE_STATUE, WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE);

    //TODO: Add signing fish function.
    //TODO: Make slime statues combine-able.

    private static final String[] statuePrefixes = new String[] {
            "",
            "waxed_clean",
            "waxed_exposed",
            "waxed_dirty",
            "waxed_mossy",
            "waxed_very_mossy"
    };

    /** Registers a set number of statues
     *  determined by the prefix (statuePrefixes) array length
     *  and a name corresponding with the input animal String.
     *
     *  @param animal   Name of the animal the statues represent
     *  @param blocks   The Blocks to register, must match with "statuePrefixes"
     */
    private static void registerStatues(String animal, Block... blocks) {
        for (int i = 0; i < blocks.length; i++) {
            String prefix = statuePrefixes[i];
            Block block = blocks[i];
            String name;

            if (prefix.isEmpty())
                name = String.join("_", animal, "statue");
            else
                name = String.join("_", prefix, animal, "statue");

            registerBlock(false, ATBYW_DECO, name, block);
        }
    }

    public static void initStatues() {
        registerStatues("bee", BEE_STATUE, WAXED_CLEAN_BEE_STATUE, WAXED_EXPOSED_BEE_STATUE, WAXED_DIRTY_BEE_STATUE, WAXED_MOSSY_BEE_STATUE, WAXED_VERY_MOSSY_BEE_STATUE);
        registerStatues("silverfish", SILVERFISH_STATUE, WAXED_CLEAN_SILVERFISH_STATUE, WAXED_EXPOSED_SILVERFISH_STATUE, WAXED_DIRTY_SILVERFISH_STATUE, WAXED_MOSSY_SILVERFISH_STATUE, WAXED_VERY_MOSSY_SILVERFISH_STATUE);
        registerStatues("endermite", ENDERMITE_STATUE, WAXED_CLEAN_ENDERMITE_STATUE, WAXED_EXPOSED_ENDERMITE_STATUE, WAXED_DIRTY_ENDERMITE_STATUE, WAXED_MOSSY_ENDERMITE_STATUE, WAXED_VERY_MOSSY_ENDERMITE_STATUE);
        registerStatues("shulker", SHULKER_STATUE, WAXED_CLEAN_SHULKER_STATUE, WAXED_EXPOSED_SHULKER_STATUE, WAXED_DIRTY_SHULKER_STATUE, WAXED_MOSSY_SHULKER_STATUE, WAXED_VERY_MOSSY_SHULKER_STATUE);
        registerStatues("wolf", WOLF_STATUE, WAXED_CLEAN_WOLF_STATUE, WAXED_EXPOSED_WOLF_STATUE, WAXED_DIRTY_WOLF_STATUE, WAXED_MOSSY_WOLF_STATUE, WAXED_VERY_MOSSY_WOLF_STATUE);
        registerStatues("cat", CAT_STATUE, WAXED_CLEAN_CAT_STATUE, WAXED_EXPOSED_CAT_STATUE, WAXED_DIRTY_CAT_STATUE, WAXED_MOSSY_CAT_STATUE, WAXED_VERY_MOSSY_CAT_STATUE);
        registerStatues("chicken", CHICKEN_STATUE, WAXED_CLEAN_CHICKEN_STATUE, WAXED_EXPOSED_CHICKEN_STATUE, WAXED_DIRTY_CHICKEN_STATUE, WAXED_MOSSY_CHICKEN_STATUE, WAXED_VERY_MOSSY_CHICKEN_STATUE);
        registerStatues("rabbit", RABBIT_STATUE, WAXED_CLEAN_RABBIT_STATUE, WAXED_EXPOSED_RABBIT_STATUE, WAXED_DIRTY_RABBIT_STATUE, WAXED_MOSSY_RABBIT_STATUE, WAXED_VERY_MOSSY_RABBIT_STATUE);
        registerStatues("fox", FOX_STATUE, WAXED_CLEAN_FOX_STATUE, WAXED_EXPOSED_FOX_STATUE, WAXED_DIRTY_FOX_STATUE, WAXED_MOSSY_FOX_STATUE, WAXED_VERY_MOSSY_FOX_STATUE);
        registerStatues("cod", COD_STATUE, WAXED_CLEAN_COD_STATUE, WAXED_EXPOSED_COD_STATUE, WAXED_DIRTY_COD_STATUE, WAXED_MOSSY_COD_STATUE, WAXED_VERY_MOSSY_COD_STATUE);
        registerStatues("salmon", SALMON_STATUE, WAXED_CLEAN_SALMON_STATUE, WAXED_EXPOSED_SALMON_STATUE, WAXED_DIRTY_SALMON_STATUE, WAXED_MOSSY_SALMON_STATUE, WAXED_VERY_MOSSY_SALMON_STATUE);
        registerStatues("puffer_fish", PUFFER_FISH_STATUE, WAXED_CLEAN_PUFFER_FISH_STATUE, WAXED_EXPOSED_PUFFER_FISH_STATUE, WAXED_DIRTY_PUFFER_FISH_STATUE, WAXED_MOSSY_PUFFER_FISH_STATUE, WAXED_VERY_MOSSY_PUFFER_FISH_STATUE);
        registerStatues("slime", SLIME_STATUE, WAXED_CLEAN_SLIME_STATUE, WAXED_EXPOSED_SLIME_STATUE, WAXED_DIRTY_SLIME_STATUE, WAXED_MOSSY_SLIME_STATUE, WAXED_VERY_MOSSY_SLIME_STATUE);
        registerStatues("magma_cube", MAGMA_CUBE_STATUE, WAXED_CLEAN_MAGMA_CUBE_STATUE, WAXED_EXPOSED_MAGMA_CUBE_STATUE, WAXED_DIRTY_MAGMA_CUBE_STATUE, WAXED_MOSSY_MAGMA_CUBE_STATUE, WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE);
    }
}
