package value;

public final class StringValues {

	// General status
	public static final String ACTIVE = "Active";
	public static final String ARCHIVED = "Archived";

	public static final String EDITTED_BY_ADMIN = "Editted by admin";

	// For Employee status
	public static final String EMPLOYEE_DESTROYED = "Destroyed";
	public static final String EMPLOYEE_ACTIVE = "Active";
	public static final String EMPLOYEE_PENDING_VERIFICATION = "Pending verification";
	public static final String EMPLOYEE_SUSPENDED = "Suspended";

	// For FoodOrder status
	public static final String ORDER_CONFIRMED = "Confirmed";
	public static final String ORDER_DELIVERED = "Delivered";
	public static final String ORDER_SUBMITTED = "Submitted";

	// For OrderWindow status
	public static final String ORDERPERIOD_CLOSED = "Closed";
	public static final String ORDERPERIOD_OPENED = "Opened";
	public static final String ORDERPERIOD_PROCESSING = "Processing";
	public static final String ORDERPERIOD_QUEUED = "Queued";
	public static final String PAID = "Paid";

	// For PriceModifier Types
	public static final String PRICEMODIFIER_ABSOLUTE = "Absolute";
	public static final String PRICEMODIFIER_PERCENTAGE = "Percentage";

	// *********************************************************************************
	// For Session attributes
	public static final String SESSION_ERROR = "error";
	public static final String SESSION_MESSAGE = "message";
	public static final String SESSION_ORDERS_WINDOW_OPENED_NOGROUP = "orderWindowOpenedNogroup";
	public static final String SESSION_ORDERS_WINDOW_OPENED_STALLS = "orderWindowOpenedStalls";
	public static final String SESSION_USERMGMT_VIEW = "userMgmtView";

}
