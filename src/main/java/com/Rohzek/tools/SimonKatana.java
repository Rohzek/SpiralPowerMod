package com.Rohzek.tools;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;

import net.minecraft.item.ItemSword;

public class SimonKatana extends ItemSword
{
	// applies material to item
	public SimonKatana(ToolMaterial material) 
	{
		super(material);
		
		// gives tool style "3D" rendering
		this.setFull3D();
		// Sets unlocalized name
		this.setUnlocalizedName("simonKatana");
		// Sets texture based on that name
		this.setTextureName(RefStrings.RESOURCEID + "simonKatana");
		// Sets creative tab for it
		this.setCreativeTab(SPCreativeTabs.toolsTab);
	}

}
