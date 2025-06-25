package org.springframework.samples.petclinic.mapper;

import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.TemperamentType;
import org.springframework.stereotype.Component;

@Component
public class EntityMappingSupport {

	public PetType mapPetType(String id) {
		PetType pt = new PetType();
		pt.setId(Integer.parseInt(id));
		return pt;
	}

	public TemperamentType mapTemperamentType(String id) {
		TemperamentType tt = new TemperamentType();
		tt.setId(Integer.parseInt(id));
		return tt;
	}

	public Integer mapToId(Object entity) {
		if (entity instanceof PetType pt)
			return pt.getId();
		else if (entity instanceof TemperamentType tt)
			return tt.getId();
		else if (entity instanceof Integer id)
			return id;
		return null;
	}

}
