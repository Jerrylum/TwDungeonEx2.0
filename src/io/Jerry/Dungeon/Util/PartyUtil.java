package io.Jerry.Dungeon.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.Jerry.Dungeon.Party;

public class PartyUtil {
	public static List<Party> list = new ArrayList<Party>();

	public static Party getTeam(Player p){
		for(Party py : list){
			if(py.list.contains(p.getName())){
				return py;
			}
		}
		return null;
	}
	
	public static void sendMessage(List<String> list, String... msg) {
		Player p;
		for(String str : list){
			p = Bukkit.getPlayer(str);
			if(p == null){
				continue;
			}
			for(String msg2 : msg){
				p.sendMessage("¡±3Party> ¡±f" + msg2);
			}
			p.playSound(p.getLocation(), "note.pling", 1, 1);
		}
	}
}
