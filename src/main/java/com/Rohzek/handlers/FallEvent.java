package com.Rohzek.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.Rohzek.gui.GuiManaBar;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.network.PacketPipeline;
import com.Rohzek.network.client.SyncPlayerPropsPacket;
import com.Rohzek.player.SPExtendedPlayerStats;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

// Class intercepts falling event to check if its a player.. and to pad the players fall damage by subtracting from mana
public class FallEvent
{
	// empty constructor for... reasons.
	public FallEvent()
	{
	}
	
	@SubscribeEvent
	public void fallingEvent(LivingHurtEvent event)
	{
		// If it's an SP Player or a MP player then continue. Without this you get crashes from sentient chickens wanting mana.
		if (event.entity instanceof EntityPlayer || event.entity instanceof EntityPlayerMP) 
		{
			// casts current entity into a player for debug purpose.. also saves further casting calls.
			EntityPlayerMP playerDebug = (EntityPlayerMP) event.entity;
			
			// Get's instance of explayerprops using current entity.. which is confirmed for a player by now
			SPExtendedPlayerStats props = SPExtendedPlayerStats.get(playerDebug);
			
			// If the fall damage is more than 3.0 and the player has mana continue calculations.
			// We check to see if it's >3.0 because 3 units of fall and under doesn't take damage anyway, no need to pad damage with mana
			if (event.ammount > 3.0F && props.getCurrentMana() > 0) 
			{
				// Calculates the number to reduce by removing mana if you have the mana to cover it.. 
				// Does not absorb all damage.. but usually limits it to only half a heart of damage for most falls.
				float reduceby = props.getCurrentMana() < (event.ammount - 3.0F) ? props.getCurrentMana() : (event.ammount - 3.0F);
				// The amount of damage, minus the reduction.. this is for the calculations
				event.ammount -= reduceby;
				
				if(playerDebug.getDisplayName() == props.player.getDisplayName())
				{
					// the amount of mana to consume is the number needed to pad fall damage
					props.consumeMana((int) reduceby);
					
					// syncs manas upon loss
					props.sync(playerDebug);
				}
				else
				{
					if(RefStrings.DEBUG)
					{
						// if player check didn't work, then throw an error
						System.out.println("Attempt to use other player's mana blocked");
					}
				}
			}
		}
	}
	
}
