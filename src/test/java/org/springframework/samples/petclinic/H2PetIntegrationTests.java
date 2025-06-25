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
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.samples.petclinic.helper.TestDataHelper.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@Transactional
class H2PetIntegrationTests {

	public static final String OWNER_ID = "ownerId";

	public static final String PET_ID = "petId";

	@LocalServerPort
	int port;

	@Autowired
	private PetService petService;

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
	@DisplayName("GET /owners/{ownerId}/pets should return all pets for owner")
	void shouldReturnAllPetsForOwner() {
		Owner owner = ownerService.findById(1).orElseThrow();
		List<Pet> pets = owner.getPets();

		RestTemplate template = rest.restTemplate();
		String url = rest.url(ApiPaths.BASE_V2_OWNERS + "/" + owner.getId() + "/pets");

		ResponseEntity<String> result = template.exchange(RequestEntity.get(URI.create(url)).build(), String.class);

		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		for (Pet pet : pets) {
			assertThat(result.getBody()).contains(pet.getName());
		}
	}

	@Test
	@DisplayName("POST /owners/{ownerId}/pets should create new pet for owner")
	void shouldCreateNewPetForOwner() {
		int ownerId = 1;
		String json = generatePetData();
		Map<String, String> petJson = generatePetJson(json);
		String url = rest.url(ApiPaths.BASE_V2_OWNERS + "/" + ownerId + "/pets");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(json, headers);

		ResponseEntity<String> result = rest.restTemplate().postForEntity(url, request, String.class);

		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(result.getBody()).contains(getPetNameFromJson(json));
	}

	@Test
	@DisplayName("GET /pets/{id} should return 200 OK with pet details")
	void shouldReturnPetById() {
		int ownerId = 1;
		Optional<Owner> optionalOwner = ownerService.findById(ownerId);
		assertThat(optionalOwner).isPresent();
		Owner owner = optionalOwner.get();
		assertThat(owner.getId()).isEqualTo(ownerId); // optional: validate contents
		Pet pet = owner.getPets().get(0);

		String url = UriComponentsBuilder.fromUriString(rest.url(ApiPaths.BASE_V2_PETS + "/{petId}"))
			.buildAndExpand(Map.of(OWNER_ID, ownerId, PET_ID, pet.getId()))
			.toUriString();

		ResponseEntity<String> result = rest.restTemplate().getForEntity(url, String.class);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).contains(pet.getName());
	}

	@Test
	@DisplayName("GET /pets/999999 should return 404 NOT FOUND")
	void shouldReturnNotFoundForNonExistingPet() {
		String url = UriComponentsBuilder.fromUriString(rest.url(ApiPaths.BASE_V2_PETS + "/{petId}"))
			.buildAndExpand(Map.of(OWNER_ID, 1, PET_ID, 99999))
			.toUriString();

		HttpClientErrorException.NotFound ex = assertThrows(HttpClientErrorException.NotFound.class,
				() -> rest.restTemplate().getForEntity(url, String.class));
		assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
