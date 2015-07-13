package com.Rohzek.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.Rohzek.achievements.SPAchievements;
import com.Rohzek.block.SPBlocks;
import com.Rohzek.item.SPItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class AchievementHandler 
{
	 public static int coreDrillsFound = 0;
	 
	@SubscribeEvent
	public void notifyPickup(PlayerEvent.ItemPickupEvent e)
	{
		if(e.pickedUp.getEntityItem().getItem() == SPItems.coreDrill)
		{
			coreDrillsFound = coreDrillsFound += 1;
		}
		if(e.pickedUp.getEntityItem().isItemEqual(new ItemStack(SPItems.coreDrill)))
		{
			e.player.triggerAchievement(SPAchievements.coreDrillPickupFirstTime);
		}
		
		if(e.pickedUp.getEntityItem().isItemEqual(new ItemStack(SPItems.spiralDroplet)))
		{
			e.player.triggerAchievement(SPAchievements.whatIsThisPower);
		}
	}
	
	@SubscribeEvent
	public void onCrafting(PlayerEvent.ItemCraftedEvent e)
	{
		if(e.crafting.getItem().equals(SPItems.spiralPowerChunk))
		{
			e.player.triggerAchievement(SPAchievements.powerUpYourSpirals);
		}
	}
	
	@SubscribeEvent
	public void onSmelting(PlayerEvent.ItemSmeltedEvent e)
	{
		
	}
}
