package test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import model.Admin;
import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;
import connection.MyConnection;

public class Test2 {

	public static void main(String[] args) {

		Canteen canteen = new Canteen("xiaodingdang", "123", new Date(), null);
		Stall stall = new Stall("stall", "123", "foodrepublic", 123, canteen, new Date(), null);
		Food food = new Food("chickenrice", "damn nice", 2.50, stall, new Date());

		// Insert mock Data
		Set<Stall> stallList2 = new HashSet<>();
		Set<Food> foodListB1 = new HashSet<>();
		Set<Food> foodListB2 = new HashSet<>();
		Set<Food> foodListB3 = new HashSet<>();
		Set<Food> foodListB4 = new HashSet<>();
		Set<Food> foodListB5 = new HashSet<>();
		Set<Food> foodListB6 = new HashSet<>();
		Set<Food> foodListB7 = new HashSet<>();
		Set<Food> foodListB8 = new HashSet<>();
		Set<Food> foodListB9 = new HashSet<>();
		Set<Food> foodListB10 = new HashSet<>();
		Set<Food> foodListB11 = new HashSet<>();

		Stall stall1 = new Stall("Sliced Fish Bee Hoon Stall", "123", "Fish Slice Bee Hoon",
				91379160, null, new Date(), null);
		Food food1 = new Food("Fish Slice Bee Hoon", "", 3.70, stall1, new Date());
		Food food2 = new Food("Fish Soup and Rice", "", 4.70, stall1, new Date());
		foodListB1.add(food1);
		foodListB1.add(food2);
		stall1.setFoodList(foodListB1);

		Stall stall2 = new Stall("Malay food Stall", "123", "Malay food Stall", 81145966, null,
				new Date(), null);
		Food food3 = new Food("Malay Chicken List", "", 3.00, stall2, new Date());
		Food food4 = new Food("Malay Fish List", "", 3.00, stall2, new Date());
		Food food5 = new Food("Malay Mutton List", "", 3.00, stall2, new Date());
		foodListB2.add(food3);
		foodListB2.add(food4);
		foodListB2.add(food5);
		stall2.setFoodList(foodListB2);

		Stall stall3 = new Stall("Mixed Rice Stall", "123", "Mixed Rice Stall", 93482772, null,
				new Date(), null);
		Food food6 = new Food("Mixed Rice", "", 3.00, stall3, new Date());
		Food food7 = new Food("Porridge", "", 3.00, stall3, new Date());
		foodListB3.add(food6);
		foodListB3.add(food7);
		stall3.setFoodList(foodListB3);

		Stall stall4 = new Stall("Wanton Mee Stall", "123", "Wanton Mee Stall", 0, null,
				new Date(), foodListB4);
		Food food8 = new Food("Wanton Mee", "", 3.00, stall4, new Date());
		foodListB4.add(food8);
		stall4.setFoodList(foodListB4);

		Stall stall5 = new Stall("Indian Food Stall", "123", "Indian Food Stall", 93841009, null,
				new Date(), null);
		Food food9 = new Food("Indian Chicken List", "", 3.50, stall5, new Date());
		Food food10 = new Food("Indian Fish List", "", 3.50, stall5, new Date());
		Food food11 = new Food("Mutton Briyani", "", 4.00, stall5, new Date());
		Food food12 = new Food("Ayam Penyat", "", 4.00, stall5, new Date());
		foodListB5.add(food9);
		foodListB5.add(food10);
		foodListB5.add(food11);
		foodListB5.add(food12);
		stall5.setFoodList(foodListB5);

		Stall stall6 = new Stall("Roast Duck & Chicken Rice Stall", "123",
				"Roast Duck & Chicken Rice Stall", 98427347, null, new Date(), null);
		Food food13 = new Food("Roast Chicken Rice", "add charseiw/roastmeat 3.00", 2.50, stall6,
				new Date());
		Food food14 = new Food("CharSiew Rice", "", 2.50, stall6, new Date());
		Food food15 = new Food("Roast Meat Rice", "", 2.50, stall6, new Date());
		foodListB6.add(food13);
		foodListB6.add(food14);
		foodListB6.add(food15);
		stall6.setFoodList(foodListB6);

		Stall stall7 = new Stall("REX(Halal)", "123", "REX(Halal)", 62684806, null, new Date(),
				null);
		Food food16 = new Food("Chicken Rice", "Drumstick 3.50", 3.00, stall7, new Date());
		Food food17 = new Food("Chicken Fried Rice", "", 3.00, stall7, new Date());
		Food food18 = new Food("Seafood Fried Rice", "", 3.50, stall7, new Date());
		Food food19 = new Food("Beef Fried Rice", "", 4.00, stall7, new Date());
		Food food20 = new Food("Seafood Horfun(wet)", "", 3.50, stall7, new Date());
		Food food21 = new Food("Seafood Horfun(dry)", "", 4.00, stall7, new Date());
		Food food22 = new Food("Chicken Porridge", "", 3.00, stall7, new Date());
		Food food23 = new Food("Fish Porridge", "", 3.00, stall7, new Date());
		foodListB7.add(food16);
		foodListB7.add(food17);
		foodListB7.add(food18);
		foodListB7.add(food19);
		foodListB7.add(food20);
		foodListB7.add(food21);
		foodListB7.add(food22);
		foodListB7.add(food23);
		stall7.setFoodList(foodListB7);

		Stall stall8 = new Stall("Vegetarian Stall", "123", "Vegetarian Stall", 91182963, null,
				new Date(), null);
		Food food24 = new Food("Vegetarian Rice", "", 2.50, stall8, new Date());
		Food food25 = new Food("Vegetarian Beehoon", "", 2.50, stall8, new Date());
		Food food26 = new Food("Vegetarian Mee", "", 2.50, stall8, new Date());
		foodListB8.add(food24);
		foodListB8.add(food25);
		foodListB8.add(food26);
		stall8.setFoodList(foodListB8);

		Stall stall9 = new Stall("Minced Meat Noodles Stall(Closed On Tuesday)", "123",
				"Minced Meat Noodles Stall(Closed On Tuesday)", 93686070, null, new Date(), null);
		Food food27 = new Food("Minced Meat Noodles", "upsize 3.20", 2.70, stall9, new Date());
		foodListB9.add(food27);
		stall9.setFoodList(foodListB9);

		Stall stall10 = new Stall("Noodle Stall", "123", "Noodles Stall", 96946576, null,
				new Date(), null);
		Food food28 = new Food("Lor Mee", "", 3.00, stall10, new Date());
		Food food29 = new Food("Prawn Mee", "", 3.00, stall10, new Date());
		Food food30 = new Food("Fishball Noodles", "", 3.00, stall10, new Date());
		Food food31 = new Food("Laksa", "", 3.00, stall10, new Date());
		Food food32 = new Food("Hokkien Mee", "", 3.00, stall10, new Date());
		Food food33 = new Food("Dumpling Noodles", "", 3.00, stall10, new Date());
		foodListB10.add(food28);
		foodListB10.add(food29);
		foodListB10.add(food30);
		foodListB10.add(food31);
		foodListB10.add(food32);
		foodListB10.add(food33);
		stall10.setFoodList(foodListB10);

		Stall stall11 = new Stall("Fruit Stall", "123", "Fruit Stall", 91151608, null, new Date(),
				null);
		Food food34 = new Food("Apple", "", 0.60, stall11, new Date());
		Food food35 = new Food("Watermelon", "", 0.60, stall11, new Date());
		Food food36 = new Food("DragonFruit", "", 0.60, stall11, new Date());
		Food food37 = new Food("Pear", "", 0.60, stall11, new Date());
		Food food38 = new Food("Honeydew", "", 0.60, stall11, new Date());
		Food food39 = new Food("Papaya", "", 0.60, stall11, new Date());
		Food food40 = new Food("Pineapple", "", 0.60, stall11, new Date());
		Food food41 = new Food("Banana", "", 0.60, stall11, new Date());
		Food food42 = new Food("Agar Agar", "", 0.60, stall11, new Date());
		Food food43 = new Food("Orange", "", 0.60, stall11, new Date());
		Food food44 = new Food("Guava", "", 0.60, stall11, new Date());
		Food food45 = new Food("Sarawak Pineapple", "", 1.00, stall11, new Date());
		Food food46 = new Food("Mixed Fruits", "Upsize 3.50", 3.00, stall11, new Date());
		foodListB11.add(food34);
		foodListB11.add(food35);
		foodListB11.add(food36);
		foodListB11.add(food37);
		foodListB11.add(food38);
		foodListB11.add(food39);
		foodListB11.add(food40);
		foodListB11.add(food41);
		foodListB11.add(food42);
		foodListB11.add(food43);
		foodListB11.add(food44);
		foodListB11.add(food45);
		foodListB11.add(food46);
		stall11.setFoodList(foodListB11);

		Canteen canteen2 = new Canteen("Bedok", "123", new Date(), null);
		stall1.setCanteen(canteen2);
		stall2.setCanteen(canteen2);
		stall3.setCanteen(canteen2);
		stall4.setCanteen(canteen2);
		stall5.setCanteen(canteen2);
		stall6.setCanteen(canteen2);
		stall7.setCanteen(canteen2);
		stall8.setCanteen(canteen2);
		stall9.setCanteen(canteen2);
		stall10.setCanteen(canteen2);
		stall11.setCanteen(canteen2);

		stallList2.add(stall1);
		stallList2.add(stall2);
		stallList2.add(stall3);
		stallList2.add(stall4);
		stallList2.add(stall5);
		stallList2.add(stall6);
		stallList2.add(stall7);
		stallList2.add(stall8);
		stallList2.add(stall9);
		stallList2.add(stall10);
		stallList2.add(stall11);

		canteen2.setStallList(stallList2);

		Canteen canteen1 = new Canteen("jurongCanteen", "123", new Date(), null);

		Stall kuehStall = new Stall("kuehStall", "123", "OasisKuehStall", 90685620, canteen1,
				new Date(), null);
		Food kuehfood1 = new Food("Chee cheong fun", "damn nice", 0.60, kuehStall, new Date());
		Food kuehfood2 = new Food("Yam cake", "damn nice", 1.20, kuehStall, new Date());
		Food kuehfood3 = new Food("Dumpling", "damn nice", 0.90, kuehStall, new Date());
		Food kuehfood4 = new Food("Pau", "damn nice", 0.80, kuehStall, new Date());
		Food kuehfood5 = new Food("Lor Mai Kai", "damn nice", 1.50, kuehStall, new Date());
		Food kuehfood6 = new Food("Fan Choy", "damn nice", 1.80, kuehStall, new Date());
		// add food to list to the stall
		Set<Food> foodList1 = new HashSet<Food>();

		foodList1.add(kuehfood1);
		foodList1.add(kuehfood2);
		foodList1.add(kuehfood3);
		foodList1.add(kuehfood4);
		foodList1.add(kuehfood5);
		foodList1.add(kuehfood6);
		kuehStall.setFoodList(foodList1);

		Stall malayStall = new Stall("malayStall", "123", "OasisMalayStall", 93848341, canteen1,
				new Date(), null);
		Food mfood1 = new Food("mixed veg rice", "ask for more vegs, less fried meat", 3.70,
				malayStall, new Date());
		Set<Food> foodList2 = new HashSet<Food>();
		foodList2.add(mfood1);

		malayStall.setFoodList(foodList2);

		Stall indianStall = new Stall("indianStall", "123", "IndianStall", 98717752, canteen1,
				new Date(), null);
		Food infood1 = new Food("white rice", "damn nice", 4.00, indianStall, new Date());
		Food infood2 = new Food("vegetable white rice", "damn nice", 3.00, indianStall, new Date());
		Food infood3 = new Food("chicken Briyani", "daman nice", 5.00, indianStall, new Date());
		Food infood4 = new Food("fish Briyani", "daman nice", 5.00, indianStall, new Date());
		Food infood5 = new Food("mutton Briyani", "daman nice", 5.00, indianStall, new Date());

		Set<Food> foodList3 = new HashSet<Food>();
		foodList3.add(infood1);
		foodList3.add(infood2);
		foodList3.add(infood3);
		foodList3.add(infood4);
		foodList3.add(infood5);
		indianStall.setFoodList(foodList3);

		Stall chineseMixVegStall = new Stall("chineseStall", "123", "OasisChineseMixVegStall",
				93848341, canteen1, new Date(), null);
		Food mixVegRice1 = new Food("mix veg rice", "ask for more meat, less fried meat, $3.50",
				3.00, chineseMixVegStall, new Date());

		Set<Food> foodList4 = new HashSet<Food>();
		foodList4.add(mixVegRice1);
		chineseMixVegStall.setFoodList(foodList4);

		Stall roastMeatStall = new Stall("roastMeatStall", "123", "RoastMeatStall", 123, canteen1,
				new Date(), null);
		Food roastfood1 = new Food("roast chicken rice", "2 meat choices: $4", 3.00,
				roastMeatStall, new Date());
		Food roastfood2 = new Food("wanton mee", "daman nice", 3.20, roastMeatStall, new Date());
		Set<Food> foodList5 = new HashSet<Food>();
		foodList5.add(roastfood1);
		foodList5.add(roastfood2);

		roastMeatStall.setFoodList(foodList5);

		Stall seafoodTzeCharStall = new Stall("tzeCharStall", "123", "seafoodTzeCharStall",
				92262376, canteen1, new Date(), null);
		Food seafood1 = new Food("dry type", "damn nice", 4.00, seafoodTzeCharStall, new Date());
		Food seafood2 = new Food("hor fun", "damn nice", 3.70, seafoodTzeCharStall, new Date());
		Food seafood3 = new Food("fried rice", "damn nice", 4.00, seafoodTzeCharStall, new Date());
		Food seafood4 = new Food("daily soup with rice and egg", "damn nice", 4.80,
				seafoodTzeCharStall, new Date());

		Set<Food> foodList6 = new HashSet<Food>();
		foodList6.add(seafood1);
		foodList6.add(seafood2);
		foodList6.add(seafood3);
		foodList6.add(seafood4);

		seafoodTzeCharStall.setFoodList(foodList6);

		Stall fishBeehoonStall = new Stall("fishBeehoonStall", "123", "fishBeehoonStall", 98367790,
				canteen1, new Date(), null);
		Food fishBeehoonfood1 = new Food("beehoon", "add bittergourd: $0.50", 3.50,
				fishBeehoonStall, new Date());
		Food fishBeehoonfood2 = new Food("rice", "add bittergourd: $0.50", 4.00, fishBeehoonStall,
				new Date());
		Food fishBeehoonfood3 = new Food("fried fish", "add bittergourd: $0.50", 5.00,
				fishBeehoonStall, new Date());

		Set<Food> foodList7 = new HashSet<Food>();
		foodList7.add(fishBeehoonfood1);
		foodList7.add(fishBeehoonfood2);
		foodList7.add(fishBeehoonfood3);

		fishBeehoonStall.setFoodList(foodList7);

		Stall fruitStall = new Stall("fruitStall", "123", "fruitStall", 91151608, canteen1,
				new Date(), null);
		Food apple = new Food("apple", "damn nice", 0.60, fruitStall, new Date());
		Food watermelon = new Food("watermelon", "damn nice", 0.60, fruitStall, new Date());
		Food dragonfruit = new Food("dragonfruit", "damn nice", 0.60, fruitStall, new Date());
		Food pear = new Food("pear", "damn nice", 0.60, fruitStall, new Date());
		Food honeydew = new Food("honeydew", "damn nice", 0.70, fruitStall, new Date());
		Food papaya = new Food("papaya", "damn nice", 0.60, fruitStall, new Date());
		Food pineapple = new Food("pineapple", "damn nice", 0.60, fruitStall, new Date());
		Food banana = new Food("banana", "damn nice", 0.60, fruitStall, new Date());
		Food agaragar = new Food("agaragar", "damn nice", 0.60, fruitStall, new Date());
		Food orange = new Food("orange", "damn nice", 0.60, fruitStall, new Date());
		Food guava = new Food("guava", "damn nice", 0.70, fruitStall, new Date());
		Food sarawakPineapple = new Food("sarawakPineapple", "damn nice", 1.00, fruitStall,
				new Date());
		Food mixedFruits = new Food("mixedFruits", "damn nice", 3.00, fruitStall, new Date());
		Food largeMixedFruits = new Food("largeMixedFruits", "damn nice", 3.50, fruitStall,
				new Date());

		Set<Food> foodList8 = new HashSet<Food>();
		foodList8.add(apple);
		foodList8.add(watermelon);
		foodList8.add(dragonfruit);
		foodList8.add(pear);
		foodList8.add(honeydew);
		foodList8.add(papaya);
		foodList8.add(pineapple);
		foodList8.add(banana);
		foodList8.add(agaragar);
		foodList8.add(orange);
		foodList8.add(guava);
		foodList8.add(sarawakPineapple);
		foodList8.add(mixedFruits);
		foodList8.add(largeMixedFruits);
		fruitStall.setFoodList(foodList8);

		// Stall List
		Set<Stall> newStallList = new HashSet<Stall>();
		newStallList.add(kuehStall);
		newStallList.add(malayStall);
		newStallList.add(indianStall);
		newStallList.add(chineseMixVegStall);
		newStallList.add(roastMeatStall);
		newStallList.add(seafoodTzeCharStall);
		newStallList.add(fishBeehoonStall);
		newStallList.add(fruitStall);

		canteen1.setStallList(newStallList);
		// End of insert mock Data

		Admin admin = new Admin("admin", "123", "admin123", 123, new Date());
		Company company = new Company("xiaodingdang co.", null, new Date(), null, null);
		Employee employee = new Employee("arnold123", "123", "arnold", 123, 10, 123, company, null,
				null, new Date());
		FoodOrder order = new FoodOrder("done", employee, admin, null, new Date());
		FoodOrderItem foodItem = new FoodOrderItem(order, food, 2, 2.5, "remarks", new Date());

		Set<Canteen> canteenList = new HashSet<>();
		canteenList.add(canteen);
		company.setCanteenList(canteenList);
		Set<FoodOrder> orderList = new HashSet<>();
		orderList.add(order);
		employee.setOrderHistory(orderList);
		Set<FoodOrderItem> foodOrderList = new HashSet<>();
		foodOrderList.add(foodItem);
		order.setFoodOrderList(foodOrderList);
		Set<Employee> employeeList = new HashSet<>();
		employeeList.add(employee);
		company.setEmployeeList(employeeList);
		Set<Food> foodList = new HashSet<>();
		employee.setFavouriteList(foodList);
		foodList.add(food);
		Set<Stall> stallList = new HashSet<>();
		stallList.add(stall);
		stall.setFoodList(foodList);
		canteen.setStallList(stallList);

		MyConnection.save(admin);
		MyConnection.save(company);
		MyConnection.save(employee);
		MyConnection.save(order);
		MyConnection.save(foodItem);

		MyConnection.save(canteen1);

		MyConnection.save(kuehStall);
		MyConnection.save(malayStall);
		MyConnection.save(indianStall);
		MyConnection.save(chineseMixVegStall);
		MyConnection.save(roastMeatStall);
		MyConnection.save(seafoodTzeCharStall);
		MyConnection.save(fishBeehoonStall);
		MyConnection.save(fruitStall);

		MyConnection.save(kuehfood1);
		MyConnection.save(kuehfood2);
		MyConnection.save(kuehfood3);
		MyConnection.save(kuehfood4);
		MyConnection.save(kuehfood5);
		MyConnection.save(kuehfood6);

		MyConnection.save(mfood1);

		MyConnection.save(infood1);
		MyConnection.save(infood2);
		MyConnection.save(infood3);
		MyConnection.save(infood4);
		MyConnection.save(infood5);

		MyConnection.save(mixVegRice1);

		MyConnection.save(roastfood1);
		MyConnection.save(roastfood2);

		MyConnection.save(seafood1);
		MyConnection.save(seafood2);
		MyConnection.save(seafood3);
		MyConnection.save(seafood4);

		MyConnection.save(fishBeehoonfood1);
		MyConnection.save(fishBeehoonfood2);
		MyConnection.save(fishBeehoonfood3);

		MyConnection.save(apple);
		MyConnection.save(watermelon);
		MyConnection.save(dragonfruit);
		MyConnection.save(pear);
		MyConnection.save(honeydew);
		MyConnection.save(papaya);
		MyConnection.save(pineapple);
		MyConnection.save(banana);
		MyConnection.save(agaragar);
		MyConnection.save(orange);
		MyConnection.save(guava);
		MyConnection.save(sarawakPineapple);
		MyConnection.save(mixedFruits);
		MyConnection.save(largeMixedFruits);

		MyConnection.save(canteen2);
		MyConnection.save(stall1);
		MyConnection.save(stall2);
		MyConnection.save(stall3);
		MyConnection.save(stall4);
		MyConnection.save(stall5);
		MyConnection.save(stall6);
		MyConnection.save(stall7);
		MyConnection.save(stall8);
		MyConnection.save(stall9);
		MyConnection.save(stall10);
		MyConnection.save(stall11);
		MyConnection.save(food1);
		MyConnection.save(food2);
		MyConnection.save(food3);
		MyConnection.save(food4);
		MyConnection.save(food5);
		MyConnection.save(food6);
		MyConnection.save(food7);
		MyConnection.save(food8);
		MyConnection.save(food9);
		MyConnection.save(food10);
		MyConnection.save(food11);
		MyConnection.save(food12);
		MyConnection.save(food13);
		MyConnection.save(food14);
		MyConnection.save(food15);
		MyConnection.save(food16);
		MyConnection.save(food17);
		MyConnection.save(food18);
		MyConnection.save(food19);
		MyConnection.save(food20);
		MyConnection.save(food21);
		MyConnection.save(food22);
		MyConnection.save(food23);
		MyConnection.save(food24);
		MyConnection.save(food25);
		MyConnection.save(food26);
		MyConnection.save(food27);
		MyConnection.save(food28);
		MyConnection.save(food29);
		MyConnection.save(food30);
		MyConnection.save(food31);
		MyConnection.save(food32);
		MyConnection.save(food33);
		MyConnection.save(food34);
		MyConnection.save(food35);
		MyConnection.save(food36);
		MyConnection.save(food37);
		MyConnection.save(food38);
		MyConnection.save(food39);
		MyConnection.save(food40);
		MyConnection.save(food41);
		MyConnection.save(food42);
		MyConnection.save(food43);
		MyConnection.save(food44);
		MyConnection.save(food45);
		MyConnection.save(food46);

		System.out.println("Test.java completed");

	}

}
