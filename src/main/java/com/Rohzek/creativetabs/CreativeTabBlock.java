package com.Rohzek.creativetabs;

import com.Rohzek.block.SPBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

// Only really needed one of these classes... Not sure why I used so many... Will probably reduce to one later.
public class CreativeTabBlock extends CreativeTabs 
{

	public CreativeTabBlock(String name) 
	{
		super(name);
	}

	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(SPBlocks.SpiralPowerBlock);
	}

}
