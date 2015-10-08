package model;

import java.util.Date;
import java.util.Iterator;
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

@Entity
@Table(name = "foodorder")
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodOrderId;
	private String status;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin")
	private Admin admin;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "foodOrder")
	private List<FoodOrderItem> foodOrderList;
	private Date createDate;

	public FoodOrder() {
	}

	public FoodOrder(String status, Employee employee, Admin admin,
			List<FoodOrderItem> foodOrderList, Date createDate) {
		super();
		this.status = status;
		this.employee = employee;
		this.admin = admin;
		this.foodOrderList = foodOrderList;
		this.createDate = createDate;
	}

	public List<FoodOrderItem> getFoodOrderList() {
		return foodOrderList;
	}

	public void setFoodOrderList(List<FoodOrderItem> foodOrderList) {
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

	public Admin getAdmin() {
		return admin;
	}

	public void setDriver(Admin admin) {
		this.admin = admin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// retrieve the total price for the particular food order
	public double getFoodOrderTotalPrice() {
		double result = 0.0;
		List<FoodOrderItem> foodOrderList = getFoodOrderList();
		Iterator iter = foodOrderList.iterator();
		while (iter.hasNext()) {
			FoodOrderItem tempItem = (FoodOrderItem) iter.next();
			result += (tempItem.getQuantity() * tempItem.getPrice());
		}
		return result;
	}

}
