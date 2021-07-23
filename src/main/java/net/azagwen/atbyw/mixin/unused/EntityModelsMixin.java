package net.azagwen.atbyw.mixin.unused;

import com.google.common.collect.ImmutableMap;
import net.azagwen.atbyw.client.render.AtbywEntityModelLayers;
import net.azagwen.atbyw.archived.TimerRepeaterBlockEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(EntityModels.class)
public class EntityModelsMixin {
    //Kept as an example, unused however
    @Inject(method = "getModels()Ljava/util/Map;", at =
    @At(
            value = "INVOKE",
            target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"
    ), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void getModels(CallbackInfoReturnable<Map<EntityModelLayer, TexturedModelData>> cir, ImmutableMap.Builder<EntityModelLayer, TexturedModelData> builder) {
        builder.put(AtbywEntityModelLayers.TIMER_REPEATER, TimerRepeaterBlockEntityRenderer.getTexturedModelData());
    }
}