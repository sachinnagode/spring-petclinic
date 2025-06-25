package org.springframework.samples.petclinic.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

	private final PetRepository petRepository;

	@Override
	public Page<Pet> findAll(Pageable pageable) {
		return petRepository.findAll(pageable);
	}

	@Override
	public Optional<Pet> findById(Integer petId) {
		return petRepository.findById(petId);
	}

	@Override
	public Pet save(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public List<Pet> findAll() {
		return petRepository.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		petRepository.deleteById(id);
	}

}
