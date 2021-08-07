package net.azagwen.atbyw.main;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityTypes;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.datagen.recipe.registry.RecipeRegistry;
import net.azagwen.atbyw.datagen.arrp.AtbywRRP;
import net.azagwen.atbyw.dev_tools.AutoJsonWriter;
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
	public static final String MINECRAFT = "minecraft";
	public static final String ATBYW = "atbyw";
	public static final String ATBYW_MI = "atbyw_mi";
	public static final Logger LOGGER = LogManager.getLogger("Atbyw Main");
	public static final Logger MYS_LOGGER = LogManager.getLogger("?");

	//TODO: Fix and Investigate structure issues (very high priority)
	//TODO: Add smooth variants of Deepslathe, Gqranite, Diorite, Andesite, Tuff...
	//TODO: Add Amethyst bricks
	//TODO: Add Amethyst Walls/Fences
	//TODO: Add Cactus Planks & assorted stuff
	//TODO: Add Redstone pipes & components assorted to it
	//TODO: Add Stone melter furnace
	//TODO: Move LootTables away from ARRP JSON (low priority)

	public static Identifier id(String path) {
		return new Identifier(ATBYW, path);
	}

	public static Identifier modInteractionId(String path) {
		return new Identifier(ATBYW_MI, path);
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

	public static boolean enableModInteractions() {
		boolean a = isModLoaded("betternether");
		boolean b = isModLoaded("betterend");

		return a || b;
	}

	public static List<BlockState> BLOCK_STATES;
	public static int X_SIDE_LENGTH;
	public static int Z_SIDE_LENGTH;

	public static boolean isDebugEnabled() {
		try {
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
		} catch (RuntimeException ignored) {
		}

		return false;
	}

	@Override
	public void onInitialize() {
		if (enableModInteractions()) {
			FabricLoader.getInstance().getModContainer(ATBYW).map(modContainer -> {
				return ResourceManagerHelper.registerBuiltinResourcePack(modInteractionId("mod_interaction_resources"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED);
			}).filter(success -> !success).ifPresent(success -> LOGGER.error("Unable to Load \"atbyw_mi/mod_interaction_resources\"."));

			AtbywRRP.init_mi();
		}

		AtbywItems.init();
		AtbywBlocks.init();
		AtbywWorldGen.init();
		AtbywRRP.init();
		AtbywStats.init();
		AtbywBlockEntityTypes.init();
		AtbywNetworking.init();
		RecipeRegistry.init();

		//Populate debug world with this mod's blocks (dev only)
		if (isDebugEnabled()) {
			new AutoJsonWriter().writeAll();

			BLOCK_STATES = StreamSupport.stream(Registry.BLOCK.spliterator(), false).flatMap((block) -> {
				return block.getStateManager().getStates().stream();
			}).collect(Collectors.toList());

			X_SIDE_LENGTH = MathHelper.ceil(MathHelper.sqrt((float)BLOCK_STATES.size()));
			Z_SIDE_LENGTH = MathHelper.ceil((float)BLOCK_STATES.size() / (float)X_SIDE_LENGTH);
		} else {
			MYS_LOGGER.info("As expected :)");
		}

		ATBYW_GROUP = new AtbywItemGroup(AtbywMain.id("atbyw"));

		LOGGER.info("ATBYW Inintiliazed");
	}
}
