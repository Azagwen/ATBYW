package net.azagwen.atbyw.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

public class AtbywWorldGen {
    public static final StructurePieceType BIG_IGLOO_PIECE = BigIglooGenerator.Piece::new;
    private static final StructureFeature<DefaultFeatureConfig> BIG_IGLOO_FEATURE = new BigIgloodFeature(DefaultFeatureConfig.CODEC);
    private static final ConfiguredStructureFeature<?, ?> BIG_IGLOO_CONFIG = BIG_IGLOO_FEATURE.configure(DefaultFeatureConfig.DEFAULT);

    //TODO: add trapped big igloos and other variants.
    //TODO: add more structures.

    @SuppressWarnings("deprecation")
    public static void addToBiome(String modificationName, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer) {
        BiomeModifications.create(AtbywID(modificationName)).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }
    public static void init() {
        Registry.register(Registry.STRUCTURE_PIECE, AtbywID("big_igloo"), BIG_IGLOO_PIECE);
        FabricStructureBuilder.create(AtbywID("big_igloo"), BIG_IGLOO_FEATURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> bigIgloo = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, AtbywID("big_igloo"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, bigIgloo.getValue(), BIG_IGLOO_CONFIG);

        addToBiome("big_igloo",
                context -> BiomeSelector.haveCategories(context, Biome.Category.ICY) && (BiomeSelector.hasNamespace(context, "minecraft")),
                modificationContext -> modificationContext.getGenerationSettings().addBuiltInStructure(BIG_IGLOO_CONFIG));

//        BiomeModifications.addStructure(BiomeSelectors.all(), bigIgloo);
    }
}
