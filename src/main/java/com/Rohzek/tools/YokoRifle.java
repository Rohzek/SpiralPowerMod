package com.Rohzek.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.entity.EntityBullet;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;

// Fires on click like alpha/beta bow.. fires multiple ammo types based on a switchable mode
// As a note: All debug checks are specified !onRemote... else it would print it once for client, once for server.. Don't need both to verify/debug
public class YokoRifle extends Item
{
	// Creating icons; we'll register them later.
	// Icon for default state
	private IIcon defaultIcon;
	// Icon for bullet firing state
	private IIcon bulletIcon;
	// Icon for spiral droplet firing state
	private IIcon spowerIcon;
	// Icon for arrow firing state
	private IIcon arrowIcon;
	
	// Initializations for item state.
	public YokoRifle()
	{
		super();
		
		// Dont allow stacking
		this.setMaxStackSize(1);
		// Tool style rendering isntead of basic item
		this.setFull3D();
		// Sets a creative tab
		this.setCreativeTab(SPCreativeTabs.toolsTab);
		// Sets an unlocalized name for the item
		this.setUnlocalizedName("yokoRifle");
	}
	
	// Allow change modes if "using" item... this means you have to look at a block.. May change later.
	// Not sure what these ints and floats are exactly.. super documentation wasn't clear or changed from default var_func_1_ style names
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int w, int x, int y, int z, float a, float b, float c)
    {
		// We have to pull the current mode out of the itemStack's NBT
		int currentMode = getMode(itemStack);
		
		// We do this on the server only
		if(!world.isRemote)
		{
			// Shift clicking so you don't fire while changing
			if(player.isSneaking())
			{
				// calculate what the next mode would be before changing
				int changeMode = getNextMode(itemStack);
					
				// Debug print what mode we're switching to
				if(RefStrings.DEBUG)
				{
					if(changeMode == 0)
					{
						System.out.println("Safe Mode");
					}
					else if(changeMode == 1)
					{
						System.out.println("Bullet Mode");
					}
					else if(changeMode == 2)
					{
						System.out.println("Power Mode");
					}
					else if(changeMode == 3)
					{
						System.out.println("Arrow Mode");
					}
				}
				
				// Set to the next mode
				setMode(itemStack, changeMode);
			}
		}
		
        return false;
    }
	
	// Sets right click action when not looking at block... General "use" function
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		// We pull the current mode from the itemStack's NBT data
		int currentMode = getMode(itemStack);
		
		// Dont allow firing while sneaking so you don't waste a shot while changing modes
		if(player.isSneaking())
		{	
			
		}
		
		// Try firing if player isn't sneaking
		else
		{
			// If player is in creative
			if (player.capabilities.isCreativeMode)
			{
				// Default fire mode is safety, no fire call here
				if (currentMode == 0)
				{
					// Maybe we can play a click sound here? if I get a nice one?
					if(RefStrings.DEBUG)
					{
						if(!world.isRemote)
						{
							System.out.println("You're on safe mode, can't fire!");
						}
					}
				}
				else
				{
					// We have to pass an ammotype here since we didn't make a seperate function to call
					// but it shouldn't matter since it's in creative mode anyway
					// It'll ignore ammotypes and fire the same way regardless of mode.
					fireGun(itemStack, SPItems.yokoBullet, "Rifle", world, player);
				}
			}
			
			// If player isn't in creative mode, continue as normal
			else if (!player.capabilities.isCreativeMode)
			{
				// Default fire mode is safety, no fire call here
				if (currentMode == 0)
				{
					// Maybe we can play a click sound here? if I get a nice one?
					if(RefStrings.DEBUG)
					{
						if(!world.isRemote)
						{
							System.out.println("You're on safe mode, can't fire!");
						}
					}
				}
							
				// If player is using Bullets as ammo
				if (player.inventory.hasItem(SPItems.yokoBullet) && currentMode == 1)
				{
					fireGun(itemStack, SPItems.yokoBullet, "Rifle", world, player);
				}
				
				// If player is using Raw Power as ammo
				if (player.inventory.hasItem(SPItems.spiralDroplet) && currentMode == 2)
				{
					fireGun(itemStack, SPItems.spiralDroplet, "Rifle", world, player);
				}
				
				// If player is using arrows as ammo
				if (player.inventory.hasItem(Items.arrow) && currentMode == 3)
				{
					fireGun(itemStack, Items.arrow, "Bow", world, player);
				}
			}
		}
		
		return itemStack;
	 }
	 
	// We don't need this anymore but I'll leave it here... Was empty in super class too.
		@Override
		public void onUpdate(ItemStack itemStack, World world, Entity entity, int x, boolean a)
		{
			
		}
		
	// Instead of having this code 4 times it's now moved to one function we can call.
	// Format: item to consume for firing, if it's a Rifle or Bow, world, player
	private void fireGun(ItemStack itemStack, Item ammoType, String modeType, World world, EntityPlayer player)
	{
		// If in creative mode set ludacris damage yo!
		if(player.capabilities.isCreativeMode)
		{
			// Set ludacris damage
			this.setMaxDamage(9999);
		}
		else if(!player.capabilities.isCreativeMode)
		{
		}
		
		// choose sound type
		if(modeType.equals("Rifle"))
		{
			world.playSoundAtEntity(player, RefStrings.MODID + ":rifle_fire_normal", 0.5F, 1.0F);
		}
		else if(modeType.equals("Bow"))
		{
			world.playSoundAtEntity(player, RefStrings.MODID + ":rifle_fire_arrow", 0.5F, 1.0F);
		}
		
		// Spawn a projectile
		if (!world.isRemote)
		{
			// Fires our projectile based on mode selection type
			if(modeType.equals("Rifle"))
			{
				world.spawnEntityInWorld(new EntityBullet(world, player));
			}
			else if(modeType.equals("Bow"))
			{
				world.spawnEntityInWorld(new EntityArrow(world, player, 50f));
			}
			
		}
		
		// Consume ammo type and give back item if appliciable.
		if(!player.capabilities.isCreativeMode)
		{
			if(modeType.equals("Rifle"))
			{
				if(ammoType == SPItems.yokoBullet)
				{
					player.inventory.consumeInventoryItem(ammoType);
					
					// Give an empty shell casing back
					player.inventory.addItemStackToInventory(new ItemStack(SPItems.spentCasing));
				}
				else if(ammoType == SPItems.spiralDroplet)
				{
					player.inventory.consumeInventoryItem(ammoType);
				}
			}
			else if(modeType.equals("Bow"))
			{
				player.inventory.consumeInventoryItem(ammoType);
			}
		}
		
		else
		{
			// Debug check
			if(RefStrings.DEBUG)
			{
				if(!world.isRemote)
				{
					System.out.println("We're in creative mode... I shouldn't be consuming any ammo types!");
				}
			}
		}
		
		// Debug check
		if(RefStrings.DEBUG)
		{
			if(!world.isRemote)
			{
				if(!player.capabilities.isCreativeMode)
				{
					System.out.println("Gun is doing ~" + getOurDamage(itemStack) + " damage");
				}
				
				else
				{
					System.out.println("Gun is doing a ludacris 9999 damage!");
				}
				
			}
		}
	}
	
	// Could have had this where it's used in onRightClick but made it feel messy with all the stuff going on... Made it it's own thing.
	private int getOurDamage(ItemStack itemStack)
	{
		int projectileDamage;
		int changeNum = getMode(itemStack);
		
		// Actual mode change code.
		switch(changeNum)
		{
			case 0:
				// We're in default mode
				projectileDamage = 0; // Just a formality for completeness.. Shouldn't be able to fire in default mode
				break;
							
			case 1:
				// We're in Bullet mode
				projectileDamage = 25;
				break;
							
			case 2:
				// We're in Spiral Power mode
				projectileDamage = 15;
				break;
							
		case 3:
				// We're in Arrow mode
				projectileDamage = 10;
				break;
							
			default:
				// Go back to the begining, and set us to default mode.
				projectileDamage = 0;
				break;
		}
		
		return projectileDamage;
	}
	
	// This calculates what the next mode will be based on current mode
	public int getNextMode(ItemStack itemStack)
	{
		// pull current mode from NBT data
		int currentMode = getMode(itemStack);
		
		// Add one
		currentMode = currentMode + 1;
		
		// Valid options are 0, 1 ,2 and 3.
		if(currentMode > 3)
		{
			currentMode = 0;
		}
		
		return currentMode;
	}
	
	// Gets current mode from NBT data
	public int getMode(ItemStack itemStack)
	{
		int changeNum;
		NBTTagCompound retrieveData;
		retrieveData = itemStack.getTagCompound();
		
		// If there is an NBTTagCompound and the key exists
		if(retrieveData != null && retrieveData.hasKey("changeNum"))
		{
			changeNum = retrieveData.getInteger("changeNum");
		}
		// If there's an NBTTagCompound but the key doesn't exist yet..
		else if(retrieveData != null)
		{
			retrieveData.setInteger("changeNum", 0);
			changeNum = retrieveData.getInteger("changeNum");
		}
		// If there somehow isn't an NBTTagCompound go ahead and create one and apply default mode to gun.
		else
		{
			itemStack.setTagCompound(new NBTTagCompound());
			setMode(itemStack, 0);
			changeNum = 0;
		}
		
		return changeNum;
	}
	
	// Changes mode in NBT data
	public void setMode(ItemStack itemStack, int numToSet)
	{
		int changeNum;
		
		NBTTagCompound saveData;
		saveData = itemStack.getTagCompound();
		
		if(saveData != null)
		{
			saveData.setInteger("changeNum", numToSet);
		}
		
		else
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}
	}
	
	// Registers icons with unique names
	 @Override
	 public void registerIcons(IIconRegister iconRegister)
	 {
		 // default mode texture
			defaultIcon = iconRegister.registerIcon(RefStrings.MODID + ":yokoRifleDefault");
		 // bullet mode texture
			bulletIcon = iconRegister.registerIcon(RefStrings.MODID + ":yokoRifleBullet");
		 // spower mode texture
			spowerIcon = iconRegister.registerIcon(RefStrings.MODID + ":yokoRifleSpiral");
		 // arrow mode texture
			arrowIcon = iconRegister.registerIcon(RefStrings.MODID + ":yokoRifleArrow");
	 }
	 
	 // Instead of putting this code twice below, I just made it once and call it in both.
	 private IIcon determineIcon(ItemStack itemStack)
	 {
		 IIcon textureToUse;
		 int changeIcon = getMode(itemStack);
		 
		 switch(changeIcon)
		 {
			case 0:
				textureToUse = defaultIcon;
				break;
			 		
			 case 1:
				textureToUse = bulletIcon;
				break;
			 		
			case 2:
				textureToUse = spowerIcon;
				break;
			 		
			case 3:
				textureToUse = arrowIcon;
				break;
			 		
			default:
				textureToUse = defaultIcon;
				break;
		}
		 	
		 	return textureToUse;
	 }
	 
	 // Changes icon based on current mode. Not sure if for item in hand or in inventory.. but both are required
	 @Override
	 public IIcon getIconIndex(ItemStack itemStack)
	 {
		return determineIcon(itemStack);
	 }
	 
	// Changes icon based on current mode. Not sure if for item in hand or in inventory.. but both are required
	 @Override
	 public IIcon getIcon(ItemStack itemStack, int pass)
	 { 
		 return determineIcon(itemStack);
	 }
}
