package net.azagwen.atbyw.block;

import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class AtbywMaterials {

    public static final Material SHROOMSTICK;

    static {
        SHROOMSTICK = (new Material.Builder(MaterialColor.GOLD)).allowsMovement().build();
    }
}
