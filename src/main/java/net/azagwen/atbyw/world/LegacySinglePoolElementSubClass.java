package net.azagwen.atbyw.world;

import com.mojang.datafixers.util.Either;
import net.minecraft.structure.Structure;
import net.minecraft.structure.pool.LegacySinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class LegacySinglePoolElementSubClass extends LegacySinglePoolElement {

    public LegacySinglePoolElementSubClass(Either<Identifier, Structure> either, Supplier<StructureProcessorList> supplier, StructurePool.Projection projection) {
        super(either, supplier, projection);
    }
}
