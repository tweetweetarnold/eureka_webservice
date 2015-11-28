package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import value.StringValues;

@Entity
@Table(name = "orderwindow")
public class OrderWindow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int windowId;
	private DateTime startDate;
	private DateTime endDate;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "companyId")
	private Company company;
	private String status;
	private Date createDate;

	public OrderWindow(DateTime startDate, DateTime endDate, Company company) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.company = company;
		this.status = StringValues.ORDERWINDOW_DRAFT;
		this.createDate = new Date();
	}

	public OrderWindow(DateTime startDate, Duration duration, Company company) {
		this.startDate = startDate;
		this.endDate = startDate.plus(duration);
		this.company = company;
		this.status = StringValues.ORDERWINDOW_DRAFT;
		this.createDate = new Date();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// returns true if intervals overlap one another
	public boolean overlaps(Interval other) {
		return new Interval(startDate.getMillis(), endDate.getMillis()).overlaps(other);
	}

	public Duration getDuration() {
		return new Duration(endDate.getMillis() - startDate.getMillis());
	}

	public int getWindowId() {
		return windowId;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
