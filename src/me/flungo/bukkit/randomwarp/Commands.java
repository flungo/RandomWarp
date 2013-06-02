/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.flungo.bukkit.randomwarp;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Fabrizio
 */
class Commands implements CommandExecutor {

	private RandomWarp plugin;

	public Commands(RandomWarp plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
		if (cmnd.getName().toLowerCase().equals("rwarp")) {
			return handleCommand(cs, strings);
		}
		return false;
	}

	private boolean handleCommand(CommandSender cs, String[] arg) {
		if (cs instanceof Player && !(plugin.getPermissions().isUser((Player) cs))) {
			cs.sendMessage(ChatColor.RED + "You don't have permisson to use that command");
			return true;
		}
		try {
			if (arg.length <= 0) {
				if (cs instanceof Player) {
					try {
						plugin.teleport((Player) cs, plugin.getConfig().getString("default"));
					} catch (InvalidAreaException ex) {
						cs.sendMessage("Could not teleport you. Contact server admin.");
						plugin.getLogger().log(Level.WARNING, "Default area {0} doesn't exists", ex.getMessage());
					}
					return true;
				} else {
					return false;
				}
			}
			switch (arg[0].toLowerCase()) {
				default:
					if (cs instanceof Player) {
						try {
							plugin.teleport((Player) cs, arg[0]);
						} catch (InvalidAreaException ex) {
							cs.sendMessage("Could not teleport you. Area \"" + arg[0] + "\" is undefined");
						}
						return true;
					}
			}
		} catch (InvalidWorldException ex) {
			cs.sendMessage("Could not teleport you. Contact server admin.");
			plugin.getLogger().log(Level.WARNING, "World {0} does not exists", ex.getMessage());
			return true;
		} catch (InvalidCoordinatesException ex) {
			cs.sendMessage("Could not teleport you. Contact server admin.");
			plugin.getLogger().log(Level.WARNING, "Problem with coordinates in area {0}", ex.getMessage());
			return true;
		} catch (NoLocationFoundException ex) {
			cs.sendMessage("A safe location could not be found. Please try again.");
			plugin.getLogger().log(Level.WARNING, "Was unable to find a safe location in area {0}", ex.getMessage());
			return true;
		}
		return false;
	}
}
