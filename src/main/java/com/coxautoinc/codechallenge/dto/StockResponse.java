package com.coxautoinc.codechallenge.dto;

/**
 * Envelope for outcoming messages
 * @author Bonifacio Velazquez
 * @see StockRequest
 */
public class StockResponse extends StockRequest {
	
	private boolean found = false;
	
	/**
	 * Message string for output proccess
	 */
	private String message;

	/**
	 * Default constructor for new instances
	 */
	public StockResponse() { 
		super();
	}

	/**
	 * Parametrized construtor for new instances
	 * @param message The message output to set
	 */
	public StockResponse(final String message) {
		this.message = message;
	}

	/**
	 * Parametrized construtor for new instances
	 * @param message The message output to set
	 * @param quote The quote id to set
	 */
	public StockResponse(final String message, final String quote) {
		super(quote);
		this.message = message;
	}

	public final String getMessage() {
		return message;
	}

	public final boolean isFound() {
		return found;
	}

	public final void setFound(boolean found) {
		this.found = found;
	}

	public final void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * Return a String representation of the object in json string format
	 */
	@Override
	public String toString() {
		return String.format("{\"quote\":\"%s\",\"message\":\"%s\", \"found\":%b}", getQuote(), getMessage(), isFound());
	}
	
	public static void main(String[] args) {
		StockRequest req = new StockRequest("ABC");
		System.out.println(req.toString());
		StockResponse res = StockResponse.createFromRequest(req);
		res.setFound(true);
		res.setMessage("OK");
		System.out.println(res.toString());
	}

	public static StockResponse createFromRequest(StockRequest req) {
		StockResponse val2return = new StockResponse();
		val2return.setQuote(req.getQuote());
		return val2return;
	}
}
