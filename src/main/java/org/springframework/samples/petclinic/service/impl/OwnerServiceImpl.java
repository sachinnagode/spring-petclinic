package org.springframework.samples.petclinic.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepository;

	@Override
	public Optional<Owner> findById(Integer ownerId) {
		return ownerRepository.findById(ownerId);
	}

	@Override
	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public Page<Owner> findByLastNameStartingWith(String lastname, Pageable pageable) {
		return ownerRepository.findByLastNameStartingWith(lastname, pageable);
	}

	@Override
	public void delete(Integer ownerId) {
		ownerRepository.deleteById(ownerId);
	}

	@Override
	public Page<Owner> findAll(Pageable pageable) {
		return ownerRepository.findAll(pageable);
	}

	@Override
	public List<Owner> findAll() {
		return ownerRepository.findAll();
	}

}
