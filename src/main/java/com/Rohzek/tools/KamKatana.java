package com.Rohzek.tools;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;

import net.minecraft.item.ItemSword;

public class KamKatana extends ItemSword
{

	// applies material to item
	public KamKatana(ToolMaterial material) 
	{
		super(material);
		
		// gives tool style "3D" rendering
		this.setFull3D();
		// Gives unlocalized name
		this.setUnlocalizedName("kamKatana");
		// Sets texutre based on that name
		this.setTextureName(RefStrings.RESOURCEID + "kamKatana");
		// Sets creative tab
		this.setCreativeTab(SPCreativeTabs.toolsTab);
	}

}
