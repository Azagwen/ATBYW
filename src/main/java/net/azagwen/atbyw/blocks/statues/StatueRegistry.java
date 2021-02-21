package net.azagwen.atbyw.blocks.statues;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import static net.azagwen.atbyw.blocks.AtbywBlockUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.ATBYW_MISC;

public class StatueRegistry {
    private static final FabricBlockSettings statueSettings = FabricBlockSettings.copyOf(Blocks.STONE).breakByTool(FabricToolTags.PICKAXES).ticksRandomly();

    public static final Block WAXED_CLEAN_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_EXPOSED_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_DIRTY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_MOSSY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_VERY_MOSSY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block BEE_STATUE = new StatueBlock(false, new Block[] {
            WAXED_CLEAN_BEE_STATUE,
            WAXED_EXPOSED_BEE_STATUE,
            WAXED_DIRTY_BEE_STATUE,
            WAXED_MOSSY_BEE_STATUE,
            WAXED_VERY_MOSSY_BEE_STATUE
    }, StatueBlockMobTypes.BEE, statueSettings);

    public static final Block WAXED_CLEAN_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_EXPOSED_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_DIRTY_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_MOSSY_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SILVERFISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SILVERFISH, statueSettings);
    public static final Block SILVERFISH_STATUE = new StatueBlock(false, new Block[] {
            WAXED_CLEAN_SILVERFISH_STATUE,
            WAXED_EXPOSED_SILVERFISH_STATUE,
            WAXED_DIRTY_SILVERFISH_STATUE,
            WAXED_MOSSY_SILVERFISH_STATUE,
            WAXED_VERY_MOSSY_SILVERFISH_STATUE
    }, StatueBlockMobTypes.SILVERFISH, statueSettings);

    public static final Block WAXED_CLEAN_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_EXPOSED_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_DIRTY_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_MOSSY_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block WAXED_VERY_MOSSY_ENDERMITE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.ENDERMITE, statueSettings);
    public static final Block ENDERMITE_STATUE = new StatueBlock(false, new Block[] {
            WAXED_CLEAN_ENDERMITE_STATUE,
            WAXED_EXPOSED_ENDERMITE_STATUE,
            WAXED_DIRTY_ENDERMITE_STATUE,
            WAXED_MOSSY_ENDERMITE_STATUE,
            WAXED_VERY_MOSSY_ENDERMITE_STATUE
    }, StatueBlockMobTypes.ENDERMITE, statueSettings);

    public static final Block WAXED_CLEAN_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_EXPOSED_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_DIRTY_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_MOSSY_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SHULKER_STATUE = new WaxedShulkerStatueBlock(StatueBlockMobTypes.SHULKER, statueSettings);
    public static final Block SHULKER_STATUE = new ShulkerStatueBlock(true, new Block[] {
            WAXED_CLEAN_SHULKER_STATUE,
            WAXED_EXPOSED_SHULKER_STATUE,
            WAXED_DIRTY_SHULKER_STATUE,
            WAXED_MOSSY_SHULKER_STATUE,
            WAXED_VERY_MOSSY_SHULKER_STATUE
    }, StatueBlockMobTypes.SHULKER, statueSettings);

    public static final Block WAXED_CLEAN_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_EXPOSED_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_DIRTY_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_MOSSY_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WAXED_VERY_MOSSY_WOLF_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.WOLF, statueSettings);
    public static final Block WOLF_STATUE = new StatueBlock(false, new Block[] {
            WAXED_CLEAN_WOLF_STATUE,
            WAXED_EXPOSED_WOLF_STATUE,
            WAXED_DIRTY_WOLF_STATUE,
            WAXED_MOSSY_WOLF_STATUE,
            WAXED_VERY_MOSSY_WOLF_STATUE
    }, StatueBlockMobTypes.WOLF, statueSettings);

    public static final Block WAXED_CLEAN_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_EXPOSED_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_DIRTY_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_MOSSY_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block WAXED_VERY_MOSSY_CAT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CAT, statueSettings);
    public static final Block CAT_STATUE = new StatueBlock(true, new Block[] {
            WAXED_CLEAN_CAT_STATUE,
            WAXED_EXPOSED_CAT_STATUE,
            WAXED_DIRTY_CAT_STATUE,
            WAXED_MOSSY_CAT_STATUE,
            WAXED_VERY_MOSSY_CAT_STATUE
    }, StatueBlockMobTypes.CAT, statueSettings);

    public static final Block WAXED_CLEAN_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_EXPOSED_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_DIRTY_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_MOSSY_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block WAXED_VERY_MOSSY_CHICKEN_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.CHICKEN, statueSettings);
    public static final Block CHICKEN_STATUE = new StatueBlock(true, new Block[] {
            WAXED_CLEAN_CHICKEN_STATUE,
            WAXED_EXPOSED_CHICKEN_STATUE,
            WAXED_DIRTY_CHICKEN_STATUE,
            WAXED_MOSSY_CHICKEN_STATUE,
            WAXED_VERY_MOSSY_CHICKEN_STATUE
    }, StatueBlockMobTypes.CHICKEN, statueSettings);

    public static final Block WAXED_CLEAN_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_EXPOSED_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_DIRTY_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_MOSSY_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block WAXED_VERY_MOSSY_RABBIT_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.RABBIT, statueSettings);
    public static final Block RABBIT_STATUE = new StatueBlock(true, new Block[] {
            WAXED_CLEAN_RABBIT_STATUE,
            WAXED_EXPOSED_RABBIT_STATUE,
            WAXED_DIRTY_RABBIT_STATUE,
            WAXED_MOSSY_RABBIT_STATUE,
            WAXED_VERY_MOSSY_RABBIT_STATUE
    }, StatueBlockMobTypes.RABBIT, statueSettings);

    public static final Block WAXED_CLEAN_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_EXPOSED_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_DIRTY_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_MOSSY_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block WAXED_VERY_MOSSY_FOX_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.FOX, statueSettings);
    public static final Block FOX_STATUE = new StatueBlock(false, new Block[] {
            WAXED_CLEAN_FOX_STATUE,
            WAXED_EXPOSED_FOX_STATUE,
            WAXED_DIRTY_FOX_STATUE,
            WAXED_MOSSY_FOX_STATUE,
            WAXED_VERY_MOSSY_FOX_STATUE
    }, StatueBlockMobTypes.FOX, statueSettings);

    public static final Block WAXED_CLEAN_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_EXPOSED_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_DIRTY_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_MOSSY_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block WAXED_VERY_MOSSY_COD_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.COD, statueSettings);
    public static final Block COD_STATUE = new StatueBlock(true, new Block[] {
            WAXED_CLEAN_COD_STATUE,
            WAXED_EXPOSED_COD_STATUE,
            WAXED_DIRTY_COD_STATUE,
            WAXED_MOSSY_COD_STATUE,
            WAXED_VERY_MOSSY_COD_STATUE
    }, StatueBlockMobTypes.COD, statueSettings);

    public static final Block WAXED_CLEAN_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_EXPOSED_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_DIRTY_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_MOSSY_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SALMON_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SALMON, statueSettings);
    public static final Block SALMON_STATUE = new StatueBlock(true, new Block[] {
            WAXED_CLEAN_SALMON_STATUE,
            WAXED_EXPOSED_SALMON_STATUE,
            WAXED_DIRTY_SALMON_STATUE,
            WAXED_MOSSY_SALMON_STATUE,
            WAXED_VERY_MOSSY_SALMON_STATUE
    }, StatueBlockMobTypes.SALMON, statueSettings);

    public static final Block WAXED_CLEAN_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_EXPOSED_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_DIRTY_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_MOSSY_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block WAXED_VERY_MOSSY_PUFFER_FISH_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.PUFFER_FISH, statueSettings);
    public static final Block PUFFER_FISH_STATUE = new StatueBlock(true, new Block[] {
            WAXED_CLEAN_PUFFER_FISH_STATUE,
            WAXED_EXPOSED_PUFFER_FISH_STATUE,
            WAXED_DIRTY_PUFFER_FISH_STATUE,
            WAXED_MOSSY_PUFFER_FISH_STATUE,
            WAXED_VERY_MOSSY_PUFFER_FISH_STATUE
    }, StatueBlockMobTypes.PUFFER_FISH, statueSettings);

    public static final Block WAXED_CLEAN_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_EXPOSED_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_DIRTY_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_MOSSY_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block WAXED_VERY_MOSSY_SLIME_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.SLIME, statueSettings);
    public static final Block SLIME_STATUE = new StatueBlock(false, new Block[] {
            WAXED_CLEAN_SLIME_STATUE,
            WAXED_EXPOSED_SLIME_STATUE,
            WAXED_DIRTY_SLIME_STATUE,
            WAXED_MOSSY_SLIME_STATUE,
            WAXED_VERY_MOSSY_SLIME_STATUE
    }, StatueBlockMobTypes.SLIME, statueSettings);

    public static final Block WAXED_CLEAN_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_EXPOSED_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_DIRTY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_MOSSY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.MAGMA_CUBE, statueSettings);
    public static final Block MAGMA_CUBE_STATUE = new StatueBlock(false, new Block[] {
            WAXED_CLEAN_MAGMA_CUBE_STATUE,
            WAXED_EXPOSED_MAGMA_CUBE_STATUE,
            WAXED_DIRTY_MAGMA_CUBE_STATUE,
            WAXED_MOSSY_MAGMA_CUBE_STATUE,
            WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE
    }, StatueBlockMobTypes.MAGMA_CUBE, statueSettings);

    public static void initStatues() {
        registerBlock(false, ATBYW_MISC, "bee_statue", BEE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_bee_statue", WAXED_CLEAN_BEE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_bee_statue", WAXED_EXPOSED_BEE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_bee_statue", WAXED_DIRTY_BEE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_bee_statue", WAXED_MOSSY_BEE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_bee_statue", WAXED_VERY_MOSSY_BEE_STATUE);
        registerBlock(false, ATBYW_MISC, "silverfish_statue", SILVERFISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_silverfish_statue", WAXED_CLEAN_SILVERFISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_silverfish_statue", WAXED_EXPOSED_SILVERFISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_silverfish_statue", WAXED_DIRTY_SILVERFISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_silverfish_statue", WAXED_MOSSY_SILVERFISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_silverfish_statue", WAXED_VERY_MOSSY_SILVERFISH_STATUE);
        registerBlock(false, ATBYW_MISC, "endermite_statue", ENDERMITE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_endermite_statue", WAXED_CLEAN_ENDERMITE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_endermite_statue", WAXED_EXPOSED_ENDERMITE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_endermite_statue", WAXED_DIRTY_ENDERMITE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_endermite_statue", WAXED_MOSSY_ENDERMITE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_endermite_statue", WAXED_VERY_MOSSY_ENDERMITE_STATUE);
        registerBlock(false, ATBYW_MISC, "shulker_statue", SHULKER_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_shulker_statue", WAXED_CLEAN_SHULKER_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_shulker_statue", WAXED_EXPOSED_SHULKER_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_shulker_statue", WAXED_DIRTY_SHULKER_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_shulker_statue", WAXED_MOSSY_SHULKER_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_shulker_statue", WAXED_VERY_MOSSY_SHULKER_STATUE);
        registerBlock(false, ATBYW_MISC, "wolf_statue", WOLF_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_wolf_statue", WAXED_CLEAN_WOLF_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_wolf_statue", WAXED_EXPOSED_WOLF_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_wolf_statue", WAXED_DIRTY_WOLF_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_wolf_statue", WAXED_MOSSY_WOLF_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_wolf_statue", WAXED_VERY_MOSSY_WOLF_STATUE);
        registerBlock(false, ATBYW_MISC, "cat_statue", CAT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_cat_statue", WAXED_CLEAN_CAT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_cat_statue", WAXED_EXPOSED_CAT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_cat_statue", WAXED_DIRTY_CAT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_cat_statue", WAXED_MOSSY_CAT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_cat_statue", WAXED_VERY_MOSSY_CAT_STATUE);
        registerBlock(false, ATBYW_MISC, "chicken_statue", CHICKEN_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_chicken_statue", WAXED_CLEAN_CHICKEN_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_chicken_statue", WAXED_EXPOSED_CHICKEN_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_chicken_statue", WAXED_DIRTY_CHICKEN_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_chicken_statue", WAXED_MOSSY_CHICKEN_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_chicken_statue", WAXED_VERY_MOSSY_CHICKEN_STATUE);
        registerBlock(false, ATBYW_MISC, "rabbit_statue", RABBIT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_rabbit_statue", WAXED_CLEAN_RABBIT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_rabbit_statue", WAXED_EXPOSED_RABBIT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_rabbit_statue", WAXED_DIRTY_RABBIT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_rabbit_statue", WAXED_MOSSY_RABBIT_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_rabbit_statue", WAXED_VERY_MOSSY_RABBIT_STATUE);
        registerBlock(false, ATBYW_MISC, "fox_statue", FOX_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_fox_statue", WAXED_CLEAN_FOX_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_fox_statue", WAXED_EXPOSED_FOX_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_fox_statue", WAXED_DIRTY_FOX_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_fox_statue", WAXED_MOSSY_FOX_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_fox_statue", WAXED_VERY_MOSSY_FOX_STATUE);
        registerBlock(false, ATBYW_MISC, "cod_statue", COD_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_cod_statue", WAXED_CLEAN_COD_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_cod_statue", WAXED_EXPOSED_COD_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_cod_statue", WAXED_DIRTY_COD_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_cod_statue", WAXED_MOSSY_COD_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_cod_statue", WAXED_VERY_MOSSY_COD_STATUE);
        registerBlock(false, ATBYW_MISC, "salmon_statue", SALMON_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_salmon_statue", WAXED_CLEAN_SALMON_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_salmon_statue", WAXED_EXPOSED_SALMON_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_salmon_statue", WAXED_DIRTY_SALMON_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_salmon_statue", WAXED_MOSSY_SALMON_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_salmon_statue", WAXED_VERY_MOSSY_SALMON_STATUE);
        registerBlock(false, ATBYW_MISC, "puffer_fish_statue", PUFFER_FISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_puffer_fish_statue", WAXED_CLEAN_PUFFER_FISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_puffer_fish_statue", WAXED_EXPOSED_PUFFER_FISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_puffer_fish_statue", WAXED_DIRTY_PUFFER_FISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_puffer_fish_statue", WAXED_MOSSY_PUFFER_FISH_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_puffer_fish_statue", WAXED_VERY_MOSSY_PUFFER_FISH_STATUE);
        registerBlock(false, ATBYW_MISC, "slime_statue", SLIME_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_slime_statue", WAXED_CLEAN_SLIME_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_slime_statue", WAXED_EXPOSED_SLIME_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_slime_statue", WAXED_DIRTY_SLIME_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_slime_statue", WAXED_MOSSY_SLIME_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_slime_statue", WAXED_VERY_MOSSY_SLIME_STATUE);
        registerBlock(false, ATBYW_MISC, "magma_cube_statue", MAGMA_CUBE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_clean_magma_cube_statue", WAXED_CLEAN_MAGMA_CUBE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_exposed_magma_cube_statue", WAXED_EXPOSED_MAGMA_CUBE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_dirty_magma_cube_statue", WAXED_DIRTY_MAGMA_CUBE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_mossy_magma_cube_statue", WAXED_MOSSY_MAGMA_CUBE_STATUE);
        registerBlock(false, ATBYW_MISC, "waxed_very_mossy_magma_cube_statue", WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE);
    }
}
