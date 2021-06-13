package net.azagwen.atbyw.world.structure;

import net.minecraft.structure.*;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;

import static net.azagwen.atbyw.main.AtbywMain.NewAtbywID;

public class BigIglooGenerator {
    private static final Identifier IGLOO_TOP = NewAtbywID("big_igloo/top");

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieces) {
        pieces.add(new BigIglooPiece(manager, IGLOO_TOP, pos, rotation));
    }
}