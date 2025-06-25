package org.springframework.samples.petclinic.dto.pet;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.TemperamentType;

import java.time.LocalDate;

@Data
public class PetModel extends RepresentationModel<PetModel> {

	private Integer id;

	private String name;

	private LocalDate birthDate;

	private PetType type;

	private TemperamentType temperament;

	private Double length;

	private Double weight;

}
