package com.Rohzek.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import com.Rohzek.tileentity.TileEntitySpiralFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// This is what gives the slots to the furnace and handles transferring slots, and the progress bar info being sent to client
public class ContainerSpiralFurnace extends Container
{

	private TileEntitySpiralFurnace spiralFurnace;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
	
	// We set 3 slots here
	public ContainerSpiralFurnace(InventoryPlayer player, TileEntitySpiralFurnace tileEntity)
	{
		this.spiralFurnace = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, 0, 56, 35));
		this.addSlotToContainer(new Slot(tileEntity, 1, 8, 62));
		this.addSlotToContainer(new SlotFurnace(player.player, tileEntity, 2, 116, 35));
		
		int i;
		
		for(i = 0; i < 3; ++i)
		{
			for(int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}
	
	// We initialize the progress bars with data
	@Override
	public void addCraftingToCrafters(ICrafting craft)
	{
		super.addCraftingToCrafters(craft);
		craft.sendProgressBarUpdate(this, 0, this.spiralFurnace.cookTime);
		craft.sendProgressBarUpdate(this, 1, this.spiralFurnace.burnTime);
		craft.sendProgressBarUpdate(this, 2, this.spiralFurnace.currentBurnTime);
	}
	
	// Sends the changes per tick
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting craft = (ICrafting) this.crafters.get(i);
			
			if(this.lastCookTime != this.spiralFurnace.cookTime)
			{
				craft.sendProgressBarUpdate(this, 0, this.spiralFurnace.cookTime);
			}
			
			if(this.lastBurnTime != this.spiralFurnace.burnTime)
			{
				craft.sendProgressBarUpdate(this, 1, this.spiralFurnace.burnTime);
			}
			
			if(this.lastItemBurnTime != this.spiralFurnace.currentBurnTime)
			{
				craft.sendProgressBarUpdate(this, 2, this.spiralFurnace.currentBurnTime);
			}
		}
		
		this.lastBurnTime = this.spiralFurnace.burnTime;
		this.lastCookTime = this.spiralFurnace.cookTime;
		this.lastItemBurnTime = this.spiralFurnace.currentBurnTime;
	}
	
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if(par1 == 0)
		{
			this.spiralFurnace.cookTime = par2;
		}
		
		if(par1 == 1)
		{
			this.spiralFurnace.burnTime = par2;
		}
		
		if(par1 == 2)
		{
			this.spiralFurnace.currentBurnTime = par2;
		}
	}
	
	// Sets if player can interact with.. I assume to detect sneak click?
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return this.spiralFurnace.isUseableByPlayer(player);
	}
	
	// Allows for shift click and maybe regularly placing items in? Not sure.
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemStack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack itemStack1 = slot.getStack();
			itemStack = itemStack1.copy();
			
			if(par2 == 2)
			{
				if(this.mergeItemStack(itemStack1, 3, 39, true))
				{
					return null;
				}
				slot.onSlotChange(itemStack1, itemStack);
			}
			else if(par2 != 1 && par2 != 0)
			{
				if(FurnaceRecipes.smelting().getSmeltingResult(itemStack) != null)
				{
					if(this.mergeItemStack(itemStack1, 0, 1, false))
					{
						return null;
					}
				}
				
				else if(TileEntitySpiralFurnace.isItemFuel(itemStack1))
				{
					if(!this.mergeItemStack(itemStack1, 1, 2, false)) // this is things?
					{
						return null;
					}
				}
				
				else if(par2 >= 3 && par2 <30)
				{
					if(!this.mergeItemStack(itemStack1, 30, 39, false))
					{
						return null;
					}
				}
				
				else if(par2 >= 30 && par2 < 39 && this.mergeItemStack(itemStack1, 3, 30, false))
				{
					return null;
				}
			}
			else if(!this.mergeItemStack(itemStack1, 3, 39, false))
			{
				return null;
			}
			
			if(itemStack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			
			else
			{
				slot.onSlotChanged();
			}
			
			if(itemStack1.stackSize == itemStack.stackSize)
			{
				return null;
			}
			slot.onPickupFromSlot(player, itemStack1);
		}
		
		return itemStack;
	}

}
