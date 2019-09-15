package com.demo.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.demo.helpers.Utility;

public class BaseRestAPIs {
	public static final Locale ThaiLocale = new Locale("th", "TH");
	/**
	 * d MMMM yyyy HH:mm:ss
	 */
	public static final SimpleDateFormat FormatCreateDateTileOfThai = new SimpleDateFormat("d MMMM yyyy HH:mm:ss",
			ThaiLocale);

	/**
	 * yyyyMMddHHmmss
	 */
	public static final SimpleDateFormat FormatReturnDateTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
	/**
	 * yyyyMMdd
	 */
	public static final SimpleDateFormat FormatReturnDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
	/**
	 * HHmmss
	 */
	public static final SimpleDateFormat FormatReturnTime = new SimpleDateFormat("HHmmss", Locale.US);

	/**
	 * #0.00
	 */
	public static final DecimalFormat FormatReturnDecimal = new DecimalFormat("#0.00");

	
	/**
	 * Encoded Password
	 * @param pwd
	 * @return
	 */
	public static String GetEncodedPassword(String pwd) {
		return Utility.StringEncryptBase64(pwd, "UTF-8");
	}
	
	/**
	 * Register process has to generate reference code from register date and last 4 digits of phone number like this “YYYYMMDDXXXX” (ex. 201708154652) 
	 * 
	 * @return reference code
	 */
	public static String GetReferenceCode(Date registerDate, String phoneNumber) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.US);
		return format.format(registerDate) + Utility.GetLastnCharacters(Utility.Trim(phoneNumber), 4);
	}
}
