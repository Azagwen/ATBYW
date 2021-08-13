package net.azagwen.atbyw.client.render.model;

import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.Direction;

public record Face(int u1, int v1, int u2, int v2, Sprite sprite, boolean emissive, Direction cullFace) {
}
