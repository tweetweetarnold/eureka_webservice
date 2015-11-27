package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.joda.time.Interval;

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

	public OrderWindow(DateTime startDate, DateTime endDate, Company company) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.company = company;
	}

	public Interval getInterval() {
		return new Interval(startDate.getMillis(), endDate.getMillis());
	}

	// returns true if intervals overlap one another
	public boolean overlaps(Interval other) {
		return getInterval().overlaps(other);
	}

	public long getDuration() {
		return endDate.getMillis() - startDate.getMillis();
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
