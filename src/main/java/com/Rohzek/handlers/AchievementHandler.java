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
	// core drill counter. Doesn't do anything yet but.. If I figure out how to add to stats I may keep it.
	 public int coreDrillsFound = 0;
	 
	@SubscribeEvent
	public void notifyPickup(PlayerEvent.ItemPickupEvent e)
	{
		// If you pick up a coredrill, +1 to coredrill counter
		if(e.pickedUp.getEntityItem().getItem() == SPItems.coreDrill)
		{
			coreDrillsFound = coreDrillsFound += 1;
		}
		if(e.pickedUp.getEntityItem().isItemEqual(new ItemStack(SPItems.coreDrill)))
		{
			// If you picked upo your first coredrill, trigger achievement. First coredrill is tracked in EXTPlayerProps class
			e.player.triggerAchievement(SPAchievements.coreDrillPickupFirstTime);
		}
		if(e.pickedUp.getEntityItem().isItemEqual(new ItemStack(SPItems.spiralDroplet)))
		{
			// When you pick up spiral power droplets for the first time, trigger achievement
			e.player.triggerAchievement(SPAchievements.whatIsThisPower);
		}
	}
	
	@SubscribeEvent
	public void onCrafting(PlayerEvent.ItemCraftedEvent e)
	{
		if(e.crafting.getItem().equals(SPItems.spiralPowerChunk))
		{
			// When you craft a spiral chunk out of droplets, trigger achievement
			e.player.triggerAchievement(SPAchievements.powerUpYourSpirals);
		}
	}
	
	@SubscribeEvent
	public void onSmelting(PlayerEvent.ItemSmeltedEvent e)
	{
		// When you smelt X trigger achivement Y. Currently none exists but could be added later?
		// Not sure this will work for our custom furnace and recipes... Interesting...
	}
}
