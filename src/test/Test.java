package test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import services.PasswordService;
import value.StringValues;
import model.Admin;
import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Modifier;
import model.Stall;
import connection.MyConnection;

public class Test {

	public static void main(String[] args) {

		// **************************************** Arnold Data
		// ****************************************

		Canteen canteen = new Canteen("Default Canteen", "123", new Date(), null);
		Stall stall = new Stall("stall123", "123", "Default Stall", 123, canteen, new Date(), null);
		Food food = new Food("Arnold's Fried Chicken", "Quite Nice", 2.50, stall, new Date());

		Admin admin = new Admin("admin", PasswordService.encryptPassword("1234567"), "admin123", 123456789, new Date());
		Company company = new Company("XiaoDingDang Co.", null, new Date(), null, null);
		Employee employee = new Employee("arnold", PasswordService.encryptPassword("1234567"), "arnold", 999999999, 10,
				123, company, null, null, new Date());
		FoodOrder order = new FoodOrder(StringValues.ORDER_CONFIRMED, employee, admin, null, new Date());
		FoodOrderItem foodItem = new FoodOrderItem(order, food, 1, "More meat", new Date());

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
		// MyConnection.save(employee);
		MyConnection.save(order);
		// MyConnection.save(foodItem);
		// **************************************** End Arnold Data
		// ****************************************

		// **************************************** Insert mock Data
		// ****************************************
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

		Stall stall1 = new Stall("Sliced Fish Bee Hoon Stall", "123", "Fish Slice Bee Hoon", 91379160, null, new Date(),
				null);
		Food food1 = new Food("Fish Slice Bee Hoon", "", 3.70, stall1, new Date());
		Food food2 = new Food("Fish Soup and Rice", "", 4.70, stall1, new Date());
		foodListB1.add(food1);
		foodListB1.add(food2);
		stall1.setFoodList(foodListB1);

		Stall stall2 = new Stall("Malay food Stall", "123", "Malay food Stall", 81145966, null, new Date(), null);
		Food food3 = new Food("Malay Chicken List", "", 3.00, stall2, new Date());
		Food food4 = new Food("Malay Fish List", "", 3.00, stall2, new Date());
		Food food5 = new Food("Malay Mutton List", "", 3.00, stall2, new Date());

		Modifier modifierA3 = new Modifier("Up size", "", 0.50, food3, new Date());
		Set<Modifier> modifierListA1 = new HashSet<Modifier>();
		modifierListA1.add(modifierA3);
		food3.setModifierList(modifierListA1);

		Modifier modifierA4 = new Modifier("Up size", "", 0.50, food4, new Date());
		Set<Modifier> modifierListA2 = new HashSet<Modifier>();
		modifierListA2.add(modifierA4);
		food4.setModifierList(modifierListA2);

		Modifier modifierA5 = new Modifier("Up size", "", 0.50, food5, new Date());
		Set<Modifier> modifierListA3 = new HashSet<Modifier>();
		modifierListA3.add(modifierA5);
		food5.setModifierList(modifierListA3);

		foodListB2.add(food3);
		foodListB2.add(food4);
		foodListB2.add(food5);
		stall2.setFoodList(foodListB2);

		Stall stall3 = new Stall("Mixed Rice Stall", "123", "Mixed Rice Stall", 93482772, null, new Date(), null);
		Food food6 = new Food("Mixed Rice", "", 3.00, stall3, new Date());
		Food food7 = new Food("Porridge", "", 3.00, stall3, new Date());
		foodListB3.add(food6);
		foodListB3.add(food7);
		stall3.setFoodList(foodListB3);

		Stall stall4 = new Stall("Wanton Mee Stall", "123", "Wanton Mee Stall", 0, null, new Date(), foodListB4);
		Food food8 = new Food("Wanton Mee", "", 3.00, stall4, new Date());
		foodListB4.add(food8);
		stall4.setFoodList(foodListB4);

		Stall stall5 = new Stall("Indian Food Stall", "123", "Indian Food Stall", 93841009, null, new Date(), null);
		Food food9 = new Food("Indian Chicken List", "", 3.50, stall5, new Date());
		Food food10 = new Food("Indian Fish List", "", 3.50, stall5, new Date());
		Food food11 = new Food("Mutton Briyani", "", 4.00, stall5, new Date());
		Food food12 = new Food("Ayam Penyat", "", 4.00, stall5, new Date());
		foodListB5.add(food9);
		foodListB5.add(food10);
		foodListB5.add(food11);
		foodListB5.add(food12);
		stall5.setFoodList(foodListB5);

		Stall stall6 = new Stall("Roast Duck & Chicken Rice Stall", "123", "Roast Duck & Chicken Rice Stall", 98427347,
				null, new Date(), null);
		Food food13 = new Food("Roast Chicken Rice", "", 2.50, stall6, new Date());
		Food food14 = new Food("CharSiew Rice", "", 2.50, stall6, new Date());
		Food food15 = new Food("Roast Meat Rice", "", 2.50, stall6, new Date());
		Food foodC16 = new Food("CharSiew + Roast Meat Rice", "", 3.00, stall6, new Date());
		foodListB6.add(foodC16);
		foodListB6.add(food13);
		foodListB6.add(food14);
		foodListB6.add(food15);
		stall6.setFoodList(foodListB6);

		Stall stall7 = new Stall("REX(Halal)", "123", "REX(Halal)", 62684806, null, new Date(), null);
		Food food16 = new Food("Chicken Rice", "", 3.00, stall7, new Date());
		Food food17 = new Food("Chicken Fried Rice", "", 3.00, stall7, new Date());
		Food food18 = new Food("Seafood Fried Rice", "", 3.50, stall7, new Date());
		Food food19 = new Food("Beef Fried Rice", "", 4.00, stall7, new Date());
		Food food20 = new Food("Seafood Horfun(wet)", "", 3.50, stall7, new Date());
		Food food21 = new Food("Seafood Horfun(dry)", "", 4.00, stall7, new Date());
		Food food22 = new Food("Chicken Porridge", "", 3.00, stall7, new Date());
		Food food23 = new Food("Fish Porridge", "", 3.00, stall7, new Date());

		Modifier modifierA16 = new Modifier("Change to drumstick", "", 0.50, food16, new Date());
		Set<Modifier> modifierListA16 = new HashSet<Modifier>();
		modifierListA16.add(modifierA16);
		food16.setModifierList(modifierListA16);

		foodListB7.add(food16);
		foodListB7.add(food17);
		foodListB7.add(food18);
		foodListB7.add(food19);
		foodListB7.add(food20);
		foodListB7.add(food21);
		foodListB7.add(food22);
		foodListB7.add(food23);
		stall7.setFoodList(foodListB7);

		Stall stall8 = new Stall("Vegetarian Stall", "123", "Vegetarian Stall", 91182963, null, new Date(), null);
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

		Modifier modifierA27 = new Modifier("upsize", "", 0.50, food27, new Date());
		Set<Modifier> modifierListA27 = new HashSet<Modifier>();
		modifierListA16.add(modifierA27);
		food5.setModifierList(modifierListA27);

		foodListB9.add(food27);
		stall9.setFoodList(foodListB9);

		Stall stall10 = new Stall("Noodle Stall", "123", "Noodles Stall", 96946576, null, new Date(), null);
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

		Stall stall11 = new Stall("Fruit Stall", "123", "Fruit Stall", 91151608, null, new Date(), null);
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

		Canteen canteen1 = new Canteen("Jurong Canteen", "123", new Date(), null);

		Stall kuehStall = new Stall("Kueh Stall", "123", "Oasis Kueh Stall", 90685620, canteen1, new Date(), null);
		Food kuehfood1 = new Food("Chee Cheong Fun", "", 0.60, kuehStall, new Date());
		Food kuehfood2 = new Food("Yam cake", "", 1.20, kuehStall, new Date());
		Food kuehfood3 = new Food("Dumpling", "", 0.90, kuehStall, new Date());
		Food kuehfood4 = new Food("Pau", "", 0.80, kuehStall, new Date());
		Food kuehfood5 = new Food("Lor Mai Kai", "", 1.50, kuehStall, new Date());
		Food kuehfood6 = new Food("Fan Choy", "", 1.80, kuehStall, new Date());
		// add food to list to the stall
		Set<Food> foodList1 = new HashSet<Food>();

		foodList1.add(kuehfood1);
		foodList1.add(kuehfood2);
		foodList1.add(kuehfood3);
		foodList1.add(kuehfood4);
		foodList1.add(kuehfood5);
		foodList1.add(kuehfood6);
		kuehStall.setFoodList(foodList1);

		Stall malayStall = new Stall("Malay Stall", "123", "Oasis Malay Stall", 93848341, canteen1, new Date(), null);
		Food mfood1 = new Food("Mixed Veg Rice", "ask for more vegs, less fried meat", 3.70, malayStall, new Date());

		// for mfood1 modifiers
		Modifier modifier1 = new Modifier("Ask for more vegetables", "", 0.00, mfood1, new Date());
		Modifier modifier2 = new Modifier("Ask for less fried meat", "", 0.00, mfood1, new Date());

		Set<Modifier> modifierList1 = new HashSet<Modifier>();
		modifierList1.add(modifier1);
		modifierList1.add(modifier2);
		mfood1.setModifierList(modifierList1);

		Set<Food> foodList2 = new HashSet<Food>();
		foodList2.add(mfood1);

		malayStall.setFoodList(foodList2);

		Stall indianStall = new Stall("Indian Stall", "123", "Indian Stall", 98717752, canteen1, new Date(), null);
		Food infood1 = new Food("White Rice", "Chicken/Fish/Mutton", 4.00, indianStall, new Date());
		Food infood2 = new Food("Vegetable White Rice", "", 3.00, indianStall, new Date());
		Food infood3 = new Food("Briyani", "Chicken/Fish/Mutton", 5.00, indianStall, new Date());

		// for infood1 modifiers
		Modifier modifier3 = new Modifier("Chicken", "", 0.00, infood1, new Date());
		Modifier modifier4 = new Modifier("Fish", "", 0.00, infood1, new Date());
		Modifier modifier5 = new Modifier("Mutton", "", 0.00, infood1, new Date());

		Set<Modifier> modifierList2 = new HashSet<Modifier>();
		modifierList2.add(modifier3);
		modifierList2.add(modifier4);
		modifierList2.add(modifier5);
		infood1.setModifierList(modifierList2);

		// for infood3 modifiers
		Modifier modifier6 = new Modifier("Chicken", "", 0.00, infood3, new Date());
		Modifier modifier7 = new Modifier("Fish", "", 0.00, infood3, new Date());
		Modifier modifier8 = new Modifier("Mutton", "", 0.00, infood3, new Date());

		Set<Modifier> modifierList3 = new HashSet<Modifier>();
		modifierList3.add(modifier6);
		modifierList3.add(modifier7);
		modifierList3.add(modifier8);
		infood3.setModifierList(modifierList3);

		Set<Food> foodList3 = new HashSet<Food>();
		foodList3.add(infood1);
		foodList3.add(infood2);
		foodList3.add(infood3);
		indianStall.setFoodList(foodList3);

		Stall chineseMixVegStall = new Stall("Chinese Stall", "123", "Oasis Chinese Mix Veg Stall", 93848341, canteen1,
				new Date(), null);
		Food mixVegRice1 = new Food("Mix Veg Rice", "ask for more meat, less fried meat, or upsize to $3.50", 3.00,
				chineseMixVegStall, new Date());

		// for mixVegRice1 modifiers
		Modifier modifier9 = new Modifier("Ask for more vegetables", "", 0.00, mixVegRice1, new Date());
		Modifier modifier10 = new Modifier("Ask for less fried meat", "", 0.00, mixVegRice1, new Date());
		Modifier modifier11 = new Modifier("Upsize to $3.50", "", 0.50, mixVegRice1, new Date());

		Set<Modifier> modifierList4 = new HashSet<Modifier>();
		modifierList4.add(modifier9);
		modifierList4.add(modifier10);
		modifierList4.add(modifier11);
		mixVegRice1.setModifierList(modifierList4);

		Set<Food> foodList4 = new HashSet<Food>();
		foodList4.add(mixVegRice1);
		chineseMixVegStall.setFoodList(foodList4);

		Stall roastMeatStall = new Stall("Roast Meat Stall", "123", "Roast Meat Stall", 123, canteen1, new Date(),
				null);
		Food roastfood1 = new Food("Roast Chicken Rice", "2 meat choices $4", 3.00, roastMeatStall, new Date());
		Food roastfood2 = new Food("Wanton Mee", "", 3.20, roastMeatStall, new Date());
		Food roastfood3 = new Food("CharSiew Rice", "2 meat choices $4", 3.00, roastMeatStall, new Date());
		Food roastfood4 = new Food("Roast Meat Rice", "2 meat choices $4", 3.00, roastMeatStall, new Date());

		// for roastfood1 modifiers
		Modifier modifier12 = new Modifier("Add charsiew", "", 1.00, roastfood1, new Date());
		Modifier modifier13 = new Modifier("Add roast meat", "", 1.00, roastfood1, new Date());

		Set<Modifier> modifierList5 = new HashSet<Modifier>();
		modifierList5.add(modifier12);
		modifierList5.add(modifier13);
		roastfood1.setModifierList(modifierList5);

		// for roastfood3 modifiers
		Modifier modifier14 = new Modifier("Add roast chicken", "", 1.00, roastfood3, new Date());
		Modifier modifier15 = new Modifier("Add roast meat", "", 1.00, roastfood3, new Date());

		Set<Modifier> modifierList6 = new HashSet<Modifier>();
		modifierList6.add(modifier14);
		modifierList6.add(modifier15);
		roastfood3.setModifierList(modifierList6);

		// for roastfood4 modifiers
		Modifier modifier16 = new Modifier("Add charsiew", "", 1.00, roastfood4, new Date());
		Modifier modifier17 = new Modifier("Add roast chicken", "", 1.00, roastfood4, new Date());

		Set<Modifier> modifierList7 = new HashSet<Modifier>();
		modifierList7.add(modifier16);
		modifierList7.add(modifier17);
		roastfood4.setModifierList(modifierList7);

		Set<Food> foodList5 = new HashSet<Food>();
		foodList5.add(roastfood1);
		foodList5.add(roastfood2);
		foodList5.add(roastfood3);
		foodList5.add(roastfood4);
		roastMeatStall.setFoodList(foodList5);

		Stall seafoodTzeCharStall = new Stall("Tze Char Stall", "123", "Seafood Tze Char Stall", 92262376, canteen1,
				new Date(), null);

		Food seafood1 = new Food("Hor Fun", "dry type $4.00", 3.70, seafoodTzeCharStall, new Date());
		Food seafood2 = new Food("Fried Rice", "", 4.00, seafoodTzeCharStall, new Date());
		Food seafood3 = new Food("Daily Soup With Rice And Egg", "", 4.80, seafoodTzeCharStall, new Date());
		Food seafood4 = new Food("Hokkien Noodle", "", 4.20, seafoodTzeCharStall, new Date());
		Food seafood5 = new Food("Mee Goreng", "", 4.20, seafoodTzeCharStall, new Date());

		// for seafood1 modifier
		Modifier modifier18 = new Modifier("Dry type", "", 0.30, seafood1, new Date());
		Modifier modifier19 = new Modifier("Ask for more vegetables", "", 0.00, seafood1, new Date());

		Set<Modifier> modifierList8 = new HashSet<Modifier>();
		modifierList8.add(modifier18);
		modifierList8.add(modifier19);
		seafood1.setModifierList(modifierList8);

		// for seafood4 modifier
		Modifier modifier20 = new Modifier("Ask for more vegetables", "", 0.00, seafood4, new Date());

		Set<Modifier> modifierList9 = new HashSet<Modifier>();
		modifierList9.add(modifier20);
		seafood4.setModifierList(modifierList9);

		// for seafood5 modifier
		Modifier modifier21 = new Modifier("Ask for more vegetables", "", 0.00, seafood5, new Date());

		Set<Modifier> modifierList10 = new HashSet<Modifier>();
		modifierList10.add(modifier21);
		seafood5.setModifierList(modifierList10);

		Set<Food> foodList6 = new HashSet<Food>();
		foodList6.add(seafood1);
		foodList6.add(seafood2);
		foodList6.add(seafood3);
		foodList6.add(seafood4);
		foodList6.add(seafood5);
		seafoodTzeCharStall.setFoodList(foodList6);

		Stall fishBeehoonStall = new Stall("Fish Bee Hoon Stall", "123", "Fish Beehoon Stall", 98367790, canteen1,
				new Date(), null);
		Food fishBeehoonfood1 = new Food("Fish Soup With Bee Hoon", "add bittergourd: $0.50", 3.50, fishBeehoonStall,
				new Date());
		Food fishBeehoonfood2 = new Food("Fish Soup With Rice", "add bittergourd: $0.50", 4.00, fishBeehoonStall,
				new Date());
		Food fishBeehoonfood3 = new Food("Fried Fish Soup With Bee Hoon", "add bittergourd: $0.50", 5.00,
				fishBeehoonStall, new Date());

		// for fishBeehoonfood1 modifier
		Modifier modifier22 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood1, new Date());
		Set<Modifier> modifierList11 = new HashSet<Modifier>();
		modifierList11.add(modifier22);
		fishBeehoonfood1.setModifierList(modifierList11);

