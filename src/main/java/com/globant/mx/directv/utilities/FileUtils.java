package com.globant.mx.directv.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.asm.Advice.This;

public class FileUtils {
	private static final Logger logger = Logger.getLogger(This.class.getName());
	
	
	
	public static String readPropertiesFile(String properti) {
		String propName= "setup.properties";
		File driverArchive = new File(propName);
		String path = driverArchive.getAbsolutePath();
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(path);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	    	 logger.log(Level.SEVERE, "FileNotFoundException: " +fnfe.getMessage());
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	    	 logger.log(Level.SEVERE, "IOEXCEPTION: " +ioe.getMessage());
	         ioe.printStackTrace();
	      } finally {
	         try {
				fis.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error close " + e.getMessage());
			}
	      }
	      return prop.getProperty(properti);
	   }
	

}
