package net.azagwen.atbyw.mixin;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.group.ItemGroupTabWidget;
import net.azagwen.atbyw.group.TabbedItemGroup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    private final List<ItemGroupTabWidget> tabButtons = Lists.newArrayList();
    private ItemGroupTabWidget selectedSubtab;

    @Inject(at = @At("HEAD"), method = "setSelectedTab(Lnet/minecraft/item/ItemGroup;)V")
    private void setSelectedTab(ItemGroup group, CallbackInfo cbi) {
        buttons.removeAll(tabButtons);
        tabButtons.clear();

        if(group instanceof TabbedItemGroup) {
            TabbedItemGroup tabbedGroup = (TabbedItemGroup) group;
            if(!tabbedGroup.hasInitialized()) tabbedGroup.initialize();

            for(int i = 0; i < tabbedGroup.getTabs().size(); i++) {
                int selectTab = i;
                ItemGroupTabWidget tabWidget = new ItemGroupTabWidget(x - 29, (y + 12) + (i * 30), tabbedGroup.getTabs().get(i), (button)-> {
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
                this.addButton(tabWidget);
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo cbi) {
        tabButtons.forEach(button -> {
            if(button.isHovered()) {
                renderTooltip(matrixStack, button.getMessage(), mouseX, mouseY);
            }
        });
    }

    public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }
}