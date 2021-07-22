package net.azagwen.atbyw.client.render.model;

import net.minecraft.client.render.model.BakedModel;

public abstract class TypedUnbakedModel<M extends BakedModel> {
    public final M model;

    public TypedUnbakedModel(M model) {
        this.model = model;
    }
}
