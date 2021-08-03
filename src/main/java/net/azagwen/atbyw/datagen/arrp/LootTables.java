package net.azagwen.atbyw.datagen.arrp;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.statues.StatueRegistry;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.util.AtbywUtils;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.loot.JFunction;
import net.devtech.arrp.json.loot.JLootTable;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static net.azagwen.atbyw.datagen.arrp.AtbywRRP.*;
import static net.azagwen.atbyw.util.AtbywUtils.*;
import static net.devtech.arrp.json.loot.JLootTable.*;
import static net.devtech.arrp.json.loot.JLootTable.condition;

@SuppressWarnings("deprecation")
public class LootTables {

    private static Identifier blockTableID(Identifier blockID) {
        return new Identifier(blockID.getNamespace(), "blocks/" + blockID.getPath());
    }

    private static JsonObject op(String source, String target, String op) {
        var json = new JsonObject();
        json.addProperty("source", source);
        json.addProperty("target", target);
        json.addProperty("op", op);
        return json;
    }

    private static JFunction copyNbtFunction(JsonArray ops) {
        return new JFunction("minecraft:copy_nbt")
                .parameter("source", "block_entity")
                .parameter("ops", ops);
    }

    private static JsonObject silkTouchPredicate() {
        var level = new JsonObject();
        level.addProperty("min", 1);

        var silkTouch = new JsonObject();
        silkTouch.addProperty("enchantment", "minecraft:silk_touch");
        silkTouch.add("levels", level);

        JsonArray enchantments = new JsonArray();
        enchantments.add(silkTouch);

        var predicate = new JsonObject();
        predicate.add("enchantments", enchantments);

        return predicate;
    }

    private static JsonObject blockStringProperty(String name, String value) {
        var property = new JsonObject();
        property.addProperty(name, value);

        return property;
    }

    private static JsonObject blockIntProperty(String name, int value) {
        var property = new JsonObject();
        property.addProperty(name, value);

        return property;
    }

