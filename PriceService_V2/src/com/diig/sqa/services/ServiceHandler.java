package com.diig.sqa.services;

import org.apache.log4j.Logger;

import com.diig.attis.price.service.PriceService;
import com.diig.common.application.ApplicationConfiguration;
import com.diig.common.application.ApplicationConfigurationFactory;

public class ServiceHandler {
	
	private static final Logger LOG = Logger.getLogger(ServiceHandler.class);
	private static final ApplicationConfiguration CONFIG = ApplicationConfigurationFactory.getConfiguration();
	private static final PriceService SUBJECT = (PriceService) CONFIG.getObjectById("priceService");
	
	
}
