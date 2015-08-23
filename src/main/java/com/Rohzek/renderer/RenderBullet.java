package com.Rohzek.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.Rohzek.lib.RefStrings;
import com.Rohzek.models.ModelBullet;

// Renders bullet based on Model info. I don't know a whole lot about what this does.
public class RenderBullet extends Render
{
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.RESOURCEID + "textures/entity/bulletProjectile.png");
	
	private ModelBase model;
	 
	public RenderBullet()
	{
		model = new ModelBullet();
	}
	 
	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
	 
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick)
	{
		GL11.glPushMatrix();
		bindTexture(texture);
		GL11.glTranslated(x, y - 1.25D, z);
		
		// All should be 0 except last one... default size texture is  0.0625F
		model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0150F);
		
		GL11.glPopMatrix();
	}
}