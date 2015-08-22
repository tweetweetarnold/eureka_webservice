package entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class FoodOrder {
	@Id @GeneratedValue
	private int foodOrderId;
	private int quantity;
	private Date createDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	
	public FoodOrder(int foodOrderId, int quantity, Date createDate, User user) {
		super();
		this.foodOrderId = foodOrderId;
		this.quantity = quantity;
		this.createDate = createDate;
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getFoodOrderId() {
		return foodOrderId;
	}
	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
