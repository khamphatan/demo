package com.demo.model;

public class Enums {
	public enum ROLE {
		/**
		 * ผู้ใช้งานทั่วไป
		 */
		ROLE_USER("USER"),
		/**
		 * เจ้าหน้าที่ดูแลระบบ
		 */
		ROLE_ADMIN("ADMIN");

		ROLE(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}

	public enum APPLICATION_STATUS {
		/**
		 * เปิดใช้งาน
		 */
		ACTIVE("ACTIVE"),
		/**
		 * ปิดใช้งาน
		 */
		INACTIVE("INACTIVE"),
		/**
		 * ยกเลิกการใช้งาน
		 */
		DELETE("DELETE");

		APPLICATION_STATUS(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}

	public enum TITLE_NAME {
		/**
		 * MISS
		 */
		MISS("MISS"),
		/**
		 * MRS.
		 */
		MRS("MRS."),
		/**
		 * MR.
		 */
		MR("MR.");

		TITLE_NAME(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}

	public enum GENDER {
		/**
		 * Female
		 */
		F("Female"),
		/**
		 * Male
		 */
		M("Male");

		GENDER(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}

	public enum MARITAL_STATUS {
		/**
		 * DIVORCE
		 */
		D("DIVORCE"),
		/**
		 * MARRIED
		 */
		M("MARRIED"),
		/**
		 * SINGLE
		 */
		S("SINGLE"),
		/**
		 * WIDOW
		 */
		W("WIDOW");

		MARITAL_STATUS(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}

	public enum MEMBER_TYPE {
		/**
		 * GOLD
		 */
		GOLD("GOLD"),
		/**
		 * PLATINUM
		 */
		PLATINUM("PLATINUM"),
		/**
		 * SILVER
		 */
		SILVER("SILVER");

		MEMBER_TYPE(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}

	public enum RESPONSE_TYPE {
		/**
		 * Error
		 */
		ERROR("Error"),
		/**
		 * Information
		 */
		INFO("Information"),
		/**
		 * Successfully
		 */
		SUCCESS("Successfully"),
		/**
		 * Warning
		 */
		WARNING("Warning");

		RESPONSE_TYPE(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}

	public enum RESPONSE_CODE {
		/**
		 * None
		 */
		RES_0000("None"),
		/**
		 * Error Security
		 */
		RES_8000("Error Security"),
		/**
		 * Error Processing
		 */
		RES_9000("Error Processing"),
		/**
		 * Invalid Header Parameter
		 */
		RES_9001("Invalid Header Parameter"),
		/**
		 * Invalid Parameter
		 */
		RES_9002("Invalid Parameter"),
		/**
		 * Not Found
		 */
		RES_9003("Not Found"),
		/**
		 * Duplicated
		 */
		RES_9004("Duplicated"),
		/**
		 * Call External Services
		 */
		RES_9005("Call External Services");

		RESPONSE_CODE(String desc) {
			this.description = desc;
		}

		private String description;

		public String Description() {
			return description;
		}
	}
}