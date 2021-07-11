package net.azagwen.atbyw.archived;

import net.minecraft.util.Identifier;

public class Key {
    private final Character CHAR;
    private final String TYPE;
    private final Identifier INGREDIENT;

    public Key(Character C, String T, Identifier I) {
        this.CHAR = C;
        this.TYPE = T;
        this.INGREDIENT = I;
    }

    public Character getChar() {
        return this.CHAR;
    }

    public String getType() {
        return this.TYPE;
    }

    public Identifier getIngredient() {
        return this.INGREDIENT;
    }

    @Override
    public String toString() {
        return "[ " + this.CHAR.toString() + ", " + this.TYPE + ", " + this.INGREDIENT.toString() + " ]";
    }
}
