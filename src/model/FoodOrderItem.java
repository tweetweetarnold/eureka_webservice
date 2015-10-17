package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.Table;

@Entity
@Table(name = "foodorderitem")
public class FoodOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodOrderItemId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodOrderId")
	private FoodOrder foodOrder;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodId")
	private Food food;
	private int quantity;
	private String remarks;
	private Date createDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "foodOrderItem")
	private Set<ModifierChosen> modifierChosenList;

	public FoodOrderItem() {
	}

	public FoodOrderItem(FoodOrder foodOrder, Food food, int quantity, String remarks) {
		super();
		this.foodOrder = foodOrder;
		this.food = food;
		this.quantity = quantity;
		this.remarks = remarks;
		this.createDate = new Date();
		modifierChosenList = new HashSet<>();
	}

	public Set<ModifierChosen> getModifierChosenList() {
		return modifierChosenList;
	}

	public void setModifierChosenList(Set<ModifierChosen> modifierChosenList) {
		this.modifierChosenList = modifierChosenList;
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

		if (!modifierChosenList.isEmpty()) {
			Iterator<ModifierChosen> iter = modifierChosenList.iterator();

			while (iter.hasNext()) {
				ModifierChosen tempMod = (ModifierChosen) iter.next();
				price += tempMod.getPrice();
			}
		}
		return price;
	}

	public String getPriceString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getPrice());
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

	public Set<ModifierChosen> getModifierList() {
		return modifierChosenList;
	}

	public void setModifierList(HashSet<ModifierChosen> modifierList) {
		this.modifierChosenList = modifierList;
	}

	public boolean equals(FoodOrderItem otherFoodItem) {
		System.out.println(this.food.equals(otherFoodItem.getFood()));
		if (this.food.equals(otherFoodItem.getFood())) {
			ArrayList<ModifierChosen> modifierListOrigin = new ArrayList<ModifierChosen>(
					modifierChosenList);
			ArrayList<ModifierChosen> modifierListExternal = new ArrayList<ModifierChosen>(
					otherFoodItem.getModifierList());
			int trueCount = 0;
			for (ModifierChosen m : modifierListOrigin) {
				for (ModifierChosen e : modifierListExternal) {
					if (m.equals(e)) {
						trueCount++;
					}
				}
			}
			System.out.println(trueCount + " VS " + modifierListOrigin.size());
			if (trueCount == modifierListOrigin.size()) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	// public boolean equals(FoodOrderItem otherFoodItem) {
	// if (this.food.equals(otherFoodItem.getFood())) {
	// ArrayList<Modifier> modifierListOrigin = new
	// ArrayList<Modifier>(modifierList);
	// ArrayList<Modifier> modifierListExternal = new ArrayList<Modifier>(
	// otherFoodItem.getModifierList());
	// int trueCount = 0;
	// for (Modifier m : modifierListOrigin) {
	// for (Modifier e : modifierListOrigin) {
	// if (m.equals(e)) {
	// trueCount++;
	// }
	// }
	// }
	// if (trueCount == modifierListOrigin.size()) {
	// return true;
	// } else {
	// return false;
	// }
	//
	// } else {
	// return false;
	// }
	//
	// }

}
