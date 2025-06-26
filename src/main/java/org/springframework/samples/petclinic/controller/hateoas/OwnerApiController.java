package org.springframework.samples.petclinic.controller.hateoas;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.dto.owner.OwnerResponse;
import org.springframework.samples.petclinic.mapper.OwnerMapper;
import org.springframework.samples.petclinic.converter.OwnerModelAssembler;
import org.springframework.samples.petclinic.dto.owner.OwnerRequest;
import org.springframework.samples.petclinic.exception.handler.EntityNotFoundException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.samples.petclinic.constants.CommonConstants.OWNER;
import static org.springframework.samples.petclinic.constants.ErrorMessageConstants.MESSAGE;
import static org.springframework.samples.petclinic.constants.ErrorMessageConstants.OWNER_WITH_ID_D_IS_DELETED;
import static org.springframework.samples.petclinic.constants.RequestUrlConstants.OWNER_ID_IN_REQUEST_PATH;
import static org.springframework.samples.petclinic.constants.RequestUrlConstants.V_2_API_OWNERS;

@RequiredArgsConstructor
@RestController
@RequestMapping(V_2_API_OWNERS)
public class OwnerApiController {

	private final OwnerService ownerService;

	private final OwnerModelAssembler assembler;

	private final OwnerMapper ownerMapper;

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<OwnerResponse>>> getAllOwners() {
		List<Owner> owners = ownerService.findAll();
		List<EntityModel<OwnerResponse>> ownerModels = owners.stream()
			.map(ownerMapper::toDto)
			.map(assembler::toModel)
			.toList();
		CollectionModel<EntityModel<OwnerResponse>> collection = CollectionModel.of(ownerModels,
				linkTo(methodOn(OwnerApiController.class).getAllOwners()).withSelfRel());
		return ResponseEntity.ok(collection);
	}

	@GetMapping(OWNER_ID_IN_REQUEST_PATH)
	public ResponseEntity<EntityModel<OwnerResponse>> getOwnerById(@PathVariable Integer ownerId) {
		Owner owner = ownerService.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));

		OwnerResponse dto = ownerMapper.toDto(owner);
		EntityModel<OwnerResponse> model = assembler.toModel(dto);
		return ResponseEntity.ok(model);
	}

	@PostMapping
	public ResponseEntity<EntityModel<OwnerResponse>> createOwner(@RequestBody @Valid OwnerRequest ownerRequest) {
		Owner owner = ownerMapper.toEntity(ownerRequest); // Map request DTO → Entity
		Owner saved = ownerService.save(owner); // Save entity
		OwnerResponse dto = ownerMapper.toDto(saved); // Map entity → response DTO
		EntityModel<OwnerResponse> model = assembler.toModel(dto);

		return ResponseEntity.created(linkTo(methodOn(OwnerApiController.class).getOwnerById(saved.getId())).toUri())
			.body(model);
	}

	@PutMapping(OWNER_ID_IN_REQUEST_PATH)
	public ResponseEntity<EntityModel<OwnerResponse>> updateOwner(@PathVariable Integer ownerId,
			@RequestBody @Valid OwnerRequest ownerRequest) {
		Owner existing = ownerService.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(OWNER, ownerId));
		ownerMapper.updateEntityFromDto(ownerRequest, existing);
		Owner saved = ownerService.save(existing);
		OwnerResponse dto = ownerMapper.toDto(saved);
		return ResponseEntity.ok(assembler.toModel(dto));
	}

	@DeleteMapping(OWNER_ID_IN_REQUEST_PATH)
	public ResponseEntity<Map<String, String>> deleteOwner(@PathVariable Integer ownerId) {
		ownerService.delete(ownerId);
		String message = String.format(OWNER_WITH_ID_D_IS_DELETED, ownerId);
		return ResponseEntity.ok(Map.of(MESSAGE, message));
	}

}
