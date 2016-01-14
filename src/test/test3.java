package test;

import java.util.Iterator;
import java.util.Set;

import controller.FoodOrderController;
import model.FoodDisplayPayment;
import model.FoodOrder;
import model.FoodOrderItem;
import model.ModifierChosen;

public class test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FoodOrderController foodOrderController = new FoodOrderController();
		FoodOrder tempOrder = foodOrderController.getFoodOrder(5);
		System.out.println("total price: "+tempOrder.getTotalPrice());
		System.out.println("discount%: " +tempOrder.getOrderWindow().getDiscount());
		System.out.println("discount val: " +tempOrder.getOrderWindow().getDiscountValue());
		FoodDisplayPayment foodDisplayPayment = new FoodDisplayPayment(tempOrder);
		Set<FoodOrderItem> set =foodDisplayPayment.getFoodOrderDiscountList();
		Iterator iter = set.iterator();
		while (iter.hasNext()){
			FoodOrderItem foodOrderItem =(FoodOrderItem)iter.next();
			System.out.println("Food Price: " + foodOrderItem.getFood().getPrice());
			Set<ModifierChosen> sets = foodOrderItem.getModifierChosenList();
			Iterator iter2 = sets.iterator();
			while(iter2.hasNext()){
				ModifierChosen tempMod = (ModifierChosen)iter2.next();
				System.out.println("Mod Price: "+ tempMod.getPrice());
			}
			System.out.println(foodOrderItem.getPrice());
		}
	}

}
