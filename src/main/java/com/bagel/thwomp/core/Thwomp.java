package com.bagel.thwomp.core;

import com.bagel.thwomp.core.registry.ThwompEntities;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Thwomp.MODID)
@EventBusSubscriber(modid = Thwomp.MODID)
@SuppressWarnings("deprecation")
public class Thwomp {
	public static final String MODID = "thwomp";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public Thwomp() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredEntityRegister().register(modEventBus);

		modEventBus.addListener(this::setup);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
		});
	}

	private void setup(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			ThwompEntities.registerAttributes();
		});
	}

	private void setupClient(final FMLClientSetupEvent event) {
		ThwompEntities.registerRendering();
	}

	@OnlyIn(Dist.CLIENT)
    private void registerItemColors(ColorHandlerEvent.Item event)
    {
        REGISTRY_HELPER.processSpawnEggColors(event);
    }
}
