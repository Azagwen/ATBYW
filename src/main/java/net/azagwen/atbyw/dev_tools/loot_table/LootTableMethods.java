package net.azagwen.atbyw.dev_tools.loot_table;

import com.google.gson.JsonObject;
import net.azagwen.atbyw.util.AtbywUtils;
import net.azagwen.atbyw.util.Pair;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootTableMethods {

    protected static class Conditions {
        protected static JsonObject silkTouchEnchantment() {
            var enchantment = new JsonObject();
            enchantment.addProperty("enchantment", "minecraft:silk_touch");
            enchantment.add("levels", AtbywUtils.jsonObject(new Pair<>("min", 1)));

            var predicate = new JsonObject();
            predicate.add("enchantments", AtbywUtils.jsonArray(enchantment));

            var condition = new JsonObject();
            condition.addProperty("condition", "minecraft:match_tool");
            condition.add("predicate", predicate);
            return condition;
        }
    }

    protected static class Functions {
        protected static JsonObject setCount(int min, int max) {
            var function = new JsonObject();
            function.addProperty("function", "minecraft:set_count");
            function.add("count", AtbywUtils.jsonObject(
                    new Pair<>("type", "minecraft:uniform"),
                    new Pair<>("min", 1.0F),
                    new Pair<>("max", 4.0F)
            ));
            return function;
        }
    }

    protected static class Entries{
        protected static JsonObject lootTableEntry(Identifier name) {
            var entry = new JsonObject();
            entry.addProperty("type", "minecraft:loot_table");
            entry.addProperty("name", name.toString());
            return entry;
        }

        protected static JsonObject itemEntry(Identifier item, @Nullable JsonObject[] conditions, @Nullable JsonObject[] functions) {
            var entry = new JsonObject();
            entry.addProperty("type", "minecraft:item");
            entry.addProperty("name", item.toString());

            if (conditions != null)
                entry.add("conditions", AtbywUtils.jsonArray(conditions));
            if (functions != null)
                entry.add("functions", AtbywUtils.jsonArray(functions));

            return entry;
        }

        protected static JsonObject itemEntry (Identifier item){
            return itemEntry(item, null, null);
        }

        protected static JsonObject[] functions(JsonObject... functions) {
            return functions;
        }

        protected static JsonObject[] conditions(JsonObject... conditions) {
            return conditions;
        }
    }

    protected static JsonObject pool(float rolls, float bonusRolls, JsonObject... entries) {
        var pool = new JsonObject();
        pool.addProperty("rolls", rolls);
        pool.addProperty("bonus_rolls", bonusRolls);
        pool.add("entries", AtbywUtils.jsonArray(entries));
        return pool;
    }

    protected static JsonObject blockTable(@NotNull JsonObject... pools) {
        var table = new JsonObject();
        table.addProperty("type", "minecraft:block");
        table.add("pools", AtbywUtils.jsonArray(pools));
        return table;
    }
}
