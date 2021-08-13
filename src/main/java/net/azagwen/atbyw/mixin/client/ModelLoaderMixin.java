package net.azagwen.atbyw.mixin.client;

import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.client.render.model.*;
import net.azagwen.atbyw.main.AtbywMain;
import net.azagwen.atbyw.util.AtbywUtils;
import net.minecraft.block.Block;
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
                    if (this.isModelOf(modelId, AtbywBlocks.TIMER_REPEATER)) {
                        this.putModel(id, new UnbakedForwardingModel(unbakedModel, BakedTimerRepeaterDigitModel::new));
                        ci.cancel();
                    }
                    if (this.isModelOf(modelId, AtbywBlocks.GLOWING_CANVAS_BLOCK)) {
                        this.putModel(id, new UnbakedForwardingModel(unbakedModel, BakedGlowingCanvasBlockModel::new));
                        ci.cancel();
                    }
                }
            }
        }
    }

    private boolean isModelOf(ModelIdentifier modelId, Block block) {
        return modelId.getPath().equals(AtbywUtils.getBlockID(block).getPath());
    }
}
