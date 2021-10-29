package com.tis.in.BanX.common;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.tis.in.BanX.common.UserConstants;

/**
 * @Clsaa Utility
 *
 */
public class Utility {

	private static Logger logger = LoggerFactory.getLogger(Utility.class);

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss a");

	/**
	 * 
	 * @Method
	 * @Param
	 * @return SQL Date format
	 * 
	 */
	public static java.util.Date getSQLDate() {
		return new java.util.Date();
	}

	/**
	 * 
	 * @Method
	 * @Param
	 * @return SQL Date format
	 * 
	 */
	public static java.sql.Date convertDateFormat(java.util.Date uDate) {
		return new java.sql.Date(uDate.getTime());
	}

	/**
	 * 
	 * @Method
	 * @Param
	 * @return Formatted Date = MMM-dd-yyyy hh:mm:ss a
	 * 
	 */
	public static String dateToString(java.util.Date date) {
		String formattedDate = "";
		try {
			formattedDate = new Utility().simpleDateFormat.format(date);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return formattedDate;
	}

	/**
	 * 
	 * @Method
	 * @Param
	 * @return Formatted Date = MMM-dd-yyyy hh:mm:ss a
	 * 
	 */
	public static String getCurrentDate() {
		String formattedDate = "";
		try {
			formattedDate = new Utility().simpleDateFormat.format(new java.util.Date());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return formattedDate;
	}

	/**
	 * 
	 * @Method
	 * @Param empty
	 * @return ResourceBundleMessageSource
	 * 
	 */
	public static ResourceBundleMessageSource getSuccessMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(UserConstants.MESSAGEDIR.getConstants());
		return messageSource;
	}

	/**
	 * 
	 * @Method
	 * @Param MessageKey
	 * @return Success Messages based on locale
	 * 
	 */
	public static String getLocalizedMessage(String messageKey) {
		return getSuccessMessageSource().getMessage(messageKey, null, new Locale(UserConstants.LOCALE.getConstants()));
	}

	/**
	 * @param responseMessage
	 * @param responseStatus
	 * @return
	 */
	public static ResponseBuilder responseBuilder(String responseMessage, int responseStatus) {

		ResponseBuilder builder = new ResponseBuilder();
		builder.setResponseMessage(responseMessage);
		builder.setResponseCode(responseStatus);
		builder.setTimeStamp(Utility.getCurrentDate());

		return builder;
	}
}
