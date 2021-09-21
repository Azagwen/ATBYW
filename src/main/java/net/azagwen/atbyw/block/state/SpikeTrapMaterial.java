package net.azagwen.atbyw.block.state;


import net.minecraft.block.Block;

public interface SpikeTrapMaterial {
    String getName();

    float getDamageValue();

    float getPushingStrength();

    int getEffectAmplifier();

    Block getSpikeBlock();

    Block getTrapBlock();
}
