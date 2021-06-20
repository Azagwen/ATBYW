package net.azagwen.atbyw.datagen;

import java.util.ArrayList;

public interface RecipeConfig {
    ArrayList<String> getPattern();
    ArrayList<Character> getKeyChars();
    int getCount();

}
