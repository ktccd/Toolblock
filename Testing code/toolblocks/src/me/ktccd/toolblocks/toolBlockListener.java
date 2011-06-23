package me.ktccd.toolblocks;

import java.util.logging.Logger;
import me.ktccd.toolblocks.ToolBlock;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class toolBlockListener extends BlockListener{
	public static toolblocks plugin;
	toolBlockListener(toolblocks instance){
		plugin = instance;
	}
	
	Logger log = Logger.getLogger("Minecraft");
	
	public void onBlockBreak(BlockBreakEvent event){
		ToolBlock testTool = new ToolBlock();
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if (testTool.getBreakable(player, block)==false){
			if (!toolblocks.canUseReplace(player)){
				event.setCancelled(true);
				log.info(player.getName()+" failed to break: "+block.getTypeId());
			}
			else {
				log.info("Letting "+player.getName()+" override, breaking "+block.getTypeId());
			}
		}
	}
}
