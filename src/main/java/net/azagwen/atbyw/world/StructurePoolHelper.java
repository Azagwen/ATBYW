package net.azagwen.atbyw.world;

import com.mojang.datafixers.util.Either;
import net.minecraft.structure.Structure;
import net.minecraft.structure.pool.LegacySinglePoolElement;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.Supplier;

public class StructurePoolHelper {

    public static class SinglePoolElementSubClass extends SinglePoolElement {
        public SinglePoolElementSubClass(Either<Identifier, Structure> either, Supplier<StructureProcessorList> supplier, StructurePool.Projection projection) {
            super(either, supplier, projection);
        }
    }

    public static class LegacySinglePoolElementSubClass extends LegacySinglePoolElement {
        public LegacySinglePoolElementSubClass(Either<Identifier, Structure> either, Supplier<StructureProcessorList> supplier, StructurePool.Projection projection) {
            super(either, supplier, projection);
        }
    }

    public static Function<StructurePool.Projection, LegacySinglePoolElement> addLegacySinglePoolElement(Identifier identifier, StructureProcessorList structureProcessorList) {
        return (projection) -> {
            return new LegacySinglePoolElementSubClass(Either.left(identifier), () -> structureProcessorList, projection);
        };
    }

    public static Function<StructurePool.Projection, LegacySinglePoolElement> addLegacySinglePoolElement(Identifier identifier) {
        return (projection) -> {
            return new LegacySinglePoolElementSubClass(Either.left(identifier), () -> StructureProcessorLists.EMPTY, projection);
        };
    }

    public static Function<StructurePool.Projection, SinglePoolElement> addSinglePoolElement(Identifier identifier, StructureProcessorList structureProcessorList) {
        return (projection) -> {
            return new SinglePoolElementSubClass(Either.left(identifier), () -> structureProcessorList, projection);
        };
    }

    public static Function<StructurePool.Projection, SinglePoolElement> addSinglePoolElement(Identifier identifier) {
        return (projection) -> {
            return new SinglePoolElementSubClass(Either.left(identifier), () -> StructureProcessorLists.EMPTY, projection);
        };
    }
}
