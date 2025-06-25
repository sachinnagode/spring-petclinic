package org.springframework.samples.petclinic.dto.pet;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePetRequest {

	@NotBlank(message = "Name is mandatory")
	@Size(max = 30, message = "Name can be at most 30 characters long")
	private String name;

	@NotNull(message = "Birth date is required")
	@PastOrPresent(message = "Birth date cannot be in the future")
	private LocalDate birthDate;

	@NotNull(message = "Pet type is required")
	private Integer type;

	@NotNull(message = "Temperament is required")
	private Integer temperament;

	@NotNull(message = "Length is required")
	@DecimalMin(value = "0.1", inclusive = false, message = "Length must be greater than 0")
	private Double length;

	@NotNull(message = "Weight is required")
	@DecimalMin(value = "0.1", inclusive = false, message = "Weight must be greater than 0")
	private Double weight;

}
