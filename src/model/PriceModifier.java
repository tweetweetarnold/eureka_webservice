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
	@JoinColumn(name = "orderPeriodId")
	private OrderPeriod orderPeriod;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int priceModifierId;
	private String type;
	private double value;

	/**
	 * Creates a default constructor for PriceModifier
	 */
	public PriceModifier() {
	}

	/**
	 * Creates a new PriceModifier
	 * 
	 * @param name The name of the PriceModifier
	 * @param type The PriceModifier type
	 * @param value The PriceModifier value
	 * @param orderPeriod The OrderPeriod associate with this PriceModifier
	 */
	public PriceModifier(String name, String type, double value, OrderPeriod orderPeriod) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.orderPeriod = orderPeriod;
	}

	/**
	 * Retrieves the name of the PriceModifier
	 * 
	 * @return The PriceModifier name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the OrderPeriod associated with this PriceModifier
	 * 
	 * @return The OrderPeriod asscoated with this PriceModifier
	 */
	public OrderPeriod getOrderPeriod() {
		return orderPeriod;
	}

	/**
	 * Retrieves the PriceModifier type
	 * 
	 * @return The PriceModifier type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Retrieves the PriceModifier value
	 * 
	 * @return The PriceModifier value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Set the name of the PriceModifier
	 * 
	 * @param name The name to be set for the PriceModifier
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Changes the OrderPeriod associated with this PriceModifier
	 * 
	 * @param orderPeriod The new OrderPeriod to be associated with this PriceModifier
	 */
	public void setOrderPeriod(OrderPeriod orderPeriod) {
		this.orderPeriod = orderPeriod;
	}

	/**
	 * Changes the type of the PriceModifier
	 * 
	 * @param type The type to be set for this PriceModifier
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Changes the value of the PriceModifier
	 * 
	 * @param value The value to be set for this PriceModifier
	 */
	public void setValue(double value) {
		this.value = value;
	}

}
