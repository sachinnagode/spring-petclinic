package org.springframework.samples.petclinic.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataHelper {

	private static final Faker faker = new Faker();

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private TestDataHelper() {
		// utility class
	}

	public static String generateOwnerJson() {
		Map<String, Object> owner = new HashMap<>();
		owner.put("firstName", faker.name().firstName());
		owner.put("lastName", faker.name().lastName());
		owner.put("address", faker.address().streetAddress());
		owner.put("city", faker.address().city());
		owner.put("telephone", faker.phoneNumber().subscriberNumber(10));

		try {
			return objectMapper.writeValueAsString(owner);
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to create JSON for Owner", e);
		}
	}

	public static Map<String, String> generateOwnerData(String json) {
		try {
			return objectMapper.readValue(json, Map.class);
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to create JSON for Owner", e);
		}
	}

	public static Map<String, String> generateOwnerData() {
		Map<String, String> owner = new HashMap<>();
		owner.put("firstName", faker.name().firstName());
		owner.put("lastName", faker.name().lastName());
		owner.put("address", faker.address().streetAddress());
		owner.put("city", faker.address().city());
		owner.put("telephone", faker.phoneNumber().subscriberNumber(10));
		return owner;

	}

	public static Map<String, String> generatePetJson(String json) {
		try {
			return objectMapper.readValue(json, Map.class);
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to create JSON for Owner", e);
		}
	}

	public static String generatePetData() {
		Map<String, Object> petData = new HashMap<>();

		petData.put("name", faker.animal().name());
		petData.put("birthDate", getRandomBirthDate());
		petData.put("type", faker.options().option("1", "2", "3", "4"));
		petData.put("temperament", faker.options().option("1", "2", "3", "4"));
		petData.put("length", faker.number().randomDouble(1, 20, 100)); // e.g. 35.6
		petData.put("weight", faker.number().randomDouble(1, 5, 50)); // e.g. 18.2
		try {
			return objectMapper.writeValueAsString(petData);
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to create JSON for Owner", e);
		}
	}

	public static String getPetNameFromJson(String json) {
		try {
			JsonNode root = objectMapper.readTree(json);
			return root.get("name").asText();
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to extract pet name from JSON", e);
		}
	}

	private static String getRandomBirthDate() {
		// Random date in past 10 years
		LocalDate now = LocalDate.now();
		LocalDate start = now.minusYears(10);
		long startEpochDay = start.toEpochDay();
		long endEpochDay = now.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
		return LocalDate.ofEpochDay(randomDay).toString(); // Format: YYYY-MM-DD
	}

}
