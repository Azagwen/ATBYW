package net.azagwen.atbyw.blocks.statues;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

import static net.azagwen.atbyw.blocks.AtbywBlockUtils.*;
import static net.azagwen.atbyw.main.AtbywMain.*;

public class StatueRegistry {
    private static final FabricBlockSettings statueSettings = FabricBlockSettings.copyOf(Blocks.STONE).breakByTool(FabricToolTags.PICKAXES).ticksRandomly();

    public static final Block WAXED_CLEAN_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_EXPOSED_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_DIRTY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_MOSSY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block WAXED_VERY_MOSSY_BEE_STATUE = new WaxedStatueBlock(StatueBlockMobTypes.BEE, statueSettings);
    public static final Block BEE_STATUE = new StatueBlock(true, new Block[] {
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
    public static final Block SLIME_STATUE = new StatueBlock(true, new Block[] {
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
    public static final Block MAGMA_CUBE_STATUE = new StatueBlock(true, new Block[] {
            WAXED_CLEAN_MAGMA_CUBE_STATUE,
            WAXED_EXPOSED_MAGMA_CUBE_STATUE,
            WAXED_DIRTY_MAGMA_CUBE_STATUE,
            WAXED_MOSSY_MAGMA_CUBE_STATUE,
            WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE
    }, StatueBlockMobTypes.MAGMA_CUBE, statueSettings);

    //TODO: Add signing fish function.
    //TODO: Make slime statues combine-able.

    private static void registerStatues(String animal, Block[] statues) {
        HashMap<String, Block> pairs = new HashMap<>();
        pairs.put("", statues[0]);
        pairs.put("clean", statues[1]);
        pairs.put("exposed", statues[2]);
        pairs.put("dirty", statues[3]);
        pairs.put("mossy", statues[4]);
        pairs.put("very_mossy", statues[5]);

        for (Map.Entry<String, Block> pair : pairs.entrySet()) {
            registerBlock(false, ATBYW_DECO, String.join("_", "waxed", pair.getKey(), animal, "statue"), pair.getValue());
        }
    }

    public static void initStatues() {
        registerStatues("bee", new Block[] {
                BEE_STATUE,
                WAXED_CLEAN_BEE_STATUE,
                WAXED_EXPOSED_BEE_STATUE,
                WAXED_DIRTY_BEE_STATUE,
                WAXED_MOSSY_BEE_STATUE,
                WAXED_VERY_MOSSY_BEE_STATUE
        });
        registerStatues("silverfish", new Block[] {
                SILVERFISH_STATUE,
                WAXED_CLEAN_SILVERFISH_STATUE,
                WAXED_EXPOSED_SILVERFISH_STATUE,
                WAXED_DIRTY_SILVERFISH_STATUE,
                WAXED_MOSSY_SILVERFISH_STATUE,
                WAXED_VERY_MOSSY_SILVERFISH_STATUE
        });
        registerStatues("endermite", new Block[]{
                ENDERMITE_STATUE,
                WAXED_CLEAN_ENDERMITE_STATUE,
                WAXED_EXPOSED_ENDERMITE_STATUE,
                WAXED_DIRTY_ENDERMITE_STATUE,
                WAXED_MOSSY_ENDERMITE_STATUE,
                WAXED_VERY_MOSSY_ENDERMITE_STATUE
        });
        registerStatues("shulker", new Block[]{
                SHULKER_STATUE,
                WAXED_CLEAN_SHULKER_STATUE,
                WAXED_EXPOSED_SHULKER_STATUE,
                WAXED_DIRTY_SHULKER_STATUE,
                WAXED_MOSSY_SHULKER_STATUE,
                WAXED_VERY_MOSSY_SHULKER_STATUE
        });
        registerStatues("wolf", new Block[]{
                WOLF_STATUE,
                WAXED_CLEAN_WOLF_STATUE,
                WAXED_EXPOSED_WOLF_STATUE,
                WAXED_DIRTY_WOLF_STATUE,
                WAXED_MOSSY_WOLF_STATUE,
                WAXED_VERY_MOSSY_WOLF_STATUE
        });
        registerStatues("cat", new Block[]{
                CAT_STATUE,
                WAXED_CLEAN_CAT_STATUE,
                WAXED_EXPOSED_CAT_STATUE,
                WAXED_DIRTY_CAT_STATUE,
                WAXED_MOSSY_CAT_STATUE,
                WAXED_VERY_MOSSY_CAT_STATUE
        });
        registerStatues("chicken", new Block[]{
                CHICKEN_STATUE,
                WAXED_CLEAN_CHICKEN_STATUE,
                WAXED_EXPOSED_CHICKEN_STATUE,
                WAXED_DIRTY_CHICKEN_STATUE,
                WAXED_MOSSY_CHICKEN_STATUE,
                WAXED_VERY_MOSSY_CHICKEN_STATUE
        });
        registerStatues("rabbit", new Block[]{
                RABBIT_STATUE,
                WAXED_CLEAN_RABBIT_STATUE,
                WAXED_EXPOSED_RABBIT_STATUE,
                WAXED_DIRTY_RABBIT_STATUE,
                WAXED_MOSSY_RABBIT_STATUE,
                WAXED_VERY_MOSSY_RABBIT_STATUE
        });
        registerStatues("fox", new Block[]{
                FOX_STATUE,
                WAXED_CLEAN_FOX_STATUE,
                WAXED_EXPOSED_FOX_STATUE,
                WAXED_DIRTY_FOX_STATUE,
                WAXED_MOSSY_FOX_STATUE,
                WAXED_VERY_MOSSY_FOX_STATUE
        });
        registerStatues("cod", new Block[]{
                COD_STATUE,
                WAXED_CLEAN_COD_STATUE,
                WAXED_EXPOSED_COD_STATUE,
                WAXED_DIRTY_COD_STATUE,
                WAXED_MOSSY_COD_STATUE,
                WAXED_VERY_MOSSY_COD_STATUE
        });
        registerStatues("salmon", new Block[]{
                SALMON_STATUE,
                WAXED_CLEAN_SALMON_STATUE,
                WAXED_EXPOSED_SALMON_STATUE,
                WAXED_DIRTY_SALMON_STATUE,
                WAXED_MOSSY_SALMON_STATUE,
                WAXED_VERY_MOSSY_SALMON_STATUE
        });
        registerStatues("puffer_fish", new Block[]{
                PUFFER_FISH_STATUE,
                WAXED_CLEAN_PUFFER_FISH_STATUE,
                WAXED_EXPOSED_PUFFER_FISH_STATUE,
                WAXED_DIRTY_PUFFER_FISH_STATUE,
                WAXED_MOSSY_PUFFER_FISH_STATUE,
                WAXED_VERY_MOSSY_PUFFER_FISH_STATUE
        });
        registerStatues("slime", new Block[]{
                SLIME_STATUE,
                WAXED_CLEAN_SLIME_STATUE,
                WAXED_EXPOSED_SLIME_STATUE,
                WAXED_DIRTY_SLIME_STATUE,
                WAXED_MOSSY_SLIME_STATUE,
                WAXED_VERY_MOSSY_SLIME_STATUE
        });
        registerStatues("magma_cube", new Block[]{
                MAGMA_CUBE_STATUE,
                WAXED_CLEAN_MAGMA_CUBE_STATUE,
                WAXED_EXPOSED_MAGMA_CUBE_STATUE,
                WAXED_DIRTY_MAGMA_CUBE_STATUE,
                WAXED_MOSSY_MAGMA_CUBE_STATUE,
                WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE
        });
    }
}
