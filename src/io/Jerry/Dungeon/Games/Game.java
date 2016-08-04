package io.Jerry.Dungeon.Games;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.Jerry.Dungeon.Aerry;
import io.Jerry.Dungeon.Main;
import io.Jerry.Dungeon.Util.ConfigUtil;
import io.Jerry.Dungeon.Util.FileUtil;
import io.Jerry.Dungeon.Util.GameUtil;
import io.Jerry.Dungeon.Util.I18n;
import io.Jerry.Dungeon.Util.MoneyUtil;
import net.elseland.xikage.MythicMobs.API.Mobs;

@SuppressWarnings("deprecation")
public class Game{
	private String name;
	private File Path;
	private List<OfflinePlayer> CanJoin;
	private World world;
	private Location loc;
	private List<StopCase> CaseList;
	
	public Game(String Name,Player main, List<OfflinePlayer> players) throws GameError{
		if(!ConfigUtil.hasGame(Name)){
			throw new GameError(I18n.t("Game.NoMap"));
		}
		
		File f = new File(Main.PL.getDataFolder(), Name);		
		if(!f.exists()){
			throw new GameError(I18n.t("Game.NoWorld"));
		}
		
		int mix = ConfigUtil.getNeed_JoinSize_Mix(Name);
		int max = ConfigUtil.getNeed_JoinSize_Max(Name);
		if(players.size() < mix || (max != -1 && players.size() > max)){
			throw new GameError(I18n.t("Game.NoJoinSize",mix,max));
		}
		
		int money = ConfigUtil.getNeed_Money(Name);
		if(MoneyUtil.getEconomy().getBalance(main) < money){
			throw new GameError(I18n.t("Game.NoMoney"));
		}
		
		String per = ConfigUtil.getNeed_Permission(Name);
		if(per != null && !main.hasPermission(per)){
			throw new GameError(I18n.t("Game.NoPer"));
		}
		
		mix = ConfigUtil.getNeed_Exp_Mix(Name);
		max = ConfigUtil.getNeed_Exp_Max(Name);
		if(main.getLevel() < mix || (max != -1 && main.getLevel() > max)){
			throw new GameError(I18n.t("Game.NoExp"));
		}
		
		this.name = Name;
		this.Path = f;
		this.CanJoin = players;
		this.CaseList = new ArrayList<StopCase>();
	}
	
	public List<OfflinePlayer> getCanJoinPlayers(){
		return CanJoin;
	}
	
	public String getName(){
		return name;
	}
	
	public World getWorld(){
		return world;
	}
	
	public Location getSpawn(){
		return loc;
	}
	
	public void Start() throws GameError{
//		OfflinePlayer T;
//		List<Player> players = new ArrayList<Player>();
//		for(String str : list_player){
//			T = Bukkit.getOfflinePlayer(str);
//			if(T.isOnline()){
//				players .add(T.getPlayer());
//			}
//		}
//		
//		if(Pass == false){
//			
//			int mix = GameUtil.getMix(Name);
//			int max = GameUtil.getMax(Name);
//			
//			if(mix > players.size()){
//				throw new GameError("§c進入人數需大於" + (mix-1) );
//			}
//					
//			if(max != 0 && max < players.size()){
//				throw new GameError("§c進入人數需少於" + (max +1) );
//			}
//		}

		//Start make world
		String worldname = "副本地圖 " + name + " ID" + new Random().nextInt();
		File WorldFile = new File(worldname);
		try {
			if(WorldFile.exists() == false){
				FileUtil.copyFolder(this.Path,WorldFile);
			}
			WorldCreator creator = WorldCreator.name(worldname);
		    World world = creator.createWorld();
			if(world == null){
			   	throw new GameError("Fall - null");
			}
		    this.world = world;
		} catch (IOException e) {
			throw new GameError("Fall");
		}
		//stop make world
		
		//Start Spawn Entity
		List<String> list = ConfigUtil.getSpawn_EntityList(name);
		if(list != null && list.isEmpty() == false){
			Aerry A;
			for(String str : list){
				A = new Aerry(str,this.world);
				Mobs.spawnMythicMob(A.getMobName(), A.getLocation());
			}
		}
		//stop Spawn Entity
		
		//Start tp players
		
		String strLoc = ConfigUtil.getSpawn_SpawnPoint(name);
		String[] split = strLoc.split(",");
		loc = new Location (world,Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2]));
		
		boolean Inventory = ConfigUtil.getOptions(name, "Inventory");
		Player p;
		ItemStack[] a;
		ItemStack[] b;
		for(OfflinePlayer op : CanJoin){
			p = op.getPlayer();
			if(p != null){
				a = p.getInventory().getContents();
				b = p.getInventory().getArmorContents();
				p.getPlayer().teleport(loc);
				if(Inventory){
					 p.getPlayer().getInventory().setContents(a);
					 p.getPlayer().getInventory().setArmorContents(b);
				}
			}

		}
		//stop tp players
		
//		if(GameUtil.getTime(Name) != 0 && Pass == false){
//			this.Task = new BukkitRunnable(){
//				int Time = GameUtil.getTime(Name);
//				
//		    	public void run(){
//		    		if(Time <= 0){
//			    		for(Player T : world.getPlayers()){
//			    			T.sendMessage("§3Dun> §f時限已到");
//			    		}
//			    		onGameEnd();
//		    		}else{
//		    			Time --;
//		    			for(Player T : world.getPlayers()){
//			    			TitleApi.sendBar( "副本時間剩下" + Time +"秒時間",T);
//			    		}
//		    		}
//		    	}
//		   }.runTaskTimer(Main.PL, 0, 20).getTaskId();
//		}

		for(String str : ConfigUtil.getQuit(name)){
			try{
				CaseList.add(new StopCase(this,str));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void Stop(){
		for(StopCase sc : CaseList){
			if(sc.getType().equals("T")){
				sc.Handler();
				return;
			}
		}
		End();
	}
	
	public void End(){
		for(StopCase sc : CaseList){
			sc.Stop();
		}
			
		for(Player T : this.world.getPlayers()){
			T.kickPlayer("Kick with map close");
		}
		Main.PL.getServer().unloadWorld(world, true);
		GameUtil.Game.remove(this);
	}

	
	public void Ban(OfflinePlayer p) {
		CanJoin.remove(p);
	}

	public List<StopCase> getCaseList() {
		return CaseList;
	}
	
}
