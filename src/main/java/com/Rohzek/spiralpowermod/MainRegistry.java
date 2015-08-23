package com.Rohzek.spiralpowermod;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import com.Rohzek.achievements.SPAchievements;
import com.Rohzek.armor.SPArmors;
import com.Rohzek.block.SPBlocks;
import com.Rohzek.crafting.CraftingManager;
import com.Rohzek.creativetabs.SPCreativeTabs;
import com.Rohzek.entity.EntityBullet;
import com.Rohzek.gui.SPGuiHandler;
import com.Rohzek.handlers.AchievementHandler;
import com.Rohzek.handlers.CoreDrillHandler;
import com.Rohzek.handlers.ExtendedPlayerStatsHandler;
import com.Rohzek.handlers.FallEvent;
import com.Rohzek.handlers.RespawningEvent;
import com.Rohzek.handlers.SpiralDrillBreakHandler;
import com.Rohzek.item.SPItems;
import com.Rohzek.lib.RefStrings;
import com.Rohzek.network.PacketPipeline;
import com.Rohzek.tools.SPTools;
import com.Rohzek.util.LoadMetaData;
import com.Rohzek.util.LogHelper;
import com.Rohzek.util.RecipeRemover;
import com.Rohzek.world.SPWorldGen;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

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
		LogHelper.log("Beginning Pre-Initialization");
		
		LogHelper.log("Loading MCMOD replacement info");
		
		// This has to load first! This is a replacement for our MCMOD.Info
		LoadMetaData.load(PreEvent);
		
		LogHelper.log("Registering Creative Tabs");
		// Creative Tabs
		SPCreativeTabs.initialiseTabs();
		
		LogHelper.log("Registering Blocks");
		// Blocks
		SPBlocks.mainRegistry();
		
		LogHelper.log("Registering Items");
		// Items
		SPItems.mainRegistry();
		
		LogHelper.log("Registering Tools");
		// Tools
		SPTools.mainRegistry();
		
		LogHelper.log("Registering Armor");
		// Armors
		SPArmors.mainRegistry();
		
		LogHelper.log("Registering Crafting Recipes");
		// Crafting Recipes
		CraftingManager.mainRegistry();
		
		LogHelper.log("Registering Ore Generation Information");
		// Ore Generation
		SPWorldGen.mainRegistry();
		
		LogHelper.log("Initializing Acheievement Handler");
		// Achievements Handler
		FMLCommonHandler.instance().bus().register(new AchievementHandler());
		
		LogHelper.log("Registering Achievements");
		// Achievements
		SPAchievements.mainRegistry();
		
		LogHelper.log("Registering Custom Entities");
		EntityRegistry.registerModEntity(EntityBullet.class, "BULLET", 0, spiralpowermod, 64, 10, true);
		
		LogHelper.log("Registering Custom Event Handlers");
		// FML Event Busses
		FMLCommonHandler.instance().bus().register(new AchievementHandler()); // Achievements
		MinecraftForge.EVENT_BUS.register(new ExtendedPlayerStatsHandler());  // Player Stats
		MinecraftForge.EVENT_BUS.register(new CoreDrillHandler());            // Core Drill Checker
		MinecraftForge.EVENT_BUS.register(new SpiralDrillBreakHandler());     // Spiral Drill Breaking
		MinecraftForge.EVENT_BUS.register(new FallEvent());                   // Fall Damage tracker
		MinecraftForge.EVENT_BUS.register(new RespawningEvent());             // Respawn Data Saver
		
		LogHelper.log("Registering Packets for SMP");
		// Remember to register packets here!
		PacketPipeline.registerPackets();
		
		LogHelper.log("Registering Render information.");
		// Register Renders. As I understand it: THIS HAS TO BE DONE LAST. NO IFS ANDS OR ASSES
		// But uh... I could be wrong.
		proxyServer.registerTileEntity();
		proxyServer.registerRenderInfo();
		
		LogHelper.log("Pre-Initialization Complete");
	}
	
	// As far as I can tell nothing needs to be done in this, it's all done in PreLoad.
	@EventHandler
	public static void load(FMLInitializationEvent event)
	{
		LogHelper.log("Beginning Initialization");
		
		LogHelper.log("Registering GUI Handlers");
		NetworkRegistry.INSTANCE.registerGuiHandler(spiralpowermod, new SPGuiHandler());
		//proxyServer.RegisterNetwork();
		
		LogHelper.log("Initialization Comeplete");
	}
	
	// As far as I can tell nothing needs to be done in this, it's all done in PreLoad.
	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent PostEvent)
	{}
}
