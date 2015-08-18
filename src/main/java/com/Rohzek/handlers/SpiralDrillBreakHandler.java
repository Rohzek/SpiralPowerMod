package com.Rohzek.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.tools.SPTools;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

// When currently held item breaks (As far as I can tell, only works with tools and armor?) Check if it's my drill, if it is, return broken drill.
public class SpiralDrillBreakHandler 
{
	ItemStack muhDrill = new ItemStack(SPTools.spiralDrill);
	
	// Fires a call when an item player is holding breaks.
	@SubscribeEvent
	public void drillBreak(PlayerDestroyItemEvent event)
	{
		if(RefStrings.DEBUG)
		{
			System.out.println(event.original.getDisplayName() + " just broke!");
		}
		// If the item broken, is the Spiral Drill then:
		if(event.original.getItem() == muhDrill.getItem())
		{
			if(RefStrings.DEBUG)
			{
				System.out.println("I should be giving you back an item right now!");
			}
			// Give player a broken drill when it breaks
			event.entityPlayer.inventory.addItemStackToInventory(new ItemStack(SPItems.brokenDrill));
		}
	}
}
