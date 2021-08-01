package net.azagwen.atbyw.main;

import net.azagwen.atbyw.screen.TintingTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class AtbywScreenHandlerType {
    public static final ScreenHandlerType<TintingTableScreenHandler> TINTING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(AtbywMain.id("tinting_table"), TintingTableScreenHandler::new);
}
