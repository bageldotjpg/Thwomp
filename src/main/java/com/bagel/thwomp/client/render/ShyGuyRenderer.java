package com.bagel.thwomp.client.render;

import com.bagel.thwomp.client.model.ShyGuyModel;
import com.bagel.thwomp.common.entity.ShyGuyEntity;
import com.bagel.thwomp.core.Thwomp;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShyGuyRenderer extends MobRenderer<ShyGuyEntity, ShyGuyModel<ShyGuyEntity>> {

	public ShyGuyRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ShyGuyModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(ShyGuyEntity entity) {
		return new ResourceLocation(Thwomp.MODID, "textures/entity/shy_guy.png");
	}

	@Override
	protected void preRenderCallback(ShyGuyEntity entity, MatrixStack matrixStack, float partialTickTime) {
		if (entity.isChild())
			matrixStack.scale(0.5F, 0.5F, 0.5F);
	}
}
