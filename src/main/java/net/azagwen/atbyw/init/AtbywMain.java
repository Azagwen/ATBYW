package net.azagwen.atbyw.init;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.items.AtbywItems;
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
			new ItemStack(AtbywBlocks.TERRACOTTA_BRICKS)).build();

	public static final ItemGroup ATBYW_REDSTONE = FabricItemGroupBuilder.create(newID("redstone")).icon(() ->
			new ItemStack(AtbywBlocks.ACACIA_BOOKSHELF_TOGGLE)).build();

	public static final ItemGroup ATBYW_MISC = FabricItemGroupBuilder.create(newID("items")).icon(() ->
			new ItemStack(AtbywItems.ACACIA_STICK)).build();


	@Override
	public void onInitialize() {
		AtbywBlocks.init();
		AtbywItems.init();

		System.out.println("ATBYW Inintiliazed 😳");
	}
}
