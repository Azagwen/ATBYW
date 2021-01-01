package net.azagwen.atbyw.main;

import net.azagwen.atbyw.blocks.AtbywBlockEntities;
import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.blocks.AtbywExperimentalBlocks;
import net.azagwen.atbyw.items.AtbywItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtbywMain implements ModInitializer {
	public static final String nameSpace = "atbyw";
	public static final String modInteractionNameSpace = "atbyw_mi";
	public static final Logger LOGGER  = LogManager.getLogger();

	public static Identifier newID(String path) {
		return new Identifier(nameSpace, path);
	}
	public static Identifier newModInteractionID(String path) {
		return new Identifier(modInteractionNameSpace, path);
	}

	public static boolean isModLoaded(String ModID) {
		return FabricLoader.getInstance().isModLoaded(ModID);
	}

	public static final ItemGroup ATBYW_BLOCKS = FabricItemGroupBuilder.create(newID("blocks")).icon(() -> {
		return new ItemStack(AtbywBlocks.TERRACOTTA_BRICKS);
	}).build();

	public static final ItemGroup ATBYW_REDSTONE = FabricItemGroupBuilder.create(newID("redstone")).icon(() -> {
		return new ItemStack(AtbywBlocks.REDSTONE_LANTERN);
	}).build();

	public static final ItemGroup ATBYW_MISC = FabricItemGroupBuilder.create(newID("items")).icon(() -> {
		return new ItemStack(AtbywItems.ACACIA_STICK);
	}).build();

	public static boolean enable_mod_interactions() {
		boolean a = isModLoaded("betternether");
		boolean b = isModLoaded("betterend");

		return a || b;
	}

	@Override
	public void onInitialize() {
		if (enable_mod_interactions()) {
			FabricLoader.getInstance().getModContainer(nameSpace).map(modContainer -> {
				return ResourceManagerHelper.registerBuiltinResourcePack(newModInteractionID("mod_interaction_resources"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED);
			}).filter(success -> !success).ifPresent(success -> LOGGER.error("Unable to Load \"atbyw_mi/mod_interaction_resources\"."));
		}

		AtbywBlocks.init();
		AtbywItems.init();

		LOGGER.info("ATBYW Inintiliazed");
	}
}
