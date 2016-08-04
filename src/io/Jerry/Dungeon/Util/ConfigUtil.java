package io.Jerry.Dungeon.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import io.Jerry.Dungeon.Main;

public class ConfigUtil {
	public static List<ItemStack> getItemList(String str) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		try{
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(str));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			ItemStack item = null;
		    while((item = (ItemStack) dataInput.readObject()) != null){
		    	list.add(item);
		    }
	        inputStream.close();
	        dataInput.close();
		}catch(Exception ex){}
		
		return list;
	}
	
	public static String toString(String type, String info) {
		String str = "¡±e";
		if(type == null ||info == null){
			return "¡±c" + I18n.t("ConfigUtil.Info.Null");
		}
		try{
			if(type.equalsIgnoreCase("PD")){
				str = str + I18n.t("ConfigUtil.Type.PD",info) ;
			}else if(type.equalsIgnoreCase("ED")){
				String[] split = info.split(",");
				int i = Integer.parseInt(split[0]);
				str = str + I18n.t("ConfigUtil.Type.ED",i,split[1]);
			}else if(type.equalsIgnoreCase("CE")){
				str = str + I18n.t("ConfigUtil.Type.CE",info) ;
			}else if(type.equalsIgnoreCase("BB")){
				String[] split = info.split(",");
				int i = Integer.parseInt(split[0]);
				str = str + I18n.t("ConfigUtil.Type.BB",i,Material.getMaterial(split[1]));
			}else if(type.equalsIgnoreCase("CB")){
				str = str + I18n.t("ConfigUtil.Type.CB",info) ;
			}else if(type.equalsIgnoreCase("TL")){
				str = str + I18n.t("ConfigUtil.Type.TL",info) ;
			}else if(type.equalsIgnoreCase("T")){
				str = str + I18n.t("ConfigUtil.Type.T", info);
			}else{
				throw new Exception();
			}
		}catch(Exception ex){return null;}
		
		return str;
	}
	
	public static boolean hasGame(String Name){
		return Main.c.get("Game." +  Name) != null;
	}
	
	public static void createGame(String name){
		Main.c.set("Game." + name + ".UUID",UUID.randomUUID().toString());
		Main.PL.saveConfig();
	}
	
	public static void deleteGame(String name){
		Main.c.set("Game." + name ,null);
		Main.PL.saveConfig();
	}

	//Event
	public static String getJoin_ClickEntity(String name) {
		return Main.c.getString("Game." + name + ".Join.ClickEntity");
	}
	
	public static void setJoin_ClickEntity(String name,String entity_name) {
		Main.c.set("Game." + name + ".Join.ClickEntity", entity_name);
		Main.PL.saveConfig();
	}

	public static String getJoin_ClickBlock(String name) {
		return Main.c.getString("Game." + name + ".Join.ClickBlock");
	}
	
	public static void setJoin_ClickBlock(String name,Location l) {
		Main.c.set("Game." + name + ".Join.ClickBlock", 
				l == null ? null : l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ());
		Main.PL.saveConfig();
	}
	
	public static ItemStack getJoin_ClickBlock_useItem(String name) {
		return Main.c.getItemStack("Game." + name + ".Item");
	}
	
	public static void setJoin_ClickBlock_useItem(String name,ItemStack item) {
		Main.c.set("Game." + name + ".Item", item);
		Main.PL.saveConfig();
	}

	public static String getJoin_ToLocation(String name) {
		if(Main.c.isConfigurationSection("Game." + name + ".In")){
			return Main.c.getString("Game." + name + ".In.W")  + "," + 
			Main.c.getInt("Game." + name + ".In.X") + "," + 
			Main.c.getInt("Game." + name + ".In.Y") + "," + 
			Main.c.getInt("Game." + name + ".In.Z");
		}
		return null;		
	}
	
	public static void setJoin_ToLocation(String name, Location L){
		if(L == null){
			Main.c.set("Game." + name + ".In",null);
		}else{
			Main.c.set("Game." + name + ".In.W",L.getWorld().getName());
			Main.c.set("Game." + name + ".In.X",L.getBlockX());
			Main.c.set("Game." + name + ".In.Y",L.getBlockY());
			Main.c.set("Game." + name + ".In.Z",L.getBlockZ());
		}
		Main.PL.saveConfig();
	}

