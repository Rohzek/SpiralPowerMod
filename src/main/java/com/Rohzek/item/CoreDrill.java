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

// Class for most important part of the entire mod.
public class CoreDrill extends Item
{
	// Normal item creation.. assigns unlocalized name and texture based on said name. also gives it a creative tab
	public CoreDrill()
	{
		super();
		this.setUnlocalizedName("coreDrill");
		this.setTextureName(RefStrings.MODID + ":coreDrill");
		this.setCreativeTab(SPCreativeTabs.itemsTab);
		// When used in a crafting recipe, return the coredrill back
		this.setContainerItem(SPItems.coreDrill);
		// only allow a stack of one... just a formality.. player should never have more than one anyway.
		this.setMaxStackSize(1);
	}
	
	public boolean hasEffect(ItemStack stack)
	{
		// displays glow effect
		return true;
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)
	{
		// "flavor text" or "lore text" whatever you want to call it.
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
