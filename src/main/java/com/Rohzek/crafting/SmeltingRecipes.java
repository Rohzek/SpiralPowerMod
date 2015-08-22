package com.Rohzek.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.Rohzek.block.SPBlocks;
import com.Rohzek.item.SPItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SmeltingRecipes
{
	private static final SmeltingRecipes SMELTING_BASE = new SmeltingRecipes();
	private Map smelingList = new HashMap();
	private Map experienceList = new HashMap();
	
	public static SmeltingRecipes smelting()
	{
		return SMELTING_BASE;
	}
	
	// Add Custom Recipes Here!
	private SmeltingRecipes()
	{
		this.addRecipe(SPItems.spentCasing, new ItemStack(SPItems.ironNugget), 0.8f);
		this.addRecipe(Item.getItemFromBlock(SPBlocks.spiralPowerBlock), new ItemStack(SPItems.spiralPowerChunk, 9), 0.8f);
	}
	
	
	public void addRecipe(Item item, ItemStack itemStack, float xp)
	{
		this.addLists(item, itemStack, xp);
	}
	
	public void addLists(Item item, ItemStack itemStack, float xp)
	{
		this.putLists(new ItemStack(item, 1, 32767), itemStack, xp);
	}
	
	public void putLists(ItemStack itemStack0, ItemStack itemStack1, float xp)
	{
		this.smelingList.put(itemStack0, itemStack1);
		this.experienceList.put(itemStack1, Float.valueOf(xp));
	}
	
	public ItemStack getSmeltingResult(ItemStack itemStack)
	{
		Iterator iterator = this.smelingList.entrySet().iterator();
		Entry entry;
		
		do
		{
			if(!iterator.hasNext())
			{
				return null;
			}
			
			entry = (Entry) iterator.next();
		}
		while(!canBeSmelted(itemStack, (ItemStack)entry.getKey()));
		return (ItemStack) entry.getValue();
	}
	
	private boolean canBeSmelted(ItemStack itemStack0, ItemStack itemStack1)
	{
		return itemStack1.getItem() == itemStack0.getItem() && itemStack1.getItemDamage() == 32767 
									|| itemStack1.getItemDamage() == itemStack0.getItemDamage();
	}
	
	public float giveExperience(ItemStack itemStack)
	{
		Iterator iterator = this.experienceList.entrySet().iterator();
		Entry entry;
		
		do
		{
			if(!iterator.hasNext())
			{
				return 0.0f;
			}
			
			entry = (Entry)iterator.next();
		}
		
		while(!this.canBeSmelted(itemStack, (ItemStack)entry.getKey()));
		
		if(itemStack.getItem().getSmeltingExperience(itemStack) != -1)
		{
			return itemStack.getItem().getSmeltingExperience(itemStack);
		}
		
		return ((Float) entry.getValue()).floatValue();
	}
}
