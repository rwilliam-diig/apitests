package com.diig.sqa.tests;


import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.diig.sqa.utilities.Logging;
import com.diig.attis.intergation.order.xml.XMLOrderRequestBuilder;
import com.diig.attis.price.model.Price;
import com.diig.attis.price.model.PriceItem;
import com.diig.attis.price.model.PriceRequest;
import com.diig.attis.price.model.PriceResponse;
import com.diig.attis.price.model.PriceSet;
import com.diig.attis.price.model.impl.PriceRequestImpl;
import com.diig.attis.price.service.PriceService;
import com.diig.common.application.ApplicationConfiguration;
import com.diig.common.application.ApplicationConfigurationFactory;
import com.diig.common.service.ServiceException;
import com.diig.common.service.ServiceState;
import com.diig.sqa.services.ServiceHandler;
import com.diig.sqa.utilities.DatabaseHandler;
import com.diig.sqa.utilities.Reporting;
import com.diig.sqa.utilities.TextHandler;

public class UpdateMultiplePriceSets {

	public UpdateMultiplePriceSets(){
		super();
	}
	
	static ServiceHandler psh = new ServiceHandler();
	static Reporting results = new Reporting();
	static DatabaseHandler db = new DatabaseHandler();
	static TextHandler th = new TextHandler();
	static Logging log = new Logging();
	private static final Logger LOG = Logger.getLogger(UpdateMultiplePriceSets.class);
	private static final ApplicationConfiguration CONFIG = ApplicationConfigurationFactory.getConfiguration();
	private static final PriceService SUBJECT = (PriceService) CONFIG.getObjectById("priceService");
	
	static Object obj = UpdateMultiplePriceSets.class.getName();
	
	
	static String strTestName = "UpdateMultiplePriceSets";
	static int iTestNum= 1;
	
	static String strBusUnit1 = "QA1";
	static String strBusUnit2 = "QA2";
	
