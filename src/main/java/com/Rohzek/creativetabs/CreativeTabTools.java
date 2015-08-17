package com.Rohzek.creativetabs;

import com.Rohzek.item.SPItems;
import com.Rohzek.tools.SPTools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

// Only really needed one of these classes... Not sure why I used so many... Will probably reduce to one later.
public class CreativeTabTools extends CreativeTabs 
{

	public CreativeTabTools(String name) 
	{
		super(name);
	}

	@Override
	public Item getTabIconItem() 
	{
		return SPTools.kamKatana;
	}

}
