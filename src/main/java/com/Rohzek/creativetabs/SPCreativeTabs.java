package com.Rohzek.creativetabs;

import net.minecraft.creativetab.CreativeTabs;


public class SPCreativeTabs 
{
	
	// defines tabs
	public static CreativeTabs itemsTab;
	public static CreativeTabs blocksTab;
	public static CreativeTabs toolsTab;
	public static CreativeTabs armorTab;
	
	public static void initialiseTabs()
	{
		// creates tabs. Can be exapanded to use one tab to create all 4.. Will probably do at some point.
		itemsTab = new CreativeTabItem("itemsTab");
		blocksTab = new CreativeTabBlock("blocksTab");
		toolsTab = new CreativeTabTools("toolsTab");
		armorTab = new CreativeTabArmor("armorTab");
	}
}
