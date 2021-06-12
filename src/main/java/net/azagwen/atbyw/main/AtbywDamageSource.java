package net.azagwen.atbyw.main;

import net.minecraft.entity.damage.DamageSource;

public class AtbywDamageSource extends DamageSource {
    public static final DamageSource SPIKE_TRAP = new AtbywDamageSource("spike_trap");

    protected AtbywDamageSource(String name) {
        super(name);
    }
}
