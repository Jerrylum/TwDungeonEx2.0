package io.Jerry.Dungeon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.InventoryHolder;

import io.Jerry.Dungeon.Handler.CaseHandler;
import io.Jerry.Dungeon.Handler.ChatHandler;
import io.Jerry.Dungeon.Handler.GameHandler;
import io.Jerry.Dungeon.Handler.GuiHandler;
import io.Jerry.Dungeon.Util.ChatUtil;

@SuppressWarnings("deprecation")
public class PlayerListener implements Listener {
//	@EventHandler
//	public void OpenBook(PlayerInteractEvent e) {
//		if(e.getPlayer().getItemInHand() == null){
//			return;
//		}
//		
//		Player p = e.getPlayer();
//		if(Main.c.isConfigurationSection("Game")){
//			ItemStack i2 = p.getItemInHand();
//			ItemStack i;
//			
//			for(String Name : Main.c.getConfigurationSection("Game").getKeys(false)){
//				i = GameUtil.getItem(Name);
//				if(i2.equals(i)){
//					double startTime = System.currentTimeMillis();
//						
//					List<String> list2 = Lists.newArrayList();
//					Party py = GameUtil.getTeam(p);
//					if(py != null){
//						list2.addAll(py.list);
//					}
//					list2.add(p.getName());
//					
//					Game LastG = null;
//					for(Game G : GameUtil.Game){
//						for(String T : G.hasPlayed()){
//							if(list2.contains(T)){
//								LastG = G;
//							}
//						}
//
//					}
//					
//					if(LastG != null){
//						TitleApi.sendAction("{\"text\":\"§3Dun> §f本隊伍正在進行副本,按\","
//								+ "\"extra\":["
//								+ "{\"text\":\"§a我\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/party play\"}},"
//								+ "{\"text\":\"§f加入\"}"
//								+ "]}",p);
//						return;
//					}
//					
//					p.setItemInHand(null);
//					
//					try {
//						Game G = new Game(Name,p);
//						G.onGameStart(false, list2);
//									
//						GameUtil.Game.add(G);
//						double endTime = System.currentTimeMillis();
//						double totalTime = endTime - startTime;
//						totalTime = totalTime/1000;
//						p.sendMessage("§3Dun> §f使用" + totalTime + "秒創建副本地圖");
//					} catch (GameError ex) {
//						p.sendMessage("§3Dun> §c" + ex.getMessage());
//						p.setItemInHand(i2);
//					}
//					break;
//				}
//			}
//			
//		}
//	}
	
	@EventHandler
	public void Tp(PlayerTeleportEvent e){

//		Player p = e.getPlayer();
//		for(Game G : GameUtil.Game){
//				if(G.getWorld().equals(e.getTo().getWorld()) && G.getWorld().equals(e.getFrom().getWorld()) == false){
//					for(Player T : G.getWorld().getPlayers()){
//						T.sendMessage("§3Party> §f" + p.getName() + "加入副本");
//					}
//					if(G.hasPlayed().contains(p.getName()) == false){
//						G.hasPlayed().add(p.getName());	
//					}
//				}else if(G.getWorld().equals(e.getFrom().getWorld()) && G.getWorld().equals(e.getTo().getWorld()) == false){
//					for(Player T : G.getWorld().getPlayers()){
//						T.sendMessage("§3Party> §f" + p.getName() + "退出副本");
//					}
//				}			
//		}
	}
	

	
//	@EventHandler
//	public void UseCommand(PlayerCommandPreprocessEvent e){
//		Player p = e.getPlayer();
//		if(p.isOp() == false){
//			for(Game G : GameUtil.Game){
//				if(G.getPlayers().contains(p.getName())){
//					if((e.getMessage().startsWith("/game") || e.getMessage().startsWith("/party")) == false){
//						e.setCancelled(true);
//						p.sendMessage("§c玩家進行副本時指令一律禁止,若要離開副本請輸入/party leave");
//					}
//					return;
//				}
//			}
//		}
//	}
	
