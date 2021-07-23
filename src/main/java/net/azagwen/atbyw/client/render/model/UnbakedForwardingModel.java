package net.azagwen.atbyw.client.render.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

/**
 * Taken from Aurora's Decorations.
 * Represents an unbaked model that forwards another unbaked model.
 *
 * @author LambdAurora
 * @version 1.0.0
 * @since 1.0.0
 */
@Environment(EnvType.CLIENT)
public record UnbakedForwardingModel(UnbakedModel baseModel, Function<BakedModel, BakedModel> factory) implements AtbywUnbakedModel {

    @Override
    public Collection<Identifier> getModelDependencies() {
        return this.baseModel.getModelDependencies();
    }

    @Override
    public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter, Set<com.mojang.datafixers.util.Pair<String, String>> unresolvedTextureReferences) {
        return this.baseModel.getTextureDependencies(unbakedModelGetter, unresolvedTextureReferences);
    }

    @Nullable
    @Override
    public BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        return this.factory.apply(this.baseModel.bake(loader, textureGetter, rotationContainer, modelId));
    }
}