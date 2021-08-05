package net.azagwen.atbyw.block.state;

import net.azagwen.atbyw.mixin.SignTypeInvoker;
import net.minecraft.util.SignType;

public class SignTypeSubClass extends SignType {
    public static final SignType CACTUS = SignTypeInvoker.register(new SignTypeSubClass("cactus"));

    protected SignTypeSubClass(String name) {
        super(name);
    }
}
