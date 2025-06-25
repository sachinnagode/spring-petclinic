package org.springframework.samples.petclinic.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetTypeRepository;
import org.springframework.samples.petclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetTypeServiceImpl implements PetTypeService {

	private final PetTypeRepository petTypeRepository;

	@Override
	public List<PetType> findPetTypes() {
		return petTypeRepository.findPetTypes();
	}

}
