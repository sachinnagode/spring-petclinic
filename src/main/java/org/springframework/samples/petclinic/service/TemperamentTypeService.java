package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.TemperamentType;

import java.util.List;

public interface TemperamentTypeService {

	List<TemperamentType> findTemperamentTypes();

}
