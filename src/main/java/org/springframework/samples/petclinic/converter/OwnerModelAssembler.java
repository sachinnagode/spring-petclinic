package org.springframework.samples.petclinic.converter;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.samples.petclinic.controller.hateoas.OwnerApiController;
import org.springframework.samples.petclinic.controller.hateoas.PetApiController;
import org.springframework.samples.petclinic.dto.owner.OwnerDTO;
import org.springframework.samples.petclinic.dto.pet.PetDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.samples.petclinic.constants.RequestUrlConstants.V_2_OWNERS_OWNER_ID_PETS_PET_ID;

@Component
public class OwnerModelAssembler implements RepresentationModelAssembler<OwnerDTO, EntityModel<OwnerDTO>> {

	@Override
	public EntityModel<OwnerDTO> toModel(OwnerDTO owner) {
		EntityModel<OwnerDTO> model = EntityModel.of(owner);

		model.add(linkTo(methodOn(OwnerApiController.class).getOwnerById(owner.getId())).withSelfRel());
		model.add(linkTo(methodOn(OwnerApiController.class).deleteOwner(owner.getId())).withRel("delete"));
		model.add(linkTo(methodOn(OwnerApiController.class).updateOwner(owner.getId(), null)).withRel("update"));

		for (PetDTO pet : owner.getPets()) {
			String url = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(V_2_OWNERS_OWNER_ID_PETS_PET_ID)
				.buildAndExpand(owner.getId(), pet.getId()) // ✅ Matches {ownerId},
															// {petId}
				.toUriString();
			model.add(Link.of(url).withRel("pet_" + pet.getId()));
		}

		return model;
	}

}
