package com.coxautoinc.codechallenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.coxautoinc.codechallenge.dto.StockRequest;
import com.coxautoinc.codechallenge.dto.StockResponse;
import com.coxautoinc.codechallenge.exception.StockNotFoundException;
import com.coxautoinc.codechallenge.services.IStockService;

@RestController
public class StockRestController {

	@Autowired
	IStockService service;

	/**
	 * Log handler
	 */
	private static Logger log = LoggerFactory.getLogger(StockRestController.class);
	
	@PostMapping("/stock")
	public StockResponse stock(@RequestBody StockRequest message) {
		StockResponse val2return = new StockResponse();
		log.info("We got a new message...");
		if (log.isDebugEnabled()) {
			log.debug("{}", message);
		}
		val2return.setQuote(message.getQuote().toUpperCase());
		try {
			String str = service.getStockById(val2return.getQuote());
			val2return.setMessage(HtmlUtils.htmlEscape(str));
			val2return.setFound(true);
		} catch (StockNotFoundException e) {
			val2return.setMessage(HtmlUtils.htmlEscape(e.getMessage()));
			val2return.setFound(false);
			log.error(e.getMessage(), e);
		}
		log.info("... message processed.");
		if (log.isDebugEnabled()) {
			log.debug("{}", val2return);
		}
		return val2return;
	}
}
