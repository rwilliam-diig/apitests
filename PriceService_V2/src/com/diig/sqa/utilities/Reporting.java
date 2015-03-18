package com.diig.sqa.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import com.diig.sqa.setup.TestProperties;



public class Reporting {

	
	static TestProperties properties = new TestProperties("resources/test.properties");
	
	FileHandler fh = new FileHandler();
	
	static Logging log = new Logging();

	static Object obj = Reporting.class.getName();
	
	private static boolean bGlobalReporting;
	
	private static int iTestStepsCount;
	
	private static int iTestStepsExecuted;

	public static Map<Integer,String> mapExecutionResults = new TreeMap<Integer, String>();
	
	private String strMessage;
	private static Map <Integer,String> mapTotalTestResults = new TreeMap<Integer, String>();
	private static Map <Integer,String> mapLoopedTestResults = new TreeMap<Integer, String>();
	public static Map <Integer,String> mapGlobalTestResults = new TreeMap<Integer, String>();
	public static Map <Integer,String> mapLocalTestResults = new TreeMap<Integer, String>();
	public static ArrayList <String> arrayList = new ArrayList<String>();
	
	public static ArrayList <String> arrayListResults = new ArrayList<String>();
	
	public static Map <Integer,String> mTestResults = new TreeMap<Integer,String>();
	
	public Reporting(){
		iTestStepsExecuted = 0;
		//turnOffGlobalReporting();
	}
	
	/**
	 * This method turns the global reporting flag on
	 */
	public void turnOnGlobalReporting(){
		log.logInfo(obj.toString(), "Turning global reporting on!");
		bGlobalReporting = true;
	}
	
	/**
	 * This method turns the global reporting flag off
	 */
	public void turnOffGlobalReporting(){
		log.logInfo(obj.toString(), "Turning global reporting off!");
		bGlobalReporting = false;
	}
	
	/**
	 * This method gets the status of the global reporting flag
	 * @return true or false
	 */
	private static boolean getGlobalReportingFlag(){
		return bGlobalReporting;
	}
	/**
	 * This method increases iTestStepsCount by 1
	 * @author rwilliams
	 */
	private void increaseTestStepsCount(){
		iTestStepsCount = iTestStepsCount + 1;
	}
	
	/**
	 * This method gets the value of iTestStepsCount
	 * @return Count of Test Steps
	 * @author rwilliams
	 */
	public int getTestStepsCount(){
		int iCount = iTestStepsCount;
		
		return iCount;
	}
	
	/**
	 * This method sets iTestStepsCount to zero
	 * @author rwilliams
	 */
	public void resetTestStepsCount(){
		iTestStepsCount = 0;
	}
	
	public void storeTestResults(){
		
	}
	
	/**
	 * This method checks results of the comparison of two values and reports on the results
	 * and then adds these results to the global test results
	 * The results and test steps count are returned in a Map
	 * 
	 * @param iStepRef - Test Step Reference
	 * @param sTestStepId - Test Step Name
	 * @param sTestScriptId - Test Script Name
	 * @param strMessage - Results message
	 * @param iValue1 - Expected results
	 * @param iValue2 - Actual results
	 * @return Map containing Global Test Step Count and Results
	 * @author rwilliams
	 */
	public Map<Integer,String> verifyValueEquals(int iStepRef, String sTestStepId,String strTestScriptName,String strMessage, int iValue1, int iValue2){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		
		if(iValue1==iValue2){
			//this.strMessage = "Step "+iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value = "+iValue1+" as Expected";
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value = "+iValue1+" as Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
			}
		else{
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\Step-"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			//this.strMessage = "Step "+iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Expected Value = "+iValue1+" but Actual Value = "+iValue2+"@"+strScreenshot; 
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Expected Value = "+iValue1+" but Actual Value = "+iValue2;
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
			}
				
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),"Step "+iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		AddTestsToLocalReport(map);
			
		//assertEquals(this.strMessage,iValue1,iValue2);
		
