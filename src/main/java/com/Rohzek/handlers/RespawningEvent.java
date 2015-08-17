package com.Rohzek.handlers;

import net.minecraftforge.event.entity.player.PlayerEvent;
import com.Rohzek.player.SPExtendedPlayerStats;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

// To save sensative data after death and when changing dimensions
public class RespawningEvent 
{
	// empty constructor
	public RespawningEvent()
	{}
	
	// Players get "cloned" when dying or switching dimensions. The data remains for a bit and then the original is destroyed
	// This takes the data and saves the important bits before that happens
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) 
	{
		// When you die only keep the max mana amount and the coredrill.. Sorry you don't get anymore
		if(event.wasDeath)
		{
			SPExtendedPlayerStats.get(event.entityPlayer).copyDeath(SPExtendedPlayerStats.get(event.original));
		}
		
		// When you switch dimensions you're also killed and cloned.. We want to keep ALL data when that happens.
		else if(!event.wasDeath)
		{
			SPExtendedPlayerStats.get(event.entityPlayer).copyDim(SPExtendedPlayerStats.get(event.original));
		}
	}
}
