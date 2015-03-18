package com.diig.sqa.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

import com.diig.sqa.setup.TestProperties;


public class FileHandler {

	static TestProperties properties = new TestProperties("resources/test.properties");
	
	static Object obj = FileHandler.class.getName();
	
	
	static Logging log = new Logging();
	
	//static Browser br = new Browser();
	
	public static void createTestReport(ArrayList<String> arrayList,String strHtmlFilename,String strTime, String strUrl){
		String []aResults;
		String strCssLocation = "file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\assets\\css\\bootstrap.css";
		String strJQueryLocation = "file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\assets\\js\\jquery-1.11.1.js";
		String h1="<html><head><meta charset=\"utf-8\"><title>Automated Test Execution Report</title><meta name=\"description\" content=\"\"><meta name=\"author\" content=\"\">";
		String h2="<link href=\""+strCssLocation+"\" rel=\"stylesheet\"><script type=\"text/javascript\" src=\""+strJQueryLocation+"\"></script>";
		String h3="<script type=\"text/javascript\">";
		String h4="$(function(){var fail = 'Failed';$(\"table tr td\").filter(function() {return $(this).text() == fail;}).parent('tr').css('color','#FF0000');});";
		String h5="$(function(){ var pass = 'Passed'; $(\"table tr td\").filter(function() {return $(this).text() == pass;}).parent('tr').css('color','#00FF00');});";
		String h6="</script></head><body><table id=\"table\" class=\"table table-striped table-bordered table-condensed\">";
		String h7="<thead class=\"thead\"><tr><th>Test Ref</th><th>Step No</th><th>Description</th><th>Status</th><th>Message</th><th>Evidence</th></tr></thead><tbody class=\"tbody\">";
		String h8="",h9="";
		for(int a=0;a<arrayList.size();a++){
			
			aResults=arrayList.get(a).split("@");
			
			if(aResults.length>5){
				h8=h8+"<tr><td>"+aResults[0]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+aResults[5]+"</td></tr>";
				}
			else{
				h8=h8+"<tr><td>"+aResults[0]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+"..."+"</td></tr>";
				}
			}
		
		h9="</tbody><tfoot><p><h2 align=\"center\">Tests executed using "+properties.ReadProperty(obj.toString(), "browser")+". Date & Time Completed: - "+strTime+"</h2></p><p><h3 align=\"center\">Application Under Test = "+ strUrl +"</p></tfoot></table></body></html>";
		
		String strHtmlContents = h1+h2+h3+h4+h5+h6+h7+h8+h9;
		
		writeToFile(strHtmlFilename, strHtmlContents);
		log.logInfo(obj.toString(), "Writing Results...");
	}
	
