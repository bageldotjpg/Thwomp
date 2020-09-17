package com.bagel.thwomp.core.registry;

import com.bagel.thwomp.common.block.QuestionBlock;
import com.bagel.thwomp.core.Thwomp;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThwompBlocks {
	public static final RegistryHelper HELPER = Thwomp.REGISTRY_HELPER;

	public static final RegistryObject<Block> QUESTION_BLOCK = HELPER.createBlock("question_block", () -> new QuestionBlock(Block.Properties.from(Blocks.BRICKS)), ItemGroup.DECORATIONS);

}
