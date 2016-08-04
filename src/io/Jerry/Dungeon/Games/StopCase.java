package io.Jerry.Dungeon.Games;

import org.bukkit.scheduler.BukkitRunnable;

import io.Jerry.Dungeon.Main;
import io.Jerry.Dungeon.Util.ConfigUtil;

public class StopCase {
	private Game Hash;
	private String Quit;
	private String Type;
	private String TargetString;
	private int TargetNum;
	private int Num;
	private int Task = -1;;
	
	public StopCase(Game g, String quit) throws Exception {
		Hash = g;
		Quit = quit;
		Type = ConfigUtil.getQuit_Type(Hash.getName(), Quit);
		String info = ConfigUtil.getQuit_Info(Hash.getName(), Quit);
		
		if(Type.equalsIgnoreCase("PD")){
			TargetNum = Integer.parseInt(info);
		}else if(Type.equalsIgnoreCase("ED")){
			String[] split = info.split(",");
			TargetNum = Integer.parseInt(split[0]);
			TargetString = split[1];
		}else if(Type.equalsIgnoreCase("CE")){
			TargetString = info;
		}else if(Type.equalsIgnoreCase("BB")){
			String[] split = info.split(",");
			TargetNum = Integer.parseInt(split[0]);
			TargetString = split[1].toLowerCase();
		}else if(Type.equalsIgnoreCase("CB")){
			TargetString = info;
		}else if(Type.equalsIgnoreCase("TL")){
			TargetString = info;
		}else if(Type.equalsIgnoreCase("T")){
			TargetNum = Integer.parseInt(info);
			
			Task = new BukkitRunnable(){
				@Override
				public void run() {
					Num ++;
					if(Num >= TargetNum){
						Handler();
					}
				}
			}.runTaskTimer(Main.PL, 20, 20).getTaskId();
		}else{
			throw new Exception();
		}
		
	}

	public String getType() {
		return Type;
	}

	public String getName() {
		return Quit;
	}

	public Game getGame() {
		return Hash;
	}

	public void add(String str) {
		if(Type.equals("PD")){
			Num ++;
			if(Num >= TargetNum){
				Handler();
			}
		}else if(Type.equals("ED")){
			if(TargetString.equals(str)){
				Num ++;
				if(Num >= TargetNum){
					Handler();
				}
			}
			
		}else if(Type.equals("CE")){
			if(TargetString.equals(str)){
				Handler();
			}
			
		}else if(Type.equals("BB")){
			if(TargetString.equals(str)){
				Num ++;
				if(Num >= TargetNum){
					Handler();
				}
			}
		}else if(Type.equals("CB")){
			if(TargetString.equals(str)){
				Handler();
			}
			
		}else if(Type.equals("TL")){
			if(TargetString.equals(str)){
				Handler();
			}
			
		}
	}

	public void Handler(){
		new Bonus(Hash.getCanJoinPlayers(), Hash.getName(),Quit);
		Hash.End();
	}
	
	public void Stop(){
		if(Task != -1){
			Main.PL.getServer().getScheduler().cancelTask(Task);
		}
	}
}
