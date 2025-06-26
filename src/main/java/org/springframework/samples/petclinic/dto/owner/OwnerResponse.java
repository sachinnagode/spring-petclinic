package org.springframework.samples.petclinic.dto.owner;

import lombok.Data;
import org.springframework.samples.petclinic.dto.pet.PetResponse;

import java.util.List;

@Data
public class OwnerResponse {

	private Integer id;

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private String telephone;

	private List<PetResponse> pets;

}
