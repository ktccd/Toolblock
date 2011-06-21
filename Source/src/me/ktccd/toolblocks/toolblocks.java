package me.ktccd.toolblocks;

import java.util.logging.Logger;
import me.ktccd.toolblocks.toolBlockListener;

import org.bukkit.entity.Player;
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
		log.info("[toolblocks] is enabled.");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		setupPermissions();
		
		
		
	}
	public void onDisable(){
		log.info("toolblocks is disabled.");
	}
	
	////////////////////////////////////////////////////////////////////////////
	//Kudos to captainawesome7 for helping make permission-checking simple ^^.//
	////////////////////////////////////////////////////////////////////////////
	private static boolean UsePermissions;
    public static PermissionHandler Permissions;
	private void setupPermissions() {
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        if (this.Permissions == null) {
            if (test != null) {
                UsePermissions = true;
                this.Permissions = ((Permissions) test).getHandler();
                System.out.println("[SimpleReplace] Permissions system detected!");
            } else {
                log.info("[SimpleReplace] Permission system not detected, defaulting to OP");
                UsePermissions = false;
            }
        }
    }
    public static boolean canUseReplace(Player p) {
        if (UsePermissions) {
            return toolblocks.Permissions.has(p, "toolblock.override");
        }
        return p.isOp();
    }
}
