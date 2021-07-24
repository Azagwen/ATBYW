package net.azagwen.atbyw.client.render.model;

import net.minecraft.client.texture.Sprite;

public record Face(int u1, int v1, int u2, int v2, Sprite sprite, boolean emissive, int uMultiplier, int vMultiplier) {
}
