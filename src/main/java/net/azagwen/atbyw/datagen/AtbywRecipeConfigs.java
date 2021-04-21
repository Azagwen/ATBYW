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
    BRICKS_1(Lists.newArrayList("##", "##"), Lists.newArrayList('#'), 4),
    STAIRS_1(Lists.newArrayList("#  ", "## ", "###"), Lists.newArrayList('#'), 4),
    SLAB_1(Lists.newArrayList("###"), Lists.newArrayList('#'), 6),
    WALL_1(Lists.newArrayList("###", "###"), Lists.newArrayList('#'), 6),
    STICK_1(Lists.newArrayList("#", "#"), Lists.newArrayList('#'), 2),
    COLUMN_1(Lists.newArrayList("#", "#", "#"), Lists.newArrayList('#'), 3),

    // 2 Ingredients
    DYING_2(Lists.newArrayList("BBB", "BDB", "BBB"), Lists.newArrayList('B', 'D'), 8),
    SWORD_2(Lists.newArrayList("#", "#", "S"), Lists.newArrayList('#', 'S'), 1),
    AXE_2(Lists.newArrayList("##", "#S", " S"), Lists.newArrayList('#', 'S'), 1),
    PICKAXE_2(Lists.newArrayList("###", " S ", " S "), Lists.newArrayList('#', 'S'), 1),
    SHOVEL_2(Lists.newArrayList("#", "S", "S"), Lists.newArrayList('#', 'S'), 1),
    HOE_2(Lists.newArrayList("##", " S", " S"), Lists.newArrayList('#', 'S'), 1),

    // 3 Ingredients
    DYING_DASHED_3(Lists.newArrayList("X#X", "#O#", "X#X"), Lists.newArrayList('#', 'X', 'O'), 8);

    ArrayList<String> pattern;
    ArrayList<Character> keyChars;
    int count;

    AtbywRecipeConfigs(ArrayList<String> pattern, ArrayList<Character> keyChars, int count) {
        this.pattern = pattern;
        this.count = count;
        this.keyChars = keyChars;
    }

    @Override
    public ArrayList<String> getPattern() {
        return this.pattern;
    }

    @Override
    public ArrayList<Character> getKeyChars() {
        return this.keyChars;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
