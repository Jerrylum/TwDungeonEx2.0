package io.Jerry.Dungeon.Games;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import io.Jerry.Dungeon.Main;
import io.Jerry.Dungeon.Util.ConfigUtil;
import io.Jerry.Dungeon.Util.MoneyUtil;
import net.milkbowl.vault.economy.Economy;

public class Bonus {

	public Bonus(List<OfflinePlayer> list, String name, String quit) {
		final String cmd = ConfigUtil.getQuit_Bonus_Command(name, quit);
		final int exp = ConfigUtil.getQuit_Bonus_Exp(name, quit);
		final int m = ConfigUtil.getQuit_Bonus_Money(name, quit);
		final List<ItemStack> iList = ConfigUtil.getItemList(ConfigUtil.getQuit_Bonus_Item(name, quit));
		String[] split = ConfigUtil.getQuit_Bonus_Location(name, quit).split(",");
		final Location loc = new Location(
				Bukkit.getWorld(split[0]), 
				Integer.parseInt(split[1]), 
				Integer.parseInt(split[2]), 
				Integer.parseInt(split[3])
			);
		
		Economy E = MoneyUtil.getEconomy();
		CommandSender s = Main.PL.getServer().getConsoleSender();
		Player p;
		ItemStack[] a;
		ItemStack[] b;
		boolean Inventory = ConfigUtil.getOptions(name, "Inventory");
		
		for(OfflinePlayer op : list){
			if(cmd != null){
				s.getServer().dispatchCommand(s, cmd.replace("<PLAYER>", op.getName()));
			}
			E.depositPlayer(op, m);
			p = op.getPlayer();
			if(p != null){
				p.setTotalExperience(p.getTotalExperience() + exp);
				try{
					PlayerInventory pi = p.getInventory();
					pi.addItem(iList.toArray(new ItemStack[iList.size()]));
				}catch(Exception ex){}
				
				a = p.getInventory().getContents();
				b = p.getInventory().getArmorContents();
				p.getPlayer().teleport(loc);
				if(Inventory){
					 p.getPlayer().getInventory().setContents(a);
					 p.getPlayer().getInventory().setArmorContents(b);
				}
			}
		}
		
	}
	
}
