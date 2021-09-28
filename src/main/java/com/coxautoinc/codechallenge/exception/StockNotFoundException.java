package com.coxautoinc.codechallenge.exception;

/**
 * Exception to handle custom outputs when not a valid stockId
 * @author Bonifacio Velazquez
 *
 */
public class StockNotFoundException extends Exception {
	
	private static final long serialVersionUID = 7926308784636718297L;
	
	/**
	 * Constructs a new Exception with id of the stock
	 * @param stockId Stock Id not found
	 */
	public StockNotFoundException (String stockId) {
		super(String.format("No stock info for %s", stockId.toUpperCase()), null);
	}
}
