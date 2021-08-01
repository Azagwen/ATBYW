package net.azagwen.atbyw.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DynamicTexturedButtonWidget extends ButtonWidget {
    private final Identifier texture;
    private final int hoveredVOffset;
    private final int textureWidth;
    private final int textureHeight;
    private int u;
    private int v;

    public DynamicTexturedButtonWidget(int x, int y, int width, int height, int u, int v, Identifier texture, PressAction pressAction) {
        this(x, y, width, height, u, v, height, texture, 256, 256, pressAction);
    }

    public DynamicTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, PressAction pressAction) {
        this(x, y, width, height, u, v, hoveredVOffset, texture, 256, 256, pressAction);
    }

    public DynamicTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, PressAction pressAction) {
        this(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, LiteralText.EMPTY);
    }

    public DynamicTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, PressAction pressAction, Text text) {
        this(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, EMPTY, text);
    }

    public DynamicTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, PressAction pressAction, TooltipSupplier tooltipSupplier, Text text) {
        super(x, y, width, height, text, pressAction, tooltipSupplier);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.u = u;
        this.v = v;
        this.hoveredVOffset = hoveredVOffset;
        this.texture = texture;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setU(int u) {
        this.u = u;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, this.texture);
        int i = this.v;
        if (this.isHovered()) {
            i += this.hoveredVOffset;
        }

        RenderSystem.enableDepthTest();
        drawTexture(matrices, this.x, this.y, (float)this.u, (float)i, this.width, this.height, this.textureWidth, this.textureHeight);
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }
}
