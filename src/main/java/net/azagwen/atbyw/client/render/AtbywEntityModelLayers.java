package net.azagwen.atbyw.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;

import static net.azagwen.atbyw.main.AtbywMain.NewAtbywID;

@Environment(EnvType.CLIENT)
public class AtbywEntityModelLayers extends EntityModelLayers {
    public static final EntityModelLayer TIMER_REPEATER = new EntityModelLayer(NewAtbywID("timer_repeater"), "main");
}
