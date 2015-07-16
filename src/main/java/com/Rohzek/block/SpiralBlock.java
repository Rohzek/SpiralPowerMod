package com.Rohzek.block;

import java.util.Random;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.tools.SPTools;
import com.Rohzek.tools.SpiralDrill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpiralBlock extends Block
{
	private int metadata;
	private int least_quantity;
	private int most_quantity;
	private Item drill;
	
	protected SpiralBlock(Material mat, int metadata, int least_quantity, int most_quantity) 
	{
		super(mat);
		
	    this.metadata = metadata;
	    this.least_quantity = least_quantity;
	    this.most_quantity = most_quantity;
	    
	    this.setHardness(10.0f);
	    this.setResistance(20.0f);
	    this.setLightLevel(0.3f);
	    this.setHarvestLevel("pickaxe", 4);
		
	    this.setBlockName("spiralBlock");
	    this.setBlockTextureName(RefStrings.MODID + ":spiralBlock");
	    this.setCreativeTab(SPCreativeTabs.blocksTab);
	    
	    this.drill = SPTools.spiralDrill;
	}
	
	protected SpiralBlock(Material mat, int least_quantity, int most_quantity) 
	{
		this(mat, 0, least_quantity, most_quantity);
	}
	
	protected SpiralBlock(Material mat) 
	{
		this(mat, 1, 1);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) 
	{
	    return SPItems.spiralDroplet;
	}

	@Override
	public int damageDropped(int metadata) 
	{
	    return this.metadata;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) 
	{
	    if (this.least_quantity >= this.most_quantity)
	        return this.least_quantity;
	    return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	}
	// This makes it so that only item of type SpiralDrill can break this block.
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer entityPlayer, World world, int x, int y, int z ){
		ItemStack heldStack = entityPlayer.getHeldItem();
		if( heldStack != null && heldStack.getItem() instanceof SpiralDrill)
		{
			return super.getPlayerRelativeBlockHardness( entityPlayer, world, x, y, z );
		}
		return 0.0F;
	}
}
