package com.max.couponsprojectphase2.enums;

public enum ErrorType {
	// Common enums:
	GENERAL_ERROR(601, "", "General error.", true), 
	LOGIN_FAILED(602, "", "Login has failed. Please try again.", true),
	NON_REPLACEABLE_NAME(603, "", "Can not change this name.", false),
	ID_NOT_EXIST(604, "", "This ID is not exist.", false),
	RESULT_SET_EXTRACTION_FAIL(605, "", "Failed to extract from ResultSet.", false),
	EMPTY_TABLE(606, "", "The table is empty", false), NULL_DATA(608, "", "Null data.", false),
	EMPTY_DATA(607, "EMPTY DATA", "Empty data.", false), INVALID_ID(610, "", "Invalid ID.", false),
	REDUNDANT_DATA(608, "", "ID is redundant.", false),
	UPDATE_FAILED(609, "", "No difference between an old and new data.", true),
	FAIL_TO_GENERATE_ID(610, "", "Could not generate an ID.", false),
	// User enums:
	USER_ALREADY_EXIST(630, "", "User is already exist.", false),
	USER_DOES_NOT_EXIST(631, "", "User doesn't exist.", false),
	USERNAME_ALREADY_EXISTS(632, "", "Username is allready exists.", false),
	INVALID_PASSWORD(633, "", "Password must contain 6-14 characters, at least one letter, at least one digit.",	false),
	INVALID_USER_TYPE(634, "", "User type is not valid.", false),
	USERNAME_DOES_NOT_EXIST(635, "", "Username doesn't exist.", false),
	USER_TYPE_DOES_NOT_EXIST(636, "", "Usertype doesn't exist.", false),
	// Customer enums:
	CUSTOMER_DOES_NOT_EXIST(640, "", "Customer doesn't exist", false), 
	CUSTOMER_ALREADY_EXIST(641, "", "Customer is already exist.", false),
	NAME_ALLREADY_EXISTS(642, "", "The name you chose already exist. Please enter another name.", false),
	EMAIL_ALREADY_EXISTS(643, "", "This email already exist.", false),
	FIRST_NAME_DOES_NOT_EXIST(644, "", "First name doesn't exist.", false),
	LAST_NAME_DOES_NOT_EXIST(645, "", "Last name doesn't exist.", false),
	EMAIL_DOES_NOT_EXIST(646, "", "Email doesn't exist.", false),
	ADDRESS_DOES_NOT_EXIST(647, "", "Address doesn't exist.", false),
	// Company enums:
	COMPANY_DOES_NOT_EXIST(651, "", "Company doesnt exist.", false),
	COMPANY_ALREADY_EXIST(652, "", "Company already exist.", false),
	// Coupon enums:
	COUPON_EXPIERED(661, "", "This coupon is expiered.", false),
	COUPON_TITLE_EXIST_FOR_COMPANY(662, "",	"This company already have a coupon with the same title.\n Please change the title.", true),
	INVALID_AMOUNT(663, "", "Amount must be possitive > 0.", false),
	INVALID_PRICE(664, "", "Price must be possitive > 0.", false),
	INVALID_DATES(665, "", "At least one of the dates you entered is not valid.", false),
	COUPON_DOES_NOT_EXIST(666, "", "Coupon doesn't exist.", false),
	COUPON_TYPE_DOES_NOT_EXIST(667, "", "Coupon type doesn't exist.", false),
	COUPON_ALREADY_EXISTS(668, "", "This coupon already exists.", false),
	// Purchase enums:
	PURCHASE_ALREADY_EXISTS(671, "", "Purchase is already exist.", false),
    PURCHASE_DOES_NOT_EXIST(672, "", "Purchase doesn't exist", true),
    NOT_ENOUGH_COUPONS_LEFT(673, "", "Not enogh coupons left to purchase the requested amount.", false);
	// CAN_NOT_UPDATE_COMPANY_ID("Coupons company ID can not be changed.", false),

	private int errorNumber;
	private String errorName;
	private String errorMessage;
	private boolean isShowStackTrace;

	// private Constructor

	private ErrorType(int errorNumber, String errorName, String internalMessage, boolean isShowStackTrace) {
		this.errorNumber = errorNumber;
		this.errorName = errorName;
		this.errorMessage = internalMessage;
		this.isShowStackTrace = isShowStackTrace;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public String getErrorName() {
		return errorName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

}