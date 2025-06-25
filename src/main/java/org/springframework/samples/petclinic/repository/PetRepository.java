package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
