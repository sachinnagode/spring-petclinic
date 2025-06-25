package org.springframework.samples.petclinic.controller.hateoas;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.converter.PetModelAssembler;
import org.springframework.samples.petclinic.dto.pet.CreatePetRequest;
import org.springframework.samples.petclinic.dto.pet.PetDTO;
import org.springframework.samples.petclinic.dto.pet.UpdatePetRequest;
import org.springframework.samples.petclinic.exception.handler.EntityNotFoundException;
import org.springframework.samples.petclinic.mapper.PetMapper;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.samples.petclinic.constants.CommonConstants.*;
import static org.springframework.samples.petclinic.constants.RequestUrlConstants.PET_ID_IN_REQUEST_PATH;
import static org.springframework.samples.petclinic.constants.RequestUrlConstants.V_2_PETS;

@RestController
@RequestMapping(V_2_PETS)
@RequiredArgsConstructor
public class PetApiController {

	private final PetService petService;

	private final OwnerService ownerService;

	private final PetMapper petMapper;

	private final PetModelAssembler petModelAssembler;

	@GetMapping(PET_ID_IN_REQUEST_PATH)
	public ResponseEntity<EntityModel<PetDTO>> getPetById(@PathVariable Integer ownerId, @PathVariable Integer petId) {
		ownerService.findById(petId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));
		Pet pet = petService.findById(petId).orElseThrow(() -> new EntityNotFoundException(PET, petId));
		PetDTO dto = petMapper.toDto(pet);
		return ResponseEntity.ok(petModelAssembler.toModel(dto));
	}

	@PostMapping
	public ResponseEntity<EntityModel<PetDTO>> createPetForOwner(@PathVariable Integer ownerId,
			@RequestBody @Valid CreatePetRequest request) {

		Owner owner = ownerService.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));

		Pet pet = petMapper.toEntity(request);
		pet.setOwner(owner);
		owner.getPets().add(pet);
		Owner savedOwner = ownerService.save(owner);
		Optional<Pet> first = savedOwner.getPets().stream().filter(p -> p.getName().equals(pet.getName())).findFirst();
		PetDTO petDTO = petMapper.toDto(first.orElse(pet));

		return ResponseEntity.created(linkTo(methodOn(OwnerApiController.class).getOwnerById(ownerId)).toUri())
			.body(petModelAssembler.toModel(petDTO));
	}

	@PutMapping(PET_ID_IN_REQUEST_PATH)
	public ResponseEntity<EntityModel<PetDTO>> updatePet(@PathVariable Integer petId,
			@RequestBody @Valid UpdatePetRequest request) {
		Pet existingPet = petService.findById(petId).orElseThrow(() -> new EntityNotFoundException(PET, petId));

		petMapper.updateEntityFromDto(request, existingPet);
		Pet updatedPet = petService.save(existingPet);

		PetDTO dto = petMapper.toDto(updatedPet);
		return ResponseEntity.ok(petModelAssembler.toModel(dto));
	}

	@DeleteMapping(PET_ID_IN_REQUEST_PATH)
	public ResponseEntity<Void> deletePet(@PathVariable Integer petId) {
		petService.findById(petId).orElseThrow(() -> new EntityNotFoundException(PET, petId));
		petService.deleteById(petId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<PetDTO>>> getAllPetsForOwner(@PathVariable Integer ownerId) {
		Owner owner = ownerService.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));

		List<EntityModel<PetDTO>> petModels = owner.getPets().stream().map(petMapper::toDto).map(pet -> {
			EntityModel<PetDTO> petDTOEntityModel = petModelAssembler.toModel(pet);
			linkTo(methodOn(PetApiController.class).getAllPetsForOwner(ownerId)).withRel(ALL_PETS);
			return petDTOEntityModel;
		}).toList();

		CollectionModel<EntityModel<PetDTO>> collectionModel = CollectionModel.of(petModels);
		collectionModel.add(linkTo(methodOn(PetApiController.class).getAllPetsForOwner(ownerId)).withSelfRel());
		collectionModel.add(linkTo(methodOn(OwnerApiController.class).getOwnerById(ownerId)).withRel(OWNER));
		return ResponseEntity.ok(collectionModel);
	}

}
