package net.azagwen.atbyw.block.registry;

import net.azagwen.atbyw.block.*;
import net.azagwen.atbyw.block.state.AtbywProperties;
import net.azagwen.atbyw.block.state.SpikeTrapMaterials;
import net.azagwen.atbyw.mod_interaction.block.AtbywModInteractionBlocks;
import net.azagwen.atbyw.util.naming.FlowerNames;
import net.azagwen.atbyw.util.naming.WoodNames;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;

import static net.azagwen.atbyw.main.AtbywMain.REDSTONE_TAB;
import static net.azagwen.atbyw.util.BlockUtils.*;
import static net.azagwen.atbyw.util.BlockUtils.registerBlockOnly;

public class RedstoneBlockRegistry extends AtbywBlocks {
    public static final Block REDSTONE_PIPE = new RedstonePipeBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.COPPER));
    public static final Block REDSTONE_PIPE_INVERTER = new RedstonePipeInverterBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.COPPER));
    public static final Block REDSTONE_PIPE_REPEATER = new RedstonePipeRepeaterBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.COPPER));
    public static final Block TIMER_REPEATER = new TimerRepeaterBlock(FabricBlockSettings.copyOf(Blocks.REPEATER));
    public static final Block REDSTONE_CROSS_PATH = new RedstoneCrossPathBlock(FabricBlockSettings.copyOf(Blocks.REPEATER));
    public static final Block REDSTONE_LANTERN = new RedstoneLanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN).breakByTool(FabricToolTags.PICKAXES).luminance(lightLevelFromState(2, AtbywProperties.POWER_INTENSITY, Properties.LIT)));
    public static final Block REDSTONE_JACK_O_LANTERN = new RedstoneJackOlantern(FabricBlockSettings.of(Material.GOURD, MapColor.ORANGE).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance(lightLevelFromState(7, RedstoneJackOlantern.LIT)).solidBlock(AtbywBlocks::never).allowsSpawning(AtbywBlocks::always));
    public static final Block OAK_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block SPRUCE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block BIRCH_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block JUNGLE_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block ACACIA_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block DARK_OAK_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block CRIMSON_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block WARPED_BOOKSHELF_TOGGLE = new BookshelfToggleBlock();
    public static final Block DANDELION_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.DANDELION).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block POPPY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.POPPY).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block BLUE_ORCHID_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.BLUE_ORCHID).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block ALLIUM_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.ALLIUM).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block AZURE_BLUET_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.AZURE_BLUET).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block RED_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.RED_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block ORANGE_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block WHITE_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.WHITE_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block PINK_TULIP_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.PINK_TULIP).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block OXEYE_DAISY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.OXEYE_DAISY).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block CORNFLOWER_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.CORNFLOWER).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block LILY_OF_THE_VALLEY_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.LILY_OF_THE_VALLEY).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block WITHER_ROSE_PULL_SWITCH = new FlowerButtonBlock(FabricBlockSettings.copyOf(Blocks.WITHER_ROSE).luminance(lightLevelFromState(8, Properties.LIT)).sounds(BlockSoundGroup.WOOD));
    public static final Block IRON_SPIKE_TRAP_SPIKES = new SpikeBlock(SpikeTrapMaterials.IRON, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block GOLD_SPIKE_TRAP_SPIKES = new SpikeBlock(SpikeTrapMaterials.GOLD, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block DIAMOND_SPIKE_TRAP_SPIKES = new SpikeBlock(SpikeTrapMaterials.DIAMOND, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block NETHERITE_SPIKE_TRAP_SPIKES = new SpikeBlock(SpikeTrapMaterials.NETHERITE, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never).suffocates(AtbywBlocks::never).blockVision(AtbywBlocks::never).dropsNothing().nonOpaque().noCollision());
    public static final Block IRON_SPIKE_TRAP = new SpikeTrapBlock(SpikeTrapMaterials.IRON, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block GOLD_SPIKE_TRAP = new SpikeTrapBlock(SpikeTrapMaterials.GOLD, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block DIAMOND_SPIKE_TRAP = new SpikeTrapBlock(SpikeTrapMaterials.DIAMOND, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block NETHERITE_SPIKE_TRAP = new SpikeTrapBlock(SpikeTrapMaterials.NETHERITE, FabricBlockSettings.of(Material.PISTON).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES).solidBlock(AtbywBlocks::never));
    public static final Block OAK_FENCE_DOOR = createFenceDoor(Blocks.OAK_PLANKS, true);
    public static final Block SPRUCE_FENCE_DOOR = createFenceDoor(Blocks.SPRUCE_DOOR, true);
    public static final Block BIRCH_FENCE_DOOR = createFenceDoor(Blocks.BIRCH_DOOR, true);
    public static final Block JUNGLE_FENCE_DOOR = createFenceDoor(Blocks.JUNGLE_DOOR, true);
    public static final Block ACACIA_FENCE_DOOR = createFenceDoor(Blocks.ACACIA_DOOR, true);
    public static final Block DARK_OAK_FENCE_DOOR = createFenceDoor(Blocks.DARK_OAK_DOOR, true);
    public static final Block CRIMSON_FENCE_DOOR = createFenceDoor(Blocks.CRIMSON_DOOR, true);
    public static final Block WARPED_FENCE_DOOR = createFenceDoor(Blocks.WARPED_DOOR, true);
    public static final Block IRON_FENCE_DOOR = createFenceDoor(Blocks.IRON_DOOR, false);

    private static Block createFenceDoor(Block materialBlock, boolean wooden) {
        var material = materialBlock.getDefaultState().getMaterial();
        var mapColor = materialBlock.getDefaultMapColor();
        var requiredTool = wooden ? FabricToolTags.AXES : FabricToolTags.PICKAXES;
        var hardness = materialBlock.getHardness();
        var resistance = materialBlock.getBlastResistance();
        var sound = materialBlock.getSoundGroup(materialBlock.getDefaultState());

        var settings = FabricBlockSettings.of(material, mapColor).breakByTool(requiredTool).strength(hardness, resistance).sounds(sound);
        var finalSettings = wooden ? settings : settings.requiresTool();
        return new FenceDoorBlock(finalSettings);
    }

    public static void init() {
        registerBlock(false, REDSTONE_TAB, "redstone_pipe", REDSTONE_PIPE);
        registerBlock(false, REDSTONE_TAB, "redstone_pipe_inverter", REDSTONE_PIPE_INVERTER);
        registerBlock(false, REDSTONE_TAB, "redstone_pipe_repeater", REDSTONE_PIPE_REPEATER);
        registerBlock(false, REDSTONE_TAB, "timer_repeater", TIMER_REPEATER);
        registerBlock(false, REDSTONE_TAB, "redstone_cross_path", REDSTONE_CROSS_PATH);
        registerBlock(false, REDSTONE_TAB, "redstone_lantern", REDSTONE_LANTERN);
        registerBlock(false, REDSTONE_TAB, "redstone_jack_o_lantern", REDSTONE_JACK_O_LANTERN);
        registerBlocks(false, REDSTONE_TAB, "bookshelf_toggle", WoodNames.getNames(), OAK_BOOKSHELF_TOGGLE, SPRUCE_BOOKSHELF_TOGGLE, BIRCH_BOOKSHELF_TOGGLE, JUNGLE_BOOKSHELF_TOGGLE, ACACIA_BOOKSHELF_TOGGLE, DARK_OAK_BOOKSHELF_TOGGLE, CRIMSON_BOOKSHELF_TOGGLE, WARPED_BOOKSHELF_TOGGLE);
        AtbywModInteractionBlocks.initBookshelfToggles();
        registerBlocks(false, REDSTONE_TAB, "pull_switch", FlowerNames.getNames(), DANDELION_PULL_SWITCH, POPPY_PULL_SWITCH, BLUE_ORCHID_PULL_SWITCH, ALLIUM_PULL_SWITCH, AZURE_BLUET_PULL_SWITCH, RED_TULIP_PULL_SWITCH, ORANGE_TULIP_PULL_SWITCH, WHITE_TULIP_PULL_SWITCH, PINK_TULIP_PULL_SWITCH, OXEYE_DAISY_PULL_SWITCH, CORNFLOWER_PULL_SWITCH, LILY_OF_THE_VALLEY_PULL_SWITCH, WITHER_ROSE_PULL_SWITCH);
        registerBlock(false, REDSTONE_TAB, "iron_spike_trap", IRON_SPIKE_TRAP);
        registerBlock(false, REDSTONE_TAB, "gold_spike_trap", GOLD_SPIKE_TRAP);
        registerBlock(false, REDSTONE_TAB, "diamond_spike_trap", DIAMOND_SPIKE_TRAP);
        registerBlock(false, REDSTONE_TAB, "netherite_spike_trap", NETHERITE_SPIKE_TRAP);
        registerBlocks(false, REDSTONE_TAB, "fence_door", WoodNames.getNames(), OAK_FENCE_DOOR, SPRUCE_FENCE_DOOR, BIRCH_FENCE_DOOR, JUNGLE_FENCE_DOOR, ACACIA_FENCE_DOOR, DARK_OAK_FENCE_DOOR, CRIMSON_FENCE_DOOR, WARPED_FENCE_DOOR);
        registerBlock(false, REDSTONE_TAB, "iron_fence_door", IRON_FENCE_DOOR);

        registerBlockOnly("iron_spike_trap_spikes", IRON_SPIKE_TRAP_SPIKES);
        registerBlockOnly("gold_spike_trap_spikes", GOLD_SPIKE_TRAP_SPIKES);
        registerBlockOnly("diamond_spike_trap_spikes", DIAMOND_SPIKE_TRAP_SPIKES);
        registerBlockOnly("netherite_spike_trap_spikes", NETHERITE_SPIKE_TRAP_SPIKES);
    }
}
