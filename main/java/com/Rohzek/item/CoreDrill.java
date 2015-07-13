package com.Rohzek.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;

public class CoreDrill extends Item
{
	public CoreDrill()
	{
		super();
		this.setUnlocalizedName("coreDrill");
		this.setTextureName(RefStrings.MODID + ":coreDrill");
		this.setCreativeTab(SPCreativeTabs.itemsTab);
		this.setMaxStackSize(1);
	}
	
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)
	{
		list.add("");
		list.add(EnumChatFormatting.GOLD + "The Core Drill is a small drill, almost key-like in size.");
		list.add(EnumChatFormatting.GOLD + "It seems to glow with power as you hold it.");
		list.add(EnumChatFormatting.GOLD + "Not much is known about it, but it is suspected ");
		list.add(EnumChatFormatting.GOLD + "that there are others just like it.");
		list.add("");
		list.add(EnumChatFormatting.RED + "WARNING: The Core Drill is \"soul bound\":");
		list.add(EnumChatFormatting.RED + "It's very easy to accidentally lose...");
		list.add(EnumChatFormatting.RED + "But it will be next to impossible to get another one.");
		
	}
}
