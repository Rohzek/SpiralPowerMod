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
	public static void mainRegistry()
	{
		initializeItem();
		registerItem();
	}
	
	public static CoreDrill coreDrill;
	public static SpiralDroplet spiralDroplet;
	public static Item spiralPowerChunk;
	public static Item yokoBullet;
	
	
	public static void initializeItem()
	{
		coreDrill = new CoreDrill();
		spiralDroplet = new SpiralDroplet();
		spiralPowerChunk = new Item().setUnlocalizedName("spiralPowerChunk").setTextureName(RefStrings.MODID + ":spiralPower").setCreativeTab(SPCreativeTabs.itemsTab);
		yokoBullet = new Item().setUnlocalizedName("yokoBullet").setTextureName(RefStrings.MODID + ":yokoBullet").setCreativeTab(SPCreativeTabs.itemsTab);
	}
	
	public static void registerItem()
	{
		GameRegistry.registerItem(coreDrill, coreDrill.getUnlocalizedName());
		GameRegistry.registerItem(spiralDroplet, spiralDroplet.getUnlocalizedName());
		GameRegistry.registerItem(spiralPowerChunk, spiralPowerChunk.getUnlocalizedName());
		GameRegistry.registerItem(yokoBullet, yokoBullet.getUnlocalizedName());
	}
}