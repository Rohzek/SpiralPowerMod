package com.Rohzek.achievements;

import com.Rohzek.item.SPItems;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class SPAchievements 
{
	// Creates the tab on the Achievements Page
	public static AchievementPage spAchievementsPage;
	
	// The achievements themselves
	public static Achievement coreDrillPickupFirstTime;
	public static Achievement whatIsThisPower;
	public static Achievement powerUpYourSpirals;
	
	// Calls the other two functions in one easy call. Is called in main class
	public static void mainRegistry()
	{
		createAchievments();
		registerAchievmentPage();
	}
	
	// Creating Achievements.
	
	public static void createAchievments()
	{
		// Format: Registration, Unlocalized Name, locations on page X, Y values, ItemStack to be used for icon, previous if any Achievement required to get
		// .setSpecial optional to make it glow, have to run .registerStat to finalize.. Will error without
		
		coreDrillPickupFirstTime = new Achievement("achievement.coreDrill", "coreDrillAchievement", 0, 0, SPItems.coreDrill, (Achievement)null).setSpecial().registerStat();
		whatIsThisPower = new Achievement("achievement.spiralDroplet", "rawSpiralAchievement", 0, 2, SPItems.spiralDroplet, coreDrillPickupFirstTime).registerStat();
		powerUpYourSpirals = new Achievement("achievement.spiralChunk", "spiralChunkAchievement", 2, 2, SPItems.spiralPowerChunk, whatIsThisPower).registerStat();
		
		
	}
	
	public static void registerAchievmentPage()
	{
		// As far as I can tell you want the page to be created after you have achievements to populate it to avoid errors
		spAchievementsPage = new AchievementPage(" Spiral Power", coreDrillPickupFirstTime, 
													whatIsThisPower, powerUpYourSpirals);
		AchievementPage.registerAchievementPage(spAchievementsPage);
	}
}
