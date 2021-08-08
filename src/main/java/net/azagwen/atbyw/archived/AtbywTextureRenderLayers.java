package net.azagwen.atbyw.archived;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.main.AtbywMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class AtbywTextureRenderLayers {
    public static ArrayList<SpriteIdentifier> ATBYW_TEXTURES = Lists.newArrayList();
}
