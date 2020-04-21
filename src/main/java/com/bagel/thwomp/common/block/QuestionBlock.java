package com.bagel.thwomp.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class QuestionBlock extends Block {
	protected static final VoxelShape HITBOX 	= Block.makeCuboidShape(0.0D, 1.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public QuestionBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	     return VoxelShapes.fullCube();
	}
	
	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.fullCube();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return HITBOX;
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof PlayerEntity) {
			spawnAsEntity(worldIn, pos.up(), new ItemStack(Items.DIAMOND, 1));
			worldIn.setBlockState(pos, Blocks.BRICKS.getDefaultState(), 2);
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE, SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
		}
	}
	
	public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, Entity projectile) {
		BlockPos pos = hit.getPos();
		spawnAsEntity(worldIn, pos.up(), new ItemStack(Items.DIAMOND, 1));
		worldIn.setBlockState(pos, Blocks.BRICKS.getDefaultState(), 2);
		worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE, SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
	}
}
