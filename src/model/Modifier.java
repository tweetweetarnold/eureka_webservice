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
@Table(name = "modifier")
public class Modifier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modifierId;
	private String name;
	private String description;
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodId")
	private Food food;
	private Date createDate;

	public Modifier() {
	}

	public Modifier(String name, String description, double price, Food food) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.food = food;
		this.createDate = new Date();
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

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// Check if otherModifier equal current modifier
	public boolean equals(Modifier otherModifier) {
		if (this.food.equals(otherModifier.getFood())) {
			if (this.name.equals(otherModifier.getName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

}
