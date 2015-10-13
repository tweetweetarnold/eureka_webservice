package test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import model.Admin;
import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Modifier;
import model.ModifierChosen;
import model.Stall;
import services.PasswordService;
import value.StringValues;
import connection.MyConnection;

public class Test {

	public static void main(String[] args) {

		// **************************************** Arnold Data
		// ****************************************

		Session session = MyConnection.getSession();
		session.beginTransaction();

		Canteen canteen = new Canteen("Default Canteen", "123", new Date(), null);
		Stall stall = new Stall("stall123", "123", "Default Stall", 123, canteen, new Date(), null);
		Food food = new Food("Arnold's Fried Chicken", "Quite Nice", 2.50, null, stall, new Date());

		Admin admin = new Admin("admin", PasswordService.encryptPassword("1234567"), "admin123",
				123456789, new Date());
		Company company = new Company("XiaoDingDang Co.", null, new Date(), null, null);
		Employee employee = new Employee("arnold", PasswordService.encryptPassword("1234567"),
				"Arnold Lee", 12345678, 10, 123, company, null, null, new Date());
		FoodOrder order = new FoodOrder(StringValues.ORDER_CONFIRMED, employee, null, new Date());
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

		Modifier m1 = new Modifier("m1", "nice", 2.3, food, new Date());
		Modifier m2 = new Modifier("m2", "nice", 2.3, food, new Date());
		HashSet<Modifier> set = new HashSet<>();
		set.add(m1);
		set.add(m2);
		food.setModifierList(set);

		ModifierChosen mc1 = new ModifierChosen("mc1", "nicenice", 2.33, foodItem, new Date());
		ModifierChosen mc2 = new ModifierChosen("mc2", "nicenice", 2.33, foodItem, new Date());

		HashSet<ModifierChosen> set2 = new HashSet<>();
		set2.add(mc1);
		set2.add(mc2);
		foodItem.setModifierChosenList(set2);

		session.save(admin);
		session.save(company);
		session.save(employee);
		session.save(order);
		session.save(foodItem);

//		session.getTransaction().commit();
//		session.close();

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

		Stall stall1 = new Stall("Sliced Fish Bee Hoon Stall", "123", "Fish Slice Bee Hoon",
				91379160, null, new Date(), null);
		Food food1 = new Food("Fish Slice Bee Hoon", "", 3.70, null, stall1, new Date());
		Food food2 = new Food("Fish Soup and Rice", "", 4.70, null, stall1, new Date());
		foodListB1.add(food1);
		foodListB1.add(food2);
		stall1.setFoodList(foodListB1);

		Stall stall2 = new Stall("Malay food Stall", "123", "Malay food Stall", 81145966, null,
				new Date(), null);
		Food food3 = new Food("Malay Chicken List", "", 3.00, null, stall2, new Date());
		Food food4 = new Food("Malay Fish List", "", 3.00, null, stall2, new Date());
		Food food5 = new Food("Malay Mutton List", "", 3.00, null, stall2, new Date());

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

		Stall stall3 = new Stall("Mixed Rice Stall", "123", "Mixed Rice Stall", 93482772, null,
				new Date(), null);
		Food food6 = new Food("Mixed Rice", "", 3.00, null, stall3, new Date());
		Food food7 = new Food("Porridge", "", 3.00, null, stall3, new Date());
		foodListB3.add(food6);
		foodListB3.add(food7);
		stall3.setFoodList(foodListB3);

		Stall stall4 = new Stall("Wanton Mee Stall", "123", "Wanton Mee Stall", 0, null,
				new Date(), foodListB4);
		Food food8 = new Food("Wanton Mee", "", 3.00, null, stall4, new Date());
		foodListB4.add(food8);
		stall4.setFoodList(foodListB4);

		Stall stall5 = new Stall("Indian Food Stall", "123", "Indian Food Stall", 93841009, null,
				new Date(), null);
		Food food9 = new Food("Indian Chicken List", "", 3.50, null, stall5, new Date());
		Food food10 = new Food("Indian Fish List", "", 3.50, null, stall5, new Date());
		Food food11 = new Food("Mutton Briyani", "", 4.00, null, stall5, new Date());
		Food food12 = new Food("Ayam Penyat", "", 4.00, null, stall5, new Date());
		foodListB5.add(food9);
		foodListB5.add(food10);
		foodListB5.add(food11);
		foodListB5.add(food12);
		stall5.setFoodList(foodListB5);

		Stall stall6 = new Stall("Roast Duck & Chicken Rice Stall", "123",
				"Roast Duck & Chicken Rice Stall", 98427347, null, new Date(), null);
		Food food13 = new Food("Roast Chicken Rice", "", 2.50, null, stall6, new Date());
		Food food14 = new Food("CharSiew Rice", "", 2.50, null, stall6, new Date());
		Food food15 = new Food("Roast Meat Rice", "", 2.50, null, stall6, new Date());
		Food foodC16 = new Food("CharSiew + Roast Meat Rice", "", 3.00, null, stall6, new Date());
		foodListB6.add(foodC16);
		foodListB6.add(food13);
		foodListB6.add(food14);
		foodListB6.add(food15);
		stall6.setFoodList(foodListB6);

		Stall stall7 = new Stall("REX(Halal)", "123", "REX(Halal)", 62684806, null, new Date(),
				null);
		Food food16 = new Food("Chicken Rice", "", 3.00, null, stall7, new Date());
		Food food17 = new Food("Chicken Fried Rice", "", 3.00, null, stall7, new Date());
		Food food18 = new Food("Seafood Fried Rice", "", 3.50, null, stall7, new Date());
		Food food19 = new Food("Beef Fried Rice", "", 4.00, null, stall7, new Date());
		Food food20 = new Food("Seafood Horfun(wet)", "", 3.50, null, stall7, new Date());
		Food food21 = new Food("Seafood Horfun(dry)", "", 4.00, null, stall7, new Date());
		Food food22 = new Food("Chicken Porridge", "", 3.00, null, stall7, new Date());
		Food food23 = new Food("Fish Porridge", "", 3.00, null, stall7, new Date());

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

		Stall stall8 = new Stall("Vegetarian Stall", "123", "Vegetarian Stall", 91182963, null,
				new Date(), null);
		Food food24 = new Food("Vegetarian Rice", "", 2.50, null, stall8, new Date());
		Food food25 = new Food("Vegetarian Beehoon", "", 2.50, null, stall8, new Date());
		Food food26 = new Food("Vegetarian Mee", "", 2.50, null, stall8, new Date());
		foodListB8.add(food24);
		foodListB8.add(food25);
		foodListB8.add(food26);
		stall8.setFoodList(foodListB8);

		Stall stall9 = new Stall("Minced Meat Noodles Stall(Closed On Tuesday)", "123",
				"Minced Meat Noodles Stall(Closed On Tuesday)", 93686070, null, new Date(), null);
		Food food27 = new Food("Minced Meat Noodles", "upsize 3.20", 2.70, null, stall9, new Date());

		Modifier modifierA27 = new Modifier("upsize", "", 0.50, food27, new Date());
		Set<Modifier> modifierListA27 = new HashSet<Modifier>();
		modifierListA16.add(modifierA27);
		food5.setModifierList(modifierListA27);

		foodListB9.add(food27);
		stall9.setFoodList(foodListB9);

		Stall stall10 = new Stall("Noodle Stall", "123", "Noodles Stall", 96946576, null,
				new Date(), null);
		Food food28 = new Food("Lor Mee", "", 3.00, null, stall10, new Date());
		Food food29 = new Food("Prawn Mee", "", 3.00, null, stall10, new Date());
		Food food30 = new Food("Fishball Noodles", "", 3.00, null, stall10, new Date());
		Food food31 = new Food("Laksa", "", 3.00, null, stall10, new Date());
		Food food32 = new Food("Hokkien Mee", "", 3.00, null, stall10, new Date());
		Food food33 = new Food("Dumpling Noodles", "", 3.00, null, stall10, new Date());
		foodListB10.add(food28);
		foodListB10.add(food29);
		foodListB10.add(food30);
		foodListB10.add(food31);
		foodListB10.add(food32);
		foodListB10.add(food33);
		stall10.setFoodList(foodListB10);

		Stall stall11 = new Stall("Fruit Stall", "123", "Fruit Stall", 91151608, null, new Date(),
				null);
		Food food34 = new Food("Apple", "", 0.60, null, stall11, new Date());
		Food food35 = new Food("Watermelon", "", 0.60, null, stall11, new Date());
		Food food36 = new Food("DragonFruit", "", 0.60, null, stall11, new Date());
		Food food37 = new Food("Pear", "", 0.60, null, stall11, new Date());
		Food food38 = new Food("Honeydew", "", 0.60, null, stall11, new Date());
		Food food39 = new Food("Papaya", "", 0.60, null, stall11, new Date());
		Food food40 = new Food("Pineapple", "", 0.60, null, stall11, new Date());
		Food food41 = new Food("Banana", "", 0.60, null, stall11, new Date());
		Food food42 = new Food("Agar Agar", "", 0.60, null, stall11, new Date());
		Food food43 = new Food("Orange", "", 0.60, null, stall11, new Date());
		Food food44 = new Food("Guava", "", 0.60, null, stall11, new Date());
		Food food45 = new Food("Sarawak Pineapple", "", 1.00, null, stall11, new Date());
		Food food46 = new Food("Mixed Fruits", "Upsize 3.50", 3.00, null, stall11, new Date());
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

		Stall kuehStall = new Stall("Kueh Stall", "123", "Oasis Kueh Stall", 90685620, canteen1,
				new Date(), null);
		Food kuehfood1 = new Food("Chee Cheong Fun", "", 0.60, null, kuehStall, new Date());
		Food kuehfood2 = new Food("Yam cake", "", 1.20, null, kuehStall, new Date());
		Food kuehfood3 = new Food("Dumpling", "", 0.90, null, kuehStall, new Date());
		Food kuehfood4 = new Food("Pau", "", 0.80, null, kuehStall, new Date());
		Food kuehfood5 = new Food("Lor Mai Kai", "", 1.50, null, kuehStall, new Date());
		Food kuehfood6 = new Food("Fan Choy", "", 1.80, null, kuehStall, new Date());
		// add food to list to the stall
		Set<Food> foodList1 = new HashSet<Food>();

		foodList1.add(kuehfood1);
		foodList1.add(kuehfood2);
		foodList1.add(kuehfood3);
		foodList1.add(kuehfood4);
		foodList1.add(kuehfood5);
		foodList1.add(kuehfood6);
		kuehStall.setFoodList(foodList1);

		Stall malayStall = new Stall("Malay Stall", "123", "Oasis Malay Stall", 93848341, canteen1,
				new Date(), null);
		Food mfood1 = new Food("Mixed Veg Rice", "ask for more vegs, less fried meat", 3.70, null,
				malayStall, new Date());

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

		Stall indianStall = new Stall("Indian Stall", "123", "Indian Stall", 98717752, canteen1,
				new Date(), null);
		Food infood1 = new Food("White Rice", "Chicken/Fish/Mutton", 4.00, null, indianStall,
				new Date());
		Food infood2 = new Food("Vegetable White Rice", "", 3.00, null, indianStall, new Date());
		Food infood3 = new Food("Briyani", "Chicken/Fish/Mutton", 5.00, null, indianStall,
				new Date());

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

		Stall chineseMixVegStall = new Stall("Chinese Stall", "123", "Oasis Chinese Mix Veg Stall",
				93848341, canteen1, new Date(), null);
		Food mixVegRice1 = new Food("Mix Veg Rice",
				"ask for more meat, less fried meat, or upsize to $3.50", 3.00, null,
				chineseMixVegStall, new Date());

		// for mixVegRice1 modifiers
		Modifier modifier9 = new Modifier("Ask for more vegetables", "", 0.00, mixVegRice1,
				new Date());
		Modifier modifier10 = new Modifier("Ask for less fried meat", "", 0.00, mixVegRice1,
				new Date());
		Modifier modifier11 = new Modifier("Upsize to $3.50", "", 0.50, mixVegRice1, new Date());

		Set<Modifier> modifierList4 = new HashSet<Modifier>();
		modifierList4.add(modifier9);
		modifierList4.add(modifier10);
		modifierList4.add(modifier11);
		mixVegRice1.setModifierList(modifierList4);

		Set<Food> foodList4 = new HashSet<Food>();
		foodList4.add(mixVegRice1);
		chineseMixVegStall.setFoodList(foodList4);

		Stall roastMeatStall = new Stall("Roast Meat Stall", "123", "Roast Meat Stall", 123,
				canteen1, new Date(), null);
		Food roastfood1 = new Food("Roast Chicken Rice", "2 meat choices $4", 3.00, null,
				roastMeatStall, new Date());
		Food roastfood2 = new Food("Wanton Mee", "", 3.20, null, roastMeatStall, new Date());
		Food roastfood3 = new Food("CharSiew Rice", "2 meat choices $4", 3.00, null,
				roastMeatStall, new Date());
		Food roastfood4 = new Food("Roast Meat Rice", "2 meat choices $4", 3.00, null,
				roastMeatStall, new Date());

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

		Stall seafoodTzeCharStall = new Stall("Tze Char Stall", "123", "Seafood Tze Char Stall",
				92262376, canteen1, new Date(), null);

		Food seafood1 = new Food("Hor Fun", "dry type $4.00", 3.70, null, seafoodTzeCharStall,
				new Date());
		Food seafood2 = new Food("Fried Rice", "", 4.00, null, seafoodTzeCharStall, new Date());
		Food seafood3 = new Food("Daily Soup With Rice And Egg", "", 4.80, null,
				seafoodTzeCharStall, new Date());
		Food seafood4 = new Food("Hokkien Noodle", "", 4.20, null, seafoodTzeCharStall, new Date());
		Food seafood5 = new Food("Mee Goreng", "", 4.20, null, seafoodTzeCharStall, new Date());

		// for seafood1 modifier
		Modifier modifier18 = new Modifier("Dry type", "", 0.30, seafood1, new Date());
		Modifier modifier19 = new Modifier("Ask for more vegetables", "", 0.00, seafood1,
				new Date());

		Set<Modifier> modifierList8 = new HashSet<Modifier>();
		modifierList8.add(modifier18);
		modifierList8.add(modifier19);
		seafood1.setModifierList(modifierList8);

		// for seafood4 modifier
		Modifier modifier20 = new Modifier("Ask for more vegetables", "", 0.00, seafood4,
				new Date());

		Set<Modifier> modifierList9 = new HashSet<Modifier>();
		modifierList9.add(modifier20);
		seafood4.setModifierList(modifierList9);

		// for seafood5 modifier
		Modifier modifier21 = new Modifier("Ask for more vegetables", "", 0.00, seafood5,
				new Date());

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

		Stall fishBeehoonStall = new Stall("Fish Bee Hoon Stall", "123", "Fish Beehoon Stall",
				98367790, canteen1, new Date(), null);
		Food fishBeehoonfood1 = new Food("Fish Soup With Bee Hoon", "add bittergourd: $0.50", 3.50,
				null, fishBeehoonStall, new Date());
		Food fishBeehoonfood2 = new Food("Fish Soup With Rice", "add bittergourd: $0.50", 4.00,
				null, fishBeehoonStall, new Date());
		Food fishBeehoonfood3 = new Food("Fried Fish Soup With Bee Hoon", "add bittergourd: $0.50",
				5.00, null, fishBeehoonStall, new Date());

		// for fishBeehoonfood1 modifier
		Modifier modifier22 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood1,
				new Date());
		Set<Modifier> modifierList11 = new HashSet<Modifier>();
		modifierList11.add(modifier22);
		fishBeehoonfood1.setModifierList(modifierList11);

