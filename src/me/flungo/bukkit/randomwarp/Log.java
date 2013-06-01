package me.flungo.bukkit.randomwarp;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Log {
	private final Logger logger = Logger.getLogger("MineCraft");
	
	private static JavaPlugin plugin;
	
	public Log(JavaPlugin instance) {
		plugin = instance;
	}
	
	public void logMessage(String msg) {
		logMessage(msg, Level.INFO);
	}
	
	public void logMessage(String msg, Level level) {
		logger.log(level, "[{0}] {1}", new Object[]{plugin.getDescription().getName(), msg});
	}
}
