package io.Jerry.Dungeon.Command;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import io.Jerry.Dungeon.Aerry;
import io.Jerry.Dungeon.Util.ChatUtil;
import io.Jerry.Dungeon.Util.ConfigUtil;
import io.Jerry.Dungeon.Util.GameUtil;
import io.Jerry.Dungeon.Util.GuiUtil;
import io.Jerry.Dungeon.Util.TitleApi;

public class CMD implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if((sender.isOp() && sender instanceof Player) == false){
			return false;
		}

		Player p = (Player)sender;
		//----------------------------------------------------------------------------------------------
//		//DONE
//		if(args.length == 2 && args[0].equalsIgnoreCase("Goto")){
//			for(Game G : GameUtil.Game){
//				if(G.getWorld().getPlayers().contains(p)){
//					p.sendMessage("§3Dun> §f你正在使用其他副本");
//					return true;
//				}
//			}
//			
//			double startTime = System.currentTimeMillis();
//			
//			try {
//				Game G = new Game(args[1],null);
//				G.onGameStart(true, Arrays.asList(new String[]{p.getName()}));
//				GameUtil.Game.add(G);
//			} catch (GameError e) {
//				p.sendMessage("§3Dun> §f" + e.getMessage());
//			}
//			
//			double endTime   = System.currentTimeMillis();
//			double totalTime = endTime - startTime;
//			totalTime = totalTime/1000;
//			p.sendMessage("§3Dun> §f使用" + totalTime + "秒嘗試創建");
//			return true;
//		}
//		//----------------------------------------------------------------------------------------------
//
//		if(args.length == 1 && args[0].equalsIgnoreCase("End")){
//			boolean use = false;
//			for(Game G : GameUtil.Game){
//				if(G.getWorld().getPlayers().contains(p)){
//					double startTime = System.currentTimeMillis();
//					
//					G.onGameEnd();
//					GameUtil.Game.remove(G);
//					
//					double endTime   = System.currentTimeMillis();
//					double totalTime = endTime - startTime;
//					totalTime = totalTime/1000;
//					p.sendMessage("§3Dun> §f使用" + totalTime + "秒刪除");
//					
//					use = true;
//					break;
//				}
//			}
//			if(use == false){
//				p.sendMessage("§3Dun> §f你不在任何副本內");
//			}
//			return true;
//		}

