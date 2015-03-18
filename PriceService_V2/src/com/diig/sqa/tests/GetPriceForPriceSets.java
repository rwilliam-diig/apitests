package com.diig.sqa.tests;

import org.apache.log4j.Logger;

import com.diig.attis.price.service.PriceService;
import com.diig.attis.intergation.order.xml.XMLOrderRequestBuilder;
import com.diig.attis.price.model.Price;
import com.diig.attis.price.model.PriceItem;
import com.diig.attis.price.model.PriceRequest;
import com.diig.attis.price.model.PriceResponse;
import com.diig.attis.price.model.PriceSet;
import com.diig.attis.price.model.impl.PriceRequestImpl;
import com.diig.common.application.ApplicationConfiguration;
import com.diig.common.application.ApplicationConfigurationFactory;
import com.diig.common.service.ServiceException;
import com.diig.sqa.services.ServiceHandler;
import com.diig.sqa.utilities.DatabaseHandler;
import com.diig.sqa.utilities.Logging;
import com.diig.sqa.utilities.Reporting;
import com.diig.sqa.utilities.TextHandler;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;


public class GetPriceForPriceSets {
	
	static ServiceHandler psh = new ServiceHandler();
	static Reporting results = new Reporting();
	static DatabaseHandler db = new DatabaseHandler();
	static TextHandler th = new TextHandler();
	static Logging log = new Logging();
	private static final Logger LOG = Logger.getLogger(AddNewPriceSet.class);
	private static final ApplicationConfiguration CONFIG = ApplicationConfigurationFactory.getConfiguration();
	private static final PriceService SUBJECT = (PriceService) CONFIG.getObjectById("priceService");
	static Object obj = GetPriceForPriceSets.class.getName();
	static String strTestName = "GetPriceForPriceSets";
	static int iTestNum= 1;
	
	static String dataUnderTest="";

