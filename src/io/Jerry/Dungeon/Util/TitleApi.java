package io.Jerry.Dungeon.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleApi {
	public static String version = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	
	public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException{
		return Class.forName("net.minecraft.server." + getServerVersion() + "." + nmsClassName);
	}
			  
	public static String getServerVersion(){
		return version;
	}
	
	public static void sendAction(String msg,Player... player){
	    try{
	    	Object icbc = getNmsClass(version.equalsIgnoreCase("v1_8_R1") ? "ChatSerializer": "IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { msg });
			Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(new Class[] { getNmsClass("IChatBaseComponent"), Byte.TYPE }).newInstance(new Object[] { icbc, Byte.valueOf("1") });      
    		for(Player p : player){
        		sendPacket(p,ppoc);	
    		}
	   }catch (Exception e){
	    	Bukkit.broadcastMessage("§3Bug> §f傳送封包時發生錯誤 " + e.getMessage());
	    	e.printStackTrace();
	    }
	}
	
	
	public static void sendBar(String msg,Player... player){
	    try{
	    	Object icbc = getNmsClass(version.equalsIgnoreCase("v1_8_R1") ? "ChatSerializer": "IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + msg + "\"}" });
			Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(new Class[] { getNmsClass("IChatBaseComponent"), Byte.TYPE }).newInstance(new Object[] { icbc, Byte.valueOf("2") });      
    		for(Player p : player){
        		sendPacket(p,ppoc);	
    		}
	    }catch (Exception e){
	    	Bukkit.broadcastMessage("§3Bug> §f傳送封包時發生錯誤 " + e.getMessage());
	    	e.printStackTrace();
	    }
	}
	 
	public static Class<?> getOutput() throws ClassNotFoundException{
    	return getNmsClass("IChatBaseComponent");
	}
	  
	public static void sendPacket(Player player, Object packet){
	    try{
	    	Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
	    	Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
	    	playerConnection.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(playerConnection, new Object[] { packet });
	    }catch (Exception e){}
	}
}