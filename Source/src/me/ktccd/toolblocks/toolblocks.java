package me.ktccd.toolblocks;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class toolblocks extends JavaPlugin {

	public static PermissionHandler permissionHandler;
	public static PermissionHandler Permissions;

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// Kudos to captainawesome7 for helping make permission-checking simple                         //
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// And to winsock, who helped me lots with making the customisable config (and code cleaning :D)//
	//////////////////////////////////////////////////////////////////////////////////////////////////
	private static boolean UsePermissions;

	public static boolean canUseReplace(Player p) {
		if (UsePermissions) {
			return toolblocks.Permissions.has(p, "toolblock.override");
		}
		return p.isOp();
	}

	private final toolBlockListener blockListener = new toolBlockListener(this);

	public Logger log;

	public Configuration myConfig;

	private toolReader read = new toolReader(this);

	// Just my preference to use getters instead of making public variables
	public toolReader getReader() {
		return read; // Return the toolReader
	}

	@Override
	public void onDisable() {
		log.info("[toolblocks] is disabled.");
	}

	@Override
	public void onEnable() {
		log = Bukkit.getServer().getLogger();
		log.info("[toolblocks] is enabled.");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		setupPermissions();
		read.loadConfig();
		log.info("[toolblocks] loaded config.");

	}

	private void setupPermissions() {
		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
		if (toolblocks.Permissions == null) {
			if (test != null) {
				UsePermissions = true;
				toolblocks.Permissions = ((Permissions) test).getHandler();
				System.out.println("[SimpleReplace] Permissions system detected!");
			} else {
				log.info("[SimpleReplace] Permission system not detected, defaulting to OP");
				UsePermissions = false;
			}
		}
	}
}