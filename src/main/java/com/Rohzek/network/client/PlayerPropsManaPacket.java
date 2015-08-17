package com.Rohzek.network.client;

import java.io.IOException;
import com.Rohzek.network.AbstractPacket.AbstractClientPacket;
import com.Rohzek.player.SPExtendedPlayerStats;
import com.Rohzek.spiralpowermod.MainRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

// This class doesn't actually do anything yet.

public class PlayerPropsManaPacket extends AbstractClientPacket<SyncPlayerPropsPacket>
{
	// The basic, no-argument constructor MUST be included to use the new automated handling
	public PlayerPropsManaPacket()
	{}

	// We need to initialize our data, so provide a suitable constructor:
	public PlayerPropsManaPacket(EntityPlayer player) 
	{
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException 
	{
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException 
	{
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{
	}
}

