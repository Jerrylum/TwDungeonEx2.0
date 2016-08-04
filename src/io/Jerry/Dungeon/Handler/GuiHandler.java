package io.Jerry.Dungeon.Handler;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.Jerry.Dungeon.Util.ConfigUtil;
import io.Jerry.Dungeon.Util.GameUtil;
import io.Jerry.Dungeon.Util.I18n;

public class GuiHandler {
	public static boolean Click(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if(e.getInventory().getTitle().equals("副本許可權") == false){
			return false;
		}
		
		e.setCancelled(true);
		Inventory inv = e.getClickedInventory();
		if(inv != null && inv.getName().equals("副本許可權")){
			String select = GameUtil.getSelect(p);
			if(select == null){
				p.sendMessage("§3Dun> §f" + I18n.t("GuiHandler.Select"));	
				return true;
			}
			
			ItemStack i = e.getCurrentItem();
			if(i == null || i.getType() == Material.AIR){
				return true;
			}
			
			ItemMeta im = i.getItemMeta();
			String options = im.getDisplayName();
			options = options.substring(2, options.length());
			boolean b = e.getClick() == ClickType.LEFT;
			
			ConfigUtil.setOptions(select, options, b);
			
			List<String> lore = im.getLore();
			lore.set(0, I18n.t("GuiUtil.Info.State", (b ? "§a" + I18n.t("GuiUtil.Info.True") : "§c" + I18n.t("GuiUtil.Info.False"))));
			if(b){
				im.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
				im.addEnchant(Enchantment.LUCK, 1,true);
			}else{
				im.removeEnchant(Enchantment.LUCK);
			}
			im.setLore(lore);
			i.setItemMeta(im);

		}
		
		return true;
	}
}
