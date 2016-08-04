package io.Jerry.Dungeon.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import io.Jerry.Dungeon.Party;
import io.Jerry.Dungeon.Games.Game;
import io.Jerry.Dungeon.Games.GameError;

public class GameUtil {
	public static List<Game> Game = new ArrayList<Game>();
	private static HashMap<Player,String> select = new HashMap<Player,String>();	
	private static HashMap<Player,String> select_End = new HashMap<Player,String>();
	public static List<OfflinePlayer> ban = new ArrayList<OfflinePlayer>();
	
//	public static boolean isInLocation(Location L, String Name){
//		if(Main.c.get("Game." + Name + ".In") != null){
//			if(!(L.getWorld().getName().equals(Main.c.getString("Game." + Name + ".In.W")) && 
//				L.getBlockX() == Main.c.getInt("Game." + Name + ".In.X") &&
//				L.getBlockY() == Main.c.getInt("Game." + Name + ".In.Y") && 
//				L.getBlockZ() == Main.c.getInt("Game." + Name + ".In.Z"))){
//
//				return false;
//			}
//		}
//		return true;
//	}

	public static String getSelect(Player p) {
		String str = select.get(p);
		return str != null ? ConfigUtil.hasGame(str) ? str : null : null;
	}
	
	public static String getSelectEnd(Player p) {
		return select_End.get(p);
	}
	
	public static void selectGame(Player p, String name) {
		select.put(p, name);
	}
	
	public static void selectEnd(Player p, String name) {
		select_End.put(p, name);
	}

	public static Game getGame(OfflinePlayer p) {
		for(Game g : Game){
			if(g.getCanJoinPlayers().contains(p)){
				return g;
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static void startGame(String str,Player p){
		List<OfflinePlayer> list = Lists.newArrayList();
		Party py = PartyUtil.getTeam(p);
		if(py != null){
			for(String name : py.list){
				list.add(Bukkit.getOfflinePlayer(name));
			}
		}else{
			list.add(p);
		}
		
		
		Game LastG = null;
		for(OfflinePlayer op : list){
			if(ban.contains(op)){
				TitleApi.sendBar(I18n.t("GameUtil.Info.Running"), p);
				return;
			}
			if(LastG == null){
				LastG = GameUtil.getGame(op);
			}
		}
		
		if(LastG != null){
			TitleApi.sendAction("{\"text\":\"¡±3Dun> ¡±f" + I18n.t("GameUtil.Info.Invite") + "\","
					+ "\"clickEvent\":"
					+ "{"
					+ "\"action\":\"run_command\",\"value\":\"/party play\""
					+ "}}",p);
			return;
		}
		
		ItemStack hand = p.getItemInHand();
		p.setItemInHand(null);
		
//		new Thread(){
//			public void run(){
				ban.addAll(list);
				System.out.println("New dungeon now is start to create!");
				
				try {
					Game G = new Game(str,p,list);
					G.Start();
								
					GameUtil.Game.add(G);
				} catch (Exception | GameError ex) {
					p.sendMessage("¡±3Dun> ¡±c" + ex.getMessage());
					p.setItemInHand(hand);
				}
				
				System.out.println("New dungeon now is stop to create!");
				ban.removeAll(list);
//			}
//		}.start();
	}
	
//	public static Location getSpawnLoc(World w, String Name){
//		if(Main.c.get("Game." + Name + ".X") == null){
//			return w.getSpawnLocation();
//		}else{
//		    return new Location(w,Main.c.getInt("Game." + Name + ".X"),Main.c.getInt("Game." + Name + ".Y"),Main.c.getInt("Game." + Name + ".Z"));
//        }
//	}
//	
//	public static Location getExitLoc(String Name){
//		return Main.c.get("Game." +  Name + ".Exit") != null ? new Location(
//				Bukkit.getWorld(Main.c.getString("Game." + Name + ".Exit.W")),
//				Main.c.getInt("Game." + Name + ".Exit.X"),
//				Main.c.getInt("Game." + Name + ".Exit.Y"),
//				Main.c.getInt("Game." + Name + ".Exit.Z")) : null;
//	}
//	
//	public static ItemStack getItem(String name){
//		return Main.c.getItemStack("Game." + name + ".Item");
//	}
//	
//	public static int getMix(String Name){
//		return Main.c.getInt("Game." + Name + ".Mix",0);
//	}
//	
//	public static int getMax(String Name){
//		return Main.c.getInt("Game." + Name + ".Max",0);
//	}
//	
//	public static int getTime(String Name){
//		return Main.c.getInt("Game." + Name + ".Time",20);
//	}
//	
//	public static List<String> getEntity(String Name){
//		return Main.c.getStringList("Game." + Name + ".Entity");
//	}


	
}
