package net.azagwen.atbyw.mixin.datagen;

import com.google.gson.JsonElement;
import net.azagwen.atbyw.datagen.RecipeDatagen;
import net.minecraft.advancement.Advancement;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(ServerAdvancementLoader.class)
public class ServerAdvancementLoaderMixin {

    @Inject(
            method = "apply",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;forEach(Ljava/util/function/BiConsumer;)V"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void onApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info, Map<Identifier, Advancement.Task> builder) {
        RecipeDatagen.applyAdvancements(builder);
    }
}