	public static void main(String[] args) {
		log.logInfo(obj.toString(), strTestName+" Started at:- "+Logging.getCurrentDateAndTime());
		
		try {
			SUBJECT.start();
			} 
		catch (ServiceException e1) {
			LOG.error("Error Starting Service...\n"+e1.getMessage());
			}
		
		//do a price request for a discounted customer
		Document doc = TextHandler.convertStringToDocument("<order>"
	            + "<orderSections>"
	            + "<orderSection>"
	            + "<products>"
	            + "<product productTypeId=\"FORM12\">"
	            + "</product>"
	            + "</products>"
	            + "</orderSection>"
	            + "</orderSections>"
	            + "</order>");
		Map<String, Object> data = XMLOrderRequestBuilder.parseOrderXML(doc.getFirstChild());
		
		String strCustomerId1 = "CUSTOMER1";
		
		String strGroupId1 = "";
		
		String strBusinessUnit = "MB";
		
		PriceRequest request = new PriceRequestImpl(strBusinessUnit, strCustomerId1, strGroupId1, data);
		
		PriceResponse response = SUBJECT.process(request);
		
		Price price1 = response.getResponseData();
		
		results.verifyExpectedText( iTestNum,"checkBusinessUnitId", strTestName, "Is the Business Unit as expected?", strBusinessUnit, request.getBusinessUnitId());
		iTestNum++;
		
		results.verifyExpectedText( iTestNum,"checkCustomerId", strTestName, "Is the Customer Id as expected?", strCustomerId1, request.getCustomerId());
		iTestNum++;
		
		results.verifyExpectedText( iTestNum,"checkGroupId", strTestName, "Is the Group Id as expected?", strGroupId1, request.getCustomerGroupId());
		iTestNum++;
		
		results.verifyBooleanEquals(iTestNum, "checkResponseForErrors", strTestName, "Does the response contain no errors", true, response.getErrors().isEmpty());
		iTestNum++;
		
		results.verifyBooleanEquals(iTestNum, "checkIfGrandTotalIsNotNull", strTestName, "Is grand total null?",false,TextHandler.isObjectNull(response.getResponseData().getGrandTotal()));
		iTestNum++;
		
		List<PriceItem> prItem = response.getResponseData().getItems();
		PriceItem item = response.getResponseData().getItem(0);
		String priceId = response.getResponseData().getPriceId();
		int recordState = response.getResponseData().getRecordState();
		double rowId =  response.getResponseData().getRowId();
		Date dateModified = response.getResponseData().getModifiedOn();
		
		System.out.println(response.getResponseData().getRowId());
		
		PriceItem item1 = response.getResponseData().getItems().get(0);
		
		String strProductId = item1.getProductId();
		
		results.verifyValueEquals(iTestNum, "checkRowId", strTestName, "Is the Row Id as expected",1, response.getResponseData().getRowId());
		iTestNum++;
		
		results.verifyExpectedText( iTestNum,"checkProductId", strTestName, "Is the Product Id as expected?", "FORM12", item1.getProductId());
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkMarginCalculation", strTestName, "Is the Margin Price calculation as expected", TextHandler.convertMonetaryAmountToDouble(item1.getCostPrice()), TextHandler.checkMarginPrice(TextHandler.convertMonetaryAmountToDouble(item1.getRetailPrice())));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkSalePriceDiscountCalculation", strTestName, "Is the Discounted amount for Sale Price calculation as expected", TextHandler.convertMonetaryAmountToDouble(item1.getSalePrice()), TextHandler.getDiscountedAmount(TextHandler.convertMonetaryAmountToDouble(item1.getRetailPrice()),item1.getDiscount()));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkUnitPriceDiscountCalculation", strTestName, "Is the Discounted amount for Unit Price calculation as expected", TextHandler.convertMonetaryAmountToDouble(item1.getUnitPrice()), TextHandler.getDiscountedAmount(TextHandler.convertMonetaryAmountToDouble(item1.getRetailPrice()),item1.getDiscount()));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkTaxCalculation", strTestName, "Is the Tax calculation as expected",  TextHandler.getTaxAmount(TextHandler.convertMonetaryAmountToDouble(item1.getUnitPrice()),item1.getTaxRate()),TextHandler.convertMonetaryAmountToDouble(item1.getTaxAmount()));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkAmountIncludingTaxCalculation", strTestName, "Is the Taxed Total calculation as expected", TextHandler.getAmountAfterTax(TextHandler.convertMonetaryAmountToDouble(item1.getUnitPrice()),item1.getTaxRate()),TextHandler.convertMonetaryAmountToDouble(item1.getTotalPrice()));
		iTestNum++;
		
		//Do a price request for a non-discounted customer
		String strCustomerId2 = "TEST_CUSTOMER1";
		
		String strGroupId2 = "QA_GROUP1";
		
		PriceRequest request2 = new PriceRequestImpl(strBusinessUnit, strCustomerId2, strGroupId2, data);
		
		PriceResponse response2 = SUBJECT.process(request2);
		
		Price price2 = response2.getResponseData();
		
		PriceItem item2 = response2.getResponseData().getItems().get(0);
				
		results.verifyExpectedText( iTestNum,"checkBusinessUnitId", strTestName, "Is the Business Unit as expected?", strBusinessUnit, request2.getBusinessUnitId());
		iTestNum++;
		
		results.verifyExpectedText( iTestNum,"checkCustomerId", strTestName, "Is the Customer Id as expected?", strCustomerId2, request2.getCustomerId());
		iTestNum++;
		
		results.verifyExpectedText( iTestNum,"checkGroupId", strTestName, "Is the Group Id as expected?", strGroupId2, request2.getCustomerGroupId());
		iTestNum++;
		
		results.verifyBooleanEquals(iTestNum, "checkResponseForErrors", strTestName, "Does the response contain no errors", true, response2.getErrors().isEmpty());
		iTestNum++;
		
		results.verifyBooleanEquals(iTestNum, "checkGrandTotalIsNotNull", strTestName, "Is grand total null?",false,TextHandler.isObjectNull(response2.getResponseData().getGrandTotal()));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkRowId", strTestName, "Is the Row Id as expected",2, response2.getResponseData().getRowId());
		iTestNum++;
		
		results.verifyExpectedText( iTestNum,"checkProductId", strTestName, "Is the Product Id as expected?", "FORM12", item2.getProductId());
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkMarginCalculation", strTestName, "Is the Margin Price calculation as expected", TextHandler.convertMonetaryAmountToDouble(item2.getCostPrice()), TextHandler.checkMarginPrice(TextHandler.convertMonetaryAmountToDouble(item2.getRetailPrice())));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkSalePriceDiscountCalculation", strTestName, "Is the Discounted amount for Sale Price calculation as expected", TextHandler.convertMonetaryAmountToDouble(item2.getSalePrice()), TextHandler.getDiscountedAmount(TextHandler.convertMonetaryAmountToDouble(item2.getRetailPrice()),item2.getDiscount()));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkUnitPriceDiscountCalculation", strTestName, "Is the Discounted amount for Unit Price calculation as expected", TextHandler.convertMonetaryAmountToDouble(item2.getUnitPrice()), TextHandler.getDiscountedAmount(TextHandler.convertMonetaryAmountToDouble(item2.getRetailPrice()),item2.getDiscount()));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkTaxCalculation", strTestName, "Is the Tax calculation as expected",  TextHandler.getTaxAmount(TextHandler.convertMonetaryAmountToDouble(item2.getUnitPrice()),item2.getTaxRate()),TextHandler.convertMonetaryAmountToDouble(item2.getTaxAmount()));
		iTestNum++;
		
		results.verifyValueEquals(iTestNum, "checkAmountIncludingTaxCalculation", strTestName, "Is the Taxed Total calculation as expected", TextHandler.getAmountAfterTax(TextHandler.convertMonetaryAmountToDouble(item1.getUnitPrice()),item1.getTaxRate()),TextHandler.convertMonetaryAmountToDouble(item1.getTotalPrice()));
		iTestNum++;
		
		log.logInfo(obj.toString(), strTestName+" Completed at:- "+Logging.getCurrentDateAndTime());
		
		Reporting.generateHtmlReport();
		
		Reporting.resetExecutionResults();
		
	}
	
	
	 
}
