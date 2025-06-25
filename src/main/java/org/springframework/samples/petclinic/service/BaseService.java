package org.springframework.samples.petclinic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

	Optional<T> findById(Integer ownerId);

	T save(T owner);

	Page<T> findAll(Pageable pageable);

	List<T> findAll();

}
