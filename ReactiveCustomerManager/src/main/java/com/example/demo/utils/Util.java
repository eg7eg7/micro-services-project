package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static boolean isEmailValid(String emailAddress) {
		Pattern regexPattern;
		Matcher regMatcher;
		regexPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
		regMatcher = regexPattern.matcher(emailAddress);
		if (regMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDateValid(String date, String format) {
		SimpleDateFormat d = new SimpleDateFormat(format);
		d.setLenient(false);
		try {
			d.parse(date);
		} catch (ParseException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

	public static Date getDate(String date, String format) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat(format);
		d.setLenient(false);
		try {
			Date s = d.parse(date);
			return s;
		} catch (ParseException e) {
			System.err.println(e);
			throw e;
		}
	}
}
