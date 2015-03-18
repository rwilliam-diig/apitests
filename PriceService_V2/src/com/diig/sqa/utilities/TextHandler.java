package com.diig.sqa.utilities;

import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.money.MonetaryAmount;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class TextHandler {
	
	static Logging log = new Logging();
	static Object obj = TextHandler.class.getName();
	private static final Logger LOG = Logger.getLogger(TextHandler.class);
	/**
	 * This method takes in an array, searches for a pattern in all the elements
	 * and returns the index of the first element that contains the pattern
	 * 
	 * @param array
	 * @param strPattern
	 * @return Index of the matching element
	 * @author rwilliams
	 */	
	public int returnIndexOfTextMatchInArray(String [] array, String strPattern){
		int mIndex=-1;
		for(int x=0;x<array.length;x++){
			if(array[x].contains(strPattern)){
				mIndex = x;
				log.logInfo(obj.toString(), strPattern+" was found in index "+mIndex);
				x = x + array.length;
			}
		}
		return mIndex;
	}

	/**
	 * This method takes in an array, searches for a pattern in all the elements
	 * and returns the contents of the first element that contains the pattern
	 * 
	 * @param array
	 * @param strPattern
	 * @return String - Contents of the matching element
	 * @author rwilliams
	 */
	public String returnStringOfTextMatchInArray(String [] array, String strPattern){
		int mIndex=-1;
		String strText="NOT FOUND";
		for(int x=0;x<array.length;x++){
			if(array[x].contains(strPattern)){
				strText = array[x];
				mIndex = x;
				log.logInfo(obj.toString(), strPattern+" was found in index "+mIndex);
				x = x + array.length;
				
			}
		}
		
		return strText;
	}
	
	/**
	 * This method confirms if the number of characters specified text has a length
	 * equals or greater than the number specified
	 * 
	 * @param sText
	 * @param iLen
	 * @return true or false
	 * @author rwilliams
	 */
	public boolean isLengthOfTextUpTo(String sText,int iLen){
		boolean res = false;
		if(sText.length()<=iLen){
			res = true;
		}
		
		return res;
	}
	
	/**
	 * The method encrypts the input using MD5 
	 * @param source - Unencrypted String
	 * @return - String - Encrypted String
	 */
	public String encryptPassword(String source) {
		String md5 = null;
		try {
		    MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
		    mdEnc.update(source.getBytes(), 0, source.length());
		    md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
		} catch (Exception ex) {
		    return null;
		}
		return md5;
		}
	
	/**
	 * This method checks if the specified text is contained in the specified ArrayList
	 * @param arrayList
	 * @param sText
	 * @return
	 */
	public boolean doesArrayListContainText(ArrayList<String> arrayList,String sText){
		boolean bMatch = false;
		for(int a=0;a<arrayList.size();a++){
			if(arrayList.get(a).toLowerCase().contains(sText.toLowerCase())){
				log.logInfo(obj.toString(), sText+" found in index "+a);
				bMatch = true;
				a = a + arrayList.size();
			}
		}
		
		return bMatch;
	}
	
	/**
	 * This method checks if arrayList is empty and returns true or false based on results
	 * @param resultsArrayList - ArrayList under check
	 * @return
	 */
	public boolean isArrayListEmpty(ArrayList<String> resultsArrayList){
		boolean res = false;
		if(resultsArrayList.size()==0){
			res = true;
		}
		
		return res;
	}
	
	/**
	 * This method checks if the specified text is contained in the specified ArrayList
	 * @param listErrors
	 * @param sText
	 * @return
	 * @author rwilliams
	 */
	public boolean doesListContainText(List<?> listErrors,String sText){
		boolean bMatch = false;
		for(int a=0;a<listErrors.size();a++){
			if(((String) listErrors.get(a)).toLowerCase().contains(sText.toLowerCase())){
				log.logInfo(obj.toString(), sText+" found in index "+a);
				bMatch = true;
				a = a + listErrors.size();
			}
		}
		
		return bMatch;
	}
	
	/**
	 * Takes in a list and converts it to an arrayList
	 * @param eList - List
	 * @return al - ArrayList
	 * @author rwilliams
	 */
	public ArrayList<String> convertToArrayList(List<?>eList){
		
		ArrayList<String> al = new ArrayList<String>();
		
		for (Object error : eList){
			al.add(error.toString());
			log.logInfo(obj.toString(), "\""+error.toString()+"\" error added to arrayList");
		}
		
		return al;
	}
	
	/**
	 * This method extracts the testname
	 * @param strName
	 * @return TestName
	 * @author rwilliams
	 */
	public String getTestStepName(String strName){
		String [] sArray = strName.split("\\.");

		return sArray[sArray.length-1];
	}
	
	/**
	 * This method write the errors returned to the log file and console
	 * @param errors
	 * @author rwilliams
	 */
	public void printContentOfList(List<?> errors){
		for (Object error : errors){
			log.logInfo(obj.toString(), "Error Message: "+error.toString());
		}
	}
	
	/**
	 * This method checks if specified Date parameter is null
	 * @param date
	 * @return true or false
	 */
	public static boolean isDateNull(Date date){
		boolean res = false;
		try{
			if(date.equals(null)){
				res = true;
				}
			else{
				res = false;
				}
			}
		catch(NullPointerException ex){
			res = true;
			}
		catch(Exception exp){
			System.out.println(exp.getMessage());
		}
		
		return res;
	}
	
	
	/**
	 * This method checks if specified object is null
	 * @param Object
	 * @return true or false
	 */
	public static boolean isObjectNull(Object obj){
		boolean res = false;
		try{
			if(obj.equals(null)){
				res = true;
				}
			else{
				res = false;
				}
			}
		catch(NullPointerException ex){
			res = true;
			}
		catch(Exception exp){
			System.out.println(exp.getMessage());
		}
		
		return res;
	}
	
	public static Document convertStringToDocument(String xmlStr) {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
	    try {
	      builder = factory.newDocumentBuilder();
	      Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
	      
	      doc.normalize();
	      return doc;
	    } catch (Exception ex) {
	      LOG.error("failed to convert to XML." ,ex);
	    }
	    return null;
	  }
	
	/**
	 * This method takes in the price MonetaryAmount object and strips it to return the amount as a double
	 * @param mAmount
	 * @return
	 * @author rwilliams
	 */
	public static double convertMonetaryAmountToDouble(MonetaryAmount mAmount){
		log.logInfo(obj.toString(), "Extracting amount from "+mAmount.toString());
		return Double.parseDouble(mAmount.toString().replace("MoneyImpl={amount='", "").replace("', ccy='GBP'}", ""));
	}
	
	/**
	 * Calculates the expected margin price
	 * @param dRetailAmount
	 * @return
	 */
	public static double checkMarginPrice(double dRetailAmount){
		return dRetailAmount * 0.5;
	}
	
	/**
	 * Calculates the expected discounted amount
	 * @param dRetailAmount
	 * @param discountRate
	 * @return
	 */
	public static double getDiscountedAmount(double dRetailAmount,double discountRate){
		return dRetailAmount -dRetailAmount * discountRate;
	}
	
	/**
	 * Calculates the expected tax amount
	 * @param dCostPrice
	 * @param dTaxRate
	 * @return
	 */
	public static double getTaxAmount(double dCostPrice, double dTaxRate){
		return dCostPrice * dTaxRate;
	}
	
	/**
	 * Calculates the amount after tax
	 * @param dCostPrice
	 * @param dTaxRate
	 * @return
	 */
	public static double getAmountAfterTax(double dCostPrice, double dTaxRate){
		return dCostPrice + getTaxAmount(dCostPrice,dTaxRate);
	}
	
	/**
	 * This method takes in the number of days apart and returns two dates 
	 * where date 1 is todays date
	 * 
	 * @param differenceOfDatesInDays
	 * @return Map of type Date
	 * @author rwilliams
	 */
	public static Map<Integer,Date> getTwoDates(int differenceOfDatesInDays){
		
		Map <Integer,Date> dates = new HashMap<Integer, Date>();
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		
		Calendar calendar = Calendar.getInstance();
		
		//calendar.set(2015, 04, 01);
		Date dBaseline = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, 0);
		
		Date date1 = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, differenceOfDatesInDays);
		
		Date date2 = calendar.getTime();
		
		dates.put(0, date1);
		dates.put(1, date2);
		
		return dates;
	}
	
}
