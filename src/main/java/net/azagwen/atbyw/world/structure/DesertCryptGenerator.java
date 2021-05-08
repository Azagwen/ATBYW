package net.azagwen.atbyw.world.structure;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

public class DesertCryptGenerator {
    private static final Identifier CRYPT_BUILDING = AtbywID("desert_crypt/building");
    private static final Identifier CRYPT_TRAP_SPIKES = AtbywID("desert_crypt/trap_spikes");
    private static final Identifier CRYPT_TRAP_BOMB = AtbywID("desert_crypt/trap_bomb");
    private static final Identifier CRYPT_TRAP_LAVA = AtbywID("desert_crypt/trap_lava");

    public static void addPieces(StructureManager manager, BlockPos pos, List<StructurePiece> pieces, Random random) {
        pieces.add(new DesertCryptPiece(manager, pos, CRYPT_BUILDING, BlockRotation.NONE));

        BlockPos trapPos = new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 3);

        switch (random.nextInt(3)) {
            case 0:
                pieces.add(new DesertCryptPiece(manager, trapPos, CRYPT_TRAP_SPIKES, BlockRotation.NONE));
                break;
            case 1:
                pieces.add(new DesertCryptPiece(manager, trapPos, CRYPT_TRAP_BOMB, BlockRotation.NONE));
                break;
            case 2:
                pieces.add(new DesertCryptPiece(manager, trapPos, CRYPT_TRAP_LAVA, BlockRotation.NONE));
                break;
        }
    }
}