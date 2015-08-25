package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.LocalTime;

@Entity
public class OperationDayHours {
	@Id
	@GeneratedValue
	private int operationDayHoursId;
	private int day;
	private LocalTime openingTime;
	private LocalTime closingTime;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hawkerId")
	private Hawker hawker;

	public OperationDayHours(int day, LocalTime openingTime,
			LocalTime closingTime) {
		super();
		this.day = day;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public int getOperationDayHoursId() {
		return operationDayHoursId;
	}

	public void setOperationDayHoursId(int operationDayHoursId) {
		this.operationDayHoursId = operationDayHoursId;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

}
