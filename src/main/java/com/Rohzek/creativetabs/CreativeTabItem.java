package com.Rohzek.creativetabs;

import com.Rohzek.item.SPItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabItem extends CreativeTabs 
{

	public CreativeTabItem(String name) 
	{
		super(name);
	}

	@Override
	public Item getTabIconItem() 
	{
		return SPItems.coreDrill;
	}

}
