package io.Jerry.Dungeon.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	public static void copyFolder(File src, File dest)
	    	throws IOException{
	    	
	    	if(src.isDirectory()){
	    		
	    		//if directory not exists, create it
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		}
	    		
	    		//list all the directory contents
	    		String files[] = src.list();
	    		
	    		for (String file : files) {
	    		   //construct the src and dest file structure
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   //recursive copy
	    		   copyFolder(srcFile,destFile);
	    		}
	    	   
	    	}else{
	    		//if file, then copy it
	    		//Use bytes stream to support all file types
	    		InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest); 
	    	                     
	    	        byte[] buffer = new byte[1024];
	    	    
	    	        int length;
	    	        //copy the file content in bytes 
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }
	 
	    	        in.close();
	    	        out.close();
	    	}
	    }
	
	public static void cleanDirectory(File directory) throws IOException{
		if (!directory.exists()) {
			String message = directory + " does not exist";
			throw new IllegalArgumentException(message);
		}
			/*      */     
		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}
			/*      */     
		File[] files = directory.listFiles();
		if (files == null) {
			throw new IOException("Failed to list contents of " + directory);
		}
			/*      */     
		IOException exception = null;
		for (File file : files) {
			try {
				forceDelete(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}
			/*      */     
		if (null != exception) {
			throw exception;
		}
	}
	
	public static void deleteDirectory(File directory) throws IOException{
		if (!directory.exists()) {
			return;
		}
			/*      */     
		cleanDirectory(directory);
			/*      */     
		if (!directory.delete()) {
			String message = "Unable to delete directory " + directory + ".";
			/*      */       
			throw new IOException(message);
		}
	}
	
	public static void forceDelete(File file) throws IOException{
		if (file.isDirectory()) {
			deleteDirectory(file);
		} else {
			boolean filePresent = file.exists();
			if (!file.delete()) {
				if (!filePresent) {
					throw new FileNotFoundException("File does not exist: " + file);
				}
				String message = "Unable to delete file: " + file;
				/*      */         
				throw new IOException(message);
			}
		}
	}
}
