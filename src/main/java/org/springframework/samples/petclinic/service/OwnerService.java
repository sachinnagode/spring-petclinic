package org.springframework.samples.petclinic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.model.Owner;

public interface OwnerService extends BaseService<Owner> {

	Page<Owner> findByLastNameStartingWith(String lastname, Pageable pageable);

	void delete(Integer ownerId);

}
