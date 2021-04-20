package net.azagwen.atbyw.datagen;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public enum AtbywRecipeConfigs implements AtbywRecipeConfig {
    /**
     *  Below are the recipe Configs, those contain a patterns, a list of keys and a result count.
     *  these configs follow a naming convention which goes as follow:
     *  NAME_[number of ingredients]
     *  With this naming convention applied, "BRICKS_1" contains the pattern of the vanilla stone bricks,
     *  and only require one ingredient.
     */
    // 1 Ingredients
    BRICKS_1(Lists.newArrayList("##", "##"), Lists.newArrayList(newKey('#', "item")), 4),
    STAIRS_1(Lists.newArrayList("#  ", "## ", "###"), Lists.newArrayList(newKey('#', "item")), 4),
    SLAB_1(Lists.newArrayList("###"), Lists.newArrayList(newKey('#', "item")), 6),
    WALL_1(Lists.newArrayList("###", "###"), Lists.newArrayList(newKey('#', "item")), 6),
    STICK_1(Lists.newArrayList("#", "#"), Lists.newArrayList(newKey('#', "item")), 2),
    COLUMN_1(Lists.newArrayList("#", "#", "#"), Lists.newArrayList(newKey('#', "item")), 3),

    // 2 Ingredients
    DYING_2(Lists.newArrayList("BBB", "BDB", "BBB"), Lists.newArrayList(newKey('B', "item"), newKey('D', "item")), 8),
    SWORD_2(Lists.newArrayList("#", "#", "S"), Lists.newArrayList(newKey('#', "item"), newKey('S', "tag")), 1),
    AXE_2(Lists.newArrayList("##", "#S", " S"), Lists.newArrayList(newKey('#', "item"), newKey('S', "tag")), 1),
    PICKAXE_2(Lists.newArrayList("###", " S ", " S "), Lists.newArrayList(newKey('#', "item"), newKey('S', "tag")), 1),
    SHOVEL_2(Lists.newArrayList("#", "S", "S"), Lists.newArrayList(newKey('#', "item"), newKey('S', "tag")), 1),
    HOE_2(Lists.newArrayList("##", " S", " S"), Lists.newArrayList(newKey('#', "item"), newKey('S', "tag")), 1);

    ArrayList<String> pattern;
    ArrayList<Key<Character, String>> keys;
    int count;

    AtbywRecipeConfigs(ArrayList<String> pattern, ArrayList<Key<Character, String>> keys, int count) {
        this.pattern = pattern;
        this.count = count;
        this.keys = keys;
    }

    private static Key<Character, String> newKey(Character c, String type) {
        return new Key<>(c, type);
    }

    public AtbywRecipeConfigs overrideCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public ArrayList<String> getPattern() {
        return this.pattern;
    }

    @Override
    public ArrayList<Key<Character, String>> getKeys() {
        return this.keys;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
