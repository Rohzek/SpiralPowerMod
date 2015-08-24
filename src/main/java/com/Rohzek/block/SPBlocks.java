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
	
	public static Block spiralPowerBlock;
	
	public static Block spiralFurnace;
	public static Block spiralFurnaceActive;
	
	public static Block spiralSmelter;
	public static Block spiralSmelterActive;
	
	// initalizes/creates block material
	public static void initializeBlock()
	{
		spiralFurnace = new SpiralFurnace(false).setBlockName("spiralFurnace").setCreativeTab(SPCreativeTabs.blocksTab);
		spiralFurnaceActive = new SpiralFurnace(true).setBlockName("spiralFurnaceActive").setLightLevel(1.0f);
		
		// Format: Unique based on type of block, see classes for more details
		spiralPowerBlock = new SpiralBlock(Material.ground, 0, 4, 8);
	}
	
	// Registers item into game: item, name
	public static void registerBlock()
	{
		GameRegistry.registerBlock(spiralPowerBlock, spiralPowerBlock.getUnlocalizedName());
		GameRegistry.registerBlock(spiralFurnace, spiralFurnace.getUnlocalizedName());
		GameRegistry.registerBlock(spiralFurnaceActive, spiralFurnaceActive.getUnlocalizedName());
	}
}