		return gMap;
		
	}

	public Map<Integer,String> verifyValueEquals(int iStepRef, String sTestStepId,String strTestScriptName,String strMessage, double dValue1, double dValue2){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		
		if(dValue1==dValue2){
			//this.strMessage = "Step "+iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value = "+iValue1+" as Expected";
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value = "+dValue1+" as Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
			}
		else{
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\Step-"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			//this.strMessage = "Step "+iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Expected Value = "+iValue1+" but Actual Value = "+iValue2+"@"+strScreenshot; 
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Expected Value = "+dValue1+" but Actual Value = "+dValue2;
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
			}
				
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),"Step "+iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		AddTestsToLocalReport(map);
			
		//assertEquals(this.strMessage,iValue1,iValue2);
		
		return gMap;
		
	}
	
	
	public Map<Integer,String> verifyValueNotEqualTo(int iStepRef, String sTestStepId,String strTestScriptName,String strMessage, int iValue1, int iValue2){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		
		if(iValue1!=iValue2){
			//this.strMessage = "Step "+iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value = "+iValue1+" as Expected";
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value 1 - "+iValue1+" is not equal to value 2 - "+iValue2+" as Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
			}
		else{
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\Step-"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			//this.strMessage = "Step "+iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Expected Value = "+iValue1+" but Actual Value = "+iValue2+"@"+strScreenshot; 
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Actual value "+iValue1+" is equal to second value "+iValue2;
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
			}
				
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),"Step "+iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		AddTestsToLocalReport(map);
			
		//assertEquals(this.strMessage,iValue1,iValue2);
		
		return gMap;
		
	}
	
	
	/**
	 * This method checks results of the comparison of two values and reports on the results
	 * and then adds these results to the global test results
	 * The results and test steps count are returned in a Map
	 * 
	 * @param sTestStepId - Test Step Identifier
	 * @param strMessage - Results message
	 * @param iValue1 - Expected results
	 * @param iValue2 - Actual results
	 * @return Map containing Global Test Step Count and Results
	 * @author rwilliams
	 */
	public Map<Integer,String> verifyBooleanEquals(int iStepRef, String sTestStepId, String strTestScriptName, String strMessage, boolean bExpected, boolean bActual){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		
		if(bExpected==bActual){
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value = "+bExpected+" as Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
			}
		else{
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			 
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Expected Value = "+bExpected+" but Actual Value = "+bActual;
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
			}
				
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		AddTestsToLocalReport(map);
			
		//assertEquals(this.strMessage,bExpected,bActual);
		
		return gMap;
		
	}
	
	
	/**
	 * This method checks if specified text exists in specified Array
	 * and then adds these results to the global test results
	 * The results and test steps count are returned in a Map
	 * 
	 * @param sTestStepId - Test Step Identifier
	 * @param strMessage - Results message
	 * @param strArray - Specified Array
	 * @param strText - Specified Text
	 * @return Map containing Global Test Step Count and Results
	 * @author rwilliams
	 */	
	public Map<Integer,String> verifyArrayContainsText(int iStepRef, String sTestStepId, String strTestScriptName, String strMessage,String [] strArray, String strText){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		String strText2="",strText3="";
		
		increaseTestStepsCount();
		
		List<String> list = Arrays.asList(strArray);
		
		for (int x=0;x<list.size();x++){
			if(list.get(x).contains(strText)){
				strText2 = list.get(x);
				}
			strText3 = strText3 +" "+ x +"-"+list.get(x);
			}
		
		
		if(!strText2.isEmpty()){
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Value = "+strText+" as Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
		}
		else{
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\Step-"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Expected Value = "+strText+" Not Found In "+strText3;
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
		}
		
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map, strTestScriptName);
		AddTestsToLocalReport(map);
		return gMap;
	}
	
	/**
	 * This method checks if specified text matches the expected text
	 * and then adds these results to the global test results
	 * The results and test steps count are returned in a Map
	 * 
	 * @param sTestStepId - Test Step Identifier
	 * @param strMessage - Results message
	 * @param strExpectedText - Expected Text
	 * @param strText - Actual Text
	 * @return Map containing Global Test Step Count and Results
	 * @author rwilliams
	 */	
	public Map<Integer,String> verifyExpectedText(int iStepRef, String sTestStepId,String strTestScriptName,String strMessage,String strExpectedText, String strText){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		
		//if(strExpectedText.toLowerCase().contains(strText.toLowerCase())){
		if(strExpectedText.toLowerCase().contentEquals(strText.toLowerCase())){
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Actual Text "+strText+" is as Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
		}
		else{
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\Step-"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Actual Text: "+strText+" Does Not Match Expected: "+strExpectedText+" Not Found";
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
		}
		
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		AddTestsToLocalReport(map);
		return gMap;
	}
		
	
	public Map<Integer,String> verifyIsNotExpectedText(int iStepRef, String sTestStepId,String strTestScriptName,String strMessage,String strExpectedText, String strText){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		//if(strExpectedText.toLowerCase().contains(strText.toLowerCase())){
		if(strExpectedText.toLowerCase().contentEquals(strText.toLowerCase())){
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\Step-"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Actual Text: "+strText+" Should Not Match: "+strExpectedText+" But It Does";
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
		}
		else{
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Actual Text "+strText+" does not match "+strExpectedText+" as Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
		}
		
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),"Test Step "+iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		AddTestsToLocalReport(map);
		return gMap;
	}
	/**
	 * This method checks if boolean is as expected
	 * and then adds these results to the global test results
	 * The results and test steps count are returned in a Map
	 * 
	 * @param sTestStepId - Test Step Identifier
	 * @param strMessage - Results message
	 * @param expectedBoolean - Expected Status (True/False)
	 * @param actualBoolean - Actual Status (True/False)
	 * @return Map containing Global Test Step Count and Results
	 * @author rwilliams
	 */	
	public Map<Integer,String> verifyObjectExists(int iStepRef, String sTestStepId,String strTestScriptName,String strMessage,boolean expectedBoolean, boolean actualBoolean){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		
		if(expectedBoolean == actualBoolean){
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Actual Status Is "+actualBoolean+" As Expected";
			updateExecutionArray(strTestScriptName,this.strMessage);
		}
		else{
			//String strScreenshot = "<a href=\"file:///"+System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "screenshots_location")+"\\Step-"+iStepRef+"-"+sTestStepId+"-Failed.png\">Click Here</a>";
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"Actual Status: "+actualBoolean+" Does Not Match Expected Status: "+expectedBoolean;
			updateExecutionArray(strTestScriptName,this.strMessage);
			//takeScreenshot(iStepRef+"-"+sTestStepId+"-Failed");
		}
		
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		AddTestsToLocalReport(map);
		
		return gMap;
	}
	
	public Map<Integer,String> verifyScreenshotIsTaken(int iStepRef, String sTestStepId,String strTestScriptName,String strMessage,String strScreenShotReturned){
		Map<Integer,String> map = new TreeMap<Integer,String>();Map<Integer,String> gMap = new TreeMap<Integer,String>();
		
		increaseTestStepsCount();
		
		if(strScreenShotReturned!=null){
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Passed@Screenshot taken@"+"<a href=\"file:///"+strScreenShotReturned+"\">Click Here</a>";
			updateExecutionArray(strTestScriptName,this.strMessage);
		}
		else{
			this.strMessage = iStepRef+"@"+sTestStepId+"@"+strMessage+"@Failed@"+"No Sreenshot@null";
			updateExecutionArray(strTestScriptName,this.strMessage);
		}
		
		map.put(iStepRef,this.strMessage);
		
		log.logInfo(obj.toString(),iStepRef+" : "+this.strMessage);
		
		gMap = AddTestsToReport(map,strTestScriptName);
		
		AddTestsToLocalReport(map);
		
		return gMap;
	}
	
	/**
	 * This method increases the number of test steps executed by 1
	 */
	private void increaseNumberOfTestStepsExecuted(){
		iTestStepsExecuted++;
	}
	
	/**
	 * This method returns the number of test steps executed
	 * @return
	 */
	private int getNumberOfTestStepsExecuted(){
		return iTestStepsExecuted;
	}
	
	/**
	 * This method populates an arraylist with the results of tests step executed
	 * @param strResults
	 */
	private void updateExecutionArray(String strTestSriptName, String strResults){
		increaseNumberOfTestStepsExecuted();
		
		int iNumber = getNumberOfTestStepsExecuted();
		//if(iNumber<1){
			//mapExecutionResults.add(iNumber+":"+strResults);
		int iTestResults=mTestResults.size();
		mTestResults.put(iTestResults+1, strTestSriptName+":"+strResults);
		
		mapExecutionResults.put(iTestResults+1, strTestSriptName+":"+strResults);
			/*}
		else{
			mapExecutionResults[iNumber]=strResults;
			}*/
		
	}
	
	public void collateResults(String sTestScriptName, Map<Integer,String> mapResults){
		ArrayList <String> aList = new ArrayList();
		int iSize =	arrayListResults.size();
		int iSize2 = mapResults.size();
		
		for(int a=0;a<=iSize;a++){
	
			//continue from here
		}
	
	}
	
	/**
	 * This method returns the arraylist that contains the test steps executed
	 * @return Array - Test steps executed
	 */
	public static Map<Integer,String> getExecutionResults(){
		return mapExecutionResults;
	}
	
	public static void resetExecutionResults(){
		mapExecutionResults.clear();
	}
	
	
	private static Map<Integer,String> getTotalResultsMap(){
		return mapTotalTestResults;
	}
	
	private static Map<Integer,String> getGlobalResultsMap(){
		return mapGlobalTestResults;
	}
	
	public static Map<Integer,String> getTestResultsMap(){
		return mTestResults;
	}
	
	public static Map<Integer,String> getLocalResultsMap(){
		return mapLocalTestResults;
		//return mapGlobalTestResults;
	}
	
	public void resetLocalResultsMap(){
		mapLocalTestResults.clear();
		System.out.println("local Test Results cleared");
	}
	
	public void resetTestResultsMap(){
		mTestResults.clear();
		System.out.println("Test Results Map cleared");
	}
	
	public static void runTestReportCreator(){
		//writeResultsToSummaryReport(getTotalResultsMap());
		writeResultsToSummaryReport(getGlobalResultsMap());
		
		//page.closeTheActiveBrowser();
	}
	
	public static void runLocalTestReportCreator(){
			
		writeResultsToSummaryReport(getLocalResultsMap());
				
	}
	
	/**
	 * This method takes in test results and adds them to a global test results map
	 * 
	 * @param mapTestResults - Test Results Input
	 * @return Global Test Results
	 */
	private Map <Integer,String> AddTestsToReport(Map <Integer,String> mapTestResults,String strTestScriptName){
		int iSize = mapTotalTestResults.size();
		Iterator<Integer> it = mapTestResults.keySet().iterator();
		while(it.hasNext()){
			iSize++;
			Integer key = it.next();
			if(mapTestResults.get(key).contains(strTestScriptName)){
				mapTotalTestResults.put(iSize, mapTestResults.get(key));
				arrayList.add(mapTestResults.get(key));
				}
			}
		return mapTotalTestResults;
		}
	
	public void AddTestsToGlobalReport(){
		int iSize = mapGlobalTestResults.size();
		int iSize2 = mapLoopedTestResults.size();
		/*for(int x=1;x<=iSize2;x++){
			iSize++;
			mapGlobalTestResults.put(iSize, mapLoopedTestResults.get(x));
		}*/

	}
	
	public void AddTestsToLocalReport(Map <Integer,String> map){
		int iSize = mapLocalTestResults.size();
		int iSize2 = map.size();
		Iterator<Integer> it = map.keySet().iterator();
		while(it.hasNext()){
			Integer key = it.next();
			String strTemp = map.get(key);
			if(strTemp!=null){
				iSize++;
				mapTotalTestResults.put(iSize, map.get(key));
			}
		}
	}
	
	public Map<Integer,String> addLoopedTestsToResults(){
		Map<Integer,String> map = new HashMap<Integer, String>();
		int iSize = mapLoopedTestResults.size();
		int iSize2 = mapTotalTestResults.size();
		for(int x=1;x<=iSize2;x++){
			iSize++;
			log.logInfo(obj.toString(), "Inserting "+iSize+" - "+mapTotalTestResults.get(x));
			mapLoopedTestResults.put(iSize, mapTotalTestResults.get(x));
		}
		return mapLoopedTestResults;
	}
	
	public static void writeResultsToSummaryReport(Map <Integer,String> mapResults){
		int i=0;
		ArrayList<String> aList = new ArrayList<String>();
		String strExecutionTimeRaw = Logging.getCurrentDateAndTime();
		String strExecutionTime = Logging.getCurrentDateAndTime().replace("/", "_").replace(":", "_").replace(" ","_");
		
		String strEmailFile = "Test_Results_"+strExecutionTime+".html";
		String strEmailFileLocation = properties.ReadProperty(obj.toString(), "test_report_location")+"\\";
		
		String strHtmlFile = System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\Test_Results_"+strExecutionTime+".html";
		
		//String sUrl = BasePage.getAutLink();
		
		Iterator<Integer> it = mapResults.keySet().iterator();
		while(it.hasNext()){
			Integer key = it.next();
			//mapTotalTestResults.put(key, mapResults.get(key));
			aList.add(i,mapResults.get(key));
			
			//System.out.println(aList.get(i));
			i++;
		}
		
		FileHandler.createTestReport(aList,strHtmlFile,strExecutionTimeRaw,"NW_API_Test");
		/*if(properties.ReadProperty(obj.toString(), "sendmail")=="true"){
		try {
			ActivateEmail.execute(strEmailFileLocation, strEmailFile);
			} 
		catch (Exception e) 
			{
			e.getMessage();
			}
		}*/
	}
	
	public static void writeResultsToLocalReport(String strTestName,Map <Integer,String> mapResults){
		int i=0;
		ArrayList<String> aList = new ArrayList<String>();
		String strExecutionTimeRaw = Logging.getCurrentDateAndTime();
		String strExecutionTime = Logging.getCurrentDateAndTime().replace("/", "_").replace(":", "_").replace(" ","_");
		
		String strEmailFile = strTestName+"_Test_Results_"+strExecutionTime+".html";
		String strEmailFileLocation = properties.ReadProperty(obj.toString(), "test_report_location")+"\\";
		
		String strHtmlFile = System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\local\\"+strEmailFile.replaceAll(" ", "");
		
		//String sUrl = BasePage.getAutLink();
		
		
		/*Iterator<Integer> it = mapResults.keySet().iterator();
		while(it.hasNext()){
			Integer key = it.next();
			//mapTotalTestResults.put(key, mapResults.get(key));
			aList.add(i,mapResults.get(key));
			
			//System.out.println(aList.get(i));
			i++;
		}*/
		
		FileHandler.createLocalTestReport(arrayList,strHtmlFile,strExecutionTimeRaw,"NW_API_TEST");
		/*if(properties.ReadProperty(obj.toString(), "sendmail")=="true"){
		try {
			ActivateEmail.execute(strEmailFileLocation, strEmailFile);
			} 
		catch (Exception e) 
			{
			e.getMessage();
			}
		}*/
	}
	
	//reportCreator(Map<Integer,String> mResults,String strHtmlFilename,String strTime, String strUrl)
	
	public static void generateHtmlReport(){
		if(getGlobalReportingFlag()==true){
			generateGlobalHtmlReport();
		}
		else{
			
			Map <Integer,String> mapResultsInput=getExecutionResults();
			
			String strExecutionTimeRaw = Logging.getCurrentDateAndTime();
			
			String strExecutionTime = Logging.getCurrentDateAndTime().replace("/", "_").replace(":", "_").replace(" ","_");
			
			String strTestName=getTestNameFromExecutionResults(mapResultsInput);
			
			String strEmailFile = strTestName+"_Test_Results_"+strExecutionTime+".html";
			
			String strHtmlFile = System.getProperty("user.dir")+"\\reports\\"+strEmailFile.replaceAll(" ", "");
			
			String sUrl = "NEW_WORLD_API_TEST";
			
			FileHandler.reportCreator(mapResultsInput,strHtmlFile,strExecutionTimeRaw,sUrl);
		}
	}
	
	
	public static void generateTestReport(){
		if(getGlobalReportingFlag()==true){
			generateGlobalHtmlReport();
		}
		else{
			
			Map <Integer,String> mapResultsInput=getTestResultsMap();
			
			String strExecutionTimeRaw = Logging.getCurrentDateAndTime();
			
			String strExecutionTime = Logging.getCurrentDateAndTime().replace("/", "_").replace(":", "_").replace(" ","_");
			
			String strTestName=getTestNameFromExecutionResults(mapResultsInput);
			
			String strEmailFile = strTestName+"_Test_Results_"+strExecutionTime+".html";
						
			String strHtmlFile = System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\"+strEmailFile.replaceAll(" ", "");
			
			String sUrl = "NEW_WORLD_API_TEST";
			
			FileHandler.reportCreator(mapResultsInput,strHtmlFile,strExecutionTimeRaw,sUrl);
		}
	}
	
	
	public static void generateGlobalHtmlReport(){
		
		Map <Integer,String> mapResultsInput=getExecutionResults();
				
		String strExecutionTimeRaw = Logging.getCurrentDateAndTime();
		
		String strExecutionTime = Logging.getCurrentDateAndTime().replace("/", "_").replace(":", "_").replace(" ","_");
		
		String strTestName=getTestNameFromExecutionResults(mapResultsInput);
		
		String strEmailFile = strTestName+"_Test_Results_"+strExecutionTime+".html";
				
		String strHtmlFile = System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\"+strEmailFile.replaceAll(" ", "");
		
		String sUrl = "NEW_WORLD_API_TEST";
		
		FileHandler.reportCreator(mapResultsInput,strHtmlFile,strExecutionTimeRaw,sUrl);
	}

	
