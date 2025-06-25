package org.springframework.samples.petclinic.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetServiceImpl implements VetService {

	private final VetRepository vetRepository;

	@Override
	public Page<Vet> findAll(Pageable pageable) {
		return vetRepository.findAll(pageable);
	}

	@Override
	public List<Vet> findAll() {
		return (List<Vet>) vetRepository.findAll();
	}

}
