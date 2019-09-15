package com.demo.helpers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SelectOption implements Serializable {
	@Override
	public String toString() {
		return "SelectOption [index=" + index + ", display=" + display + ", value=" + value + ", objectValue="
				+ objectValue + "]";
	}

	private static final long serialVersionUID = 1L;
	private int index;
	private String display;
	private String value;

	private Object objectValue;

	public SelectOption() {
	}

	public SelectOption(String value, String display) {
		this.value = value;
		this.display = display;
	}

	public SelectOption(String value, String display, Object objectValue) {
		this.value = value;
		this.display = display;
		this.objectValue = objectValue;
	}

	public SelectOption(int index, String value, String display, Object objectValue) {
		this.index = index;
		this.value = value;
		this.display = display;
		this.objectValue = objectValue;
	}

	/**
	 * GetValue To String
	 * Value+"{separate}"+Value+"{separate}"+Value+"{separate}"+...
	 * 
	 * @param list
	 * @param separate
	 * @return
	 */
	public static String GetValueToString(List<SelectOption> list, String separate) {
		String text = "";
		try {
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SelectOption option = (SelectOption) list.get(i);
					text += Utility.Trim(option.value) + separate + " ";
				}
				text = !text.equals("") ? text.substring(0, text.lastIndexOf(separate)) : text;
			}
		} catch (Exception ex) {
		}
		return text;
	}

	/**
	 * GetValue To String
	 * display+"{separate}"+display+"{separate}"+display+"{separate}"+...
	 * 
	 * @param list
	 * @param separate
	 * @return
	 */
	public static String GetDisplayToString(List<SelectOption> list, String separate) {
		String text = "";
		try {
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SelectOption option = (SelectOption) list.get(i);
					text += Utility.Trim(option.display) + separate + " ";
				}
				text = !text.equals("") ? text.substring(0, text.lastIndexOf(separate)) : text;
			}
		} catch (Exception ex) {
		}
		return text;
	}

	/**
	 * IsHaveValue
	 * 
	 * @param list
	 * @param code
	 * @return
	 */
	public static boolean IsHaveValue(List<SelectOption> list, String code) {
		return IndexOfValue(list, code) >= 0;
	}

	/**
	 * IsHave
	 * 
	 * @param list
	 * @param code
	 * @return
	 */
	public static boolean IsHaveDisplay(List<SelectOption> list, String code) {
		return IndexOfDisplay(list, code) >= 0;
	}

	/**
	 * IndexOfValue
	 * 
	 * @param list
	 * @param code
	 * @return -1 is find not found.
	 */
	public static int IndexOfValue(List<SelectOption> list, String code) {
		int index = -1;
		try {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SelectOption option = (SelectOption) list.get(i);
					if (Utility.Trim(code).equalsIgnoreCase(Utility.Trim(option.value))) {
						index = i;
						break;
					}
				}
			}
		} catch (Exception ex) {
		}
		return index;
	}

	public static SelectOption GetByValue(List<SelectOption> list, String code) {
		SelectOption dto = null;
		try {
			if (list != null && list.size() > 0) {
				for (SelectOption option : list) {
					if (code.equalsIgnoreCase(option.value)) {
						dto = option;
						break;
					}
				}
			}
		} catch (Exception ex) {
		}
		return dto;
	}

	/**
	 * IndexOfDisplay
	 * 
	 * @param list
	 * @param code
	 * @return -1 is find not found.
	 */
	public static int IndexOfDisplay(List<SelectOption> list, String code) {
		int index = -1;
		try {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SelectOption option = (SelectOption) list.get(i);
					if (Utility.Trim(code).equalsIgnoreCase(Utility.Trim(option.display))) {
						index = i;
						break;
					}
				}
			}
		} catch (Exception ex) {
		}
		return index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getObjectValue() {
		return objectValue;
	}

	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}

	public static final int OrderByIndex = 0;
	public static final int OrderByDisplay = 1;
	public static final int OrderByValue = 2;
	public static final int OrderByObjectValue = 3;

	public static class OrderByComparator implements Comparator<SelectOption> {
		private int orderBy;
		private boolean isAscOrderBy = true;

		public OrderByComparator(int orderBy, boolean isAscOrderBy) {
			this.orderBy = orderBy;
			this.isAscOrderBy = isAscOrderBy;
		}

		@Override
		public int compare(SelectOption obj1, SelectOption obj2) {
			if (isAscOrderBy) {
				switch (orderBy) {
				case OrderByDisplay:
					return Utility.ObjectToString(obj1.getDisplay()).compareTo(Utility.ObjectToString(obj2.getDisplay()));
				case OrderByValue:
					return Utility.ObjectToString(obj1.getValue()).compareTo(Utility.ObjectToString(obj2.getValue()));
				case OrderByObjectValue:
					if (obj1.getObjectValue() instanceof Integer) {
						return Utility.ObjectToInt(obj1.getObjectValue()) == Utility.ObjectToInt(obj2.getObjectValue()) ? 0
								: (Utility.ObjectToInt(obj1.getObjectValue()) > Utility.ObjectToInt(obj2.getObjectValue()) ? 1
										: -1);
					} else if (obj1.getObjectValue() instanceof Long || obj1.getObjectValue() instanceof BigDecimal) {
						return Utility.ObjectToLong(obj1.getObjectValue()) == Utility.ObjectToLong(obj2.getObjectValue()) ? 0
								: (Utility.ObjectToLong(obj1.getObjectValue()) > Utility.ObjectToLong(obj2.getObjectValue())
										? 1 : -1);
					} else if (obj1.getObjectValue() instanceof Date) {
						return Utility.ObjectToDate(obj1.getObjectValue())
								.compareTo(Utility.ObjectToDate(obj2.getObjectValue()));
					} else {
						return Utility.ObjectToString(obj1.getObjectValue())
								.compareTo(Utility.ObjectToString(obj2.getObjectValue()));
					}
				default:
					return obj1.getIndex() == obj2.getIndex() ? 0 : (obj1.getIndex() > obj2.getIndex() ? 1 : -1);
				}
			} else {
				switch (orderBy) {
				case OrderByDisplay:
					return Utility.ObjectToString(obj2.getDisplay()).compareTo(Utility.ObjectToString(obj1.getDisplay()));
				case OrderByValue:
					return Utility.ObjectToString(obj2.getValue()).compareTo(Utility.ObjectToString(obj1.getValue()));
				case OrderByObjectValue:
					if (obj1.getObjectValue() instanceof Integer) {
						return Utility.ObjectToInt(obj2.getObjectValue()) == Utility.ObjectToInt(obj1.getObjectValue()) ? 0
								: (Utility.ObjectToInt(obj2.getObjectValue()) > Utility.ObjectToInt(obj1.getObjectValue()) ? 1
										: -1);
					} else if (obj1.getObjectValue() instanceof Long || obj1.getObjectValue() instanceof BigDecimal) {
						return Utility.ObjectToLong(obj2.getObjectValue()) == Utility.ObjectToLong(obj1.getObjectValue()) ? 0
								: (Utility.ObjectToLong(obj2.getObjectValue()) > Utility.ObjectToLong(obj1.getObjectValue())
										? 1 : -1);
					} else if (obj1.getObjectValue() instanceof Date) {
						return Utility.ObjectToDate(obj2.getObjectValue())
								.compareTo(Utility.ObjectToDate(obj1.getObjectValue()));
					} else {
						return Utility.ObjectToString(obj2.getObjectValue())
								.compareTo(Utility.ObjectToString(obj1.getObjectValue()));
					}
				default:
					return obj2.getIndex() == obj1.getIndex() ? 0 : (obj2.getIndex() > obj1.getIndex() ? 1 : -1);
				}
			}
		}
	}
}