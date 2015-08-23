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

// This class contains a clusterfuck of things I don't quite understand and only achieved due to a tutorial. GL11 makes little sense to me.
// We still need to be able to hide it on will/ in creative mode.
public class GuiManaBar extends Gui
{
	// gets instance of minecraft
	private static Minecraft mc;
	
	// locates the texture inside the jar file
	private static final ResourceLocation texturepath = new ResourceLocation(RefStrings.MODID, "textures/gui/mana_bar.png");

	// Lets super call set up the basics from the GUI class
	public GuiManaBar(Minecraft mc)
	{
		super();
		
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		// Somewhere here is where we hide this bar in creative mode I'm sure... but haven't found it yet.
		// Not sure why this works... Sorry.
		if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)
		{
			return;
		}
	
		// Pulls player out of the current minecraft thread and uses it to get EPS class.
		// This works as the GUI class cannot be registered on server, only client.. So you can simply check the player of the current client.
		SPExtendedPlayerStats props = SPExtendedPlayerStats.get(this.mc.thePlayer);
		
		// If no player exists or max mana is 0 on initilization we don't need to draw a bar yet.. there's either no player or he doesn't have mana access yet.
		if (props == null || props.getMaxMana() == 0)
		{
			return;
		}
	
		// location for bar
		int xPos = 2;
		int yPos = 2;
	
		// int xPos = event.resolution.getScaledWidth() / 2;
		// int yPos = event.resolution.getScaledHeight() / 2;
	
		// int xPos = (event.resolution.getScaledWidth() + textureWidth) / 2;
		// int yPos = (event.resolution.getScaledHeight() + textureHeight) / 2;
	
		
		// Not sure why we disable lighting but it was recommended in tutorial? GL11 is one big mystery to me.
		GL11.glDisable(GL11.GL_LIGHTING);
		
		// Binds texture path to client minecraft texture manager
		this.mc.getTextureManager().bindTexture(texturepath);
	
		// Actual drawing of the GUI using GL11.
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		drawTexturedModalRect(xPos, yPos, 0, 0, 56, 9);
		
		// Gets overlay bar width based on current mana. Controls bluegreen bar, not container bar.
		int manabarwidth = (int)(((float) props.getCurrentMana() / props.getMaxMana()) * 49);
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 9, manabarwidth, 3);
	
		// font renderer and variables for number overlay
		String s = "   " + props.getCurrentMana() + "/" + props.getMaxMana();
		yPos += 10;
		this.mc.fontRenderer.drawString(s, xPos + 1, yPos, 0);
		this.mc.fontRenderer.drawString(s, xPos - 1, yPos, 0);
		this.mc.fontRenderer.drawString(s, xPos, yPos + 1, 0);
		this.mc.fontRenderer.drawString(s, xPos, yPos - 1, 0);
		this.mc.fontRenderer.drawString(s, xPos, yPos, 8453920);
		
		// Closing the GL11 blobk
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		
	}
}