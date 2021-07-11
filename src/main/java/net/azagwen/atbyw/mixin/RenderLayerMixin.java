package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.client.render.AtbywRenderLayer;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(RenderLayer.class)
public class RenderLayerMixin {

    @Inject(method = "getBlockLayers()Ljava/util/List;", at = @At(value = "HEAD"),
            cancellable = true)
    private static void mixin(CallbackInfoReturnable<List<RenderLayer>> cir) {
        cir.setReturnValue(AtbywRenderLayer.getBlockLayers());
    }
}
