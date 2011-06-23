package me.ktccd.toolblocks;

import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class toolBlockListener extends BlockListener {

	Logger log = Logger.getLogger("Minecraft");

	public toolblocks plugin;

	toolBlockListener(toolblocks instance) {
		plugin = instance;
	}

	public boolean getBreakable(Player player, Block block) {
		toolReader reader = plugin.getReader();
		// is the block in the map?
		
		Set<Material> tools = reader.getTools(block.getType());
		
		if (tools != null && !tools.isEmpty()) {
			if (tools.contains(player.getItemInHand().getType())) {
				log.info(block.getType()+" found in map, allowed tool, "+player.getItemInHand().getType()+", held, returning true");
				return true;
			} else {
				log.info(block.getType()+" found in map, unallowed tool, "+player.getItemInHand().getType()+", not held, returning false");
				return false;
			}
		} else {
			log.info("map did not contain: " + block.getType().toString());
			log.info(block.getType()+" not listed, returning true");
			return true;
		}
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if (getBreakable(player, block) == false) {
			if (!toolblocks.canUseReplace(player)) {
				event.setCancelled(true);
				log.info(player.getName() + " failed to break: " + block.getType());
			} else {
				log.info("Letting " + player.getName() + " override, breaking " + block.getType());
			}
		}
	}
}
