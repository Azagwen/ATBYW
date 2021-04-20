package net.azagwen.atbyw.datagen;

import java.util.ArrayList;

public interface AtbywRecipeConfig {
    ArrayList<String> getPattern();
    ArrayList<Key<Character, String>> getKeys();
    int getCount();

}
