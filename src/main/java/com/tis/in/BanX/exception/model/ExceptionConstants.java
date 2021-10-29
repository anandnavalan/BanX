package com.tis.in.BanX.exception.model;

/**
 * Holds the key for all technical exceptions
 *
 */
public class ExceptionConstants {
	
	
	private ExceptionConstants() {
		// Default constructor
	}
	
	public static final String METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION = "MethodArgumentTypeMismatchException";
	public static final String HTTP_METHOD_NOT_SUPPORTED = "HttpRequestMethodNotSupportedException";
	public static final String MALFUNCTIONED_JSON = "HttpMessageNotReadableException";
	public static final String UNSUPPORTED_MEDIA = "HttpMediaTypeNotSupportedException";
	public static final String VALIDATION_ERROR = "ValidationException";
	public static final String MISSING_REQUEST_PARAM = "MissingServletRequestParameterException";
	public static final String TECHNICAL_EXCEPTION = "TechnicalException";

}
