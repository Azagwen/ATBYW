package net.azagwen.atbyw.main;

import com.google.common.collect.Lists;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityType;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.datagen.AtbywRecipes;
import net.azagwen.atbyw.datagen.arrp.AtbywRRP;
import net.azagwen.atbyw.group.AtbywItemGroup;
import net.azagwen.atbyw.items.AtbywItems;
import net.azagwen.atbyw.world.AtbywWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class AtbywMain implements ModInitializer {
	public static final String mcNameSpace = "minecraft";
	public static final String AtbywNamespace = "atbyw";
	public static final String modInteractionNameSpace = "atbyw_mi";
	public static final Logger LOGGER  = LogManager.getLogger("Atbyw Main");

	public static Identifier NewAtbywID(String path) {
		return new Identifier(AtbywNamespace, path);
	}

	public static Identifier NewAtbywModInteractionID(String path) {
		return new Identifier(modInteractionNameSpace, path);
	}

	public static boolean isModLoaded(String ModID) {
		return FabricLoader.getInstance().isModLoaded(ModID);
	}

	public static ItemGroup ATBYW_GROUP;
	public static ArrayList<Item> BLOCKS_TAB = Lists.newArrayList(); 		//used in (net.azagwen.atbyw.datagen.arrp.AtbywDatagenTags)
	public static ArrayList<Item> DECO_TAB = Lists.newArrayList(); 			//used in (net.azagwen.atbyw.datagen.arrp.AtbywDatagenTags)
	public static ArrayList<Item> REDSTONE_TAB = Lists.newArrayList(); 		//used in (net.azagwen.atbyw.datagen.arrp.AtbywDatagenTags)
	public static ArrayList<Item> MISC_TAB = Lists.newArrayList(); 			//used in (net.azagwen.atbyw.datagen.arrp.AtbywDatagenTags)
	public static ArrayList<Item> GEN_BLOCKS_TAB = Lists.newArrayList(); 	//used in (net.azagwen.atbyw.datagen.arrp.AtbywDatagenTags)

	public static ItemGroup ATBYW_BLOCKS; 		//Unused, kept for testing.
	public static ItemGroup ATBYW_DECO; 		//Unused, kept for testing.
	public static ItemGroup ATBYW_REDSTONE; 	//Unused, kept for testing.
	public static ItemGroup ATBYW_MISC; 		//Unused, kept for testing.

	public static boolean enable_mod_interactions() {
		boolean a = isModLoaded("betternether");
		boolean b = isModLoaded("betterend");

		return a || b;
	}

	@Override
	public void onInitialize() {

		if (enable_mod_interactions()) {
			FabricLoader.getInstance().getModContainer(AtbywNamespace).map(modContainer -> {
				return ResourceManagerHelper.registerBuiltinResourcePack(NewAtbywModInteractionID("mod_interaction_resources"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED);
			}).filter(success -> !success).ifPresent(success -> LOGGER.error("Unable to Load \"atbyw_mi/mod_interaction_resources\"."));

			AtbywRRP.init_mi();
		}

		AtbywItems.init();
		AtbywBlocks.init();
		AtbywBlockEntityType.init();
		AtbywRecipes.init();
		AtbywWorldGen.init();
		AtbywRRP.init();

		ATBYW_GROUP = new AtbywItemGroup(NewAtbywID("atbyw"));

		LOGGER.info("ATBYW Inintiliazed");
	}
}
