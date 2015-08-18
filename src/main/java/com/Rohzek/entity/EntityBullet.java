package com.Rohzek.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable
{
	// Can explode!
	private static final int explosionRadius = 1;
	
	private int projectileDamage;
	
	// We chose 10 for the default projectile damage because thats the maximum possible damage a bow can do
	public EntityBullet(World world)
	{
		super(world);
		projectileDamage = 10;
	}
	
	// We chose 10 for the default projectile damage because thats the maximum possible damage a bow can do
	public EntityBullet(World world, EntityLivingBase entity)
	{
		super(world, entity);
		projectileDamage = 10;
	}
	
	// Allows you to choose damage
	public EntityBullet(World world, EntityLivingBase entity, int damage)
	{
		super(world, entity);
		this.projectileDamage = damage;
	}
	 
	// If you want an explosive finale you can call this in onImpact
	private void explode(int explosionPower)
	{
		int bx = (int)posX;
		int by = (int)posY;
		int bz = (int)posZ;
		// This explosion is taken from the creeper
		worldObj.createExplosion(this, posX, posY, posZ, explosionPower, true);
	} 
	
	// This is used to draw particles as entity moves
	private void drawParticles(String particleName)
	{
		for (int i = 0; i < 10; i++)
		{
			double x = (double)(rand.nextInt(10) - 5) / 8.0D;
			double y = (double)(rand.nextInt(10) - 5) / 8.0D;
			double z = (double)(rand.nextInt(10) - 5) / 8.0D;
			worldObj.spawnParticle(particleName, posX, posY, posZ, x, y, z);
		}
	}
	
	// This is the velocity of the moving entity... No clue why it never got changed to getVelocity or something like that.
	// Default value is 1.5f... We wanna speed this up.
	// The faster it goes, the less accurate it is...
	@Override
	public float func_70182_d()
    {
        return 6.0F;
    }
	
	// Counts per tick
	@Override 
	public void onUpdate() 
	{ 
		super.onUpdate();
		
		// If 3 seconds has gone by without hitting anything
		if (ticksExisted > 60)
		{	
			// Then destroy the particle so it doesn't render forever
			setDead();
		}
		
		// You can draw particles with this. The example below is the super charged bow particles
		//drawParticles("crit");
	}
	 
	// We don't want gravity effecting this.. it's a super OP bullet!
	@Override
	protected float getGravityVelocity()
	{
		return 0.0F;
	}
	
	// When this hits things.. We wanna do stuff yo!
	@Override
	public void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition.entityHit != null)
		{
			movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), projectileDamage);
			
			// Kills particle so it doesn't render it forever
			setDead();
		}
	}
}
