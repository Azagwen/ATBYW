package net.azagwen.atbyw.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.world.StructurePoolHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

import static net.azagwen.atbyw.world.structure.StructureProcessors.*;

public class MesaTombData {
    public static final StructurePool BASE_POOL;
    private static final String rootFile = "mesa_tomb";

    public static void init() {
    }

    static {
        BASE_POOL = StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/entrance/v1"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/entrance/v1")), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/entrance/middle"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/entrance/middle")), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/entrance/v2"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/entrance/v2"), SHUFFLE_FLOOR_LANTERNS), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/hallways"),
                AtbywMain.id(rootFile + "/hallways_terminators"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/hallway"), SHUFFLE_CEILING_LANTERNS), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/tomb_room"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/fire_room"), SHUFFLE_CEILING_LANTERNS), 1)
                        ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/hallways_terminators"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/terminator")), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/hallways/rooms"),
                AtbywMain.id(rootFile + "/hallways/rooms_terminators"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/rooms/room_bedroom"), SHUFFLE_CEILING_LANTERNS), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/rooms/room_treasure_1"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/rooms/room_small_tomb"), SHUFFLE_CEILING_LANTERNS), 2)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/hallways/rooms_terminators"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/rooms/room_terminator"), SHUFFLE_FLOOR_LANTERNS), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywMain.id(rootFile + "/ceiling_chains"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_9a"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_8a"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_7a"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_6a"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_5a"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_4a"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_9b")), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_8b")), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_7b")), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_6b")), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_5b")), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywMain.id(rootFile + "/hallways/ends/chains/chain_4b")), 3)
                ),
                StructurePool.Projection.RIGID
        ));
    }
}
