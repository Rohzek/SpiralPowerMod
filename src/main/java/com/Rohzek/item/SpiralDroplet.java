package com.Rohzek.item;

import java.util.List;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.player.SPExtendedPlayerStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class SpiralDroplet extends Item
{
	// mana to give back when consumed.
	private int manaToReturn = 5;
	
	// Normal item creation.. assigns unlocalized name and texture based on said name. also gives it a creative tab
	public SpiralDroplet()
	{
		super();
		this.setUnlocalizedName("spiralDroplet");
		this.setTextureName(RefStrings.MODID + ":spiralDroplet");
		this.setCreativeTab(SPCreativeTabs.itemsTab);
		
	}
	
	// When player right clicks with item
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
		// Gets EXPlayerProperties for player performing action
		SPExtendedPlayerStats props = SPExtendedPlayerStats.get(player);
		
		// If mana isn't full
		if(props.getCurrentMana() < props.getMaxMana())
		{
			
			// If player is on server
			if (!player.worldObj.isRemote)
			{
				// easier to check name of player against prop, also saves against further casting calls.
				EntityPlayerMP playerDebug = (EntityPlayerMP) player;
				
				if(player.getDisplayName() == props.player.getDisplayName())
				{
					// add mana
					props.addCurrentMana(manaToReturn);
					
					// syncs manas upon gain
					props.sync(playerDebug);
					
					// consume an item from stack
					--item.stackSize;
				}
				else
				{
					// if player checker fails, throw and error
					System.out.println("Attempt to use other player's mana blocked");
				}
			}
		}
        return item;
    }
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)
	{
		// Adds "flavor text" or "lore text" whatever you want to call it.
		list.add("");
		list.add(EnumChatFormatting.GOLD + "Raw piece of physical Spiral Power.");
		
	}
}
