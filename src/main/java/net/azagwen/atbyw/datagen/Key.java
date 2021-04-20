package net.azagwen.atbyw.datagen;

public class Key<A, B> {
    private A character;
    private B type;

    public Key(A first, B second) {
        this.character = first;
        this.type = second;
    }

    public A getCharacter() {
        return this.character;
    }

    public B getType() {
        return this.type;
    }
}
