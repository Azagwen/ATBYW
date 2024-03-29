package net.azagwen.atbyw.mixin.client;

import net.azagwen.atbyw.client.render.model.*;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    private final ModelLoader self = (ModelLoader) (Object) this;

    @Shadow protected abstract void putModel(Identifier id, UnbakedModel unbakedModel);

    @Inject(method = "putModel", at = @At("HEAD"), cancellable = true)
    private void onPutModel(Identifier id, UnbakedModel unbakedModel, CallbackInfo ci) {
        if (id instanceof ModelIdentifier modelId && !(unbakedModel instanceof AtbywUnbakedModel)) {
            if (!modelId.getVariant().equals("inventory")) {
                if (modelId.getNamespace().equals(AtbywMain.ATBYW)) {
                    if (modelId.getPath().contains("timer")) {
                        this.putModel(id, new UnbakedForwardingModel(unbakedModel, BakedTimerRepeaterDigitModel::new));
                        ci.cancel();
                    }
                }
            }
        }
    }
}
