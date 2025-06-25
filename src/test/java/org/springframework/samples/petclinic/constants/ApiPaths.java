package org.springframework.samples.petclinic.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPaths {

	public static final String BASE_V2_OWNERS = "/v2/owners";

	public static final String BASE_V2_PETS = BASE_V2_OWNERS + "/{ownerId}/pets";

}
