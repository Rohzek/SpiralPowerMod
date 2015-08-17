package com.Rohzek.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import com.Rohzek.item.SPItems;
import com.Rohzek.tools.SPTools;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

// CLASS NOT IMPLEMENTED YET
public class SpiralDrillBreakHandler 
{
	// When your spiral drill breaks I want to intercept and add a broken item back to your hand which can be fixed in spiral machinery
	@SubscribeEvent
	public void drillBreak(PlayerDestroyItemEvent event)
	{
		if(event.original == new ItemStack(SPTools.spiralDrill))
		{
			
		}
	}
}
