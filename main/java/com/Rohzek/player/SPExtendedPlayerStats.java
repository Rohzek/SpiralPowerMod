package com.Rohzek.player;

import ibxm.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import com.Rohzek.network.PacketPipeline;
import com.Rohzek.network.client.SyncPlayerPropsPacket;
import com.Rohzek.spiralpowermod.MainRegistry;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SPExtendedPlayerStats implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = "SPExtendedPlayer";
	public static EntityPlayer player;
	
	private static boolean coreDrillFound;
	private static int currentMana;
	private static int maxMana;
	
	public SPExtendedPlayerStats(EntityPlayer p) 
	{	
		this.coreDrillFound = false;
		
		player = p;
		
		this.maxMana = 50;
		
		this.currentMana = this.maxMana = 50;
	}
	
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(SPExtendedPlayerStats.EXT_PROP_NAME, new SPExtendedPlayerStats(player));
	}
	
	public static final SPExtendedPlayerStats get(EntityPlayer player)
	{
		return (SPExtendedPlayerStats) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	
	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setBoolean("CoreDrillsFound", this.coreDrillFound);
		
		properties.setInteger("CurrentMana", this.currentMana);
		properties.setInteger("MaxMana", this.maxMana);
		
		compound.setTag(EXT_PROP_NAME, properties);
		
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		
		this.coreDrillFound = properties.getBoolean("coreDrillFound");
		this.currentMana = properties.getInteger("CurrentMana");
		this.maxMana = properties.getInteger("MaxMana");
	}

	@Override
	public void init(Entity entity, World world) 
	{	
	}
		
	public static boolean getCoreDrillFound()
	{
		return coreDrillFound;
	}
	
	public static void setCoreDrillFound(boolean found)
	{
		coreDrillFound = found;
	}
	
	public final static boolean consumeMana(float amount)
	{
		boolean sufficient = amount <= currentMana;
		currentMana -= (amount < currentMana ? amount : currentMana);
		
		sync();
		
		return sufficient;
	}
	
	public final static boolean consumeMana(int amount)
	{
		boolean sufficient = amount <= currentMana;
		currentMana -= (amount < currentMana ? amount : currentMana);
		
		sync();
		
		return sufficient;
	}
	
	public final void replenishMana()
	{
		this.currentMana = this.maxMana;
		
		sync();
	}
	
	public static int getMaxMana()
	{
		return maxMana;
	}
	
	public void setMaxMana(int amount)
	{
		this.maxMana = (amount > 0 ? amount : 0);
		
		sync();
	}
	
	public final static int getCurrentMana()
	{
		return currentMana;
	}
	
	public final static void setCurrentMana(int amount)
	{
		currentMana = amount < currentMana ? amount : amount;
		
		sync();
	}
	
	public final static void addCurrentMana(int amount)
	{
		int add = currentMana += amount;
		currentMana = add;
		
		sync();
	}
	
	public final static void sync()
	{
		if (!player.worldObj.isRemote) 
		{
			// If is server, use packets
			System.out.println("Sending packet: Player.NBT to " + player);
			PacketPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
		}
		else
		{}
	}
}