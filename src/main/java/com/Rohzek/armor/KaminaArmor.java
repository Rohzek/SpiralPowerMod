package com.Rohzek.armor;

import com.Rohzek.lib.RefStrings;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class KaminaArmor extends ItemArmor
{

	public KaminaArmor(ArmorMaterial material, int durability, int enchantability) 
	{
		super(material, durability, enchantability);
	}
	
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
