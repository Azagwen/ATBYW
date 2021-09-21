package net.azagwen.atbyw.world.structure;

import com.google.common.collect.ImmutableList;
import net.azagwen.atbyw.block.RedstoneLanternBlock;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.world.AtbywWorldGen;
import net.minecraft.block.Blocks;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;

public class StructureProcessors {

    public StructureProcessors() {
    }

    public static final StructureProcessorList DEGRADE_DIRT = AtbywWorldGen.registerStructProcessor(
            AtbywMain.id("degrade_dirt"),
            ImmutableList.of(
                    new RuleStructureProcessor(
                            ImmutableList.of(
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.25F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.GRAVEL.getDefaultState()
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.5F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.COARSE_DIRT.getDefaultState()
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.125F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            BuildingBlockRegistry.DIRT_SLAB.getDefaultState()
                                    )
                            )
                    )
            )
    );
    public static final StructureProcessorList DEGRADE_DIRT_NO_SLABS = AtbywWorldGen.registerStructProcessor(
            AtbywMain.id("degrade_dirt_no_slabs"),
            ImmutableList.of(
                    new RuleStructureProcessor(
                            ImmutableList.of(
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.25F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.GRAVEL.getDefaultState()
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.5F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.COARSE_DIRT.getDefaultState()
                                    )
                            )
                    )
            )
    );
    public static final StructureProcessorList DEGRADE_DIRT_AND_STONE = AtbywWorldGen.registerStructProcessor(
            AtbywMain.id("degrade_dirt_and_stone"),
            ImmutableList.of(
                    new RuleStructureProcessor(
                            ImmutableList.of(
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.25F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.GRAVEL.getDefaultState()
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.5F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.COARSE_DIRT.getDefaultState()
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.DIRT, 0.125F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            BuildingBlockRegistry.DIRT_SLAB.getDefaultState()
                                    )
                            )
                    ),
                    new RuleStructureProcessor(
                            ImmutableList.of(
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.STONE, 0.25F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.GRAVEL.getDefaultState()
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.STONE, 0.25F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.COAL_ORE.getDefaultState()
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(Blocks.STONE, 0.25F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.ANDESITE.getDefaultState()
                                    )
                            )
                    )
            )
    );
    public static final StructureProcessorList SHUFFLE_CEILING_LANTERNS = AtbywWorldGen.registerStructProcessor(
            AtbywMain.id("shuffle_ceiling_lanterns"),
            ImmutableList.of(
                    new RuleStructureProcessor(
                            ImmutableList.of(
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(RedstoneBlockRegistry.REDSTONE_LANTERN, 0.5F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            RedstoneBlockRegistry.REDSTONE_LANTERN.getDefaultState().with(RedstoneLanternBlock.POWER_INTENSITY, 10).with(RedstoneLanternBlock.HANGING, true)
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(RedstoneBlockRegistry.REDSTONE_LANTERN, 0.5F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            RedstoneBlockRegistry.REDSTONE_LANTERN.getDefaultState().with(RedstoneLanternBlock.POWER_INTENSITY, 5).with(RedstoneLanternBlock.HANGING, true)
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(RedstoneBlockRegistry.REDSTONE_LANTERN, 0.125F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.LANTERN.getDefaultState().with(RedstoneLanternBlock.HANGING, true)
                                    )
                            )
                    )
            )
    );
    public static final StructureProcessorList SHUFFLE_FLOOR_LANTERNS = AtbywWorldGen.registerStructProcessor(
            AtbywMain.id("shuffle_floor_lanterns"),
            ImmutableList.of(
                    new RuleStructureProcessor(
                            ImmutableList.of(
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(RedstoneBlockRegistry.REDSTONE_LANTERN, 0.5F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            RedstoneBlockRegistry.REDSTONE_LANTERN.getDefaultState().with(RedstoneLanternBlock.POWER_INTENSITY, 10)
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(RedstoneBlockRegistry.REDSTONE_LANTERN, 0.5F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            RedstoneBlockRegistry.REDSTONE_LANTERN.getDefaultState().with(RedstoneLanternBlock.POWER_INTENSITY, 5)
                                    ),
                                    new StructureProcessorRule(
                                            new RandomBlockMatchRuleTest(RedstoneBlockRegistry.REDSTONE_LANTERN, 0.125F),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            Blocks.LANTERN.getDefaultState()
                                    )
                            )
                    )
            )
    );
}
