package net.azagwen.atbyw.mixin.client;

import net.azagwen.atbyw.client.render.AtbywTextureRenderLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(TexturedRenderLayers.class)
@Environment(EnvType.CLIENT)
public class TexturedRenderLayersMixin {

    @Inject(method = "addDefaultTextures(Ljava/util/function/Consumer;)V", at =
    @At(value = "HEAD"), cancellable = true)
    private static void addDefaultTextures(Consumer<SpriteIdentifier> adder, CallbackInfo cbi) {
        for (SpriteIdentifier identifier : AtbywTextureRenderLayers.addAtbywTextures) {
            adder.accept(identifier);
        }
    }
}
