package io.Jerry.Dungeon.Handler;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import io.Jerry.Dungeon.Games.Game;
import io.Jerry.Dungeon.Games.StopCase;
import io.Jerry.Dungeon.Util.ConfigUtil;
import io.Jerry.Dungeon.Util.GameUtil;
import io.Jerry.Dungeon.Util.I18n;

public class GameHandler {
	public static boolean canContinue(Player p, String options){
		for(Game g : GameUtil.Game){
			if(g.getWorld().equals(p.getWorld()) && g.getCanJoinPlayers().contains((OfflinePlayer)p)){
				return ConfigUtil.getOptions(g.getName(), options);
			}
		}
		
		return true;
	}
	
	public static boolean canContinue(World w, String options){
		for(Game g : GameUtil.Game){
			if(g.getWorld().equals(w)){
				return ConfigUtil.getOptions(g.getName(), options);
			}
		}
		
		return true;
	}

	public static boolean respawn(PlayerDeathEvent e) {
		Game g = GameUtil.getGame(e.getEntity());
		if(g == null){
			return false;
		}
		
		Player p = e.getEntity();
		if(ConfigUtil.getOptions(g.getName(), "Respawn")){
			boolean Inventory = ConfigUtil.getOptions(g.getName(), "Inventory");

			p.setHealth(p.getMaxHealth());
			ItemStack[] a;
			ItemStack[] b;
			a = p.getInventory().getContents();
			b = p.getInventory().getArmorContents();
			p.getPlayer().teleport(g.getSpawn());
			if(Inventory){
				 p.getPlayer().getInventory().setContents(a);
				 p.getPlayer().getInventory().setArmorContents(b);
			}
		}else{
			p.sendMessage("¡±3" + I18n.t("GameHandler.Quit"));
			g.Ban(p);
		}
		
		for(StopCase sc : g.getCaseList()){
			if(sc.getType().equals("PD")){
				sc.add(null);
			}
		}
		return false;
	}

	public static void Case(String string,World w, String str) {
		for(Game g : GameUtil.Game){
			if(g.getWorld().equals(w)){
				for(StopCase sc : g.getCaseList()){
					if(sc.getType().equals(string)){
						sc.add(str);
					}
				}
			}
		}
	}
}
	