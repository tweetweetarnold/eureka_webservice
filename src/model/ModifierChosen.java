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
@Table(name = "modifierchoosen")
public class ModifierChosen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modifierId;
	private String name;
	private String description;
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodOrderItemId")
	private FoodOrderItem foodOrderItem;
	// private image byte[];
	private Date createDate;

	public ModifierChosen() {
	}

	public ModifierChosen(String name, String description, double price, FoodOrderItem foodOrderItem, Date createDate) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.foodOrderItem = foodOrderItem;
		this.createDate = createDate;
	}

	public int getModifierId() {
		return modifierId;
	}

	public void setModifierId(int modifierId) {
		this.modifierId = modifierId;
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
