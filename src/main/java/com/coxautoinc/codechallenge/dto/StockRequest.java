package com.coxautoinc.codechallenge.dto;

/**
 * Envelope for incoming messages
 * @author Bonifacio Velazquez
 * @see StockResponse
 */
public class StockRequest {
	
	/**
	 * Quote id to lookup
	 */
	private String quote;

	/**
	 * Default constructor for new instances
	 */
	public StockRequest() { }

	/**
	 * Parametrized construtor for new instances
	 * @param name Quote's name
	 */
	public StockRequest(String name) {
		this.quote = name;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(final String name) {
		this.quote = name;
	}
	
	/**
	 * Return a Strin representation of the object in json string format
	 */
	@Override
	public String toString() {
		return String.format("{\"quote\":\"%s\"}", getQuote());
	}

}
