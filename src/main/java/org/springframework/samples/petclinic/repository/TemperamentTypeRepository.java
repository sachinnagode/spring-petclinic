package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.TemperamentType;

import java.util.List;

public interface TemperamentTypeRepository extends JpaRepository<TemperamentType, Integer> {

	@Query("SELECT tType FROM TemperamentType tType ORDER BY tType.name")
	List<TemperamentType> findTemperamentTypes();

}
