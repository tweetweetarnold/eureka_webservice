package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import value.StringValues;

/**
 * Represents the Ordering Window entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "orderwindow")
public class OrderWindow {

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "canteenId")
	private Canteen canteen;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "companyId")
	private Company company;
	private Date createDate;
	private double discount, discountAbsolute;
	@Transient
	private DateTime endDate;
	@Column(name = "endDate")
	private Date endDateFormatted;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "orderWindow")
	private List<PriceModifier> priceModifierList;
	private String remarks, status;
	@Transient
	private DateTime startDate;
	@Column(name = "startDate")
	private Date startDateFormatted;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int windowId;

	/**
	 * Creates a default constructor for OrderWindow
	 */
	public OrderWindow() {
	}

	public OrderWindow(DateTime startDate, DateTime endDate, Company company, Canteen canteen,
			String remarks, ArrayList<PriceModifier> priceModifierList) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startDateFormatted = startDate.toDate();
		this.endDateFormatted = endDate.toDate();
		this.company = company;
		this.canteen = canteen;
		this.createDate = new Date();
		this.remarks = remarks;
		this.status = StringValues.ACTIVE;
		this.priceModifierList = priceModifierList;
	}

	public OrderWindow(DateTime startDate, DateTime endDate, Company company, Canteen canteen,
			double discount, double discountAbsolute, String remarks,
			ArrayList<PriceModifier> priceModifierList) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startDateFormatted = startDate.toDate();
		this.endDateFormatted = endDate.toDate();
		this.company = company;
		this.canteen = canteen;
		this.discount = discount;
		this.createDate = new Date();
		this.discountAbsolute = discountAbsolute;
		this.remarks = remarks;
		this.status = StringValues.ACTIVE;
		this.priceModifierList = priceModifierList;
	}

	/**
	 * Creates a new OrderWindow with a starting and ending DateTime, company and the canteen
	 * 
	 * @param startDate The starting Date and Time of this OrderWindow
	 * @param endDate The ending Date and Time of this OrderWindow
	 * @param company The Company indicated in this OrderWindow
	 * @param canteen The Canteen indicated in this OrderWindow
	 */
	// public OrderWindow(DateTime startDate, DateTime endDate, Company company, Canteen canteen,
	// double discount, String remarks, ArrayList<PriceModifier> priceModifierList) {
	// this.startDate = startDate;
	// this.endDate = endDate;
	// this.startDateFormatted = startDate.toDate();
	// this.endDateFormatted = endDate.toDate();
	// this.company = company;
	// this.canteen = canteen;
	// this.discount = discount;
	// this.createDate = new Date();
	// this.remarks = remarks;
	// this.status = StringValues.ACTIVE;
	// this.priceModifierList = priceModifierList;
	// }

	/**
	 * Creates a new OrderWindow with a starting date and time, duration, the Company and the
	 * Canteen
	 * 
	 * @param startDate The starting date and time of this OrderWindow
	 * @param duration The duration of this OrderWindow
	 * @param company The Company in this OrderWindow
	 * @param canteen The Canteen in this OrderWindow
	 */
	// public OrderWindow(DateTime startDate, Duration duration, Company company, Canteen canteen,
	// double discount, String remarks, ArrayList<PriceModifier> priceModifierList) {
	// this.startDate = startDate;
	// this.endDate = startDate.plus(duration);
	// this.startDateFormatted = startDate.toDate();
	// this.endDateFormatted = endDate.toDate();
	// this.canteen = canteen;
	// this.company = company;
	// this.discount = discount;
	// this.createDate = new Date();
	// this.status = StringValues.ACTIVE;
	// this.priceModifierList = priceModifierList;
	// }

	/**
	 * Retrieves the Canteen in this order window
	 * 
	 * @return The Canteen object in this order window
	 */
	public Canteen getCanteen() {
		return canteen;
	}

	/**
	 * Retrieves the Company of this OrderWindow
	 * 
	 * @return The Company object in this OrderWindow
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Retrieves the date which this OrderWindow is created
	 * 
	 * @return The date that this OrderWindow object is created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the discount of this order window
	 * 
	 * @return The current discount in this order window
	 */
	public double getDiscount() {
		return discount;
	}

	public double getDiscountAbsolute() {
		return discountAbsolute;
	}

	/**
	 * Retrieves the duration of this OrderWindow
	 * 
	 * @return The duration of the OrderWindow
	 */
	public Duration getDuration() {
		return new Duration(endDate.getMillis() - startDate.getMillis());
	}

	/**
	 * Retrieves the ending Date and Time of this OrderWindow
	 * 
	 * @return The current ending Date and Time in Joda-Time
	 */
	public DateTime getEndDate() {
		return new DateTime(endDateFormatted);
	}

	/**
	 * Retrieves the formatted ending DateTime of this order window
	 * 
	 * @return The current formatted ending DateTime
	 */
	public Date getEndDateFormatted() {
		return endDateFormatted;
	}

	public List<PriceModifier> getPriceModifierList() {
		return priceModifierList;
	}

	public String getRemarks() {
		return remarks;
	}

	/**
	 * Retrieves the starting Date and Time of this OrderWindow
	 * 
	 * @return The starting Date and Time in Joda-Time
	 */
	public DateTime getStartDate() {
		return new DateTime(startDateFormatted);
	}

	/**
	 * Retrieves the formatted starting DateTime of this order window
	 * 
	 * @return The current formatted starting DateTime
	 */
	public Date getStartDateFormatted() {
		return startDateFormatted;
	}

	/**
	 * Retrieves the current status of this OrderWindow
	 * 
	 * @return The status of the order window. It can be indicated as "Queued", "Opened" or "Closed"
	 */
	public String getStatus() {
		return this.status;
		// Interval i = new Interval(startDate.getMillis(), endDate.getMillis());
		// String status = "Error";
		//
		// if (i.containsNow()) {
		// status = StringValues.ORDERWINDOW_OPENED;
		// } else if (i.isBeforeNow()) {
		// status = StringValues.ORDERWINDOW_QUEUED;
		// } else if (i.isAfterNow()) {
		// status = StringValues.ORDERWINDOW_CLOSED;
		// }
		// return status;
	}

	/**
	 * Retrieves the ID of the OrderWindow
	 * 
	 * @return The OrderWindow ID
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * Check if current OrderWindow overlaps with another Interval
	 * 
	 * @param other Interval to be compared with
	 * @return Returns true if this OrderWindow overlaps with the parameter Interval
	 */
	public boolean overlaps(Interval other) {
		return new Interval(startDate.getMillis(), endDate.getMillis()).overlaps(other);
	}

	/**
	 * Changes the current Canteen in this order window
	 * 
	 * @param canteen The new Canteen object
	 */
	public void setCanteen(Canteen canteen) {
		this.canteen = canteen;
	}

	/**
	 * Changes the current Company in this OrderWindow
	 * 
	 * @param company The Company object to be set in this OrderWindow
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Changes the current date of this OrderWindow being created
	 * 
	 * @param createDate The new date created of this OrderWindow
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Changes the current discount of this order window
	 * 
	 * @param discount The new discount to be updated
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setDiscountAbsolute(double discountAbsolute) {
		this.discountAbsolute = discountAbsolute;
	}

	/**
	 * Changes the current ending Date and Time of this OrderWindow
	 * 
	 * @param endDate The new ending Date and Time in java DateTime
	 */
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
		this.endDateFormatted = endDate.toDate();
	}

	/**
	 * Changes the current formatted ending DateTime of this order window
	 * 
	 * @param endDateFormatted The new formatted ending DateTime
	 */
	public void setEndDateFormatted(Date endDateFormatted) {
		this.endDateFormatted = endDateFormatted;
	}

	public void setPriceModifierList(ArrayList<PriceModifier> priceModifierList) {
		this.priceModifierList = priceModifierList;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * Changes the current starting Date and Time of this OrderWindow
	 * 
	 * @param startDate The new starting Date and Time in java DateTime
	 */
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
		this.startDateFormatted = startDate.toDate();
	}

	/**
	 * Changes the current formatted starting DateTime of this order window
	 * 
	 * @param startDateFormatted The new formatted starting DataTime
	 */
	public void setStartDateFormatted(Date startDateFormatted) {
		this.startDateFormatted = startDateFormatted;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Changes the current OrderWindow ID
	 * 
	 * @param windowId The OrderWindow's new ID
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

}
