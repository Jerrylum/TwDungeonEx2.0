package io.Jerry.Dungeon;

import org.bukkit.Location;
import org.bukkit.World;

public class Aerry{
	private Location L;
	private String m; 
	
	public Aerry(String string,World w){
		String[] str = string.split(",");
		L  = new Location(w,Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
		m = str[3];
	}

	public Location getLocation() {
		return L;
	}

	public String getMobName() {
		return m;
	}
}