package org.springframework.samples.petclinic.dto.pet;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PetDTO {

	private Integer id;

	private String name;

	private LocalDate birthDate;

	private String type;

	private String temperament;

	private Double length;

	private Double weight;

	private List<VisitDTO> visits;

	private Integer ownerId;

}
