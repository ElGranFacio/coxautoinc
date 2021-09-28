package com.coxautoinc.codechallenge;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void ulrIndexTest() throws Exception {
		String url = "http://localhost:" + port + "/";
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		assertThat(exchange.getStatusCode().toString()).contains("200 OK");
	}
}