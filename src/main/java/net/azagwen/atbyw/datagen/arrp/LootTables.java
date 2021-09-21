package net.azagwen.atbyw.datagen.arrp;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.block.registry.DecorationBlockRegistry;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.block.registry.BuildingBlockRegistry;
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
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.MYCELIUM_STAIRS), getBlockID(BuildingBlockRegistry.DIRT_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PODZOL_STAIRS), getBlockID(BuildingBlockRegistry.DIRT_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.GRASS_PATH_STAIRS), getBlockID(BuildingBlockRegistry.DIRT_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.CRIMSON_NYLIUM_STAIRS), getBlockID(BuildingBlockRegistry.NETHERRACK_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.WARPED_NYLIUM_STAIRS), getBlockID(BuildingBlockRegistry.NETHERRACK_STAIRS), 1);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.SPRUCE_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BIRCH_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.JUNGLE_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.ACACIA_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.DARK_OAK_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.CRIMSON_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.WARPED_BOOKSHELF), getItemID(Items.BOOK), 3);
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.CHISELED_PACKED_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.CHISELED_BLUE_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PACKED_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BLUE_ICE_BRICKS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PACKED_ICE_STAIRS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BLUE_ICE_STAIRS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PACKED_ICE_BRICKS_STAIRS));
        blockSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BLUE_ICE_BRICKS_STAIRS));

        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.GRASS_BLOCK_SLAB), getBlockID(BuildingBlockRegistry.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.MYCELIUM_SLAB), getBlockID(BuildingBlockRegistry.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PODZOL_SLAB), getBlockID(BuildingBlockRegistry.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.GRASS_PATH_SLAB), getBlockID(BuildingBlockRegistry.DIRT_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.CRIMSON_NYLIUM_SLAB), getBlockID(BuildingBlockRegistry.NETHERRACK_SLAB));
        blockSlabSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.WARPED_NYLIUM_SLAB), getBlockID(BuildingBlockRegistry.NETHERRACK_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PACKED_ICE_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BLUE_ICE_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PACKED_ICE_BRICKS_SLAB));
        blockSlabSilkTouchOnly(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BLUE_ICE_BRICKS_SLAB));

        blockCompactedSnow(ATBYW_RESOURCE_PACK, getBlockID(DecorationBlockRegistry.COMPACTED_SNOW));

        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.SHATTERED_GLASS), getItemID(AtbywItems.GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.WHITE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.WHITE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.ORANGE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.ORANGE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.MAGENTA_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.MAGENTA_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.LIGHT_BLUE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.LIGHT_BLUE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.YELLOW_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.YELLOW_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.LIME_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.LIME_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PINK_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.PINK_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.GRAY_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.GRAY_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.LIGHT_GRAY_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.LIGHT_GRAY_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.CYAN_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.CYAN_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.PURPLE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.PURPLE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BLUE_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.BLUE_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BROWN_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.BROWN_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.GREEN_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.GREEN_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.RED_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.RED_STAINED_GLASS_SHARD), 4);
        blockSilkTouch(ATBYW_RESOURCE_PACK, getBlockID(BuildingBlockRegistry.BLACK_STAINED_SHATTERED_GLASS), getItemID(AtbywItems.BLACK_STAINED_GLASS_SHARD), 4);

        blockColorNbt(ATBYW_RESOURCE_PACK, getBlockID(DecorationBlockRegistry.CANVAS_BLOCK));
        blockColorNbt(ATBYW_RESOURCE_PACK, getBlockID(DecorationBlockRegistry.GLOWING_CANVAS_BLOCK));

        blocksDoorDropSelf(ATBYW_RESOURCE_PACK, Lists.newArrayList(
                RedstoneBlockRegistry.IRON_FENCE_DOOR,
                RedstoneBlockRegistry.OAK_FENCE_DOOR,
                RedstoneBlockRegistry.SPRUCE_FENCE_DOOR,
                RedstoneBlockRegistry.BIRCH_FENCE_DOOR,
                RedstoneBlockRegistry.JUNGLE_FENCE_DOOR,
                RedstoneBlockRegistry.ACACIA_FENCE_DOOR,
                RedstoneBlockRegistry.DARK_OAK_FENCE_DOOR,
                RedstoneBlockRegistry.CRIMSON_FENCE_DOOR,
                RedstoneBlockRegistry.WARPED_FENCE_DOOR
        ));

        blockSlabDropSelf(ATBYW_RESOURCE_PACK, Lists.newArrayList(
                BuildingBlockRegistry.DIRT_SLAB,
                BuildingBlockRegistry.COARSE_DIRT_SLAB,
                BuildingBlockRegistry.NETHERRACK_SLAB,
                BuildingBlockRegistry.PURPUR_TILES_SLAB,
                BuildingBlockRegistry.CUT_PURPUR_SLAB,
                BuildingBlockRegistry.SMOOTH_PURPUR_SLAB,
                BuildingBlockRegistry.COMPACTED_SNOW_BLOCK_SLAB,
                BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_SLAB,
                BuildingBlockRegistry.TERRACOTTA_SLAB,
                BuildingBlockRegistry.WHITE_TERRACOTTA_SLAB,
                BuildingBlockRegistry.ORANGE_TERRACOTTA_SLAB,
                BuildingBlockRegistry.MAGENTA_TERRACOTTA_SLAB,
                BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_SLAB,
                BuildingBlockRegistry.YELLOW_TERRACOTTA_SLAB,
                BuildingBlockRegistry.LIME_TERRACOTTA_SLAB,
                BuildingBlockRegistry.PINK_TERRACOTTA_SLAB,
                BuildingBlockRegistry.GRAY_TERRACOTTA_SLAB,
                BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_SLAB,
                BuildingBlockRegistry.CYAN_TERRACOTTA_SLAB,
                BuildingBlockRegistry.PURPLE_TERRACOTTA_SLAB,
                BuildingBlockRegistry.BLUE_TERRACOTTA_SLAB,
                BuildingBlockRegistry.BROWN_TERRACOTTA_SLAB,
                BuildingBlockRegistry.GREEN_TERRACOTTA_SLAB,
                BuildingBlockRegistry.RED_TERRACOTTA_SLAB,
                BuildingBlockRegistry.BLACK_TERRACOTTA_SLAB,
                BuildingBlockRegistry.TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_SLAB,
                BuildingBlockRegistry.WHITE_CONCRETE_SLAB,
                BuildingBlockRegistry.ORANGE_CONCRETE_SLAB,
                BuildingBlockRegistry.MAGENTA_CONCRETE_SLAB,
                BuildingBlockRegistry.LIGHT_BLUE_CONCRETE_SLAB,
                BuildingBlockRegistry.YELLOW_CONCRETE_SLAB,
                BuildingBlockRegistry.LIME_CONCRETE_SLAB,
                BuildingBlockRegistry.PINK_CONCRETE_SLAB,
                BuildingBlockRegistry.GRAY_CONCRETE_SLAB,
                BuildingBlockRegistry.LIGHT_GRAY_CONCRETE_SLAB,
                BuildingBlockRegistry.CYAN_CONCRETE_SLAB,
                BuildingBlockRegistry.PURPLE_CONCRETE_SLAB,
                BuildingBlockRegistry.BLUE_CONCRETE_SLAB,
                BuildingBlockRegistry.BROWN_CONCRETE_SLAB,
                BuildingBlockRegistry.GREEN_CONCRETE_SLAB,
                BuildingBlockRegistry.RED_CONCRETE_SLAB,
                BuildingBlockRegistry.BLACK_CONCRETE_SLAB,
                BuildingBlockRegistry.GRANITE_TILES_SLAB,
                BuildingBlockRegistry.DIORITE_BRICKS_SLAB,
                BuildingBlockRegistry.ANDESITE_BRICKS_SLAB,
                BuildingBlockRegistry.ROOTED_DIRT_SLAB,
                BuildingBlockRegistry.SAND_SLAB,
                BuildingBlockRegistry.RED_SAND_SLAB,
                BuildingBlockRegistry.GRAVEL_SLAB,
                BuildingBlockRegistry.OAK_LOG_SLAB,
                BuildingBlockRegistry.SPRUCE_LOG_SLAB,
                BuildingBlockRegistry.BIRCH_LOG_SLAB,
                BuildingBlockRegistry.JUNGLE_LOG_SLAB,
                BuildingBlockRegistry.ACACIA_LOG_SLAB,
                BuildingBlockRegistry.DARK_OAK_LOG_SLAB,
                BuildingBlockRegistry.CRIMSON_STEM_SLAB,
                BuildingBlockRegistry.WARPED_STEM_SLAB,
                BuildingBlockRegistry.STRIPPED_OAK_LOG_SLAB,
                BuildingBlockRegistry.STRIPPED_SPRUCE_LOG_SLAB,
                BuildingBlockRegistry.STRIPPED_BIRCH_LOG_SLAB,
                BuildingBlockRegistry.STRIPPED_JUNGLE_LOG_SLAB,
                BuildingBlockRegistry.STRIPPED_ACACIA_LOG_SLAB,
                BuildingBlockRegistry.STRIPPED_DARK_OAK_LOG_SLAB,
                BuildingBlockRegistry.STRIPPED_CRIMSON_STEM_SLAB,
                BuildingBlockRegistry.STRIPPED_WARPED_STEM_SLAB,
                BuildingBlockRegistry.CACTUS_SLAB
        ));

        blocksDropSelf(ATBYW_RESOURCE_PACK, Lists.newArrayList(
                RedstoneBlockRegistry.OAK_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.SPRUCE_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.BIRCH_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.JUNGLE_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.ACACIA_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.DARK_OAK_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.CRIMSON_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.WARPED_BOOKSHELF_TOGGLE,
                RedstoneBlockRegistry.REDSTONE_LANTERN,
                RedstoneBlockRegistry.DANDELION_PULL_SWITCH,
                RedstoneBlockRegistry.POPPY_PULL_SWITCH,
                RedstoneBlockRegistry.BLUE_ORCHID_PULL_SWITCH,
                RedstoneBlockRegistry.ALLIUM_PULL_SWITCH,
                RedstoneBlockRegistry.AZURE_BLUET_PULL_SWITCH,
                RedstoneBlockRegistry.RED_TULIP_PULL_SWITCH,
                RedstoneBlockRegistry.ORANGE_TULIP_PULL_SWITCH,
                RedstoneBlockRegistry.WHITE_TULIP_PULL_SWITCH,
                RedstoneBlockRegistry.PINK_TULIP_PULL_SWITCH,
                RedstoneBlockRegistry.OXEYE_DAISY_PULL_SWITCH,
                RedstoneBlockRegistry.CORNFLOWER_PULL_SWITCH,
                RedstoneBlockRegistry.LILY_OF_THE_VALLEY_PULL_SWITCH,
                RedstoneBlockRegistry.WITHER_ROSE_PULL_SWITCH,
                BuildingBlockRegistry.DIRT_STAIRS,
                BuildingBlockRegistry.COARSE_DIRT_STAIRS,
                BuildingBlockRegistry.NETHERRACK_STAIRS,
                DecorationBlockRegistry.SPRUCE_LADDER,
                DecorationBlockRegistry.BIRCH_LADDER,
                DecorationBlockRegistry.JUNGLE_LADDER,
                DecorationBlockRegistry.ACACIA_LADDER,
                DecorationBlockRegistry.DARK_OAK_LADDER,
                DecorationBlockRegistry.CRIMSON_LADDER,
                DecorationBlockRegistry.WARPED_LADDER,
                DecorationBlockRegistry.BAMBOO_LADDER,
                BuildingBlockRegistry.PURPUR_TILES,
                BuildingBlockRegistry.CHISELED_PURPUR_BLOCK,
                BuildingBlockRegistry.CUT_PURPUR_BLOCK,
                BuildingBlockRegistry.SMOOTH_PURPUR_BLOCK,
                BuildingBlockRegistry.PURPUR_TILES_STAIRS,
                BuildingBlockRegistry.CUT_PURPUR_STAIRS,
                BuildingBlockRegistry.SMOOTH_PURPUR_STAIRS,
                BuildingBlockRegistry.COMPACTED_SNOW_BLOCK,
                BuildingBlockRegistry.COMPACTED_SNOW_BRICKS,
                BuildingBlockRegistry.CHISELED_COMPACTED_SNOW_BRICKS,
                BuildingBlockRegistry.COMPACTED_SNOW_BLOCK_STAIRS,
                BuildingBlockRegistry.COMPACTED_SNOW_BRICKS_STAIRS,
                BuildingBlockRegistry.BASALT_BRICKS,
                BuildingBlockRegistry.BASALT_PILLAR,
                BuildingBlockRegistry.TERRACOTTA_STAIRS,
                BuildingBlockRegistry.WHITE_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.ORANGE_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.MAGENTA_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.YELLOW_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.LIME_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.PINK_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.GRAY_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.CYAN_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.PURPLE_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.BLUE_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.BROWN_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.GREEN_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.RED_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.BLACK_TERRACOTTA_STAIRS,
                BuildingBlockRegistry.TERRACOTTA_BRICKS,
                BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.RED_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS,
                BuildingBlockRegistry.TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.WHITE_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.ORANGE_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.YELLOW_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.LIME_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.PINK_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.GRAY_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.CYAN_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.PURPLE_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.BLUE_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.BROWN_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.GREEN_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.RED_TERRACOTTA_BRICKS_STAIRS,
                BuildingBlockRegistry.BLACK_TERRACOTTA_BRICKS_STAIRS,
                DecorationBlockRegistry.TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.WHITE_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.ORANGE_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.MAGENTA_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.YELLOW_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.LIME_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.PINK_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.GRAY_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.CYAN_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.PURPLE_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.BLUE_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.BROWN_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.GREEN_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.RED_TERRACOTTA_BRICKS_WALL,
                DecorationBlockRegistry.BLACK_TERRACOTTA_BRICKS_WALL,
                BuildingBlockRegistry.WHITE_CONCRETE_STAIRS,
                BuildingBlockRegistry.ORANGE_CONCRETE_STAIRS,
                BuildingBlockRegistry.MAGENTA_CONCRETE_STAIRS,
                BuildingBlockRegistry.LIGHT_BLUE_CONCRETE_STAIRS,
                BuildingBlockRegistry.YELLOW_CONCRETE_STAIRS,
                BuildingBlockRegistry.LIME_CONCRETE_STAIRS,
                BuildingBlockRegistry.PINK_CONCRETE_STAIRS,
                BuildingBlockRegistry.GRAY_CONCRETE_STAIRS,
                BuildingBlockRegistry.LIGHT_GRAY_CONCRETE_STAIRS,
                BuildingBlockRegistry.CYAN_CONCRETE_STAIRS,
                BuildingBlockRegistry.PURPLE_CONCRETE_STAIRS,
                BuildingBlockRegistry.BLUE_CONCRETE_STAIRS,
                BuildingBlockRegistry.BROWN_CONCRETE_STAIRS,
                BuildingBlockRegistry.GREEN_CONCRETE_STAIRS,
                BuildingBlockRegistry.RED_CONCRETE_STAIRS,
                BuildingBlockRegistry.BLACK_CONCRETE_STAIRS,
                BuildingBlockRegistry.WHITE_CINDER_BLOCKS,
                BuildingBlockRegistry.ORANGE_CINDER_BLOCKS,
                BuildingBlockRegistry.MAGENTA_CINDER_BLOCKS,
                BuildingBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS,
                BuildingBlockRegistry.YELLOW_CINDER_BLOCKS,
                BuildingBlockRegistry.LIME_CINDER_BLOCKS,
                BuildingBlockRegistry.PINK_CINDER_BLOCKS,
                BuildingBlockRegistry.GRAY_CINDER_BLOCKS,
                BuildingBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS,
                BuildingBlockRegistry.CYAN_CINDER_BLOCKS,
                BuildingBlockRegistry.PURPLE_CINDER_BLOCKS,
                BuildingBlockRegistry.BLUE_CINDER_BLOCKS,
                BuildingBlockRegistry.BROWN_CINDER_BLOCKS,
                BuildingBlockRegistry.GREEN_CINDER_BLOCKS,
                BuildingBlockRegistry.RED_CINDER_BLOCKS,
                BuildingBlockRegistry.BLACK_CINDER_BLOCKS,
                DecorationBlockRegistry.WHITE_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.ORANGE_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.MAGENTA_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.LIGHT_BLUE_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.YELLOW_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.LIME_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.PINK_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.GRAY_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.LIGHT_GRAY_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.CYAN_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.PURPLE_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.BLUE_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.BROWN_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.GREEN_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.RED_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.BLACK_CINDER_BLOCKS_WALL,
                DecorationBlockRegistry.GRANITE_COLUMN,
                DecorationBlockRegistry.DIORITE_COLUMN,
                DecorationBlockRegistry.ANDESITE_COLUMN,
                DecorationBlockRegistry.SANDSTONE_COLUMN,
                DecorationBlockRegistry.CHISELED_SANDSTONE_COLUMN,
                DecorationBlockRegistry.RED_SANDSTONE_COLUMN,
                DecorationBlockRegistry.CHISELED_RED_SANDSTONE_COLUMN,
                DecorationBlockRegistry.PURPUR_COLUMN,
                DecorationBlockRegistry.STONE_BRICKS_COLUMN,
                DecorationBlockRegistry.MOSSY_STONE_BRICKS_COLUMN,
                DecorationBlockRegistry.CRACKED_STONE_BRICKS_COLUMN,
                DecorationBlockRegistry.NETHER_BRICKS_COLUMN,
                DecorationBlockRegistry.QUARTZ_COLUMN,
                DecorationBlockRegistry.PRISMARINE_COLUMN,
                DecorationBlockRegistry.BLACKSTONE_COLUMN,
                DecorationBlockRegistry.DEVELOPER_BLOCK,
                DecorationBlockRegistry.SHROOMSTICK,
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
                RedstoneBlockRegistry.IRON_SPIKE_TRAP,
                RedstoneBlockRegistry.GOLD_SPIKE_TRAP,
                RedstoneBlockRegistry.DIAMOND_SPIKE_TRAP,
                RedstoneBlockRegistry.NETHERITE_SPIKE_TRAP,
                RedstoneBlockRegistry.REDSTONE_JACK_O_LANTERN,
                BuildingBlockRegistry.SOUL_JACK_O_LANTERN,
                RedstoneBlockRegistry.TIMER_REPEATER,
                RedstoneBlockRegistry.REDSTONE_CROSS_PATH,
                BuildingBlockRegistry.GRANITE_TILES,
                BuildingBlockRegistry.DIORITE_BRICKS,
                BuildingBlockRegistry.ANDESITE_BRICKS,
                BuildingBlockRegistry.GRANITE_TILES_STAIRS,
                BuildingBlockRegistry.DIORITE_BRICKS_STAIRS,
                BuildingBlockRegistry.ANDESITE_BRICKS_STAIRS,
                DecorationBlockRegistry.LARGE_CHAIN,
                BuildingBlockRegistry.ROOTED_DIRT_STAIRS,
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
                BuildingBlockRegistry.SAND_STAIRS,
                BuildingBlockRegistry.RED_SAND_STAIRS,
                BuildingBlockRegistry.GRAVEL_STAIRS,
                BuildingBlockRegistry.OAK_LOG_STAIRS,
                BuildingBlockRegistry.SPRUCE_LOG_STAIRS,
                BuildingBlockRegistry.BIRCH_LOG_STAIRS,
                BuildingBlockRegistry.JUNGLE_LOG_STAIRS,
                BuildingBlockRegistry.ACACIA_LOG_STAIRS,
                BuildingBlockRegistry.DARK_OAK_LOG_STAIRS,
                BuildingBlockRegistry.CRIMSON_STEM_STAIRS,
                BuildingBlockRegistry.WARPED_STEM_STAIRS,
                BuildingBlockRegistry.STRIPPED_OAK_LOG_STAIRS,
                BuildingBlockRegistry.STRIPPED_SPRUCE_LOG_STAIRS,
                BuildingBlockRegistry.STRIPPED_BIRCH_LOG_STAIRS,
                BuildingBlockRegistry.STRIPPED_JUNGLE_LOG_STAIRS,
                BuildingBlockRegistry.STRIPPED_ACACIA_LOG_STAIRS,
                BuildingBlockRegistry.STRIPPED_DARK_OAK_LOG_STAIRS,
                BuildingBlockRegistry.STRIPPED_CRIMSON_STEM_STAIRS,
                BuildingBlockRegistry.STRIPPED_WARPED_STEM_STAIRS,
                RedstoneBlockRegistry.REDSTONE_PIPE,
                RedstoneBlockRegistry.REDSTONE_PIPE_REPEATER,
                RedstoneBlockRegistry.REDSTONE_PIPE_INVERTER,
                DecorationBlockRegistry.TINTING_TABLE,
                BuildingBlockRegistry.RAW_CACTUS_PLANKS,
                BuildingBlockRegistry.CACTUS_PLANKS,
                BuildingBlockRegistry.CACTUS_STAIRS,
                DecorationBlockRegistry.CACTUS_FENCE,
                DecorationBlockRegistry.CACTUS_LADDER,
                DecorationBlockRegistry.CACTUS_SIGN
        ));
    }
}
