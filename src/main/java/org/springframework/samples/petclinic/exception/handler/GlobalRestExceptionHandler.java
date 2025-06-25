package org.springframework.samples.petclinic.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.controller.hateoas.OwnerApiController;
import org.springframework.samples.petclinic.controller.hateoas.PetApiController;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.samples.petclinic.constants.ErrorMessageConstants.*;

@RestControllerAdvice(assignableTypes = { OwnerApiController.class, PetApiController.class })
public class GlobalRestExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
		return buildResponse(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND, ex.getMessage(), ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
		List<Map<String, String>> fieldErrors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(error -> Map.of("field", error.getField(), "message", error.getDefaultMessage()))
			.toList();

		return buildResponse(HttpStatus.BAD_REQUEST, "Validation Failed", "Input validation error", ex,
				Map.of("errors", fieldErrors));
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Map<String, Object>> handleNullPointerException(NullPointerException ex) {
		log.error("NullPointerException: {}", ex.getMessage(), ex);
		return buildResponse(HttpStatus.BAD_REQUEST, "Null reference encountered", ex.getMessage(), ex);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
		log.error("Unhandled Exception: {}", ex.getMessage(), ex);
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), ex);
	}

	private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message,
			Exception ex) {
		return buildResponse(status, error, message, ex, Collections.emptyMap());
	}

	private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message,
			Exception ex, Map<String, Object> extraFields) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(STATUS, status.value());
		body.put(ERROR, error);
		body.put(MESSAGE, message);
		body.put("exception", ex.getClass().getSimpleName());
		body.putAll(extraFields);
		return new ResponseEntity<>(body, status);
	}

}
