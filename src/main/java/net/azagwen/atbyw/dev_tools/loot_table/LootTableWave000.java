package net.azagwen.atbyw.dev_tools.loot_table;

import net.azagwen.atbyw.dev_tools.AutoJsonWriter;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.util.Identifier;

import static net.azagwen.atbyw.dev_tools.loot_table.LootTableMethods.*;

public class LootTableWave000 {

    public static void write() {
        var writer = new AutoJsonWriter();

        AtbywUtils.dyeColorNames().forEach((color) -> {
            var shardEntry = Entries.itemEntry(AtbywMain.id(color + "_stained_glass_shard"), null, LootTableMethods.Entries.functions(LootTableMethods.Functions.setCount(1, 4)));
            var blockEntry = Entries.itemEntry(AtbywMain.id(color + "_stained_shattered_glass"));
            var shardTable = blockTable(pool(1.0F, 0.0F, shardEntry, blockEntry));
            writer.write("loot_tables/blocks/" + color + "_stained_glass_shards.json", shardTable);
        });

        AtbywUtils.dyeColorNames().forEach((color) -> {
            var silkTouchEntry = Entries.itemEntry(AtbywMain.id(color + "_stained_glass_shard"), Entries.conditions(Conditions.silkTouchEnchantment()), null);
            var shardEntry = Entries.lootTableEntry(new Identifier("blocks/" + color + "_stained_glass_shards"));
            var table = blockTable(pool(1.0F, 0.0F, silkTouchEntry, shardEntry));
            writer.write("loot_tables/blocks/" + color + "_stained_glass.json", table);
        });

        var silkTouchEntry = Entries.itemEntry(AtbywMain.id("glass_shard"), Entries.conditions(Conditions.silkTouchEnchantment()), null);
        var shardEntry = Entries.lootTableEntry(new Identifier("blocks/glass_shards"));
        var table = blockTable(pool(1.0F, 0.0F, silkTouchEntry, shardEntry));
        writer.write("loot_tables/blocks/glass.json", table);

        shardEntry = Entries.itemEntry(AtbywMain.id("glass_shard"), null, LootTableMethods.Entries.functions(LootTableMethods.Functions.setCount(1, 4)));
        var blockEntry = Entries.itemEntry(AtbywMain.id("shattered_glass"));
        var shardTable = blockTable(pool(1.0F, 0.0F, shardEntry, blockEntry));
        writer.write("loot_tables/blocks/glass_shards.json", shardTable);
    }
}
