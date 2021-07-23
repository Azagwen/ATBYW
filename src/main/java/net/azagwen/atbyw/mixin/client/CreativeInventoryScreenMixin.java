package net.azagwen.atbyw.mixin.client;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.group.AtbywItemGroup;
import net.azagwen.atbyw.group.ItemGroupTabWidget;
import net.azagwen.atbyw.group.TabbedItemGroup;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    private static final Identifier MEDIA_ICON_TEXTURE = AtbywMain.id("textures/gui/info_button.png");
    private final String curseforgeLink = "https://www.curseforge.com/minecraft/mc-mods/atbyw";
    private final String githubLink = "https://github.com/Azagwen/ATBYW";
    private final List<TexturedButtonWidget> mediaButtons = Lists.newArrayList();
    private final List<ItemGroupTabWidget> tabButtons = Lists.newArrayList();
    private ItemGroupTabWidget selectedSubtab;

    @Inject(at = @At("HEAD"), method = "setSelectedTab(Lnet/minecraft/item/ItemGroup;)V")
    private void setSelectedTab(ItemGroup group, CallbackInfo cbi) {
        for (var button : tabButtons) {
            this.remove(button);
        }
        for (var button : mediaButtons) {
            this.remove(button);
        }
        tabButtons.clear();
        mediaButtons.clear();

        if(group instanceof TabbedItemGroup tabbedGroup) {
            if(!tabbedGroup.hasInitialized()) {
                tabbedGroup.initialize();
            }

            int i = 0;
            for(var tab : tabbedGroup.getTabs()) {
                var selectTab = i;
                var flipTab = i > 3;
                var xOffset = flipTab ? (this.x + 191) : (this.x - 29);
                var yOffset = flipTab ? (this.y + 12) + ((i - 4) * 30) : (this.y + 12) + (i * 30);
                var tabWidget = new ItemGroupTabWidget(xOffset, yOffset, flipTab, tab, (button)-> {
                    tabbedGroup.setSelectedTab(selectTab);
                    MinecraftClient.getInstance().openScreen(this);
                    ((ItemGroupTabWidget) button).isSelected = true;
                    selectedSubtab = (ItemGroupTabWidget) button;
                });

                if(i == tabbedGroup.getSelectedTabIndex()) {
                    selectedSubtab = tabWidget;
                    tabWidget.isSelected = true;
                }

                tabButtons.add(tabWidget);
                this.addDrawableChild(tabWidget);
                i++;
            }
        }

        if(group instanceof AtbywItemGroup) {
            var curseforgeButton = new TexturedButtonWidget(this.x + 175, this.y + 4, 12, 12, 24, 0, 12, MEDIA_ICON_TEXTURE, 64, 64, (button) -> {
                this.client.openScreen(new ConfirmChatLinkScreen((opened) -> {
                    if (opened) {
                        Util.getOperatingSystem().open(curseforgeLink);
                    }

                    this.client.openScreen(this);
                }, curseforgeLink, true));
            }, new TranslatableText("itemGroup." + AtbywMain.ATBYW + ".curseforgeLink"));
            var githubButton = new TexturedButtonWidget(this.x + 161, this.y + 4, 12, 12, 12, 0, 12, MEDIA_ICON_TEXTURE, 64, 64, (button) -> {
                this.client.openScreen(new ConfirmChatLinkScreen((opened) -> {
                    if (opened) {
                        Util.getOperatingSystem().open(githubLink);
                    }

                    this.client.openScreen(this);
                }, githubLink, true));
            }, new TranslatableText("itemGroup." + AtbywMain.ATBYW + ".githubLink"));
            mediaButtons.add(curseforgeButton);
            mediaButtons.add(githubButton);
            this.addDrawableChild(curseforgeButton);
            this.addDrawableChild(githubButton);
        }
    }

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo cbi) {
        tabButtons.forEach(button -> {
            if(button.isHovered()) {
                renderTooltip(matrixStack, button.getMessage(), mouseX, mouseY);
            }
        });
        mediaButtons.forEach(button -> {
            if(button.isHovered()) {
                renderTooltip(matrixStack, button.getMessage(), mouseX, mouseY);
            }
        });
    }

    public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }
}