package com.Rohzek.util;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import com.Rohzek.lib.RefStrings;
import com.Rohzek.lib.Settings;

public class LogHelper
{
	
	private static Logger logger = Logger.getLogger(RefStrings.MODID);
	
    public static void log(Level logLevel, String string)
    {
        FMLLog.log(RefStrings.NAME, logLevel, string);
    }

    public static void all(String string)
    {
        log(Level.ALL, string);
    }

    public static void debug(String string)
    {
        log(Level.DEBUG, string);
    }

    public static void error(String string)
    {
        log(Level.ERROR, string);
    }

    public static void fatal(String string)
    {
        log(Level.FATAL, string);
    }

    public static void info(String string)
    {
        log(Level.INFO, string);
    }

    public static void off(String string)
    {
        log(Level.OFF, string);
    }

    public static void trace(String string)
    {
        if (!Settings.Debug.logTraceToInfo)
        {
            log(Level.TRACE, string);
        }
        else
        {
            log(Level.INFO, string);
        }
    }

    public static void warn(String string)
    {
        log(Level.WARN, string);
    }
}
