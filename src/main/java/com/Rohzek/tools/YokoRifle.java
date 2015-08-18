package com.Rohzek.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.entity.EntityBullet;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;

// Fires on click like alpha/beta bow.. fires multiple ammo types
public class YokoRifle extends Item
{
	// Damages based on what you're currently firing. May change.
	private int projectileDamage;
	
	// Temporary number to handle changing modes
	int changeNum = 0;
	
	// Enum for calculating mode specfic settings
	private enum Mode
	{
		DEFAULT,
		BULLET,
		SPOWER,
		ARROW
	}
	
	// Mode we're currently in
	private Mode modeCurrent;
	// temporary variable to change mode
	private Mode changeMode;
	// boolean to disable infinte cycle on right click
	private boolean canChangeMode = true;
	
	// Icon for default state
	private IIcon defaultIcon;
	// Icon for bullet firing state
	private IIcon bulletIcon;
	// Icon for spiral droplet firing state
	private IIcon spowerIcon;
	// Icon for arrow firing state
	private IIcon arrowIcon;
	
	// Initialize a fer vars in constructor.
	public YokoRifle()
	{
		super();
		
		// Firing mode is default.. by.. default.
		modeCurrent = Mode.DEFAULT;
		// Dont stack
		this.setMaxStackSize(1);
		// tool style rendering isntead of basic item
		this.setFull3D();
		// sets a creative tab
		this.setCreativeTab(SPCreativeTabs.toolsTab);
		// sets an unlocalized name
		this.setUnlocalizedName("yokoRifle");
	}
	
	// Sets right click action.. which is fire gun, maybe
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		// Dont allow firing while sneaking so you don't waste a shot while changing modes
		if(player.isSneaking())
		{
			// If it's been a few seconds since it's been done, you can switch modes.
			if(canChangeMode)
			{
				if(!world.isRemote)
				{
					changeNum++;
				}
			}
			
			// Actual change code
			switch(changeNum)
			{
				case 0:
					// We're in default mode
					modeCurrent = Mode.DEFAULT;
					projectileDamage = 0;
					canChangeMode = false;
					break;
					
				case 1:
					// We're in Bullet mode
					modeCurrent = Mode.BULLET;
					projectileDamage = 25;
					canChangeMode = false;
					break;
					
				case 2:
					// We're in Spiral Power mode
					modeCurrent = Mode.SPOWER;
					projectileDamage = 15;
					canChangeMode = false;
					break;
					
				case 3:
					// We're in Arrow mode
					modeCurrent = Mode.ARROW;
					projectileDamage = 10;
					canChangeMode = false;
					break;
					
				default:
					// Go back to the begining, and set use to default mode.
					modeCurrent = Mode.DEFAULT;
					projectileDamage = 0;
					changeNum = 0;
					canChangeMode = false;
					break;
			}
			
			// Debug printout for testing
			if(RefStrings.DEBUG)
			{
				if(!world.isRemote)
				{
					System.out.println("Mode number: " + changeNum);
				}
			}
			
		}
		
