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

@SideOnly(Side.CLIENT)
public class GuiSpiralFurnace extends GuiContainer
{
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(RefStrings.MODID + ":" + "/textures/gui/container/furnace.png");
	private TileEntitySpiralFurnace furnace;
	
	public GuiSpiralFurnace(InventoryPlayer invPlayer, TileEntitySpiralFurnace tileEntity) 
	{
		super(new ContainerSpiralFurnace(invPlayer, tileEntity));
		this.furnace = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String string = this.furnace.hasCustomInventoryName() ? this.furnace.getInventoryName() : I18n.format(this.furnace.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(string, this.xSize / 2 - this.fontRendererObj.getStringWidth(string), 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) 
	{
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
		int i1;
		
		if(this.furnace.isBurning())
		{
			i1 = this.furnace.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}
		
		i1 = this.furnace.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 +1, 16);
	}

}
