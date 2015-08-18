package com.Rohzek.tools;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;

import net.minecraft.item.ItemSword;

public class ViralCleaver extends ItemSword
{
	// applies material to item
	public ViralCleaver(ToolMaterial material) 
	{
		super(material);
		
		// gives tool style "3D" rendering
		this.setFull3D();
		// Gives unlocalized name		
		this.setUnlocalizedName("viralCleaver");
		// Sets texture based on that name
		this.setTextureName(RefStrings.MODID + ":viralCleaver");
		// Sets creative tab for it
		this.setCreativeTab(SPCreativeTabs.toolsTab);
	}

}
