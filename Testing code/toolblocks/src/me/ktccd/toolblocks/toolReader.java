package me.ktccd.toolblocks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.util.config.Configuration;

public class toolReader{
	//setup logger
	Logger log = Logger.getLogger("Minecraft");
	public static toolblocks plugin;
	toolReader (toolblocks instance){
		plugin = instance;
	}
	toolReader (ToolBlock instance){
	}
	
	
	Map<Material, Set<Material>> map = new HashMap<Material, Set<Material>>();
	public void add(Material block, Material item) {
		Set<Material> items;
		if (map.containsKey(block)) {
	            items = map.get(block);
	        }
		else {
	        	items = new HashSet<Material>();
	        }
		items.add(item);
		map.put(block, items);
		System.out.println("Put "+item+" and key "+block+" in map, displaying map now:");
		System.out.println(map);
	}
	
	//time to make/read the config file
	public Configuration myConfig;
    public void loadConfig() {
        File configFile = new File(plugin.getDataFolder(), "toolconfig.yml");
        if (configFile.exists()) {
            myConfig = new Configuration(configFile);
            myConfig.load();
            //Time to read in the blocks and the items I guess
            Iterator<String> i = myConfig.getKeys().iterator();
            while (i.hasNext()){
            	String current = i.next();
            	String data = myConfig.getString("" + current);
            	String[] tools = data.split(" ");
            	for(String s : tools) {
            		add(Material.valueOf(current),Material.valueOf(s));
            	}
            }
            System.out.println("map full, displaying map:");
            System.out.println(map);
        }
        else {
            try {
            	plugin.getDataFolder().mkdir();
                configFile.createNewFile();
                myConfig = new Configuration(configFile);
                //Should probably make default values here, but alas, I fail that XD
                log.info("[toolblocks]No config file detected! This plugin won't work without it.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}