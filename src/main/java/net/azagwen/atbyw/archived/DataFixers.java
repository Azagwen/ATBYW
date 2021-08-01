package net.azagwen.atbyw.archived;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.datafixer.Schemas;
import net.minecraft.datafixer.fix.BlockNameFix;
import net.minecraft.datafixer.fix.ItemNameFix;
import net.minecraft.datafixer.schema.IdentifierNormalizingSchema;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

@Mixin(Schemas.class)
public class DataFixers {
    private static final BiFunction<Integer, Schema, Schema> EMPTY_IDENTIFIER_NORMALIZE = IdentifierNormalizingSchema::new;


    private static DataFixer create() {
        DataFixerBuilder builder = new DataFixerBuilder(1);

        Schema dirtPathSchema = builder.addSchema(0, EMPTY_IDENTIFIER_NORMALIZE);
        builder.addFixer(ItemNameFix.create(dirtPathSchema, "Renamed grass path slab item to dirt path slab", replacing(ImmutableMap.of("atbyw:grass_path_slab", "atbyw:dirt_path_slab"))));
        builder.addFixer(BlockNameFix.create(dirtPathSchema, "Renamed grass path slab block to dirt path slab", replacing(ImmutableMap.of("atbyw:grass_path_slab", "atbyw:dirt_path_slab"))));

        return builder.build(Util.getBootstrapExecutor());
    }

    private static UnaryOperator<String> replacing(Map<String, String> replacements) {
        return (string) -> {
            return (String)replacements.getOrDefault(string, string);
        };
    }

    private static UnaryOperator<String> replacing(String old, String current) {
        return (string3) -> {
            return Objects.equals(string3, old) ? current : string3;
        };
    }
}