public static void generateGlobalHtmlReport(String sTestName,Map <Integer,String>mapResults){
		
		//Map <Integer,String> mapResultsInput=getExecutionResults();
				
		String strExecutionTimeRaw = Logging.getCurrentDateAndTime();
		
		String strExecutionTime = Logging.getCurrentDateAndTime().replace("/", "_").replace(":", "_").replace(" ","_");
		
		//String strTestName=getTestNameFromExecutionResults(mapResults);
		
		String strEmailFile = sTestName+"_Test_Results_"+strExecutionTime+".html";
				
		String strHtmlFile = System.getProperty("user.dir")+properties.ReadProperty(obj.toString(), "test_report_location")+"\\"+strEmailFile.replaceAll(" ", "");
		
		String sUrl = "NEW_WORLD_API_TEST";
		
		FileHandler.reportCreator(mapResults,strHtmlFile,strExecutionTimeRaw,sUrl);
	}


	public static String getTestNameFromExecutionResults(Map<Integer, String> mapResultsInput) {
		int iSize = mapResultsInput.size();
		
		String [] arrayTemp = mapResultsInput.get(1).split(":");
		String [] arrayTestName = arrayTemp[0].split("@");
		String strCurrentTestName = arrayTestName[0];
		
		return strCurrentTestName;
	}
	

	public String takeScreenshot(String strFilename){
		String strScreenShot="NOT_IN_USE";
		/*
		drv = SeleniumDriver.getDriver();
		String strLocation = properties.ReadProperty(obj.toString(), "test_report_location")+"\\screenshots";
		File scrFile = ((TakesScreenshot)drv).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+strLocation+"\\"+strFilename+".png"));
			FileUtils.deleteQuietly(scrFile);
			log.logInfo(obj.toString(), "Capturing screenshot "+System.getProperty("user.dir")+strLocation+"\\"+strFilename+".png");
			strScreenShot = System.getProperty("user.dir")+strLocation+"\\"+strFilename+".png";
		} catch (Exception e) {
			strScreenShot = null;
			e.getMessage();
			
		}
		*/
		return strScreenShot;
	}

	
	/**
	 * This method takes in an array and a text string, then checks if the array contains the text
	 * @param array - Input Array
	 * @param strPattern - Input text
	 * @return True or False depending on findings.
	 * @author rwilliams
	 */
	public boolean doesArrayContainText(String [] array, String strPattern){
		boolean bResults = false;
		for(int x=0;x<array.length;x++){
			if(array[x].toLowerCase().contains(strPattern.toLowerCase())){
				bResults = true;
				log.logInfo(obj.toString(), "\\\""+strPattern + "\\\" was found in array element "+x);
				x = x + array.length;
			}
			
			if(x == array.length-1 && bResults!=true){
				bResults = false;
				log.logInfo(obj.toString(), strPattern+" was not found in the array");
			}
		}
		return bResults;
	}
	
	
	
	
}
