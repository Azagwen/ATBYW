package net.azagwen.atbyw.block.state;

import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.minecraft.block.Block;

public enum SpikeTrapMaterials implements SpikeTrapMaterial {
    IRON("iron", 2.0F, 1.0F, 1, RedstoneBlockRegistry.IRON_SPIKE_TRAP_SPIKES, RedstoneBlockRegistry.IRON_SPIKE_TRAP),
    GOLD("gold", 0.5F, 0.5F, 0, RedstoneBlockRegistry.GOLD_SPIKE_TRAP_SPIKES, RedstoneBlockRegistry.GOLD_SPIKE_TRAP),
    DIAMOND("diamond", 3.0F, 3.0F, 2, RedstoneBlockRegistry.DIAMOND_SPIKE_TRAP_SPIKES, RedstoneBlockRegistry.DIAMOND_SPIKE_TRAP),
    NETHERITE("netherite", 4.0F, 6.0F, 2, RedstoneBlockRegistry.NETHERITE_SPIKE_TRAP_SPIKES, RedstoneBlockRegistry.NETHERITE_SPIKE_TRAP);

    private final String name;
    private final float DamageValue;
    private final float PushingStrength;
    private final int EffectAmplifier;
    private final Block spikeBlock;
    private final Block trapBlock;

    SpikeTrapMaterials(String name, float DamageValue, float PushingStrength, int EffectAmplifier, Block spikeBlock, Block trapBlock) {
        this.name = name;
        this.DamageValue = DamageValue;
        this.PushingStrength = PushingStrength;
        this.EffectAmplifier = EffectAmplifier;
        this.spikeBlock = spikeBlock;
        this.trapBlock = trapBlock;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getDamageValue() {
        return this.DamageValue;
    }

    @Override
    public float getPushingStrength() {
        return this.PushingStrength;
    }

    @Override
    public int getEffectAmplifier() {
        return this.EffectAmplifier;
    }

    @Override
    public Block getSpikeBlock() {
        return this.spikeBlock;
    }

    @Override
    public Block getTrapBlock() {
        return this.trapBlock;
    }
}
