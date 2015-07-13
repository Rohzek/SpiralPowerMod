package com.Rohzek.spiralpowermod;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy 
{
	public void registerRenderInfo()
	{
		
	}
	
	public int addArmor(String armor)
	{
		return 0;
	}
	
	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx) 
	{
		return ctx.getServerHandler().playerEntity;
	}
}
