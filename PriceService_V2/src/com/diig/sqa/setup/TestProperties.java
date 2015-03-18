package com.diig.sqa.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import com.diig.sqa.utilities.Logging;

public class TestProperties {
	
	Object obj = TestProperties.class.getName();
	String str, key;
	
	Logging logger = new Logging();
	
	/*String log4ConfPath = System.getProperty("user.dir")+"//TestSuite//resources//log4j.properties";
	*PropertyConfigurator.configure(log4ConfPath);
	*/
	String confPath = System.getProperty("user.dir");
	
	private String filepath;
	
	public TestProperties(String filepath){
		this.filepath=filepath;
		Logging.startLogging(obj.toString());
		}
	  
	public String ReadProperty(String strClass,String propkey){
		String propval="";
		  
		try{
			int check = 0;
			while(check == 0){
				check = 1;
				File cfgfile = new File(filepath);
				if(cfgfile.exists()){
					Properties props = new Properties();
					FileInputStream propin = new FileInputStream(cfgfile);
					props.load(propin);
					propval=props.getProperty(propkey);
					}
				else{
					check = 0;
					}
				}
			}
		catch(IOException e){
			e.printStackTrace();
			}
		if(propkey.contentEquals("browser")){
			}
		else{
			logger.logInfo(obj.toString(),"Get Configuration Property for "+propkey+" = "+propval);
			}
		return propval;
		}
	
	public String ReadProperty(String propkey){
		String propval="";
		  
		try{
			int check = 0;
			while(check == 0){
				check = 1;
				File cfgfile = new File(filepath);
				if(cfgfile.exists()){
					Properties props = new Properties();
					FileInputStream propin = new FileInputStream(cfgfile);
					props.load(propin);
					propval=props.getProperty(propkey);
					}
				else{
					check = 0;
					}
				}
			}
		catch(IOException e){
			e.printStackTrace();
			}
		if(propkey.contentEquals("browser")){
			}
		else{
			logger.logInfo(obj.toString(),"Get Configuration Property for "+propkey+" = "+propval);
			}
		return propval;
		}
	
}
