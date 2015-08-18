package com.Rohzek.item;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SPItems 
{
	// Calls all functions required in one easy call. Is called from main class.
	public static void mainRegistry()
	{
		initializeItem();
		registerItem();
	}
	
	// creates items
	public static CoreDrill coreDrill;
	public static BrokenDrill brokenDrill;
	public static SpiralDroplet spiralDroplet;
	public static Item spiralPowerChunk;
	public static Item yokoBullet;
	public static Item spentCasing;
	public static Item boxOfShells;
	
	// initializes items.
	public static void initializeItem()
	{
		// No standard format. Some things handled here, some have their own class. May or may change as items are added and tweaked.
		coreDrill = new CoreDrill();
		brokenDrill = new BrokenDrill();
		spiralDroplet = new SpiralDroplet();
		spiralPowerChunk = new Item().setUnlocalizedName("spiralPowerChunk").setTextureName(RefStrings.MODID + ":spiralPower").setCreativeTab(SPCreativeTabs.itemsTab);
		yokoBullet = new Item().setUnlocalizedName("yokoBullet").setTextureName(RefStrings.MODID + ":yokoBullet").setCreativeTab(SPCreativeTabs.itemsTab);
		spentCasing = new Item().setUnlocalizedName("spentCasing").setTextureName(RefStrings.MODID + ":spentCasing").setCreativeTab(SPCreativeTabs.itemsTab);
		boxOfShells = new Item().setUnlocalizedName("boxOfShells").setTextureName(RefStrings.MODID + ":boxOfShells").setCreativeTab(SPCreativeTabs.itemsTab);
	}
	
	// Adds items to game registry.
	public static void registerItem()
	{
		GameRegistry.registerItem(coreDrill, coreDrill.getUnlocalizedName());
		GameRegistry.registerItem(brokenDrill, brokenDrill.getUnlocalizedName());
		GameRegistry.registerItem(spiralDroplet, spiralDroplet.getUnlocalizedName());
		GameRegistry.registerItem(spiralPowerChunk, spiralPowerChunk.getUnlocalizedName());
		GameRegistry.registerItem(yokoBullet, yokoBullet.getUnlocalizedName());
		GameRegistry.registerItem(spentCasing, spentCasing.getUnlocalizedName());
		GameRegistry.registerItem(boxOfShells, boxOfShells.getUnlocalizedName());
	}
}
