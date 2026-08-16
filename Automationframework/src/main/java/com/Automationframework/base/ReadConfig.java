package com.Automationframework.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
 public static Properties prop;
 public static String config_path="C:\\Users\\Shivanand\\eclipse-workspace\\Automationframework\\src\\main\\resources\\config.properties";
 
 public static void loadprop() throws IOException {
		
		FileInputStream config = new FileInputStream(config_path);
		prop= new Properties();
		prop.load(config);
		
		 }
 public static String getConfigdata(String value) {
	 
        return prop.getProperty(value);
 
        }
 
 public static void setconfigdata(String Key, String value) {
	 
	    prop.setProperty(Key, value);
	   try(FileOutputStream Fos= new FileOutputStream(config_path)){
	    	prop.store(Fos, " Value saved in properties file");
	    	
	    }catch(IOException e) {
	    	
	    	e.printStackTrace();
	    }
	 
      }
  }
