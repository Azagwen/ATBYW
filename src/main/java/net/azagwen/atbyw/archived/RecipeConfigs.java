package net.azagwen.atbyw.archived;

import com.google.common.collect.Lists;
import java.util.ArrayList;

public enum RecipeConfigs implements RecipeConfig {
    /**
     *  Below are the recipe Configs, those contain a patterns, a list of keys and a result count.
     *  these configs follow a naming convention which goes as follow:
     *  NAME_[number of ingredients]
     *  With this naming convention applied, "BRICKS_1" contains the pattern of the vanilla stone bricks,
     *  and only require one ingredient.
     */

    // 1 Ingredients
    STICK_1(Lists.newArrayList("#", "#"), Lists.newArrayList('#'), 4),
    LADDER_1(Lists.newArrayList("# #", "###", "# #"), Lists.newArrayList('#'), 3),
    BRICKS_1(Lists.newArrayList("##", "##"), Lists.newArrayList('#'), 4),
    STAIRS_1(Lists.newArrayList("#  ", "## ", "###"), Lists.newArrayList('#'), 4),
    SLAB_1(Lists.newArrayList("###"), Lists.newArrayList('#'), 6),
    WALL_1(Lists.newArrayList("###", "###"), Lists.newArrayList('#'), 6),
    FENCE_DOOR_1(Lists.newArrayList("# #", "# #", "# #"), Lists.newArrayList('#'), 3),
    COLUMN_1(Lists.newArrayList("#", "#", "#"), Lists.newArrayList('#'), 3),
    STAR_1(Lists.newArrayList(" # ", "# #", " # "), Lists.newArrayList('#'), 1),

    // 2 Ingredients
    TORCH_2(Lists.newArrayList("#", "S"), Lists.newArrayList('#', 'S'), 4),
    FENCE_2(Lists.newArrayList("#S#", "#S#"), Lists.newArrayList('#', 'S'), 3),
    FENCE_GATE_2(Lists.newArrayList("S#S", "S#S"), Lists.newArrayList('#', 'S'), 1),
    SIGN_2(Lists.newArrayList("###", "###", " S "), Lists.newArrayList('#', 'S'), 3),
    BOOKSHELF_2(Lists.newArrayList("###", "BBB", "###"), Lists.newArrayList('#', 'B'), 1),
    DYING_2(Lists.newArrayList("BBB", "BDB", "BBB"), Lists.newArrayList('B', 'D'), 8),
    SWORD_2(Lists.newArrayList("#", "#", "S"), Lists.newArrayList('#', 'S'), 1),
    AXE_2(Lists.newArrayList("##", "#S", " S"), Lists.newArrayList('#', 'S'), 1),
    PICKAXE_2(Lists.newArrayList("###", " S ", " S "), Lists.newArrayList('#', 'S'), 1),
    SHOVEL_2(Lists.newArrayList("#", "S", "S"), Lists.newArrayList('#', 'S'), 1),
    HOE_2(Lists.newArrayList("##", " S", " S"), Lists.newArrayList('#', 'S'), 1),
    RAIL_2(Lists.newArrayList("# #", "#S#", "# #"), Lists.newArrayList('#', 'S'), 16),
    BOW_2(Lists.newArrayList(" #S", "# S", " #S"), Lists.newArrayList('#', 'S'), 1),
    FISHING_ROD_2(Lists.newArrayList("  #", " #S", "# S"), Lists.newArrayList('#', 'S'), 1),
    ARMOR_STAND_2(Lists.newArrayList("###", " # ", "#S#"), Lists.newArrayList('#', 'S'), 1),
    BED_2(Lists.newArrayList("XXX", "###"), Lists.newArrayList('#', 'X'), 1),

    // 3 Ingredients
    ARROW_3(Lists.newArrayList("#", "S", "F"), Lists.newArrayList('#', 'S', 'F'), 4),
    DYING_DASHED_3(Lists.newArrayList("X#X", "#O#", "X#X"), Lists.newArrayList('#', 'X', 'O'), 8),
    FLOWER_SWITCH_3(Lists.newArrayList(" F ", "RSR"), Lists.newArrayList('F', 'S', 'R'), 1),
    RAIL_POWERED_3(Lists.newArrayList("# #", "#S#", "#R#"), Lists.newArrayList('#', 'S', 'R'), 6),
    RAIL_ACTIVATOR_3(Lists.newArrayList("#S#", "#R#", "#S#"), Lists.newArrayList('#', 'S', 'R'), 6),
    TIMER_REPEATER_3(Lists.newArrayList(" C ", "R#R", " R "), Lists.newArrayList('C', 'R', '#'), 1),

    // 4 Ingredients
    BOOKSHELF_TOGGLE_4(Lists.newArrayList("PPP", "RBR", "CRC"), Lists.newArrayList('P', 'B', 'R', 'C'), 1),
    SPIKE_TRAP_4(Lists.newArrayList("S#S", "#I#", "SRS"), Lists.newArrayList('#', 'S', 'I', 'R'), 1),
    CROSSBOW_4(Lists.newArrayList("#I#", "SHS", " # "), Lists.newArrayList('#', 'S', 'I', 'H'), 1);

    ArrayList<String> pattern;
    ArrayList<Character> keyChars;
    int count;

    RecipeConfigs(ArrayList<String> pattern, ArrayList<Character> keyChars, int count) {
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
