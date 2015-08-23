package com.Rohzek.lib;

// Defines common things such as MODID that are used literally ALL the time in one easy to reference and change if needed place.
public class RefStrings 
{
	public static final String MODID = "spiralpowermod";
	
	// Just like MODID but with : attached to save time while assigning resource locations
	public static final String RESOURCEID = "spiralpowermod:";
	
	// The name that gets seen in brackets when we log to the console. will look like [SPPWR]
	public static final String LOG = "SPPWR";
	
	public static final String NAME = "Spiral Power Mod";
	
	// We only have to change it here... MCMOD.info is gone and the ModMetadata is hardcoded to check here
	public static final String VERSION = "0.1.10";
	
	public static final String CLIENTSIDE = "com.Rohzek.spiralpowermod.ClientProxy";
	
	public static final String SERVERSIDE = "com.Rohzek.spiralpowermod.ServerProxy";
	
	
	// GUI IDs
	public static final int SPIRALOVENGUIID = 0;
	
	// One day may be able to be set by player from the config file but uh... They probably wont like it if they set it to true.
	// Is used by me to simplify and print things to console.
	public static final boolean DEBUG = true;
}
