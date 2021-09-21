package net.azagwen.atbyw.mixin.client;

import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.azagwen.atbyw.block.registry.DecorationBlockRegistry;
import net.azagwen.atbyw.block.registry.RedstoneBlockRegistry;
import net.azagwen.atbyw.client.render.model.*;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.block.Block;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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
            if (modelId.getNamespace().equals(AtbywMain.ATBYW)) {
                if (!modelId.getVariant().equals("inventory")) {
                    if (this.isModelOf(modelId, RedstoneBlockRegistry.TIMER_REPEATER)) {
                        this.putModel(id, new UnbakedForwardingModel(unbakedModel, BakedTimerRepeaterDigitModel::new));
                        ci.cancel();
                    }
                    if (this.isModelOf(modelId, DecorationBlockRegistry.GLOWING_CANVAS_BLOCK)) {
                        this.putModel(id, new UnbakedForwardingModel(unbakedModel, BakedGlowingCanvasBlockModel::new));
                        ci.cancel();
                    }
                    if (this.isModelOf(modelId, AtbywBlocks.CTM_DEBUG_BLOCK)) {
                        this.putModel(id, new UnbakedForwardingModel(unbakedModel, (model) -> new BakedConnectedTextureModel(model, SpriteRegistry.CTM_TEMPLATE)));
                        ci.cancel();
                    }
                }
            }
        }
    }

    private boolean isModelOf(ModelIdentifier modelId, ItemConvertible itemConvertible) {
        var identifier = new Identifier("");

        if (itemConvertible instanceof Block block) {
            identifier = Registry.BLOCK.getId(block);
        } else if (itemConvertible instanceof Item item) {
            identifier = Registry.ITEM.getId(item);
        }

        return modelId.getPath().equals(identifier.getPath());
    }
}
