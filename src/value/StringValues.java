package value;

public final class StringValues {

	// General status
	public static final String ARCHIVED = "Archived";
	public static final String ACTIVE = "Active";

	// For PriceModifier Types
	public static final String PRICEMODIFIER_PERCENTAGE = "Percentage";
	public static final String PRICEMODIFIER_ABSOLUTE = "Absolute";

	// For FoodOrder status
	public static final String ORDER_SUBMITTED = "Submitted";
	public static final String ORDER_CONFIRMED = "Confirmed";
	public static final String ORDER_DELIVERED = "Delivered";

	// For Employee status
	public static final String EMPLOYEE_OK = "Ok";
	public static final String EMPLOYEE_SUSPENDED = "Suspended";
	public static final String EMPLOYEE_DESTROYED = "Destroyed";
	public static final String EMPLOYEE_PENDING_VERIFICATION = "Pending verification";

	// For OrderWindow status
	public static final String ORDERWINDOW_QUEUED = "Queued";
	public static final String ORDERWINDOW_OPENED = "Opened";
	public static final String ORDERWINDOW_PROCESSING = "Processing";
	public static final String ORDERWINDOW_CLOSED = "Closed";

	// *********************************************************************************
	// For Session attributes
	public static final String SESSION_ERROR = "error";
	public static final String SESSION_MESSAGE = "message";
	public static final String SESSION_ORDERS_WINDOW_OPENED_NOGROUP = "orderWindowOpenedNogroup";
	public static final String SESSION_ORDERS_WINDOW_OPENED_STALLS = "orderWindowOpenedStalls";
	public static final String SESSION_USERMGMT_VIEW = "userMgmtView";

}
