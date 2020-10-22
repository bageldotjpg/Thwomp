package com.bagel.thwomp.common.entity.goals;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.bagel.thwomp.common.entity.ShyGuyEntity;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ShyGuyStackUpGoal extends Goal {
	
	private static final EntityPredicate SHY_GUY_TARGETING = (new EntityPredicate()).setDistance(8.0D).setLineOfSiteRequired();
	
	protected final ShyGuyEntity thisGuy;
	protected ShyGuyEntity targetGuy;
	protected final World world;
	private final double moveSpeed;
	
	
	public ShyGuyStackUpGoal(ShyGuyEntity thisGuy, double speedIn) {
		this.thisGuy = thisGuy;
		this.world = thisGuy.world;
		this.moveSpeed = speedIn;
		
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}
	
	
	public void resetTask() {
		this.targetGuy = null;
	}
	
	
	public boolean shouldExecute() {
		this.targetGuy = this.getShyGuyNear();
		
		return targetGuy != null &&
			   hasSpaceAboveStack() &&
			   !this.thisGuy.isWaitingForStack() &&
			   this.targetGuy.getRecursivePassengers().size() < 4 &&
			   this.targetGuy.shyGuyStackAmount() <= 5 &&
			   this.thisGuy.shyGuyStackAmount() == 0 &&
			   !this.targetGuy.isPassenger();
	}
	
	
    public boolean shouldContinueExecuting() {
        boolean execute = hasSpaceAboveStack() &&
        				  this.targetGuy.isAlive() &&
        				  this.targetGuy.isWaitingForStack() &&
        				  !this.thisGuy.isPassenger() &&
        				  !this.targetGuy.isPassenger();
        
        if (!this.world.isRemote && !execute && this.targetGuy.isAlive()) {
        	this.targetGuy.setWaitingForStack(false);
        	this.thisGuy.setWaitingForStack(false);
        }
        
        return execute;
    }
    
    
    public void tick() {
    	// move to target shy guy abnd make target shy guy stare
    	this.thisGuy.getNavigator().tryMoveToEntityLiving(this.targetGuy, this.moveSpeed);
    	this.targetGuy.getLookController().setLookPositionWithEntity(this.thisGuy, 10.0F, (float)targetGuy.getVerticalFaceSpeed());
    	
    	if (!this.world.isRemote) {
    		
	    	// this makes the target shy guy not jump
	    	if (!this.targetGuy.isWaitingForStack()) {
	    		this.targetGuy.setWaitingForStack(true);
	    	}
	    	if (this.thisGuy.isWaitingForStack()) {
	    		this.thisGuy.setWaitingForStack(false);
	    	}
	    	
	    	
	    	// attempt to stack up
	    	double distanceToGuy = this.thisGuy.getDistanceSq(this.targetGuy);
	    		
	    	if (this.targetGuy.isOnGround() && this.targetGuy.isWaitingForStack()) {
	    		
	    		if (distanceToGuy <= 5.0D && this.targetGuy.getMotion().y < 0) {
		    		this.targetGuy.setWaitingForStack(false);
		    		this.targetGuy.startRiding(this.thisGuy);
		    		
		    	} else if (distanceToGuy <= 8.0D) {
		    		this.targetGuy.setMotion(new Vector3d(0f, 0.6f, 0f));
		    		this.targetGuy.getNavigator().tryMoveToEntityLiving(this.thisGuy, this.moveSpeed / 1.5f);
		    		
		    	}
	    	}
    	}
    }
	
	
	@Nullable
	private ShyGuyEntity getShyGuyNear() {
		ShyGuyEntity nearestGuy = this.world.getClosestEntityWithinAABB(this.thisGuy.getClass(), SHY_GUY_TARGETING, this.thisGuy, this.thisGuy.getPosX(), this.thisGuy.getPosY(), this.thisGuy.getPosZ(), this.thisGuy.getBoundingBox().grow(15.0D));
		
		if (nearestGuy != null && this.thisGuy.getDistanceSq(nearestGuy) < Double.MAX_VALUE) {
			return nearestGuy;
		}
		
		return null;
	}
	
	
	private boolean hasSpaceAboveStack() {
		// TODO:
		// make this return false if theres not enough space for the shy guy stack to extend
		
		return true;
	}
}
