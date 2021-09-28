package com.coxautoinc.codechallenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coxautoinc.codechallenge.controller.StockRestController;
import com.coxautoinc.codechallenge.dto.StockRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringWebsocketApplicationTests {

	@Autowired
	StockRestController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void testControllerMethod() {
		assertThat(controller.stock(new StockRequest("ABC")).getMessage()).contains("Price");
	}

	@Test
	void testControllerMethodNotFound() {
		assertThat(controller.stock(new StockRequest("GC")).getMessage()).contains("No stock info for");
	}

}
