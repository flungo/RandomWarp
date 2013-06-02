package me.flungo.bukkit.randomwarp;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Fabrizio
 */
public class RandomWarp extends JavaPlugin {

	private final Permissions permissions = new Permissions(this);
	public static RandomWarp plugin;
	private PluginDescriptionFile pdf;
	private PluginManager pm;
	private Random rand = new Random();

	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, pdf.getName() + " is now disabled");
	}

	@Override
	public void onEnable() {
		pdf = this.getDescription();
		permissions.setupPermissions();
		pm = getServer().getPluginManager();
		getCommand("rwarp").setExecutor(new Commands(this));
		getConfig().options().copyDefaults(true);
		saveConfig();
		getLogger().log(Level.INFO, pdf.getName() + " version " + pdf.getVersion() + " is enabled.");
	}

	public void reload() {
		onDisable();
		onEnable();
	}

	public void teleport(Player p, String area) throws InvalidAreaException, InvalidWorldException, InvalidCoordinatesException, NoLocationFoundException {
		p.teleport(randomLoc(area));
		p.setFallDistance(-getConfig().getInt("drop-height"));
	}

	public Location randomLoc(String area) throws InvalidAreaException, InvalidWorldException, InvalidCoordinatesException, NoLocationFoundException {
		if (!getConfig().contains("warps." + area)) {
			throw new InvalidAreaException(area);
		}
		int x1;
		int x2;
		if (getConfig().getInt("warps." + area + ".x1") < getConfig().getInt("warps." + area + ".x2")) {
			x1 = getConfig().getInt("warps." + area + ".x1");
			x2 = getConfig().getInt("warps." + area + ".x2");
		} else {
			x1 = getConfig().getInt("warps." + area + ".x2");
			x2 = getConfig().getInt("warps." + area + ".x1");
		}
		int z1;
		int z2;
		if (getConfig().getInt("warps." + area + ".z1") < getConfig().getInt("warps." + area + ".z2")) {
			z1 = getConfig().getInt("warps." + area + ".z1");
			z2 = getConfig().getInt("warps." + area + ".z2");
		} else {
			z1 = getConfig().getInt("warps." + area + ".z2");
			z2 = getConfig().getInt("warps." + area + ".z1");
		}
		if (x1 == x2 || z1 == z2) {
			throw new InvalidCoordinatesException(area);
		}

		World w = getServer().getWorld(getConfig().getString("warps." + area + ".world"));
		if (w == null) {
			throw new InvalidWorldException(getConfig().getString("warps." + area + ".world"));
		}
		double x;
		double z;
		double y;
		int attempts = 0;
		do {
			x = x1 + rand.nextInt(x2 - x1) + 0.5d;
			z = z1 + rand.nextInt(z2 - z1) + 0.5d;
			y = w.getHighestBlockYAt((int) x, (int) z) + 0.5d;
		} while (!checkBlock(w.getBlockAt((int) x, (int) y, (int) z))
				&& attempts++ < getConfig().getInt("attempts"));
		if (attempts >= getConfig().getInt("attempts") && !getConfig().getBoolean("always-teleport", true)) {
			throw new NoLocationFoundException(area);
		}
		y += getConfig().getInt("drop-height");
		return new Location(w, x, y, z);
	}

	private boolean checkBlock(Block b) {
		for (int i = 0; i < 3; i++) {
			Block cb = b.getRelative(0, -i, 0);
			getLogger().log(Level.FINE, cb.getType().toString());
			if (getConfig().getBoolean("avoid.water", true)
					&& (cb.getType().equals(Material.WATER)
					|| cb.getType().equals(Material.STATIONARY_WATER))) {
				return false;
			}
			if (getConfig().getBoolean("avoid.lava", true)
					&& (cb.getType().equals(Material.LAVA)
					|| cb.getType().equals(Material.STATIONARY_LAVA))) {
				return false;
			}
			if (getConfig().getBoolean("avoid.trees", true)
					&& cb.getType().equals(Material.LEAVES)) {
				return false;
			}
		}
		return true;
	}

	public Permissions getPermissions() {
		return permissions;
	}
}