	public static void main(String[] args) {
		log.logInfo(obj.toString(), strTestName+" Started at:- "+Logging.getCurrentDateAndTime());
		
		try {
			SUBJECT.start();
		} catch (ServiceException e1) {
			LOG.error("Error Starting Service...\n"+e1.getMessage());
		}
		
		try {
			
			Map<Integer, Date> twoDates = TextHandler.getTwoDates(30);
			
			PriceSet priceSet1 = SUBJECT.createPriceSet("QA1");
			PriceSet priceSet2 = SUBJECT.createPriceSet("QA1");
			PriceSet priceSet3 = SUBJECT.createPriceSet("QA1");
			PriceSet priceSet4 = SUBJECT.createPriceSet("QA1");
			

			ServiceState serviceState1 = SUBJECT.getState();
			ServiceState serviceState2 = SUBJECT.getState();
			ServiceState serviceState3 = SUBJECT.getState();
			ServiceState serviceState4 = SUBJECT.getState();
			//String strBusUnit = priceSet.getBusinessUnitId();
			/*
			priceSet1.setPriceSetStatus(1);
			priceSet2.setPriceSetStatus(1);
			*/
			//Set ValidFrom & ValidTo dates for priceset2
			//priceSet2.setValidFrom(twoDates.get(0));
			//priceSet2.setValidTo(twoDates.get(1));
			
			//priceSet3.setPriceSetStatus(1);
			

			//priceSet4.setPriceSetStatus(1);
			
			try{
				/*
				SUBJECT.update(priceSet1);
				SUBJECT.update(priceSet2);
				SUBJECT.update(priceSet3);
				*/
			}
			catch(Exception exp){
				LOG.info(exp.getMessage());
			}
			
			
			Document doc = TextHandler.convertStringToDocument("<order>"
		            + "<orderSections>"
		            + "<orderSection>"
		            + "<products>"
		            + "<product productTypeId=\"FORM12A\">"
		            + "</product>"
		            + "</products>"
		            + "</orderSection>"
		            + "</orderSections>"
		            + "</order>");
			Map<String, Object> orderData = XMLOrderRequestBuilder.parseOrderXML(doc.getFirstChild());
			
			Document doc2 = TextHandler.convertStringToDocument("<order>"
		            + "<orderSections>"
		            + "<orderSection>"
		            + "<products>"
		            + "<product productTypeId=\"FORM13A\">"
		            + "</product>"
		            + "</products>"
		            + "</orderSection>"
		            + "</orderSections>"
		            + "</order>");
			Map<String, Object> orderData2 = XMLOrderRequestBuilder.parseOrderXML(doc2.getFirstChild());
			
			Document doc3 = convertStringToDocument("<order>"
		            + "<orderSections>"
		            + "<orderSection>"
		            + "<products>"
		            + "<product productTypeId=\"FORM12A\">"
		            + "</product>"
		            + "<product productTypeId=\"FORM13A\">"
		            + "</product>"
		            + "</products>"
		            + "</orderSection>"
		            + "</orderSections>"
		            + "</order>");
			Map<String, Object> orderData3 = XMLOrderRequestBuilder.parseOrderXML(doc3.getFirstChild());
			
			//Discount = 0.25
			PriceRequest request = new PriceRequestImpl("QA1", "TESTCUSTOMER1","", orderData);
			
			//Discount = 0.5
			PriceRequest request2 = new PriceRequestImpl("QA1", "TESTCUSTOMER2","", orderData2);
			
			//Discount = 0.25
			PriceRequest request3 = new PriceRequestImpl("QA1", "TESTCUSTOMER3","", orderData3);
			
			//Discount = 0.5
			PriceRequest request4 = new PriceRequestImpl("QA1", "TESTCUSTOMER4","", orderData3);
			
			//SUBJECT.update(priceSet2);
			
			String strNewPriceBusUnit = request.getBusinessUnitId();
			String strCustomerId = request.getCustomerId();
			
			PriceResponse response = SUBJECT.process(request);
			
			PriceResponse response2 = SUBJECT.process(request2);
			
			PriceResponse response3 = SUBJECT.process(request3);
			
			PriceResponse response4 = SUBJECT.process(request4);
			
			Price price1 = response.getResponseData();
			
			List<PriceItem> items = response.getResponseData().getItems();
			
			PriceItem item = response.getResponseData().getItem(0);
			String priceId = response.getResponseData().getPriceId();
			int recordState = response.getResponseData().getRecordState();
			double rowId =  response.getResponseData().getRowId();
			Date dateModified = response.getResponseData().getModifiedOn();
			
			PriceItem item2 = response2.getResponseData().getItem(0);
			String priceId2 = response2.getResponseData().getPriceId();
			int recordState2 = response2.getResponseData().getRecordState();
			double rowId2 =  response2.getResponseData().getRowId();
			Date dateModified2 = response2.getResponseData().getModifiedOn();
			
			PriceItem item3 = response3.getResponseData().getItem(0);
			String priceId3 = response3.getResponseData().getPriceId();
			int recordState3 = response3.getResponseData().getRecordState();
			double rowId3 =  response3.getResponseData().getRowId();
			Date dateModified3 = response3.getResponseData().getModifiedOn();
			
			PriceItem item4 = response4.getResponseData().getItem(0);
			String priceId4 = response4.getResponseData().getPriceId();
			int recordState4 = response4.getResponseData().getRecordState();
			double rowId4 =  response4.getResponseData().getRowId();
			Date dateModified4 = response4.getResponseData().getModifiedOn();
			
			double costPrice = TextHandler.convertMonetaryAmountToDouble(item.getCostPrice());
			double discount = item.getDiscount();
			int iLineIndex = item.getLineIndex();
			String strProductId = item.getProductId();
			int iQuantity = item.getQuantity();
			double retailPrice = TextHandler.convertMonetaryAmountToDouble(item.getRetailPrice());
			double salePrice = TextHandler.convertMonetaryAmountToDouble(item.getSalePrice());
			double taxAmount = TextHandler.convertMonetaryAmountToDouble(item.getTaxAmount());
			double taxRate = item.getTaxRate();
			double unitPrice = TextHandler.convertMonetaryAmountToDouble(item.getUnitPrice());
			
			
			//item.set(arg0, arg1);
					
			results.verifyExpectedText( iTestNum,"checkPriceSetName1", strTestName, "Is the PriceSetName as expected?", "PriceService", SUBJECT.getName());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit1", strTestName, "Is the Business Unit as expected?", strBusUnit1,priceSet1.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId1", strTestName, "Is the Service State Id as expected?", "Running", serviceState1.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId1", strTestName, "Is the Version Id as expected?", "2", priceSet1.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet1", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='"+strBusUnit1+"', versionId='1', priceSetStatus='1', validFrom='null', validTo='null'}", SUBJECT.getPriceSet(strBusUnit1, 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus1", strTestName, "Is the Price Set Status as expected", 0, priceSet1.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState1", strTestName, "Is the Record State as expected", 1, priceSet1.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull1", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet1.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull1", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet1.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId1", strTestName, "Is the Row Id as expected", "3", priceSet1.getRowId()+"");
			iTestNum++;
			
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit2", strTestName, "Is the Business Unit as expected?", strBusUnit1,priceSet2.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId2", strTestName, "Is the Service State Id as expected?", "Running", serviceState2.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId2", strTestName, "Is the Version Id as expected?", "3", priceSet2.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet2", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='"+strBusUnit1+"', versionId='1', priceSetStatus='1', validFrom='null', validTo='null'}", SUBJECT.getPriceSet(strBusUnit1, 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus2", strTestName, "Is the Price Set Status as expected", 0, priceSet2.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState2", strTestName, "Is the Record State as expected", 1, priceSet2.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull2", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet2.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull2", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet2.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId2", strTestName, "Is the Row Id as expected", "4", priceSet2.getRowId()+"");
			iTestNum++;			
			

			results.verifyExpectedText( iTestNum,"checkBusinessUnit3", strTestName, "Is the Business Unit as expected?", strBusUnit1,priceSet3.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId3", strTestName, "Is the Service State Id as expected?", "Running", serviceState3.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId3", strTestName, "Is the Version Id as expected?", "4", priceSet3.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet3", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='"+strBusUnit1+"', versionId='1', priceSetStatus='1', validFrom='null', validTo='null'}", SUBJECT.getPriceSet(strBusUnit1, 1).toString());
			iTestNum++;	
			
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus3", strTestName, "Is the Price Set Status as expected", 0, priceSet3.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState3", strTestName, "Is the Record State as expected", 1, priceSet3.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull3", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet3.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull3", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet3.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId3", strTestName, "Is the Row Id as expected", "5", priceSet3.getRowId()+"");
			iTestNum++;	
			
			
			results.verifyExpectedText( iTestNum,"checkBusinessUnit4", strTestName, "Is the Business Unit as expected?", strBusUnit1,priceSet4.getBusinessUnitId());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkStateId4", strTestName, "Is the Service State Id as expected?", "Running", serviceState4.toString());
			iTestNum++;
			
			results.verifyExpectedText( iTestNum,"checkVersionId4", strTestName, "Is the Version Id as expected?", "5", priceSet4.getVersionId()+"");
			iTestNum++;	
			
			results.verifyExpectedText( iTestNum,"checkPriceSet4", strTestName, "Is the Price Set returned as expected?", "PriceSetImpl={businessUnitId='"+strBusUnit1+"', versionId='1', priceSetStatus='1', validFrom='null', validTo='null'}", SUBJECT.getPriceSet(strBusUnit1, 1).toString());
			iTestNum++;	
			
			//Confirm priceSet4 setPriceSetStatus is not changed to 1 from 0
			results.verifyValueEquals(iTestNum, "checkPriceSetStatus4", strTestName, "Is the Price Set Status as expected", 0, priceSet4.getPriceSetStatus());
			iTestNum++;
			
			results.verifyValueEquals(iTestNum, "checkRecordState4", strTestName, "Is the Record State as expected", 1, priceSet4.getRecordState());
			iTestNum++;
			
			results.verifyBooleanEquals(iTestNum, "checkValidFromIsNull4", strTestName, "Is the \"Valid From\" null as expected", true, TextHandler.isDateNull(priceSet4.getValidFrom()));
			iTestNum++;	
			
			results.verifyBooleanEquals( iTestNum,"checkValidToIsNull4", strTestName, "Is the \"Valid To\" as expected?", true, TextHandler.isDateNull(priceSet4.getValidTo()));
			iTestNum++;	
			
			results.verifyExpectedText(iTestNum, "checkRowId4", strTestName, "Is the Row Id as expected", "6", priceSet4.getRowId()+"");
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
	

	private static Document convertStringToDocument(String xmlStr) {
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


}
