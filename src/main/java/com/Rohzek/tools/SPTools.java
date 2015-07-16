package com.Rohzek.tools;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class SPTools 
{
	public static void mainRegistry()
	{
		initializeItem();
		registerItem();
	}
	
	public static ToolMaterial spiralPowerMat = EnumHelper.addToolMaterial("Spiral", 4, 3000, 10.0f, 4.5f, 10);
	
	public static Item spiralDrill;
	public static Item kamKatana;
	public static Item simonKatana;
	public static Item viralCleaver;
	
	public static Item yokoRifle;
	
	public static void initializeItem()
	{
		spiralDrill = new SpiralDrill(spiralPowerMat);
		kamKatana = new KamKatana(spiralPowerMat).setUnlocalizedName("kamKatana").setTextureName(RefStrings.MODID + ":kamKatana").setCreativeTab(SPCreativeTabs.toolsTab);
		simonKatana = new SimonKatana(spiralPowerMat).setUnlocalizedName("simonKatana").setTextureName(RefStrings.MODID + ":simonKatana").setCreativeTab(SPCreativeTabs.toolsTab);
		viralCleaver = new ViralCleaver(spiralPowerMat).setUnlocalizedName("viralCleaver").setTextureName(RefStrings.MODID + ":viralCleaver").setCreativeTab(SPCreativeTabs.toolsTab);
		
		yokoRifle = new YokoRifle();
	}
	
	public static void registerItem()
	{
		GameRegistry.registerItem(spiralDrill, spiralDrill.getUnlocalizedName());
		GameRegistry.registerItem(kamKatana, kamKatana.getUnlocalizedName());
		GameRegistry.registerItem(simonKatana, simonKatana.getUnlocalizedName());
		GameRegistry.registerItem(viralCleaver, viralCleaver.getUnlocalizedName());
		
		GameRegistry.registerItem(yokoRifle, yokoRifle.getUnlocalizedName());
	}
}
