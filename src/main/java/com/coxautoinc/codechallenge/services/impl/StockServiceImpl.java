package com.coxautoinc.codechallenge.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coxautoinc.codechallenge.exception.StockNotFoundException;
import com.coxautoinc.codechallenge.services.FICalculateAvg;
import com.coxautoinc.codechallenge.services.IStockService;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

/**
 * Implementation of the Facade business layer (Services)
 * @author Bonifacio Velazquez
 * @see IStockService
 */
@Service
public class StockServiceImpl implements IStockService {
	
	private static Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

	/**
	 * Method to be implemented as lambda function into de Functional Interface to calculate average
	 * @param historic List of HistoricalQuote
	 * @return The calculated average of the list.
	 * @see FICalculateAvg
	 */
	private static BigDecimal getAvg(List<HistoricalQuote> hist) {
		log.info("Calculating average...");
		List<BigDecimal> historic = new ArrayList<BigDecimal>();
		if(log.isDebugEnabled()) {
			hist.forEach(x->log.debug("{}", x));
		}
		hist.forEach(x->historic.add(x.getClose().setScale(10, RoundingMode.HALF_DOWN)));
		BigDecimal sum = historic.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		return sum.divide(new BigDecimal(historic.size()), 2, RoundingMode.HALF_UP);
	}
	
	public String getStockById(String id) throws StockNotFoundException {
		log.info("Looking for stock info...");
		String str = null;
		try {
			log.debug("...id to find:<{}>...", id.toUpperCase());
			Stock stock = YahooFinance.get(id.toUpperCase(), true);
			BigDecimal price = stock.getQuote().getPrice();
			log.info("...Stock found...");
			if(log.isDebugEnabled()) {
				stock.print();
			}
			FICalculateAvg getAvg = StockServiceImpl::getAvg;
			BigDecimal avg = getAvg.getAvg(stock.getHistory());
			log.debug("...Average: <{}>", avg);
			str = String.format("%s, Price: $%s, Avg: $%s", id, price.setScale(2, RoundingMode.HALF_UP).toPlainString(), avg.setScale(2).toPlainString());
		}catch (IOException | NullPointerException e) {
			log.error("Error on retrieving data", e);
			throw new StockNotFoundException(id);
		}
		log.debug("Value to return: {}", str);
		log.info("...finishing operation.");
		return str;
	}

}
