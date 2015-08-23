package com.Rohzek.spiralpowermod;

import com.Rohzek.gui.SPGuiHandler;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.tileentity.TileEntitySpiralFurnace;
import com.Rohzek.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;

// Registers Server only things.
public class ServerProxy 
{
	public void registerRenderInfo()
	{}
	
	public int addArmor(String armor)
	{
		return 0;
	}
	
	public static void registerTileEntity()
	{
		LogHelper.log("Registering Tile Entity Models and Textures");
		GameRegistry.registerTileEntity(TileEntitySpiralFurnace.class , RefStrings.RESOURCEID + "TileEntityTutFurnace");
	}
	
	public void RegisterNetwork()
	{
		LogHelper.log("Registering Custom Tile Entity GUI Handler");
		NetworkRegistry.INSTANCE.registerGuiHandler(MainRegistry.spiralpowermod, new SPGuiHandler());
	}
	
	 // Returns a side-appropriate EntityPlayer for use during message handling checking for server
	public EntityPlayer getPlayerEntity(MessageContext ctx) 
	{
		LogHelper.log("Packet asked for side, returning Server");
		return ctx.getServerHandler().playerEntity;
	}
}
