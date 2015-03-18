package com.diig.sqa.tests;



import org.apache.log4j.Logger;

import com.diig.attis.price.model.PriceSet;
import com.diig.attis.price.service.PriceService;
import com.diig.common.application.ApplicationConfiguration;
import com.diig.common.application.ApplicationConfigurationFactory;
import com.diig.common.service.ServiceException;
import com.diig.common.service.ServiceState;
import com.diig.sqa.services.ServiceHandler;
import com.diig.sqa.utilities.DatabaseHandler;
import com.diig.sqa.utilities.Logging;
import com.diig.sqa.utilities.Reporting;
import com.diig.sqa.utilities.TextHandler;


/**
 * This test adds a new PriceSet and checks that the available methods return the expected defaults and that the database is also correctly updated
 * @author rwilliams
 *
 */
public class AddNewPriceSet {

	static ServiceHandler psh = new ServiceHandler();
	static Reporting results = new Reporting();
	static DatabaseHandler db = new DatabaseHandler();
	static TextHandler th = new TextHandler();
	static Logging log = new Logging();
	private static final Logger LOG = Logger.getLogger(AddNewPriceSet.class);
	private static final ApplicationConfiguration CONFIG = ApplicationConfigurationFactory.getConfiguration();
	private static final PriceService SUBJECT = (PriceService) CONFIG.getObjectById("priceService");
	static Object obj = AddNewPriceSet.class.getName();
	static String strTestName = "AddNewPriceSet";
	static int iTestNum= 1;
	
	static String dataUnderTest="";
	
	public static void main(String[] args) {
		log.logInfo(obj.toString(), strTestName+" Started at:- "+Logging.getCurrentDateAndTime());
		
		try {
			SUBJECT.start();
		} catch (ServiceException e1) {
			LOG.error("Error Starting Service...\n"+e1.getMessage());
		}
		
		try {
			PriceSet priceSet = SUBJECT.createPriceSet("AA");
			ServiceState serviceState = SUBJECT.getState();
			
			results.verifyExpectedText( iTestNum,"checkPriceSetName", strTestName, "Is the PriceSetName as expected?", "PriceService", SUBJECT.getName());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit", strTestName, "Is the Business Unit as expected?", "AA",priceSet.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId", strTestName, "Is the Service State Id as expected?", "Running", serviceState.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId", strTestName, "Is the Version Id as expected?", "1", priceSet.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='AA', versionId='1', priceSetStatus='0', validFrom='null', validTo='null'}", SUBJECT.getPriceSet("AA", 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus", strTestName, "Is the Price Set Status as expected", 0, priceSet.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState", strTestName, "Is the Record State as expected", 1, priceSet.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId", strTestName, "Is the Row Id as expected", "4", priceSet.getRowId()+"");
			iTestNum++;
			
			priceSet.setRecordState(1);
			
			try{
			SUBJECT.delete(priceSet);
			}
			catch(Exception exp){
				System.out.println(exp.getMessage());
			}

			results.verifyValueEquals(iTestNum, "checkRecordStateConfirmsDeletion", strTestName, "Is the Record State for the deleted priceSet set to 3 as expected", 3, priceSet.getRecordState());
			iTestNum++;
		}
		catch(Exception e){
			LOG.error(e.getMessage());
			int iLastTestStep = iTestNum - 1;
			results.verifyBooleanEquals( iTestNum,"checkPriceServiceExecutionSuccessful", strTestName, "Test Script could not complete. Business Unit = "+iLastTestStep+". Last Step executed = "+iLastTestStep, true, false);
		}
			
		log.logInfo(obj.toString(), strTestName+" Completed at:- "+Logging.getCurrentDateAndTime());
		
		Reporting.generateHtmlReport();
		Reporting.resetExecutionResults();

	}
	

	


}
