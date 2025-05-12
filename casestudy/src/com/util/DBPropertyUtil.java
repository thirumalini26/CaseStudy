package com.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
	
	//Two static variables created 
	private static final String File_Path ="resources/db.properties";
	private static Properties properties;
	
	static {
		
		
		
		try {
			
			// For reading the file 
			FileInputStream fis = new FileInputStream(File_Path);
			properties = new Properties();
		
			properties.load(fis);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();  
		}
	
		
		
	}
	
	
		public static String get(String key) {
	
		return properties.getProperty(key);
	}

}