package org.springframework.samples.petclinic.controller.hateoas;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.converter.PetModelAssembler;
import org.springframework.samples.petclinic.dto.pet.PetRequest;
import org.springframework.samples.petclinic.dto.pet.PetResponse;
import org.springframework.samples.petclinic.exception.handler.EntityNotFoundException;
import org.springframework.samples.petclinic.mapper.PetMapper;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.samples.petclinic.constants.CommonConstants.*;
import static org.springframework.samples.petclinic.constants.ErrorMessageConstants.*;
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
	public ResponseEntity<EntityModel<PetResponse>> getPetById(@PathVariable Integer ownerId,
			@PathVariable Integer petId) {
		ownerService.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));
		Pet pet = petService.findById(petId).orElseThrow(() -> new EntityNotFoundException(PET, petId));
		PetResponse dto = petMapper.toDto(pet);
		return ResponseEntity.ok(petModelAssembler.toModel(dto));
	}

	@PostMapping
	public ResponseEntity<EntityModel<PetResponse>> createPetForOwner(@PathVariable Integer ownerId,
			@RequestBody @Valid PetResponse request) {

		Owner owner = ownerService.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));

		Pet pet = petMapper.toEntity(request);
		pet.setOwner(owner);
		owner.getPets().add(pet);
		Owner savedOwner = ownerService.save(owner);
		Optional<Pet> first = savedOwner.getPets().stream().filter(p -> p.getName().equals(pet.getName())).findFirst();
		PetResponse petResponseDTO = petMapper.toDto(first.orElse(pet));

		return ResponseEntity.created(linkTo(methodOn(OwnerApiController.class).getOwnerById(ownerId)).toUri())
			.body(petModelAssembler.toModel(petResponseDTO));
	}

	@PutMapping(PET_ID_IN_REQUEST_PATH)
	public ResponseEntity<EntityModel<PetResponse>> updatePet(@PathVariable Integer petId,
			@RequestBody @Valid PetRequest request) {
		Pet existingPet = petService.findById(petId).orElseThrow(() -> new EntityNotFoundException(PET, petId));

		petMapper.updateEntityFromDto(request, existingPet);
		Pet updatedPet = petService.save(existingPet);

		PetResponse dto = petMapper.toDto(updatedPet);
		return ResponseEntity.ok(petModelAssembler.toModel(dto));
	}

	@DeleteMapping(PET_ID_IN_REQUEST_PATH)
	public ResponseEntity<Map<String, String>> deletePet(@PathVariable Integer petId) {
		petService.findById(petId).orElseThrow(() -> new EntityNotFoundException(PET, petId));
		petService.deleteById(petId);
		String message = String.format(PET_WITH_ID_D_IS_DELETED, petId);
		return ResponseEntity.ok(Map.of(MESSAGE, message));
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<PetResponse>>> getAllPetsForOwner(@PathVariable Integer ownerId) {
		Owner owner = ownerService.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));

		List<EntityModel<PetResponse>> petModels = owner.getPets().stream().map(petMapper::toDto).map(pet -> {
			EntityModel<PetResponse> petDTOEntityModel = petModelAssembler.toModel(pet);
			linkTo(methodOn(PetApiController.class).getAllPetsForOwner(ownerId)).withRel(ALL_PETS);
			return petDTOEntityModel;
		}).toList();

		CollectionModel<EntityModel<PetResponse>> collectionModel = CollectionModel.of(petModels);
		collectionModel.add(linkTo(methodOn(PetApiController.class).getAllPetsForOwner(ownerId)).withSelfRel());
		collectionModel.add(linkTo(methodOn(OwnerApiController.class).getOwnerById(ownerId)).withRel(OWNER));
		return ResponseEntity.ok(collectionModel);
	}

}
