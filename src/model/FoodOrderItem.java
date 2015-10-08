package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "foodorderitem")
public class FoodOrderItem {

	@Id
	@GeneratedValue
	private int foodOrderItemId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodOrderId")
	private FoodOrder foodOrder;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodId")
	private Food food;
	private int quantity;
	private double price;
	private String remarks;
	private Date createDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "food")
	private List<Modifier> modifierList;

	public FoodOrderItem() {
	}

	public FoodOrderItem(FoodOrder foodOrder, Food food, int quantity, double price, String remarks, Date createDate) {
		super();
		this.foodOrder = foodOrder;
		this.food = food;
		this.quantity = quantity;
		this.price = price;
		this.remarks = remarks;
		this.createDate = createDate;
		modifierList = new ArrayList<>();
	}

	public int getFoodOrderItemId() {
		return foodOrderItemId;
	}

	public void setFoodOrderItemId(int foodOrderItemId) {
		this.foodOrderItemId = foodOrderItemId;
	}

	public FoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		double price = food.getPrice();
		if (!modifierList.isEmpty()) {
			Iterator iter = modifierList.iterator();
			while (iter.hasNext()) {
				Modifier tempMod = (Modifier) iter.next();
				price += tempMod.getPrice();
			}
		}
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Modifier> getModifierList() {
		return modifierList;
	}

	public void setModifierList(ArrayList<Modifier> modifierList) {
		this.modifierList = modifierList;
	}

	public boolean equals(FoodOrderItem otherFoodItem) {
		if (this.food.equals(otherFoodItem.getFood())) {
			ArrayList<Modifier> modifierListOrigin = new ArrayList<Modifier>(modifierList);
			ArrayList<Modifier> modifierListExternal = new ArrayList<Modifier>(otherFoodItem.getModifierList());
			int trueCount = 0;
			for (Modifier m : modifierListOrigin) {
				for (Modifier e : modifierListOrigin) {
					if (m.equals(e)) {
						trueCount++;
					}
				}
			}
			if (trueCount == modifierListOrigin.size()) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

}
