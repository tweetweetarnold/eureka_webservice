package test;

import java.util.HashSet;
import java.util.Set;

import model.Admin;
import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.Modifier;
import model.OrderWindow;
import model.Stall;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;

import services.AESAlgorithm;
import services.PasswordService;

public class Test {

	public static void main(String[] args) {

		// **************************************** Arnold Data
		// ****************************************

		// ************* WARNING, THIS HIBERNATE CONFIG FILE WILL CLEAR
		// EVERYTHING IN DATABASE AND POPULATE WITH BELOW
		// ************************************************************************************************************
		boolean onOpenshift = false;
		if (System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null) {
			onOpenshift = true;
		}

		SessionFactory sessionFactory = null;

		if (onOpenshift) {
			// if application on OpenShift
			sessionFactory = new Configuration().configure("hibernate-create.cfg.xml").buildSessionFactory();
			System.out.println("hibernate.cfg.xml is loaded");
		} else {
			// if application on localhost
			sessionFactory = new Configuration().configure("hibernate-local-create.cfg.xml").buildSessionFactory();
			System.out.println("hibernate-local.cfg.xml is loaded");
		}

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ************************************************************************************************************
		// ************* WARNING, THIS HIBERNATE CONFIG FILE WILL CLEAR
		// EVERYTHING IN DATABASE AND POPULATE WITH BELOW

		Company company = new Company("XiaoDingDang Co.", null, null, "XDD123");
		Set<String> buildingList = new HashSet<String>();
		buildingList.add("SEMBCORP");
		buildingList.add("PSN");
		buildingList.add("CONTROL");
		buildingList.add("MAINTAINANCE");

		AESAlgorithm aes = new AESAlgorithm();

		company.setDeliveryPointSet(buildingList);
		Employee arnold = new Employee(aes.encrypt("arnold.lee.2013@sis.smu.edu.sg" + "1234567"), "Arnold Lee",
				"arnold.lee.2013@sis.smu.edu.sg", 85698565, company);

		arnold.setDefaultDeliveryPoint("CONTROL");

		session.save(company);
		session.save(arnold);

		Admin admin = new Admin("admin", PasswordService.encryptPassword("1234567"), "admin1", 45678925);
		session.save(admin);

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

		Stall stall1 = new Stall("Sliced Fish Bee Hoon Stall", 91379160, null, null,
				"resources/img/stall/img-slicedfishbeehoonstall.jpg");
		Food food1 = new Food("Fish Slice Bee Hoon", "", 3.70, "resources/img/food/img-fishslicebeehoon.jpg", stall1);
		Food food2 = new Food("Fish Soup and Rice", "", 4.70, "resources/img/food/img-fishsoupandrice.jpg", stall1);

		foodListB1.add(food1);
		foodListB1.add(food2);
		stall1.setFoodList(foodListB1);

		Stall stall2 = new Stall("Malay food Stall", 81145966, null, null,
				"resources/img/stall/img-malayfoodstall.jpg");
		Food food3 = new Food("Chilli Chicken + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malaymixedricechicken.jpg", stall2);
		Food food4 = new Food("Fried Chicken + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malaymixedricefish.jpg", stall2);
		Food food5 = new Food("Curry Fish + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malaymixedricemutton.jpg", stall2);
		Food foodMalay1 = new Food("Fried Fish + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malaymixedricemutton.jpg", stall2);
		Food foodMalay2 = new Food("Mutton + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malaymixedricemutton.jpg", stall2);
		
		Modifier modifierA3 = new Modifier("Add Veg", "", 0.50, food3);
		Set<Modifier> modifierListA1 = new HashSet<Modifier>();
		modifierListA1.add(modifierA3);
		food3.setModifierList(modifierListA1);

		Modifier modifierA4 = new Modifier("Add Veg", "", 0.50, food4);
		Set<Modifier> modifierListA2 = new HashSet<Modifier>();
		modifierListA2.add(modifierA4);
		food4.setModifierList(modifierListA2);

		Modifier modifierA5 = new Modifier("Add Veg", "", 0.50, food5);
		Set<Modifier> modifierListA3 = new HashSet<Modifier>();
		modifierListA3.add(modifierA5);
		food5.setModifierList(modifierListA3);
		
		Modifier modifierAMalay1 = new Modifier("Add Veg", "", 0.50, foodMalay1);
		Set<Modifier> modifierListAMalay1 = new HashSet<Modifier>();
		modifierListAMalay1.add(modifierAMalay1);
		foodMalay1.setModifierList(modifierListAMalay1);
		
		Modifier modifierAMalay2 = new Modifier("Add Veg", "", 0.50, foodMalay2);
		Set<Modifier> modifierListAMalay2 = new HashSet<Modifier>();
		modifierListAMalay1.add(modifierAMalay2);
		foodMalay2.setModifierList(modifierListAMalay1);

		foodListB2.add(food3);
		foodListB2.add(food4);
		foodListB2.add(food5);
		foodListB2.add(foodMalay1);
		foodListB2.add(foodMalay2);
		stall2.setFoodList(foodListB2);

		Stall stall3 = new Stall("Mixed Rice Stall", 93482772, null, null,
				"resources/img/stall/img-mixedricestall.jpg");
		Food food6 = new Food("1 Meat + 2 Veg", "", 3.00, "resources/img/food/img-mixedrice.jpg", stall3);
		Food food7 = new Food("All Veg", "", 3.00, "resources/img/food/img-porridge.jpg", stall3);

		Modifier modifierMixed1 = new Modifier("Add Veg", "", 0.50, food6);
		Set<Modifier> modifierListMixed1 = new HashSet<Modifier>();
		modifierListMixed1.add(modifierMixed1);
		food6.setModifierList(modifierListMixed1);
		
		Modifier modifierMixed2 = new Modifier("Add Veg", "", 0.50, food7);
		Set<Modifier> modifierListMixed2 = new HashSet<Modifier>();
		modifierListMixed2.add(modifierMixed2);
		food7.setModifierList(modifierListMixed2);
		
		foodListB3.add(food6);
		foodListB3.add(food7);
		stall3.setFoodList(foodListB3);

		Stall stall4 = new Stall("Wanton Mee Stall", 0, null, foodListB4, "resources/img/stall/img-wantonmeestall.jpg");
		Food food8 = new Food("Wanton Mee", "", 3.00, "resources/img/food/img-wantonmee.jpg", stall4);

		foodListB4.add(food8);
		stall4.setFoodList(foodListB4);

		Stall stall5 = new Stall("Indian Food Stall", 93841009, null, null,
				"resources/img/stall/img-indianfoodstall.jpg");
		Food food9 = new Food("Curry Chicken Briyani", "", 3.50,
				"resources/img/food/img-indianmixedricechicken.jpg", stall5);
		Food food10 = new Food("Fish Briyani", "", 3.50, "resources/img/food/img-indianmixedricefish.jpg",
				stall5);
		Food food11 = new Food("Vegetable Briyani", "", 4.00, "resources/img/food/img-muttonbriyani.jpg", stall5);
		Food food12 = new Food("Fried Chicken Briyani", "", 4.00, "resources/img/food/img-ayampenyat.jpg", stall5);

		foodListB5.add(food9);
		foodListB5.add(food10);
		foodListB5.add(food11);
		foodListB5.add(food12);
		stall5.setFoodList(foodListB5);

		Stall stall6 = new Stall("Roast Duck & Chicken Rice Stall", 98427347, null, null,
				"resources/img/stall/img-roastduckandchickenricestall.jpg");
		Food food13 = new Food("Roast Chicken Rice", "", 2.50, "resources/img/food/img-roastchickenrice.jpg", stall6);
		Food food14 = new Food("CharSiew Rice", "", 2.50, "resources/img/food/img-charsiewrice.jpg", stall6);
		Food food15 = new Food("Roast Meat Rice", "", 2.50, "resources/img/food/img-roastmeatrice.jpg", stall6);
		Food foodC16 = new Food("CharSiew + Roast Meat Rice", "", 3.00,
				"resources/img/food/img-charsiewroastmeatrice.jpg", stall6);

		foodListB6.add(foodC16);
		foodListB6.add(food13);
		foodListB6.add(food14);
		foodListB6.add(food15);
		stall6.setFoodList(foodListB6);

		Stall stall7 = new Stall("REX(Halal)", 62684806, null, null, "resources/img/stall/img-rex.jpg");
		Food food16 = new Food("Chicken Rice", "", 3.00, "resources/img/food/img-ayampenyat.jpg", stall7);
		Food food17 = new Food("Chicken Fried Rice", "", 3.00, "resources/img/food/img-chickenfriedrice.jpg", stall7);
		Food food18 = new Food("Seafood Fried Rice", "", 3.50, "resources/img/food/img-seafoodfriedrice.jpg", stall7);
		Food food19 = new Food("Beef Fried Rice", "", 4.00, "resources/img/food/img-beeffriedrice.jpg", stall7);
		Food food20 = new Food("Seafood Horfun(wet)", "", 3.50, "resources/img/food/img-seafoodhorfunwet.jpg", stall7);
		Food food21 = new Food("Seafood Horfun(dry)", "", 4.00, "resources/img/food/img-seafoodhorfundry.jpg", stall7);
		Food food22 = new Food("Chicken Porridge", "", 3.00, "resources/img/food/img-chickenporridge.jpg", stall7);
		Food food23 = new Food("Fish Porridge", "", 3.00, "resources/img/food/img-fishporridge.jpg", stall7);
		Food foodA24 = new Food("Chicken Horfun(wet)", "", 3.50, "resources/img/food/img-fishporridge.jpg", stall7);
		Food foodA25 = new Food("Chicken Horfun(dry)", "", 4.00, "resources/img/food/img-fishporridge.jpg", stall7);
		
		
		
		Modifier modifierA16 = new Modifier("Change to drumstick", "", 0.50, food16);
		Modifier modifierA17 = new Modifier("Chicken Wing", "", 0.0, food16);
		Modifier modifierA18 = new Modifier("Chicken Breast", "", 0.0, food16);
		Modifier modifierA19 = new Modifier("Chicken Thigh", "", 0.0, food16);
		Set<Modifier> modifierListA16 = new HashSet<Modifier>();
		modifierListA16.add(modifierA16);
		modifierListA16.add(modifierA17);
		modifierListA16.add(modifierA18);
		modifierListA16.add(modifierA19);
		food16.setModifierList(modifierListA16);

		foodListB7.add(food16);
		foodListB7.add(food17);
		foodListB7.add(food18);
		foodListB7.add(food19);
		foodListB7.add(food20);
		foodListB7.add(food21);
		foodListB7.add(food22);
		foodListB7.add(food23);
		foodListB7.add(foodA24);
		foodListB7.add(foodA25);
		stall7.setFoodList(foodListB7);

		Stall stall8 = new Stall("Vegetarian Stall", 91182963, null, null,
				"resources/img/stall/img-vegetarianstall.jpg");
		Food food24 = new Food("Vegetarian Rice", "", 2.50, "resources/img/food/img-vegetarianrice.jpg", stall8);
		Food food25 = new Food("Vegetarian Beehoon", "", 2.50, "resources/img/food/img-vegetarianbeehoon.jpg", stall8);
		Food food26 = new Food("Vegetarian Mee", "", 2.50, "resources/img/food/img-vegetarianmee.jpg", stall8);

		foodListB8.add(food24);
		foodListB8.add(food25);
		foodListB8.add(food26);
		stall8.setFoodList(foodListB8);

		Stall stall9 = new Stall("Minced Meat Noodles Stall(Closed On Tuesday)", 93686070, null, null,
				"resources/img/stall/img-mincedmeatnoodlesstall.jpg");
		Food food27 = new Food("Minced Meat Noodles", "upsize to $3.20", 2.70,
				"resources/img/food/img-mincedmeatnoodles.jpg", stall9);

		Modifier modifierA27 = new Modifier("Upsize to $3.20", "", 0.50, food27);
		Set<Modifier> modifierListA27 = new HashSet<Modifier>();
		modifierListA16.add(modifierA27);
		food5.setModifierList(modifierListA27);

		foodListB9.add(food27);
		stall9.setFoodList(foodListB9);

		Stall stall10 = new Stall("Noodle Stall", 96946576, null, null, "resources/img/stall/img-noodlestall.jpg");
		Food food28 = new Food("Lor Mee", "", 3.00, "resources/img/food/img-lormee.jpg", stall10);
		Food food29 = new Food("Prawn Mee", "", 3.00, "resources/img/food/img-prawnmee.jpg", stall10);
		Food food30 = new Food("Fishball Noodles", "", 3.00, "resources/img/food/img-fishballnoodles.jpg", stall10);
		Food food31 = new Food("Laksa", "", 3.00, "resources/img/food/img-laksa.jpg", stall10);
		Food food32 = new Food("Hokkien Mee", "", 3.00, "resources/img/food/img-hokkienmee.jpg", stall10);
		Food food33 = new Food("Dumpling Noodles", "", 3.00, "resources/img/food/img-dumplingnoodles.jpg", stall10);

		foodListB10.add(food28);
		foodListB10.add(food29);
		foodListB10.add(food30);
		foodListB10.add(food31);
		foodListB10.add(food32);
		foodListB10.add(food33);
		stall10.setFoodList(foodListB10);

		// Canteen canteen2 = new Canteen("Feng Shan Market and Food Centre",
		// "123", null);
		// stall1.setCanteen(canteen2);
		// stall2.setCanteen(canteen2);
		// stall3.setCanteen(canteen2);
		// stall4.setCanteen(canteen2);
		// stall5.setCanteen(canteen2);
		// stall6.setCanteen(canteen2);
		// stall7.setCanteen(canteen2);
		// stall8.setCanteen(canteen2);
		// stall9.setCanteen(canteen2);
		// stall10.setCanteen(canteen2);

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

		// canteen2.setStallList(stallList2);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//oasis
		
		
		Canteen canteen1 = new Canteen("Taman Jurong Market and Food Centre", "123", null);

		Stall kuehStall = new Stall("Oasis Kueh Stall", 90685620, canteen1, null,
				"resources/img/stall/img-oasiskuehstall.jpg");
		Food kuehfood1 = new Food("Chee Cheong Fun", "", 0.60, "resources/img/food/img-cheecheongfun.jpg", kuehStall);
		Food kuehfood2 = new Food("Yam cake", "", 1.20, "resources/img/food/img-yamcake.jpg", kuehStall);
		Food kuehfood3 = new Food("Dumpling", "", 0.90, "resources/img/food/img-dumpling.png", kuehStall);
		Food kuehfood4 = new Food("Pau", "", 0.80, "resources/img/food/img-pau.jpg", kuehStall);
		Food kuehfood5 = new Food("Lor Mai Kai", "", 1.50, "resources/img/food/img-lormaikai.jpg", kuehStall);
		Food kuehfood6 = new Food("Fan Choy", "", 1.80, "resources/img/food/img-fanchoy.jpg", kuehStall);
		// add food to list to the stall

		// load image file

		Set<Food> foodList1 = new HashSet<Food>();

		foodList1.add(kuehfood1);
		foodList1.add(kuehfood2);
		foodList1.add(kuehfood3);
		foodList1.add(kuehfood4);
		foodList1.add(kuehfood5);
		foodList1.add(kuehfood6);
		kuehStall.setFoodList(foodList1);

		Stall malayStall = new Stall("Oasis Malay Stall", 93848341, canteen1, null,
				"resources/img/stall/img-oasismalaystall.jpg");
		Food mfood1 = new Food("Curry Chicken + Veg", "ask for more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		Food mfood2 = new Food("Asam Fish + Veg", "ask for more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		Food mfood3 = new Food("Mutton + Veg", "ask for more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		Food mfood4 = new Food("Beef + Veg", "ask for more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		Food mfood5 = new Food("Fried Chicken + Veg", "ask for more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		Food mfood6 = new Food("Fried Fish + Veg", "ask for more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		Food mfood7 = new Food("Stingray + Veg", "ask for more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		// for mfood1 modifiers
		Modifier modifier1 = new Modifier("Ask for more vegetables", "", 0.00, mfood1);
		Modifier modifier2 = new Modifier("No egg", "", 0.00, mfood1);
		Set<Modifier> modifierList1 = new HashSet<Modifier>();
		modifierList1.add(modifier1);
		modifierList1.add(modifier2);
		
		mfood1.setModifierList(modifierList1);

		// for mfood2 modifiers
		Modifier modifierM21 = new Modifier("Ask for more vegetables", "", 0.00, mfood2);
		Modifier modifierM22 = new Modifier("No egg", "", 0.00, mfood2);
		Set<Modifier> modifierList2 = new HashSet<Modifier>();
		modifierList2.add(modifierM21);
		modifierList2.add(modifierM22);
		mfood2.setModifierList(modifierList2);

		// for mfood3 modifiers
		Modifier modifierM31 = new Modifier("Ask for more vegetables", "", 0.00, mfood3);
		Modifier modifierM32 = new Modifier("No egg", "", 0.00, mfood3);
		Set<Modifier> modifierList3 = new HashSet<Modifier>();
		modifierList3.add(modifierM31);
		modifierList3.add(modifierM32);
		mfood3.setModifierList(modifierList3);

		// for mfood4 modifiers
		Modifier modifierM41 = new Modifier("Ask for more vegetables", "", 0.00, mfood4);
		Modifier modifierM42 = new Modifier("No egg", "", 0.00, mfood4);
		Set<Modifier> modifierListm4 = new HashSet<Modifier>();
		modifierListm4.add(modifierM41);
		modifierListm4.add(modifierM42);
		mfood4.setModifierList(modifierListm4);
		
		Modifier modifierM51 = new Modifier("Ask for more vegetables", "", 0.00, mfood5);
		Modifier modifierM52 = new Modifier("No egg", "", 0.00, mfood5);
		Set<Modifier> modifierListm5 = new HashSet<Modifier>();
		modifierListm5.add(modifierM51);
		modifierListm5.add(modifierM52);
		mfood5.setModifierList(modifierListm5);
		
		Modifier modifierM61 = new Modifier("Ask for more vegetables", "", 0.00, mfood6);
		Modifier modifierM62 = new Modifier("No egg", "", 0.00, mfood6);
		Set<Modifier> modifierListm6 = new HashSet<Modifier>();
		modifierListm6.add(modifierM61);
		modifierListm6.add(modifierM62);
		mfood6.setModifierList(modifierListm6);
		
		Modifier modifierM71 = new Modifier("Ask for more vegetables", "", 0.00, mfood7);
		Modifier modifierM72 = new Modifier("No egg", "", 0.00, mfood7);
		Set<Modifier> modifierListm7 = new HashSet<Modifier>();
		modifierListm7.add(modifierM71);
		modifierListm7.add(modifierM72);
		mfood7.setModifierList(modifierListm7);

		Set<Food> foodList2 = new HashSet<Food>();
		foodList2.add(mfood1);
		foodList2.add(mfood2);
		foodList2.add(mfood3);
		foodList2.add(mfood4);
		foodList2.add(mfood5);
		foodList2.add(mfood6);
		foodList2.add(mfood7);
		
		
		
		
		malayStall.setFoodList(foodList2);

		Stall indianStall = new Stall("Indian Stall", 98717752, canteen1, null,
				"resources/img/stall/img-indianstall.jpg");
		Food infood1 = new Food("White Rice(Chicken)", "Chicken/Fish/Mutton", 4.00,
				"resources/img/food/img-whiterice.jpg", indianStall);
		Food infood2 = new Food("White Rice(Fish)", "Chicken/Fish/Mutton", 4.00, "resources/img/food/img-whiterice.jpg",
				indianStall);
		Food infood3 = new Food("White Rice(Mutton)", "Chicken/Fish/Mutton", 4.00,
				"resources/img/food/img-whiterice.jpg", indianStall);
		Food infood4 = new Food("Vegetable White Rice", "", 3.00, "resources/img/food/img-vegetablewhiterice.jpg",
				indianStall);
		Food infood5 = new Food("Chicken Briyani", "Chicken", 5.00, "resources/img/food/img-briyani.jpg", indianStall);
		Food infood6 = new Food("Fish Briyani", "Fish", 5.00, "resources/img/food/img-briyani.jpg", indianStall);
		Food infood7 = new Food("Mutton Briyani", "Mutton", 5.00, "resources/img/food/img-briyani.jpg", indianStall);
		Food infood8 = new Food("Vegetarian Briyani", "Vegetarian", 5.00, "resources/img/food/img-briyani.jpg", indianStall);
		
		Modifier modifierI1 = new Modifier("More vegetables", "", 0.5, infood1);
		Modifier modifierI2 = new Modifier("More vegetables", "", 0.5, infood2);
		Modifier modifierI3 = new Modifier("More vegetables", "", 0.5, infood3);
		Modifier modifierI4 = new Modifier("More vegetables", "", 0.5, infood4);
		Modifier modifierI5 = new Modifier("More vegetables", "", 0.5, infood5);
		Modifier modifierI6 = new Modifier("More vegetables", "", 0.5, infood6);
		Modifier modifierI7 = new Modifier("More vegetables", "", 0.5, infood7);
		Modifier modifierI8 = new Modifier("More vegetables", "", 0.5, infood8);
		Set<Modifier> modifierListI1 = new HashSet<Modifier>();
		Set<Modifier> modifierListI2 = new HashSet<Modifier>();
		Set<Modifier> modifierListI3 = new HashSet<Modifier>();
		Set<Modifier> modifierListI4 = new HashSet<Modifier>();
		Set<Modifier> modifierListI5 = new HashSet<Modifier>();
		Set<Modifier> modifierListI6 = new HashSet<Modifier>();
		Set<Modifier> modifierListI7 = new HashSet<Modifier>();
		Set<Modifier> modifierListI8 = new HashSet<Modifier>();
		modifierListI1.add(modifierI1);
		modifierListI2.add(modifierI2);
		modifierListI3.add(modifierI3);
		modifierListI4.add(modifierI4);
		modifierListI5.add(modifierI5);
		modifierListI6.add(modifierI6);
		modifierListI7.add(modifierI7);
		modifierListI8.add(modifierI8);
		infood1.setModifierList(modifierListI1);
		infood2.setModifierList(modifierListI2);
		infood3.setModifierList(modifierListI3);
		infood4.setModifierList(modifierListI4);
		infood5.setModifierList(modifierListI5);
		infood6.setModifierList(modifierListI6);
		infood7.setModifierList(modifierListI7);
		infood8.setModifierList(modifierListI8);
		// // for infood1 modifiers
		// Modifier modifier3 = new Modifier("Chicken", "", 0.00, infood1);
		// Modifier modifier4 = new Modifier("Fish", "", 0.00, infood1);
		// Modifier modifier5 = new Modifier("Mutton", "", 0.00, infood1);
		//
		// Set<Modifier> modifierList2 = new HashSet<Modifier>();
		// modifierList2.add(modifier3);
		// modifierList2.add(modifier4);
		// modifierList2.add(modifier5);
		// infood1.setModifierList(modifierList2);

		// for infood3 modifiers
		// Modifier modifier6 = new Modifier("Chicken", "", 0.00, infood3);
		// Modifier modifier7 = new Modifier("Fish", "", 0.00, infood3);
		// Modifier modifier8 = new Modifier("Mutton", "", 0.00, infood3);
		//
		// Set<Modifier> modifierList3 = new HashSet<Modifier>();
		// modifierList3.add(modifier6);
		// modifierList3.add(modifier7);
		// modifierList3.add(modifier8);
		// infood3.setModifierList(modifierList3);

		Set<Food> foodList3 = new HashSet<Food>();
		foodList3.add(infood1);
		foodList3.add(infood2);
		foodList3.add(infood3);
		foodList3.add(infood4);
		foodList3.add(infood5);
		foodList3.add(infood6);
		foodList3.add(infood7);
		foodList3.add(infood8);
		indianStall.setFoodList(foodList3);

		Stall chineseMixVegStall = new Stall("Chinese Mix Veg Stall", 93848341, canteen1, null,
				"resources/img/stall/img-oasischinesemixvegstall.jpg");
		Food mixVegRice1 = new Food("1 Meat 2 Veg", "ask for more meat, less fried meat, or upsize to $3.50", 3.00,
				"resources/img/food/img-mixvegricechinese.jpg", chineseMixVegStall);
		Food mixVegRice2 = new Food("All Veg", "", 3.00,
				"resources/img/food/img-mixvegricechinese.jpg", chineseMixVegStall);
		Food mixVegRice3 = new Food("Porridge", "", 3.20,
				"resources/img/food/img-mixvegricechinese.jpg", chineseMixVegStall);
		Food mixVegRice4 = new Food("Fish + Veg", "", 3.70,
				"resources/img/food/img-mixvegricechinese.jpg", chineseMixVegStall);
		
		// for mixVegRice1 modifiers
		Modifier modifier9 = new Modifier("Ask for more vegetables", "", 0.00, mixVegRice1);
		Modifier modifier10 = new Modifier("Ask for no fried meat", "", 0.00, mixVegRice1);
		Modifier modifier11 = new Modifier("Upsize to $3.20", "", 0.20, mixVegRice1);
		Set<Modifier> modifierList4 = new HashSet<Modifier>();
		modifierList4.add(modifier9);
		modifierList4.add(modifier10);
		modifierList4.add(modifier11);
		mixVegRice1.setModifierList(modifierList4);
		
		Modifier modifierMV2 = new Modifier("Upsize to $3.20", "", 0.20, mixVegRice2);
		Set<Modifier> modifierListMV2 = new HashSet<Modifier>();
		modifierListMV2.add(modifierMV2);
		mixVegRice2.setModifierList(modifierListMV2);

		Set<Food> foodList4 = new HashSet<Food>();
		foodList4.add(mixVegRice1);
		foodList4.add(mixVegRice2);
		foodList4.add(mixVegRice3);
		foodList4.add(mixVegRice4);
		chineseMixVegStall.setFoodList(foodList4);

		Stall roastMeatStall = new Stall("Roast Meat Stall", 123, canteen1, null,
				"resources/img/stall/img-roastmeatstall.jpg");
		Food roastfood1 = new Food("Roast Chicken Rice", "2 meat choices $4", 3.00,
				"resources/img/food/img-roastchickenrice.jpg", roastMeatStall);
		Food roastfood2 = new Food("Wanton Mee", "", 3.20, "resources/img/food/img-wantonmee.jpg", roastMeatStall);
		Food roastfood3 = new Food("CharSiew Rice", "2 meat choices $4", 3.00,
				"resources/img/food/img-charsiewrice.jpg", roastMeatStall);
		Food roastfood4 = new Food("Roast Meat Rice", "2 meat choices $4", 3.00,
				"resources/img/food/img-roastmeatrice.jpg", roastMeatStall);

		// for roastfood1 modifiers
		Modifier modifier12 = new Modifier("Add charsiew", "", 1.00, roastfood1);
		Modifier modifier13 = new Modifier("Add roast meat", "", 1.00, roastfood1);

		Set<Modifier> modifierList5 = new HashSet<Modifier>();
		modifierList5.add(modifier12);
		modifierList5.add(modifier13);
		roastfood1.setModifierList(modifierList5);

		// for roastfood3 modifiers
		Modifier modifier14 = new Modifier("Add roast chicken", "", 1.00, roastfood3);
		Modifier modifier15 = new Modifier("Add roast meat", "", 1.00, roastfood3);

		Set<Modifier> modifierList6 = new HashSet<Modifier>();
		modifierList6.add(modifier14);
		modifierList6.add(modifier15);
		roastfood3.setModifierList(modifierList6);

		// for roastfood4 modifiers
		Modifier modifier16 = new Modifier("Add charsiew", "", 1.00, roastfood4);
		Modifier modifier17 = new Modifier("Add roast chicken", "", 1.00, roastfood4);

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

		Stall seafoodTzeCharStall = new Stall("Seafood Tze Char Stall", 92262376, canteen1, null,
				"resources/img/stall/img-seafoodtzecharstall.jpg");

		Food seafood1 = new Food("Horfun", "dry type $4.00", 3.70, "resources/img/food/img-horfun.jpg",
				seafoodTzeCharStall);
		Food seafood2 = new Food("Fried Rice", "", 4.00, "resources/img/food/img-friedrice.jpg", seafoodTzeCharStall);
		Food seafood3 = new Food("Soup With Rice And Egg", "", 4.80,
				"resources/img/food/img-dailysoupwithriceandegg.jpg", seafoodTzeCharStall);
		Food seafood4 = new Food("Hokkien Noodle", "", 4.20, "resources/img/food/img-hokkiennoodle.jpg",
				seafoodTzeCharStall);
		Food seafood5 = new Food("Mee Goreng", "", 4.20, "resources/img/food/img-meegoreng.jpg", seafoodTzeCharStall);
		Food seafood6 = new Food("Ginger & Onion Fish", "", 4.50, "resources/img/food/img-meegoreng.jpg", seafoodTzeCharStall);
		Food seafood7 = new Food("Dried Chilli Chicken", "", 4.50, "resources/img/food/img-meegoreng.jpg", seafoodTzeCharStall);
		Food seafood8 = new Food("Sweet & Sour Pork", "", 4.50, "resources/img/food/img-meegoreng.jpg", seafoodTzeCharStall);
		Food seafood9 = new Food("Pork Rib Soup", "", 4.80, "resources/img/food/img-meegoreng.jpg", seafoodTzeCharStall);
		Food seafood10 = new Food("Black Chicken Soup", "", 5.80, "resources/img/food/img-meegoreng.jpg", seafoodTzeCharStall);
		Food seafood11 = new Food("White Chicken Soup", "", 5.50, "resources/img/food/img-meegoreng.jpg", seafoodTzeCharStall);
		// for seafood1 modifier
		Modifier modifier18 = new Modifier("Dry type", "", 0.30, seafood1);
		Modifier modifier19 = new Modifier("Ask for more vegetables", "", 0.00, seafood1);
		
		Set<Modifier> modifierList8 = new HashSet<Modifier>();
		modifierList8.add(modifier18);
		modifierList8.add(modifier19);
		seafood1.setModifierList(modifierList8);

		
		Set<Modifier> modifierListS9 = new HashSet<Modifier>();
		Modifier modifierS91 = new Modifier("Water Crest", "", 0.00, seafood9);
		Modifier modifierS92 = new Modifier("Old Cucumber", "", 0.00, seafood9);
		Modifier modifierS93 = new Modifier("White Carrot", "", 0.00, seafood9);
		Modifier modifierS94 = new Modifier("Lotus Root", "", 0.00, seafood9);
		Modifier modifierS95 = new Modifier("Bitter Gourd", "", 0.00, seafood9);
		Modifier modifierS96 = new Modifier("Winter Melon", "", 0.00, seafood9);
		Modifier modifierS97 = new Modifier("Szechuan Vegetable", "", 0.00, seafood9);
		modifierListS9.add(modifierS91);
		modifierListS9.add(modifierS92);
		modifierListS9.add(modifierS93);
		modifierListS9.add(modifierS94);
		modifierListS9.add(modifierS95);
		modifierListS9.add(modifierS96);
		modifierListS9.add(modifierS97);
		seafood9.setModifierList(modifierListS9);
		
		Set<Modifier> modifierListS10 = new HashSet<Modifier>();
		Modifier modifierS101 = new Modifier("Herbal", "", 0.00, seafood10);
		modifierListS10.add(modifierS101);
		seafood10.setModifierList(modifierListS10);
		
		Set<Modifier> modifierListS11 = new HashSet<Modifier>();
		Modifier modifierS111 = new Modifier("Herbal", "", 0.00, seafood11);
		Modifier modifierS112 = new Modifier("Winter Melon + Corn", "", 0.00, seafood11);
		modifierListS11.add(modifierS111);
		modifierListS11.add(modifierS112);
		seafood11.setModifierList(modifierListS11);
		
		// for seafood4 modifier
		Modifier modifier20 = new Modifier("Ask for more vegetables", "", 0.00, seafood4);

		Set<Modifier> modifierList9 = new HashSet<Modifier>();
		modifierList9.add(modifier20);
		seafood4.setModifierList(modifierList9);

		// for seafood5 modifier
		Modifier modifier21 = new Modifier("Ask for more vegetables", "", 0.00, seafood5);

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

		Stall fishBeehoonStall = new Stall("Fish Beehoon Stall", 98367790, canteen1, null,
				"resources/img/stall/img-fishbeehoonstall.jpg");
		Food fishBeehoonfood1 = new Food("Fish Soup With Bee Hoon", "add bittergourd: $0.50", 3.50,
				"resources/img/food/img-fishsoupwithbeehoon.jpg", fishBeehoonStall);
		Food fishBeehoonfood2 = new Food("Fish Soup With Rice", "add bittergourd: $0.50", 4.00,
				"resources/img/food/img-fishsoupwithrice.jpg", fishBeehoonStall);
		Food fishBeehoonfood3 = new Food("Fried Fish Soup With Bee Hoon", "add bittergourd: $0.50", 5.00,
				"resources/img/food/img-friedfishsoupwithbeehoon.jpg", fishBeehoonStall);

		// save image into database

		// for fishBeehoonfood1 modifier
		Modifier modifier22 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood1);
		Set<Modifier> modifierList11 = new HashSet<Modifier>();
		modifierList11.add(modifier22);
		fishBeehoonfood1.setModifierList(modifierList11);

		// for fishBeehoonfood2 modifier
		Modifier modifier23 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood2);
		Set<Modifier> modifierList12 = new HashSet<Modifier>();
		modifierList12.add(modifier23);
		fishBeehoonfood2.setModifierList(modifierList12);

		// for fishBeehoonfood3 modifier
		Modifier modifier24 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood3);
		Set<Modifier> modifierList13 = new HashSet<Modifier>();
		modifierList13.add(modifier24);
		fishBeehoonfood3.setModifierList(modifierList13);

		Set<Food> foodList7 = new HashSet<Food>();
		foodList7.add(fishBeehoonfood1);
		foodList7.add(fishBeehoonfood2);
		foodList7.add(fishBeehoonfood3);

		fishBeehoonStall.setFoodList(foodList7);

		Stall fruitStall = new Stall("Fruit Stall", 91151608, canteen1, null, "resources/img/stall/img-fruitstall.jpg");
		Food apple = new Food("Apple", "change to juice $2.50", 0.60, "resources/img/food/img-apple.jpg", fruitStall);
		Food watermelon = new Food("Watermelon", "change to juice $2.50", 0.60, "resources/img/food/img-watermelon.jpg",
				fruitStall);
		Food dragonfruit = new Food("Dragonfruit", "change to juice $2.50", 0.60,
				"resources/img/food/img-dragonfruit.jpg", fruitStall);
		Food pear = new Food("Pear", "change to juice $2.50", 0.60, "resources/img/food/img-pear.jpg", fruitStall);
		Food honeydew = new Food("Honeydew", "change to juice $2.50", 0.70, "resources/img/food/img-honeydew.jpg",
				fruitStall);
		Food papaya = new Food("Papaya", "change to juice $2.50", 0.70, "resources/img/food/img-papaya.jpg",
				fruitStall);
		Food pineapple = new Food("Pineapple", "change to juice $2.50", 0.60, "resources/img/food/img-pineapple.jpg",
				fruitStall);
		Food banana = new Food("Banana", "change to juice $2.50", 0.70, "resources/img/food/img-banana.jpg",
				fruitStall);
		Food agaragar = new Food("Agar Agar", "", 0.60, "resources/img/food/img-agaragar.jpg", fruitStall);
		Food orange = new Food("Orange", "change to juice $2.50", 0.60, "resources/img/food/img-orange.jpg",
				fruitStall);
		Food guava = new Food("Guava", "change to juice $2.50", 0.70, "resources/img/food/img-guava.jpg", fruitStall);
		Food sarawakPineapple = new Food("Sarawak Pineapple", "change to juice $2.50", 1.20,
				"resources/img/food/img-sarawakpineapple.jpg", fruitStall);
		Food mixedFruits = new Food("Mixed Fruits", "upsize $3.50", 3.00, "resources/img/food/img-mixedfruits.jpg",
				fruitStall);

		Modifier modifier25 = new Modifier("Change to juice", "", 1.90, apple);
		Set<Modifier> modifierList14 = new HashSet<Modifier>();
		modifierList14.add(modifier25);
		apple.setModifierList(modifierList14);

		Modifier modifier26 = new Modifier("Change to juice", "", 1.90, watermelon);
		Set<Modifier> modifierList15 = new HashSet<Modifier>();
		modifierList15.add(modifier26);
		watermelon.setModifierList(modifierList14);

		Modifier modifier27 = new Modifier("Change to juice", "", 1.90, dragonfruit);
		Set<Modifier> modifierList16 = new HashSet<Modifier>();
		modifierList16.add(modifier27);
		dragonfruit.setModifierList(modifierList16);

		Modifier modifier28 = new Modifier("Change to juice", "", 1.90, pear);
		Set<Modifier> modifierList17 = new HashSet<Modifier>();
		modifierList17.add(modifier28);
		pear.setModifierList(modifierList17);

		Modifier modifier29 = new Modifier("Change to juice", "", 1.80, honeydew);
		Set<Modifier> modifierList18 = new HashSet<Modifier>();
		modifierList18.add(modifier29);
		honeydew.setModifierList(modifierList18);

		Modifier modifier30 = new Modifier("Change to juice", "", 1.90, papaya);
		Set<Modifier> modifierList19 = new HashSet<Modifier>();
		modifierList19.add(modifier30);
		papaya.setModifierList(modifierList19);

		Modifier modifier31 = new Modifier("Change to juice", "", 1.90, pineapple);
		Set<Modifier> modifierList20 = new HashSet<Modifier>();
		modifierList20.add(modifier31);
		pineapple.setModifierList(modifierList20);

		Modifier modifier32 = new Modifier("Change to juice", "", 1.90, banana);
		Set<Modifier> modifierList21 = new HashSet<Modifier>();
		modifierList21.add(modifier32);
		banana.setModifierList(modifierList21);

		Modifier modifier33 = new Modifier("Change to juice", "", 1.90, orange);
		Set<Modifier> modifierList22 = new HashSet<Modifier>();
		modifierList22.add(modifier33);
		orange.setModifierList(modifierList22);

		Modifier modifier34 = new Modifier("Change to juice", "", 1.80, guava);
		Set<Modifier> modifierList23 = new HashSet<Modifier>();
		modifierList23.add(modifier34);
		guava.setModifierList(modifierList23);

		Modifier modifier35 = new Modifier("Change to juice", "", 1.90, sarawakPineapple);
		Set<Modifier> modifierList24 = new HashSet<Modifier>();
		modifierList24.add(modifier35);
		sarawakPineapple.setModifierList(modifierList24);

		Modifier modifier36 = new Modifier("Mixed fruits upsize", "", 0.50, mixedFruits);
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
		session.save(mfood2);
		session.save(mfood3);
		session.save(mfood4);
		session.save(mfood5);
		session.save(mfood6);
		session.save(mfood7);

		session.save(infood1);
		session.save(infood2);
		session.save(infood3);
		session.save(infood4);
		session.save(infood5);
		session.save(infood6);
		session.save(infood7);
		session.save(infood8);

		session.save(mixVegRice1);
		session.save(mixVegRice2);
		session.save(mixVegRice3);
		session.save(mixVegRice4);

		session.save(roastfood1);
		session.save(roastfood2);
		session.save(roastfood3);
		session.save(roastfood4);

		session.save(seafood1);
		session.save(seafood2);
		session.save(seafood3);
		session.save(seafood4);
		session.save(seafood5);
		session.save(seafood6);
		session.save(seafood7);
		session.save(seafood8);
		session.save(seafood9);
		session.save(seafood10);
		session.save(seafood11);
		

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
		session.save(modifierM21);
		session.save(modifierM22);
		session.save(modifierM31);
		session.save(modifierM32);
		session.save(modifierM41);
		session.save(modifierM42);
		session.save(modifierM51);
		session.save(modifierM52);
		session.save(modifierM61);
		session.save(modifierM62);
		session.save(modifierM71);
		session.save(modifierM72);
		session.save(modifierMV2);
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
		session.save(modifierAMalay1);
		session.save(modifierAMalay2);
		session.save(modifierMixed1);
		session.save(modifierMixed2);
		session.save(modifierA16);
		session.save(modifierA17);
		session.save(modifierA18);
		session.save(modifierA19);
		
		session.save(modifierA27);
		session.save(modifierI1);
		session.save(modifierI2);
		session.save(modifierI3);
		session.save(modifierI4);
		session.save(modifierI5);
		session.save(modifierI6);
		session.save(modifierI7);
		session.save(modifierI8);
		
		session.save(modifierS91);
		session.save(modifierS92);
		session.save(modifierS93);
		session.save(modifierS94);
		session.save(modifierS95);
		session.save(modifierS96);
		session.save(modifierS97);
		session.save(modifierS101);
		session.save(modifierS111);
		session.save(modifierS112);
		
		
		// session.save(canteen2);
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
		session.save(foodA24);
		session.save(foodA25);
		session.save(foodC16);
		session.save(foodMalay1);
		session.save(foodMalay2);

		// **************************************** End insert of mock data
		// ****************************************

		OrderWindow window = new OrderWindow(new DateTime(2015, 12, 2, 16, 36, 0), new DateTime(2020, 12, 5, 16, 36, 0),
				company, canteen1);
		session.save(window); // arnold test data

		session.getTransaction().commit();
		session.flush();
		session.close();

		System.out.println("Test.java completed");

	}

	// public static String loadImageDirectory(String relativePath) {
	// File file = new File(relativePath);
	// byte[] bFile = new byte[(int) file.length()];
	//
	// try {
	// FileInputStream fileInputStream = new FileInputStream(file);
	// // convert file into array of bytes
	// fileInputStream.read(bFile);
	// fileInputStream.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return bFile;
	// }
}
