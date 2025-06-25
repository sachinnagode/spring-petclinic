package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.Pet;

public interface PetService extends BaseService<Pet> {

	void deleteById(Integer id);

}
