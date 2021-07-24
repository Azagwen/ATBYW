package net.azagwen.atbyw.mixin.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.azagwen.atbyw.datagen.ItemModels.*;

@Environment(EnvType.CLIENT)
@Mixin(ModelLoader.class)
public class ItemModelLoaderMixin {

    private void makeModel(Identifier id, String containsCheck, String modelJson, CallbackInfoReturnable<JsonUnbakedModel> cir) {
        if (id.getPath().contains(containsCheck)) {
            if ("".equals(modelJson)) return;
            JsonUnbakedModel model = JsonUnbakedModel.deserialize(modelJson);
            model.id = id.toString();
            cir.setReturnValue(model);
            cir.cancel();
        }
    }

    @Inject(method = "loadModelFromJson", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;getResource(Lnet/minecraft/util/Identifier;)Lnet/minecraft/resource/Resource;"), cancellable = true)
    public void loadModelFromJson(Identifier id, CallbackInfoReturnable<JsonUnbakedModel> cir) {
        if (AtbywMain.ATBYW.equals(id.getNamespace())) {
            Gson gson = new GsonBuilder().create();
            makeModel(id, "_essence", gson.toJson(createEssenceItemModelJson("generated")), cir);
        }
    }
}
