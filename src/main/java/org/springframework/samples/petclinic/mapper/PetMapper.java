package org.springframework.samples.petclinic.mapper;

import org.mapstruct.*;
import org.springframework.samples.petclinic.dto.pet.CreatePetRequest;
import org.springframework.samples.petclinic.dto.pet.PetDTO;
import org.springframework.samples.petclinic.dto.pet.UpdatePetRequest;
import org.springframework.samples.petclinic.model.Pet;

@Mapper(componentModel = "spring", uses = EntityMappingSupport.class)
public interface PetMapper {

	@Mapping(source = "owner.id", target = "ownerId")
	PetDTO toDto(Pet pet);

	Pet toEntity(CreatePetRequest request);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDto(UpdatePetRequest request, @MappingTarget Pet pet);

}
