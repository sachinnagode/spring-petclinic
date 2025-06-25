package org.springframework.samples.petclinic.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.model.TemperamentType;
import org.springframework.samples.petclinic.repository.TemperamentTypeRepository;
import org.springframework.samples.petclinic.service.TemperamentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemperamentTypeServiceImpl implements TemperamentTypeService {

	private final TemperamentTypeRepository temperamentTypeService;

	@Override
	public List<TemperamentType> findTemperamentTypes() {
		return temperamentTypeService.findTemperamentTypes();
	}

}
