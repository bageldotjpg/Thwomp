package com.bagel.thwomp.client.model;

import com.bagel.thwomp.common.entity.ShyGuyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ShyGuyModel<E extends ShyGuyEntity> extends EntityModel<E> {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	private final ModelRenderer waist;
	private final ModelRenderer mask;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer hood_thing;

	public ShyGuyModel() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 0.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 22.0F, 0.0F);
		root.addChild(body);
		body.setTextureOffset(0, 0).addBox(-5.5F, -14.0F, -5.5F, 11.0F, 12.0F, 11.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(3.0F, -2.0F, 3.0F);
		body.addChild(leftLeg);
		leftLeg.setTextureOffset(0, 23).addBox(-1.5F, 0.0F, -6.0F, 3.0F, 4.0F, 6.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(3.0F, -2.0F, 3.0F);
		body.addChild(rightLeg);
		rightLeg.setTextureOffset(0, 23).addBox(-7.5F, 0.0F, -6.0F, 3.0F, 4.0F, 6.0F, 0.0F, false);

		waist = new ModelRenderer(this);
		waist.setRotationPoint(0.0F, -2.0F, 0.0F);
		body.addChild(waist);
		waist.setTextureOffset(0, 33).addBox(-5.5F, 0.0F, -5.5F, 11.0F, 2.0F, 11.0F, 0.0F, false);

		mask = new ModelRenderer(this);
		mask.setRotationPoint(0.0F, -8.0F, -6.2F);
		body.addChild(mask);
		mask.setTextureOffset(44, 12).addBox(-4.5F, -4.5F, 0.0F, 9.0F, 9.0F, 0.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-3.5F, -3.0F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 0.0F, 0.5236F);
		rightArm.setTextureOffset(18, 25).addBox(-3.0F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(3.0F, -3.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, -0.5236F);
		leftArm.setTextureOffset(18, 25).addBox(0.5F, -1.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		hood_thing = new ModelRenderer(this);
		hood_thing.setRotationPoint(0.0F, -14.0F, 5.5F);
		body.addChild(hood_thing);
		setRotationAngle(hood_thing, -0.5463F, 0.0F, 0.0F);
		hood_thing.setTextureOffset(30, 27).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ShyGuyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
		this.rightLeg.rotateAngleY = 0.0F;
		this.leftLeg.rotateAngleY = 0.0F;
		this.rightLeg.rotateAngleZ = 0.0F;
		this.leftLeg.rotateAngleZ = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}