package com.Rohzek.handlers;

import com.Rohzek.player.SPExtendedPlayerStats;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class DeathEvent 
{
	public DeathEvent()
	{}
	
	@SubscribeEvent
	public void deathEvent(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityPlayer || event.entity instanceof EntityPlayerMP)
		{
			SPExtendedPlayerStats props = SPExtendedPlayerStats.get((EntityPlayer) event.entity);
			
			props.sync((EntityPlayerMP)event.entity);
		}
	}
}
