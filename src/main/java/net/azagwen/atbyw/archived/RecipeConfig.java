package net.azagwen.atbyw.archived;

import java.util.ArrayList;

public interface RecipeConfig {
    ArrayList<String> getPattern();
    ArrayList<Character> getKeyChars();
    int getCount();

}
