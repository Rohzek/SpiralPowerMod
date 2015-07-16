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

	// make digging too.
	protected SpiralDrill(ToolMaterial material) 
	{
		super(material);
		this.setDamage(new ItemStack(SPTools.spiralDrill), -16383);
		this.setFull3D();
		
		this.setUnlocalizedName("spiralDrill");
		this.setTextureName(RefStrings.MODID + ":spiralDrill");
		this.setCreativeTab(SPCreativeTabs.toolsTab);
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) 
	{
	    return ImmutableSet.of("pickaxe", "spade");
	}
	
	private static Set effectiveAgainst = Sets.newHashSet(new Block[] 
	{
		 Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, 
		 Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, 
		 Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, 
		 Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin
	});
	
	@Override
	public boolean func_150897_b(Block block) 
	{
	    return effectiveAgainst.contains(block) ? true : super.func_150897_b(block);
	}
	
	@Override
	public float func_150893_a(ItemStack stack, Block block) 
	{
	    if (block.getMaterial() == Material.wood || block.getMaterial() == Material.vine || block.getMaterial() == Material.plants)
	        return this.efficiencyOnProperMaterial;
	    return effectiveAgainst.contains(block) ? this.efficiencyOnProperMaterial : super.func_150893_a(stack, block);
	}
}
