package io.Jerry.Dungeon.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiUtil {
	private static List<Feel> ItemFeelList = new ArrayList<Feel>();
	
	static{
		ItemFeelList.add(new Feel("Animal",Material.MONSTER_EGG,I18n.t("GuiUtil.Button.Animal")));
		ItemFeelList.add(new Feel("AnimalDamage",Material.RAW_CHICKEN,I18n.t("GuiUtil.Button.AnimalDamage")));
		ItemFeelList.add(new Feel("Build",Material.BRICK,I18n.t("GuiUtil.Button.Build")));
		ItemFeelList.add(new Feel("Click",Material.WOOD_BUTTON,I18n.t("GuiUtil.Button.Click")));
		ItemFeelList.add(new Feel("Container",Material.STORAGE_MINECART,I18n.t("GuiUtil.Button.Container")));
		ItemFeelList.add(new Feel("Destroy",Material.ENDER_STONE,I18n.t("GuiUtil.Button.Destroy")));
		ItemFeelList.add(new Feel("Damage",Material.LEATHER_CHESTPLATE,I18n.t("GuiUtil.Button.Damage")));
		ItemFeelList.add(new Feel("Explode",Material.TNT,I18n.t("GuiUtil.Button.Explode")));
		ItemFeelList.add(new Feel("Fall",Material.LEATHER_BOOTS,I18n.t("GuiUtil.Button.Fall")));
		ItemFeelList.add(new Feel("Healing",Material.POTION,I18n.t("GuiUtil.Button.Healing")));
		ItemFeelList.add(new Feel("Inventory",Material.ENDER_PORTAL_FRAME,I18n.t("GuiUtil.Button.Inventory")));
		ItemFeelList.add(new Feel("MobDamage",Material.ROTTEN_FLESH,I18n.t("GuiUtil.Button.MobDamage")));
		ItemFeelList.add(new Feel("MobSpawn",Material.MOB_SPAWNER,I18n.t("GuiUtil.Button.MobSpawn")));
		ItemFeelList.add(new Feel("Physical",Material.TRIPWIRE_HOOK,I18n.t("GuiUtil.Button.Physical")));
		ItemFeelList.add(new Feel("PickUp",Material.APPLE,I18n.t("GuiUtil.Button.PickUp")));
		ItemFeelList.add(new Feel("PvP",Material.WOOD_SWORD,I18n.t("GuiUtil.Button.PvP")));
		ItemFeelList.add(new Feel("Respawn",Material.BED,I18n.t("GuiUtil.Button.Respawn")));
		ItemFeelList.add(new Feel("Trample",Material.DIRT,I18n.t("GuiUtil.Button.Trample")));

	}
	
	private static class Feel{
		public Material m;
		public String l;
		public String n;
		
		public Feel(String name,Material m, String l){
			this.m = m;
			this.l = l;
			this.n = name;
		}
	}
	
	public static Inventory getOptionsGui(String name){
		Inventory inv = Bukkit.createInventory(null, 18, "副本許可權");
		for(Feel f : ItemFeelList){
			inv.addItem(getItem(name,f));
		}
		return inv;
	}
	
	private static ItemStack getItem(String name,Feel f){
		boolean b = ConfigUtil.getOptions(name,f.n);
		
		ItemStack item = new ItemStack(f.m);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("§a" + f.n);
		im.setLore(Arrays.asList(new String[]{
				"§e" + I18n.t("GuiUtil.Info.State", (b ? "§a" + I18n.t("GuiUtil.Info.True") : "§c" + I18n.t("GuiUtil.Info.False"))),
				"§e" + f.l,
				"§2" + I18n.t("GuiUtil.Info.LeftClick"),
				"§c" + I18n.t("GuiUtil.Info.RightClick")
			}));
		if(b){
			im.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
			im.addEnchant(Enchantment.LUCK, 1,true);
		}
		item.setItemMeta(im);
		
		
		return item;
	}
	
	
}
