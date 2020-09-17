package com.bagel.thwomp.core.registry;

import com.bagel.thwomp.client.render.ShyGuyRenderer;
import com.bagel.thwomp.common.entity.ShyGuyEntity;
import com.bagel.thwomp.core.Thwomp;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThwompEntities {
	public static final RegistryHelper HELPER = Thwomp.REGISTRY_HELPER;

	public static final RegistryObject<EntityType<ShyGuyEntity>> SHY_GUY = HELPER.createLivingEntity("shy_guy", ShyGuyEntity::new, EntityClassification.CREATURE, 0.6F, 0.6F);

	public static void registerRendering() {
		RenderingRegistry.registerEntityRenderingHandler(SHY_GUY.get(), ShyGuyRenderer::new);
	}
	
	public static void registerAttributes() {
		GlobalEntityTypeAttributes.put(SHY_GUY.get(), FoxEntity.func_234192_eI_().create());
	}
}
