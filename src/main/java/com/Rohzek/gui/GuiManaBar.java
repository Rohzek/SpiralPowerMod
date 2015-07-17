package com.Rohzek.gui;

import java.lang.annotation.ElementType;

import org.lwjgl.opengl.GL11;

import com.Rohzek.lib.RefStrings;
import com.Rohzek.player.SPExtendedPlayerStats;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class GuiManaBar extends Gui
{
	private static Minecraft mc;
	
	private static final ResourceLocation texturepath = new ResourceLocation(RefStrings.MODID, "textures/gui/mana_bar.png");

	public GuiManaBar(Minecraft mc)
	{
		super();
		
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)
		{
			return;
		}
	
		SPExtendedPlayerStats props = SPExtendedPlayerStats.get(this.mc.thePlayer);
		
		if (props == null || props.getMaxMana() == 0)
		{
			return;
		}
	
		int xPos = 2;
		int yPos = 2;
	
		// int xPos = event.resolution.getScaledWidth() / 2;
		// int yPos = event.resolution.getScaledHeight() / 2;
	
		// int xPos = (event.resolution.getScaledWidth() + textureWidth) / 2;
		// int yPos = (event.resolution.getScaledHeight() + textureHeight) / 2;
	
		GL11.glDisable(GL11.GL_LIGHTING);
		
		
		this.mc.getTextureManager().bindTexture(texturepath);
	
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		drawTexturedModalRect(xPos, yPos, 0, 0, 56, 9);
		int manabarwidth = (int)(((float) props.getCurrentMana() / props.getMaxMana()) * 49);
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 9, manabarwidth, 3);
	
		String s = "   " + props.getCurrentMana() + "/" + props.getMaxMana();
		yPos += 10;
		this.mc.fontRenderer.drawString(s, xPos + 1, yPos, 0);
		this.mc.fontRenderer.drawString(s, xPos - 1, yPos, 0);
		this.mc.fontRenderer.drawString(s, xPos, yPos + 1, 0);
		this.mc.fontRenderer.drawString(s, xPos, yPos - 1, 0);
		this.mc.fontRenderer.drawString(s, xPos, yPos, 8453920);
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		
	}
}