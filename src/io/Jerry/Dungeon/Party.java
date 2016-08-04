package io.Jerry.Dungeon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Party {
	public List<String> list = new ArrayList<String>();
	public String Owner;
	public List<String> AddList = new ArrayList<String>();
	
	public boolean isOwner(Player p){
		return Owner.equals(p.getName());
	}
	
	public boolean isMembers(String s){
		for(String p : list){
			if(p.equals(s)){
				return true;
			}
		}
		return false;
	}
	
	public void remove(String s) {
		for(String p : list){
			if(p.equals(s)){
				list.remove(p);
				return;
			}
		}
	}
	
	public int Size(){
		return list.size();
	}
}
