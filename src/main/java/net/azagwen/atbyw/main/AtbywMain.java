package net.azagwen.atbyw.main;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.azagwen.atbyw.block.entity.AtbywBlockEntityTypes;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.client.render.model.ConnectionType;
import net.azagwen.atbyw.datagen.recipe.registry.RecipeRegistry;
import net.azagwen.atbyw.datagen.arrp.AtbywRRP;
import net.azagwen.atbyw.dev_tools.AutoJsonWriter;
import net.azagwen.atbyw.group.AtbywItemGroup;
import net.azagwen.atbyw.item.AtbywItems;
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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AtbywMain implements ModInitializer {
	public static final String MINECRAFT = "minecraft";
	public static final String ATBYW = "atbyw";
	public static final String ATBYW_MI = "atbyw_mi";
	public static final Logger LOGGER = LogManager.getLogger("Atbyw Main");
	public static final Logger MYS_LOGGER = LogManager.getLogger("?");
	public static final Map<String, Boolean> DEBUGGER_FEATURES = Maps.newHashMap();

	//TODO: Fix and Investigate structure issues (very high priority)
	//TODO: Add smooth variants of Deepslathe, Granite, Diorite, Andesite, Tuff...
	//TODO: Add Amethyst bricks
	//TODO: Add Amethyst Walls/Fences
	//TODO: Add Cactus Planks & assorted stuff
	//TODO: Add Redstone pipes & components assorted to it
	//TODO: Add Stone melter furnace
	//TODO: Move LootTables away from ARRP JSON (low priority)

	public static Identifier id(String path) {
		return new Identifier(ATBYW, path);
	}

	public static Identifier miId(String path) {
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

	public static boolean enableModInteractions() {
		boolean a = isModLoaded("betternether");
		boolean b = isModLoaded("betterend");

		return a || b;
	}

	public static List<BlockState> BLOCK_STATES;
	public static int X_SIDE_LENGTH;
	public static int Z_SIDE_LENGTH;

	private void checkForDebugElement(JsonElement element, String name) {
		if (element.getAsString().equals(name)) {
			DEBUGGER_FEATURES.put(name, true);
		}
	}

	public void tryEnableDebug() {
		try {
			var client = MinecraftClient.getInstance();
			var file = new File(client.runDirectory, "config/atbyw.json");
			try {
				var reader = new JsonReader(new FileReader(file));
				var parser = new JsonParser();
				var json = parser.parse(reader).getAsJsonObject();

				if (json.has("debug")) {
					DEBUGGER_FEATURES.clear();
					var debugObj = JsonHelper.getArray(json, "debug");

					for (var element : debugObj) {
						this.checkForDebugElement(element, "redstone_cross");
						this.checkForDebugElement(element, "shroomstick");
						this.checkForDebugElement(element, "debug_world");
					}
				}
			} catch (FileNotFoundException ignored) {
			}
		} catch (RuntimeException ignored) {
		}
	}

	public static boolean checkDebugEnabled(String key) {
		return DEBUGGER_FEATURES.containsKey(key) && DEBUGGER_FEATURES.get(key);
	}

	@Override
	public void onInitialize() {
		this.tryEnableDebug();

		if (enableModInteractions()) {
			FabricLoader.getInstance().getModContainer(ATBYW).map(modContainer -> {
				return ResourceManagerHelper.registerBuiltinResourcePack(miId("mod_interaction_resources"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED);
			}).filter(success -> !success).ifPresent(success -> LOGGER.error("Unable to Load \"atbyw_mi/mod_interaction_resources\"."));

			AtbywRRP.init_mi();
		}

		AtbywItems.init();
		AtbywBlocks.init();
		AtbywRRP.init();
		AtbywStats.init();
		AtbywBlockEntityTypes.init();
		AtbywNetworking.init();
		RecipeRegistry.init();

		//Populate debug world with this mod's blocks (dev only)
		if (checkDebugEnabled("debug_world")) {
			new AutoJsonWriter().writeAll();

			BLOCK_STATES = StreamSupport.stream(Registry.BLOCK.spliterator(), false).flatMap((block) -> {
				return block.getStateManager().getStates().stream();
			}).collect(Collectors.toList());

			X_SIDE_LENGTH = MathHelper.ceil(MathHelper.sqrt((float)BLOCK_STATES.size()));
			Z_SIDE_LENGTH = MathHelper.ceil((float)BLOCK_STATES.size() / (float)X_SIDE_LENGTH);

			MYS_LOGGER.info("ATBYW Debug features enabled.");
		} else {
			MYS_LOGGER.info("[...]");
		}

		ATBYW_GROUP = new AtbywItemGroup(AtbywMain.id("atbyw"));

		LOGGER.info("ATBYW Inintiliazed");
	}
}
