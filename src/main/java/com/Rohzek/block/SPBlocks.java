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
	// Calls both functions in one easy call. Is called in main class file
	public static void mainRegistry()
	{
		initializeBlock();
		registerBlock();
	}
	
	public static Block SpiralPowerBlock;
	
	// initalizes/creates block material
	public static void initializeBlock()
	{
		// This is the other way to create new things, not used in the armors. We assign too many things to blocks to really put them all here
		
		// Format: Unique based on type of block, see classes for more details
		SpiralPowerBlock = new SpiralBlock(Material.ground, 0, 4, 8);
	}
	
	// Registers item into game: item, name
	public static void registerBlock()
	{
		GameRegistry.registerBlock(SpiralPowerBlock, SpiralPowerBlock.getUnlocalizedName());
	}
}