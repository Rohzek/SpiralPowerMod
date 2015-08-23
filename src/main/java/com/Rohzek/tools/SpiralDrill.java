package com.Rohzek.tools;

import java.util.Set;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.lib.RefStrings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class SpiralDrill extends ItemPickaxe
{

	// applies material to item
	protected SpiralDrill(ToolMaterial material) 
	{
		super(material);
		// gives tool style "3D" rendering
		this.setFull3D();
		// name of item
		this.setUnlocalizedName("spiralDrill");
		// gives texture based on unlocalized name
		this.setTextureName(RefStrings.RESOURCEID + "spiralDrill");
		// sets creative tab
		this.setCreativeTab(SPCreativeTabs.toolsTab);
	}
	
	// Adds additional tool classes to make it an omni tool.
	@Override
	public Set<String> getToolClasses(ItemStack stack) 
	{
	    return ImmutableSet.of("pickaxe", "spade", "axe");
	}
	
	// sets blocks that this is most effective against. We used pickaxe as a base and added on to that because pickaxes have the most stuff to deal with.
	private static Set effectiveAgainst = Sets.newHashSet(new Block[] 
	{
		 Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, 
		 Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, 
		 Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, 
		 Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin
	});
	
	// Not sure about this function... deobsfucated but not translated. Required for omnitool creation
	@Override
	public boolean func_150897_b(Block block) 
	{
	    return effectiveAgainst.contains(block) ? true : super.func_150897_b(block);
	}
	
	// also not sure about this but serves a similar purpose... Used to make additional effeincies.
	@Override
	public float func_150893_a(ItemStack stack, Block block) 
	{
	    if (block.getMaterial() == Material.wood || block.getMaterial() == Material.vine || block.getMaterial() == Material.plants)
	        return this.efficiencyOnProperMaterial;
	    return effectiveAgainst.contains(block) ? this.efficiencyOnProperMaterial : super.func_150893_a(stack, block);
	}
}
