package net.azagwen.atbyw.group;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import static net.azagwen.atbyw.main.AtbywMain.AtbywID;

// This code was originally taken from https://github.com/Lemonszz/gubbins/blob/master/src/main/java/party/lemons/gubbins/gui/ItemGroupTabWidget.java,
// which is licensed under MIT.
// and edited to match my needs.
public class ItemGroupTabWidget extends ButtonWidget {
    public static final Identifier TEXTURE = AtbywID("textures/gui/side_tabs.png");
    public boolean isSelected = false;
    private final ItemGroupTab tab;

    public ItemGroupTabWidget(int x, int y, ItemGroupTab tab, PressAction onPress) {
        super(x, y, 33, 28, new TranslatableText(tab.getTranslationKey()), onPress);

        this.tab = tab;
    }

    @Override
    protected int getYImage(boolean isHovered) {
        return isSelected ? 2 : (isHovered ? 1 : 0);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        minecraftClient.getTextureManager().bindTexture(TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int yOffset = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.defaultBlendFunc();
        this.drawTexture(matrixStack, this.x, this.y, 0, yOffset * height, this.width, this.height);
        this.renderBg(matrixStack, minecraftClient, mouseX, mouseY);
        minecraftClient.getItemRenderer().renderGuiItemIcon(tab.getIcon(), this.x + (this.isHovered() || isSelected ? 7 : 10), this.y + 6);
    }
}