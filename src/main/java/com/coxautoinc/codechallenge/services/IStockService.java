package com.coxautoinc.codechallenge.services;

import com.coxautoinc.codechallenge.exception.StockNotFoundException;
import com.coxautoinc.codechallenge.services.impl.StockServiceImpl;

/**
 * Facade for the business layer
 * @author Bonifacio Velazquez
 * @see StockServiceImpl
 */
public interface IStockService {
	/**
	 * Looks up for the id into de YahooFinancial services API
	 * @param id Id of the quote to lookUp
	 * @return the string with the resume of the stock (Price &amp; Average)
	 */
	String getStockById(String id) throws StockNotFoundException;
}
