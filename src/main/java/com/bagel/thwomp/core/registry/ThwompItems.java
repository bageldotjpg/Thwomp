package com.bagel.thwomp.core.registry;

import com.bagel.thwomp.core.Thwomp;
import com.bagel.thwomp.core.util.RegistryUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThwompItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Thwomp.MODID);
	
	public static RegistryObject<Item> COIN = RegistryUtils.createItem("coin", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
}
