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

public class YokoRifle extends Item
{
	public YokoRifle()
	{
		super();
		
		this.setMaxStackSize(1);
		this.setMaxDamage(578);
		this.setFull3D();
		
		this.setCreativeTab(SPCreativeTabs.toolsTab);
		this.setUnlocalizedName("yokoRifle");
		this.setTextureName(RefStrings.MODID + ":yokoRifle");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
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
