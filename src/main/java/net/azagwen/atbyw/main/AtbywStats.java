package net.azagwen.atbyw.main;

import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AtbywStats extends net.minecraft.stat.Stats {
    public static final Identifier COLOR_CANVAS_BLOCK = AtbywMain.id("color_canvas_block");
    public static final Identifier INTERACT_WITH_TINTING_TABLE = AtbywMain.id("interact_with_tinting_table");

    private static void registerStat(Identifier identifier, StatFormatter formatter) {
        Registry.register(Registry.CUSTOM_STAT, identifier.getPath(), identifier);
        CUSTOM.getOrCreateStat(identifier, formatter);
    }

    public static void init() {
        registerStat(COLOR_CANVAS_BLOCK, StatFormatter.DEFAULT);
        registerStat(INTERACT_WITH_TINTING_TABLE, StatFormatter.DEFAULT);
    }
}
