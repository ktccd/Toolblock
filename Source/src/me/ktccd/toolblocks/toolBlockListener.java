package me.ktccd.toolblocks;

import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class toolBlockListener extends BlockListener {
	
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
				return true;
			} else {
				return false;
			}
		} else {
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
				} else {
			}
		}
	}
}
