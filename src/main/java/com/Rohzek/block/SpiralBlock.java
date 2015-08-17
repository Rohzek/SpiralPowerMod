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
		
		// can be ignored.. metadata can store more info, we're not using it really.
	    this.metadata = metadata;
	    // Least amount of drops that can be dropped without enchantments
	    this.least_quantity = least_quantity;
	    // most drops that can be dropped without enchantments
	    this.most_quantity = most_quantity;
	    // Hardness of block; mining resistance
	    this.setHardness(10.0f);
	    // explosions resistance
	    this.setResistance(20.0f);
	    // block can glow with this, like redstone when the block is hit
	    this.setLightLevel(0.3f);
	    // the level of pickaxe required to mine it up, 3 is diamond, 4 is generally modded super picks.
	    // This is technically not required for this block as I have my own way of handling it, customized but is included for standardizing.
	    this.setHarvestLevel("pickaxe", 4);
		// the "unlocalized name" of the block
	    this.setBlockName("spiralBlock");
	    // name for the texture
	    this.setBlockTextureName(RefStrings.MODID + ":spiralBlock");
	    // creative tab it's registered to
	    this.setCreativeTab(SPCreativeTabs.blocksTab);
	    
	    
	    // Item required to break this block, this is custom and is not generally wanted on normal blocks!!!
	    this.drill = SPTools.spiralDrill;
	}
	
	// Two constructors covering various possibilities
	protected SpiralBlock(Material mat, int least_quantity, int most_quantity) 
	{
		this(mat, 0, least_quantity, most_quantity);
	}
	
	protected SpiralBlock(Material mat) 
	{
		this(mat, 1, 1);
	}
	
	// What item is dropped when you mine this block
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) 
	{
	    return SPItems.spiralDroplet;
	}

	// Covers damage to the block.. this is how the "stop mining and save progress" mod worked I think
	@Override
	public int damageDropped(int metadata) 
	{
	    return this.metadata;
	}

	// amount of items that can be dropped with enchantments
	@Override
	public int quantityDropped(int meta, int fortune, Random random) 
	{
	    if (this.least_quantity >= this.most_quantity)
	        return this.least_quantity;
	    return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	}
	
	// This makes it so that only item of type SpiralDrill can break this block.
	// This is highly custom for this block and is generally not wanted for normal blocks!!!!!
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer entityPlayer, World world, int x, int y, int z )
	{
		// Checks what item is held by player
		ItemStack heldStack = entityPlayer.getHeldItem();
		
		// If player is holding our drill then set the hardness to a mineable level
		if( heldStack != null && heldStack.getItem() instanceof SpiralDrill)
		{
			return super.getPlayerRelativeBlockHardness( entityPlayer, world, x, y, z );
		}
		// If player not holding drill, set block to unmineable
		return 0.0F;
	}
}
