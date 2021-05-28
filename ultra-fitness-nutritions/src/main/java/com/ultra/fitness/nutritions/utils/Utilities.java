package com.ultra.fitness.nutritions.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class Utilities {
	public static boolean isNullOrEmptyCollection(final Collection<?> c) {
	    return c == null || c.isEmpty();
	}

	public static boolean isNullOrEmptyMap(final Map<?,?> c) {
	    return c == null || c.isEmpty();
	}
	
	public static boolean isNullOrEmptyString(String s) {
	    return s == null ||s.isEmpty() || s.trim().length() < 1;
	}
	
	public static boolean validateIfNullOrEmptyString(String str) {
		if (null == str || str.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidString(String str) {
		if (null != str && " " != str && str.trim().length() > 1) {
			return true;
		}
		return false;
	}
	
	public static boolean validateIfNullOrInvalidInteger(Integer number) {
		if (null == number || number < 0) {
			return true;
		}
		return false;
	}
	
	public static boolean validateIfNullOrInvalidDouble(Double number) {
		if (null == number) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidDate(Date date, String formatForValidation) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatForValidation);
		String dateToBeValidated = null; 
		boolean valid = false;
		
		if (null == date) {
			return false;
		}
		
		dateToBeValidated = date.toString();
		if (isNullOrEmptyString(dateToBeValidated)) {
			return false;
		}
		
		try {
			sdf.parse(dateToBeValidated);
			// strict mode - check 30 or 31 days, leap year
			sdf.setLenient(false);
			valid = true;
		} catch (ParseException e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
	}
	
	public static boolean isValidDateForString(String date, String formatForValidation) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatForValidation);
		boolean valid = false;
		
		if (isNullOrEmptyString(date)) {
			return false;
		}
		try {
			sdf.parse(date);
			// strict mode - check 30 or 31 days, leap year
			sdf.setLenient(false);
			valid = true;
		} catch (ParseException e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
	}
}
