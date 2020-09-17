package com.bagel.thwomp.core.registry;

import com.bagel.thwomp.core.Thwomp;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThwompItems {
	public static final RegistryHelper HELPER = Thwomp.REGISTRY_HELPER;
	
	public static final RegistryObject<Item> COIN 				= HELPER.createItem("coin", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> SHY_GUY_SPAWN_EGG 	= HELPER.createSpawnEggItem("shy_guy", () -> ThwompEntities.SHY_GUY.get(), 0xA82960, 0xF9E4E4);
}
