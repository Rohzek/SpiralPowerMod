package com.Rohzek.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.Rohzek.gui.GuiManaBar;
import com.Rohzek.network.PacketPipeline;
import com.Rohzek.network.client.SyncPlayerPropsPacket;
import com.Rohzek.player.SPExtendedPlayerStats;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;


public class LivingFallEvent
{
	public LivingFallEvent()
	{
	}
	
	@SubscribeEvent
	public void fallingEvent(LivingHurtEvent  event)
	{
		if (event.entity instanceof EntityPlayer || event.entity instanceof EntityPlayerMP) 
		{
			SPExtendedPlayerStats props = SPExtendedPlayerStats.get((EntityPlayerMP) event.entity);
			if (event.ammount > 3.0F && props.getCurrentMana() > 0) 
			{
				float reduceby = props.getCurrentMana() < (event.ammount - 3.0F) ? props.getCurrentMana() : (event.ammount - 3.0F);
				event.ammount -= reduceby;
				props.consumeMana((int) reduceby);
				
				// Print to console for debugging
				EntityPlayerMP debugName = (EntityPlayerMP)event.entity;
				
				props.sync(debugName);
			}
		}
	}
	
}
