package io.Jerry.Dungeon.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Updater {
	private static String downloadUrl;
	private static String InfoUrl;
	private static String Version;
	
	public static boolean update(File f){
		try{
			URL website = new URL(downloadUrl);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(f);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	public static boolean check() {
		try{
			URL url = new URL("http://pastebin.com/raw/T5iGDenN");
			URLConnection conn = url.openConnection();
			conn.setReadTimeout(5000);
			conn.setDoOutput(true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String response = "";
			String str = null;
			while((str = reader.readLine()) != null){
				response = response + str;
			}
	    	JSONArray array = (JSONArray)JSONValue.parse(response);
	    	if (array.size() == 0){
	    		return false;
	    	}
	    	JSONObject obj = ((JSONObject)array.get(array.size() - 1));
	    	downloadUrl = (String) obj.get("downloadUrl");
	    	InfoUrl = (String) obj.get("infoUrl");
	    	Version = (String) obj.get("version");
	    	return true;
	    }catch (Exception e){
	    	e.printStackTrace();
		    return false;
	    }
	}

	public static String getDownloadUrl() {
		return downloadUrl;
	}

	public static String getInfoUrl() {
		return InfoUrl;
	}

	public static String getVersion() {
		return Version;
	}

}
