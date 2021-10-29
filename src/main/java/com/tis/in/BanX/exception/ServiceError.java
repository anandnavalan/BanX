package com.tis.in.BanX.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/**
 * Bean with fields to hold relevant information about errors raised during
 * service calls
 *
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
class ServiceError {

	/**
	 * Operation call status
	 */
	private HttpStatus status;

	/**
	 * The date-time instance of when the error happened
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	/**
	 * Error code indicating the error that happened
	 */
	private String errorCode;

	/**
	 * User-friendly message about the error
	 */
	private String message;

	/**
	 * List of sub-errors that happened
	 */
	private List<ServiceSubError> subErrors;

	/**
	 * Default constructor with timestamp as current time
	 */
	private ServiceError() {
		timestamp = LocalDateTime.now();
	}

	/**
	 * Constructor with HttpStatus as arg and Message defaulted
	 * @param status
	 */
	ServiceError(HttpStatus status) {
		this();
		this.status = status;
		this.message = "Unexpected error";
	}

	/**
	 * Constructor with HttpStatus and Message as input arg
	 * @param status
	 */
	ServiceError(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	/**
	 * Add suberror to the existing list and creates new list when empty
	 * @param subError
	 */
	private void addSubError(ServiceSubError subError) {
		if (subErrors == null) {
			subErrors = new ArrayList<>();
		}
		subErrors.add(subError);
	}

	
	/**
	 * Adds validation error with all params
	 * 
	 * @param object
	 * @param field
	 * @param rejectedValue
	 * @param message
	 */
	private void addValidationError(String object, String field, Object rejectedValue, String message) {
		addSubError(new ServiceValidationError(object, field, rejectedValue, message));
	}

	/**
	 * Adds validation error with message and object
	 * 
	 * @param object
	 * @param message
	 */
	private void addValidationError(String object, String message) {
		addSubError(new ServiceValidationError(object, message));
	}

	/**
	 * Adds validation error from fielderror
	 * 
	 * @param fieldError
	 */
	private void addValidationError(FieldError fieldError) {
		this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
	}

	/**
	 * Adds validation errors from list of field errors
	 * 
	 * @param fieldErrors
	 */
	void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}

	/**
	 * Adds validation error from Object error
	 * 
	 * @param objectError
	 */
	private void addValidationError(ObjectError objectError) {
		this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}

	/**
	 * Adds validation error from list of object errors
	 * 
	 * @param globalErrors
	 */
	void addValidationError(List<ObjectError> globalErrors) {
		globalErrors.forEach(this::addValidationError);
	}

	/**
	 * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
	 * 
	 * @param cv the ConstraintViolation
	 */
	private void addValidationError(ConstraintViolation<?> cv) {
		this.addValidationError(cv.getRootBeanClass().getSimpleName(), ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
	}

	/**
	 * Adds validation error from constraintViolations
	 * 
	 * @param constraintViolations
	 */
	public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addValidationError);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<ServiceSubError> getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(List<ServiceSubError> subErrors) {
		this.subErrors = subErrors;
	}

	interface ServiceSubError {

	}

	class ServiceValidationError implements ServiceSubError {
		private String object;
		private String field;
		private Object rejectedValue;
		private String message;

		ServiceValidationError(String object, String message) {
			this.object = object;
			this.message = message;
		}

		ServiceValidationError(String object, String field, Object rejectedValue, String message) {
			this.object = object;
			this.field = field;
			this.rejectedValue = rejectedValue;
			this.message = message;
		}

		public String getObject() {
			return object;
		}

		public void setObject(String object) {
			this.object = object;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public Object getRejectedValue() {
			return rejectedValue;
		}

		public void setRejectedValue(Object rejectedValue) {
			this.rejectedValue = rejectedValue;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}

class LowerCaseClassNameResolver extends TypeIdResolverBase {

	@Override
	public String idFromValue(Object value) {
		return value.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public String idFromValueAndType(Object value, Class<?> suggestedType) {
		return idFromValue(value);
	}

	@Override
	public JsonTypeInfo.Id getMechanism() {
		return JsonTypeInfo.Id.CUSTOM;
	}
}