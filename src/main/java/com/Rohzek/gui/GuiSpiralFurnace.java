package com.Rohzek.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.Rohzek.inventory.ContainerSpiralFurnace;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.tileentity.TileEntitySpiralFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Handles the GUI for our Furnace
@SideOnly(Side.CLIENT)
public class GuiSpiralFurnace extends GuiContainer
{
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(RefStrings.RESOURCEID + "textures/gui/container/furnace.png");
	private TileEntitySpiralFurnace furnace;
	
	public GuiSpiralFurnace(InventoryPlayer invPlayer, TileEntitySpiralFurnace tileEntity) 
	{
		super(new ContainerSpiralFurnace(invPlayer, tileEntity));
		
		this.furnace = tileEntity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	// This only draws the name at the top of the GUI, and Inventory at the bottom
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String name = this.furnace.hasCustomInventoryName() ? this.furnace.getInventoryName() : I18n.format(this.furnace.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 118, this.ySize - 96 + 2, 4210752);
	}
	
	// We draw all of our background elements here
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) 
	{
		// Texture color: r g b a
		GL11.glColor4f(1f, 1f, 1f, 1f);
		
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		
		// Calculate where in the texture png file the GUI starts
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
		
		// If we're actively cooking something we want to animate the arrow and burn meter
		if(this.furnace.isBurning())
		{
			int i1 = furnace.getBurnTimeRemainingScaled(15);
			//       Where to draw from left, fromm top        ,area for image from x, from y
			drawTexturedModalRect(guiLeft + 8, guiTop + 58 - i1, 176, 14 - i1, 14, i1);
		}
		
		// This handles the progress arrow in the middle
		int k = this.furnace.getCookProgressScaled(18);
		drawTexturedModalRect(guiLeft + 82, guiTop + 34, 190, 0, k + 1, 60);
	}

}
