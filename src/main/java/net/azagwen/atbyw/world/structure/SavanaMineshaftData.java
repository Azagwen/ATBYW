package net.azagwen.atbyw.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.azagwen.atbyw.world.StructurePoolHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

public class SavanaMineshaftData {
    public static final StructurePool BASE_POOL;
    private static final String rootFile = "savana_mineshaft";

    //TODO: fix this shit :3
    //TODO: update : fuck you Mojang, I go back to monkey

    public static void init() {
    }

    static {
        BASE_POOL = StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/shaft_entrance"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/top")), 100)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/entrance/sections"),
                AtbywID(rootFile + "/entrance/sections"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/sections/section_1")), 100),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/sections/section_2")), 100)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/entrance/junctions"),
                AtbywID(rootFile + "/entrance/junctions"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_1")), 20),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_2")), 30),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_3")), 30),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/entrance/junctions/junction_4")), 30)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/tunnels"),
                AtbywID(rootFile + "/tunnels"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_1")), 75),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_l")), 50),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_t")), 25),
                        Pair.of(StructurePoolHelper.addSinglePoolElement(AtbywID(rootFile + "/tunnels/tunnel_x")), 25)
                        ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/lighting"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a6")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a5")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a4")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a3")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b6")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b5")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b4")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b3")), 100)
                ),
                StructurePool.Projection.RIGID
        ));

        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/lighting_short"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_a3")), 30),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lighting/lamp_b3")), 30),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_1")), 20),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_2")), 20),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_3")), 20)
                ),
                StructurePool.Projection.RIGID
        ));
        StructurePools.register(new StructurePool(
                AtbywID(rootFile + "/lanterns/ceiling"),
                new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_1")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_2")), 100),
                        Pair.of(StructurePoolHelper.addLegacySinglePoolElement(AtbywID(rootFile + "/lanterns/ceiling/lantern_3")), 100)
                ),
                StructurePool.Projection.RIGID
        ));
    }
}
