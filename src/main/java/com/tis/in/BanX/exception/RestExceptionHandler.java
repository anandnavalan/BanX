package com.tis.in.BanX.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tis.in.BanX.common.ServiceConstants;
import com.tis.in.BanX.exception.ServiceError.ServiceSubError;
import com.tis.in.BanX.exception.ServiceError.ServiceValidationError;
import com.tis.in.BanX.exception.model.BaseBusinessException;
import com.tis.in.BanX.exception.model.ExceptionConstants;


/**
 * Generic handler for all exceptions
 *
 */
@ControllerAdvice(basePackages=ServiceConstants.BASE_PACKAGE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger EXP_LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@Value("${exception.message.location:exception/message}")
	private String messageLocation;
	
    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ServiceError object
     */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ServiceError serviceError = new ServiceError(HttpStatus.BAD_REQUEST);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.MISSING_REQUEST_PARAM, serviceError);
		return buildResponseEntity(serviceError);
	}


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ServiceError object
     */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		logException(ex);
        ServiceError serviceError = new ServiceError(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.MALFUNCTIONED_JSON, serviceError);
  
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(serviceError.getMessage());
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		serviceError.setMessage(builder.substring(0, builder.length() - 2));
		
		return buildResponseEntity(serviceError);
	}

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ServiceError object
     */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ServiceError serviceError = new ServiceError(HttpStatus.BAD_REQUEST);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.VALIDATION_ERROR, serviceError);
		serviceError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		serviceError.setSubErrors(populateSubErrorsWithMessage( serviceError));
		serviceError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(serviceError);
	}

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ServiceError object
     */
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex, HttpServletRequest request) {
		ServiceError serviceError = new ServiceError(HttpStatus.BAD_REQUEST);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.VALIDATION_ERROR, serviceError);
		serviceError.addValidationErrors(ex.getConstraintViolations());
		serviceError.setSubErrors(populateSubErrorsWithMessage( serviceError));
		return buildResponseEntity(serviceError);
	}

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ServiceError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServiceError serviceError = new ServiceError(HttpStatus.BAD_REQUEST);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.MALFUNCTIONED_JSON, serviceError);
        return buildResponseEntity(serviceError);
    }

    /**
     * Handle HttpRequestMethodNotSupportedException.
     *
     * @param ex      HttpRequestMethodNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ServiceError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServiceError serviceError = new ServiceError(HttpStatus.BAD_REQUEST);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.HTTP_METHOD_NOT_SUPPORTED, serviceError);
        return buildResponseEntity(serviceError);
    }
    
    /**
     * Handle AccessDeniedException
     * 
     * @param ex Exception
     * @param request request
     * @return ResponseEntity
     */
//    @ExceptionHandler(AccessDeniedException.class)
//    protected ResponseEntity<Object> handleAccessDeniedException(java.lang.Exception ex, HttpServletRequest request) {
//        logException(ex);
//        ServiceError serviceError = new ServiceError(HttpStatus.UNAUTHORIZED);
//        populateServiceErrorWithCodeAndMessage("AccessDenied", serviceError);
//        return buildResponseEntity(serviceError);
//    }
    
    /**
     * Handle javax.persistence.EntityNotFoundException
     */
    @ExceptionHandler(java.lang.Exception.class)
    protected ResponseEntity<Object> handleException(java.lang.Exception ex, HttpServletRequest request) {
    	logException(ex);
    	return buildResponseEntity(getTechincalExceptionServiceError());
    }

	private ServiceError getTechincalExceptionServiceError() {
		ServiceError serviceError = new ServiceError(HttpStatus.INTERNAL_SERVER_ERROR);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.TECHNICAL_EXCEPTION, serviceError);
		return serviceError;
	}

  
    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ServiceError object
     */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		logException(ex);
		ServiceError serviceError = new ServiceError(BAD_REQUEST);
		populateServiceErrorWithCodeAndMessage( ExceptionConstants.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION, serviceError);
		serviceError.setMessage(String.format(serviceError.getMessage(), ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
		return buildResponseEntity(serviceError);
	}


	/**
	 * Builds the response entity using ServiceError
	 * 
	 * @param serviceError
	 * @return the ResponseEntity
	 */
    private ResponseEntity<Object> buildResponseEntity(ServiceError serviceError) {
        return new ResponseEntity<>(serviceError, serviceError.getStatus());
    }
    

	/**
	 * Handles BusinessExceptions. Created to encapsulate errors with more detail.
	 * 
	 * @param request HttpServletRequest
	 * @param ex the BaseBusinessException
	 * @return ResponseEntity
	 */
	@ExceptionHandler(BaseBusinessException.class)
	protected ResponseEntity<Object> handleBusinessException(HttpServletRequest request, BaseBusinessException ex) {
		logException(ex);
		ServiceError serviceError = new ServiceError(HttpStatus.OK);
		populateServiceErrorWithCodeAndMessage( ex.getMessage(), serviceError);
		return buildResponseEntity(serviceError);
	}

	private ResourceBundleMessageSource getMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(messageLocation);
        return messageSource;
    } 

	private void populateServiceErrorWithCodeAndMessage(String errorCode, ServiceError serviceError) {
		
		serviceError.setErrorCode(errorCode);
		serviceError.setMessage(getLocalizedMessage(errorCode));
		
	}
	
	
	private List<ServiceSubError> populateSubErrorsWithMessage(ServiceError serviceError) {
		List<ServiceSubError> subErrors = serviceError.getSubErrors();
		List<ServiceSubError> modifedSubErrors = new ArrayList<>();
		
		if(subErrors != null && !subErrors.isEmpty()) {
			for (ServiceSubError serviceSubError : subErrors) {
				ServiceValidationError validationError = (ServiceValidationError)serviceSubError;
				String localizedMsg = getLocalizedMessage(generateErrorKey(validationError));
				if (localizedMsg != null) {
					validationError.setMessage(localizedMsg);
				}
				modifedSubErrors.add(validationError);
			}
			
		}
		
		return modifedSubErrors;
				
	}


	private String generateErrorKey(ServiceValidationError validationError) {
		
		StringBuilder errorKey = new StringBuilder();
		errorKey.append(validationError.getObject()).append(".");
		errorKey.append(validationError.getField()).append(".");
		errorKey.append(validationError.getMessage());
		
		return errorKey.toString();
	}
	
	private String getLocalizedMessage(String errorCode) {
		return getMessageSource().getMessage(errorCode, null, LocaleContextHolder.getLocale());
	}

	private void logException(Exception ex) {
		EXP_LOGGER.error(ex.getMessage(), ex);
	}
    

}
