package com.bagel.thwomp.common.entity;

import com.bagel.thwomp.core.registry.ThwompEntities;
import com.bagel.thwomp.core.registry.ThwompItems;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ShyGuyEntity extends FoxEntity {

	public ShyGuyEntity(EntityType<? extends FoxEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(ThwompItems.SHY_GUY_SPAWN_EGG.get());
	}
	
	@Override
    public ShyGuyEntity createChild(AgeableEntity ageable) {
        return ThwompEntities.SHY_GUY.get().create(this.world);
    }

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
