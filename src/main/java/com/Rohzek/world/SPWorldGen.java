package com.Rohzek.world;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class SPWorldGen 
{
	public static void mainRegistry()
	{
		initialiseWorldGen();
	}
	
	public static void initialiseWorldGen()
	{
		registerWorldGen(new SpiralOre(), 1);
	}
	
	public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability)
	{
		GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
	}
}
