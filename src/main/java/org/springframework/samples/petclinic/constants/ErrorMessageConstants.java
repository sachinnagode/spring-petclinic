package org.springframework.samples.petclinic.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageConstants {

	public static final String OWNER_NOT_FOUND = "Owner not found";

	public static final String S_WITH_ID_S_NOT_FOUND = "%s with ID [%s] not found";

	public static final String TIMESTAMP = "timestamp";

	public static final String STATUS = "status";

	public static final String ERROR = "error";

	public static final String MESSAGE = "message";

	public static final String RESOURCE_NOT_FOUND = "Resource Not Found";

}
