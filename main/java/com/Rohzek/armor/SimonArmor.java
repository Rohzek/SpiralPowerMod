package com.Rohzek.armor;

import com.Rohzek.lib.RefStrings;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SimonArmor extends ItemArmor
{

	public SimonArmor(ArmorMaterial material, int durability, int enchantability) 
	{
		super(material, durability, enchantability);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(stack.getItem() == SPArmors.simonGlasses || stack.getItem() == SPArmors.simonChest || stack.getItem() == SPArmors.simonBoots)
		{
			return RefStrings.MODID + ":textures/models/armor/Simon1.png";
		}
		
		else if(stack.getItem() == SPArmors.simonLegs)
		{
			return RefStrings.MODID + ":textures/models/armor/Simon2.png";
		}
		else
		{
			return null;
		}
	}
}
