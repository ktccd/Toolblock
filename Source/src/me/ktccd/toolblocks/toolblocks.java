package me.ktccd.toolblocks;

import java.util.logging.Logger;
import me.ktccd.toolblocks.toolBlockListener;

import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class toolblocks extends JavaPlugin {
	private final toolBlockListener blockListener = new toolBlockListener(this);
	Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler permissionHandler;
	
	public void onEnable(){
		log.info("toolblocks is enabled.");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		setupPermissions();
	}
	public void onDisable(){
		log.info("toolblocks is disabled.");
	}
	
	@SuppressWarnings("static-access")
	private void setupPermissions() {
	      Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

	      if (this.permissionHandler == null) {
	          if (permissionsPlugin != null) {
	              this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	          }
	          else {
	              log.info("Permission system not detected, all runes ");
	          }
	      }
	}
	
}
