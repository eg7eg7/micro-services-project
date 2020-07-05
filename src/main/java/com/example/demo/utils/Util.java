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
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(emailAddress);
        if(regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isDateValid(String date, String format) {
    	SimpleDateFormat d = new SimpleDateFormat(format);
    	d.setLenient(false);
    	try {
			Date s = d.parse(date);
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
