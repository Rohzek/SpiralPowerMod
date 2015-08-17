package com.Rohzek.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Fires on click like alpha/beta bow.. fires multiple ammo types
public class YokoRifle extends Item
{
	public YokoRifle()
	{
		super();
		
		// Dont stack
		this.setMaxStackSize(1);
		// Does enough damage to kill anything right now... Will scale damage based on ammo type later.
		this.setMaxDamage(578);
		// tool style rendering isntead of basic item
		this.setFull3D();
		// sets a creative tab
		this.setCreativeTab(SPCreativeTabs.toolsTab);
		// sets an unlocalized name
		this.setUnlocalizedName("yokoRifle");
		// looks for texutre based on unlocalized name
		this.setTextureName(RefStrings.MODID + ":yokoRifle");
	}
	
	// Sets right click action
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		// If player is in creative or has one of the ammo types
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(SPItems.spiralDroplet) || 
												player.inventory.consumeInventoryItem(SPItems.yokoBullet) || 
													player.inventory.consumeInventoryItem(Items.arrow))
		{
		world.playSoundAtEntity(player, RefStrings.MODID + ":rifle_fire", 0.5F, 1.0F);
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityArrow(world, player, 50f));
			}
		}
		return itemStack;
	}
}
