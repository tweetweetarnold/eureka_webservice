package value;

public final class StringValues {

	public static final String ACTIVE = "Active";
	// General status
	public static final String ARCHIVED = "Archived";

	public static final String EMPLOYEE_DESTROYED = "Destroyed";
	// For Employee status
	public static final String EMPLOYEE_OK = "Ok";

	public static final String EMPLOYEE_PENDING_VERIFICATION = "Pending verification";
	public static final String EMPLOYEE_SUSPENDED = "Suspended";
	public static final String ORDER_CONFIRMED = "Confirmed";

	public static final String ORDER_DELIVERED = "Delivered";
	// For FoodOrder status
	public static final String ORDER_SUBMITTED = "Submitted";
	public static final String ORDERWINDOW_CLOSED = "Closed";
	public static final String ORDERWINDOW_OPENED = "Opened";
	public static final String EDITTED_BY_ADMIN = "Editted by admin";

	public static final String ORDERWINDOW_PROCESSING = "Processing";
	// For OrderWindow status
	public static final String ORDERWINDOW_QUEUED = "Queued";
	public static final String PRICEMODIFIER_ABSOLUTE = "Absolute";
	// For PriceModifier Types
	public static final String PRICEMODIFIER_PERCENTAGE = "Percentage";

	// *********************************************************************************
	// For Session attributes
	public static final String SESSION_ERROR = "error";
	public static final String SESSION_MESSAGE = "message";
	public static final String SESSION_ORDERS_WINDOW_OPENED_NOGROUP = "orderWindowOpenedNogroup";
	public static final String SESSION_ORDERS_WINDOW_OPENED_STALLS = "orderWindowOpenedStalls";
	public static final String SESSION_USERMGMT_VIEW = "userMgmtView";

}
