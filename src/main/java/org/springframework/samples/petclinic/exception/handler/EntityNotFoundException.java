package org.springframework.samples.petclinic.exception.handler;

import org.springframework.samples.petclinic.constants.ErrorMessageConstants;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(String entityName, Object id) {
		super(String.format(ErrorMessageConstants.S_WITH_ID_S_NOT_FOUND, entityName, id));
	}

}
