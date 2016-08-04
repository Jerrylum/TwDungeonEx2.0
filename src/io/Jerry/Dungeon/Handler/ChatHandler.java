package io.Jerry.Dungeon.Handler;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import io.Jerry.Dungeon.Util.ChatUtil;
import io.Jerry.Dungeon.Util.ConfigUtil;
import io.Jerry.Dungeon.Util.GameUtil;
import io.Jerry.Dungeon.Util.I18n;

@SuppressWarnings("deprecation")
public class ChatHandler {
	public static boolean Chat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		String str = ChatUtil.list.get(p);
		if(str == null){
			return false;
		}
		e.setCancelled(true);
		String msg = e.getMessage();
		String name = GameUtil.getSelect(p);
		if(name == null){
			ChatUtil.leave(p);
			p.sendMessage("¡±3Dun> ¡±f" + I18n.t("GuiHandler.Select"));	
			return true;
		}
		
		if(str.equals("0")){
			if(msg.equals("1")){
				ChatUtil.page0_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page0_2(p);
			}else if(msg.equals("3")){
				ChatUtil.leave(p);
				GameUtil.selectGame(p, name);
			}else{
				ChatUtil.page0(p);
			}
		}else if(str.equals("0_1")){
			if(msg.equals("1")){
				ChatUtil.page0_1_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page0_1_2(p);
			}else if(msg.equals("3")){
				ChatUtil.page0_1_3(p);
			}else if(msg.equals("4")){
				ChatUtil.page0(p);
			}else{
				ChatUtil.page0_1(p);
			}
			
		}else if(str.equals("0_1_1")){
			if(msg.equals("delete")){
				ConfigUtil.setJoin_ClickEntity(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_1(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_1(p);
			}else{
				ConfigUtil.setJoin_ClickEntity(name, msg);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_1(p);
			}
		}else if(str.equals("0_1_2")){
			if(msg.equals("1")){
				ChatUtil.page0_1_2_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page0_1_2_2(p);
			}else if(msg.equals("3")){
				ChatUtil.page0_1(p);
			}else{
				ChatUtil.page0_1_2(p);
			}
		}else if(str.equals("0_1_2_1")){
			if(msg.equals("here")){
				ConfigUtil.setJoin_ClickBlock(name, p.getLocation().add(0, -1, 0));
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_2_1(p);
			}else if(msg.equals("delete")){
				ConfigUtil.setJoin_ClickBlock(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_2_1(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_1_2(p);
			}else{
				ChatUtil.page0_1_2_1(p);
			}
		}else if(str.equals("0_1_2_2")){
			if(msg.equals("hand")){
				ConfigUtil.setJoin_ClickBlock_useItem(name, p.getItemInHand());
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_2_2(p);
			}else if(msg.equals("delete")){
				ConfigUtil.setJoin_ClickBlock_useItem(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_2_2(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_1_2(p);
			}else{
				ChatUtil.page0_1_2_2(p);
			}
		}else if(str.equals("0_1_3")){
			if(msg.equals("delete")){
				ConfigUtil.setJoin_ToLocation(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_3(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_1(p);
			}else{
				ConfigUtil.setJoin_ToLocation(name, p.getLocation());
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_1_3(p);
			}
		}else if(str.equals("0_2")){
			if(msg.equals("1")){
				ChatUtil.page0_2_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page0_2_2(p);
			}else if(msg.equals("3")){
				ChatUtil.page0_2_3(p);
			}else if(msg.equals("4")){
				ChatUtil.page0_2_4(p);
			}else if(msg.equals("5")){
				ChatUtil.page0(p);
			}else{
				ChatUtil.page0_1(p);
			}
			
		}else if(str.equals("0_2_1")){
			if(msg.equals("delete")){
				ConfigUtil.setNeed_JoinSize_Mix(name, null);
				ConfigUtil.setNeed_JoinSize_Max(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_2_1(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_2(p);
			}else{
				try{
					String[] split = msg.split("~");
					int mix = Integer.parseInt(split[0]);
					int max = Integer.parseInt(split[1]);
					
					if(mix < 1 || max < 1){
						throw new Exception();
					}
					
					ConfigUtil.setNeed_JoinSize_Mix(name, mix <= 0 ? null : mix);
					ConfigUtil.setNeed_JoinSize_Max(name, max <= 0 ? null : max);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
				}
				ChatUtil.page0_2_1(p);
			}
			
		}else if(str.equals("0_2_2")){
			if(msg.equals("delete")){
				ConfigUtil.setNeed_Money(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_2_2(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_2(p);
			}else{
				try{
					int m = Integer.parseInt(msg);
				
					if(m <= 0){
						throw new Exception();
					}
					
					ConfigUtil.setNeed_Money(name, m);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
				}
				ChatUtil.page0_2_2(p);
			}
			
		}else if(str.equals("0_2_3")){
			if(msg.equals("delete")){
				ConfigUtil.setNeed_Permission(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_2_3(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_2(p);
			}else{
				ConfigUtil.setNeed_Permission(name, msg);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_2_3(p);
			}
			
		}else if(str.equals("0_2_4")){
			if(msg.equals("delete")){
				ConfigUtil.setNeed_Exp_Mix(name, null);
				ConfigUtil.setNeed_Exp_Max(name, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page0_2_4(p);
			}else if(msg.equals("back")){
				ChatUtil.page0_2(p);
			}else{
				try{
					String[] split = msg.split("~");
					int mix = Integer.parseInt(split[0]);
					int max = Integer.parseInt(split[1]);
					
					if(mix < 1 || max < 0){
						throw new Exception();
					}
					
					ConfigUtil.setNeed_Exp_Mix(name, mix <= 0 ? null : mix);
					ConfigUtil.setNeed_Exp_Max(name, max <= 0 ? null : max);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
				}
				ChatUtil.page0_2_4(p);
			}
			
		}else if(str.equals("1")){
			if(msg.equals("1")){
				ChatUtil.page1_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page1_2(p);
			}else if(msg.equals("3")){
				ChatUtil.page1_3(p);
			}else if(msg.equals("4")){
				ChatUtil.leave(p);
				GameUtil.selectGame(p, name);
			}else{
				ChatUtil.page1(p);
			}
		}else if(str.equals("1_1")){
			if(msg.equals("back")){
				ChatUtil.page1(p);
			}else{
				ConfigUtil.createQuit(name,msg);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				GameUtil.selectEnd(p, msg);
				ChatUtil.page1_2_0(p);
			}
			
		}else if(str.equals("1_2")){
			if(msg.equals("back")){
				ChatUtil.page1(p);
			}else{
				if(ConfigUtil.getQuit(name).contains(msg)){
					GameUtil.selectEnd(p, msg);
					ChatUtil.page1_2_0(p);
				}else{
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.NoStopCase"));
					ChatUtil.page1_2(p);
				}
			}
		}else if(str.equals("1_2_0")){
			if(msg.equals("1")){
				ChatUtil.page1_2_0_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page1_2_0_2(p);
			}else if(msg.equals("3")){
				GameUtil.selectEnd(p, null);
				ChatUtil.page1(p);
			}else{
				ChatUtil.page1_2_0(p);
			}
		}else if(str.equals("1_2_0_1")){
			if(msg.equals("1")){
				ChatUtil.page1_2_0_1_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page1_2_0_1_2(p);
			}else if(msg.equals("3")){
				ChatUtil.page1_2_0_1_3(p);
			}else if(msg.equals("4")){
				ChatUtil.page1_2_0_1_4(p);
			}else if(msg.equals("5")){
				ChatUtil.page1_2_0_1_5(p);
			}else if(msg.equals("6")){
				ChatUtil.page1_2_0_1_6(p);
			}else if(msg.equals("7")){
				ChatUtil.page1_2_0_1_7(p);
			}else if(msg.equals("8")){
				ChatUtil.page1_2_0(p);
			}else{
				ChatUtil.page1_2_0_1(p);
			}
		}else if(str.equals("1_2_0_1_1")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0(p);
			}else{
				try{
					Integer.parseInt(msg);
					
					String quit = GameUtil.getSelectEnd(p);
					ConfigUtil.setQuit_Type(name, quit, "PD");
					ConfigUtil.setQuit_Info(name, quit, msg);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2_0(p);
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
					ChatUtil.page1_2_0_1_1(p);
				}
				
			}
		}else if(str.equals("1_2_0_1_2")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0(p);
			}else{
				try{
					String[] split = msg.split(" ");
					Integer.parseInt(split[0]);
					
					String quit = GameUtil.getSelectEnd(p);
					ConfigUtil.setQuit_Type(name, quit, "ED");
					ConfigUtil.setQuit_Info(name, quit, split[0] + "," + split[1]);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2_0(p);
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
					ChatUtil.page1_2_0_1_2(p);
				}
				
			}
		}else if(str.equals("1_2_0_1_3")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0(p);
			}else{
				try{
					String quit = GameUtil.getSelectEnd(p);
					ConfigUtil.setQuit_Type(name, quit, "CE");
					ConfigUtil.setQuit_Info(name, quit, msg);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2_0(p);
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
					ChatUtil.page1_2_0_1_3(p);
				}
				
			}
		}else if(str.equals("1_2_0_1_4")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0(p);
			}else{
				try{
					String[] split = msg.split(" ");
					Integer.parseInt(split[0]);
					
					String quit = GameUtil.getSelectEnd(p);
					ConfigUtil.setQuit_Type(name, quit, "BB");
					ConfigUtil.setQuit_Info(name, quit, split[0] + "," + split[1]);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2_0(p);
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
					ChatUtil.page1_2_0_1_4(p);
				}
				
			}
		}else if(str.equals("1_2_0_1_5")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0(p);
			}else{
				try{
					String[] split = msg.split(" ");
					Integer.parseInt(split[0]);
					Integer.parseInt(split[1]);
					Integer.parseInt(split[2]);
					
					String quit = GameUtil.getSelectEnd(p);
					ConfigUtil.setQuit_Type(name, quit, "CB");
					ConfigUtil.setQuit_Info(name, quit, split[0] + "," + split[1] + "," + split[2]);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2_0(p);
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
					ChatUtil.page1_2_0_1_5(p);
				}
				
			}
		}else if(str.equals("1_2_0_1_6")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0(p);
			}else{
				try{
					String[] split = msg.split(" ");
					Integer.parseInt(split[0]);
					Integer.parseInt(split[1]);
					Integer.parseInt(split[2]);
					
					String quit = GameUtil.getSelectEnd(p);
					ConfigUtil.setQuit_Type(name, quit, "TL");
					ConfigUtil.setQuit_Info(name, quit, split[0] + "," + split[1] + "," + split[2]);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2_0(p);
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
					ChatUtil.page1_2_0_1_6(p);
				}
				
			}
		}else if(str.equals("1_2_0_1_7")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0(p);
			}else{
				try{					
					Integer.parseInt(msg);
					String quit = GameUtil.getSelectEnd(p);
					ConfigUtil.setQuit_Type(name, quit, "T");
					ConfigUtil.setQuit_Info(name, quit, msg);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2_0(p);
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
					ChatUtil.page1_2_0_1_7(p);
				}
				
			}
		}else if(str.equals("1_2_0_2")){
			if(msg.equals("1")){
				ChatUtil.page1_2_0_2_1(p);
			}else if(msg.equals("2")){
				ChatUtil.page1_2_0_2_2(p);
			}else if(msg.equals("3")){
				ChatUtil.page1_2_0_2_3(p);
			}else if(msg.equals("4")){
				ChatUtil.page1_2_0_2_4(p);
			}else if(msg.equals("5")){
				ChatUtil.page1_2_0_2_5(p);
			}else if(msg.equals("6")){
				ChatUtil.page1_2_0(p);
			}else{
				ChatUtil.page1_2_0_2(p);
			}
		}else if(str.equals("1_2_0_2_1")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0_2(p);
			}else if(msg.equals("delete")){
				String quit = GameUtil.getSelectEnd(p);
				ConfigUtil.setQuit_Bonus_Command(name, quit, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page1_2_0_2_1(p);
			}else{
				String quit = GameUtil.getSelectEnd(p);
				ConfigUtil.setQuit_Bonus_Command(name, quit, msg);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page1_2_0_2_1(p);
			}
		}else if(str.equals("1_2_0_2_2")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0_2(p);
			}else if(msg.equals("delete")){
				String quit = GameUtil.getSelectEnd(p);
				ConfigUtil.setQuit_Bonus_Money(name, quit, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page1_2_0_2_2(p);
			}else{
				String quit = GameUtil.getSelectEnd(p);
				try{
					ConfigUtil.setQuit_Bonus_Money(name, quit, Integer.parseInt(msg) );
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
				}
				ChatUtil.page1_2_0_2_2(p);
			}
		}else if(str.equals("1_2_0_2_3")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0_2(p);
			}else if(msg.equals("delete")){
				String quit = GameUtil.getSelectEnd(p);
				ConfigUtil.setQuit_Bonus_Exp(name, quit, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page1_2_0_2_3(p);
			}else{
				String quit = GameUtil.getSelectEnd(p);
				try{
					ConfigUtil.setQuit_Bonus_Exp(name, quit, Integer.parseInt(msg) );
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
				}
				ChatUtil.page1_2_0_2_3(p);
			}
		}else if(str.equals("1_2_0_2_4")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0_2(p);
			}else if(msg.equals("add")){
				String quit = GameUtil.getSelectEnd(p);
				ItemStack item = p.getItemInHand();
				
				if(item == null || item.getType() == Material.AIR){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.NoItem"));
				}
				
				List<ItemStack> item_list = ConfigUtil.getItemList(ConfigUtil.getQuit_Bonus_Item(name, quit));
				item_list.add(item);
				ConfigUtil.setQuit_Bonus_Item(name, quit, item_list);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page1_2_0_2_4(p);
			}else if(msg.startsWith("delete")){
				String quit = GameUtil.getSelectEnd(p);
				try{
					List<ItemStack> item_list = ConfigUtil.getItemList(ConfigUtil.getQuit_Bonus_Item(name, quit));
					item_list.remove(Integer.parseInt(msg.replace("delete ", ""))-1);
					ConfigUtil.setQuit_Bonus_Item(name, quit, item_list);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				}catch(Exception ex){
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Wrong"));
				}
				ChatUtil.page1_2_0_2_4(p);
			}else{
				ChatUtil.page1_2_0_2_4(p);
			}
		}else if(str.equals("1_2_0_2_5")){
			if(msg.equals("back")){
				ChatUtil.page1_2_0_2(p);
			}else if(msg.equals("delete")){
				String quit = GameUtil.getSelectEnd(p);
				ConfigUtil.setQuit_Bonus_Location(name, quit, null);
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page1_2_0_2_5(p);
			}else{
				String quit = GameUtil.getSelectEnd(p);
				ConfigUtil.setQuit_Bonus_Location(name, quit, p.getLocation());
				p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
				ChatUtil.page1_2_0_2_5(p);
			}
		}else if(str.equals("1_3")){
			if(msg.equals("back")){
				ChatUtil.page1(p);
			}else{
				if(ConfigUtil.getQuit(name).contains(msg)){
					ConfigUtil.deleteQuit(name,msg);
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.Done"));
					ChatUtil.page1_2(p);
				}else{
					p.sendMessage("¡±3Dun> ¡±f" + I18n.t("ChatHandler.NoStopCase"));
					ChatUtil.page1_2(p);
				}
				
			}
			
		}
		return true;
	}
}
