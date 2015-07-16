package com.Rohzek.creativetabs;

import net.minecraft.creativetab.CreativeTabs;

public class SPCreativeTabs 
{
	
	public static CreativeTabs itemsTab;
	public static CreativeTabs blocksTab;
	public static CreativeTabs toolsTab;
	public static CreativeTabs armorTab;
	
	public static void initialiseTabs()
	{
		itemsTab = new CreativeTabItem("itemsTab");
		blocksTab = new CreativeTabBlock("blocksTab");
		toolsTab = new CreativeTabTools("toolsTab");
		armorTab = new CreativeTabArmor("armorTab");
	}
}
