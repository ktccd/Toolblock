

package me.ktccd.toolblocks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.util.config.Configuration;

public class toolReader {

	private Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	private String message;
	private int hurt; //damage
	// time to make/read the config file
	public Configuration myConfig;
	public Configuration secondConfig;

	public toolblocks plugin;

	toolReader(toolblocks instance) {
		plugin = instance;
	}

	public void add(String block, String item) {
		Set<String> items;
		if (map.containsKey(block)) {
			items = map.get(block);
		} else {
			items = new HashSet<String>();
		}
		items.add(item);
		map.put(block, items);
	}

	// Simple method to get the tools
	public Set<String> getTools(String m, Byte b) {
		if (map.containsKey(m+"_"+b)) { //Is there a specified datavalue for this block?
			return new HashSet<String>(map.get(m+"_"+b));
		} else if  (map.containsKey(m)){ //Ok then, is there a block at all in here?
			return new HashSet<String>(map.get(m));
		} else { //Ok, crap, let's return null.
			return null;
		}
	}
	
	
	public String getMessage(){
		return message;
	}
	
	public int getDamage() {
		return hurt;
	}

	public void loadConfig() {
		File configFile = new File(plugin.getDataFolder(), "toolconfig.yml");
		File secondconfigFile = new File(plugin.getDataFolder(), "secondConfig.yml");
		if (configFile.exists()) {
			myConfig = new Configuration(configFile);
			myConfig.load();
			// Time to read in the blocks and the items I guess
			Iterator<String> i = myConfig.getKeys().iterator();
			while (i.hasNext()) {
				String current = i.next().toUpperCase();
				String data = myConfig.getString("" + current);
				String[] tools = data.split(" ");
				for (String s : tools) {
					add(current, s);
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
					plugin.log.info("[toolblocks]Error making default configuration.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (secondconfigFile.exists()) {
			secondConfig = new Configuration(secondconfigFile);
			secondConfig.load();
			// Time to read in the blocks and the items I guess
			hurt=secondConfig.getInt("Damage", 0);
			message=secondConfig.getString("Message","You do not have the right tool to break this");
		} else {
			try {
				plugin.getDataFolder().mkdir();
				secondconfigFile.createNewFile();
				secondConfig = new Configuration(secondconfigFile);
				secondConfig.setHeader("#Simple yml file to change the default message and damage when using the wrong tool.");
				secondConfig.setProperty("Message", "You do not have the right tool to break this.");
				secondConfig.setProperty("Damage", 0);
				if (secondConfig.save()) {
					plugin.log.info("[toolblocks]No second config file detected! This plugin will generate a default one though.");
					hurt=secondConfig.getInt("Damage", 0);
					message=secondConfig.getString("Message","You do not have the right tool to break this");
				} else {
					plugin.log.info("[toolblocks]Error making default second configuration.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}