package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FoodDisplayPayment {

	private Date createDate;
	private Employee employee;
	private Set<FoodOrderItem> foodOrderDiscountList;
	private int foodOrderId;
	private Set<FoodOrderItem> foodOrderList;
	private OrderWindow orderWindow;
	private String status;
	private double totalPriceIncludingDisc;
	private String transactionId;

	public FoodDisplayPayment(FoodOrder order) {
		this.foodOrderId = order.getFoodOrderId();
		this.status = order.getStatus();
		this.employee = order.getEmployee();
		this.foodOrderList = order.getFoodOrderList();
		this.createDate = order.getCreateDate();
		this.transactionId = order.getTransactionId();
		this.orderWindow = order.getOrderWindow();
		double tempDisc = orderWindow.getDiscountAbsolute();
		this.totalPriceIncludingDisc = order.getTotalPriceBeforePriceModifiers() - tempDisc;
		// prevent negative values
		if (totalPriceIncludingDisc < 0) {
			totalPriceIncludingDisc = 0;
		}
		this.foodOrderDiscountList = generateFoodOrderDiscountList(foodOrderList, tempDisc);

	}

	public Set<FoodOrderItem> generateFoodOrderDiscountList(Set<FoodOrderItem> foodOrderList,
			double tempDisc) {
		Set<FoodOrderItem> foodOrderDiscountList = new HashSet<FoodOrderItem>();
		Iterator<FoodOrderItem> iter = foodOrderList.iterator();

		while (iter.hasNext()) {
			FoodOrderItem foodOrderItem = (FoodOrderItem) iter.next();

			FoodOrderItem tempfoodOrderItem = foodOrderItem;
			Food oldFood = tempfoodOrderItem.getFood();
			Food newFood = new Food(oldFood.getName(), oldFood.getDescription(),
					oldFood.getPrice(), oldFood.getImageDirectory(), oldFood.getPublicId(), oldFood.getStall());
			newFood.setModifierList(oldFood.getModifierList());
			double foodPrice = tempfoodOrderItem.getPrice();
			System.out.println("Food Price ORIGINAL :" + foodPrice);
			System.out.println("TempDisc " + tempDisc);
			if (tempDisc > 0) {
				if (foodPrice <= tempDisc) {

					tempDisc -= foodPrice;
					System.out.println("TempDisc2 " + tempDisc);
					Set<ModifierChosen> modifierChosenSet = tempfoodOrderItem
							.getModifierChosenList();
					for (ModifierChosen mod : modifierChosenSet) {
						mod.setPrice(0);
					}
					newFood.setPrice(0);
					tempfoodOrderItem.setFood(newFood);
					System.out.println("HERE 1");
				} else if (foodPrice > tempDisc) {
					System.out.println("TempDisc3 " + tempDisc);
					System.out.println("HERE 2");
					Set<ModifierChosen> modifierChosenSet = tempfoodOrderItem
							.getModifierChosenList();
					for (ModifierChosen mod : modifierChosenSet) {
						double modPrice = mod.getPrice();
						if (modPrice > tempDisc) {
							modPrice -= tempDisc;
							tempDisc = 0;
							mod.setPrice(modPrice);
							System.out.println("Here2 :: modPrice>TempDis");
						} else if (tempDisc != 0) {
							tempDisc -= modPrice;
							mod.setPrice(0);
							System.out.println("Here2 :: modPrice<=TempDis");
						}
					}

					double tempFoodPrice = newFood.getPrice();
					if (tempDisc != 0) {
						tempFoodPrice -= tempDisc;
						tempDisc = 0;
					}
					newFood.setPrice(tempFoodPrice);
					tempfoodOrderItem.setFood(newFood);

				}

			}
			System.out.println("Size of Modifier: "
					+ tempfoodOrderItem.getModifierChosenList().size());
			foodOrderDiscountList.add(tempfoodOrderItem);

		}
		return foodOrderDiscountList;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public Set<FoodOrderItem> getFoodOrderDiscountList() {
		return foodOrderDiscountList;
	}

	public int getFoodOrderId() {
		return foodOrderId;
	}

	public Set<FoodOrderItem> getFoodOrderList() {
		return foodOrderList;
	}

	public OrderWindow getOrderWindow() {
		return orderWindow;
	}

	public String getStatus() {
		return status;
	}

	public double getTotalPriceIncludingDisc() {
		return totalPriceIncludingDisc;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setFoodOrderDiscountList(Set<FoodOrderItem> foodOrderDiscountList) {
		this.foodOrderDiscountList = foodOrderDiscountList;
	}

	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	public void setFoodOrderList(Set<FoodOrderItem> foodOrderList) {
		this.foodOrderList = foodOrderList;
	}

	public void setOrderWindow(OrderWindow orderWindow) {
		this.orderWindow = orderWindow;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotalPriceIncludingDisc(double totalPriceIncludingDisc) {
		this.totalPriceIncludingDisc = totalPriceIncludingDisc;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "FoodDisplayPayment [foodOrderId=" + foodOrderId + ", status=" + status
				+ ", employee=" + employee + ", foodOrderList=" + foodOrderList
				+ ", foodOrderDiscountList=" + foodOrderDiscountList + ", createDate=" + createDate
				+ ", transactionId=" + transactionId + ", orderWindow=" + orderWindow
				+ ", totalPriceIncludingDisc=" + totalPriceIncludingDisc + "]";
	}

}
