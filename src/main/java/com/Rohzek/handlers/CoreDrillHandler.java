package com.Rohzek.handlers;

import java.util.Random;

import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.player.SPExtendedPlayerStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CoreDrillHandler 
{
	// Creates a random number generator
	Random r = new Random();
	
	// Not sure whats up with SubscribeEvent. See main classes for more info~ish.
	@SubscribeEvent
	public void breakStone(HarvestDropsEvent event)
	{
		// Find out if this is a player doing this.. Endermen and Sheep don't have mana to take from and don't need a coredrill.
		if (event.harvester instanceof EntityPlayer || event.harvester instanceof EntityPlayerMP) 
		{
			// Get's isntance of EXPlayerProperties for current player
			SPExtendedPlayerStats props = SPExtendedPlayerStats.get((EntityPlayer)event.harvester);
			
			// Only check when stone is broken.. We only want coredrill to come from stone.
			if(event.block == Blocks.stone)
			{
				// check to make sure player hasn't already got a coredrill.
				if(props.getCoreDrillFound() == false)
				{
					// uses random number geenrator to generate a number between 1 and 500
					int check = r.nextInt(500) + 1;
					
					// If debugging make it easy to get a coredrill
					if(RefStrings.DEBUG)
					{
						if(check > 1)
						{
							if(check > 1)
							{
								event.drops.add(new ItemStack(SPItems.coreDrill, 1));
								// Sorry we're heartless. Coredrills are "soulbound" so generating one blocks generation..
								// Hope it doesn't fall in lava or someone else steals it...
								props.setCoreDrillFound(true);
								
								props.sync((EntityPlayerMP)props.player);
							}
						}
					}
					// Else on the release make coredrills the normal drops.
					else
					{
						if(check == 13 || check == 169 || check == 221 || check == 343 || check == 404)
						{
							if(check > 1)
							{
								event.drops.add(new ItemStack(SPItems.coreDrill, 1));
								// Sorry we're heartless. Coredrills are "soulbound" so generating one blocks generation..
								// Hope it doesn't fall in lava or someone else steals it...
								props.setCoreDrillFound(true);
								
								props.sync((EntityPlayerMP)props.player);
							}
						}
					}
				}
			}
		}
	}
}
