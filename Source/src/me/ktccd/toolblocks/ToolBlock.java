package me.ktccd.toolblocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ToolBlock {
	
	public int getPick(Player player) {
		switch (player.getItemInHand().getTypeId()){
		case 257:
			return 4; //iron pick
		case 270:
			return 1; //wooden pick
		case 274:
			return 3; //stone pick
		case 278:
			return 5; //diamond pick
		case 285:
			return 2; //gold pick
		default:
			return 0;
		}
	}

	public int getAxe(Player player) {
		switch (player.getItemInHand().getTypeId()) {
		case 258:
			return 4; //iron axe
		case 271:
			return 1; //wooden axe
		case 275:
			return 3; //stone axe
		case 279:
			return 5; //diamond axe
		case 286:
			return 2; //gold axe
		default:
			return 0;
		}
	}
	
	
	public int getBreakable(Player player, Block block){
		switch (block.getTypeId()) {
		case 1:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 4:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 14:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 15:
			if (getPick(player)>2){
				return 1;
			}
			else{
				return 0;
			}
		case 16:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 21:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 22:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 23:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 24:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}	
		case 41:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 42:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 43:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 44:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 45:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 48:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 49:
			if (getPick(player)>4){
				return 1;
			}
			else{
				return 0;
			}
		case 56:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 57:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 61:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 62:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 67:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 70:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 71:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 73:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 74:
			if (getPick(player)>3){
				return 1;
			}
			else{
				return 0;
			}
		case 87:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		case 89:
			if (getPick(player)>0){
				return 1;
			}
			else{
				return 0;
			}
		default:
			return 1;
		
		}
	}
}
