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

// More logic behind our custom furnace.. This one handles increasing/decrementing stacks and smelting logic
public class TileEntitySpiralFurnace extends TileEntity implements ISidedInventory
{
	
	// Where the slots are
	private static final int[] slotsTop = new int[]{0};
	private static final int[] slotsBottom = new int[]{2, 1};
	private static final int[] slotsSides = new int[]{1};
	
	private ItemStack[] slots = new ItemStack[3];
	
	// how fast the furnace completes it's task
	public int furnaceSpeed = 250;
	
	public int burnTime;
	public int currentBurnTime;
	public int cookTime;
	
	private String furnaceName;

	
	public void furnaceName(String string)
	{
		this.furnaceName = string;
	}
	
	@Override
	public int getSizeInventory() 
	{
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return this.slots[slot];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) 
	{
		if(this.slots[par1] != null)
		{
			ItemStack itemStack;
			
			if(this.slots[par1].stackSize <= par2)
			{
				itemStack = this.slots[par1];
				this.slots[par1] = null;
				return itemStack;
			}
			else
			{
				itemStack = this.slots[par1].splitStack(par2);
				
				if(this.slots[par1].stackSize == 0)
				{
					this.slots[par1] = null;
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
		if(this.slots[slot] != null)
		{
			ItemStack itemStack = this.slots[slot];
			
			this.slots[slot] = null;
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
		this.slots[par1] = itemStack;
		
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
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		return this.cookTime * par1 / this.furnaceSpeed;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		if(this.currentBurnTime == 0)
		{
			this.currentBurnTime = this.furnaceSpeed;
		}
		
		return this.burnTime * par1 / this.currentBurnTime;
	}
	
	public boolean isBurning()
	{
		return this.burnTime > 0;
	}
	
	@Override
	public void updateEntity()
	{
		boolean flag0 = this.burnTime > 0;
		boolean flag1 = false;
		
		if(this.burnTime > 0)
		{
			--this.burnTime;
		}
		
		if(!this.worldObj.isRemote)
		{
			if(this.burnTime == 0 && this.canSmelt())
			{
				this.currentBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);
				
				if(this.burnTime > 0)
				{
					flag1 = true;
					
					if(this.slots[1] != null)
					{
						--this.slots[1].stackSize;
						
						if(this.slots[1].stackSize == 0)
						{
							this.slots[1] = slots[1].getItem().getContainerItem(this.slots[1]);
						}
					}
				}
			}
			
			if(this.isBurning() && this.canSmelt())
			{
				++this.cookTime;
				
				if(this.cookTime == 200)
				{
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			
			else
			{
				this.cookTime = 0;
			}
		}
		
		if(flag0 != this.burnTime > 0)
		{
			flag1 = true;
			SpiralFurnace.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
		
		if(flag1)
		{
			this.markDirty();
		}
	}
	
	// Checks if an Item can smelt.
	private boolean canSmelt()
	{
		if(this.slots[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemStack = SmeltingRecipes.smelting().getSmeltingResult(this.slots[0]);
			
			if(itemStack == null)
			{
				return false;
			}
			
			if(this.slots[2] == null)
			{
				return true;
			}
			
			if(!this.slots[2].isItemEqual(itemStack))
			{
				return false;
			}
			
			int result = slots[2].stackSize + itemStack.stackSize;
			
			return result < getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize();
		}
	}
	
	// Smelts the item
	public void smeltItem()
	{
		if(this.canSmelt())
		{
			ItemStack itemStack = SmeltingRecipes.smelting().getSmeltingResult(this.slots[0]);
			
			if(this.slots[2] == null)
			{
				this.slots[2] = itemStack.copy();
			}
			
			else if(this.slots[2].getItem() == itemStack.getItem())
			{
				this.slots[2].stackSize += itemStack.stackSize;
			}
			
			--this.slots[0].stackSize;
			
			if(this.slots[0].stackSize == 0)
			{
				this.slots[0] = null;
			}
		}
	}
	
	// Here you can choose what to burn and how long! Maybe where custom fuel goes for "machinery"?
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
				// Temp fuels... Later we don't want fuel at all.
				Block block = Block.getBlockFromItem(item);
				
				if(block == SPBlocks.spiralPowerBlock)
				{
					return 300;
				}
			}
			
			if(item == SPItems.spiralPowerChunk)
			{
				return 150;
			}
			
			return GameRegistry.getFuelValue(itemStack);
		}
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
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) 
	{
		return slot == 2 ? false : slot == 1 ? isItemFuel(itemStack) : true;
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
	
	@Override
	public void readFromNBT(NBTTagCompound comp)
	{
		super.readFromNBT(comp);
		
		NBTTagList list = comp.getTagList("items", 10);
		
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
			
			byte b0 = compound.getByte("slot");
			
			if(b0 >= 0 && b0 < this.slots.length)
			{
				this.slots[b0] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
		this.burnTime = (int)comp.getShort("burnTime");
		this.cookTime = (int)comp.getShort("cookTime");
		this.currentBurnTime = (int)comp.getShort("currentBurnTime");
		
		if(comp.hasKey("customName"))
		{
			this.furnaceName = comp.getString("customName");
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound comp)
	{
		super.writeToNBT(comp);
		
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.slots.length; i++)
		{
			if(this.slots[i] != null)
			{
				NBTTagCompound compound = new NBTTagCompound();
				
				compound.setByte("slot", (byte) i);
				
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		comp.setTag("items", list);
		
		comp.setShort("burnTime", (short)this.burnTime);
		comp.setShort("cookTime", (short)this.cookTime);
		comp.setShort("currentBurnTime", (short)this.currentBurnTime);
		
		if(this.hasCustomInventoryName())
		{
			comp.setString("customName", this.furnaceName);
		}
	}
}
