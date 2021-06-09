package net.azagwen.atbyw.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.azagwen.atbyw.world.StructurePoolHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;
import static net.azagwen.atbyw.world.structure.StructureProcessors.*;

public class SavanaMineshaftData {
    public static final StructurePool BASE_POOL;
    private static final String rootFile = "savana_mineshaft";

    public static void init() {
    }

    static {
        BASE_POOL = StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/shaft_entrance"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/top")), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/top_alt")), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/entrance/sections"),
                AtbywID(rootFile + "/entrance/sections"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/sections/section_1")), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/sections/section_2"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/sections/section_3")), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/sections/section_4"), SHUFFLE_CEILING_LANTERNS), 3)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/entrance/junctions"),
                AtbywID(rootFile + "/entrance/junctions"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_1"), DEGRADE_DIRT), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_2"), DEGRADE_DIRT), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_3"), DEGRADE_DIRT), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_4"), DEGRADE_DIRT), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_1_alt"), DEGRADE_DIRT_AND_STONE), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_2_alt"), DEGRADE_DIRT_AND_STONE), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_3_alt"), DEGRADE_DIRT_AND_STONE), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_4_alt"), DEGRADE_DIRT_AND_STONE), 2)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/tunnels"),
                AtbywID(rootFile + "/terminators"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_1"), DEGRADE_DIRT), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_2"), DEGRADE_DIRT), 7),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_3"), DEGRADE_DIRT), 5),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_l"), DEGRADE_DIRT), 8),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_t"), DEGRADE_DIRT), 5),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_x"), DEGRADE_DIRT), 4)
                        ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/terminators"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/terminators/terminator_1"), DEGRADE_DIRT_NO_SLABS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/terminators/terminator_2"), DEGRADE_DIRT_NO_SLABS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/terminators/terminator_3"), DEGRADE_DIRT_NO_SLABS), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/tunnels/rooms"),
                AtbywID(rootFile + "/tunnels/rooms"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/rooms/room_1"), DEGRADE_DIRT), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/rooms/room_2"), DEGRADE_DIRT), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/rooms/bedroom"), DEGRADE_DIRT), 2)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/lighting"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a5"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a4"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a3"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a2"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b5"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b4"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b3"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b2"), SHUFFLE_CEILING_LANTERNS), 1)
                ),
                StructurePool.Projection.RIGID
        ));

        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/lighting_short"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a2"), SHUFFLE_CEILING_LANTERNS), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b2"), SHUFFLE_CEILING_LANTERNS), 3),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_1"), SHUFFLE_CEILING_LANTERNS), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_2"), SHUFFLE_CEILING_LANTERNS), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_3"), SHUFFLE_CEILING_LANTERNS), 2)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/lanterns/ceiling"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_1"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_2"), SHUFFLE_CEILING_LANTERNS), 1),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_3"), SHUFFLE_CEILING_LANTERNS), 1)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/floor_decorations"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/floor_decorations/lantern"), SHUFFLE_FLOOR_LANTERNS), 2),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/floor_decorations/empty")), 1)
                ),
                StructurePool.Projection.RIGID
        ));
    }
}
