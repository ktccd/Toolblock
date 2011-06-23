package me.ktccd.toolblocks;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ToolBlock {
	toolblocks plugin;
	ToolBlock(toolblocks plugin){
		this.plugin = plugin;
	}
	ToolBlock(){
	}
	
	
	public boolean getBreakable(Player player, Block block){
		toolReader reader = plugin.read;
		//is the block in the map?
		System.out.println("printing block.getType() "+block.getType());
		System.out.println("printing map before checking it:");
		System.out.println(reader.map);
		if (reader.map.containsKey(block.getType().toString())){
			Set<Material> mySet = reader.map.get(block.getType());
			if (mySet.contains(player.getItemInHand().getType())){
				System.out.println("Block found in map, allowed tool held, returning true");
				return true;
			}
			else {
				System.out.println("Block found in map, allowed tool not held, returning false");
				return false;
			}
		}
		else {
			System.out.println("map did not contain: "+block.getType().toString());
			System.out.println("No block denied, returning true");
			return true;
		}
	}
}