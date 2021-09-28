package com.coxautoinc.codechallenge.services;

import java.math.BigDecimal;
import java.util.List;

import yahoofinance.histquotes.HistoricalQuote;

/**
 * Functional Interface to define average calculator
 * @author Bonifacio Velazquez
 */
@FunctionalInterface
public interface FICalculateAvg {
	/**
	 * Abstract method must be imlpemented for client as a lambda expr
	 * @param historic List of HistoricalQuote
	 * @return The calculated average of the list.
	 */
	BigDecimal getAvg(List<HistoricalQuote> historic);
}
