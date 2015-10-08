package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodId;
	private String name;
	private String description;
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stallId")
	private Stall stall;
	// private image byte[];
	private Date createDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "food")
	private List<Modifier> modifierList;

	public Food() {
	}

	public Food(String name, String description, double price, Stall stall, Date createDate) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stall = stall;
		this.createDate = createDate;
		this.modifierList = new ArrayList<>();
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
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

	public Stall getStall() {
		return stall;
	}

	public void setStall(Stall stall) {
		this.stall = stall;
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

	public void setModifierList(List<Modifier> modifierList) {
		this.modifierList = modifierList;
	}

	// check if canteen, stall and food name are the same and returns true if
	// they are all the same
	public boolean equals(Food otherFood) {
		if (this.stall.getCanteen().getName().equals(otherFood.getStall().getCanteen().getName())) {
			if (this.stall.getName().equals(otherFood.getStall().getName())) {
				if (this.name.equals(otherFood.getName())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
