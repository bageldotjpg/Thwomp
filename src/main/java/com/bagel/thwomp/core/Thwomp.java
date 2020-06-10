package com.bagel.thwomp.core;

import com.bagel.thwomp.core.registry.ThwompBlocks;
import com.bagel.thwomp.core.registry.ThwompItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Thwomp.MODID)
@EventBusSubscriber(modid = Thwomp.MODID)
public class Thwomp
{
	public static final String MODID = "thwomp";
    
    public Thwomp() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	ThwompItems.ITEMS.register(modEventBus);
    	ThwompBlocks.BLOCKS.register(modEventBus);


        modEventBus.addListener(this::setup);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
		});
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
	}
    
    private void setup(final FMLCommonSetupEvent event) {
    }

    
}