		// for fishBeehoonfood2 modifier
		Modifier modifier23 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood2,
				new Date());
		Set<Modifier> modifierList12 = new HashSet<Modifier>();
		modifierList12.add(modifier23);
		fishBeehoonfood2.setModifierList(modifierList12);

		// for fishBeehoonfood3 modifier
		Modifier modifier24 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood3,
				new Date());
		Set<Modifier> modifierList13 = new HashSet<Modifier>();
		modifierList13.add(modifier24);
		fishBeehoonfood3.setModifierList(modifierList13);

		Set<Food> foodList7 = new HashSet<Food>();
		foodList7.add(fishBeehoonfood1);
		foodList7.add(fishBeehoonfood2);
		foodList7.add(fishBeehoonfood3);

		fishBeehoonStall.setFoodList(foodList7);

		Stall fruitStall = new Stall("Fruit Stall", "123", "Fruit Stall", 91151608, canteen1,
				new Date(), null);
		Food apple = new Food("Apple", "change to juice $2.50", 0.60, null, fruitStall, new Date());
		Food watermelon = new Food("Watermelon", "change to juice $2.50", 0.60, null, fruitStall,
				new Date());
		Food dragonfruit = new Food("Dragonfruit", "change to juice $2.50", 0.60, null, fruitStall,
				new Date());
		Food pear = new Food("Pear", "change to juice $2.50", 0.60, null, fruitStall, new Date());
		Food honeydew = new Food("Honeydew", "change to juice $2.50", 0.70, null, fruitStall,
				new Date());
		Food papaya = new Food("Papaya", "change to juice $2.50", 0.60, null, fruitStall,
				new Date());
		Food pineapple = new Food("Pineapple", "change to juice $2.50", 0.60, null, fruitStall,
				new Date());
		Food banana = new Food("Banana", "change to juice $2.50", 0.60, null, fruitStall,
				new Date());
		Food agaragar = new Food("Agar Agar", "", 0.60, null, fruitStall, new Date());
		Food orange = new Food("Orange", "change to juice $2.50", 0.60, null, fruitStall,
				new Date());
		Food guava = new Food("Guava", "change to juice $2.50", 0.70, null, fruitStall, new Date());
		Food sarawakPineapple = new Food("Sarawak Pineapple", "change to juice $2.50", 1.00, null,
				fruitStall, new Date());
		Food mixedFruits = new Food("Mixed Fruits", "upsize $3.50", 3.00, null, fruitStall,
				new Date());

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

		Modifier modifier35 = new Modifier("Change to juice", "", 1.90, sarawakPineapple,
				new Date());
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
		session.save(canteen1);

		session.save(kuehStall);
		session.save(malayStall);
		session.save(indianStall);
		session.save(chineseMixVegStall);
		session.save(roastMeatStall);
		session.save(seafoodTzeCharStall);
		session.save(fishBeehoonStall);
		session.save(fruitStall);

		session.save(kuehfood1);
		session.save(kuehfood2);
		session.save(kuehfood3);
		session.save(kuehfood4);
		session.save(kuehfood5);
		session.save(kuehfood6);

		session.save(mfood1);

		session.save(infood1);
		session.save(infood2);
		session.save(infood3);

		session.save(mixVegRice1);

		session.save(roastfood1);
		session.save(roastfood2);
		session.save(roastfood3);
		session.save(roastfood4);

		session.save(seafood1);
		session.save(seafood2);
		session.save(seafood3);
		session.save(seafood4);
		session.save(seafood5);

		session.save(fishBeehoonfood1);
		session.save(fishBeehoonfood2);
		session.save(fishBeehoonfood3);

		session.save(apple);
		session.save(watermelon);
		session.save(dragonfruit);
		session.save(pear);
		session.save(honeydew);
		session.save(papaya);
		session.save(pineapple);
		session.save(banana);
		session.save(agaragar);
		session.save(orange);
		session.save(guava);
		session.save(sarawakPineapple);
		session.save(mixedFruits);

		// saving of the modifiers into database
		session.save(modifier1);
		session.save(modifier2);
		session.save(modifier3);
		session.save(modifier4);
		session.save(modifier5);
		session.save(modifier6);
		session.save(modifier7);
		session.save(modifier8);
		session.save(modifier9);
		session.save(modifier10);
		session.save(modifier11);
		session.save(modifier12);
		session.save(modifier13);
		session.save(modifier14);
		session.save(modifier15);
		session.save(modifier16);
		session.save(modifier17);
		session.save(modifier18);
		session.save(modifier19);
		session.save(modifier20);
		session.save(modifier21);
		session.save(modifier22);
		session.save(modifier23);
		session.save(modifier24);
		session.save(modifier25);
		session.save(modifier26);
		session.save(modifier27);
		session.save(modifier28);
		session.save(modifier29);
		session.save(modifier30);
		session.save(modifier31);
		session.save(modifier32);
		session.save(modifier33);
		session.save(modifier34);
		session.save(modifier35);
		session.save(modifier36);

		session.save(modifierA3);
		session.save(modifierA4);
		session.save(modifierA5);
		session.save(modifierA16);
		session.save(modifierA27);

		session.save(canteen2);
		session.save(stall1);
		session.save(stall2);
		session.save(stall3);
		session.save(stall4);
		session.save(stall5);
		session.save(stall6);
		session.save(stall7);
		session.save(stall8);
		session.save(stall9);
		session.save(stall10);
		session.save(stall11);
		session.save(food1);
		session.save(food2);
		session.save(food3);
		session.save(food4);
		session.save(food5);
		session.save(food6);
		session.save(food7);
		session.save(food8);
		session.save(food9);
		session.save(food10);
		session.save(food11);
		session.save(food12);
		session.save(food13);
		session.save(food14);
		session.save(food15);
		session.save(food16);
		session.save(food17);
		session.save(food18);
		session.save(food19);
		session.save(food20);
		session.save(food21);
		session.save(food22);
		session.save(food23);
		session.save(food24);
		session.save(food25);
		session.save(food26);
		session.save(food27);
		session.save(food28);
		session.save(food29);
		session.save(food30);
		session.save(food31);
		session.save(food32);
		session.save(food33);
		session.save(food34);
		session.save(food35);
		session.save(food36);
		session.save(food37);
		session.save(food38);
		session.save(food39);
		session.save(food40);
		session.save(food41);
		session.save(food42);
		session.save(food43);
		session.save(food44);
		session.save(food45);
		session.save(food46);

		// **************************************** End insert of mock data
		// ****************************************
		
		session.getTransaction().commit();
		session.close();

		System.out.println("Test.java completed");

	}
}
