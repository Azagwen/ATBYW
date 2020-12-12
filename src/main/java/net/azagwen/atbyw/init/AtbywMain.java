package net.azagwen.atbyw.init;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AtbywMain implements ModInitializer {
	public static final String nameSpace = "atbyw";

	public static Identifier newID(String path) {
		return new Identifier(nameSpace, path);
	}

	public static final ItemGroup ATBYW_BLOCKS = FabricItemGroupBuilder.create(newID("blocks")).icon(() ->
			new ItemStack(AbtywBlocks.TERRACOTTA_BRICKS)).build();

	@Override
	public void onInitialize() {
		AbtywBlocks.init();

		System.out.println("Hello Fabric world! owo");
	}
}
