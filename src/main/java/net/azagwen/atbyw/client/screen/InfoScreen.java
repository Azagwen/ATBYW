package net.azagwen.atbyw.client.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import static net.azagwen.atbyw.main.AtbywMain.NewAtbywID;

public class InfoScreen extends Screen {
    private static final Identifier TEXTURED_WIDGETS_TEXTURE = NewAtbywID("textures/gui/textured_widgets.png");
    private final String curseforgeLink = "https://www.curseforge.com/minecraft/mc-mods/atbyw";
    private final String githubLink = "https://github.com/Azagwen/ATBYW";
    private final Screen parent;
    private final int childOffsetY = 15;

    public InfoScreen(Screen parent) {
        super(new TranslatableText("screen.atbyw.info.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        //Curseforge buttons
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 38, this.height / 2 - childOffsetY, 20, 20, 0, 0, 20, TEXTURED_WIDGETS_TEXTURE, 64, 64, (button) -> {
            this.client.keyboard.setClipboard(curseforgeLink);
        }));
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 60, this.height / 2 - childOffsetY, 20, 20, 20, 0, 20, TEXTURED_WIDGETS_TEXTURE, 64, 64, (button) -> {
            Util.getOperatingSystem().open(curseforgeLink);
        }));

        //Github buttons
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 38, this.height / 2 + childOffsetY, 20, 20, 0, 0, 20, TEXTURED_WIDGETS_TEXTURE, 64, 64, (button) -> {
            this.client.keyboard.setClipboard(githubLink);
        }));
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 60, this.height / 2 + childOffsetY, 20, 20, 20, 0, 20, TEXTURED_WIDGETS_TEXTURE, 64, 64, (button) -> {
            Util.getOperatingSystem().open(githubLink);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        Text curseforgeTxt = new TranslatableText("screen.atbyw.info.curseforgeTxt");
        Text githubTxt = new TranslatableText("screen.atbyw.info.githubTxt");

        textRenderer.drawWithShadow(matrices, curseforgeTxt, this.width / 2 - 80, this.height / 2 - childOffsetY + 6, 16777215);
        textRenderer.drawWithShadow(matrices, githubTxt, this.width / 2 - 80, this.height / 2 + childOffsetY + 6, 16777215);

        super.render(matrices, mouseX, mouseY, delta);
    }
}
