package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pricemodifier")
public class PriceModifier {

	private String name;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "orderWindowId")
	private OrderPeriod orderWindow;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int priceModifierId;
	private String type;
	private double value;

	public PriceModifier() {
	}

	public PriceModifier(String name, String type, double value, OrderPeriod orderWindow) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.orderWindow = orderWindow;
	}

	public String getName() {
		return name;
	}

	public OrderPeriod getOrderWindow() {
		return orderWindow;
	}

	public String getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrderWindow(OrderPeriod orderWindow) {
		this.orderWindow = orderWindow;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