	@EventHandler
	public void Quit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		ChatUtil.leave(p);
//		for(Game G : GameUtil.Game){
//			if(G.getWorld().equals(p.getWorld())){
//				if(Main.c.get("Game." + G.getName() + ".Exit") == null){//TODO
//					p.performCommand("spawn");
//				}else{
//				   	p.teleport(GameUtil.getExitLoc(G.getName()));
//				}
//			}
//			
//		}
	}
	
	@EventHandler
	public void Join(PlayerJoinEvent e){
//		Player p = e.getPlayer();
	
//		List<String> list2 = Lists.newArrayList();
//		Party py = GameUtil.getTeam(p);
//		if(py != null){
//			list2.addAll(py.list);
//		}
//		list2.add(p.getName());
//		
//		Game LastG = null;
//		for(Game G : GameUtil.Game){
//			for(String T : G.hasPlayed()){
//				if(list2.contains(T)){
//					LastG = G;
//				}
//			}
//
//		}
//		
//		if(LastG != null){
//			TitleApi.sendAction("{\"text\":\"§3Dun> §f本隊伍正在進行副本,按\","
//					+ "\"extra\":["
//					+ "{\"text\":\"§a我\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/party play\"}},"
//					+ "{\"text\":\"§f加入\"}"
//					+ "]}",p);
//			return;
//		}
	}
	
	@EventHandler
	public void Respawn(PlayerDeathEvent e){
		if(GameHandler.respawn(e)){
			return;
		}
		
//		for(Game G : GameUtil.Game){
//			if(G.getWorld().equals(p.getWorld()) ){
//				p.setHealth(p.getMaxHealth());
//				if(Main.c.getBoolean("Game." + G.getName() + ".Respawn", true) ){//TODO
//					ItemStack[] Items = p.getInventory().getContents();
//					Location L = GameUtil.getSpawnLoc(G.getWorld(), G.getName());
//					p.teleport(L);
//					p.getInventory().setContents(Items);
//					p.teleport(L);
//				}else{
//					p.sendMessage("§3你退出了副本");
//					
//					ItemStack[] Items = p.getInventory().getContents();
//					if(Main.c.get("Game." + G.getName() + ".Exit") == null){//TODO
//						p.performCommand("/spawn");
//						p.getInventory().setContents(Items);
//						p.performCommand("/spawn");					
//					}else{
//						Location L = GameUtil.getExitLoc(G.getName());
//						p.teleport(L);
//						p.getInventory().setContents(Items);
//						p.teleport(L);
//					}
//					
//					G.getDiedPlayers().add(p.getName());
//				}
//			}
//			
//		}
	}
	
	
	@EventHandler
	public void chat(PlayerChatEvent e){
		if(ChatHandler.Chat(e)){
			return;
		}
	}
	
	@EventHandler
	public void IEntity(PlayerInteractEntityEvent e){
		CaseHandler.Join_ClickEntity(e);
		
		Entity E = e.getRightClicked();
		GameHandler.Case("CE",E.getWorld(),E.getCustomName());
//		for(Game g : GameUtil.Game){
//			if(g.getWorld().equals(E.getWorld())){
//				for(StopCase sc : g.getCaseList()){
//					if(sc.getType().equals("CE")){
//						sc.add(E.getCustomName());
//					}
//				}
//			}
//		}
		
		
	}
	
	@EventHandler
	public void ToLoc(PlayerMoveEvent e){
		CaseHandler.Join_ToLocation(e);

		Location loc = e.getPlayer().getLocation();
		GameHandler.Case("TL",loc.getWorld(),loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
//		for(Game g : GameUtil.Game){
//			if(g.getWorld().equals(loc.getWorld())){
//				for(StopCase sc : g.getCaseList()){
//					if(sc.getType().equals("TL")){
//						
//						sc.add(loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
//					}
//				}
//			}
//		}
	}
	
	@EventHandler
	public void click(InventoryClickEvent e){
		if(GuiHandler.Click(e)){
			return;
		}
	}
	
	@EventHandler
	public void health(EntityRegainHealthEvent e){
		Entity E = e.getEntity();
		if(E instanceof Player == false){
			return;
		}
		
		if(!GameHandler.canContinue((Player) e.getEntity(), "Healing")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Pickup(PlayerPickupItemEvent e){
		Player p = e.getPlayer();
		if(!GameHandler.canContinue(p, "PickUp")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Build(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(!GameHandler.canContinue(p, "Build")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Destroy(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(!GameHandler.canContinue(p, "Destroy")){
			e.setCancelled(true);
		}
		
		Block b = e.getBlock();
		GameHandler.Case("BB", b.getWorld(), b.getType().toString().toLowerCase());
//		for(Game g : GameUtil.Game){
//			if(g.getWorld().equals(b.getWorld())){
//				for(StopCase sc : g.getCaseList()){
//					if(sc.getType().equals("BB")){
//						sc.add(b.getType().toString().toLowerCase());
//					}
//				}
//			}
//		}
	}
	
	@EventHandler
	public void Interact(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Action a = e.getAction();
		Block b = e.getClickedBlock();

		
		if(a == Action.PHYSICAL){
			if(b != null && b.getType() == Material.SOIL){
				if(!GameHandler.canContinue(p, "Trample")){
					e.setCancelled(true);
				}
			}else if(!GameHandler.canContinue(p, "PickUp")){
				e.setCancelled(true);
			}
		}else{
			if(b != null && b.getState() instanceof InventoryHolder){
				if(!GameHandler.canContinue(p, "Container")){
					e.setCancelled(true);
					return;
				}
			}else if(!GameHandler.canContinue(p, "Click")){
				e.setCancelled(true);
				return;
			}
			
			CaseHandler.Join_ClickBlock(e);
			
			
			Location loc = b.getLocation();
			GameHandler.Case("CB", loc.getWorld(), loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
//			for(Game g : GameUtil.Game){
//				if(g.getWorld().equals(loc.getWorld())){
//					for(StopCase sc : g.getCaseList()){
//						if(sc.getType().equals("CB")){
//							
//							sc.add(loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
//						}
//					}
//				}
//			}
			
		}		
		
	}
	
	@EventHandler
	public void explode(EntityExplodeEvent e){
		Entity E = e.getEntity();
		if(!GameHandler.canContinue(E.getWorld(), "Explode")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Spawn(CreatureSpawnEvent e){
		if(e.getSpawnReason() == SpawnReason.NATURAL){
			Entity E = e.getEntity();
			if(E instanceof Animals){
				if(!GameHandler.canContinue(E.getWorld(), "Animal")){
					e.setCancelled(true);
				}
			}else if(E instanceof Monster){
				if(!GameHandler.canContinue(E.getWorld(), "MobSpawn")){
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void Damage(EntityDamageEvent e){
		if(e.getCause() == DamageCause.VOID){
			return;
		}
		
		Entity E = e.getEntity();
		if(E instanceof Animals){
			if(!GameHandler.canContinue(E.getWorld(), "AnimalDamage")){
				e.setCancelled(true);
			}
		}else if(E instanceof Monster){
			if(!GameHandler.canContinue(E.getWorld(), "MobDamage")){
				e.setCancelled(true);
			}
		}else if(E instanceof Player){
			if(!GameHandler.canContinue((Player)E, "Damage")){
				e.setCancelled(true);
			}else if(e.getCause() == DamageCause.FALL){
				if(!GameHandler.canContinue((Player)E, "Fall")){
					e.setCancelled(true);
				}
			}
		}
		
		
	}
	
	@EventHandler
	public void Damage(EntityDamageByEntityEvent e){
		Entity E = e.getEntity();
		if(E instanceof Animals){
			if(!GameHandler.canContinue(E.getWorld(), "AnimalDamage")){
				e.setCancelled(true);
			}
		}else if(E instanceof Monster){
			if(!GameHandler.canContinue(E.getWorld(), "MobDamage")){
				e.setCancelled(true);
			}
		}else if(E instanceof Player){
			if(!GameHandler.canContinue((Player)E, "Damage")){
				e.setCancelled(true);
			}else if(e.getDamager() instanceof Player){
				if(!GameHandler.canContinue((Player)E, "PvP")){
					e.setCancelled(true);
				}
			}
			
		}
	}
	
	@EventHandler
	public void Death(EntityDeathEvent e){
		Entity E = e.getEntity();
		if(E instanceof Player){
			return;
		}
		
		GameHandler.Case("ED", E.getWorld(), E.getCustomName());
//		for(Game g : GameUtil.Game){
//			if(g.getWorld().equals(E.getWorld())){
//				for(StopCase sc : g.getCaseList()){
//					if(sc.getType().equals("ED")){
//						sc.add(E.getCustomName());
//					}
//				}
//			}
//		}
		
		
	}
}
