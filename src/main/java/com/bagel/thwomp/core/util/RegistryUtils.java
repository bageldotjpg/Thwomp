package com.bagel.thwomp.core.util;

import com.bagel.thwomp.core.registry.ThwompBlocks;
import com.bagel.thwomp.core.registry.ThwompItems;
import com.google.common.base.Supplier;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;

public class RegistryUtils {
	
	public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
		RegistryObject<I> item = ThwompItems.ITEMS.register(name, supplier);
		return item;
	}
	
//	public static RegistryObject<Item> createSpawnEggItem(String entityName, Supplier<EntityType<?>> supplier, int primaryColor, int secondaryColor) {
//		RegistryObject<Item> spawnEgg = ThwompItems.ITEMS.register(entityName + "_spawn_egg", () -> new ThwompSpawnEggItem(supplier, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC)));
//		ThwompItems.SPAWN_EGGS.add(spawnEgg);
//		return spawnEgg;
//	}
	
	public static BlockItem createSimpleItemBlock(Block block, ItemGroup itemGroup) {
        return (BlockItem) new BlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
    }
	
	public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        RegistryObject<B> block = ThwompBlocks.BLOCKS.register(name, supplier);
        ThwompItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));	
        return block;
    }
	
	public static <B extends Block> RegistryObject<B> createBlockCompat(String mod, String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
		ItemGroup determinedGroup = ModList.get().isLoaded(mod) || mod == "indev" ? itemGroup : null;
		RegistryObject<B> block = ThwompBlocks.BLOCKS.register(name, supplier);
		ThwompItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(determinedGroup)));
		return block;		
    }

    public static <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = ThwompBlocks.BLOCKS.register(name, supplier);
        return block;
    }
}
