package net.azagwen.atbyw.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.JigsawFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class MesaTombFeature extends JigsawFeature {

    public MesaTombFeature(Codec<StructurePoolFeatureConfig> codec) {
        super(codec, 0, true, true);
    }
}
