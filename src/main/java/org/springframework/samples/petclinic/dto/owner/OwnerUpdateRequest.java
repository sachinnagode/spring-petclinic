package org.springframework.samples.petclinic.dto.owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OwnerUpdateRequest {

	@NotNull(message = "Owner ID is required for update")
	private Integer id;

	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotBlank(message = "Address is required")
	private String address;

	@NotBlank(message = "City is required")
	private String city;

	@Pattern(regexp = "\\d{10}", message = "Telephone must be 10 digits")
	private String telephone;

}
