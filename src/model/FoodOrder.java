package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "foodorder")
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodOrderId;
	private String status;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "username")
	private Employee employee;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "foodOrder")
	private Set<FoodOrderItem> foodOrderList;
	private Date createDate;

	public FoodOrder() {
	}

	public FoodOrder(String status, Employee employee, Set<FoodOrderItem> foodOrderList) {
		super();
		this.status = status;
		this.employee = employee;
		this.foodOrderList = foodOrderList;
		this.createDate = new Date();
	}

	public Set<FoodOrderItem> getFoodOrderList() {
		return foodOrderList;
	}

	public void setFoodOrderList(Set<FoodOrderItem> foodOrderList) {
		this.foodOrderList = foodOrderList;
	}

	public int getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// retrieve the total price for the particular food order
	public double getTotalPrice() {
		double result = 0;
		Set<FoodOrderItem> set = getFoodOrderList();
		for (FoodOrderItem item : set) {
			result += (item.getQuantity() * item.getPrice());
		}
		return result;
	}

}
