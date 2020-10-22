package com.bagel.thwomp.common.entity.goals;

import java.util.EnumSet;

import com.bagel.thwomp.common.entity.ShyGuyEntity;

import net.minecraft.entity.ai.goal.Goal;

public class ShyGuyStackLookGoal extends Goal {
	protected final ShyGuyEntity thisGuy;
	
	public ShyGuyStackLookGoal(ShyGuyEntity thisGuy) {
		this.thisGuy = thisGuy;
		this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
	}
	
	public boolean shouldExecute() {
		return this.thisGuy.isPassenger();
	}
	
    public boolean shouldContinueExecuting() {
        return this.thisGuy.isPassenger();
    }
	
	public void tick() {
		/*ShyGuyEntity belowGuy = (ShyGuyEntity)this.thisGuy.getRidingEntity();
		
		if (belowGuy != null) {
			this.thisGuy.getLookController().setLookPositionWithEntity(belowGuy, 10.0F, (float)belowGuy.getVerticalFaceSpeed());
			
		}*/
		// TODO:
		// make this shit work
	}
}