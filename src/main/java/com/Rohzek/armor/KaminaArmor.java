package com.Rohzek.armor;

import com.Rohzek.lib.RefStrings;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class KaminaArmor extends ItemArmor
{

	// Calls Super to base armor class to create ours, passes in a material, a durability int, and enchantability
	// Diamond's dur is 33, and enc is 10 as per base files.
	public KaminaArmor(ArmorMaterial material, int durability, int enchantability) 
	{
		super(material, durability, enchantability);
	}
	
	// Rendering for item on player, legs have their own file
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(stack.getItem() == SPArmors.kamGlasses || stack.getItem() == SPArmors.kamChest || stack.getItem() == SPArmors.kamBoots)
		{
			return RefStrings.MODID + ":textures/models/armor/Kamina1.png";
		}
		
		else if(stack.getItem() == SPArmors.kamLegs)
		{
			return RefStrings.MODID + ":textures/models/armor/Kamina2.png";
		}
		else
		{
			return null;
		}
	}
}
