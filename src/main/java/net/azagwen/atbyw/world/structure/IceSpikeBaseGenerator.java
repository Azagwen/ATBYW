package net.azagwen.atbyw.world.structure;

import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public class IceSpikeBaseGenerator {
    private static final Identifier BASE_TOP = AtbywMain.id("ice_spike_base/v1");
    private static final Identifier BASE_MIDDLE = AtbywMain.id("ice_spike_base/middle");
    private static final Identifier BASE_BOTTOM = AtbywMain.id("ice_spike_base/v2");

    public static void addPieces(StructureManager manager, BlockPos pos, List<StructurePiece> pieces, Random random, BlockRotation rotation) {
        if (random.nextDouble() < 0.5D) {
            pieces.add(new IceSpikeBasePiece(manager, BASE_MIDDLE, new BlockPos(pos.getX(), (pos.getY() - 18), pos.getZ()), rotation));
            pieces.add(new IceSpikeBasePiece(manager, BASE_BOTTOM, new BlockPos(pos.getX(), (pos.getY() - (18 + 8)), pos.getZ()), rotation));
        }

        pieces.add(new IceSpikeBasePiece(manager, BASE_TOP, pos, rotation));
    }
}