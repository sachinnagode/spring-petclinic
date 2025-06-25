package org.springframework.samples.petclinic.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.samples.petclinic.dto.owner.OwnerCreateRequest;
import org.springframework.samples.petclinic.dto.owner.OwnerDTO;
import org.springframework.samples.petclinic.dto.owner.OwnerUpdateRequest;
import org.springframework.samples.petclinic.model.Owner;

@Mapper(componentModel = "spring", uses = EntityMappingSupport.class)
public interface OwnerMapper {

	OwnerDTO toDto(Owner owner);

	Owner toEntity(OwnerCreateRequest request);

	// UPDATE: Partial update from UpdateRequest to existing Owner entity
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDto(OwnerUpdateRequest dto, @MappingTarget Owner owner);

}
