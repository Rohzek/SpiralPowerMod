package com.Rohzek.block;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SPBlocks
{
	public static void mainRegistry()
	{
		initializeBlock();
		registerBlock();
	}
	
	public static Block SpiralPowerBlock;
	
	public static void initializeBlock()
	{
		SpiralPowerBlock = new SpiralBlock(Material.ground, 0, 4, 8);
	}
	
	public static void registerBlock()
	{
		GameRegistry.registerBlock(SpiralPowerBlock, SpiralPowerBlock.getUnlocalizedName());
	}
}