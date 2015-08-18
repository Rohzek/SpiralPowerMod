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
public class BrokenDrill extends Item
{
	// Normal item creation.. assigns unlocalized name and texture based on said name. also gives it a creative tab
	public BrokenDrill()
	{
		super();
		// Gives unlocalized name
		this.setUnlocalizedName("brokenDrill");
		// Sets texture based on that name
		this.setTextureName(RefStrings.MODID + ":brokenDrill");
		// Sets creative tab for it
		this.setCreativeTab(SPCreativeTabs.itemsTab);
		// Should be held like a tool, like the original drill it came from.
		this.setFull3D();
		// only allow a stack of one.
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
		list.add(EnumChatFormatting.GOLD + "A broken Spiral Drill, still pulsating with power.");
		list.add(EnumChatFormatting.GOLD + "How did this even happen?");
		
	}
}
