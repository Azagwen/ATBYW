package net.azagwen.atbyw.world.structure;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

public class IceSpikeBaseGenerator {
    private static final Identifier BASE_TOP = AtbywID("ice_spike_base/top");
    private static final Identifier BASE_MIDDLE = AtbywID("ice_spike_base/middle");
    private static final Identifier BASE_BOTTOM = AtbywID("ice_spike_base/bottom");

    public static void addPieces(StructureManager manager, BlockPos pos, List<StructurePiece> pieces, Random random, BlockRotation rotation) {
        if (random.nextDouble() < 0.5D) {
            pieces.add(new IceSpikeBasePiece(manager, new BlockPos(pos.getX(), (pos.getY() - 18), pos.getZ()), BASE_MIDDLE, rotation));
            pieces.add(new IceSpikeBasePiece(manager, new BlockPos(pos.getX(), (pos.getY() - (18 + 8)), pos.getZ()), BASE_BOTTOM, rotation));
        }

        pieces.add(new IceSpikeBasePiece(manager, pos, BASE_TOP, rotation));
    }
}