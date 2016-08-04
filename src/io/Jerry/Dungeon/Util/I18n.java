package io.Jerry.Dungeon.Util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class I18n {
	private static ResourceBundle res;
	private static Map<String, MessageFormat> messageFormatCache = new HashMap<String, MessageFormat>();
	
	public static void run(){
		res = ResourceBundle.getBundle("messages", Locale.getDefault());
		//res = ResourceBundle.getBundle("messages", new Locale("en", "US"));
	}
	
	public static String t(String str, Object... obj){
		String format = res.getString(str);
		MessageFormat messageFormat = (MessageFormat)messageFormatCache.get(format);
	    if (messageFormat == null){
	    	try{
	    		messageFormat = new MessageFormat(format);
	    	}catch (IllegalArgumentException e){
	    		format = format.replaceAll("\\{(\\D*?)\\}", "\\[$1\\]");
	        	messageFormat = new MessageFormat(format);
	    	}
	    	messageFormatCache.put(format, messageFormat);
	   }
	   return messageFormat.format(obj);
	}
}
