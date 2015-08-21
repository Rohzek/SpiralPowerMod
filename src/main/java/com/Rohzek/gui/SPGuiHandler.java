package com.Rohzek.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.Rohzek.inventory.ContainerSpiralFurnace;
import com.Rohzek.tileentity.TileEntitySpiralFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class SPGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == 0)
		{
			TileEntitySpiralFurnace spFurnace = (TileEntitySpiralFurnace)world.getTileEntity(x, y, z);
			return new ContainerSpiralFurnace(player.inventory, spFurnace);
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == 0)
		{
			TileEntitySpiralFurnace spFurnace = (TileEntitySpiralFurnace)world.getTileEntity(x, y, z);
			return new GuiSpiralFurnace(player.inventory, spFurnace);
		}
		return null;
	}
}
