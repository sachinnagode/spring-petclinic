package org.springframework.samples.petclinic.mapper;

import org.mapstruct.*;
import org.springframework.samples.petclinic.dto.pet.PetRequest;
import org.springframework.samples.petclinic.dto.pet.PetResponse;
import org.springframework.samples.petclinic.model.Pet;

@Mapper(componentModel = "spring", uses = EntityMappingSupport.class)
public interface PetMapper {

	@Mapping(source = "owner.id", target = "ownerId")
	PetResponse toDto(Pet pet);

	Pet toEntity(PetResponse request);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDto(PetRequest request, @MappingTarget Pet pet);

}
