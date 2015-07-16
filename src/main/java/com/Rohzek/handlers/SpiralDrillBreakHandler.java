package com.Rohzek.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import com.Rohzek.item.SPItems;
import com.Rohzek.tools.SPTools;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpiralDrillBreakHandler 
{
	@SubscribeEvent
	public void drillBreak(PlayerDestroyItemEvent event)
	{
		if(event.original == new ItemStack(SPTools.spiralDrill))
		{
			
		}
	}
}
