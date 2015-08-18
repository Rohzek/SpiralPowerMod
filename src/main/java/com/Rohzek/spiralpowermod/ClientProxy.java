package com.Rohzek.spiralpowermod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import com.Rohzek.entity.EntityBullet;
import com.Rohzek.gui.GuiManaBar;
import com.Rohzek.renderer.RenderBullet;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

// Registers client side only things
public class ClientProxy extends ServerProxy
{
	// Gets Minecraft thread
	private final Minecraft mc = Minecraft.getMinecraft();
	
	// Render client side only stuff here
	public void registerRenderInfo()
	{
		// Registers our GUI bar
		MinecraftForge.EVENT_BUS.register(new GuiManaBar(Minecraft.getMinecraft()));
		
		// Render the custom bullet projectile entity
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
	}
	
	// Same with armors. We want to register renderer on client side only.
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	// Returns a side-appropriate EntityPlayer for use during message handling checking for client
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return (ctx.side.isClient() ? mc.thePlayer : super.getPlayerEntity(ctx));
	}
}
