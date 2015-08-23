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
	
	public static Item ironNugget;
	
	public static Item yokoBullet;
	public static Item spentCasing;
	public static Item bulletTip;
	public static Item bulletPrimer;
	public static Item boxOfShells;
	public static Item boxOfTips;
	public static Item boxOfPrimers;
	public static Item boxOfEmpty;
	
	// initializes items.
	public static void initializeItem()
	{
		// No standard format. Some things handled here, some have their own class. May or may change as items are added and tweaked.
		coreDrill = new CoreDrill();
		brokenDrill = new BrokenDrill();
		spiralDroplet = new SpiralDroplet();
		spiralPowerChunk = new Item().setUnlocalizedName("spiralPowerChunk").setTextureName(RefStrings.RESOURCEID + "spiralPower").setCreativeTab(SPCreativeTabs.itemsTab);
		
		ironNugget = new Item().setUnlocalizedName("ironNugget").setTextureName(RefStrings.RESOURCEID + "ironNugget").setCreativeTab(CreativeTabs.tabMisc);
		
		yokoBullet = new Item().setUnlocalizedName("yokoBullet").setTextureName(RefStrings.RESOURCEID + "yokoBullet").setCreativeTab(SPCreativeTabs.itemsTab);
		spentCasing = new Item().setUnlocalizedName("spentCasing").setTextureName(RefStrings.RESOURCEID + "spentCasing").setCreativeTab(SPCreativeTabs.itemsTab);
		bulletTip = new Item().setUnlocalizedName("bulletTip").setTextureName(RefStrings.RESOURCEID + "bulletTip").setCreativeTab(SPCreativeTabs.itemsTab);
		bulletPrimer = new Item().setUnlocalizedName("bulletPrimer").setTextureName(RefStrings.RESOURCEID + "bulletPrimer").setCreativeTab(SPCreativeTabs.itemsTab);
		boxOfShells = new Item().setUnlocalizedName("boxOfShells").setTextureName(RefStrings.RESOURCEID + "boxOfShells").setCreativeTab(SPCreativeTabs.itemsTab);
		boxOfTips = new Item().setUnlocalizedName("boxOfTips").setTextureName(RefStrings.RESOURCEID + "boxOfTips").setCreativeTab(SPCreativeTabs.itemsTab);
		boxOfPrimers = new Item().setUnlocalizedName("boxOfPrimers").setTextureName(RefStrings.RESOURCEID + "boxOfPrimers").setCreativeTab(SPCreativeTabs.itemsTab);
		boxOfEmpty = new Item().setUnlocalizedName("boxOfEmpty").setTextureName(RefStrings.RESOURCEID + "boxOfEmpty").setCreativeTab(SPCreativeTabs.itemsTab);
	}
	
	// Adds items to game registry.
	public static void registerItem()
	{
		GameRegistry.registerItem(coreDrill, coreDrill.getUnlocalizedName());
		GameRegistry.registerItem(brokenDrill, brokenDrill.getUnlocalizedName());
		GameRegistry.registerItem(spiralDroplet, spiralDroplet.getUnlocalizedName());
		GameRegistry.registerItem(spiralPowerChunk, spiralPowerChunk.getUnlocalizedName());
		
		GameRegistry.registerItem(ironNugget, ironNugget.getUnlocalizedName());
		
		GameRegistry.registerItem(yokoBullet, yokoBullet.getUnlocalizedName());
		GameRegistry.registerItem(spentCasing, spentCasing.getUnlocalizedName());
		GameRegistry.registerItem(bulletTip, bulletTip.getUnlocalizedName());
		GameRegistry.registerItem(bulletPrimer, bulletPrimer.getUnlocalizedName());
		GameRegistry.registerItem(boxOfShells, boxOfShells.getUnlocalizedName());
		GameRegistry.registerItem(boxOfTips, boxOfTips.getUnlocalizedName());
		GameRegistry.registerItem(boxOfPrimers, boxOfPrimers.getUnlocalizedName());
		GameRegistry.registerItem(boxOfEmpty, boxOfEmpty.getUnlocalizedName());
	}
}
