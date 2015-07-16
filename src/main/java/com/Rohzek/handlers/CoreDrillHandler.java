package com.Rohzek.handlers;

import java.util.Random;

import com.Rohzek.item.SPItems;
import com.Rohzek.player.SPExtendedPlayerStats;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CoreDrillHandler 
{
	Random r = new Random();
	
	@SubscribeEvent
	public void breakStone(HarvestDropsEvent event)
	{
		if(event.block == Blocks.stone)
		{
			if(SPExtendedPlayerStats.getCoreDrillFound() == false)
			{
				int check = r.nextInt(500) + 1;
				
				if(check == 13 || check == 169 || check == 221 || check == 343 || check == 404 || check == 504)
				{
					event.drops.add(new ItemStack(SPItems.coreDrill, 1));
					SPExtendedPlayerStats.setCoreDrillFound(true);
				}
			}
			else
			{
						
			}	
		}
	}
}
