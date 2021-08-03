package net.azagwen.atbyw.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.azagwen.atbyw.block.CanvasBlock;
import net.azagwen.atbyw.block.entity.CanvasBlockEntity;
import net.azagwen.atbyw.item.ColorizerItem;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {
    private static final Identifier ICONS_TEXTURE = AtbywMain.id("textures/gui/icons.png");
    @Shadow private final MinecraftClient client;
    @Shadow private int scaledWidth;
    @Shadow private int scaledHeight;

    public InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void renderCrosshair(MatrixStack matrices, CallbackInfo ci) {
        var gameOptions = this.client.options;
        var player = this.client.player;
        var world = this.client.world;
        var stack = player.getMainHandStack();
        var hitResult = this.client.crosshairTarget;

        if (gameOptions.getPerspective().isFirstPerson()) {
            if (this.client.interactionManager.getCurrentGameMode() != GameMode.SPECTATOR) {
                if (stack.getItem() instanceof ColorizerItem colorizerItem && hitResult.getType() == HitResult.Type.BLOCK) {
                    var pos = ((BlockHitResult) hitResult).getBlockPos();
                    var block = world.getBlockState(pos).getBlock();

                    if (block instanceof CanvasBlock) {
                        var entity = (CanvasBlockEntity) world.getBlockEntity(pos);
                        var color = new Color(colorizerItem.getColor(stack));

                        if (entity.getColor() != color.getRGB() && colorizerItem.hasColor(stack)) {
                            RenderSystem.setShaderTexture(0, ICONS_TEXTURE);
                            RenderSystem.defaultBlendFunc();
                            this.setShaderColor(color);
                            this.drawTexture(matrices, (this.scaledWidth - 15) / 2, (this.scaledHeight - 15) / 2, 0, 0, 15, 15);
                            ci.cancel();
                        }
                    }
                    this.setShaderColor(Color.WHITE);
                }
            }
        }
    }

    private float channelToFloat(int channel) {
        return ((float) channel) / 255;
    }

    private void setShaderColor(Color color) {
        var red = channelToFloat(color.getRed());
        var green = channelToFloat(color.getGreen());
        var blue = channelToFloat(color.getBlue());
        var alpha = channelToFloat(color.getAlpha());
        RenderSystem.setShaderColor(red, green, blue, alpha);
    }
}