//	public static String getJoin_UseCommand(String name) {
//		return Main.c.getString("Game." + name + ".Join.Command");
//	}
//	
//	public static void setJoin_UseCommand(String name,String cmd) {
//		Main.c.set("Game." + name + ".Join.Command", cmd);
//		Main.PL.saveConfig();
//	}

	//Needs
	public static int getNeed_JoinSize_Mix(String name) {
		return Main.c.getInt("Game." + name + ".Mix",1);
	}
	
	public static void setNeed_JoinSize_Mix(String name,Object i) {
		Main.c.set("Game." + name + ".Mix",i);
		Main.PL.saveConfig();
	}
	
	public static int getNeed_JoinSize_Max(String name) {
		return Main.c.getInt("Game." + name + ".Max",-1);
	}
	
	public static void setNeed_JoinSize_Max(String name,Object i) {
		Main.c.set("Game." + name + ".Max",i);
		Main.PL.saveConfig();
	}

	public static int getNeed_Money(String name) {
		return Main.c.getInt("Game." + name + ".Need.Money",-1);
	}
	
	public static void setNeed_Money(String name,Object i) {
		Main.c.set("Game." + name + ".Need.Money",i);
		Main.PL.saveConfig();
	}

	public static String getNeed_Permission(String name) {
		return Main.c.getString("Game." + name + ".Need.Permission");
	}
	
	public static void setNeed_Permission(String name, String per) {
		Main.c.set("Game." + name + ".Need.Permission", per);
		Main.PL.saveConfig();
	}

	public static int getNeed_Exp_Mix(String name) {
		return Main.c.getInt("Game." + name + ".Need.Level.Mix",0);
	}
	
	public static void setNeed_Exp_Mix(String name,Object i) {
		Main.c.set("Game." + name + ".Need.Level.Mix",i);
		Main.PL.saveConfig();
	}
	
	public static int getNeed_Exp_Max(String name) {
		return Main.c.getInt("Game." + name + ".Need.Level.Max",-1);
	}
	
	public static void setNeed_Exp_Max(String name,Object i) {
		Main.c.set("Game." + name + ".Need.Level.Max",i);
		Main.PL.saveConfig();
	}

	//Quit
	public static void createQuit(String name, String msg) {
		Main.c.set("Game." + name + ".Quit." + msg + ".UUID",UUID.randomUUID().toString());
		Main.PL.saveConfig();
	}

	public static void deleteQuit(String name, String msg) {
		Main.c.set("Game." + name + ".Quit." + msg,null);
		Main.PL.saveConfig();
	}

	public static String getQuit_Type(String name, String quit) {
		return Main.c.getString("Game." + name + ".Quit." + quit + ".Type");
	}
	
	public static void setQuit_Type(String name, String quit,String type) {
		Main.c.set("Game." + name + ".Quit." + quit + ".Type",type);
		Main.PL.saveConfig();
	}

	public static String getQuit_Info(String name, String quit) {
		return Main.c.getString("Game." + name + ".Quit." + quit + ".Info");

	}
	
	public static void setQuit_Info(String name, String quit,String info) {
		Main.c.set("Game." + name + ".Quit." + quit + ".Info",info);
		Main.PL.saveConfig();
	}

	public static boolean hasQuit_Bonus(String name, String quit) {
		return getQuit_Bonus_Command(name,quit) != null ||
				getQuit_Bonus_Money(name,quit) != -1 || 
				getQuit_Bonus_Exp(name,quit) != -1 ||
				getQuit_Bonus_Item(name,quit) != null;
	}
	
	public static String getQuit_Bonus_Command(String name, String quit) {
		return Main.c.getString("Game." + name + ".Quit." + quit + ".Bonus.Command");

	}
	
	public static void setQuit_Bonus_Command(String name, String quit, String str) {
		Main.c.set("Game." + name + ".Quit." + quit + ".Bonus.Command", str);
		Main.PL.saveConfig();
	}
	
	public static int getQuit_Bonus_Money(String name, String quit) {
		return Main.c.getInt("Game." + name + ".Quit." + quit + ".Bonus.Money", -1);
	}
	
	public static void setQuit_Bonus_Money(String name, String quit, Object i) {
		Main.c.set("Game." + name + ".Quit." + quit + ".Bonus.Money", i);
		Main.PL.saveConfig();
	}
	
	public static int getQuit_Bonus_Exp(String name, String quit) {
		return Main.c.getInt("Game." + name + ".Quit." + quit + ".Bonus.Exp", -1);
	}
	
	public static void setQuit_Bonus_Exp(String name, String quit, Object i) {
		Main.c.set("Game." + name + ".Quit." + quit + ".Bonus.Exp", i);
		Main.PL.saveConfig();
	}
	

	public static String getQuit_Bonus_Location(String name, String quit) {
		return Main.c.getString("Game." + name + ".Quit." + quit + ".Location");
	}
	
	public static void setQuit_Bonus_Location(String name, String quit,Location l) {
		Main.c.set("Game." + name + ".Quit." + quit + ".Location", 
				l == null ? null : l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ());
		Main.PL.saveConfig();
	}
	
	public static String getQuit_Bonus_Item(String name, String quit) {
		return Main.c.getString("Game." + name + ".Quit." + quit + ".Bonus.Item");

	}
	
	public static void setQuit_Bonus_Item(String name, String quit, List<ItemStack> item) {
		String str = null;
		try{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
		    
		    for (ItemStack i : item) {
		        dataOutput.writeObject(i);
		    }
		    
		    dataOutput.close();
		    str = Base64Coder.encodeLines(outputStream.toByteArray() );
		}catch(Exception ex){}
	    
		Main.c.set("Game." + name + ".Quit." + quit + ".Bonus.Item",str);
		Main.PL.saveConfig();

	}

	public static List<String> getSpawn_EntityList(String name) {
		return Main.c.getStringList("Game." + name + ".Entity");
	}

	public static void setSpawn_EntityList(String name, List<String> list) {
		Main.c.set("Game." + name + ".Entity", list);
		Main.PL.saveConfig();

	}
	
	public static String getSpawn_SpawnPoint(String Name){
	  	return Main.c.getInt("Game." + Name + ".X") + "," + 
	  		   Main.c.getInt("Game." + Name + ".Y") + "," +
	  		   Main.c.getInt("Game." + Name + ".Z");
   	}
	
	public static void setSpawn_SpawnPoint(String Name,int x,int y, int z){
	  	Main.c.set("Game." + Name + ".X",x);
	  	Main.c.set("Game." + Name + ".Y",y);
	    Main.c.set("Game." + Name + ".Z",z);
	    Main.PL.saveConfig();
   	}

	public static boolean getOptions(String name, String options) {
		if(options.equals("Respawn")){
			return Main.c.getBoolean("Game." + name + ".Respawn", false);
		}
		return Main.c.getBoolean("Game." + name + ".Options." + options, false);
	}
	
	public static void setOptions(String name, String options,boolean b) {
		if(options.equals("Respawn")){
			Main.c.set("Game." + name + ".Respawn", b ? b : null);
		}else {
			Main.c.set("Game." + name + ".Options." + options, b ? b : null);
		}
		Main.PL.saveConfig();
	}

	public static Set<String> getGame() {
		ConfigurationSection list = Main.c.getConfigurationSection("Game");
		return list == null ? new HashSet<String>() : list.getKeys(false);
	}

	public static Set<String> getQuit(String name) {
		ConfigurationSection list = Main.c.getConfigurationSection("Game." + name + ".Quit");
		return list == null ? new HashSet<String>() : list.getKeys(false);
	}

}
