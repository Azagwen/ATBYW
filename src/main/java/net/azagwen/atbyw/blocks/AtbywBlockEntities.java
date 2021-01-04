package net.azagwen.atbyw.blocks;

import net.azagwen.atbyw.blocks.statues.StatueBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static net.azagwen.atbyw.main.AtbywMain.newID;

public class AtbywBlockEntities {

    public static BlockEntityType<StatueBlockEntity> CHICKEN_STATUE;
    public static BlockEntityType<StatueBlockEntity> WOLF_STATUE;
    public static BlockEntityType<StatueBlockEntity> SLIME_STATUE;
    public static BlockEntityType<StatueBlockEntity> MAGMA_CUBE_STATUE;
    public static BlockEntityType<StatueBlockEntity> SILVERFISH_STATUE;
    public static BlockEntityType<StatueBlockEntity> CAT_STATUE;
    public static BlockEntityType<StatueBlockEntity> RABBIT_STATUE;
    public static BlockEntityType<StatueBlockEntity> SHULKER_STATUE;
    public static BlockEntityType<StatueBlockEntity> ENDERMITE_STATUE;
    public static BlockEntityType<StatueBlockEntity> COD_STATUE;
    public static BlockEntityType<StatueBlockEntity> SALMON_STATUE;
    public static BlockEntityType<StatueBlockEntity> PUFFER_FISH_STATUE;
    public static BlockEntityType<StatueBlockEntity> FOX_STATUE;
    public static BlockEntityType<StatueBlockEntity> BEE_STATUE;

    private static BlockEntityType<StatueBlockEntity> registerStatue(String name, BlockEntityType<StatueBlockEntity> self, Block ownerBlock) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, newID(name), BlockEntityType.Builder.create(() -> new StatueBlockEntity(self), ownerBlock).build(null));
    }

    public static void init() {
        CHICKEN_STATUE = registerStatue("chicken_statue", CHICKEN_STATUE, AtbywBlocks.CHICKEN_STATUE);
        WOLF_STATUE = registerStatue("wolf_statue", WOLF_STATUE, AtbywBlocks.WOLF_STATUE);
        SLIME_STATUE = registerStatue("slime_statue", SLIME_STATUE, AtbywBlocks.SLIME_STATUE);
        MAGMA_CUBE_STATUE = registerStatue("magma_cube_statue", MAGMA_CUBE_STATUE, AtbywBlocks.MAGMA_CUBE_STATUE);
        SILVERFISH_STATUE = registerStatue("silverfish_statue", SILVERFISH_STATUE, AtbywBlocks.SILVERFISH_STATUE);
        CAT_STATUE = registerStatue("cat_statue", CAT_STATUE, AtbywBlocks.CAT_STATUE);
        RABBIT_STATUE = registerStatue("rabbit_statue", RABBIT_STATUE, AtbywBlocks.RABBIT_STATUE);
        SHULKER_STATUE = registerStatue("shulker_statue", SHULKER_STATUE, AtbywBlocks.SHULKER_STATUE);
        ENDERMITE_STATUE = registerStatue("endermite_statue", ENDERMITE_STATUE, AtbywBlocks.ENDERMITE_STATUE);
        COD_STATUE = registerStatue("cod_statue", COD_STATUE, AtbywBlocks.COD_STATUE);
        SALMON_STATUE = registerStatue("salmon_statue", SALMON_STATUE, AtbywBlocks.SALMON_STATUE);
        PUFFER_FISH_STATUE = registerStatue("puffer_fish_statue", PUFFER_FISH_STATUE, AtbywBlocks.PUFFER_FISH_STATUE);
        FOX_STATUE = registerStatue("fox_statue", FOX_STATUE, AtbywBlocks.FOX_STATUE);
        BEE_STATUE = registerStatue("bee_statue", BEE_STATUE, AtbywBlocks.BEE_STATUE);
    }
}
