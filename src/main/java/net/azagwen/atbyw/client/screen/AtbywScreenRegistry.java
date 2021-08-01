package net.azagwen.atbyw.client.screen;

import net.azagwen.atbyw.main.AtbywScreenHandlerType;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class AtbywScreenRegistry {

    public static void init() {
        ScreenRegistry.register(AtbywScreenHandlerType.TINTING_TABLE_SCREEN_HANDLER, TintingTableScreen::new);
    }
}
