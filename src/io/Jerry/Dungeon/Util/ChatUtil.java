package io.Jerry.Dungeon.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChatUtil {
	public static HashMap<Player,String> list = new HashMap<Player,String>();
	
	public static void setJoin(Player p){
		if(list.get(p) != null){
			p.sendMessage("§3Dun> §f請先退出目前頁面");
			return;
		}
		
		String name = GameUtil.getSelect(p);
		if(name == null){
			p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
			return;
		}
		GameUtil.ban.add(p);
		page0(p);
		
	}
	
	public static void setQuit(Player p){
		if(list.get(p) != null){
			p.sendMessage("§3Dun> §f請先退出目前頁面");
			return;
		}
		
		String name = GameUtil.getSelect(p);
		if(name == null){
			p.sendMessage("§3Dun> §f請使用指令/Dun select <副本> 選擇需要設定的副本");
			return;
		}
		GameUtil.ban.add(p);
		page1(p);
	}
	
	public static void leave(Player p){
		list.put(p, null);
		GameUtil.selectGame(p, null);
		GameUtil.selectEnd(p, null);
		p.sendMessage("§3Dun> §f退出編輯");
		GameUtil.ban.remove((OfflinePlayer)p);
	}
	
	public static void page0(Player p){
		list.put(p, "0");
		
		p.sendMessage("§3Dun> §f編輯副本進入觸發");
		p.sendMessage("§61. §e編輯進入事件");
		p.sendMessage("§62. §e編輯進入要求");
		p.sendMessage("§63. §3退出編輯");
		p.sendMessage("§6\n§6當§e其中一個§6事件(Event)觸發後會檢查進入要求(Needs)");
		p.sendMessage("§6如果§e所有§6進入要求滿足即可進入副本");
	}
	
	public static void page0_1(Player p){
		list.put(p, "0_1");
		
		String name = GameUtil.getSelect(p);
		p.sendMessage("§3Dun> §f編輯副本進入事件");
		p.sendMessage("§61. §e點擊實體" + (ConfigUtil.getJoin_ClickEntity(name) == null ? "§7(沒有設置)" : "§a(已經設置)"));
		p.sendMessage("§62. §e點擊方塊" + (
				ConfigUtil.getJoin_ClickBlock(name) != null || ConfigUtil.getJoin_ClickBlock_useItem(name) != null 
				? "§a(已經設置)" : "§7(沒有設置)" ));
		p.sendMessage("§63. §e進入位置" + (ConfigUtil.getJoin_ToLocation(name) == null ? "§7(沒有設置)" : "§a(已經設置)"));
		p.sendMessage("§64. §3上一頁");
		
	}
	
	public static void page0_1_1(Player p){
		list.put(p, "0_1_1");
		
		String name = GameUtil.getSelect(p);
		String entity = ConfigUtil.getJoin_ClickEntity(name);
		p.sendMessage("§3Dun> §f編輯點擊實體觸發條件");
		p.sendMessage("§e填寫要點擊的實體的顯示名稱，目前" + (entity == null ? "沒有設置" : "設定§f" +entity));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該觸發");
	}
	
	public static void page0_1_2(Player p){
		list.put(p, "0_1_2");
		
		String name = GameUtil.getSelect(p);
		p.sendMessage("§3Dun> §f編輯點擊方塊觸發細節");
		p.sendMessage("§61. §e點擊方塊" + (ConfigUtil.getJoin_ClickBlock(name) == null ? "§7(沒有限制)" : "§a(設置限制)"));
		p.sendMessage("§62. §e使用物品" + (ConfigUtil.getJoin_ClickBlock_useItem(name) == null ? "§7(沒有限制)" : "§a(設置限制)"));
		p.sendMessage("§63. §3上一頁");
	}
	
	public static void page0_1_3(Player p){
		list.put(p, "0_1_3");
		
		String name = GameUtil.getSelect(p);
		String loc = ConfigUtil.getJoin_ToLocation(name);
		p.sendMessage("§3Dun> §f編輯進入位置觸發條件");
		p.sendMessage("§e填寫\"here\"，設置需要點擊你§l身處§e的位置，目前" + (loc == null ? "沒有設置" : "設定§f" + loc));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該條件");
	}
	
	public static void page0_1_2_1(Player p){
		list.put(p, "0_1_2_1");
		
		String name = GameUtil.getSelect(p);
		String Block = ConfigUtil.getJoin_ClickBlock(name);
		p.sendMessage("§3Dun> §f編輯點擊方塊觸發條件");
		p.sendMessage("§e填寫\"here\"，設置需要點擊你§l腳下§e的方塊，目前" + (Block == null ? "沒有設置" : "設定§f" + Block));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該條件");
	}
	
	public static void page0_1_2_2(Player p){
		list.put(p, "0_1_2_2");
		
		String name = GameUtil.getSelect(p);
		ItemStack item = ConfigUtil.getJoin_ClickBlock_useItem(name);
		p.sendMessage("§3Dun> §f編輯點擊方塊觸發條件");
		p.sendMessage("§e填寫\"hand\"，設置需要使用你§l手上§e的物品點擊，目前" + 
				(item == null ? "沒有設置" : "設定§f" + ItemsUtil.GetName(item)));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該條件");
	}
	
	public static void page0_2(Player p){
		list.put(p, "0_2");
		
		String name = GameUtil.getSelect(p);
		p.sendMessage("§3Dun> §f編輯副本進入要求");
		p.sendMessage("§61. §e隊伍人數" + 
				(ConfigUtil.getNeed_JoinSize_Mix(name) != 1 || ConfigUtil.getNeed_JoinSize_Max(name)  != -1  
				? "§a(已經設置)" : "§7(沒有限制)"));
		p.sendMessage("§62. §e金錢" + (ConfigUtil.getNeed_Money(name) == -1 ? "§7(沒有限制)" : "§a(已經設置)"));
		p.sendMessage("§63. §e權限" + (ConfigUtil.getNeed_Permission(name) == null ? "§7(沒有限制)" : "§a(已經設置)"));
		p.sendMessage("§64. §e等級" + (ConfigUtil.getNeed_Exp_Mix(name) != 0 || ConfigUtil.getNeed_Exp_Max(name) != -1
				?  "§a(已經設置)" : "§7(沒有限制)"));
		p.sendMessage("§65. §3上一頁");
	}
	
	public static void page0_2_1(Player p){
		list.put(p, "0_2_1");
		
		String name = GameUtil.getSelect(p);
		int mix = ConfigUtil.getNeed_JoinSize_Mix(name);
		int max = ConfigUtil.getNeed_JoinSize_Max(name);
		
		p.sendMessage("§3Dun> §f編輯隊伍人數要求");
		p.sendMessage("§e填寫格式:\"<最少人數>~<最大人數>\"，目前" + 
				(mix != 1 || max != -1 ? "設定§f" + mix + "~" + (max == -1 ? "∞" : max) : "沒有設置"));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該要求");
		p.sendMessage("§e填寫負數代表該項無限");
	}
	
	public static void page0_2_2(Player p){
		list.put(p, "0_2_2");
		
		String name = GameUtil.getSelect(p);
		int money = ConfigUtil.getNeed_Money(name);
		
		p.sendMessage("§3Dun> §f編輯金錢要求");
		p.sendMessage("§e填寫需要達到的金錢，目前" + 
				(money == -1 ? "沒有設置" : "設定§f$" + money ));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該要求");
	}
	
	public static void page0_2_3(Player p){
		list.put(p, "0_2_3");
		
		String name = GameUtil.getSelect(p);
		String per = ConfigUtil.getNeed_Permission(name);
		
		p.sendMessage("§3Dun> §f編輯權限要求");
		p.sendMessage("§e填寫需要的權限，目前" + 
				(per == null ? "沒有設置" : "設定§f$" + per ));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該要求");
	}
	
	public static void page0_2_4(Player p){
		list.put(p, "0_2_4");
		
		String name = GameUtil.getSelect(p);
		int mix = ConfigUtil.getNeed_Exp_Mix(name);
		int max = ConfigUtil.getNeed_Exp_Max(name);
		
		p.sendMessage("§3Dun> §f編輯等級要求");
		p.sendMessage("§e填寫格式:\"<最少等級>~<最大等級>\"，目前" + 
				(mix != 0 || max != -1 ? "設定§f" + mix + "~" + (max == -1 ? "∞" : max) : "沒有設置"));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該要求");
		p.sendMessage("§e填寫負數代表該項無限");
	}
	
	public static void page1(Player p){
		list.put(p, "1");
		
		p.sendMessage("§3Dun> §f編輯副本結束觸發");
		p.sendMessage("§61. §e新增結束觸發");
		p.sendMessage("§62. §e編輯結束觸發");
		p.sendMessage("§63. §e移除結束觸發");
		p.sendMessage("§64. §3退出編輯");
		p.sendMessage("§6\n§6當§e其中一個§6事件(Event)觸發後配給予其獎勵(Bonus)");
		p.sendMessage("§6同一個事件類型可以有§e不同§6的觸發");
	}
	
	public static void page1_1(Player p){
		list.put(p, "1_1");
		
		p.sendMessage("§3Dun> §f新增結束觸發");
		p.sendMessage("§e填寫觸發自訂名字，填寫\"back\"退出編輯");
	}
	
	public static void page1_2(Player p){
		list.put(p, "1_2");
		
		String name = GameUtil.getSelect(p);
		Set<String> list = ConfigUtil.getQuit(name);
		p.sendMessage("§3Dun> §f編輯結束觸發");
		p.sendMessage("§e結束觸發列表");
		for(String str : list){
			p.sendMessage("§6 - §f" + str);
		}
		p.sendMessage("§e填寫要編輯的觸發的名字，填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0(Player p){
		list.put(p, "1_2_0");
		
		String name = GameUtil.getSelect(p);
		String quit = GameUtil.getSelectEnd(p);
		String type = ConfigUtil.getQuit_Type(name,quit);
		String info = ConfigUtil.getQuit_Info(name,quit);
		p.sendMessage("§3Dun> §f編輯結束觸發");
		p.sendMessage("§61. §e觸發: " +  ConfigUtil.toString(type,info));
		p.sendMessage("§62. §e結束獎勵: " +  (ConfigUtil.hasQuit_Bonus(name,quit) ? "§a(已經設置)" : "§7(沒有設置)"));
		p.sendMessage("§63. §3退出編輯");
	}
	
	public static void page1_2_0_1(Player p){
		list.put(p, "1_2_0_1");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發條件");
		p.sendMessage("§61. §e玩家死亡");
		p.sendMessage("§62. §e實體死亡");
		p.sendMessage("§63. §e點擊實體");
		p.sendMessage("§64. §e破壞方塊");
		p.sendMessage("§65. §e點擊方塊");
		p.sendMessage("§66. §e進入位置");
		p.sendMessage("§67. §e時間");
		p.sendMessage("§68. §3退出編輯");
	}
	
	public static void page1_2_0_1_1(Player p){
		list.put(p, "1_2_0_1_1");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發細節");
		p.sendMessage("§e填寫幾多個進入副本的玩家死亡觸發該事件");
		p.sendMessage("§e例如填寫\"4\"代表4個，填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0_1_2(Player p){
		list.put(p, "1_2_0_1_2");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發細節");
		p.sendMessage("§e填寫<x>個名字為<y>的實體死亡觸發該事件");
		p.sendMessage("§e格式: \"<x> <y>\"，例如填寫\"10 &c土匪\"");
		p.sendMessage("§E填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0_1_3(Player p){
		list.put(p, "1_2_0_1_3");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發細節");
		p.sendMessage("§e填寫該名字的實體被點擊後會觸發事件");
		p.sendMessage("§E填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0_1_4(Player p){
		list.put(p, "1_2_0_1_4");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發細節");
		p.sendMessage("§e填寫破壞<x>個種類為<y>的方塊觸發該事件");
		p.sendMessage("§e格式: \"<x> <y>\"，例如填寫\"3 glass\"");
		p.sendMessage("§E填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0_1_5(Player p){
		list.put(p, "1_2_0_1_5");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發細節");
		p.sendMessage("§e填寫該座標的方塊被點擊後會觸發事件");
		p.sendMessage("§e格式: \"<x> <y> <z>\"，例如填寫\"0 64 0\"");
		p.sendMessage("§E填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0_1_6(Player p){
		list.put(p, "1_2_0_1_6");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發細節");
		p.sendMessage("§e填寫進入該座標後會觸發事件");
		p.sendMessage("§e格式: \"<x> <y> <z>\"，例如填寫\"0 64 0\"");
		p.sendMessage("§E填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0_1_7(Player p){
		list.put(p, "1_2_0_1_7");
		
		p.sendMessage("§3Dun> §f編輯結束觸發觸發細節");
		p.sendMessage("§e遊玩時間達到幾多秒時會觸發事件");
		p.sendMessage("§E填寫\"back\"退出編輯");
	}
	
	public static void page1_2_0_2(Player p){
		list.put(p, "1_2_0_2");
		
		String name = GameUtil.getSelect(p);
		String quit = GameUtil.getSelectEnd(p);
		p.sendMessage("§3Dun> §f編輯結束觸發獎勵");
		p.sendMessage("§61. §e指令" + (ConfigUtil.getQuit_Bonus_Command(name, quit) != null  ? "§a(已經設置)" : "§7(沒有設置)"));
		p.sendMessage("§62. §e金錢" + (ConfigUtil.getQuit_Bonus_Money(name, quit) != -1  ? "§a(已經設置)" : "§7(沒有設置)"));
		p.sendMessage("§63. §e經驗值" + (ConfigUtil.getQuit_Bonus_Exp(name, quit) != -1  ? "§a(已經設置)" : "§7(沒有設置)"));
		p.sendMessage("§64. §e物品" + (ConfigUtil.getQuit_Bonus_Item(name, quit) != null  ? "§a(已經設置)" : "§7(沒有設置)"));
		p.sendMessage("§65. §e位置" + (ConfigUtil.getQuit_Bonus_Location(name, quit) != null  ? "§a(已經設置)" : "§7(沒有設置)"));
		p.sendMessage("§66. §3退出編輯");
	}
	
	public static void page1_2_0_2_1(Player p){
		list.put(p, "1_2_0_2_1");
		
		String name = GameUtil.getSelect(p);
		String quit = GameUtil.getSelectEnd(p);
		String cmd = ConfigUtil.getQuit_Bonus_Command(name, quit);
		
		p.sendMessage("§e填寫結束後系統會自動執行的指令，目前" + 
				(cmd == null ? "沒有設置" : "設定§f" + cmd ));
		p.sendMessage("§e指令包含 <PLAYER>可取得玩家名稱");
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該獎勵");
	}
	
	public static void page1_2_0_2_2(Player p){
		list.put(p, "1_2_0_2_2");
		
		String name = GameUtil.getSelect(p);
		String quit = GameUtil.getSelectEnd(p);
		int m = ConfigUtil.getQuit_Bonus_Money(name, quit);
		
		p.sendMessage("§e填寫結束後玩家獲得多少金錢，目前" + 
				(m == -1 ? "沒有設置" : "設定§f$" + m ));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該獎勵");
	}
	
	public static void page1_2_0_2_3(Player p){
		list.put(p, "1_2_0_2_3");
		
		String name = GameUtil.getSelect(p);
		String quit = GameUtil.getSelectEnd(p);
		int m = ConfigUtil.getQuit_Bonus_Exp(name, quit);
		
		p.sendMessage("§e填寫結束後玩家獲得多少經驗值，目前" + 
				(m == -1 ? "沒有設置" : "設定§f" + m ));
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該獎勵");
	}
	
	public static void page1_2_0_2_4(Player p){
		list.put(p, "1_2_0_2_4");
		
		String name = GameUtil.getSelect(p);
		String quit = GameUtil.getSelectEnd(p);
		String item = ConfigUtil.getQuit_Bonus_Item(name, quit);
		
		p.sendMessage("§e填寫結束後玩家獲得的物品" + 
				(item == null ? "，目前沒有設置" : "" ));
		p.sendMessage("§e填寫\"add\"增加手上的物品到獲得列表中");
		p.sendMessage("§e填寫\"delete <x>\"移除列表中第x項的物品");
		p.sendMessage("§e填寫\"back\"退出編輯");
		if(item != null){
			List<ItemStack> item_list = ConfigUtil.getItemList(item);
			p.sendMessage("§e獲得列表:");
			for(int i = 0; i < item_list.size(); i ++){
				p.sendMessage("§6" + (i+1) + ". §f" + ItemsUtil.GetName(item_list.get(i)));
			}
		}
	}
	
	public static void page1_2_0_2_5(Player p){
		list.put(p, "1_2_0_2_5");
		
		String name = GameUtil.getSelect(p);
		String quit = GameUtil.getSelectEnd(p);
		String loc = ConfigUtil.getQuit_Bonus_Location(name, quit);
		
		p.sendMessage("§e填寫結束後玩家會傳送到什麼地方，目前" + 
				(loc == null ? "沒有設置" : "設定§f" + loc ));
		p.sendMessage("§e填寫\"here\"觸發後傳送到你的位置");
		p.sendMessage("§e填寫\"back\"退出編輯，填寫\"delete\"取消該獎勵");
	}
	
	public static void page1_3(Player p){
		list.put(p, "1_3");
		
		String name = GameUtil.getSelect(p);
		Set<String> list = ConfigUtil.getQuit(name);
		p.sendMessage("§3Dun> §f移除結束觸發");
		p.sendMessage("§e結束觸發列表");
		for(String str : list){
			p.sendMessage("§6 - §f" + str);
		}
		p.sendMessage("§e填寫要移除的觸發的名字，填寫\"back\"退出編輯");
	}
}
