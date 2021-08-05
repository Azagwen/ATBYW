package net.azagwen.atbyw.mixin;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignType.class)
public interface SignTypeInvoker {

    @Invoker("register")
    static SignType register(SignType type) {
        throw new AssertionError();
    }
}
