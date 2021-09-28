package com.coxautoinc.codechallenge.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.coxautoinc.codechallenge.controller.StockRestController;
import com.coxautoinc.codechallenge.dto.StockRequest;
import com.coxautoinc.codechallenge.exception.StockNotFoundException;
import com.coxautoinc.codechallenge.services.IStockService;

@SpringBootTest
class StockControllerTest {

	static final String msgOK = "ABC, Price: $20.00, Avg: $19.90";

	static final String msgKO = "No stock info for CK";

	@Mock
	IStockService stockService;

	@InjectMocks
	StockRestController stockController;

	@Test
	void testStockOK() throws Exception {
		String stockId = "CK";
		Mockito.when(stockService.getStockById(stockId)).thenReturn(msgOK);
		StockRequest msg = new StockRequest(stockId);
		assertThat(stockController.stock(msg).getMessage()).isEqualTo(msgOK);
	}

	@Test
	void testStockKO() throws Exception {
		String stockId = "CK";
		Mockito.when(stockService.getStockById(stockId)).thenThrow(new StockNotFoundException(stockId));
		StockRequest msg = new StockRequest(stockId);
		assertThat(stockController.stock(msg).getMessage()).isEqualTo(msgKO);
	}
}
