package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.PetType;

import java.util.List;

public interface PetTypeService {

	List<PetType> findPetTypes();

}
