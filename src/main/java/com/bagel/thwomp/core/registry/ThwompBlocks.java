package com.bagel.thwomp.core.registry;

import com.bagel.thwomp.common.block.QuestionBlock;
import com.bagel.thwomp.core.Thwomp;
import com.bagel.thwomp.core.util.RegistryUtils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThwompBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Thwomp.MODID);

	public static final RegistryObject<Block> QUESTION_BLOCK   	= RegistryUtils.createBlock("question_block", () -> new QuestionBlock(Block.Properties.from(Blocks.BRICKS)), ItemGroup.DECORATIONS);
	
}