//		if(args.length == 1 && args[0].equalsIgnoreCase("Spawn")){
//			for(Game G : GameUtil.Game){
//				if(G.getWorld().getPlayers().contains(p)){
//					if(Main.c.get("Game." + G.getName() + ".X") == null){//TODO
//						p.sendMessage("§3Dun> §f副本出生點沒有設定");
//					}else{
//						p.teleport(GameUtil.getSpawnLoc(G.getWorld(), G.getName()));
//					}
//					return true;
//				}
//			}
//			p.sendMessage("§3Dun> §f你不在任何副本內");
//			return true;
//		}
//		//----------------------------------------------------------------------------------------------
//
//		if(args.length == 1 && args[0].equalsIgnoreCase("SetSpawn")){
//			boolean use = false;
//			for(Game G : GameUtil.Game){
//				if(G.getWorld().getPlayers().contains(p)){
//					Location L = p.getLocation();//TODO
//					Main.c.set("Game." + G.getName() + ".X",L.getBlockX());
//					Main.c.set("Game." + G.getName() + ".Y",L.getBlockY());
//					Main.c.set("Game." + G.getName() + ".Z",L.getBlockZ());
//					Main.PL.saveConfig();
//					p.sendMessage("§3Dun> §f已設定出生點");
//					use = true;
//					break;
//				}
//			}
//			if(use == false){
//				p.sendMessage("§3Dun> §f你不在任何副本內");
//			}
//			return true;
//		}
//		//----------------------------------------------------------------------------------------------
//		//DONE
//		if(args.length >= 2 && args[0].equalsIgnoreCase("SetExit")){
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			if(args.length == 2){
//				Location L = p.getLocation();//TODO
//				Main.c.set("Game." + args[1] + ".Exit.W",L.getWorld().getName());
//				Main.c.set("Game." + args[1] + ".Exit.X",L.getBlockX());
//				Main.c.set("Game." + args[1] + ".Exit.Y",L.getBlockY());
//				Main.c.set("Game." + args[1] + ".Exit.Z",L.getBlockZ());
//				Main.PL.saveConfig();
//				p.sendMessage("§3Dun> §f已設定離開位置");
//				return true;
//			}else if(args.length == 3 && args[2].equalsIgnoreCase("null")){
//				Main.c.set("Game." + args[1] + ".Exit",null);
//				p.sendMessage("§3Dun> §f已設定離開位置為Spawn點");
//				
//				Main.PL.saveConfig();
//				return true;
//			}
//			
//		}
//		//----------------------------------------------------------------------------------------------
//		//DONE
//		if(args.length >= 2 && args[0].equalsIgnoreCase("SetIn")){
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			if(args.length == 2){
//				Location L = p.getLocation();//TODO
//				Main.c.set("Game." + args[1] + ".In.W",L.getWorld().getName());
//				Main.c.set("Game." + args[1] + ".In.X",L.getBlockX());
//				Main.c.set("Game." + args[1] + ".In.Y",L.getBlockY());
//				Main.c.set("Game." + args[1] + ".In.Z",L.getBlockZ());
//				p.sendMessage("§3Dun> §f已設定進入位置");
//				
//				Main.PL.saveConfig();
//				return true;
//			}else if(args.length == 3 && args[2].equalsIgnoreCase("null")){
//				Main.c.set("Game." + args[1] + ".In",null);
//				p.sendMessage("§3Dun> §f已設定任何位置都能進入");
//				
//				Main.PL.saveConfig();
//				return true;
//			}
//			
//			
//		}
//		//----------------------------------------------------------------------------------------------
//		//DONE
//		if(args.length == 2 && args[0].equalsIgnoreCase("SetItem")){
//			if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR){
//				p.sendMessage("§3Dun> §f手上應拿著物品");
//				return true;
//			}
//			
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			Main.c.set("Game." + args[1] + ".Item",p.getItemInHand());//TODO
//			Main.PL.saveConfig();
//			p.sendMessage("§3Dun> §f已設定副本傳送書為" + ItemsUtil.GetName(p.getItemInHand()));
//			return true;
//		}
//		//----------------------------------------------------------------------------------------------
//		//DONE
//		if(args.length == 2 && args[0].equalsIgnoreCase("getItem")){
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			ItemStack i = GameUtil.getItem(args[1]);
//			if(i == null){
//				p.sendMessage("§3Dun> §f沒有設定副本傳送書");
//			}else{
//				p.getInventory().addItem(i);
//			}
//			return true;
//		}
//		
//		//----------------------------------------------------------------------------------------------
//		if(args.length == 2 && args[0].equalsIgnoreCase("Create")){
//			if(GameUtil.hasGame(args[1])){
//				p.sendMessage("§3Dun> §f已經有此副本");
//				return true;
//			}
//			
//			Main.c.set("Game." + args[1] + ".UUID",UUID.randomUUID().toString());
//			Main.PL.saveConfig();
//			p.sendMessage("§3Dun> §f成功增設");
//			return true;
//		}
//		
//		
//		if(args.length == 2 && args[0].equalsIgnoreCase("Delete")){
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			Main.c.set("Game." + args[1] ,null);//TODO
//			Main.PL.saveConfig();
//			p.sendMessage("§3Dun> §f已刪除副本設定檔");
//			return true;
//		}
//		
//		if(args.length == 4 && args[0].equalsIgnoreCase("Limit")){
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			try{
//				Main.c.set("Game." + args[1] + ".Mix",Integer.parseInt(args[2]));//TODO
//				Main.c.set("Game." + args[1] + ".Max",Integer.parseInt(args[3]));
//				Main.PL.saveConfig();
//				p.sendMessage("§3Dun> §f已更改副本設定檔");
//			}catch(Exception ex){
//				p.sendMessage("§3Dun> §f設定錯誤");
//			}
//			return true;
//		}
//		
//		if(args.length == 3 && args[0].equalsIgnoreCase("Time")){
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			try{
//				Main.c.set("Game." + args[1] + ".Time",Integer.parseInt(args[2]));//TODO
//				Main.PL.saveConfig();
//				p.sendMessage("§3Dun> §f已更改副本設定檔");
//			}catch(Exception ex){
//				p.sendMessage("§3Dun> §f設定錯誤");
//			}
//			return true;
//		}
//		
//		if(args.length == 3 && args[0].equalsIgnoreCase("Respawn")){
//			if(GameUtil.hasGame(args[1]) ==  false){
//				p.sendMessage("§3Dun> §f沒有此副本");
//				return true;
//			}
//			
//			try{
//				Main.c.set("Game." + args[1] + ".Respawn",Boolean.valueOf(args[2]));//TODO
//				Main.PL.saveConfig();
//				p.sendMessage("§3Dun> §f已更改副本設定檔");
//			}catch(Exception ex){
//				p.sendMessage("§3Dun> §f設定錯誤");
//			}
//			return true;
//		}
		if(args.length == 2){
			if(args[0].equalsIgnoreCase("Create")){
				if(ConfigUtil.hasGame(args[1])){
					p.sendMessage("§3Dun> §f已經有此副本");
					return true;
				}
				
				ConfigUtil.createGame(args[1]);
				GameUtil.selectGame(p, args[1]);
				p.sendMessage("§3Dun> §f成功增設");
				return true;
			}
			
			
			if(args[0].equalsIgnoreCase("Delete")){
				if(ConfigUtil.hasGame(args[1]) ==  false){
					p.sendMessage("§3Dun> §f沒有此副本");
					return true;
				}
				
				ConfigUtil.deleteGame(args[1]);
				p.sendMessage("§3Dun> §f已刪除副本設定檔");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("Select")){
				if(ConfigUtil.hasGame(args[1]) ==  false){
					p.sendMessage("§3Dun> §f沒有此副本");
					return true;
				}
				
				GameUtil.selectGame(p, args[1]);
				p.sendMessage("§3Dun> §f已選擇副本");
				return true;
			}
	
			if(args[0].equalsIgnoreCase("delMob")){
				String name = GameUtil.getSelect(p);
				if(name == null){
					p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
					return true;
				}
					
				try{
					List<String> list = ConfigUtil.getSpawn_EntityList(name);
					if(list == null){
						p.sendMessage("§3Dun> §f列表為空的");
						return true;
					}
					
					list.remove(Integer.parseInt(args[1]));
					ConfigUtil.setSpawn_EntityList(name,list);
				}catch(Exception ex){
					p.sendMessage("§3Dun> §f設置錯誤");
				}
				return true;
			}
		}
		
		
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("Join")){
				ChatUtil.setJoin(p);
				return true;
			}else if(args[0].equalsIgnoreCase("Quit")){
				ChatUtil.setQuit(p);
				return true;
			}else if(args[0].equalsIgnoreCase("Set")){
				String name = GameUtil.getSelect(p);
				if(name == null){
					p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
					return true;
				}
				p.openInventory(GuiUtil.getOptionsGui(name));
				return true;
			}else if(args[0].equalsIgnoreCase("listMob")){
				String name = GameUtil.getSelect(p);
				if(name == null){
					p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
					return true;
				}
					
				List<String> list = ConfigUtil.getSpawn_EntityList(name);
				if(list == null){
					p.sendMessage("§3Dun> §f列表為空的");
					return true;
				}
						
				Aerry A;
				Location L;
				for(String str : list){
					A = new Aerry(str,p.getWorld());
					L = A.getLocation();
					p.sendMessage(" - " + L.getBlockX() + "," + L.getBlockY() + "," + L.getBlockZ() + " | " + A.getMobName());
				}
				return true;
			}else if(args[0].equalsIgnoreCase("listMob")){
				String name = GameUtil.getSelect(p);
				if(name == null){
					p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
					return true;
				}
				
				
			}else if(args[0].equalsIgnoreCase("2")){
				p.sendMessage("§3Dun> §f設定指令");
				help(p,helplist2);
				p.sendMessage("§3請打/Dun <頁數> 查詢");
				return true;
			}else if(args[0].equalsIgnoreCase("3")){
				p.sendMessage("§3Dun> §f怪物指令");
				help(p,helplist3);
				p.sendMessage("§3請打/Dun <頁數> 查詢");
				return true;
			}
			
		}
		
		if(args.length == 4){
			if(args[0].equalsIgnoreCase("SetSpawn")){
				String name = GameUtil.getSelect(p);
				if(name == null){
					p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
					return true;
				}
				
				try{
					ConfigUtil.setSpawn_SpawnPoint(name, 
							Integer.parseInt(args[1]), 
							Integer.parseInt(args[2]), 
							Integer.parseInt(args[3]));
					p.sendMessage("§3Dun> §f已設定出生點");
				}catch(Exception ex){
					p.sendMessage("§3Dun> §f設置錯誤");
				}
				return true;
			}
		}
		
		if(args.length == 5){
			if(args[0].equalsIgnoreCase("addMob")){
				String name = GameUtil.getSelect(p);
				if(name == null){
					p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
					return true;
				}
				
				try{
					String str = 
							Integer.parseInt(args[2]) + "," + 
							Integer.parseInt(args[3]) + "," + 
							Integer.parseInt(args[4]) + "," + 
							args[1];
					
					List<String> list = ConfigUtil.getSpawn_EntityList(name);
					list = list == null ? Lists.newArrayList() : list;
					list.add(str);
					ConfigUtil.setSpawn_EntityList(name, list);
					p.sendMessage("§3Dun> §f已新增怪物");
				}catch(Exception ex){
					p.sendMessage("§3Dun> §f設置錯誤");
				}
				return true;
			}
		}
		
		
		p.sendMessage("§3Dun> §f基本指令");
		help(p,helplist1);
		p.sendMessage("§3請打/Dun <頁數> 查詢");
		return true;
	}
	public static String[] helplist1 = {
			"/Dun §7Create <副本>","/Dun Create <副本>","創建副本設定檔",
			"/Dun §7Delete <副本>","/Dun Delete <副本>","刪除副本設定檔",
			"/Dun §7select <副本>","/Dun select <副本>","選擇副本"};

	public static String[] helplist2 = {
			"/Dun §7Join ","/Dun Join ","設定進入觸發",
			"/Dun §7Quit","/Dun Quit","設定結束觸發",
			"/Dun §7Set","/Dun Set","設定副本許可權",
			"/Dun §7setspawn <x> <y> <z>","/Dun setspawn <x> <y> <z>","設定出生點"};
	
	public static String[] helplist3 = {
			"/Dun §7addMob <怪物名稱> <x> <y> <z>","/Dun addMob <x> <y> <z>","新增怪物",
			"/Dun §7delMob <行>","/Dun delMob <行>","移除生成怪物列表的第幾行",
			"/Dun §7listMob","/Dun listMob","查看生成怪物列表"};
	
		
	public void help(Player p, String[] list){
		for(int i = 0; i + 2 < list.length; i = i +3){
			TitleApi.sendAction("{\"text\":\"" + list[i] + "\","
					+ "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"" + list[i+1] + "\"},"
					+ "\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""
						+ list[i+2]
						+ "\n點擊以查看指令"
					+ "\"}}", p);
		}
	}
}
