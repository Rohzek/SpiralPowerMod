package com.Rohzek.achievements;

import com.Rohzek.item.SPItems;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class SPAchievements 
{
	public static AchievementPage spAchievementsPage;
	
	public static Achievement coreDrillPickupFirstTime;
	public static Achievement whatIsThisPower;
	public static Achievement powerUpYourSpirals;
	
	public static void mainRegistry()
	{
		createAchievments();
		registerAchievmentPage();
	}
	
	public static void createAchievments()
	{
		coreDrillPickupFirstTime = new Achievement("achievement.coreDrill", "coreDrillAchievement", 0, 0, SPItems.coreDrill, (Achievement)null).setSpecial().registerStat();
		whatIsThisPower = new Achievement("achievement.spiralDroplet", "rawSpiralAchievement", 0, 2, SPItems.spiralDroplet, coreDrillPickupFirstTime).registerStat();
		powerUpYourSpirals = new Achievement("achievement.spiralChunk", "spiralChunkAchievement", 2, 2, SPItems.spiralPowerChunk, whatIsThisPower).registerStat();
		
		
	}
	
	public static void registerAchievmentPage()
	{
		spAchievementsPage = new AchievementPage(" Spiral Power", coreDrillPickupFirstTime, 
													whatIsThisPower, powerUpYourSpirals);
		AchievementPage.registerAchievementPage(spAchievementsPage);
	}
}
