package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modifierchosen")
public class ModifierChosen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modifierChosenId;
	private String name;
	private String description;
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodOrderItemId")
	private FoodOrderItem foodOrderItem;
	private Date createDate;

	public ModifierChosen() {
	}

	public ModifierChosen(Modifier m, FoodOrderItem item) {
		this.name = m.getName();
		this.description = m.getDescription();
		this.price = m.getPrice();
		this.createDate = new Date();
		this.foodOrderItem = item;
	}

	public int getModifierId() {
		return modifierChosenId;
	}

	public void setModifierId(int modifierChosenId) {
		this.modifierChosenId = modifierChosenId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public FoodOrderItem getFood() {
		return foodOrderItem;
	}

	public void setFood(FoodOrderItem foodOrderItem) {
		this.foodOrderItem = foodOrderItem;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// Check if otherModifier equal current modifier
	public boolean equals(ModifierChosen otherModifier) {
		if (this.name.equals((otherModifier).getName())) {
			return true;
		} else {
			return false;
		}
	}

}
