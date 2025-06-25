package org.springframework.samples.petclinic.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.service.impl.OwnerServiceImpl;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

	@Mock
	private OwnerRepository ownerRepository;

	@InjectMocks
	private OwnerServiceImpl ownerService;

	private Owner owner;

	@BeforeEach
	void setUp() {
		owner = new Owner();
		owner.setId(1);
		owner.setFirstName("John");
		owner.setLastName("Doe");
	}

	@Test
	void findById_shouldReturnOwner() {
		when(ownerRepository.findById(1)).thenReturn(Optional.of(owner));

		Optional<Owner> result = ownerService.findById(1);

		assertThat(result).isPresent().contains(owner);
		verify(ownerRepository).findById(1);
	}

	@Test
	void save_shouldReturnSavedOwner() {
		when(ownerRepository.save(owner)).thenReturn(owner);

		Owner saved = ownerService.save(owner);

		assertThat(saved).isEqualTo(owner);
		verify(ownerRepository).save(owner);
	}

	@Test
	void findByLastNameStartingWith_shouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		List<Owner> owners = List.of(owner);
		Page<Owner> page = new PageImpl<>(owners);

		when(ownerRepository.findByLastNameStartingWith("Do", pageable)).thenReturn(page);

		Page<Owner> result = ownerService.findByLastNameStartingWith("Do", pageable);

		assertThat(result.getContent()).hasSize(1).contains(owner);
		verify(ownerRepository).findByLastNameStartingWith("Do", pageable);
	}

	@Test
	void delete_shouldInvokeRepositoryDelete() {
		doNothing().when(ownerRepository).deleteById(1);

		ownerService.delete(1);

		verify(ownerRepository).deleteById(1);
	}

	@Test
	void findAll_withPageable_shouldReturnPagedOwners() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Owner> page = new PageImpl<>(List.of(owner));

		when(ownerRepository.findAll(pageable)).thenReturn(page);

		Page<Owner> result = ownerService.findAll(pageable);

		assertThat(result.getContent()).contains(owner);
		verify(ownerRepository).findAll(pageable);
	}

	@Test
	void findAll_shouldReturnAllOwners() {
		when(ownerRepository.findAll()).thenReturn(List.of(owner));

		List<Owner> result = ownerService.findAll();

		assertThat(result).containsExactly(owner);
		verify(ownerRepository).findAll();
	}

}
