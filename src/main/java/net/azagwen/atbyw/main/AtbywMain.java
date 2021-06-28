package net.azagwen.atbyw.main;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityType;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.datagen.RecipeRegistry;
import net.azagwen.atbyw.datagen.arrp.AtbywRRP;
import net.azagwen.atbyw.dev_tools.AutoModelWriter;
import net.azagwen.atbyw.group.AtbywItemGroup;
import net.azagwen.atbyw.item.AtbywItems;
import net.azagwen.atbyw.world.AtbywWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AtbywMain implements ModInitializer {
	public static final String mcNameSpace = "minecraft";
	public static final String AtbywNamespace = "atbyw";
	public static final String modInteractionNameSpace = "atbyw_mi";
	public static final Logger LOGGER  = LogManager.getLogger("Atbyw Main");
	public static final Logger MYS_LOGGER  = LogManager.getLogger("?");

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

	public static ItemGroup ATBYW_BLOCKS; 		//Unused, kept for testing.
	public static ItemGroup ATBYW_DECO; 		//Unused, kept for testing.
	public static ItemGroup ATBYW_REDSTONE; 	//Unused, kept for testing.
	public static ItemGroup ATBYW_MISC; 		//Unused, kept for testing.

	public static boolean enable_mod_interactions() {
		boolean a = isModLoaded("betternether");
		boolean b = isModLoaded("betterend");

		return a || b;
	}

	public static List<BlockState> BLOCK_STATES;
	public static int X_SIDE_LENGTH;
	public static int Z_SIDE_LENGTH;

	public static boolean isDebugEnabled() {
		var client = MinecraftClient.getInstance();
		var file = new File(client.runDirectory, "config/atbyw.json");
		try {
			JsonReader reader = new JsonReader(new FileReader(file));
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(reader).getAsJsonObject();

			if (json.has("enable_debug")) {
				return JsonHelper.getBoolean(json, "enable_debug");
			}
		} catch (FileNotFoundException ignored) {
		}

		return false;
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
		RecipeRegistry.init();
		AtbywWorldGen.init();
		AtbywRRP.init();

		if (isDebugEnabled()) {
			new AutoModelWriter().writeAll();

			BLOCK_STATES = StreamSupport.stream(Registry.BLOCK.spliterator(), false).flatMap((block) -> {
				return block.getStateManager().getStates().stream();
			}).collect(Collectors.toList());

			X_SIDE_LENGTH = MathHelper.ceil(MathHelper.sqrt((float)BLOCK_STATES.size()));
			Z_SIDE_LENGTH = MathHelper.ceil((float)BLOCK_STATES.size() / (float)X_SIDE_LENGTH);
		} else {
			MYS_LOGGER.info("As expected :)");
		}

		ATBYW_GROUP = new AtbywItemGroup(new AtbywIdentifier("atbyw"));

		LOGGER.info("ATBYW Inintiliazed");
	}
}
