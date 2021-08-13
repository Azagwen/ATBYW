package net.azagwen.atbyw.world;

import com.google.common.collect.ImmutableList;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.world.feature.*;
import net.azagwen.atbyw.world.structure.*;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.function.Predicate;

@SuppressWarnings("deprecation")
public class AtbywWorldGen {
    public static final StructurePieceType BIG_IGLOO_PIECE = BigIglooPiece::new;
    public static final StructurePieceType DESERT_CRYPT_PIECE = DesertCryptPiece::new;
    public static final StructurePieceType ICE_SPIKE_BASE_PIECE = IceSpikeBasePiece::new;

    private static final StructureFeature<DefaultFeatureConfig> BIG_IGLOO_FEATURE = new BigIglooFeature(DefaultFeatureConfig.CODEC);
    private static final StructureFeature<DefaultFeatureConfig> DESERT_CRYPT_FEATURE = new DesertCryptFeature(DefaultFeatureConfig.CODEC);
    private static final StructureFeature<DefaultFeatureConfig> ICE_SPIKE_BASE_FEATURE = new IceSpikeBaseFeature(DefaultFeatureConfig.CODEC);
    private static final StructureFeature<StructurePoolFeatureConfig> SAVANA_MINESHAFT_FEATURE = new SavanaMineshaftFeature(StructurePoolFeatureConfig.CODEC);
    private static final StructureFeature<StructurePoolFeatureConfig> MESA_TOMB_FEATURE = new MesaTombFeature(StructurePoolFeatureConfig.CODEC);

    private static final ConfiguredStructureFeature<?, ?> BIG_IGLOO_CONFIG = BIG_IGLOO_FEATURE.configure(DefaultFeatureConfig.DEFAULT);
    private static final ConfiguredStructureFeature<?, ?> DESERT_CRYPT_CONFIG = DESERT_CRYPT_FEATURE.configure(DefaultFeatureConfig.DEFAULT);
    private static final ConfiguredStructureFeature<?, ?> ICE_SPIKE_BASE_CONFIG = ICE_SPIKE_BASE_FEATURE.configure(DefaultFeatureConfig.DEFAULT);
    private static final ConfiguredStructureFeature<?, ?> SAVANA_MINESHAFT_CONFIG = SAVANA_MINESHAFT_FEATURE.configure(new StructurePoolFeatureConfig(() -> {
        return SavanaMineshaftData.BASE_POOL;
    }, 7));
    private static final ConfiguredStructureFeature<?, ?> MESA_TOMB_CONFIG = MESA_TOMB_FEATURE.configure(new StructurePoolFeatureConfig(() -> {
        return MesaTombData.BASE_POOL;
    }, 7));

    //TODO: add more structures.

    public static StructureProcessorList registerStructProcessor(Identifier identifier, ImmutableList<StructureProcessor> processorList) {
        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);
        return BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_PROCESSOR_LIST, identifier, structureProcessorList);
    }

    private static FabricStructureBuilder<?, ?> registerSurfaceStructure(Identifier identifier, int spacing, int separation, int salt, StructurePieceType pieceType, StructureFeature<?> feature) {
        Registry.register(Registry.STRUCTURE_PIECE, identifier, pieceType);

        return FabricStructureBuilder.create(identifier, feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(spacing, separation, salt);
    }

    private static FabricStructureBuilder<?, ?> registerJigsawStructure(Identifier identifier, int spacing, int separation, int salt, StructureFeature<?> feature) {
        return FabricStructureBuilder.create(identifier, feature)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(spacing, separation, salt);
    }

    private static void addStructureToBiome(Identifier identifier, ConfiguredStructureFeature<?, ?> config, Predicate<BiomeSelectionContext> biomeSelector) {
        var STRUCT = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, identifier);

        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, STRUCT.getValue(), config);
        BiomeModifications.addStructure(biomeSelector, STRUCT);
    }

    public static void init() {
        registerSurfaceStructure(AtbywMain.id("big_igloo"), 32, 8, 56987, BIG_IGLOO_PIECE, BIG_IGLOO_FEATURE).register();
        registerSurfaceStructure(AtbywMain.id("desert_crypt"), 32, 8, 12345, DESERT_CRYPT_PIECE, DESERT_CRYPT_FEATURE).register();
        registerSurfaceStructure(AtbywMain.id("ice_spike_base"), 24, 8, 696969, ICE_SPIKE_BASE_PIECE, ICE_SPIKE_BASE_FEATURE).register();
        registerJigsawStructure(AtbywMain.id("savana_mineshaft"), 16, 8, 464643, SAVANA_MINESHAFT_FEATURE).register();
        registerJigsawStructure(AtbywMain.id("mesa_tomb"), 16, 8, 727272, MESA_TOMB_FEATURE).register();

        addStructureToBiome(AtbywMain.id("big_igloo"), BIG_IGLOO_CONFIG, BiomeSelectors.categories(Biome.Category.ICY).and(BiomeSelectors.excludeByKey(BiomeKeys.ICE_SPIKES)));
        addStructureToBiome(AtbywMain.id("desert_crypt"), DESERT_CRYPT_CONFIG, BiomeSelectors.categories(Biome.Category.DESERT));
        addStructureToBiome(AtbywMain.id("ice_spike_base"), ICE_SPIKE_BASE_CONFIG, BiomeSelectors.includeByKey(BiomeKeys.ICE_SPIKES));
        addStructureToBiome(AtbywMain.id("savana_mineshaft"), SAVANA_MINESHAFT_CONFIG, BiomeSelectors.categories(Biome.Category.SAVANNA).and(BiomeSelectors.excludeByKey(BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU)));
        addStructureToBiome(AtbywMain.id("mesa_tomb"), MESA_TOMB_CONFIG, BiomeSelectors.includeByKey(BiomeKeys.BADLANDS));
    }
}
