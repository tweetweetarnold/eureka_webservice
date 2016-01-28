package model;

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
@Table(name = "modifiersection")
public class ModifierSection {
	private String categoryName;
	private String displayType;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "foodId")
	private Food food;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "modifierSection")
	private Set<Modifier> modifierList;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modifierSectionId;

	/**
	 * Creates a default constructor for ModifierSection
	 */
	public ModifierSection() {
	}

	public ModifierSection(String categoryName, String displayType, Set<Modifier> modifierList,
			Food food) {
		this.categoryName = categoryName;
		this.displayType = displayType;
		this.modifierList = modifierList;
		this.food = food;
	}

	// public ModifierSection(String categoryName, String displayType, Set<Modifier> modifierList){
	// this.categoryName = categoryName;
	// this.displayType = displayType;
	// this.modifierList = modifierList;
	// try{
	// this.food = modifierList..getFood();
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// }

	public String getCategoryName() {
		return categoryName;
	}

	public String getDisplayType() {
		return displayType;
	}

	public Food getFood() {
		return food;
	}

	public Set<Modifier> getModifierList() {
		return modifierList;
	}

	public int getModifierSectionId() {
		return modifierSectionId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public void setModifierList(Set<Modifier> modifierList) {
		this.modifierList = modifierList;
	}

	public void setModifierSectionId(int modifierSectionId) {
		this.modifierSectionId = modifierSectionId;
	}

}
