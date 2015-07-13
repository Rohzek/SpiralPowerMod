package com.Rohzek.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.Rohzek.player.SPExtendedPlayerStats;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExtendedPlayerStatsHandler
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		/*
		Be sure to check if the entity being constructed is the correct type for the extended properties you're about to add! The null check may not be necessary - I only use it to make sure properties are only registered once per entity
		*/
		if (event.entity instanceof EntityPlayer && SPExtendedPlayerStats.get((EntityPlayer) event.entity) == null)
		// This is how extended properties are registered using our convenient method from earlier
			SPExtendedPlayerStats.register((EntityPlayer) event.entity);
		// That will call the constructor as well as cause the init() method
		// to be called automatically

		// If you didn't make the two convenient methods from earlier, your code would be
		// much uglier:
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(SPExtendedPlayerStats.EXT_PROP_NAME) == null)
			event.entity.registerExtendedProperties(SPExtendedPlayerStats.EXT_PROP_NAME, new SPExtendedPlayerStats((EntityPlayer) event.entity));
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		//Only need to synchronize when the world is remote (i.e. we're on the server side)
		// and only for player entities, as that's what we need for the GuiManaBar
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
			SPExtendedPlayerStats.get((EntityPlayer) event.entity);
	}
}
