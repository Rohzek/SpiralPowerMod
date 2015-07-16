package com.Rohzek.creativetabs;

import com.Rohzek.block.SPBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

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
