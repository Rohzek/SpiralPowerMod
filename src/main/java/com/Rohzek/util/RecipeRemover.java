package com.Rohzek.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;

// Can remove custom crafting recipes and smelting recipes from the game... Not recommended unless GregTech style changing...
public class RecipeRemover 
{
	// Checks for reciepes that turn into items
	public static void removeCraftingRecipe(Item item)
	{
	     List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
	     Iterator<IRecipe> remover = recipes.iterator();
	     
	     while(remover.hasNext())
	     {
	    	// If the output matches the specified block
	    	 ItemStack itemstack = remover.next().getRecipeOutput();
				
				if(itemstack != null && itemstack.getItem() == item)
				{
					// Remove the recipe
					remover.remove();
				}
	     }
	}
	
	// Checks for recipes that turn into blocks
	public static void removeCraftingRecipe(Block block)
	{
	     List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
	     Iterator<IRecipe> remover = recipes.iterator();
	     
	     while(remover.hasNext())
	     {
	    	 ItemStack itemstack = remover.next().getRecipeOutput();
				
	    	 	// If the output matches the specified block
				if(itemstack != null && itemstack.getItem() == Item.getItemFromBlock(block))
				{
					// Remove the recipe
					remover.remove();
					
				}
	     }
	}
	
	// You have to pass in a new ItemStack().. But this will work for Items or Blocks without extra work.
	public static void removeSmeltingRecipe (ItemStack resultItem)
	{
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.smelting().getSmeltingList();
		
		for (Iterator<Map.Entry<ItemStack,ItemStack>> remover = recipes.entrySet().iterator(); remover.hasNext();)
		{
			Map.Entry<ItemStack,ItemStack> entry = remover.next();
			
			ItemStack result = entry.getValue();
			
			// If the output matches the specified ItemStack,
			if (ItemStack.areItemStacksEqual(result, resultItem))
			{ 
				// Remove the recipe
				remover.remove(); 
			}
		}
	}
}