		// for fishBeehoonfood2 modifier
		Modifier modifier23 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood2, new Date());
		Set<Modifier> modifierList12 = new HashSet<Modifier>();
		modifierList12.add(modifier23);
		fishBeehoonfood2.setModifierList(modifierList12);

		// for fishBeehoonfood3 modifier
		Modifier modifier24 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood3, new Date());
		Set<Modifier> modifierList13 = new HashSet<Modifier>();
		modifierList13.add(modifier24);
		fishBeehoonfood3.setModifierList(modifierList13);

		Set<Food> foodList7 = new HashSet<Food>();
		foodList7.add(fishBeehoonfood1);
		foodList7.add(fishBeehoonfood2);
		foodList7.add(fishBeehoonfood3);

		fishBeehoonStall.setFoodList(foodList7);

		Stall fruitStall = new Stall("Fruit Stall", "123", "Fruit Stall", 91151608, canteen1, new Date(), null);
		Food apple = new Food("Apple", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food watermelon = new Food("Watermelon", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food dragonfruit = new Food("Dragonfruit", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food pear = new Food("Pear", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food honeydew = new Food("Honeydew", "change to juice $2.50", 0.70, fruitStall, new Date());
		Food papaya = new Food("Papaya", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food pineapple = new Food("Pineapple", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food banana = new Food("Banana", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food agaragar = new Food("Agar Agar", "", 0.60, fruitStall, new Date());
		Food orange = new Food("Orange", "change to juice $2.50", 0.60, fruitStall, new Date());
		Food guava = new Food("Guava", "change to juice $2.50", 0.70, fruitStall, new Date());
		Food sarawakPineapple = new Food("Sarawak Pineapple", "change to juice $2.50", 1.00, fruitStall, new Date());
		Food mixedFruits = new Food("Mixed Fruits", "upsize $3.50", 3.00, fruitStall, new Date());

		Modifier modifier25 = new Modifier("Change to juice", "", 1.90, apple, new Date());
		Set<Modifier> modifierList14 = new HashSet<Modifier>();
		modifierList14.add(modifier25);
		apple.setModifierList(modifierList14);

		Modifier modifier26 = new Modifier("Change to juice", "", 1.90, watermelon, new Date());
		Set<Modifier> modifierList15 = new HashSet<Modifier>();
		modifierList15.add(modifier26);
		watermelon.setModifierList(modifierList14);

		Modifier modifier27 = new Modifier("Change to juice", "", 1.90, dragonfruit, new Date());
		Set<Modifier> modifierList16 = new HashSet<Modifier>();
		modifierList16.add(modifier27);
		dragonfruit.setModifierList(modifierList16);

		Modifier modifier28 = new Modifier("Change to juice", "", 1.90, pear, new Date());
		Set<Modifier> modifierList17 = new HashSet<Modifier>();
		modifierList17.add(modifier28);
		pear.setModifierList(modifierList17);

		Modifier modifier29 = new Modifier("Change to juice", "", 1.80, honeydew, new Date());
		Set<Modifier> modifierList18 = new HashSet<Modifier>();
		modifierList18.add(modifier29);
		honeydew.setModifierList(modifierList18);

		Modifier modifier30 = new Modifier("Change to juice", "", 1.90, papaya, new Date());
		Set<Modifier> modifierList19 = new HashSet<Modifier>();
		modifierList19.add(modifier30);
		papaya.setModifierList(modifierList19);

		Modifier modifier31 = new Modifier("Change to juice", "", 1.90, pineapple, new Date());
		Set<Modifier> modifierList20 = new HashSet<Modifier>();
		modifierList20.add(modifier31);
		pineapple.setModifierList(modifierList20);

		Modifier modifier32 = new Modifier("Change to juice", "", 1.90, banana, new Date());
		Set<Modifier> modifierList21 = new HashSet<Modifier>();
		modifierList21.add(modifier32);
		banana.setModifierList(modifierList21);

		Modifier modifier33 = new Modifier("Change to juice", "", 1.90, orange, new Date());
		Set<Modifier> modifierList22 = new HashSet<Modifier>();
		modifierList22.add(modifier33);
		orange.setModifierList(modifierList22);

		Modifier modifier34 = new Modifier("Change to juice", "", 1.80, guava, new Date());
		Set<Modifier> modifierList23 = new HashSet<Modifier>();
		modifierList23.add(modifier34);
		guava.setModifierList(modifierList23);

		Modifier modifier35 = new Modifier("Change to juice", "", 1.90, sarawakPineapple, new Date());
		Set<Modifier> modifierList24 = new HashSet<Modifier>();
		modifierList24.add(modifier35);
		sarawakPineapple.setModifierList(modifierList24);

		Modifier modifier36 = new Modifier("Mixed fruits upsize", "", 0.50, mixedFruits, new Date());
		Set<Modifier> modifierList25 = new HashSet<Modifier>();
		modifierList25.add(modifier36);
		mixedFruits.setModifierList(modifierList25);

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

		MyConnection.save(mixVegRice1);

		MyConnection.save(roastfood1);
		MyConnection.save(roastfood2);
		MyConnection.save(roastfood3);
		MyConnection.save(roastfood4);

		MyConnection.save(seafood1);
		MyConnection.save(seafood2);
		MyConnection.save(seafood3);
		MyConnection.save(seafood4);
		MyConnection.save(seafood5);

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

		// saving of the modifiers into database
		MyConnection.save(modifier1);
		MyConnection.save(modifier2);
		MyConnection.save(modifier3);
		MyConnection.save(modifier4);
		MyConnection.save(modifier5);
		MyConnection.save(modifier6);
		MyConnection.save(modifier7);
		MyConnection.save(modifier8);
		MyConnection.save(modifier9);
		MyConnection.save(modifier10);
		MyConnection.save(modifier11);
		MyConnection.save(modifier12);
		MyConnection.save(modifier13);
		MyConnection.save(modifier14);
		MyConnection.save(modifier15);
		MyConnection.save(modifier16);
		MyConnection.save(modifier17);
		MyConnection.save(modifier18);
		MyConnection.save(modifier19);
		MyConnection.save(modifier20);
		MyConnection.save(modifier21);
		MyConnection.save(modifier22);
		MyConnection.save(modifier23);
		MyConnection.save(modifier24);
		MyConnection.save(modifier25);
		MyConnection.save(modifier26);
		MyConnection.save(modifier27);
		MyConnection.save(modifier28);
		MyConnection.save(modifier29);
		MyConnection.save(modifier30);
		MyConnection.save(modifier31);
		MyConnection.save(modifier32);
		MyConnection.save(modifier33);
		MyConnection.save(modifier34);
		MyConnection.save(modifier35);
		MyConnection.save(modifier36);

		MyConnection.save(modifierA3);
		MyConnection.save(modifierA4);
		MyConnection.save(modifierA5);
		MyConnection.save(modifierA16);
		MyConnection.save(modifierA27);

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

		// **************************************** End insert of mock data
		// ****************************************

		System.out.println("Test.java completed");

	}

}
