package model;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(columnDefinition="longblob")
	private byte[] image;
	private Date createDate;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stallId")
	private Stall stall;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "food")
	private Set<Modifier> modifierList;

	public Food() {
	}

	public Food(String name, String description, double price, byte[] image, Stall stall) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stall = stall;
		this.image = image;
		this.createDate = new Date();
		this.modifierList = new HashSet<>();
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public String getPriceString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getPrice());
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

	public Set<Modifier> getModifierList() {
		return modifierList;
	}

	public void setModifierList(Set<Modifier> modifierList) {
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