    private static void blockColorNbt(RuntimeResourcePack pack, Identifier blockID) {
        pack.addLootTable(blockTableID(blockID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:item")
                                .name(blockID.toString())
                                .function(copyNbtFunction(AtbywUtils.jsonArray(
                                        op("color", "display.color", "replace")
                                )))
                        )
                )
        );
    }

    private static void blockCompactedSnow(RuntimeResourcePack pack, Identifier blockID) {
        String propertyName = "layers";

        pack.addLootTable(blockTableID(blockID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:item")
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockIntProperty(propertyName, 2)))
                                        .parameter("count", 2))
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockIntProperty(propertyName, 3)))
                                        .parameter("count", 3))
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockIntProperty(propertyName, 4)))
                                        .parameter("count", 4))
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockIntProperty(propertyName, 5)))
                                        .parameter("count", 5))
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockIntProperty(propertyName, 6)))
                                        .parameter("count", 6))
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockIntProperty(propertyName, 7)))
                                        .parameter("count", 7))
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockIntProperty(propertyName, 8)))
                                        .parameter("count", 8))
                                .function("minecraft:explosion_decay")
                                .name(blockID.toString()))
                        .condition(condition("minecraft:survives_explosion")))
        );
    }

    private static void blockDropSelf(RuntimeResourcePack pack, Identifier blockID) {
        pack.addLootTable(blockTableID(blockID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:item")
                                .name(blockID.toString()))
                        .condition(condition("minecraft:survives_explosion")))
        );
    }

    private static void blockSilkTouchOnly(RuntimeResourcePack pack, Identifier blockID) {
        pack.addLootTable(blockTableID(blockID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:item")
                                .name(blockID.toString()))
                        .condition(condition("minecraft:match_tool")
                                .parameter("predicate", silkTouchPredicate())))
        );
    }

    private static void blockSilkTouch(RuntimeResourcePack pack, Identifier silkTouchID, Identifier normalID, int normalCount) {
        pack.addLootTable(blockTableID(silkTouchID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:alternatives")
                                .child(entry()
                                        .type("minecraft:item")
                                        .condition(condition("minecraft:match_tool")
                                                .parameter("predicate", silkTouchPredicate()))
                                        .name(silkTouchID.toString()))
                                .child(entry()
                                        .type("minecraft:item")
                                        .function(function("minecraft:set_count")
                                                .parameter("count", normalCount))
                                        .function(function("minecraft:explosion_decay"))
                                        .name(normalID.toString())
                                )
                        )
                        .condition(condition("minecraft:survives_explosion")))
        );
    }

    private static void blockSilkTouch(RuntimeResourcePack pack, Identifier silkTouchID, Identifier normalID, int minCount, int maxCount) {
        var count = new JsonObject();
        count.addProperty("type", "minecraft:uniform");
        count.addProperty("min", minCount);
        count.addProperty("max", maxCount);

        pack.addLootTable(blockTableID(silkTouchID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:alternatives")
                                .child(entry()
                                        .type("minecraft:item")
                                        .condition(condition("minecraft:match_tool")
                                                .parameter("predicate", silkTouchPredicate()))
                                        .name(silkTouchID.toString()))
                                .child(entry()
                                        .type("minecraft:item")
                                        .function(function("minecraft:set_count")
                                                .parameter("count", count))
                                        .function(function("minecraft:explosion_decay"))
                                        .name(normalID.toString())
                                )
                        )
                        .condition(condition("minecraft:survives_explosion")))
        );
    }

    private static void blockSlabDropSelf(RuntimeResourcePack pack, Identifier blockID) {
        pack.addLootTable(blockTableID(blockID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:item")
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockStringProperty("type", "double")))
                                        .parameter("count", 2))
                                .function("minecraft:explosion_decay")
                                .name(blockID.toString()))
                        .condition(condition("minecraft:survives_explosion")))
        );
    }

    private static void blockSlabSilkTouchOnly(RuntimeResourcePack pack, Identifier blockID) {
        pack.addLootTable(blockTableID(blockID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:item")
                                .function(function("minecraft:set_count")
                                        .condition(condition("minecraft:block_state_property")
                                                .parameter("block", blockID.toString())
                                                .parameter("properties", blockStringProperty("type", "double")))
                                        .parameter("count", 2))
                                .function("minecraft:explosion_decay")
                                .name(blockID.toString()))
                        .condition(condition("minecraft:match_tool")
                                .parameter("predicate", silkTouchPredicate())))
        );
    }

    private static void blockSlabSilkTouch(RuntimeResourcePack pack, Identifier silkTouchID, Identifier normalID) {
        pack.addLootTable(blockTableID(silkTouchID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:alternatives")
                                .child(entry()
                                        .type("minecraft:item")
                                        .condition(condition("minecraft:match_tool")
                                                .parameter("predicate", silkTouchPredicate()))
                                        .name(silkTouchID.toString())
                                        .function(function("minecraft:set_count")
                                                .condition(condition("minecraft:block_state_property")
                                                        .parameter("block", silkTouchID.toString())
                                                        .parameter("properties", blockStringProperty("type", "double")))
                                                .parameter("count", 2)))
                                .child(entry()
                                        .type("minecraft:item")
                                        .function(function("minecraft:set_count")
                                                .condition(condition("minecraft:block_state_property")
                                                        .parameter("block", silkTouchID.toString())
                                                        .parameter("properties", blockStringProperty("type", "double")))
                                                .parameter("count", 2))
                                        .function("minecraft:explosion_decay")
                                        .name(normalID.toString()))
                        )
                        .condition(condition("minecraft:survives_explosion")))
        );
    }

    private static void blockDoorDropSelf(RuntimeResourcePack pack, Identifier blockID) {
        pack.addLootTable(blockTableID(blockID), JLootTable.loot("minecraft:block")
                .pool(pool()
                        .rolls(1)
                        .entry(entry()
                                .type("minecraft:item")
                                .condition(condition("minecraft:block_state_property")
                                        .parameter("block", blockID.toString())
                                        .parameter("properties", blockStringProperty("half", "lower")))
                                .name(blockID.toString()))
                        .condition(condition("minecraft:survives_explosion")))
        );
    }

    private static void blocksDropSelf(RuntimeResourcePack pack, ArrayList<Block> blocks) {
        for (var block : blocks) {
            blockDropSelf(pack, getBlockID(block));
        }
    }

    private static void blockSlabDropSelf(RuntimeResourcePack pack, ArrayList<Block> blocks) {
        for (var block : blocks) {
            blockSlabDropSelf(pack, getBlockID(block));
        }
    }

    private static void blocksDoorDropSelf(RuntimeResourcePack pack, ArrayList<Block> blocks) {
        for (var block : blocks) {
            blockDoorDropSelf(pack, getBlockID(block));
        }
    }

    //TODO: Add slab loot table builders.

    public static void init() {
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.MYCELIUM_STAIRS), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PODZOL_STAIRS), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.GRASS_PATH_STAIRS), getBlockID(AtbywBlocks.DIRT_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_STAIRS), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.WARPED_NYLIUM_STAIRS), getBlockID(AtbywBlocks.NETHERRACK_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.SPRUCE_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BIRCH_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.JUNGLE_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.ACACIA_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.DARK_OAK_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.CRIMSON_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.WARPED_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.CHISELED_PACKED_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.CHISELED_BLUE_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PACKED_ICE_STAIRS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BLUE_ICE_STAIRS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_STAIRS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_STAIRS));

        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.GRASS_BLOCK_SLAB), getBlockID(AtbywBlocks.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.MYCELIUM_SLAB), getBlockID(AtbywBlocks.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PODZOL_SLAB), getBlockID(AtbywBlocks.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.GRASS_PATH_SLAB), getBlockID(AtbywBlocks.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.CRIMSON_NYLIUM_SLAB), getBlockID(AtbywBlocks.NETHERRACK_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.WARPED_NYLIUM_SLAB), getBlockID(AtbywBlocks.NETHERRACK_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PACKED_ICE_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BLUE_ICE_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PACKED_ICE_BRICKS_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BLUE_ICE_BRICKS_SLAB));

        blockCompactedSnow(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.COMPACTED_SNOW));

        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.SHATTERED_GLASS), getItemID(AtbywItems.GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.WHITE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.WHITE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.ORANGE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.ORANGE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.MAGENTA_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.MAGENTA_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.LIGHT_BLUE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.LIGHT_BLUE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.YELLOW_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.YELLOW_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.LIME_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.LIME_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PINK_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.PINK_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.GRAY_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.GRAY_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.LIGHT_GRAY_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.LIGHT_GRAY_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.CYAN_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.CYAN_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.PURPLE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.PURPLE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BLUE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.BLUE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BROWN_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.BROWN_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.GREEN_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.GREEN_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.RED_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.RED_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.BLACK_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.BLACK_STAINED_GLASS_SHARD), 4);

        blockColorNbt(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.CANVAS_BLOCK));
        blockColorNbt(ATBYW_RESOURCE_PACK, getBlockID(AtbywBlocks.GLOWING_CANVAS_BLOCK));

        blocksDoorDropSelf(ATBYW_RESOURCE_PACK, Lists.newArrayList(
                AtbywBlocks.IRON_FENCE_DOOR,
                AtbywBlocks.OAK_FENCE_DOOR,
                AtbywBlocks.SPRUCE_FENCE_DOOR,
                AtbywBlocks.BIRCH_FENCE_DOOR,
                AtbywBlocks.JUNGLE_FENCE_DOOR,
                AtbywBlocks.ACACIA_FENCE_DOOR,
                AtbywBlocks.DARK_OAK_FENCE_DOOR,
                AtbywBlocks.CRIMSON_FENCE_DOOR,
                AtbywBlocks.WARPED_FENCE_DOOR
        ));

        blockSlabDropSelf(ATBYW_RESOURCE_PACK, Lists.newArrayList(
                AtbywBlocks.DIRT_SLAB,
                AtbywBlocks.COARSE_DIRT_SLAB,
                AtbywBlocks.NETHERRACK_SLAB,
                AtbywBlocks.PURPUR_TILES_SLAB,
                AtbywBlocks.CUT_PURPUR_SLAB,
                AtbywBlocks.SMOOTH_PURPUR_SLAB,
                AtbywBlocks.COMPACTED_SNOW_BLOCK_SLAB,
                AtbywBlocks.COMPACTED_SNOW_BRICKS_SLAB,
                AtbywBlocks.TERRACOTTA_SLAB,
                AtbywBlocks.WHITE_TERRACOTTA_SLAB,
                AtbywBlocks.ORANGE_TERRACOTTA_SLAB,
                AtbywBlocks.MAGENTA_TERRACOTTA_SLAB,
                AtbywBlocks.LIGHT_BLUE_TERRACOTTA_SLAB,
                AtbywBlocks.YELLOW_TERRACOTTA_SLAB,
                AtbywBlocks.LIME_TERRACOTTA_SLAB,
                AtbywBlocks.PINK_TERRACOTTA_SLAB,
                AtbywBlocks.GRAY_TERRACOTTA_SLAB,
                AtbywBlocks.LIGHT_GRAY_TERRACOTTA_SLAB,
                AtbywBlocks.CYAN_TERRACOTTA_SLAB,
                AtbywBlocks.PURPLE_TERRACOTTA_SLAB,
                AtbywBlocks.BLUE_TERRACOTTA_SLAB,
                AtbywBlocks.BROWN_TERRACOTTA_SLAB,
                AtbywBlocks.GREEN_TERRACOTTA_SLAB,
                AtbywBlocks.RED_TERRACOTTA_SLAB,
                AtbywBlocks.BLACK_TERRACOTTA_SLAB,
                AtbywBlocks.TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.WHITE_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.LIME_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.PINK_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.GRAY_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.CYAN_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.BLUE_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.BROWN_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.GREEN_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.RED_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.BLACK_TERRACOTTA_BRICKS_SLAB,
                AtbywBlocks.WHITE_CONCRETE_SLAB,
                AtbywBlocks.ORANGE_CONCRETE_SLAB,
                AtbywBlocks.MAGENTA_CONCRETE_SLAB,
                AtbywBlocks.LIGHT_BLUE_CONCRETE_SLAB,
                AtbywBlocks.YELLOW_CONCRETE_SLAB,
                AtbywBlocks.LIME_CONCRETE_SLAB,
                AtbywBlocks.PINK_CONCRETE_SLAB,
                AtbywBlocks.GRAY_CONCRETE_SLAB,
                AtbywBlocks.LIGHT_GRAY_CONCRETE_SLAB,
                AtbywBlocks.CYAN_CONCRETE_SLAB,
                AtbywBlocks.PURPLE_CONCRETE_SLAB,
                AtbywBlocks.BLUE_CONCRETE_SLAB,
                AtbywBlocks.BROWN_CONCRETE_SLAB,
                AtbywBlocks.GREEN_CONCRETE_SLAB,
                AtbywBlocks.RED_CONCRETE_SLAB,
                AtbywBlocks.BLACK_CONCRETE_SLAB,
                AtbywBlocks.GRANITE_TILES_SLAB,
                AtbywBlocks.DIORITE_BRICKS_SLAB,
                AtbywBlocks.ANDESITE_BRICKS_SLAB,
                AtbywBlocks.ROOTED_DIRT_SLAB,
                AtbywBlocks.SAND_SLAB,
                AtbywBlocks.RED_SAND_SLAB,
                AtbywBlocks.GRAVEL_SLAB,
                AtbywBlocks.OAK_LOG_SLAB,
                AtbywBlocks.SPRUCE_LOG_SLAB,
                AtbywBlocks.BIRCH_LOG_SLAB,
                AtbywBlocks.JUNGLE_LOG_SLAB,
                AtbywBlocks.ACACIA_LOG_SLAB,
                AtbywBlocks.DARK_OAK_LOG_SLAB,
                AtbywBlocks.CRIMSON_STEM_SLAB,
                AtbywBlocks.WARPED_STEM_SLAB,
                AtbywBlocks.STRIPPED_OAK_LOG_SLAB,
                AtbywBlocks.STRIPPED_SPRUCE_LOG_SLAB,
                AtbywBlocks.STRIPPED_BIRCH_LOG_SLAB,
                AtbywBlocks.STRIPPED_JUNGLE_LOG_SLAB,
                AtbywBlocks.STRIPPED_ACACIA_LOG_SLAB,
                AtbywBlocks.STRIPPED_DARK_OAK_LOG_SLAB,
                AtbywBlocks.STRIPPED_CRIMSON_STEM_SLAB,
                AtbywBlocks.STRIPPED_WARPED_STEM_SLAB
        ));

        blocksDropSelf(ATBYW_RESOURCE_PACK, Lists.newArrayList(
                AtbywBlocks.OAK_BOOKSHELF_TOGGLE,
                AtbywBlocks.SPRUCE_BOOKSHELF_TOGGLE,
                AtbywBlocks.BIRCH_BOOKSHELF_TOGGLE,
                AtbywBlocks.JUNGLE_BOOKSHELF_TOGGLE,
                AtbywBlocks.ACACIA_BOOKSHELF_TOGGLE,
                AtbywBlocks.DARK_OAK_BOOKSHELF_TOGGLE,
                AtbywBlocks.CRIMSON_BOOKSHELF_TOGGLE,
                AtbywBlocks.WARPED_BOOKSHELF_TOGGLE,
                AtbywBlocks.REDSTONE_LANTERN,
                AtbywBlocks.DANDELION_PULL_SWITCH,
                AtbywBlocks.POPPY_PULL_SWITCH,
                AtbywBlocks.BLUE_ORCHID_PULL_SWITCH,
                AtbywBlocks.ALLIUM_PULL_SWITCH,
                AtbywBlocks.AZURE_BLUET_PULL_SWITCH,
                AtbywBlocks.RED_TULIP_PULL_SWITCH,
                AtbywBlocks.ORANGE_TULIP_PULL_SWITCH,
                AtbywBlocks.WHITE_TULIP_PULL_SWITCH,
                AtbywBlocks.PINK_TULIP_PULL_SWITCH,
                AtbywBlocks.OXEYE_DAISY_PULL_SWITCH,
                AtbywBlocks.CORNFLOWER_PULL_SWITCH,
                AtbywBlocks.LILY_OF_THE_VALLEY_PULL_SWITCH,
                AtbywBlocks.WITHER_ROSE_PULL_SWITCH,
                AtbywBlocks.DIRT_STAIRS,
                AtbywBlocks.COARSE_DIRT_STAIRS,
                AtbywBlocks.NETHERRACK_STAIRS,
                AtbywBlocks.SPRUCE_LADDER,
                AtbywBlocks.BIRCH_LADDER,
                AtbywBlocks.JUNGLE_LADDER,
                AtbywBlocks.ACACIA_LADDER,
                AtbywBlocks.DARK_OAK_LADDER,
                AtbywBlocks.CRIMSON_LADDER,
                AtbywBlocks.WARPED_LADDER,
                AtbywBlocks.BAMBOO_LADDER,
                AtbywBlocks.PURPUR_TILES,
                AtbywBlocks.CHISELED_PURPUR_BLOCK,
                AtbywBlocks.CUT_PURPUR_BLOCK,
                AtbywBlocks.SMOOTH_PURPUR_BLOCK,
                AtbywBlocks.PURPUR_TILES_STAIRS,
                AtbywBlocks.CUT_PURPUR_STAIRS,
                AtbywBlocks.SMOOTH_PURPUR_STAIRS,
                AtbywBlocks.COMPACTED_SNOW_BLOCK,
                AtbywBlocks.COMPACTED_SNOW_BRICKS,
                AtbywBlocks.COMPACTED_SNOW_BLOCK_STAIRS,
                AtbywBlocks.COMPACTED_SNOW_BRICKS_STAIRS,
                AtbywBlocks.BASALT_BRICKS,
                AtbywBlocks.BASALT_PILLAR,
                AtbywBlocks.TERRACOTTA_STAIRS,
                AtbywBlocks.WHITE_TERRACOTTA_STAIRS,
                AtbywBlocks.ORANGE_TERRACOTTA_STAIRS,
                AtbywBlocks.MAGENTA_TERRACOTTA_STAIRS,
                AtbywBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS,
                AtbywBlocks.YELLOW_TERRACOTTA_STAIRS,
                AtbywBlocks.LIME_TERRACOTTA_STAIRS,
                AtbywBlocks.PINK_TERRACOTTA_STAIRS,
                AtbywBlocks.GRAY_TERRACOTTA_STAIRS,
                AtbywBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS,
                AtbywBlocks.CYAN_TERRACOTTA_STAIRS,
                AtbywBlocks.PURPLE_TERRACOTTA_STAIRS,
                AtbywBlocks.BLUE_TERRACOTTA_STAIRS,
                AtbywBlocks.BROWN_TERRACOTTA_STAIRS,
                AtbywBlocks.GREEN_TERRACOTTA_STAIRS,
                AtbywBlocks.RED_TERRACOTTA_STAIRS,
                AtbywBlocks.BLACK_TERRACOTTA_STAIRS,
                AtbywBlocks.TERRACOTTA_BRICKS,
                AtbywBlocks.WHITE_TERRACOTTA_BRICKS,
                AtbywBlocks.ORANGE_TERRACOTTA_BRICKS,
                AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS,
                AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS,
                AtbywBlocks.YELLOW_TERRACOTTA_BRICKS,
                AtbywBlocks.LIME_TERRACOTTA_BRICKS,
                AtbywBlocks.PINK_TERRACOTTA_BRICKS,
                AtbywBlocks.GRAY_TERRACOTTA_BRICKS,
                AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS,
                AtbywBlocks.CYAN_TERRACOTTA_BRICKS,
                AtbywBlocks.PURPLE_TERRACOTTA_BRICKS,
                AtbywBlocks.BLUE_TERRACOTTA_BRICKS,
                AtbywBlocks.BROWN_TERRACOTTA_BRICKS,
                AtbywBlocks.GREEN_TERRACOTTA_BRICKS,
                AtbywBlocks.RED_TERRACOTTA_BRICKS,
                AtbywBlocks.BLACK_TERRACOTTA_BRICKS,
                AtbywBlocks.TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.LIME_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.PINK_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.RED_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS,
                AtbywBlocks.TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.WHITE_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.ORANGE_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.MAGENTA_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.YELLOW_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.LIME_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.PINK_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.GRAY_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.CYAN_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.PURPLE_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.BLUE_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.BROWN_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.GREEN_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.RED_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.BLACK_TERRACOTTA_BRICKS_WALL,
                AtbywBlocks.WHITE_CONCRETE_STAIRS,
                AtbywBlocks.ORANGE_CONCRETE_STAIRS,
                AtbywBlocks.MAGENTA_CONCRETE_STAIRS,
                AtbywBlocks.LIGHT_BLUE_CONCRETE_STAIRS,
                AtbywBlocks.YELLOW_CONCRETE_STAIRS,
                AtbywBlocks.LIME_CONCRETE_STAIRS,
                AtbywBlocks.PINK_CONCRETE_STAIRS,
                AtbywBlocks.GRAY_CONCRETE_STAIRS,
                AtbywBlocks.LIGHT_GRAY_CONCRETE_STAIRS,
                AtbywBlocks.CYAN_CONCRETE_STAIRS,
                AtbywBlocks.PURPLE_CONCRETE_STAIRS,
                AtbywBlocks.BLUE_CONCRETE_STAIRS,
                AtbywBlocks.BROWN_CONCRETE_STAIRS,
                AtbywBlocks.GREEN_CONCRETE_STAIRS,
                AtbywBlocks.RED_CONCRETE_STAIRS,
                AtbywBlocks.BLACK_CONCRETE_STAIRS,
                AtbywBlocks.WHITE_CINDER_BLOCKS,
                AtbywBlocks.ORANGE_CINDER_BLOCKS,
                AtbywBlocks.MAGENTA_CINDER_BLOCKS,
                AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS,
                AtbywBlocks.YELLOW_CINDER_BLOCKS,
                AtbywBlocks.LIME_CINDER_BLOCKS,
                AtbywBlocks.PINK_CINDER_BLOCKS,
                AtbywBlocks.GRAY_CINDER_BLOCKS,
                AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS,
                AtbywBlocks.CYAN_CINDER_BLOCKS,
                AtbywBlocks.PURPLE_CINDER_BLOCKS,
                AtbywBlocks.BLUE_CINDER_BLOCKS,
                AtbywBlocks.BROWN_CINDER_BLOCKS,
                AtbywBlocks.GREEN_CINDER_BLOCKS,
                AtbywBlocks.RED_CINDER_BLOCKS,
                AtbywBlocks.BLACK_CINDER_BLOCKS,
                AtbywBlocks.WHITE_CINDER_BLOCKS_WALL,
                AtbywBlocks.ORANGE_CINDER_BLOCKS_WALL,
                AtbywBlocks.MAGENTA_CINDER_BLOCKS_WALL,
                AtbywBlocks.LIGHT_BLUE_CINDER_BLOCKS_WALL,
                AtbywBlocks.YELLOW_CINDER_BLOCKS_WALL,
                AtbywBlocks.LIME_CINDER_BLOCKS_WALL,
                AtbywBlocks.PINK_CINDER_BLOCKS_WALL,
                AtbywBlocks.GRAY_CINDER_BLOCKS_WALL,
                AtbywBlocks.LIGHT_GRAY_CINDER_BLOCKS_WALL,
                AtbywBlocks.CYAN_CINDER_BLOCKS_WALL,
                AtbywBlocks.PURPLE_CINDER_BLOCKS_WALL,
                AtbywBlocks.BLUE_CINDER_BLOCKS_WALL,
                AtbywBlocks.BROWN_CINDER_BLOCKS_WALL,
                AtbywBlocks.GREEN_CINDER_BLOCKS_WALL,
                AtbywBlocks.RED_CINDER_BLOCKS_WALL,
                AtbywBlocks.BLACK_CINDER_BLOCKS_WALL,
                AtbywBlocks.GRANITE_COLUMN,
                AtbywBlocks.DIORITE_COLUMN,
                AtbywBlocks.ANDESITE_COLUMN,
                AtbywBlocks.SANDSTONE_COLUMN,
                AtbywBlocks.CHISELED_SANDSTONE_COLUMN,
                AtbywBlocks.RED_SANDSTONE_COLUMN,
                AtbywBlocks.CHISELED_RED_SANDSTONE_COLUMN,
                AtbywBlocks.PURPUR_COLUMN,
                AtbywBlocks.STONE_BRICKS_COLUMN,
                AtbywBlocks.MOSSY_STONE_BRICKS_COLUMN,
                AtbywBlocks.CRACKED_STONE_BRICKS_COLUMN,
                AtbywBlocks.NETHER_BRICKS_COLUMN,
                AtbywBlocks.QUARTZ_COLUMN,
                AtbywBlocks.PRISMARINE_COLUMN,
                AtbywBlocks.BLACKSTONE_COLUMN,
                AtbywBlocks.DEVELOPER_BLOCK,
                AtbywBlocks.SHROOMSTICK,
                StatueRegistry.WAXED_CLEAN_BEE_STATUE,
                StatueRegistry.WAXED_EXPOSED_BEE_STATUE,
                StatueRegistry.WAXED_DIRTY_BEE_STATUE,
                StatueRegistry.WAXED_MOSSY_BEE_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_BEE_STATUE,
                StatueRegistry.BEE_STATUE,
                StatueRegistry.WAXED_CLEAN_SILVERFISH_STATUE,
                StatueRegistry.WAXED_EXPOSED_SILVERFISH_STATUE,
                StatueRegistry.WAXED_DIRTY_SILVERFISH_STATUE,
                StatueRegistry.WAXED_MOSSY_SILVERFISH_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SILVERFISH_STATUE,
                StatueRegistry.SILVERFISH_STATUE,
                StatueRegistry.WAXED_CLEAN_ENDERMITE_STATUE,
                StatueRegistry.WAXED_EXPOSED_ENDERMITE_STATUE,
                StatueRegistry.WAXED_DIRTY_ENDERMITE_STATUE,
                StatueRegistry.WAXED_MOSSY_ENDERMITE_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_ENDERMITE_STATUE,
                StatueRegistry.ENDERMITE_STATUE,
                StatueRegistry.WAXED_CLEAN_SHULKER_STATUE,
                StatueRegistry.WAXED_EXPOSED_SHULKER_STATUE,
                StatueRegistry.WAXED_DIRTY_SHULKER_STATUE,
                StatueRegistry.WAXED_MOSSY_SHULKER_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SHULKER_STATUE,
                StatueRegistry.SHULKER_STATUE,
                StatueRegistry.WAXED_CLEAN_WOLF_STATUE,
                StatueRegistry.WAXED_EXPOSED_WOLF_STATUE,
                StatueRegistry.WAXED_DIRTY_WOLF_STATUE,
                StatueRegistry.WAXED_MOSSY_WOLF_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_WOLF_STATUE,
                StatueRegistry.WOLF_STATUE,
                StatueRegistry.WAXED_CLEAN_CAT_STATUE,
                StatueRegistry.WAXED_EXPOSED_CAT_STATUE,
                StatueRegistry.WAXED_DIRTY_CAT_STATUE,
                StatueRegistry.WAXED_MOSSY_CAT_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_CAT_STATUE,
                StatueRegistry.CAT_STATUE,
                StatueRegistry.WAXED_CLEAN_CHICKEN_STATUE,
                StatueRegistry.WAXED_EXPOSED_CHICKEN_STATUE,
                StatueRegistry.WAXED_DIRTY_CHICKEN_STATUE,
                StatueRegistry.WAXED_MOSSY_CHICKEN_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_CHICKEN_STATUE,
                StatueRegistry.CHICKEN_STATUE,
                StatueRegistry.WAXED_CLEAN_RABBIT_STATUE,
                StatueRegistry.WAXED_EXPOSED_RABBIT_STATUE,
                StatueRegistry.WAXED_DIRTY_RABBIT_STATUE,
                StatueRegistry.WAXED_MOSSY_RABBIT_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_RABBIT_STATUE,
                StatueRegistry.RABBIT_STATUE,
                StatueRegistry.WAXED_CLEAN_FOX_STATUE,
                StatueRegistry.WAXED_EXPOSED_FOX_STATUE,
                StatueRegistry.WAXED_DIRTY_FOX_STATUE,
                StatueRegistry.WAXED_MOSSY_FOX_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_FOX_STATUE,
                StatueRegistry.FOX_STATUE,
                StatueRegistry.WAXED_CLEAN_COD_STATUE,
                StatueRegistry.WAXED_EXPOSED_COD_STATUE,
                StatueRegistry.WAXED_DIRTY_COD_STATUE,
                StatueRegistry.WAXED_MOSSY_COD_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_COD_STATUE,
                StatueRegistry.COD_STATUE,
                StatueRegistry.WAXED_CLEAN_SALMON_STATUE,
                StatueRegistry.WAXED_EXPOSED_SALMON_STATUE,
                StatueRegistry.WAXED_DIRTY_SALMON_STATUE,
                StatueRegistry.WAXED_MOSSY_SALMON_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SALMON_STATUE,
                StatueRegistry.SALMON_STATUE,
                StatueRegistry.WAXED_CLEAN_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_EXPOSED_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_DIRTY_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_MOSSY_PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_PUFFER_FISH_STATUE,
                StatueRegistry.PUFFER_FISH_STATUE,
                StatueRegistry.WAXED_CLEAN_SLIME_STATUE,
                StatueRegistry.WAXED_EXPOSED_SLIME_STATUE,
                StatueRegistry.WAXED_DIRTY_SLIME_STATUE,
                StatueRegistry.WAXED_MOSSY_SLIME_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_SLIME_STATUE,
                StatueRegistry.SLIME_STATUE,
                StatueRegistry.WAXED_CLEAN_MAGMA_CUBE_STATUE,
                StatueRegistry.WAXED_EXPOSED_MAGMA_CUBE_STATUE,
                StatueRegistry.WAXED_DIRTY_MAGMA_CUBE_STATUE,
                StatueRegistry.WAXED_MOSSY_MAGMA_CUBE_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_MAGMA_CUBE_STATUE,
                StatueRegistry.MAGMA_CUBE_STATUE,
                AtbywBlocks.IRON_SPIKE_TRAP,
                AtbywBlocks.GOLD_SPIKE_TRAP,
                AtbywBlocks.DIAMOND_SPIKE_TRAP,
                AtbywBlocks.NETHERITE_SPIKE_TRAP,
                AtbywBlocks.REDSTONE_JACK_O_LANTERN,
                AtbywBlocks.SOUL_JACK_O_LANTERN,
                AtbywBlocks.TIMER_REPEATER,
                AtbywBlocks.REDSTONE_CROSS_PATH,
                AtbywBlocks.GRANITE_TILES,
                AtbywBlocks.DIORITE_BRICKS,
                AtbywBlocks.ANDESITE_BRICKS,
                AtbywBlocks.GRANITE_TILES_STAIRS,
                AtbywBlocks.DIORITE_BRICKS_STAIRS,
                AtbywBlocks.ANDESITE_BRICKS_STAIRS,
                AtbywBlocks.LARGE_CHAIN,
                AtbywBlocks.ROOTED_DIRT_STAIRS,
                StatueRegistry.AXOLOTL_STATUE,
                StatueRegistry.WAXED_CLEAN_AXOLOTL_STATUE,
                StatueRegistry.WAXED_EXPOSED_AXOLOTL_STATUE,
                StatueRegistry.WAXED_DIRTY_AXOLOTL_STATUE,
                StatueRegistry.WAXED_MOSSY_AXOLOTL_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_AXOLOTL_STATUE,
                StatueRegistry.BAT_STATUE,
                StatueRegistry.WAXED_CLEAN_BAT_STATUE,
                StatueRegistry.WAXED_EXPOSED_BAT_STATUE,
                StatueRegistry.WAXED_DIRTY_BAT_STATUE,
                StatueRegistry.WAXED_MOSSY_BAT_STATUE,
                StatueRegistry.WAXED_VERY_MOSSY_BAT_STATUE,
                AtbywBlocks.SAND_STAIRS,
                AtbywBlocks.RED_SAND_STAIRS,
                AtbywBlocks.GRAVEL_STAIRS,
                AtbywBlocks.OAK_LOG_STAIRS,
                AtbywBlocks.SPRUCE_LOG_STAIRS,
                AtbywBlocks.BIRCH_LOG_STAIRS,
                AtbywBlocks.JUNGLE_LOG_STAIRS,
                AtbywBlocks.ACACIA_LOG_STAIRS,
                AtbywBlocks.DARK_OAK_LOG_STAIRS,
                AtbywBlocks.CRIMSON_STEM_STAIRS,
                AtbywBlocks.WARPED_STEM_STAIRS,
                AtbywBlocks.STRIPPED_OAK_LOG_STAIRS,
                AtbywBlocks.STRIPPED_SPRUCE_LOG_STAIRS,
                AtbywBlocks.STRIPPED_BIRCH_LOG_STAIRS,
                AtbywBlocks.STRIPPED_JUNGLE_LOG_STAIRS,
                AtbywBlocks.STRIPPED_ACACIA_LOG_STAIRS,
                AtbywBlocks.STRIPPED_DARK_OAK_LOG_STAIRS,
                AtbywBlocks.STRIPPED_CRIMSON_STEM_STAIRS,
                AtbywBlocks.STRIPPED_WARPED_STEM_STAIRS,
                AtbywBlocks.REDSTONE_PIPE,
                AtbywBlocks.REDSTONE_PIPE_REPEATER,
                AtbywBlocks.TINTING_TABLE
        ));
    }
}
