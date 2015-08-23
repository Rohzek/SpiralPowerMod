package com.Rohzek.player;

import ibxm.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import akka.actor.Props;

import com.Rohzek.lib.RefStrings;
import com.Rohzek.network.PacketPipeline;
import com.Rohzek.network.client.SyncPlayerPropsPacket;
import com.Rohzek.spiralpowermod.MainRegistry;
import com.Rohzek.util.LogHelper;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

// This class handles our non standard player stuff added by the mod. Mana, Coredrills.. etc.
public class SPExtendedPlayerStats implements IExtendedEntityProperties
{
	// This has to be unique to this mod or conflicts arise.. Hopefully this is unique enough?
	public final static String EXT_PROP_NAME = "SPExtendedPlayer";
	// Player to be used for this instance of the class
	public EntityPlayer player;
	// Has coredrill been found? Track that.
	private boolean coreDrillFound;
	// Current mana to be used for anything involving mana, including GUI bar.
	private int currentMana;
	// Same but for max mana.
	private int maxMana;
	
	// Initialize the above variables
	public SPExtendedPlayerStats(EntityPlayer p) 
	{
		// player in this class is set to the one passed in.
		player = p;
				
		// Start off having not found the core drill.
		this.coreDrillFound = false;
		
		// Player starts off with 50 max mana.
		this.maxMana = 50;
		
		// Player should start out with max mana.
		this.currentMana = this.maxMana = 50;
	}
	
	// Registers a player to the EXTPlayerProperties
	public final static void register(EntityPlayer player)
	{
		player.registerExtendedProperties(SPExtendedPlayerStats.EXT_PROP_NAME, new SPExtendedPlayerStats(player));
	}
	
	// Retrieves a players EXTPlayerProperties
	public final static SPExtendedPlayerStats get(EntityPlayer player)
	{
		return (SPExtendedPlayerStats) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	// Saves data from players into the NBT
	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setBoolean("coreDrillFound", this.coreDrillFound);
		
		properties.setInteger("CurrentMana", this.currentMana);
		properties.setInteger("MaxMana", this.maxMana);
		
		compound.setTag(EXT_PROP_NAME, properties);	
	}

	// Loads data from the NBT
	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		
		this.coreDrillFound = properties.getBoolean("coreDrillFound");
		this.currentMana = properties.getInteger("CurrentMana");
		this.maxMana = properties.getInteger("MaxMana");
	}
	
	// Copies data from old player to new player using cloning. Is called when respawning on death
	public void copyDeath(SPExtendedPlayerStats props) 
	{
	    this.maxMana = props.maxMana;
	    this.coreDrillFound = props.coreDrillFound;
	}
	
	// Copies data from old player to new player using cloning. Is called when respawning on dimension change
	public void copyDim(SPExtendedPlayerStats props) 
	{
	    this.maxMana = props.maxMana;
	    this.currentMana = props.currentMana;
	    this.coreDrillFound = props.coreDrillFound;
	}

	// Blank initialization function.. not used right now
	@Override
	public void init(Entity entity, World world) 
	{}
	
	// Getter for Core Drill tracking
	public boolean getCoreDrillFound()
	{
		return coreDrillFound;
	}
	
	// Setter for Core Drill tracking
	public void setCoreDrillFound(boolean found)
	{
		coreDrillFound = found;
	}
	
	
	// Ternary Operator is used as such: value = a < b ? a : b
	//                                   value = a if a < b else it's equal to b
	
	
	// Consume float amount of mana.. included for completeness.. probably wont be used?
	public boolean consumeMana(float amount)
	{
		boolean sufficient = amount <= currentMana;
		currentMana -= (amount < currentMana ? amount : currentMana);
		manaCheck();
		
		return sufficient;
	}
	
	// Consume int amount of mana.
	public boolean consumeMana(int amount)
	{
		boolean sufficient = amount <= currentMana;
		currentMana -= (amount < currentMana ? amount : currentMana);
		manaCheck();
		
		return sufficient;
	}
	
	// refills current mana to max mana
	public void replenishMana()
	{
		this.currentMana = this.maxMana;
		manaCheck();
	}
	
	// Getter for maxmimum mana amount
	public int getMaxMana()
	{
		manaCheck();
		return maxMana;
	}
	
	// Setter to change maximum mana.. used for leveling up mana amounts later,
	public void setMaxMana(int amount)
	{
		this.maxMana = (amount > 0 ? amount : 0);
		manaCheck();
	}
	
	// Getter for current mana amount
	public int getCurrentMana()
	{
		manaCheck();
		return currentMana;
	}
	
	// Setter for current mana amount.. Changes current mana to this amount.
	public void setCurrentMana(int amount)
	{
		currentMana = amount < currentMana ? amount : amount;
		manaCheck();
	}
	
	// Setter for adding mana amount.. adds this amount to the existing mana amount
	public void addCurrentMana(int amount)
	{
		int localAmount = amount < currentMana ? amount : maxMana;
		currentMana += localAmount;
		manaCheck();
	}
	
	private void manaCheck()
	{
		if(currentMana > maxMana)
		{
			currentMana = maxMana;
		}
		else
		{}
	}
	// send packets from server to clients to display GUI bar with. Also has debug println
	public void sync(EntityPlayerMP playerMP)
	{
		if (!player.worldObj.isRemote) 
		{
			PacketPipeline.sendTo(new SyncPlayerPropsPacket((EntityPlayer)playerMP), (EntityPlayerMP) playerMP);
			
			if(RefStrings.DEBUG)
			{	
				LogHelper.log("Sending Packet to " + playerMP.getDisplayName());
				LogHelper.log("Packet information is as follows:");
				LogHelper.log(playerMP.getDisplayName() + " has " + currentMana + " mana left");
				LogHelper.log(playerMP.getDisplayName() + " has a maximum limit of " + maxMana + " mana");
				LogHelper.log(playerMP.getDisplayName() + "'s CoreDrill Status: " + coreDrillFound);
			}
		}
		else
		{}
	}
}