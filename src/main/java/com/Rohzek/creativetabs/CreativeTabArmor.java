package com.Rohzek.creativetabs;

import com.Rohzek.armor.SPArmors;
import com.Rohzek.item.SPItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabArmor extends CreativeTabs 
{

	public CreativeTabArmor(String name) 
	{
		super(name);
	}

	@Override
	public Item getTabIconItem() 
	{
		return SPArmors.kamGlasses;
	}

}
