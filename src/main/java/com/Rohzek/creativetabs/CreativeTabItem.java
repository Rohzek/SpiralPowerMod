package com.Rohzek.creativetabs;

import com.Rohzek.item.SPItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

// Only really needed one of these classes... Not sure why I used so many... Will probably reduce to one later.
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
