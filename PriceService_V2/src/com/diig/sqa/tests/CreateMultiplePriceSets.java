package com.diig.sqa.tests;


import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.diig.sqa.utilities.Logging;
import com.diig.attis.price.model.PriceSet;
import com.diig.attis.price.service.PriceService;
import com.diig.common.application.ApplicationConfiguration;
import com.diig.common.application.ApplicationConfigurationFactory;
import com.diig.common.service.ServiceException;
import com.diig.common.service.ServiceState;
import com.diig.sqa.services.ServiceHandler;
import com.diig.sqa.utilities.DatabaseHandler;
import com.diig.sqa.utilities.Reporting;
import com.diig.sqa.utilities.TextHandler;

public class CreateMultiplePriceSets {

	public CreateMultiplePriceSets(){
		super();
	}
	
	static ServiceHandler psh = new ServiceHandler();
	static Reporting results = new Reporting();
	static DatabaseHandler db = new DatabaseHandler();
	static TextHandler th = new TextHandler();
	static Logging log = new Logging();
	private static final Logger LOG = Logger.getLogger(CreateMultiplePriceSets.class);
	private static final ApplicationConfiguration CONFIG = ApplicationConfigurationFactory.getConfiguration();
	private static final PriceService SUBJECT = (PriceService) CONFIG.getObjectById("priceService");
	
	static Object obj = CreateMultiplePriceSets.class.getName();
	
	
	static String strTestName = "CreateMultiplePriceSets";
	static int iTestNum= 1;
	
	public static void main(String[] args) {
		log.logInfo(obj.toString(), strTestName+" Started at:- "+Logging.getCurrentDateAndTime());
		
		try {
			SUBJECT.start();
		} catch (ServiceException e1) {
			LOG.error("Error Starting Service...\n"+e1.getMessage());
		}
		
		try {
			
			Map<Integer, Date> twoDates = TextHandler.getTwoDates(30);
			
			PriceSet priceSet1 = SUBJECT.createPriceSet("AB");
			PriceSet priceSet2 = SUBJECT.createPriceSet("AB2");
			PriceSet priceSet3 = SUBJECT.createPriceSet("AB3");
			PriceSet priceSet4 = SUBJECT.createPriceSet("AB4");
			

			ServiceState serviceState1 = SUBJECT.getState();
			ServiceState serviceState2 = SUBJECT.getState();
			ServiceState serviceState3 = SUBJECT.getState();
			ServiceState serviceState4 = SUBJECT.getState();
			//String strBusUnit = priceSet.getBusinessUnitId();
			
			priceSet1.setPriceSetStatus(1);
			priceSet2.setPriceSetStatus(1);
			//Set ValidFrom & ValidTo dates for priceset2
			priceSet2.setValidFrom(twoDates.get(0));
			priceSet2.setValidTo(twoDates.get(1));
			
			priceSet3.setPriceSetStatus(1);
			priceSet4.setPriceSetStatus(1);
			/*
			try{
				SUBJECT.update(priceSet1);
				SUBJECT.update(priceSet2);
			}
			catch(Exception exp){
				LOG.info(exp.getMessage());
			}*/
			results.verifyExpectedText( iTestNum,"checkPriceSetName1", strTestName, "Is the PriceSetName as expected?", "PriceService", SUBJECT.getName());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit1", strTestName, "Is the Business Unit as expected?", "AB",priceSet1.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId1", strTestName, "Is the Service State Id as expected?", "Running", serviceState1.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId1", strTestName, "Is the Version Id as expected?", "1", priceSet1.getVersionId()+"");
			iTestNum++;	
			
			
			String strTemp = SUBJECT.getPriceSet("AB", 1).toString();
			results.verifyExpectedText( iTestNum,"checkPriceSet1", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='AB', versionId='1', priceSetStatus='0', validFrom='null', validTo='null'}", SUBJECT.getPriceSet("AB", 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus1", strTestName, "Is the Price Set Status as expected", 1, priceSet1.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState1", strTestName, "Is the Record State as expected", 1, priceSet1.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull1", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet1.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull1", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet1.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId1", strTestName, "Is the Row Id as expected", "4", priceSet1.getRowId()+"");
			iTestNum++;
			
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit2", strTestName, "Is the Business Unit as expected?", "AB2",priceSet2.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId2", strTestName, "Is the Service State Id as expected?", "Running", serviceState2.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId2", strTestName, "Is the Version Id as expected?", "1", priceSet2.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet2", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='AB2', versionId='1', priceSetStatus='0', validFrom='null', validTo='null'}", SUBJECT.getPriceSet("AB2", 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus2", strTestName, "Is the Price Set Status as expected", 1, priceSet2.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState2", strTestName, "Is the Record State as expected", 1, priceSet2.getRecordState());
			iTestNum++;
			
			
			boolean bRes2 = TextHandler.isDateNull(priceSet2.getValidFrom());
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull2", strTestName, "Is the \"Valid From\" null as expected", false, TextHandler.isDateNull(priceSet2.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull2", strTestName, "Is the \"Valid To\" as expected?", false, TextHandler.isDateNull(priceSet2.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId2", strTestName, "Is the Row Id as expected", "5", priceSet2.getRowId()+"");
			iTestNum++;			
			

			results.verifyExpectedText( iTestNum,"checkBusinessUnit3", strTestName, "Is the Business Unit as expected?", "AB3",priceSet3.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId3", strTestName, "Is the Service State Id as expected?", "Running", serviceState3.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId3", strTestName, "Is the Version Id as expected?", "1", priceSet3.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet3", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='AB3', versionId='1', priceSetStatus='0', validFrom='null', validTo='null'}", SUBJECT.getPriceSet("AB3", 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus3", strTestName, "Is the Price Set Status as expected", 1, priceSet3.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState3", strTestName, "Is the Record State as expected", 1, priceSet3.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull3", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet3.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull3", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet3.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId3", strTestName, "Is the Row Id as expected", "6", priceSet3.getRowId()+"");
			iTestNum++;	
			
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit4", strTestName, "Is the Business Unit as expected?", "AB4",priceSet4.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId4", strTestName, "Is the Service State Id as expected?", "Running", serviceState4.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId4", strTestName, "Is the Version Id as expected?", "1", priceSet4.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet4", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='AB4', versionId='1', priceSetStatus='0', validFrom='null', validTo='null'}", SUBJECT.getPriceSet("AB4", 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus4", strTestName, "Is the Price Set Status as expected", 1, priceSet4.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState4", strTestName, "Is the Record State as expected", 1, priceSet4.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull4", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet4.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull4", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet4.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId4", strTestName, "Is the Row Id as expected", "7", priceSet4.getRowId()+"");
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
