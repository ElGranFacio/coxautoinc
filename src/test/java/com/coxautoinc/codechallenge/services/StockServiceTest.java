package com.coxautoinc.codechallenge.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coxautoinc.codechallenge.exception.StockNotFoundException;
import com.coxautoinc.codechallenge.services.IStockService;

@SpringBootTest
class StockServiceTest {

	@Autowired
	IStockService service;

	@Test
	void testGetStockByIdOK() throws StockNotFoundException {
		assertThat(service.getStockById("ABC")).contains("Price");
	}

	@Test
	void testGetStockByIdKO() throws StockNotFoundException {
		assertThatThrownBy(
			() -> service.getStockById("GC")
				).isInstanceOf(StockNotFoundException.class)
					.hasMessageContaining("No stock info for");
	}

	@Test
	void testStockKO() {
		assertThatExceptionOfType(StockNotFoundException.class)
			.isThrownBy(
					() -> {
						service.getStockById("GC");
					}
				);
	}

}
