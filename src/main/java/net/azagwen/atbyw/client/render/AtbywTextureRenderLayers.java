package net.azagwen.atbyw.client.render;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.main.AtbywIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class AtbywTextureRenderLayers {
    public static final Identifier DIGIT_ATLAS_TEXTURE = new AtbywIdentifier("textures/atlas/timer_repeater_digits.png");
    public static final Identifier DIGIT_ATLAS_TEXTURE_ON = new AtbywIdentifier("textures/atlas/timer_repeater_digits_on.png");
    public static final SpriteIdentifier DIGIT_TEXTURE = new SpriteIdentifier(DIGIT_ATLAS_TEXTURE, new AtbywIdentifier("entity/timer_repeater/timer_repeater_digits"));
    public static final SpriteIdentifier DIGIT_TEXTURE_ON = new SpriteIdentifier(DIGIT_ATLAS_TEXTURE_ON, new AtbywIdentifier("entity/timer_repeater/timer_repeater_digits_on"));

    public static ArrayList<SpriteIdentifier> addAtbywTextures = Lists.newArrayList(
            DIGIT_TEXTURE,
            DIGIT_TEXTURE_ON
    );
}
