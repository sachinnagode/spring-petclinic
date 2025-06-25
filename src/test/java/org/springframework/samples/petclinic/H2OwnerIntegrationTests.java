package org.springframework.samples.petclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.samples.petclinic.constants.ApiPaths;
import org.springframework.samples.petclinic.helper.TestRestHelper;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.samples.petclinic.constants.CommonTestConstants.FIRST_NAME;
import static org.springframework.samples.petclinic.constants.CommonTestConstants.LAST_NAME;
import static org.springframework.samples.petclinic.helper.TestDataHelper.generateOwnerData;
import static org.springframework.samples.petclinic.helper.TestDataHelper.generateOwnerJson;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@Transactional
class H2OwnerIntegrationTests {

	@LocalServerPort
	int port;

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private RestTemplateBuilder builder;

	private TestRestHelper rest;

	@BeforeEach
	void setup() {
		rest = new TestRestHelper(builder, port);
	}

	@Test
	@DisplayName("GET /owners/1 should return 200 OK with owner details")
	void testFindAll() {
		ownerService.findAll();
		ownerService.findAll(); // served from cache
	}

	@Test
	@DisplayName("GET /owners/1 should return 200 OK with owner details")
	void testOwnerDetails() {
		RestTemplate template = rest.restTemplate();
		String url = rest.url(ApiPaths.BASE_V2_OWNERS + "/1");
		ResponseEntity<String> result = template.exchange(RequestEntity.get(URI.create(url)).build(), String.class);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@DisplayName("GET /owners/999999 should throw HttpClientErrorException.NotFound")
	void shouldThrowNotFound_whenOwnerNotFound() {
		RestTemplate template = rest.restTemplate();
		String url = rest.url(ApiPaths.BASE_V2_OWNERS + "/999999");

		HttpClientErrorException.NotFound ex = assertThrows(HttpClientErrorException.NotFound.class,
				() -> template.exchange(RequestEntity.get(URI.create(url)).build(), String.class));

		assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(ex.getMessage()).contains("404 Not Found");
	}

	@Test
	@DisplayName("POST /api/v2/owners should create a new owner with dynamic data")
	void shouldCreateNewOwner() {
		RestTemplate template = rest.restTemplate();
		String url = rest.url(ApiPaths.BASE_V2_OWNERS);
		String json = generateOwnerJson();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(json, headers);
		ResponseEntity<String> result = template.postForEntity(url, request, String.class);

		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(result.getHeaders().getLocation()).isNotNull();

		Map<String, String> ownerData = generateOwnerData(json);
		assertThat(result.getBody()).contains(ownerData.get(FIRST_NAME), ownerData.get(LAST_NAME));
	}

}