		// Fire if player isn't sneaking
		else
		{
			// If player is in creative, require no ammo
			if (player.capabilities.isCreativeMode && modeCurrent != Mode.DEFAULT)
			{
				// Assign ludacris damage
				this.setMaxDamage(9999);
				// Sound like a rifle
				world.playSoundAtEntity(player, RefStrings.MODID + ":rifle_fire_normal", 0.5F, 1.0F);
				// Spawn a projectile
				if (!world.isRemote)
				{
					// Uses custom Sepcial Arrow projectile
					world.spawnEntityInWorld(new EntityBullet(world, player));
				}
				
				// Debug check on current gun damage
				if(RefStrings.DEBUG)
				{
					if(!world.isRemote)
					{
						System.out.println("Gun is doing " + projectileDamage + " damage");
					}
				}
			}
			
			// Default fire mode is safety
			if (modeCurrent == Mode.DEFAULT)
			{
				if(RefStrings.DEBUG)
				{
					if(!world.isRemote)
					{
						System.out.println("You're on safe mode, can't fire!");
					}
				}
			}
						
			// If player is using Bullets as ammo
			if (player.inventory.hasItem(SPItems.yokoBullet) && modeCurrent == Mode.BULLET)
			{
				// Should just about be able to kill most mobs in one hit.. still pretty OP yo.
				this.setMaxDamage(projectileDamage);
				// Sound like a rifle
				world.playSoundAtEntity(player, RefStrings.MODID + ":rifle_fire_normal", 0.5F, 1.0F);
				// Spawn a projectile
				if (!world.isRemote)
				{
					// Fires our custom projectile
					world.spawnEntityInWorld(new EntityBullet(world, player));
				}
				
				// Debug check on current gun damage
				if(RefStrings.DEBUG)
				{
					if(!world.isRemote)
					{
						System.out.println("Gun is doing " + projectileDamage + " damage");
					}
				}
				
				// A check to stop ammo consumption while in creative mode.
				if(!player.capabilities.isCreativeMode)
				{
					// Consume the bullet.. 
					player.inventory.consumeInventoryItem(SPItems.yokoBullet);
					
					// Give an empty shell back
					player.inventory.addItemStackToInventory(new ItemStack(SPItems.spentCasing));
				}
			}
			
			// If player is using Spiral Power as ammo
			if (player.inventory.hasItem(SPItems.spiralDroplet) && modeCurrent == Mode.SPOWER)
			{
				// Better than arrows... not bullet levels though.
				this.setMaxDamage(projectileDamage);
				// Sound like a rifle
				world.playSoundAtEntity(player, RefStrings.MODID + ":rifle_fire_normal", 0.5F, 1.0F);
				// Spawn a projectile
				if (!world.isRemote)
				{
					// Fires our custom projectile
					world.spawnEntityInWorld(new EntityBullet(world, player));
				}
				
				// Debug check on current gun damage
				if(RefStrings.DEBUG)
				{
					if(!world.isRemote)
					{
						System.out.println("Gun is doing " + projectileDamage + " damage");
					}
				}
				
				// A check to stop ammo consumption while in creative mode.
				if(!player.capabilities.isCreativeMode)
				{
					// Consume the droplet..
					player.inventory.consumeInventoryItem(SPItems.spiralDroplet);
				}
			}
			
			// If player is using arrows as ammo
			if (player.inventory.hasItem(Items.arrow) && modeCurrent == Mode.ARROW)
			{
				// Deals maximum amount of damage a bow can ever do
				this.setMaxDamage(projectileDamage);
				// Sound like a rifle
				world.playSoundAtEntity(player, RefStrings.MODID + ":rifle_fire_arrow", 0.5F, 1.0F);
				// Spawn a projectile
				if (!world.isRemote)
				{
					// fires an arrow
					world.spawnEntityInWorld(new EntityArrow(world, player, 50f));
				}
				
				// Debug check on current gun damage
				if(RefStrings.DEBUG)
				{
					if(!world.isRemote)
					{
						System.out.println("Gun is doing " + projectileDamage + " damage");
					}
				}
				
				// A check to stop ammo consumption while in creative mode.
				if(!player.capabilities.isCreativeMode)
				{
					// Consume the arrow..
					player.inventory.consumeInventoryItem(Items.arrow);
				}
			}
		}
		
		return itemStack;
	 }
	 
	// Change modes if "using" item... this means you have to look at a block.
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int w, int x, int y, int z, float a, float b, float c)
    {
		if(!world.isRemote)
		{
			if(RefStrings.DEBUG)
			{
				System.out.println("Item Used!");
			}
			
			canChangeMode = true;
		}
		
		
        return false;
    }
	
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
	 
	 @Override
	 public IIcon getIconIndex(ItemStack thisItem)
	 {
		 int textureNumber = modeCurrent.ordinal();
		 IIcon textureToUse;
		 	
		 switch(textureNumber)
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
	 
	 @Override
	 public IIcon getIcon(ItemStack stack, int pass)
	 {
		 int textureNumber = modeCurrent.ordinal();
		 
		 IIcon textureToUse;
		 	
		 switch(textureNumber)
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
}
