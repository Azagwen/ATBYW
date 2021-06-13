package net.azagwen.atbyw.datagen.arrp;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;

import static net.azagwen.atbyw.main.AtbywMain.*;

public class AtbywRRP {
    public static final RuntimeResourcePack ATBYW_RESOURCE_PACK = RuntimeResourcePack.create(NewAtbywID("atbyw_rrp").toString());
    public static final RuntimeResourcePack ATBYW_MI_RESOURCE_PACK = RuntimeResourcePack.create(NewAtbywModInteractionID("atbyw_rrp").toString());

    public static void init() {
        AtbywLootTables.init();
        AtbywDatagenTags.init();
        RRPCallback.EVENT.register(a -> a.add(ATBYW_RESOURCE_PACK));
        LOGGER.info("ATBYW RRP Inintiliazed");
    }
    public static void init_mi() {
        RRPCallback.EVENT.register(a -> a.add(ATBYW_MI_RESOURCE_PACK));
        LOGGER.info("ATBYW Mod Interaction RRP Inintiliazed");
    }
}
