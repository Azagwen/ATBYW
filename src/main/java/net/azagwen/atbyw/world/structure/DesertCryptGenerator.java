package net.azagwen.atbyw.world.structure;

import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public class DesertCryptGenerator {
    private static final Identifier CRYPT_BUILDING = AtbywMain.id("desert_crypt/building");
    private static final Identifier CRYPT_TRAP_SPIKES = AtbywMain.id("desert_crypt/trap_spikes");
    private static final Identifier CRYPT_TRAP_BOMB = AtbywMain.id("desert_crypt/trap_bomb");
    private static final Identifier CRYPT_TRAP_LAVA = AtbywMain.id("desert_crypt/trap_lava");

    public static void addPieces(StructureManager manager, BlockPos pos, List<StructurePiece> pieces, Random random) {
        pieces.add(new DesertCryptPiece(manager, CRYPT_BUILDING, pos, BlockRotation.NONE));

        BlockPos trapPos = new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 3);

        switch (random.nextInt(3)) {
            case 0:
                pieces.add(new DesertCryptPiece(manager, CRYPT_TRAP_SPIKES, trapPos, BlockRotation.NONE));
                break;
            case 1:
                pieces.add(new DesertCryptPiece(manager, CRYPT_TRAP_BOMB, trapPos, BlockRotation.NONE));
                break;
            case 2:
                pieces.add(new DesertCryptPiece(manager, CRYPT_TRAP_LAVA, trapPos, BlockRotation.NONE));
                break;
        }
    }
}