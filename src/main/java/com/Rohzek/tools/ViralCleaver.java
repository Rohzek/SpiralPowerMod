package com.Rohzek.tools;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;

public class ViralCleaver extends ItemSword
{
	// applies material to item
	public ViralCleaver(ToolMaterial material) 
	{
		super(material);
		
		// gives tool style "3D" rendering
		this.setFull3D();
		// Gives unlocalized name		
		this.setUnlocalizedName("viralCleaver");
		// Sets texture based on that name
		this.setTextureName(RefStrings.RESOURCEID + "viralCleaver");
		// Sets creative tab for it
		this.setCreativeTab(SPCreativeTabs.toolsTab);
	}
	
	// Has item in creative tab show up/spawn in as enchanted.. Doesn't handle crafting enchantment.. That's in the crafting class.
	public void getSubItems(Item item, CreativeTabs tab, List subItems)
	{
		//subItems.add(new ItemStack(itemIn, 1, 0));
		ItemStack stack = new ItemStack(SPTools.viralCleaver);
		stack.addEnchantment(Enchantment.sharpness, 4);
		subItems.add(stack); 
	}

}
