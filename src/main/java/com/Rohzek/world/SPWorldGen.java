package com.Rohzek.world;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class SPWorldGen 
{
	// Registers world gen to main registry
	public static void mainRegistry()
	{
		initialiseWorldGen();
	}
	
	public static void initialiseWorldGen()
	{
		registerWorldGen(new SpiralOre(), 1);
	}
	
	// Registered elsewhere
	public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability)
	{
		GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
	}
}
