package net.azagwen.atbyw.mixin.sign.client;

import net.azagwen.atbyw.block.sign.AtbywSign;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(SignEditScreen.class)
public class SignEditScreenMixin {
    @Shadow @Final private SignBlockEntity sign;

    @ModifyVariable(method = "render", at =
    @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/render/TexturedRenderLayers;getSignTextureId(Lnet/minecraft/util/SignType;)Lnet/minecraft/client/util/SpriteIdentifier;"))
    private SpriteIdentifier getSignTextureId(SpriteIdentifier spriteIdentifier) {
        if (sign.getCachedState().getBlock() instanceof AtbywSign atbywSign) {
            return new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, atbywSign.getType().getTexture());
        }
        return spriteIdentifier;
    }
}
