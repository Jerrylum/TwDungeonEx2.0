package io.Jerry.Dungeon.Util;

import org.bukkit.plugin.RegisteredServiceProvider;

import io.Jerry.Dungeon.Main;
import net.milkbowl.vault.economy.Economy;

public class MoneyUtil {
	public static Economy economy;
	
	static{
		RegisteredServiceProvider<Economy> economyProvider = 
				Main.PL.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
        	economy = economyProvider.getProvider();
        }
	}
	
	public static Economy getEconomy(){
		return economy;
	}
}
