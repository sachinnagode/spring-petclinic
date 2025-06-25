package org.springframework.samples.petclinic.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.*;

import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.impl.PetServiceImpl;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

	@Mock
	private PetRepository petRepository;

	@InjectMocks
	private PetServiceImpl petService;

	private Pet pet;

	@BeforeEach
	void setUp() {
		pet = new Pet();
		pet.setId(1);
		pet.setName("Buddy");
	}

	@Test
	void findAllPageable_shouldReturnPagedPets() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Pet> petPage = new PageImpl<>(List.of(pet));

		when(petRepository.findAll(pageable)).thenReturn(petPage);

		Page<Pet> result = petService.findAll(pageable);

		assertThat(result.getContent()).containsExactly(pet);
		verify(petRepository).findAll(pageable);
	}

	@Test
	void findById_shouldReturnPet() {
		when(petRepository.findById(1)).thenReturn(Optional.of(pet));

		Optional<Pet> result = petService.findById(1);

		assertThat(result).isPresent().contains(pet);
		verify(petRepository).findById(1);
	}

	@Test
	void save_shouldReturnSavedPet() {
		when(petRepository.save(pet)).thenReturn(pet);

		Pet saved = petService.save(pet);

		assertThat(saved).isEqualTo(pet);
		verify(petRepository).save(pet);
	}

	@Test
	void findAll_shouldReturnAllPets() {
		when(petRepository.findAll()).thenReturn(List.of(pet));

		List<Pet> result = petService.findAll();

		assertThat(result).containsExactly(pet);
		verify(petRepository).findAll();
	}

	@Test
	void deleteById_shouldInvokeRepository() {
		doNothing().when(petRepository).deleteById(1);

		petService.deleteById(1);

		verify(petRepository).deleteById(1);
	}

}
