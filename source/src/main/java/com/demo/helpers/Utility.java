package com.demo.helpers;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static void main(String[] chai) {
		/*
		 * System.out.print( "\n-------------------------------------------------");
		 * System.out.print("\n- MonthNameShort !!! = "); for (String str :
		 * MonthNameShort(LOCALE_TH)) System.out.print(str+", ");
		 * System.out.print("\n- MonthNameLong !!! = "); for (String str :
		 * MonthNameLong(LOCALE_TH)) System.out.print(str+", ");
		 * System.out.print("\n- DayNameShort !!! = "); for (String str :
		 * DayNameShort(LOCALE_TH)) System.out.print(str+", ");
		 * System.out.print("\n- DAY_NAME_TH_LONG !!! = "); for (String str :
		 * DayNameLong(LOCALE_TH)) System.out.print(str+", "); System.out.print(
		 * "\n-------------------------------------------------");
		 * System.out.print("\n- MonthNameShort !!! = "); for (String str :
		 * MonthNameShort(LOCALE_US)) System.out.print(str+", ");
		 * System.out.print("\n- MonthNameLong !!! = "); for (String str :
		 * MonthNameLong(LOCALE_US)) System.out.print(str+", ");
		 * System.out.print("\n- DayNameShort !!! = "); for (String str :
		 * DayNameShort(LOCALE_US)) System.out.print(str+", ");
		 * System.out.print("\n- DAY_NAME_TH_LONG !!! = "); for (String str :
		 * DayNameLong(LOCALE_US)) System.out.print(str+", "); System.out.print(
		 * "\n-------------------------------------------------");
		 */

		// System.out.println("-
		// "+Utility.class.getClassLoader().getResource(".").getPath());
		// System.out.println("- RootClassPathFolder =
		// "+Utility.class.getClassLoader().getResource(".").getPath().substring(1));

		// System.out.println("- java.library.path =
		// "+System.getProperty("java.library.path"));

		// System.out.println("- RootDirectory = "+RootDirectory);

		// System.out.println("- RootClassFolderPath = "+Utility.RootClassFolderPath());

		/*
		 * System.out.println("- GetValidFileNameNumberToAZ = " +
		 * Utility.GetValidFileNameNumberToAZ("/*-+7ก8 9ป4_5-6123#####aB0")); /* {
		 * List<String> list = new ArrayList<String>(); list.add("A"); // 0
		 * list.add("B"); // 1 list.add("C"); // 2 System.out.println("- IndexInList = "
		 * + IndexInList(list, "a", true)); System.out.println("- IndexInList = " +
		 * IndexInList(list, "B", true)); System.out.println("- IndexInList = " +
		 * IndexInList(list, "c", true)); System.out.println("- IsHaveInList = " +
		 * IsHaveInList(list, "c", true)); System.out.println("- IsHaveInList = " +
		 * IsHaveInList(list, "c", false)); }
		 */

	}

	public static final String RootDirectory = System.getProperty("user.dir");

	public static final Locale LOCALE_TH = new Locale("th", "TH");
	public static final Locale LOCALE_US = Locale.US;

	// public static final String emailFormat =
	// "([\\w\\.\\_\\-]+)@(\\w+\\.)(\\w+)(\\.\\w+)*";
	public static final String emailFormat = "([\\w\\.\\_\\-]+)@([\\w\\-]+\\.)(\\w+)(\\.\\w+)*";
	public static final String nameFormat = "[^0-9\\p{Punct}]+";
	public static final String numberFormat = "[0-9]+";
	public static final String englishFormat = "[a-zA-Z]+";
	public static final String citizenIdFormat = "[0-9]{1,13}";
	public static final String passportFormat = "[0-9a-zA-Z]{1,20}";
	public static final String mobileFormat = "0[1-9][0-9]{8}";
	public static final String phoneFormat = "0[1-9][0-9]{7}";
	public static final String zipcodeFormat = "[0-9]{5}";
	public static final String thaiLangFormat = "[^a-zA-Z]+"; // use inverse of
																// method that
																// verify
																// english
																// language
	public static final String thaiNameLangFormat = "[^0-9a-zA-Z\\p{Blank}\\p{Graph}]+";
	public static final String englishLangFormat = "[\\p{Blank}\\p{Graph}]+";
	public static final String englishNameLangFormat = "[a-zA-Z]+";
	public static final String distnumFormat = "[0-9]{7}";
	public static final String passwordFormat = "([0-9a-zA-Z]+)";

	public static final NumberFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("#.0#################");
	public static final BigDecimal ZERO = new BigDecimal("0");

	public static final String[] MonthNameThai = new String[] { "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน ", "พฤษภาคม",
			"มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม" };
	public static final String[] MonthNameEng = new String[] { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
	public static final String[] DayOfWeekNameThai = new String[] { "อาทิตย์", "จันทร์", "อังคาร", "พุธ", "พฤหัสบดี",
			"ศุกร์", "เสาร์" };
	public static final String[] DayOfWeekNameEng = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thrusday", "Friday", "Saturday" };

	public static String RootClassFolderPath() {
		String classpath = "";
		String tmpPath = Utility.class.getProtectionDomain().getCodeSource().getLocation().toString();
		tmpPath = tmpPath.substring(tmpPath.indexOf("/") + 1);
		String[] tmp = tmpPath.split("/");
		for (String pth : tmp) {
			if (!Utility.VerifyIsEmpty(pth) && !"null".equalsIgnoreCase(pth)
					&& !Utility.Trim(pth).toLowerCase().endsWith(".jar"))
				classpath += Utility.Trim(pth) + "/";
		}
		return classpath;
	}

	public static Date dateStart(Date date) {
		Calendar c = Calendar.getInstance(Locale.US);
		if (date != null) {
			c.setTime(date);
		} else {
			c.set(Calendar.DAY_OF_MONTH, 1);
		}
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date dateEnd(Date date) {
		Calendar c = Calendar.getInstance(Locale.US);
		if (date != null) {
			c.setTime(date);
		} else {
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.add(Calendar.MONTH, +1);
			c.add(Calendar.DAY_OF_MONTH, -1);
		}
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static final List<String> MonthNameShort(Locale locale) {
		String[] names = new DateFormatSymbols(locale).getShortMonths();
		List<String> list = new ArrayList<String>();
		for (String s : names)
			if (!Trim(s).isEmpty())
				list.add(s);
		return list;
	}

	public static final List<String> MonthNameLong(Locale locale) {
		String[] names = new DateFormatSymbols(locale).getMonths();
		List<String> list = new ArrayList<String>();
		for (String s : names)
			if (!Trim(s).isEmpty())
				list.add(s);
		return list;
	}

	public static final List<String> DayNameShort(Locale locale) {
		String[] names = new DateFormatSymbols(locale).getShortWeekdays();
		List<String> list = new ArrayList<String>();
		for (String s : names)
			if (!Trim(s).isEmpty())
				list.add(s);
		return list;
	}

	public static final List<String> DayNameLong(Locale locale) {
		String[] names = new DateFormatSymbols(locale).getWeekdays();
		List<String> list = new ArrayList<String>();
		for (String s : names)
			if (!Trim(s).isEmpty())
				list.add(s);
		return list;
	}

	public static BigDecimal ConvertToBigDecimal(double a, double b) {
		String s = DEFAULT_DECIMAL_FORMAT.format(a);
		BigDecimal bd = new BigDecimal(s);
		return ConvertToBigDecimal(bd, b);
	}

	public static BigDecimal ConvertToBigDecimal(BigDecimal a, double b) {
		String s = DEFAULT_DECIMAL_FORMAT.format(b);
		BigDecimal bd = new BigDecimal(s);
		return ConvertToBigDecimal(a, bd);
	}

	public static BigDecimal ConvertToBigDecimal(BigDecimal a, BigDecimal b) {
		if (a == null)
			return (b == null) ? ZERO : b;
		return a.add(b);
	}

	public static BigDecimal ObjectToBigDecimal(Object obj) {
		return ObjectToBigDecimal(obj, BigDecimal.ZERO);
	}

	public static BigDecimal ObjectToBigDecimal(Object obj, BigDecimal defaultValue) {
		BigDecimal value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			if (obj instanceof BigDecimal) {
				value = (BigDecimal) obj;
			} else {
				value = ConvertToBigDecimal(ObjectToDouble(obj), ObjectToDouble(obj));
			}
		} catch (Exception e) {
		}
		return value;
	}

	public static Date ObjectToDate(Object obj) {
		return ObjectToDate(obj, null);
	}

	public static Date ObjectToDate(Object obj, Date defaultValue) {
		Date value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = (Date) obj;
		} catch (Exception e) {
		}
		return value;
	}

	public static Date ObjectResultSetToDate(ResultSet rs, String colName) {
		return ObjectResultSetToDate(rs, colName, null);
	}

	public static Date ObjectResultSetToDate(ResultSet rs, String colName, Date defaultValue) {
		Date value = defaultValue;
		try {
			if (rs.getObject(colName) != null) {
				value = rs.getTimestamp(colName);
			}
		} catch (Exception e) {
		}
		return value;
	}
	
	public static String ObjectToString(Object obj) {
		return ObjectToString(obj, "");
	}

	public static String ObjectToString(Object obj, String defaultValue) {
		String value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			/*if (obj instanceof net.sourceforge.jtds.jdbc.ClobImpl) {
				net.sourceforge.jtds.jdbc.ClobImpl nObj = (net.sourceforge.jtds.jdbc.ClobImpl) obj;
				value = (nObj.getSubString(1, (int) nObj.length())).trim();
			} else*/
			{
				value = obj.toString().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static int ObjectToInt(Object obj) {
		return ObjectToInt(obj, 0);
	}

	public static int ObjectToInt(Object obj, int defaultValue) {
		int value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = Integer.parseInt(obj.toString().trim());
		} catch (Exception e) {
		}
		return value;
	}

	public static Integer ObjectToInteger(Object obj) {
		return ObjectToInteger(obj, null);
	}

	public static Integer ObjectToInteger(Object obj, Integer defaultValue) {
		Integer value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = Integer.valueOf(obj.toString().trim());
		} catch (Exception e) {
		}
		return value;
	}

	public static long ObjectToLong(Object obj) {
		return ObjectToLong(obj, 0);
	}

	public static long ObjectToLong(Object obj, long defaultValue) {
		long value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = Long.parseLong(obj.toString().trim());
		} catch (Exception e) {
		}
		return value;
	}

	public static Long ObjectToObjectLong(Object obj) {
		return ObjectToObjectLong(obj, null);
	}

	public static Long ObjectToObjectLong(Object obj, Long defaultValue) {
		Long value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = Long.valueOf(obj.toString().trim());
		} catch (Exception e) {
		}
		return value;
	}

	public static double ObjectToDouble(Object obj) {
		return ObjectToDouble(obj, 0);
	}

	public static double ObjectToDouble(Object obj, double defaultValue) {
		double value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = Double.valueOf(obj.toString().trim());
		} catch (Exception e) {
		}
		return value;
	}

	public static float ObjectToFloat(Object obj, float defaultValue) {
		float value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = Float.valueOf(obj.toString().trim());
		} catch (Exception e) {
		}
		return value;
	}

	public static Float ObjectToObjectFloat(Object obj, Float defaultValue) {
		Float value = defaultValue;
		try {
			if (obj == null)
				return defaultValue;
			value = Float.valueOf(obj.toString().trim());
		} catch (Exception e) {
		}
		return value;
	}

	public static boolean ObjectToBoolean(Object obj) {
		return ObjectToBoolean(obj, false);
	}

	public static boolean ObjectToBoolean(Object obj, Boolean defaultValue) {
		if (obj == null)
			return defaultValue;
		try {
			String sv = ObjectToString(obj);
			return sv.equalsIgnoreCase("true") || sv.equalsIgnoreCase("yes") || sv.equalsIgnoreCase("on")
					|| sv.equalsIgnoreCase("1");
		} catch (Exception ex) {
			return defaultValue;
		}
	}
	
	public static boolean IsNoValue(String str)
    {
        return Trim(str).equals("");
    }

	/**
	 * Trim() String
	 * 
	 * @param str
	 * @return
	 */
	public static String Trim(String str) {
		return Trim(str, "");
	}

	/**
	 * Trim() String
	 * 
	 * @param str
	 * @param vDefault
	 * @return
	 */
	public static String Trim(String str, String vDefault) {
		String value = "";
		try {
			if (str == null)
				return value;
			if (str.trim().equals("")) {
				value = vDefault;
			} else {
				value = str.trim();
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * Trim + Remove :: xA0
	 * 
	 * @param str
	 * @return
	 */
	public static String TrimRemoveCharUnkow(String str) {
		if (!Utility.VerifyIsNull(str)) {
			return str.trim().replace(" ", "");
		} else {
			return "";
		}
	}

	/**
	 * addBack
	 * 
	 * @param str
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String AddBack(String str, int len, String ch) {
		int count = len - str.length();
		str = Trim(str);
		for (int i = 0; i < count; i++)
			str = str + ch;
		return str;
	}

	/**
	 * addFront
	 * 
	 * @param n
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String AddFront(int n, int len, String ch) {
		String str = "" + n;
		int count = len - str.length();
		for (int i = 0; i < count; i++)
			str = ch + str;
		return str;
	}

	/**
	 * addFront
	 * 
	 * @param str
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String AddFront(String str, int len, String ch) {
		int count = len - str.length();
		str = Trim(str);
		for (int i = 0; i < count; i++)
			str = ch + str;
		return str;
	}

	/**
	 * GenKeyAddDateTime : yyyyMMddHHmmss+GenID(?)
	 * 
	 * @param isDateThai : True is Thai
	 * @param maxsize    : GenID(?)
	 * @return yyyyMMddHHmmss+GenID(?)
	 */
	public static String GenKeyAddDateTime(boolean isDateThai, int maxsize) {
		return StrDateTimeTH("yyyyMMddHHmmss") + GenID(maxsize);
	}

	public static String RandomString(int maxsize) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(maxsize);
		for (int i = 0; i < maxsize; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	/**
	 * genID
	 * 
	 * @param len
	 * @return
	 */
	public static String GenID(int len) {
		String result = "";
		while (result.length() < len)
			result += (int) (Math.random() * 9);
		if (result.length() > len)
			result = result.substring(0, len);
		return result;
	}

	/**
	 * replace
	 * 
	 * @param str
	 * @param pattern
	 * @param replace
	 * @return
	 */
	public static String Replace(String str, String pattern, String replace) {
		int slen = str.length();
		int plen = pattern.length();
		int s = 0, e = 0;
		StringBuffer result = new StringBuffer(slen * 2);
		char[] chars = new char[slen];
		while ((e = str.indexOf(pattern, s)) >= 0) {
			str.getChars(s, e, chars, 0);
			result.append(chars, 0, e - s).append(replace);
			s = e + plen;
		}
		str.getChars(s, slen, chars, 0);
		result.append(chars, 0, slen - s);
		return result.toString();
	}

	/**
	 * sortArray
	 * 
	 * @param array
	 */
	public static void SortArray(String[] array) {
		Sort(array, 0, array.length - 1);
	}

	/**
	 * String Of int Only
	 * 
	 * @param array
	 * @param min
	 * @param max
	 */
	private static void Sort(String[] array, int min, int max) {
		if (min == max)
			return;
		// Find the smallest.
		int index = Select(array, min, max);
		// Swap the smallest with the first.
		int temp = INumber(array[min]);
		array[min] = array[index];
		array[index] = String.valueOf(temp);
		// Sort the rest.
		Sort(array, min + 1, max);
	}

	/**
	 * select
	 * 
	 * @param array
	 * @param min
	 * @param max
	 * @return
	 */
	private static int Select(String[] array, int min, int max) {
		int index = min;
		for (int i = min + 1; i <= max; ++i)
			if (INumber(array[i]) < INumber(array[index]))
				index = i;
		return index;
	}

	/**
	 * sortArray
	 * 
	 * @param array
	 */
	public static void SortArray(int array[]) {
		Sort(array, 0, array.length - 1);
	}

	/**
	 * Reference (sort) method
	 * 
	 * @param array
	 * @param min
	 * @param max
	 */
	private static void Sort(int array[], int min, int max) {
		if (min == max)
			return;
		// Find the smallest.
		int index = Select(array, min, max);
		// Swap the smallest with the first.
		int temp = array[min];
		array[min] = array[index];
		array[index] = temp;
		// Sort the rest.
		Sort(array, min + 1, max);
	}

	/**
	 * Reference (select) method
	 * 
	 * @param array
	 * @param min
	 * @param max
	 * @return
	 */
	private static int Select(int array[], int min, int max) {
		int index = min;
		for (int i = min + 1; i <= max; ++i)
			if (array[i] < array[index])
				index = i;
		return index;
	}

	/**
	 * unionArray
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int[] UnionArray(int[] p1, int[] p2) {
		List<String> vc = new ArrayList<String>();
		int i = 0, j = 0, tmp = 0;
		char chk = 'n';
		vc.add("0");
		for (i = 0; i < p1.length; i++) {
			chk = 'n';
			for (j = 1; j < vc.size(); j++) {
				tmp = Integer.parseInt((String) vc.get(j));
				if (p1[i] == tmp) {
					chk = 'y';
					break;
				}
			}
			if (chk == 'n')
				vc.add("" + p1[i]);
		}

		for (i = 0; i < p2.length; i++) {
			chk = 'n';
			for (j = 1; j < vc.size(); j++) {
				tmp = Integer.parseInt((String) vc.get(j));
				if (p2[i] == tmp) {
					chk = 'y';
					break;
				}
			}
			if (chk == 'n')
				vc.add("" + p2[i]);
		}

		int[] result = new int[vc.size() - 1];
		for (i = 1; i < vc.size(); i++) {
			result[i - 1] = Integer.parseInt(vc.get(i));
		}

		vc.clear();
		return result;
	}

	/**
	 * unionArray
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static String[] UnionArray(String[] p1, String[] p2) {
		List<String> vc = new ArrayList<String>();
		int i = 0, j = 0;
		char chk = 'n';
		String tmp = "";
		vc.add(" ");

		for (i = 0; i < p1.length; i++) {
			chk = 'n';
			for (j = 1; j < vc.size(); j++) {
				if (p1[i] != null) {
					tmp = (String) vc.get(j);
					if (p1[i].equals(tmp)) {
						chk = 'y';
						break;
					}
				} else {
					chk = 'y';
				}
			}
			if (chk == 'n')
				vc.add(p1[i]);
		}

		for (i = 0; i < p2.length; i++) {
			chk = 'n';
			for (j = 1; j < vc.size(); j++) {
				if (p2[i] != null) {
					tmp = (String) vc.get(j);
					if (p2[i].equals(tmp)) {
						chk = 'y';
						break;
					}
				} else {
					chk = 'y';
				}
			}
			if (chk == 'n')
				vc.add(p2[i]);
		}

		String[] result = new String[vc.size() - 1];
		for (i = 1; i < vc.size(); i++) {
			result[i - 1] = (String) vc.get(i);
		}

		vc.clear();
		return result;
	}

	/**
	 * setNumeric
	 * 
	 * @param value
	 * @param dec
	 * @return
	 */
	public static String SetNumeric(String value, int dec) {
		int pos = value.indexOf(".");
		String result = "", tmp = "";
		String value1 = "", value2 = "";
		try {
			switch (pos) {
			case -1:
				value1 = value;
				break;
			case 0:
				value1 = "0";
				value2 = value.substring(pos + 1);
				break;
			default:
				value1 = value.substring(0, pos).trim();
				value2 = value.substring(pos + 1);
				break;
			}

			int chk = 0;
			for (int i = value1.length() - 1; i >= 0; i--) {
				if ((chk == 2) && (i != 0)) {
					tmp = "," + value1.charAt(i) + tmp;
					chk = 0;
				} else {
					tmp = value1.charAt(i) + tmp;
					chk++;
				}
			}
			value1 = tmp;

			if (dec == 0) {
				result = value1;
			} else {
				value2 = AddBack(value2, dec, "0");
				value2 = value2.substring(0, dec);
				result = value1 + "." + value2;
			}
		} catch (Exception e) {
			result = "Invalid input !";
		}

		return result;
	}

	/**
	 * split
	 * 
	 * @param source
	 * @param chr
	 * @return
	 */
	public static List<String> Split(String source, String chr) {
		List<String> tmp = new ArrayList<String>();
		int index;
		while (source.length() > 0) {
			index = source.indexOf(chr);
			switch (index) {
			case -1:
				tmp.add(source);
				source = "";
				break;
			case 0:
				tmp.add("");
				source = source.substring(index + 1);
				break;
			default:
				tmp.add(source.substring(0, index));
				source = source.substring(index + 1);
				break;
			}
		}
		return tmp;
	}

	/**
	 * Number
	 * 
	 * @param format
	 * @param number
	 * @return
	 */
	public static String Number(String format, double number) {
		return (new DecimalFormat(format).format(number));
	}

	/**
	 * Number
	 * 
	 * @param number
	 * @return
	 */
	public static String Number(double number) {
		return (new DecimalFormat("#,##0.00").format(number));
	}

	/**
	 * Number
	 * 
	 * @param format
	 * @param number
	 * @return
	 */
	public static String Number(String format, long number) {
		return (new DecimalFormat(format).format(number));
	}

	/**
	 * Number
	 * 
	 * @param number
	 * @return
	 */
	public static String Number(long number) {
		return (new DecimalFormat("#,##0.00").format(number));
	}

	/**
	 * Number
	 * 
	 * @param format
	 * @param number
	 * @return
	 */
	public static String Number(String format, int number) {
		return (new DecimalFormat(format).format(number));
	}

	/**
	 * Number
	 * 
	 * @param number
	 * @return
	 */
	public static String Number(int number) {
		return (new DecimalFormat("#,##0").format(number));
	}

	/**
	 * 
	 * @param format
	 * @param number
	 * @return
	 */
	public static String Number(String format, float number) {
		return (new DecimalFormat(format).format(number));
	}

	/**
	 * Number
	 * 
	 * @param number
	 * @return
	 */
	public static String Number(float number) {
		return (new DecimalFormat("#,##0.00").format(number));
	}

	/**
	 * Number
	 * 
	 * @param format
	 * @param number
	 * @return
	 */
	public static String Number(String format, BigDecimal number) {
		return (new DecimalFormat(format).format(number));
	}

	/**
	 * Number
	 * 
	 * @param number
	 * @return
	 */
	public static String Number(BigDecimal number) {
		return (new DecimalFormat("#,##0.00").format(number));
	}

	/**
	 * Number
	 * 
	 * @param num
	 * @return
	 */
	public static int INumber(String num) {
		return (int) DNumber(num);
	}

	/**
	 * Number
	 * 
	 * @param num
	 * @return
	 */
	public static double DNumber(String num) {
		try {
			return (Double.parseDouble(num.trim().replaceAll(",", "")));
		} catch (Exception e) {
			return (0.00);
		}
	}

	/**
	 * Number
	 * 
	 * @param num
	 * @return
	 */
	public static long LNumber(String num) {
		try {
			return (Long.parseLong(num.trim().replaceAll(",", "")));
		} catch (Exception e) {
			return (0);
		}
	}

	public static float FNumber(String num) {
		try {
			return (Float.parseFloat(num.trim().replaceAll(",", "")));
		} catch (Exception e) {
			return (0);
		}
	}

	/**
	 * Number
	 * 
	 * @param num
	 * @return
	 */
	public static Long LongNumber(String num) {
		try {
			return (new Long(num.trim().replaceAll(",", "")));
		} catch (Exception e) {
			return (null);
		}
	}

	/**
	 * Convert to Boolean
	 * 
	 * @param strBoolean : true | false
	 * @return boolean
	 */
	public static boolean IsBoolean(String strBoolean) {
		try {
			return Boolean.parseBoolean(strBoolean);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Convert to Boolean
	 * 
	 * @param num : number > 0 is true | otherwise is false
	 * @return boolean
	 */
	public static boolean IsBoolean(int number) {
		return number > 0;
	}

	/**
	 * getDate
	 * 
	 * @return Date Now
	 */
	public static Date GetDate() {
		return (new Date());
	}

	/**
	 * getDate
	 * 
	 * @param date   : Date
	 * @param hour   : 0-23
	 * @param minute : 0-59
	 * @param sec    : 0-59
	 * @param msec   : 0-999
	 * @return Date
	 */
	public static Date GetDate(Date date, int hour, int minute, int sec, int msec) {
		Calendar cal = Calendar.getInstance(LOCALE_US);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, msec);
		return cal.getTime();
	}

	/**
	 * getDate
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @param sec
	 * @return
	 */
	public static Date GetDate(Date date, int hour, int minute, int sec) {
		return GetDate(date, hour, minute, 0);
	}

	/**
	 * getDate
	 * 
	 * @param hour
	 * @param minute
	 * @param sec
	 * @param msec
	 * @return
	 */
	public static Date GetDate(int hour, int minute, int sec, int msec) {
		return GetDate(new Date(), hour, minute, sec, msec);
	}

	/**
	 * getDate
	 * 
	 * @param hour
	 * @param minute
	 * @param sec
	 * @return
	 */
	public static Date GetDate(int hour, int minute, int sec) {
		return GetDate(new Date(), hour, minute, sec, 0);
	}

	/**
	 * verifyRange : String of length (min to max)
	 * 
	 * @param str
	 * @param min
	 * @param max
	 * @return boolean
	 */
	public static boolean VerifyRange(String str, int min, int max) {
		if (str.trim().length() >= min && str.trim().length() <= max)
			return true;
		return false;
	}

	/**
	 * verifyRange : Number of length (min to max)
	 * 
	 * @param numCheck
	 * @param min
	 * @param max
	 * @return boolean
	 */
	public static boolean VerifyRange(int numCheck, int min, int max) {
		if (numCheck >= min && numCheck <= max)
			return true;
		return false;
	}

	/**
	 * verifyRange : Number of length (min to max)
	 * 
	 * @param numCheck
	 * @param min
	 * @param max
	 * @return boolean
	 */
	public static boolean VerifyRange(double numCheck, double min, double max) {
		if (numCheck >= min && numCheck <= max)
			return true;
		return false;
	}

	/**
	 * verifyRange : Number of length (min to max)
	 * 
	 * @param numCheck
	 * @param min
	 * @param max
	 * @return boolean
	 */
	public static boolean VerifyRange(float numCheck, float min, float max) {
		if (numCheck >= min && numCheck <= max)
			return true;
		return false;
	}

	/**
	 * verifyRange : Number of length (min to max)
	 * 
	 * @param numCheck
	 * @param min
	 * @param max
	 * @return boolean
	 */
	public static boolean VerifyRange(long numCheck, long min, long max) {
		if (numCheck >= min && numCheck <= max)
			return true;
		return false;
	}

	/**
	 * verifyIsEmpty : String is Empty
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyIsEmpty(String str) {
		return Trim(str).equals("");
	}

	/**
	 * verifyIsNull : String is Null
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyIsNull(String str) {
		if (str == null)
			return true;
		return false;
	}

	/**
	 * verifyEmailFormat : String is Email
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean VerifyEmailFormat(String email) {
		if (VerifyIsNull(email) || VerifyIsEmpty(email))
			return false;
		return java.util.regex.Pattern.matches(emailFormat, email);
	}

	/**
	 * verifyNameFormat : String is NAME
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean VerifyNameFormat(String name) {
		if (VerifyIsNull(name) || VerifyIsEmpty(name))
			return false;
		return java.util.regex.Pattern.matches(nameFormat, name);
	}

	/**
	 * verifyNumberFormat : String is NUMBER
	 * 
	 * @param num
	 * @return boolean
	 */
	public static boolean VerifyNumberFormat(String num) {
		if (VerifyIsNull(num) || VerifyIsEmpty(num))
			return false;
		return java.util.regex.Pattern.matches(numberFormat, num);
	}

	/**
	 * verifyCitizenIdFormat : String is CitizenId
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyCitizenIdFormat(String str) {
		return java.util.regex.Pattern.matches(citizenIdFormat, str);
	}

	/**
	 * verifyPassportFormat : String is Passport
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyPassportFormat(String str) {
		return java.util.regex.Pattern.matches(passportFormat, str);
	}

	/**
	 * verifyPhoneFormat : String is PHONE
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyPhoneFormat(String str) {
		return java.util.regex.Pattern.matches(phoneFormat, str);
	}

	/**
	 * verifyMobileFormat : String is Mobile
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyMobileFormat(String str) {
		return java.util.regex.Pattern.matches(mobileFormat, str);
	}

	/**
	 * verifyZipcodeFormat : String is Zipcode
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyZipcodeFormat(String str) {
		return java.util.regex.Pattern.matches(zipcodeFormat, str);
	}

	/**
	 * verifyThaiLanguageFormat : String is Thai Language
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyThaiLanguageFormat(String str) {
		return java.util.regex.Pattern.matches(thaiLangFormat, str);
	}

	/**
	 * verifyEnglishLanguageFormat : String is English Language
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean VerifyEnglishLanguageFormat(String str) {
		return java.util.regex.Pattern.matches(englishLangFormat, str);
	}

	/**
	 * verifyEndDateOfMonth : US : EndDate Of Month
	 * 
	 * @param date
	 * @param month : index base on 1 (not 0), January = 1
	 * @param year  : in US
	 * @return boolean
	 */
	public static boolean VerifyEndDateOfMonth(int date, int month, int year) {
		Calendar calendar = new GregorianCalendar(LOCALE_US);
		calendar.set(year, month - 1, 1);
		return date <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * verifyDateOver : : US
	 * 
	 * @param date
	 * @param month
	 * @param year
	 * @param number : Check date
	 * @return boolean
	 */
	public static boolean VerifyDateOver(int date, int month, int year, int number) {
		Calendar current = new GregorianCalendar(LOCALE_US);
		Calendar specDate = new GregorianCalendar(LOCALE_US);
		specDate.set(year, month - 1, date);

		int cday = current.get(Calendar.DAY_OF_YEAR);
		int sday = specDate.get(Calendar.DAY_OF_YEAR);
		return (sday - cday) <= number;
	}

	/**
	 * verifyDateBeforeCurrentDate : : US
	 * 
	 * @param date
	 * @param month
	 * @param year
	 * @return boolean
	 */
	public static boolean VerifyDateBeforeCurrentDate(int date, int month, int year) {
		Calendar current = new GregorianCalendar(LOCALE_US);
		Calendar specDate = new GregorianCalendar(LOCALE_US);
		specDate.set(year, month - 1, date);

		long ctime = current.getTimeInMillis();
		long stime = specDate.getTimeInMillis();
		return ctime > stime;
	}

	/**
	 * verifyPasswordFormat :
	 * 
	 * @param password
	 * @return boolean
	 */
	public static boolean VerifyPasswordFormat(String password) {
		if (java.util.regex.Pattern.matches(englishFormat, password))
			return false;
		if (java.util.regex.Pattern.matches(numberFormat, password))
			return false;
		return java.util.regex.Pattern.matches(passwordFormat, password);
	}

	/**
	 * verifyCurrentDateOfRangDate : US
	 * 
	 * @param sDate : Start
	 * @param eDate : End
	 * @return boolean
	 */
	public static boolean VerifyCurrentDateOfRangDate(Date sDate, Date eDate) {
		Calendar current = new GregorianCalendar(LOCALE_US);
		Date currDate = current.getTime();
		return ((currDate.compareTo(sDate) > 0) && (eDate.compareTo(currDate) > 0));
	}

	/**
	 * Set Html Tag
	 * 
	 * @param str
	 * @return String
	 */
	public static String ToHtml(String str) {
		StringBuffer sf = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c == '<') {
				sf.append("&lt;");
			} else if (c == '>') {
				sf.append("&gt;");
			} else if (c == '"') {
				sf.append("&quot;");
			} else if (c == '&') {
				sf.append("&amp;");
			} else {
				sf.append(c);
			}
		}
		return sf.toString();
	}

	public static String HtmlToStr(String str) {
		str = str.replace("&lt;", "<");
		str = str.replace("&gt;", ">");
		str = str.replace("&quot;", "\"");
		str = str.replace("&amp;", "&");
		return str;
	}

	/**
	 * Convert MS874 To Unicode
	 * 
	 * @param value
	 * @return String
	 */
	public static String MS874ToUnicode(String value) {
		StringBuffer strTemp = new StringBuffer(value);
		for (int i = 0; i < value.length(); i++) {
			int code = strTemp.charAt(i);
			if (161 <= code && code <= 251)
				strTemp.setCharAt(i, (char) (code + 3424));
		}
		return strTemp.toString();
	}

	/**
	 * Convert Unicode To MS874
	 * 
	 * @param value
	 * @return String
	 */
	public static String UnicodeToMS874(String value) {
		StringBuffer strTemp = new StringBuffer(value);
		for (int i = 0; i < value.length(); i++) {
			int code = strTemp.charAt(i);
			if (3585 <= code && code <= 3675)
				strTemp.setCharAt(i, (char) (code - 3424));
		}
		return strTemp.toString();
	}

	/**
	 * Convert Unicode To ASCII
	 * 
	 * @param unicode
	 * @return String
	 */
	public static String Unicode2ASCII(String unicode) {
		StringBuffer ascii = new StringBuffer(unicode);
		int code;
		for (int i = 0; i < unicode.length(); i++) {
			code = (int) unicode.charAt(i);
			if ((0xE01 <= code) && (code <= 0xE5B))
				ascii.setCharAt(i, (char) (code - 0xD60));
		}
		return ascii.toString();
	}

	/**
	 * Convert ASCII To Unicode
	 * 
	 * @param ascii
	 * @return String
	 */
	public static String ASCII2Unicode(String ascii) {
		StringBuffer unicode = new StringBuffer(ascii);
		int code;
		for (int i = 0; i < ascii.length(); i++) {
			code = (int) ascii.charAt(i);
			if ((0xA1 <= code) && (code <= 0xFB))
				unicode.setCharAt(i, (char) (code + 0xD60));
		}
		return unicode.toString();
	}

	/**
	 * Date to String : US
	 * 
	 * @param format
	 * @param date
	 * @return String
	 */
	public static String StrDateTime(String format, Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format, LOCALE_US);
		return simpledateformat.format(date);
	}

	/**
	 * Date to String : TH
	 * 
	 * @param format
	 * @param date
	 * @return String
	 */
	public static String StrDateTimeTH(String format, Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format, LOCALE_TH);
		return simpledateformat.format(date);
	}

	/**
	 * Date to String : Locale
	 * 
	 * @param format
	 * @param date
	 * @param local
	 * @return String
	 */
	public static String StrDateTime(String format, Date date, Locale local) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format, local);
		return simpledateformat.format(date);
	}

	/**
	 * Date to String : Locale
	 * 
	 * @param format
	 * @param local
	 * @return String
	 */
	public static String StrDateTime(String format, Locale local) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format, local);
		return simpledateformat.format(new Date());
	}

	/**
	 * Date to String : TH
	 * 
	 * @param format
	 * @return String
	 */
	public static String StrDateTimeTH(String format) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format, LOCALE_TH);
		return simpledateformat.format(new Date());
	}

	/**
	 * Date to String : US
	 * 
	 * @param format
	 * @return String
	 */
	public static String StrDateTimeEng(String format) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format, LOCALE_US);
		return simpledateformat.format(new Date());
	}

	/**
	 * Parse String To DateTime
	 * 
	 * @param strDatetime
	 * @param format
	 * @param locale
	 * @return Date
	 * @throws ParseException
	 */
	public static Date ParseToDateTime(String strDatetime, String format, Locale locale) throws ParseException {
		return new SimpleDateFormat(format, locale).parse(strDatetime);
	}

	/**
	 * Parse String To DateTime
	 * 
	 * @param strDatetime
	 * @param format
	 * @return Date
	 * @throws ParseException
	 */
	public static Date ParseToDateTime(String strDatetime, String format) throws ParseException {
		return new SimpleDateFormat(format, LOCALE_US).parse(strDatetime);
	}

	/**
	 * getNumFormStringValue :
	 * 
	 * @param str
	 * @return String of NUMBER
	 */
	public static String GetNumFormStringValue(String str) {
		String strNum = "";
		try {
			for (int i = 0; i < str.length(); i++) {
				try {
					strNum += Integer.parseInt(str.charAt(i) + "");
				} catch (Exception ex) {
				}
			}
		} catch (Exception e) {
			strNum = "";
		}
		return (strNum);
	}

	/**
	 * isNightTime : US
	 * 
	 * @return boolean
	 */
	public static boolean IsNightTime() {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH", LOCALE_US);
		int hh = Integer.parseInt(simpledateformat.format(new Date()));
		return !(hh >= 6 && hh <= 18);
	}

	/**
	 * isDayTime : US
	 * 
	 * @return boolean
	 */
	public static boolean IsDayTime() {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH", LOCALE_US);
		int hh = Integer.parseInt(simpledateformat.format(new Date()));
		return (hh >= 6 && hh <= 18);
	}

	/**
	 * Random Int
	 * 
	 * @param min
	 * @param max
	 * @return int
	 */
	public static int RandomInt(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	/**
	 * RandomStringLowerCase
	 * 
	 * @param min
	 * @param max
	 * @return String
	 */
	public static String RandomStringLowerCase(int min, int max) {
		int num = RandomInt(min, max);
		byte b[] = new byte[num];
		for (int i = 0; i < num; i++)
			b[i] = (byte) RandomInt('a', 'z');
		return new String(b);
	}

	/**
	 * RandomStringUpperCase
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static String RandomStringUpperCase(int min, int max) {
		int num = RandomInt(min, max);
		byte b[] = new byte[num];
		for (int i = 0; i < num; i++)
			b[i] = (byte) RandomInt('A', 'Z');
		return new String(b);
	}

	public static Date GetThaiDate(Date dateUs) {
		if (dateUs != null) {
			Calendar calendar = Calendar.getInstance(Locale.US);
			calendar.setTime(dateUs);
			calendar.add(Calendar.YEAR, 543);
			return calendar.getTime();
		} else {
			return dateUs;
		}
	}

	public static Date GetEngDate(Date dateThai) {
		if (dateThai != null) {
			Calendar calendar = Calendar.getInstance(Locale.US);
			calendar.setTime(dateThai);
			calendar.add(Calendar.YEAR, -543);
			return calendar.getTime();
		} else {
			return dateThai;
		}
	}

	/**
	 * LoadMonthTH
	 * 
	 * @param startIndex
	 * @return
	 */
	public static List<SelectOption> LoadMonthTH(SelectOption defaultOption, int startIndex) {
		List<SelectOption> list = new ArrayList<SelectOption>();
		if (defaultOption != null)
			list.add(defaultOption);
		for (String s : MonthNameThai) {
			list.add(new SelectOption(startIndex + "", s));
			startIndex++;
		}
		return list;
	}

	/**
	 * LoadMonthEng
	 * 
	 * @param startIndex
	 * @return
	 */
	public static List<SelectOption> LoadMonthEng(SelectOption defaultOption, int startIndex) {
		List<SelectOption> list = new ArrayList<SelectOption>();
		if (defaultOption != null)
			list.add(defaultOption);
		for (String s : MonthNameEng) {
			list.add(new SelectOption(startIndex + "", s));
			startIndex++;
		}
		return list;
	}

	public static List<SelectOption> LoadDayOfWeek(SelectOption defaultOption, boolean isThai) {
		List<SelectOption> list = new ArrayList<SelectOption>();
		if (defaultOption != null)
			list.add(defaultOption);
		for (int i = 0; i < DayOfWeekNameThai.length; i++) {
			list.add(new SelectOption(DayOfWeekNameEng[i].toUpperCase(),
					isThai ? DayOfWeekNameThai[i] : DayOfWeekNameEng[i]));
		}
		return list;
	}

	public static List<SelectOption> LoadDayOfWeekTH(SelectOption defaultOption, int startIndex) {
		List<SelectOption> list = new ArrayList<SelectOption>();
		if (defaultOption != null)
			list.add(defaultOption);
		for (String s : DayOfWeekNameThai) {
			list.add(new SelectOption(startIndex + "", s));
			startIndex++;
		}
		return list;
	}

	public static List<SelectOption> LoadDayOfWeekEng(SelectOption defaultOption, int startIndex) {
		List<SelectOption> list = new ArrayList<SelectOption>();
		if (defaultOption != null)
			list.add(defaultOption);
		for (String s : DayOfWeekNameEng) {
			list.add(new SelectOption(startIndex + "", s));
			startIndex++;
		}
		return list;
	}

	public static List<SelectOption> LoadNumber(SelectOption defaultOption, int start, int end) {
		List<SelectOption> list = new ArrayList<SelectOption>();
		if (defaultOption != null)
			list.add(defaultOption);
		for (int i = start; i <= end; i++)
			list.add(new SelectOption(i + "", i + ""));
		return list;
	}

	/**
	 * ตรวจสอบความถูกต้องของชื่อไฟล์ ว่าสามารถนำไปตั้งชื่อไฟล์ได้หรือไม่
	 * 
	 * @param fileName
	 * @return
	 */
	public static String GetValidFileName(String fileName) {
		return Trim(fileName.replaceAll("[\\\\/:\"*?<>|]+", ""));
	}

	public static String GetValidFileNameNumberToAZ(String fileName) {
		fileName = Trim(fileName.replaceAll("[\\\\/:\"*?<>|]+", ""));
		fileName = Trim(fileName.replaceAll("[^0-9a-zA-Z]+", ""));
		return fileName;
	}

	/**
	 * GetException
	 * 
	 * @param ex       : Exception
	 * @param isForweb
	 * @return String
	 */
	public static String GetException(Exception ex, boolean isForweb) {
		StringBuffer errorMsg = new StringBuffer();
		StackTraceElement[] elements = ex.getStackTrace();
		if (elements != null && elements.length > 0) {
			for (int i = 0; i < elements.length; i++) {
				StackTraceElement traceElement = elements[i];
				errorMsg.append((isForweb ? "<br/>" : FileUtil.NL) + "- getClassName = " + traceElement.getClassName());
				errorMsg.append((isForweb ? "<br/>" : FileUtil.NL) + "- getFileName = " + traceElement.getFileName());
				errorMsg.append(
						(isForweb ? "<br/>" : FileUtil.NL) + "- getLineNumber = " + traceElement.getLineNumber());
				errorMsg.append(
						(isForweb ? "<br/>" : FileUtil.NL) + "- getMethodName = " + traceElement.getMethodName());
				errorMsg.append((isForweb ? "<br/>" : FileUtil.NL) + "- toString = " + traceElement.toString());
			}
			errorMsg.append((isForweb ? "<br/>" : FileUtil.NL) + "*- Exception = " + ex.toString());
		}
		return errorMsg.toString();
	}

	/**
	 * Index In List
	 * 
	 * @param list
	 * @param code
	 * @param isIgnoreCase
	 * @return -1 is find not found.
	 */
	public static int IndexInList(List<String> list, String code, boolean isIgnoreCase) {
		int index = -1;
		try {
			if (list != null && list.size() > 0) {
				if (isIgnoreCase) {
					for (int i = 0; i < list.size(); i++) {
						if (Utility.Trim(code).equalsIgnoreCase(Utility.Trim(list.get(i)))) {
							index = i;
							break;
						}
					}
				} else {
					index = list.indexOf(Utility.Trim(code));
				}
			}
		} catch (Exception ex) {
		}
		return index;
	}

	/**
	 * Index In List
	 * 
	 * @param list
	 * @param code
	 * @return -1 is find not found.
	 */
	public static int IndexInList(List<Long> list, long code) {
		int index = -1;
		try {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).longValue() == code) {
						index = i;
						break;
					}
				}
			}
		} catch (Exception ex) {
		}
		return index;
	}

	/**
	 * Is Have in List
	 * 
	 * @param list
	 * @param code
	 * @param isIgnoreCase
	 * @return
	 */
	public static boolean IsHaveInList(List<String> list, String code, boolean isIgnoreCase) {
		return IndexInList(list, code, isIgnoreCase) != -1;
	}

	/**
	 * Is Have in List
	 * 
	 * @param list
	 * @param code
	 * @return
	 */
	public static boolean IsHaveInList(List<Long> list, long code) {
		return IndexInList(list, code) != -1;
	}

	/**
	 * Set where in format
	 * 
	 * @param list
	 * @return 'AAA','BBB','NNN',...
	 */
	public static String SQLWhereInFormat(List<String> list) {
		String str = "";
		try {
			if (list != null && list.size() > 0) {
				if (list.size() == 1) {
					str += "'" + Utility.Trim(list.get(0)) + "'";
				} else {
					for (String s : list)
						str += "'" + Utility.Trim(s) + "',";
					str = str.substring(0, str.lastIndexOf(","));
				}
			}
		} catch (Exception ex) {
		}
		return str;
	}

	/**
	 * GetFormat
	 * 
	 * @param list
	 * @param separateChar
	 * @return AAA,BBB,NNN,...
	 */
	public static String GetFormat(List<String> list, String separateChar) {
		String str = "";
		try {
			if (list != null && list.size() > 0) {
				if (list.size() == 1) {
					str += Utility.Trim(list.get(0));
				} else {
					for (String s : list)
						str += Utility.Trim(s) + separateChar;
					str = str.substring(0, str.lastIndexOf(separateChar));
				}
			}
		} catch (Exception ex) {
		}
		return str;
	}

	/**
	 * GetFormat
	 * 
	 * @param list
	 * @param separateChar
	 * @return AAA,BBB,NNN,...
	 */
	public static String GetFormat(String[] array, String separateChar) {
		String str = "";
		try {
			if (array != null && array.length > 0) {
				if (array.length == 1) {
					str += Utility.Trim(array[0]);
				} else {
					for (String s : array)
						str += Utility.Trim(s) + separateChar;
					str = str.substring(0, str.lastIndexOf(separateChar));
				}
			}
		} catch (Exception ex) {
		}
		return str;
	}

	/***
	 * elapsed time
	 * 
	 * @param startTimeInMilliseconds
	 * @return
	 */
	public static String GetElapsedTimeInProcess(long startTimeInMilliseconds) {
		String str = "";
		try {
			NumberFormat dayFormat = new DecimalFormat("#0");
			NumberFormat hoursFormat = new DecimalFormat("#0");
			NumberFormat minutesFormat = new DecimalFormat("#0");
			NumberFormat secondsFormat = new DecimalFormat("#0");
			NumberFormat millisecondsFormat = new DecimalFormat("##0");

			// Get current time
			// long start = System.currentTimeMillis();
			// Get elapsed time in milliseconds
			long elapsedTimeMillis = System.currentTimeMillis() - startTimeInMilliseconds;
			// Get elapsed time in seconds
			float elapsedTimeSec = elapsedTimeMillis / 1000F;
			// Get elapsed time in minutes
			float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);
			// Get elapsed time in hours
			float elapsedTimeHour = elapsedTimeMillis / (60 * 60 * 1000F);
			// Get elapsed time in days
			float elapsedTimeDay = elapsedTimeMillis / (24 * 60 * 60 * 1000F);
			str = "Day=[" + dayFormat.format(elapsedTimeDay) + "], Hours=[" + hoursFormat.format(elapsedTimeHour)
					+ "], Minutes=[" + minutesFormat.format(elapsedTimeMin) + "], Seconds=["
					+ secondsFormat.format(elapsedTimeSec) + "], Milliseconds=["
					+ millisecondsFormat.format(elapsedTimeMillis) + "]";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;
	}

	/**
	 * get user agent
	 * 
	 * @param request
	 * @return user-agent
	 */
	public static String GetUserAgent(HttpServletRequest request, int limitLength) {
		try {
			String agent = Utility.Trim(request.getHeader("user-agent"));
			if (limitLength > 0) {
				agent = agent.length() > limitLength ? agent.substring(0, limitLength) : agent;
			}
			return agent;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * get request headers
	 * 
	 * @param request
	 * @return Map<String, String>
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> GetHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = Utility.ObjectToString(headerNames.nextElement());
				String value = Utility.Trim(request.getHeader(key));
				map.put(key, value);
			}
		} catch (Exception ex) {
		}
		return map;
	}

	/**
	 * get request headers
	 * 
	 * @param request
	 * @param limitLength
	 * @return
	 */
	public static String GetHeadersInfo(HttpServletRequest request, int limitLength) {
		try {
			List<String> list = new ArrayList<String>();
			Map<String, String> map = GetHeadersInfo(request);
			for (String key : map.keySet())
				list.add("[" + key + "]=[" + map.get(key) + "]");

			String info = Utility.GetFormat(list, ", ");
			if (limitLength > 0) {
				info = info.length() > limitLength ? info.substring(0, limitLength) : info;
			}
			return info;
		} catch (Exception ex) {
			return "";
		}
	}

	public static String StringEncryptBase64(String plainText, String encoding) {
		try {
			byte[] encodedBytes = java.util.Base64.getEncoder().encode(plainText.getBytes());
			return new String(encodedBytes, encoding);
		} catch (Exception ex) {
			return "";
		}
	}

	public static String StringDecryptBase64(String base64EncodedData, String encoding) {
		try {
			byte[] encodedBytes = base64EncodedData.getBytes();
			byte[] decodedBytes = java.util.Base64.getDecoder().decode(encodedBytes);
			return new String(decodedBytes, encoding);
		} catch (Exception ex) {
			return "";
		}
	}

	public static String GetLastnCharacters(String inputString, int subStringLength) {
		int length = inputString.length();
		if (length <= subStringLength) return inputString;
		int startIndex = length - subStringLength;
		return inputString.substring(startIndex);
	}
}
