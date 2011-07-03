

package me.ktccd.toolblocks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.util.config.Configuration;

public class toolReader {

	private Map<Material, Set<Material>> map = new HashMap<Material, Set<Material>>();

	// time to make/read the config file
	public Configuration myConfig;

	public toolblocks plugin;

	toolReader(toolblocks instance) {
		plugin = instance;
	}

	public void add(Material block, Material item) {
		Set<Material> items;
		if (map.containsKey(block)) {
			items = map.get(block);
		} else {
			items = new HashSet<Material>();
		}
		items.add(item);
		map.put(block, items);
	}

	// Simple method to get the tools
	public Set<Material> getTools(Material m) {
		if (map.containsKey(m)) {
			return new HashSet<Material>(map.get(m)); // Create a copy to
														// prevent editing
														// outside of the class
		} else {
			return null;
		}
	}

	public void loadConfig() {
		File configFile = new File(plugin.getDataFolder(), "toolconfig.yml");
		if (configFile.exists()) {
			myConfig = new Configuration(configFile);
			myConfig.load();
			// Time to read in the blocks and the items I guess
			Iterator<String> i = myConfig.getKeys().iterator();
			while (i.hasNext()) {
				String current = i.next();
				String data = myConfig.getString("" + current);
				String[] tools = data.split(" ");
				for (String s : tools) {
					add(Material.valueOf(current), Material.valueOf(s));
				}
			}
		} else {
			try {
				plugin.getDataFolder().mkdir();
				configFile.createNewFile();
				myConfig = new Configuration(configFile);
				myConfig.setHeader("#Simple yml file to control what blocks can be broken with tools/blocks", "#Format is: \"TOOL: MATERIAL MATERIAL ...\"", "#An example line to break stone is listed below.");
				myConfig.setProperty("STONE", "IRON_PICKAXE WOOD_PICKAXE");
				if (myConfig.save()) {
					plugin.log.info("[toolblocks]No config file detected! This plugin won't work without it, generated default config file");
					loadConfig();
				} else {
					plugin.log.info("[toolblocks]Error making default configuration");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}