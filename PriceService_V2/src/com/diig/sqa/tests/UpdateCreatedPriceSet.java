package com.diig.sqa.tests;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class UpdateCreatedPriceSet {

	public UpdateCreatedPriceSet(){
		super();
	}
	
	static ServiceHandler psh = new ServiceHandler();
	static Reporting results = new Reporting();
	static DatabaseHandler db = new DatabaseHandler();
	static TextHandler th = new TextHandler();
	static Logging log = new Logging();
	
	static Object obj = UpdateCreatedPriceSet.class.getName();
	
	private static final Logger LOG = Logger.getLogger(UpdateCreatedPriceSet.class);
	private static final ApplicationConfiguration CONFIG = ApplicationConfigurationFactory.getConfiguration();
	private static final PriceService SUBJECT = (PriceService) CONFIG.getObjectById("priceService");
	
	static String strTestName = "UpdateCreatedPriceSet";
	static int iTestNum= 1;
	
	public static void main(String[] args) {
		log.logInfo(obj.toString(), strTestName+" Started at:- "+Logging.getCurrentDateAndTime());
		
		try {
			SUBJECT.start();
		} catch (ServiceException e1) {
			LOG.error("Error Starting Service...\n"+e1.getMessage());
		}
		
		try {
			PriceSet priceSet = SUBJECT.createPriceSet("AB");
			ServiceState serviceState = SUBJECT.getState();
			
			results.verifyExpectedText( iTestNum,"checkPriceSetName", strTestName, "Is the PriceSetName as expected?", "PriceService", SUBJECT.getName());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit", strTestName, "Is the Business Unit as expected?", "AB",priceSet.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId", strTestName, "Is the Service State Id as expected?", "Running", serviceState.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId", strTestName, "Is the Version Id as expected?", "1", priceSet.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='AB', versionId='1', priceSetStatus='0', validFrom='null', validTo='null'}", SUBJECT.getPriceSet("AB", 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus", strTestName, "Is the Price Set Status as expected", 0, priceSet.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState", strTestName, "Is the Record State as expected", 1, priceSet.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId", strTestName, "Is the Row Id as expected", "2", priceSet.getRowId()+"");
			iTestNum++;
			
			
			
			//Update Version Id and check results
			priceSet.set("versionId", 2);
			
			priceSet.setPriceSetStatus(PriceSet.ACTIVE);
			
			Boolean isUpdateMethodRanSuccessfullyTrue = null;
			try{
				isUpdateMethodRanSuccessfullyTrue = SUBJECT.update(priceSet);
			}
			catch(Exception exp){
				LOG.error(exp.getMessage());
			}
			
			results.verifyValueEquals(iTestNum, "checkUpdatedVersionId", strTestName, "Does \"getRowId\" return expected value", 2, priceSet.getVersionId());
			iTestNum++;
					
			results.verifyValueEquals(iTestNum, "checkGetPriceSetStatusHoldsUpdate", strTestName, "Does \"getPriceSetStatus\" return expected value", 1, priceSet.getPriceSetStatus());
			iTestNum++;
			
			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
			
			Calendar calendar = Calendar.getInstance();
			
			//calendar.set(2015, 04, 01);
			Date dBaseline = calendar.getTime();
			
			calendar.add(Calendar.DAY_OF_MONTH, 30);
			
			Date date1 = calendar.getTime();
			
			calendar.add(Calendar.DAY_OF_MONTH, 30);
			
			Date date2 = calendar.getTime();

			priceSet.setValidFrom(date1);
			
			priceSet.setValidTo(date2);
			
			//priceSet.setPriceSetStatus(1);
			
			priceSet.setPriceSetStatus(PriceSet.ACTIVE);
			
			try{
				boolean res = SUBJECT.update(priceSet);
				results.verifyBooleanEquals( iTestNum,"checkUpdatePriceSetSuccessful", strTestName, "Did the Update PriceSet Execute as expected?", true, true);
				iTestNum++;
				}
			catch(Exception ex){
				LOG.error("Error running update  method:- \n"+ex);
				results.verifyBooleanEquals( iTestNum,"checkUpdatePriceSetSuccessful", strTestName, "Did the Update PriceSet Execute as expected?", true, false);
				iTestNum++;
				}			
			
			
			Date dValidFrom2 = priceSet.getValidFrom();
			Date dValidTo2 = priceSet.getValidTo();
			
			results.verifyBooleanEquals( iTestNum,"checkUpdateValidFromDateIsNotNull", strTestName, "Is the \"ValidFrom\" date not null as expected?", false, TextHandler.isDateNull(priceSet.getValidFrom()));
			iTestNum++;
			
			results.verifyBooleanEquals( iTestNum,"checkUpdateValidToDateIsNotNull", strTestName, "Is the \"ValidTo\" date not null as expected?", false, TextHandler.isDateNull(priceSet.getValidTo()));
			iTestNum++;
					
			//Update priceSetStatus
			results.verifyValueEquals(iTestNum, "checkGetPriceSetStatusHoldsUpdate", strTestName, "Does \"getPriceSetStatus\" return expected value", 1, priceSet.getPriceSetStatus());
			iTestNum++;

			priceSet.setRecordState(0);
			
			results.verifyValueEquals(iTestNum, "checkGetRecordStateHoldsUpdate", strTestName, "Does \"getRecordState\" return expected value", 0, priceSet.getRecordState());
			iTestNum++;

			//Set ValidFrom date to be after ValidTo date
			priceSet.setValidFrom(date2);
			
			priceSet.setValidTo(date1);
			
			Date dValidFrom3 = priceSet.getValidFrom();
			Date dValidTo3 = priceSet.getValidTo();
			
			
			results.verifyBooleanEquals( iTestNum,"checkValidFromDateIsNotAfterValidFromDate", strTestName, "Is the \"ValidFrom\" date not after \"ValidTo\" date?", false, dValidFrom3.after(dValidTo3));
			iTestNum++;
			
			results.verifyBooleanEquals( iTestNum,"checkValidToDateIsNotBeforeValidFrom", strTestName, "Is the \"ValidTo\" date not before \"ValidFrom\" date?", false, dValidTo3.before(dValidFrom3));
			iTestNum++;
			
			int iNegativeValue = -4;
			priceSet.setPriceSetStatus(iNegativeValue);
			
			results.verifyValueNotEqualTo(iTestNum, "checkPriceSetStatusCanNotBeSetToNegativeValue", strTestName, "Is \"PriceSetStatus\" not set to a negative number", iNegativeValue, priceSet.getPriceSetStatus());
			iTestNum++;
			
			priceSet.setRecordState(iNegativeValue);
			
			results.verifyValueNotEqualTo(iTestNum, "checkRecordStateCanNotBeSetToNegativeValue", strTestName, "Is \"RecordState\" not set to a negative number", iNegativeValue, priceSet.getRecordState());
			iTestNum++;
			
			//Update Version Id and check results
			priceSet.set("versionId", iNegativeValue);
			
			results.verifyValueNotEqualTo(iTestNum, "checkVersionIdCanNotBeSetToNegativeValue", strTestName, "Is \"VersionId\" not set to a negative number", iNegativeValue, priceSet.getVersionId());
			iTestNum++;
		}
		catch(Exception e){
			LOG.error(e.getMessage());
			results.verifyBooleanEquals( iTestNum,"checkPriceServiceExecutionSuccessful", strTestName, "Did the PriceService Execute as expected?", true, false);
		}
			
			//priceSet.setRecordState(arg0)
			
		log.logInfo(obj.toString(), strTestName+" Completed at:- "+Logging.getCurrentDateAndTime());
		
		Reporting.generateHtmlReport();
		Reporting.resetExecutionResults();

	}
	

	


}
