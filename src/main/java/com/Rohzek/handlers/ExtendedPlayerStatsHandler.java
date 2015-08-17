package com.Rohzek.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.Rohzek.player.SPExtendedPlayerStats;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

// ExtendedPlayerProperties are created for player when player is first created. This is upon creating a new SP world.. or the first join on a MP World.
// If errors occur it may be required to delete player data from server so that this can recreate this. I've had to do this in testing.
public class ExtendedPlayerStatsHandler
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		
		 // Be sure to check if the entity being constructed is the correct type for the extended properties you're about to add. The null check may not be necessary
		 // I only use it to make sure properties are only registered once per entity
		if (event.entity instanceof EntityPlayer && SPExtendedPlayerStats.get((EntityPlayer) event.entity) == null)
		// Registering our properties
			SPExtendedPlayerStats.register((EntityPlayer) event.entity);
		// That will call the constructor as well as cause the init() method
		// to be called automatically
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		//Only need to synchronize when the world is remote (i.e. we're on the server side)
		// and only for player entities, as that's what we need for the GuiManaBar
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			SPExtendedPlayerStats props = SPExtendedPlayerStats.get((EntityPlayer)event.entity);
			
			props.sync((EntityPlayerMP)event.entity);
		}
	}
}
