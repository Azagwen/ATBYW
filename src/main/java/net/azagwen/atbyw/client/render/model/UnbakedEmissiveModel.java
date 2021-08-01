package net.azagwen.atbyw.client.render.model;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
        return new BakedEmissiveModel(baseModel.bake(loader, textureGetter, rotationContainer, modelId));
    }
}
