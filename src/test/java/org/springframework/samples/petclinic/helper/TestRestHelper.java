package org.springframework.samples.petclinic.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class TestRestHelper {

	private final RestTemplateBuilder builder;

	private final int port;

	public RestTemplate restTemplate() {
		return builder.rootUri("http://localhost:" + port).build();
	}

	public String url(String path) {
		return "http://localhost:" + port + path;
	}

}
