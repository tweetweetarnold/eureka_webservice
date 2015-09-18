package entity;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodOrderId;
	private String status;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driverId")
	private Driver driver;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "foodOrder")
	private Set<FoodOrderItem> foodOrderList;
	private Date createDate;

	public FoodOrder(String status, Employee employee, Driver driver,
			Set<FoodOrderItem> foodOrderList, Date createDate) {
		super();
		this.status = status;
		this.employee = employee;
		this.driver = driver;
		this.foodOrderList = foodOrderList;
		this.createDate = createDate;
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	//retrieve the total price for the particular food order
	public double getFoodOrderTotalPrice(){
		double result = 0.0;
		Set<FoodOrderItem> foodOrderList = getFoodOrderList();
		Iterator iter = foodOrderList.iterator();
		while(iter.hasNext()){
			FoodOrderItem tempItem = (FoodOrderItem) iter.next();
			result += (tempItem.getQuantity()* tempItem.getPrice());
		}
		return result;
	}

}
