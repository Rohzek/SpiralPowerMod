package com.Rohzek.block;

import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.Rohzek.lib.RefStrings;
import com.Rohzek.spiralpowermod.MainRegistry;
import com.Rohzek.tileentity.TileEntitySpiralFurnace;
import com.Rohzek.util.LogHelper;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Can hold objects like a normal furnace... Will be getiing remodeled into a custom block I think... Who knows?
public class SpiralFurnace extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon front;
	
	private static boolean isBurning;
	private final boolean isActive;
	private final Random random = new Random();
	
	// We set material and hardness and resistance here.. We want them to be the same on active and inactive furnaces.
	protected SpiralFurnace(boolean isActive) 
	{
		super(Material.rock);
		this.isActive = isActive;
		this.setHardness(5.0f);
		this.setResistance(17.5f);
	}

	// blockIcon is the side texture
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon(RefStrings.RESOURCEID + "spiralFurnaceSide");
		this.front = register.registerIcon(RefStrings.RESOURCEID + (this.isActive ? "spiralFurnaceActive" : "spiralFurnaceInactive"));
		this.top = register.registerIcon(RefStrings.RESOURCEID + "spiralFurnaceTop");
	}
	
	// This is required to return the correct texture based on the rotation
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		// side == 3 ? this.front then the icon never has a texture...
		// Without Without meta == 0 it's possible to glitch a front onto two sides when placing diagonally.
		// Vanilla code doesn't have either.. so.. no clue how vanilla furnaces work.. :/
		return meta == 0 && side == 3 ? this.front : (side == 1 ? this.top : (side == 0 ? this.top : (side != meta ? this.blockIcon : this.front)));
	}
	
	// This opens the GUI on right click, on server side.
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			player.openGui(MainRegistry.spiralpowermod, RefStrings.SPIRALOVENGUIID, world, x, y, z);
			
			if(RefStrings.DEBUG)
			{
				LogHelper.log(player.getDisplayName() + " Spiral Furnace activated GUI");
			}
		}
		
		return true;
	}
	
	// Not sure which furnace this is for, active or inactive, but we need both anyway
	@Override
	public Item getItem(World world, int par2, int par3, int par4)
	{
		return Item.getItemFromBlock(SPBlocks.spiralFurnace);
	}
	
	// Not sure which furnace this is for, active or inactive, but we need both anyway
	@Override
	public Item getItemDropped(int par1, Random rand, int par3)
	{
		return Item.getItemFromBlock(SPBlocks.spiralFurnace);
	}
	
	// Whhen we place the block we need to find out the direction to make it face
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.direction(world, x, y, z);
	}
	
	// This calculates the direction.. I don't really understand it, it's all vanilla code copypasta
	private void direction(World world, int x, int y, int z)
	{
		if(!world.isRemote)
		{
			Block direction0 = world.getBlock(x, y, z - 1);
			Block direction1 = world.getBlock(x, y, z + 1);
			Block direction2 = world.getBlock(x - 1, y, z - 1);
			Block direction3 = world.getBlock(x + 1, y, z - 1);
			byte byte0 = 3;
			
			if(direction0.func_149730_j() && direction0.func_149730_j())
			{
				byte0 = 3;
			}
			
			if(direction1.func_149730_j() && direction1.func_149730_j())
			{
				byte0 = 2;
			}
			
			if(direction2.func_149730_j() && direction2.func_149730_j())
			{
				byte0 = 5;
			}
			
			if(direction3.func_149730_j() && direction3.func_149730_j())
			{
				byte0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, byte0, 2);
		}
	}
	
	// This gets the player that placed the block so we can rotate it to face them.
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		int direction = MathHelper.floor_double((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5d) & 3;
		
		if(direction == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		
		if(direction == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		
		if(direction == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		if(direction == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		
		if(itemStack.hasDisplayName())
		{
			((TileEntitySpiralFurnace) world.getTileEntity(x, y, z)).furnaceName(itemStack.getDisplayName());
		}
	}
	
	// This is how we set the block back and forth between burning and not burning.. It's not a simple texture flip.
	public static void updateBlockState(boolean burning, World world, int x, int y, int z)
	{
		int dir = world.getBlockMetadata(x, y, z);
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		isBurning = true;
		
		if(burning)
		{
			world.setBlock(x, y, z, SPBlocks.spiralFurnaceActive);
		}
		else
		{
			world.setBlock(x, y, z, SPBlocks.spiralFurnace);
		}
		
		isBurning = false;
		
		world.setBlockMetadataWithNotify(x, y, z, dir, 2);
		
		if(tileEntity != null)
		{
			tileEntity.validate();
			world.setTileEntity(x, y, z, tileEntity);
		}
	}
	
	// This is what happens when we break the block
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		if(!isBurning)
		{
			TileEntitySpiralFurnace tileEntitySPFurnace = (TileEntitySpiralFurnace)world.getTileEntity(x, y, z);
			
			if(tileEntitySPFurnace != null)
			{
				for(int i = 0; i < tileEntitySPFurnace.getSizeInventory(); ++i)
				{
					ItemStack itemStack = tileEntitySPFurnace.getStackInSlot(i);
					
					if(itemStack != null)
					{
						float f0 = this.random.nextFloat() * 0.6f + 0.1f;
						float f1 = this.random.nextFloat() * 0.6f + 0.1f;
						float f2 = this.random.nextFloat() * 0.6f + 0.1f;
						
						while(itemStack.stackSize > 0)
						{
							int j0 = this.random.nextInt(21) + 10;
							
							if(j0 > itemStack.stackSize)
							{
								j0 = itemStack.stackSize;
							}
							
							itemStack.stackSize -= j0;
							
							EntityItem entityItem = new EntityItem(world, (double)((float) x + f0), (double)((float) y + f1), (double) ((float) z + f2), new ItemStack(itemStack.getItem(), j0, itemStack.getItemDamage()));
						
							if(itemStack.hasTagCompound())
							{
								entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
							}
							
							float f3 = 0.025f;
							
							entityItem.motionX = (double)((float) this.random.nextGaussian() * f3);
							entityItem.motionY = (double)((float) this.random.nextGaussian() * 0.1f);
							entityItem.motionZ = (double)((float) this.random.nextGaussian() * f3);
							
							world.spawnEntityInWorld(entityItem);
						}
					}
				}
				
				world.func_147453_f(x, y, y, block);
			}
		}
		
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	// This is how we display the random fire textures while the furnace is burning.
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		if(this.isActive)
		{
			int dir = world.getBlockMetadata(x, y, z);
			
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = rand.nextFloat() * 0.6F - 0.3F;
			
            if (dir == 4)
            {
            	world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (dir == 5)
            {
            	world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (dir == 2)
            {
            	world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (dir == 3)
            {
            	world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            	world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
		}
	}

	// This is for redstone stuff.... not sure EXACTLY what it does...
    public boolean hasComparatorInputOverride()
    {
        return true;
    }
    
    // This is for redstone stuff.... not sure EXACTLY what it does...
    public int getComparatorInputOverride(World world, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(par2, par3, par4));
    }
	 
    // This actually spawns the tile entity into the world. Without it.. it'd just be a block.
	@Override
	public TileEntity createNewTileEntity(World world, int par2) 
	{
		
		return new TileEntitySpiralFurnace();
	}
}
