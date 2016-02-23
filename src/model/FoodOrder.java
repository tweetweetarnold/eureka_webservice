package model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import value.StringValues;

/**
 * Represents the Food Order entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "foodorder")
public class FoodOrder {
	private Date createDate;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "email")
	private Employee employee;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodOrderId;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "foodOrder")
	private Set<FoodOrderItem> foodOrderList;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderWindowId")
	private OrderWindow orderWindow;
	private String status;
	private String transactionId;

	/**
	 * Creates a default constructor for FoodOrder
	 */
	public FoodOrder() {
	}

	/**
	 * Creates a new FoodOrder with a status, employee, list of food order items and the order
	 * window
	 * 
	 * @param status The status of the Food order
	 * @param employee The Employee who created this Food order
	 * @param foodOrderList The list of food order items in this Food order
	 * @param orderWindow The window period for ordering food
	 */
	public FoodOrder(String status, Employee employee, Set<FoodOrderItem> foodOrderList,
			OrderWindow orderWindow) {
		super();
		this.status = status;
		this.employee = employee;
		this.foodOrderList = foodOrderList;
		this.createDate = new Date();
		this.transactionId = null;
		this.orderWindow = orderWindow;
	}

	/**
	 * Retrieves the date of this food order being created
	 * 
	 * @return The date of the food order created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the Employee of this food order
	 * 
	 * @return The Employee of this Food order
	 */
	public Employee getEmployee() {
		return employee;
	}

	public double getFinalPrice() {
		double result = getTotalPriceBeforePriceModifiers();

		// order matters for which price modifier to affect the total price first
		List<PriceModifier> priceModifierList = orderWindow.getPriceModifierList();
		System.out.println("pricemodifierlist size: " + priceModifierList.size());

		if (!priceModifierList.isEmpty()) { // temporary fix. pricemodifierlist no idea why size
											// more than specified
			for (int i = 0; i < 1; i++) {
				PriceModifier m = priceModifierList.get(i);
				String type = m.getType();
				double value = m.getValue();
				System.out.println("PriceModifier: Name=" + m.getName() + ", Type=" + type
						+ ", Value=" + value);

				if (type.equals(StringValues.PRICEMODIFIER_ABSOLUTE)) {
					result += value;
				} else if (type.equals(StringValues.PRICEMODIFIER_PERCENTAGE)) {
					result *= value;
				}
			}
		}

		return result;
	}

	/**
	 * Retrieves the ID of the Food order
	 * 
	 * @return The Food order's ID
	 */
	public int getFoodOrderId() {
		return foodOrderId;
	}

	/**
	 * Retrieves the list of Food order items in the Food order
	 * 
	 * @return The list of food order items
	 */
	public Set<FoodOrderItem> getFoodOrderList() {
		return foodOrderList;
	}

	/**
	 * Retrieves the current order window
	 * 
	 * @return The window period for ordering food
	 */
	public OrderWindow getOrderWindow() {
		return orderWindow;
	}

	/**
	 * Retrieve the current status of the Food order
	 * 
	 * @return the status of the Food order
	 */
	public String getStatus() {
		return status;
	}

	// retrieve the total price for the particular food order
	/**
	 * Retrieves the total price for the food order
	 * 
	 * @return The food order's total price (FoodOrderItem * FoodOrderItem Quantity)
	 */
	public double getTotalPriceBeforePriceModifiers() {
		double price = 0;
		for (FoodOrderItem item : getFoodOrderList()) {
			price += item.getTotalPrice();
		}
		return price;
	}

	/**
	 * Retrieves the transaction ID for this food order
	 * 
	 * @return The food order's transaction ID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Changes the current date of the food order being created
	 * 
	 * @param createDate The new date of the food order
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Changes the Employee of this food order
	 * 
	 * @param employee The new Employee of this food order
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * Changes the Food order ID
	 * 
	 * @param foodOrderId The new Food order ID
	 */
	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	/**
	 * Changes the current list of Food order items
	 * 
	 * @param foodOrderList The new list of Food order items
	 */
	public void setFoodOrderList(Set<FoodOrderItem> foodOrderList) {
		this.foodOrderList = foodOrderList;
	}

	/**
	 * Changes the current order window with a new order window
	 * 
	 * @param orderWindow The new order window for ordering food
	 */
	public void setOrderWindow(OrderWindow orderWindow) {
		this.orderWindow = orderWindow;
	}

	/**
	 * Changes the current status of the Food order
	 * 
	 * @param status The new status of the Food order
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Changes the current transaction ID of the Food order
	 * 
	 * @param transactionId The payment transaction ID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
