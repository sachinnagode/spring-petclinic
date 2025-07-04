package org.springframework.samples.petclinic.dto.pet;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PetRequest {

	@Size(max = 30, message = "Name can be at most 30 characters long")
	private String name;

	@PastOrPresent(message = "Birth date cannot be in the future")
	private LocalDate birthDate;

	private String type;

	private String temperament;

	@DecimalMin(value = "0.1", inclusive = false, message = "Length must be greater than 0")
	private Double length;

	@DecimalMin(value = "0.1", inclusive = false, message = "Weight must be greater than 0")
	private Double weight;

}
