package net.azagwen.atbyw.client.render;

import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;

@Environment(EnvType.CLIENT)
public class AtbywEntityModelLayers extends EntityModelLayers {
    public static final EntityModelLayer TIMER_REPEATER = new EntityModelLayer(AtbywMain.id("timer_repeater"), "main");
}
