package org.springframework.samples.petclinic.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUrlConstants {

	public static final String V_2_API_OWNERS = "/v2/owners";

	public static final String V_2_PETS = V_2_API_OWNERS + "/{ownerId}/pets";

	public static final String OWNER_ID_IN_REQUEST_PATH = "/{ownerId}";

	public static final String PET_ID_IN_REQUEST_PATH = "/{petId}";

	public static final String V_2_OWNERS_OWNER_ID_PETS_PET_ID = "/v2/owners/{ownerId}/pets/{petId}";

}