	public static void createLocalTestReport(ArrayList<String> arrayList,String strHtmlFilename,String strTime, String strUrl){
		String []aResults;
		//String strCssLocation = "\\"+"\\"+"khwpfsr001.eu.local\\CSF\\IT DEPARTMENT\\PMO\\2014 Projects\\Test Automation\\assets\\css\\bootstrap.css";		
		//String strJQueryLocation = "\\"+"\\"+"khwpfsr001.eu.local\\CSF\\IT DEPARTMENT\\PMO\\2014 Projects\\Test Automation\\assets\\js\\jquery-1.11.1.js";
		String strCssLocation = "file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\assets\\css\\bootstrap.css";
		String strJQueryLocation = "file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\assets\\js\\jquery-1.11.1.js";
		String h1="<html><head><meta charset=\"utf-8\"><title>Automated Test Execution Report</title><meta name=\"description\" content=\"\"><meta name=\"author\" content=\"\">";
		String h2="<link href=\""+strCssLocation+"\" rel=\"stylesheet\"><script type=\"text/javascript\" src=\""+strJQueryLocation+"\"></script>";
		String h3="<script type=\"text/javascript\">";
		String h4="$(function(){var fail = 'Failed';$(\"table tr td\").filter(function() {return $(this).text() == fail;}).parent('tr').css('color','#FF0000');});";
		String h5="$(function(){ var pass = 'Passed'; $(\"table tr td\").filter(function() {return $(this).text() == pass;}).parent('tr').css('color','#00FF00');});";
		String h6="</script></head><body><table id=\"table\" class=\"table table-striped table-bordered table-condensed\">";
		String h7="<thead class=\"thead\"><tr><th>Test Ref</th><th>Step No</th><th>Description</th><th>Status</th><th>Message</th><th>Evidence</th></tr></thead><tbody class=\"tbody\">";
		String h8="",h9="";
		for(int a=0;a<arrayList.size();a++){
			
			aResults=arrayList.get(a).split("@");
			
			if(aResults.length>5){
				h8=h8+"<tr><td>"+aResults[0]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+aResults[5]+"</td></tr>";
				}
			else{
				h8=h8+"<tr><td>"+aResults[0]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+"..."+"</td></tr>";
				}
			}
		
		h9="</tbody><tfoot><p><h2 align=\"center\">Tests executed using "+properties.ReadProperty(obj.toString(), "browser")+". Date & Time Completed: - "+strTime+"</h2></p><p><h3 align=\"center\">Application Under Test = "+ strUrl +"</p></tfoot></table></body></html>";
		
		String strHtmlContents = h1+h2+h3+h4+h5+h6+h7+h8+h9;
		
		writeToFile(strHtmlFilename, strHtmlContents);
		log.logInfo(obj.toString(), "Writing Results...");
	}
	
	
	public static void reportCreator(Map<Integer,String> mResults,String strHtmlFilename,String strTime, String strUrl){
		String []aResults;

		String strCssLocation = "file:///"+System.getProperty("user.dir")+"\\reports\\assets\\css\\bootstrap.css";
		String strJQueryLocation = "file:///"+System.getProperty("user.dir")+"\\reports\\assets\\js\\jquery-1.11.1.js";
		String h1="<html><head><meta charset=\"utf-8\"><title>Automated Test Execution Report</title><meta name=\"description\" content=\"\"><meta name=\"author\" content=\"\">";
		String h2="<link href=\""+strCssLocation+"\" rel=\"stylesheet\"><script type=\"text/javascript\" src=\""+strJQueryLocation+"\"></script>";
		String h3="<script type=\"text/javascript\">";
		String h4="$(function(){var fail = 'Failed';$(\"table tr td\").filter(function() {return $(this).text() == fail;}).parent('tr').css('color','#FF0000');});";
		String h5="$(function(){ var pass = 'Passed'; $(\"table tr td\").filter(function() {return $(this).text() == pass;}).parent('tr').css('color','#00FF00');});";
		String h6="</script></head><body><table id=\"table\" class=\"table table-striped table-bordered table-condensed\">";
		String h7="<thead class=\"thead\"><tr><th>Test Ref</th><th>Step No</th><th>Description</th><th>Status</th><th>Message</th><th>Evidence</th></tr></thead><tbody class=\"tbody\">";
		String h8="",h9="";
		
		for(int a=1;a<=mResults.size();a++){
			
			aResults=mResults.get(a).split("@");
			String [] aTmpArray=aResults[0].split(":");
			int iSize = aTmpArray.length;
			if(aResults.length>5){
				try{
					if(iSize==1){
						h8=h8+"<tr><td>"+aTmpArray[0]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+aResults[5]+"</td></tr>";
					}
					if(iSize>1){
						h8=h8+"<tr><td>"+aTmpArray[1]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+aResults[5]+"</td></tr>";
					}
				}
				catch(Exception exp){
					log.logInfo(obj.toString(), "Num : - "+a);
					exp.getMessage();
					exp.printStackTrace();
				}
				}
			else{
				try{
					if(iSize==1){
						h8=h8+"<tr><td>"+aTmpArray[0]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+"..."+"</td></tr>";
					}
					if(iSize>1){
						h8=h8+"<tr><td>"+aTmpArray[1]+"</td><td>"+aResults[1]+"</td><td>"+aResults[2]+"</td><td>"+aResults[3]+"</td><td>"+aResults[4]+"</td><td>"+"..."+"</td></tr>";
					}
				}
				catch(Exception e){
					log.logInfo(obj.toString(), "Num : - "+a);
					e.getMessage();
					e.printStackTrace();
				}
				}
			}
		
		h9="</tbody><tfoot><p><h2 align=\"center\">Tests executed successfully. Date & Time Completed: - "+strTime+"</h2></p><p><h3 align=\"center\">Application Under Test = "+ strUrl +"</p></tfoot></table></body></html>";
		
		String strHtmlContents = h1+h2+h3+h4+h5+h6+h7+h8+h9;
		
		writeToFile(strHtmlFilename, strHtmlContents);
		log.logInfo(obj.toString(), "Writing Results...");
	}
	
	public static void writeToFile(String strFile, String strFileContents){
		  try{
			  // Create file 
			  FileWriter fstream = new FileWriter(strFile);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(strFileContents);
			  //Close the output stream
			  out.close();
			  log.logInfo(obj.toString(), "Writing Test Results To "+strFile);
			  }
		  catch (Exception e){
			  //Catch exception if any
			  //System.err.println("Error: " + e.getMessage());
			  log.logInfo(obj.toString(), "Error: " + e.getMessage());
			  }
		  
		}
	
}
