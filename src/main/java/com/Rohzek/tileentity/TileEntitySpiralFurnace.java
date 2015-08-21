package com.Rohzek.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.Rohzek.block.SPBlocks;
import com.Rohzek.block.SpiralFurnace;
import com.Rohzek.crafting.SmeltingRecipes;
import com.Rohzek.item.SPItems;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySpiralFurnace extends TileEntity implements ISidedInventory
{
	
	private static final int[] slotsTop = new int[]{0};
	private static final int[] slotsBottom = new int[]{2, 1};
	private static final int[] slotsSides = new int[]{1};
	
	private ItemStack[] itemStacks = new ItemStack[3];
	
	public int furnaceBurnTime;
	public int currentBurnTime;
	public int furnaceCookTime;
	
	private String furnaceName;

	
	public void furnaceName(String string)
	{
		this.furnaceName = string;
	}
	
	@Override
	public int getSizeInventory() 
	{
		return this.itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return this.itemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) 
	{
		if(this.itemStacks[par1] != null)
		{
			ItemStack itemStack;
			
			if(this.itemStacks[par1].stackSize <= par2)
			{
				itemStack = this.itemStacks[par1];
				this.itemStacks[par1] = null;
				return itemStack;
			}
			else
			{
				itemStack = this.itemStacks[par1].splitStack(par2);
				
				if(this.itemStacks[par1].stackSize == 0)
				{
					this.itemStacks[par1] = null;
				}
				
				return itemStack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		if(this.itemStacks[slot] != null)
		{
			ItemStack itemStack = this.itemStacks[slot];
			
			this.itemStacks[slot] = null;
			return itemStack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack itemStack) 
	{
		this.itemStacks[par1] = itemStack;
		
		if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
		{
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() 
	{
		return this.hasCustomInventoryName() ? this.furnaceName : "Spiral Furnace";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return this.furnaceName != null && this.furnaceName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList("Items", 10);
		this.itemStacks = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); ++i)
		{
			NBTTagCompound comp = list.getCompoundTagAt(i);
			byte byte0 = comp.getByte("slot");
			
			if(byte0 >= 0 && byte0 < this.itemStacks.length)
			{
				this.itemStacks[byte0] = ItemStack.loadItemStackFromNBT(comp);
			}
		}
		
		this.furnaceBurnTime = compound.getShort("burnTime");
		this.furnaceCookTime = compound.getShort("cookTime");
		
		this.currentBurnTime = getItemBurnTime(this.itemStacks[1]);
		
		if(compound.hasKey("customName", 8))
		{
			this.furnaceName = compound.getString("customName");
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setShort("burnTime", (short) this.furnaceBurnTime);
		compound.setShort("cookTime", (short) this.furnaceCookTime);
		
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.itemStacks.length; ++i)
		{
			if(this.itemStacks[i] != null)
			{
				NBTTagCompound comp = new NBTTagCompound();
				comp.setByte("slot", (byte) i);
				this.itemStacks[i].writeToNBT(comp);
				list.appendTag(comp);
			}
		}
		
		compound.setTag("items", list);
		
		if(this.hasCustomInventoryName())
		{
			compound.setString("customName", this.furnaceName);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		return this.furnaceCookTime * par1 / 200;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		if(this.currentBurnTime == 0)
		{
			this.currentBurnTime = 200;
		}
		
		return this.furnaceBurnTime * par1 / this.currentBurnTime;
	}
	
	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}
	
	@Override
	public void updateEntity()
	{
		boolean flag0 = this.furnaceBurnTime > 0;
		boolean flag1 = false;
		
		if(this.furnaceBurnTime > 0)
		{
			--this.furnaceBurnTime;
		}
		
		if(!this.worldObj.isRemote)
		{
			if(this.furnaceBurnTime == 0 && this.canSmelt())
			{
				this.currentBurnTime = this.furnaceBurnTime = getItemBurnTime(this.itemStacks[1]);
				
				if(this.furnaceBurnTime > 0)
				{
					flag1 = true;
					
					if(this.itemStacks[1] != null)
					{
						--this.itemStacks[1].stackSize;
						
						if(this.itemStacks[1].stackSize == 0)
						{
							this.itemStacks[1] = itemStacks[1].getItem().getContainerItem(this.itemStacks[1]);
						}
					}
				}
			}
			
			if(this.isBurning() && this.canSmelt())
			{
				++this.furnaceCookTime;
				
				if(this.furnaceCookTime == 200)
				{
					this.furnaceCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			
			else
			{
				this.furnaceCookTime = 0;
			}
		}
		
		if(flag0 != this.furnaceBurnTime > 0)
		{
			flag1 = true;
			SpiralFurnace.updateBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
		
		if(flag1)
		{
			this.markDirty();
		}
	}
	
	private boolean canSmelt()
	{
		if(this.itemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemStack = SmeltingRecipes.smelting().getSmeltingResult(this.itemStacks[0]);
			
			if(itemStack == null)
			{
				return false;
			}
			
			if(this.itemStacks[2] == null)
			{
				return true;
			}
			
			if(!this.itemStacks[2].isItemEqual(itemStack))
			{
				return false;
			}
			
			int result = itemStacks[2].stackSize + itemStack.stackSize;
			
			return result < getInventoryStackLimit() && result <= this.itemStacks[2].getMaxStackSize();
		}
	}
	
	public void smeltItem()
	{
		if(this.canSmelt())
		{
			ItemStack itemStack = SmeltingRecipes.smelting().getSmeltingResult(this.itemStacks[0]);
			
			if(this.itemStacks[2] == null)
			{
				this.itemStacks[2] = itemStack.copy();
			}
			
			else if(this.itemStacks[2].getItem() == itemStack.getItem())
			{
				this.itemStacks[2].stackSize += itemStack.stackSize;
			}
			
			--this.itemStacks[0].stackSize;
			
			if(this.itemStacks[0].stackSize == 0)
			{
				this.itemStacks[0] = null;
			}
		}
	}
	
	// Here you can choose what to burn and how long!
	public static int getItemBurnTime(ItemStack itemStack)
	{
		if(itemStack == null)
		{
			return 0;
		}
		else
		{
			Item item = itemStack.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
			{
				Block block = Block.getBlockFromItem(item);
				
				if(block == SPBlocks.spiralPowerBlock)
				{
					return 300;
				}
				
				else if(item == SPItems.spiralPowerChunk)
				{
					return 1600;
				}
				
				return GameRegistry.getFuelValue(itemStack);
			}
		}
		return 0;
	}
	
	public static boolean isItemFuel(ItemStack itemStack)
	{
		return getItemBurnTime(itemStack) > 0;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5d, (double) this.yCoord + 0.5d,(double) this.zCoord + 0.5d) <= 64.0d;
	}

	@Override
	public void openInventory() 
	{}

	@Override
	public void closeInventory() 
	{}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack itemStack) 
	{
		return par1 == 2 ? false : par1 == 1 ? isItemFuel(itemStack) : true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1) 
	{
		return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack itemStack, int par3) 
	{
		return this.isItemValidForSlot(par1, itemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack itemStack, int par3) 
	{
		return par3 != 0 || par1 != 1 || itemStack.getItem() == Items.bucket;
	}

}
