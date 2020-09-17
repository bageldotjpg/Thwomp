package com.bagel.thwomp.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.World;

public class ShyGuyEntity extends FoxEntity {

	public ShyGuyEntity(EntityType<? extends FoxEntity> type, World worldIn) {
		super(type, worldIn);
	}

}
