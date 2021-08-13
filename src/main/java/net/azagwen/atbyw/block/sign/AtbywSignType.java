package net.azagwen.atbyw.block.sign;

import com.google.common.collect.Sets;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.util.Identifier;

import java.util.Set;

public class AtbywSignType {
    public static final Set<AtbywSignType> TYPES = Sets.newHashSet();
    public static final AtbywSignType CACTUS = register("cactus");
    private final String name;

    public AtbywSignType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Identifier getTexture() {
        return AtbywMain.id("entity/signs/" + this.getName());
    }

    public static AtbywSignType register(String woodName) {
        var type = new AtbywSignType(woodName);
        TYPES.add(type);
        return type;
    }

}
