package net.azagwen.atbyw.block;

import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class AtbywMaterials {

    public static final Material SHROOMSTICK;

    static {
        SHROOMSTICK = (new Material.Builder(MapColor.GOLD)).allowsMovement().build();
    }
}
