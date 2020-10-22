package com.bagel.thwomp.common.entity;

import com.bagel.thwomp.common.entity.goals.ShyGuyStackLookGoal;
import com.bagel.thwomp.common.entity.goals.ShyGuyStackUpGoal;
import com.bagel.thwomp.core.registry.ThwompItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ShyGuyEntity extends MonsterEntity implements IMob {
	
	private static final DataParameter<Boolean> STACK_WAITING = EntityDataManager.createKey(ShyGuyEntity.class, DataSerializers.BOOLEAN);
	
	public ShyGuyEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(ThwompItems.SHY_GUY_SPAWN_EGG.get());
	}

		
	
    @Override
    protected void registerGoals() {
    	this.goalSelector.addGoal(0, new ShyGuyStackUpGoal(this, 1.5D));
    	this.goalSelector.addGoal(1, new ShyGuyStackLookGoal(this));
    	
    	this.goalSelector.addGoal(2, new ShyGuyEntity.AttackGoal(this));
    	
    	this.goalSelector.addGoal(3, new PanicGoal(this, 1.2D));
    	this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.2D));
    	
    	this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    	this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    	
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }
    
    
    
    @Override
    public void livingTick() {
    	super.livingTick();
    	
    	// TODO:
    	// make shy guy stack members separate if theres a block in the way of the bottommost one .
    	// also make it bob while moving lolol
    }
    
    
    
	// gets amount of shy guys in stack
	public int shyGuyStackAmount() {
		return this.getRecursivePassengers().size();
	}
	
	
	
	// if being honest i dont remember why i made this separate from MeleeAttackGoal, might just remove this later
    static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(ShyGuyEntity shy) {
           super(shy, 1.0D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
        	return (double)(10.0F + attackTarget.getWidth());
        }
    }
    
	
    
    // data setup
    @Override
	protected void registerData() {
		super.registerData();
		this.getDataManager().register(STACK_WAITING, false);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		compound.putBoolean("IsWaitingForStack", this.isWaitingForStack());
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		this.setWaitingForStack(compound.getBoolean("IsWaitingForStack"));
	}
    
	
    
    // data get/set methods
    public boolean isWaitingForStack() {
    	return this.getDataManager().get(STACK_WAITING);
    }
    
    public void setWaitingForStack(boolean waiting) {
    	this.getDataManager().set(STACK_WAITING, waiting);
    }
    
    
    
    // yeah
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
