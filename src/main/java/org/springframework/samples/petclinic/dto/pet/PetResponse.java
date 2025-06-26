package org.springframework.samples.petclinic.dto.pet;

import lombok.Data;
import org.springframework.samples.petclinic.dto.visit.VisitDTO;

import java.time.LocalDate;
import java.util.List;

@Data
public class PetResponse {

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
