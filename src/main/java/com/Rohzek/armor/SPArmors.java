package com.Rohzek.armor;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.spiralpowermod.MainRegistry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class SPArmors 
{
	// Calls both required functions in one easy call, is ran in main class
	public static void mainRegistry()
	{
		initializeItem();
		registerItem();
	}
	
	// Better material, makes up the two late game armors, better than diamond
	public static ArmorMaterial spiralPowerArmorMat = EnumHelper.addArmorMaterial("Spiral", 40, new int[]{3, 8, 6, 3}, 10);
	
	// Not so better material, makes up starter armor... Is still better than Iron Armor
	public static ArmorMaterial diggerArmorMat = EnumHelper.addArmorMaterial("Digger", 20, new int[]{3, 8, 6, 3}, 10);
	
	// Kamina Armor
	public static Item kamGlasses;
	public static Item kamChest;
	public static Item kamLegs;
	public static Item kamBoots;
	
	// Digger Armor
	public static Item diggerGoggles;
	public static Item diggerChest;
	public static Item diggerLegs;
	public static Item diggerBoots;
	
	// Simon Armor
	public static Item simonGlasses;
	public static Item simonChest;
	public static Item simonLegs;
	public static Item simonBoots;
	
	// Initializing armors
	public static void initializeItem()
	{
		// Format is: Material, Registery, type (0 is helmet 3 is boots), create unlocalized name, set creative tab, set texture.
		// Could be done differently, by setting some of these in the armor type class... but I kinda like this way.
		
		// Kamina Armor
		kamGlasses = new KaminaArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("KaminaArmor"), 0).setUnlocalizedName("kamGlasses").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "kamGlasses");
		kamChest = new KaminaArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("KaminaArmor"), 1).setUnlocalizedName("kamChest").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "kamChest");
		kamLegs = new KaminaArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("KaminaArmor"), 2).setUnlocalizedName("kamLegs").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "kamLegs");
		kamBoots = new KaminaArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("KaminaArmor"), 3).setUnlocalizedName("kamBoots").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "kamBoots");
		
		// Digger Armor
		diggerGoggles = new DiggerArmor(diggerArmorMat, MainRegistry.proxyServer.addArmor("DiggerArmor"), 0).setUnlocalizedName("diggerGoggles").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "diggerGoggles");
		diggerChest = new DiggerArmor(diggerArmorMat, MainRegistry.proxyServer.addArmor("DiggerArmor"), 1).setUnlocalizedName("diggerChest").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "diggerChest");
		diggerLegs = new DiggerArmor(diggerArmorMat, MainRegistry.proxyServer.addArmor("DiggerArmor"), 2).setUnlocalizedName("diggerLegs").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "diggerLegs");
		diggerBoots = new DiggerArmor(diggerArmorMat, MainRegistry.proxyServer.addArmor("DiggerArmor"), 3).setUnlocalizedName("diggerBoots").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "diggerBoots");
		
		// Simon Armor
		simonGlasses = new SimonArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("SimonArmor"), 0).setUnlocalizedName("simonGlasses").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "simonGlasses");
		simonChest = new SimonArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("SimonArmor"), 1).setUnlocalizedName("simonChest").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "simonChest");
		simonLegs = new SimonArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("SimonArmor"), 2).setUnlocalizedName("simonLegs").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "simonLegs");
		simonBoots = new SimonArmor(spiralPowerArmorMat, MainRegistry.proxyServer.addArmor("SimonArmor"), 3).setUnlocalizedName("simonBoots").setCreativeTab(SPCreativeTabs.armorTab).setTextureName(RefStrings.RESOURCEID + "simonBoots");
		
	}
	
	// Registers items. First is item type, second is item name
	public static void registerItem()
	{
		// Kamina Armor
		GameRegistry.registerItem(kamGlasses, kamGlasses.getUnlocalizedName());
		GameRegistry.registerItem(kamChest, kamChest.getUnlocalizedName());
		GameRegistry.registerItem(kamLegs, kamLegs.getUnlocalizedName());
		GameRegistry.registerItem(kamBoots, kamBoots.getUnlocalizedName());
		
		// Digger Armor
		GameRegistry.registerItem(diggerGoggles, diggerGoggles.getUnlocalizedName());
		GameRegistry.registerItem(diggerChest, diggerChest.getUnlocalizedName());
		GameRegistry.registerItem(diggerLegs, diggerLegs.getUnlocalizedName());
		GameRegistry.registerItem(diggerBoots, diggerBoots.getUnlocalizedName());
		
		// Simon Armor
		GameRegistry.registerItem(simonGlasses, simonGlasses.getUnlocalizedName());
		GameRegistry.registerItem(simonChest, simonChest.getUnlocalizedName());
		GameRegistry.registerItem(simonLegs, simonLegs.getUnlocalizedName());
		GameRegistry.registerItem(simonBoots, simonBoots.getUnlocalizedName());
	}
}
