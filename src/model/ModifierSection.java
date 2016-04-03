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
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodId")
	private Food food;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "modifierSection")
	private Set<Modifier> modifierList;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modifierSectionId;

	/**
	 * Creates a default constructor for ModifierSection
	 */
	public ModifierSection() {
	}

	/**
	 * Creates a new ModifierSection
	 * 
	 * @param categoryName The category that the new modifier section will represent
	 * @param displayType use either "d" for dropdown or "c" for checkbox
	 * @param modifierList the modifiers that will exist under this ModifierSection
	 * @param food the Food that this ModifierSection belongs to
	 */
	public ModifierSection(String categoryName, String displayType, Set<Modifier> modifierList,
			Food food) {
		this.categoryName = categoryName;
		this.displayType = displayType;
		this.modifierList = modifierList;
		this.food = food;
	}

	/**
	 * Retrieves the category name of the ModifierSection
	 * 
	 * @return The category name of the ModifierSection
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Retrieves the display type of the ModifierSection
	 * 
	 * @return The display type of the ModifierSection
	 */
	public String getDisplayType() {
		return displayType;
	}

	/**
	 * Retrieves the food that the ModifierSection belongs to
	 * 
	 * @return The food that the ModifierSection belongs to
	 */
	public Food getFood() {
		return food;
	}

	/**
	 * Retrieves the list of Modifiers under this ModifierSection
	 * 
	 * @return The list of Modifiers under this ModifierSection
	 */
	public Set<Modifier> getModifierList() {
		return modifierList;
	}

	/**
	 * Retrieves the Id for this ModifierSection
	 * 
	 * @return The Id of this ModifierSection
	 */
	public int getModifierSectionId() {
		return modifierSectionId;
	}

	/**
	 * Sets the category name of this ModifierSection
	 * 
	 * @param categoryName The new category name for this ModifierSection
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Sets the display type of this ModifierSection
	 * 
	 * @param displayType The new display type for this ModifierSection
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	/**
	 * Sets the Food associated with this ModifierSection
	 * 
	 * @param food The Food associated with this ModifierSection
	 */
	public void setFood(Food food) {
		this.food = food;
	}

	/**
	 * Changes the current list of Modifiers attached to the ModifierSection to a new set of
	 * Modifiers
	 * 
	 * @param modifierList The new Set of Modifiers to attach to the ModifierSection
	 */
	public void setModifierList(Set<Modifier> modifierList) {
		this.modifierList = modifierList;
	}

}
