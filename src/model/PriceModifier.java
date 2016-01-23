package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pricemodifier")
public class PriceModifier {

	private String name;
	private OrderWindow orderWindow;
	private String type;
	private double value;

	public PriceModifier(String name, String type, double value, OrderWindow orderWindow) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.orderWindow = orderWindow;
	}

	public String getName() {
		return name;
	}

	public OrderWindow getOrderWindow() {
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

	public void setOrderWindow(OrderWindow orderWindow) {
		this.orderWindow = orderWindow;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
