package com.Rohzek.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;

public class RecipeRemover 
{
	public static void removeCraftingRecipe(Item item)
	{
	     List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
	     Iterator<IRecipe> remover = recipes.iterator();
	     
	     while(remover.hasNext())
	     {
	    	 ItemStack itemstack = remover.next().getRecipeOutput();
				
				if(itemstack != null && itemstack.getItem() == item)
				{
					remover.remove();
				}
	     }
	}
	
	public static void removeCraftingRecipe(Block block)
	{
	     List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
	     Iterator<IRecipe> remover = recipes.iterator();
	     
	     while(remover.hasNext())
	     {
	    	 ItemStack itemstack = remover.next().getRecipeOutput();
				
				if(itemstack != null && itemstack.getItem() == Item.getItemFromBlock(block))
				{
					remover.remove();
					
				}
	     }
	}
	
	public static void removeSmeltingRecipe(Item item)
	{
		FurnaceRecipes.smelting().getSmeltingList().remove(item);
	}
	
	public static void removeSmeltingRecipe(Block block)
	{
		FurnaceRecipes.smelting().getSmeltingList().remove(block);
	}
}
