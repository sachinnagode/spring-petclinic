package org.springframework.samples.petclinic.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.samples.petclinic.dto.owner.OwnerRequest;
import org.springframework.samples.petclinic.dto.owner.OwnerResponse;
import org.springframework.samples.petclinic.model.Owner;

@Mapper(componentModel = "spring", uses = EntityMappingSupport.class)
public interface OwnerMapper {

	OwnerResponse toDto(Owner owner);

	Owner toEntity(OwnerRequest request);

	// UPDATE: Partial update from UpdateRequest to existing Owner entity
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDto(OwnerRequest dto, @MappingTarget Owner owner);

}
