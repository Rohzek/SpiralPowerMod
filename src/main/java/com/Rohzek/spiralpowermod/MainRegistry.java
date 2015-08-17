package com.Rohzek.spiralpowermod;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

import com.Rohzek.achievements.SPAchievements;
import com.Rohzek.armor.SPArmors;
import com.Rohzek.block.SPBlocks;
import com.Rohzek.crafting.CraftingManager;
import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.handlers.AchievementHandler;
import com.Rohzek.handlers.CoreDrillHandler;
import com.Rohzek.handlers.ExtendedPlayerStatsHandler;
import com.Rohzek.handlers.LivingFallEvent;
import com.Rohzek.handlers.SpiralDrillBreakHandler;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.network.PacketPipeline;
import com.Rohzek.tools.SPTools;
import com.Rohzek.world.SPWorldGen;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION)
public class MainRegistry 
{
	@Mod.Instance(RefStrings.MODID)
	
	public static MainRegistry spiralpowermod;
	
	@SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE)
	public static ServerProxy proxyServer;
	
	// Calling all the mainregistry functions from everything that needed to be registered.
	// also handles registers for handlers that needed to be registered on both sides.
	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent PreEvent)
	{
		// Creative Tabs
		SPCreativeTabs.initialiseTabs();
		// Blocks
		SPBlocks.mainRegistry();
		// Items
		SPItems.mainRegistry();
		// Tools
		SPTools.mainRegistry();
		// Armors
		SPArmors.mainRegistry();
		// Crafting Recipes
		CraftingManager.mainRegistry();
		// Ore Generation
		SPWorldGen.mainRegistry();
		// Achievements Handler
		FMLCommonHandler.instance().bus().register(new AchievementHandler());
		// Achievements
		SPAchievements.mainRegistry();
		// Messages
		
		// FML Event Busses
		FMLCommonHandler.instance().bus().register(new AchievementHandler()); // Achievements
		MinecraftForge.EVENT_BUS.register(new ExtendedPlayerStatsHandler());  // Player Stats
		MinecraftForge.EVENT_BUS.register(new CoreDrillHandler());            // Core Drill Checker
		MinecraftForge.EVENT_BUS.register(new SpiralDrillBreakHandler());     // Spiral Drill Breaking
		MinecraftForge.EVENT_BUS.register(new LivingFallEvent());             // Fall Damage tracker
		
		// Remember to register packets here!
		PacketPipeline.registerPackets();
		
		// Register Renders. As I understand it: THIS HAS TO BE DONE LAST. NO IFS ANDS OR ASSES
		// But uh... I could be wrong.
		proxyServer.registerRenderInfo();
	}
	
	// As far as I can tell nothing needs to be done in this, it's all done in PreLoad.
	@EventHandler
	public static void load(FMLInitializationEvent event)
	{
	}
	
	// As far as I can tell nothing needs to be done in this, it's all done in PreLoad.
	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent PostEvent)
	{
	}
}
