package org.springframework.samples.petclinic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.model.Vet;

import java.util.List;

public interface VetService {

	Page<Vet> findAll(Pageable pageable);

	List<Vet> findAll();

}
