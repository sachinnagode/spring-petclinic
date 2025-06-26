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

	public static final String EXCEPTION = "exception";

	public static final String FIELD = "field";

	public static final String VALIDATION_FAILED = "Validation Failed";

	public static final String INPUT_VALIDATION_ERROR = "Input validation error";

	public static final String NULL_REFERENCE_ENCOUNTERED = "Null reference encountered";

	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

	public static final String RESOURCE_NOT_FOUND = "Resource Not Found";

	public static final String OWNER_WITH_ID_D_IS_DELETED = "Owner with ID %d is deleted.";

	public static final String PET_WITH_ID_D_IS_DELETED = "Pet with ID %d is deleted.";

}
