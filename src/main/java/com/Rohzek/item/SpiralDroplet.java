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
	public SpiralDroplet()
	{
		super();
		this.setUnlocalizedName("spiralDroplet");
		this.setTextureName(RefStrings.MODID + ":spiralDroplet");
		this.setCreativeTab(SPCreativeTabs.itemsTab);
		this.setContainerItem(SPItems.coreDrill);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
		SPExtendedPlayerStats props = SPExtendedPlayerStats.get(player);
		
		if(props.getCurrentMana() < props.getMaxMana())
		{
			props.addCurrentMana(10);
			if (!player.worldObj.isRemote)
			{
				EntityPlayerMP playerDebug = (EntityPlayerMP) player;
				props.sync(playerDebug);
			}
			
			--item.stackSize;
		}
        return item;
    }
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)
	{
		list.add("");
		list.add(EnumChatFormatting.GOLD + "Raw piece of physical Spiral Power.");
		
	}
}
