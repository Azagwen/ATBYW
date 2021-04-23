package net.azagwen.atbyw.main;

import net.azagwen.atbyw.blocks.AtbywBlockEntities;
import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.datagen.AtbywRecipes;
import net.azagwen.atbyw.datagen.arrp.AtbywRRP;
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
	public static final String mcNameSpace = "minecraft";
	public static final String nameSpace = "atbyw";
	public static final String modInteractionNameSpace = "atbyw_mi";
	public static final Logger LOGGER  = LogManager.getLogger();

	public static Identifier AtbywID(String path) {
		return new Identifier(nameSpace, path);
	}
	public static Identifier AtbywModInteractionID(String path) {
		return new Identifier(modInteractionNameSpace, path);
	}

	public static boolean isModLoaded(String ModID) {
		return FabricLoader.getInstance().isModLoaded(ModID);
	}

	public static final ItemGroup ATBYW_BLOCKS = FabricItemGroupBuilder.create(AtbywID("blocks")).icon(() -> {
		return new ItemStack(AtbywBlocks.TERRACOTTA_BRICKS);
	}).build();

	public static final ItemGroup ATBYW_DECO = FabricItemGroupBuilder.create(AtbywID("decoration")).icon(() -> {
		return new ItemStack(AtbywBlocks.CYAN_CINDER_BLOCKS_WALL);
	}).build();

	public static final ItemGroup ATBYW_REDSTONE = FabricItemGroupBuilder.create(AtbywID("redstone")).icon(() -> {
		return new ItemStack(AtbywBlocks.REDSTONE_LANTERN);
	}).build();

	public static final ItemGroup ATBYW_MISC = FabricItemGroupBuilder.create(AtbywID("items")).icon(() -> {
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
				return ResourceManagerHelper.registerBuiltinResourcePack(AtbywModInteractionID("mod_interaction_resources"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED);
			}).filter(success -> !success).ifPresent(success -> LOGGER.error("Unable to Load \"atbyw_mi/mod_interaction_resources\"."));

			AtbywRRP.init_mi();
		}

		AtbywItems.init();
		AtbywBlocks.init();
		AtbywBlockEntities.init();
		AtbywRecipes.init();
		AtbywRRP.init();

		LOGGER.info("ATBYW Inintiliazed");
	}
}
