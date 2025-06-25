package org.springframework.samples.petclinic.dto.owner;

import lombok.Data;
import org.springframework.samples.petclinic.dto.pet.PetDTO;

import java.util.List;

@Data
public class OwnerDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private String telephone;

	private List<PetDTO> pets;

}
