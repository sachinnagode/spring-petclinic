package org.springframework.samples.petclinic.converter;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.samples.petclinic.controller.hateoas.PetApiController;
import org.springframework.samples.petclinic.dto.pet.PetDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.samples.petclinic.constants.RequestUrlConstants.V_2_OWNERS_OWNER_ID_PETS_PET_ID;

@Component
public class PetModelAssembler implements RepresentationModelAssembler<PetDTO, EntityModel<PetDTO>> {

	@Override
	public EntityModel<PetDTO> toModel(PetDTO dto) {
		String url = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path(V_2_OWNERS_OWNER_ID_PETS_PET_ID)
			.buildAndExpand(dto.getOwnerId(), dto.getId())
			.toUriString();
		return EntityModel.of(dto, Link.of(url).withSelfRel());
	}

}
