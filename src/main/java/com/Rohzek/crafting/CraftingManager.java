package com.Rohzek.crafting;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.Rohzek.armor.SPArmors;
import com.Rohzek.block.SPBlocks;
import com.Rohzek.item.SPItems;
import com.Rohzek.tools.SPTools;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingManager 
{
	
	private static ItemStack enchantStack;
	
	// Calls all functions in one easy call. Called in main class.
	public static void mainRegistry()
	{
		addCraftingRec();
		addSmeltingRec();
	}
	
	// Adds crafting recipies
	public static void addCraftingRec()
	{
		// This item has enchantment added to it when crafted... does not apply when spawned in.. 
		// Not sure how to make it always have it but this works for now I guess
		enchantStack = new ItemStack(SPTools.viralCleaver);
		enchantStack.addEnchantment(Enchantment.sharpness, 4);
		
		// Not going into recipe format here.. look up the super class by control clicking .addRecipe() if supported in your IDE.  Eclipse supports it.
		// Item Recipes
		GameRegistry.addRecipe(new ItemStack(SPItems.spiralPowerChunk), new Object[]{"XXX","XXX","XXX", 'X', SPItems.spiralDroplet});
		GameRegistry.addRecipe(new ItemStack(SPBlocks.SpiralPowerBlock), new Object[]{"XXX","XXX","XXX", 'X', SPItems.spiralPowerChunk});
		
		// Tool Recipes
		GameRegistry.addRecipe(new ItemStack(SPTools.spiralDrill), new Object[]{" X ","XYX"," S ", 'X', Items.iron_ingot, 'Y', SPItems.coreDrill.setContainerItem(SPItems.coreDrill), 'S', Items.stick});
		GameRegistry.addRecipe(new ItemStack(SPTools.kamKatana), new Object[]{"X","Y","S", 'X', Items.iron_ingot,  'Y', SPItems.spiralPowerChunk, 'S', Items.stick});
		GameRegistry.addRecipe(new ItemStack(SPTools.simonKatana), new Object[]{"X","Y","S", 'X', Items.diamond,  'Y', SPItems.spiralPowerChunk, 'S', Items.stick});
		GameRegistry.addRecipe(enchantStack, new Object[]{"XXS","XX ", 'X', Items.iron_ingot, 'S', Items.stick});	
		GameRegistry.addRecipe(new ItemStack(SPTools.yokoRifle), new Object[]{"X  ", "ZX ", "  Y", 'X', Items.iron_ingot, 'Y', Items.leather, 'Z', SPItems.spiralPowerChunk});
		
		// Armor Recipes
			// Kamina
			GameRegistry.addRecipe(new ItemStack(SPArmors.kamGlasses), new Object[]{"XYX","ZXZ", 'X', new ItemStack(Blocks.stained_glass_pane, 1, 1),  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.kamChest), new Object[]{"X X","XYX","XZX", 'X', Items.leather,  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.kamLegs), new Object[]{"ZYZ","X X","X X", 'X', Items.leather,  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.kamBoots), new Object[]{"X X","Y Y","Z Z", 'X', Items.leather,  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			
			// Digger
			GameRegistry.addRecipe(new ItemStack(SPArmors.diggerGoggles), new Object[]{"XXX","XYX","XXX", 'X', Items.iron_ingot, 'Y', new ItemStack(Blocks.stained_glass_pane, 1, 4)});
			GameRegistry.addRecipe(new ItemStack(SPArmors.diggerChest), new Object[]{"X X","XZX","XXX", 'X', Items.leather,'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.diggerLegs), new Object[]{"XZX","X X","X X", 'X', Items.leather,'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.diggerBoots), new Object[]{"X X","Z Z","X X", 'X', Items.leather, 'Z', Items.iron_ingot});
			
			// Simon
			GameRegistry.addRecipe(new ItemStack(SPArmors.simonGlasses), new Object[]{"X X","ZYZ", "X X", 'X', new ItemStack(Blocks.stained_glass_pane, 1, 14),  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.simonChest), new Object[]{"X X","XYX","XXX", 'X', Items.leather,  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.simonLegs), new Object[]{"XYX","X X","X X", 'X', Items.leather,  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(SPArmors.simonBoots), new Object[]{"X X","Y Y","X X", 'X', Items.leather,  'Y', SPItems.spiralPowerChunk, 'Z', Items.iron_ingot});
			
	}
	
	// Format: Item in, ItemStack out, Amount of EXP given
	public static void addSmeltingRec()
	{
		// Smelting Recipes
		// We're removing this but leaving it here as an example on how to make these.
		//GameRegistry.addSmelting(Blocks.stone, new ItemStack(SPItems.spiralDroplet), 20.0f);
	}
}
