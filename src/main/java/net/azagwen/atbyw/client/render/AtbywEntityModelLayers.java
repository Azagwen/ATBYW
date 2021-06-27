package net.azagwen.atbyw.client.render;

import net.azagwen.atbyw.main.AtbywIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;

@Environment(EnvType.CLIENT)
public class AtbywEntityModelLayers extends EntityModelLayers {
    public static final EntityModelLayer TIMER_REPEATER = new EntityModelLayer(new AtbywIdentifier("timer_repeater"), "main");
}
