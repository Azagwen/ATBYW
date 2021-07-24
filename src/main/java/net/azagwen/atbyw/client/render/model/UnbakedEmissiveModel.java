package net.azagwen.atbyw.client.render.model;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public record UnbakedEmissiveModel(UnbakedModel baseModel) implements AtbywUnbakedModel {

    @Override
    public Collection<Identifier> getModelDependencies() {
        return this.baseModel.getModelDependencies();
    }

    @Override
    public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
        return this.baseModel.getTextureDependencies(unbakedModelGetter, unresolvedTextureReferences);
    }

    @Override
    public @NotNull BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        var model = (UnbakedModel) null;
        for (var dep : this.baseModel.getModelDependencies()) {
            if (!dep.getPath().contains("_emissive")) {
                model = loader.getOrLoadModel(dep);
            }
        }
        var emissiveModel = loader.getOrLoadModel(new Identifier(modelId.getNamespace(), "block/" + modelId.getPath() + "_emissive"));
        return new BakedEmissiveModel(
                model.bake(loader, textureGetter, rotationContainer, modelId),
                emissiveModel.bake(loader, textureGetter, rotationContainer, modelId)
        );
    }
}
