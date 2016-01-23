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
		// *****BOON HUI ADDED********
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
		//
		if (onOpenshift) {
			// if application on OpenShift
			sessionFactory = new Configuration().configure("hibernate-create.cfg.xml")
					.buildSessionFactory();
			System.out.println("hibernate.cfg.xml is loaded");
		} else {
			// if application on localhost
			sessionFactory = new Configuration().configure("hibernate-local-create.cfg.xml")
					.buildSessionFactory();
			System.out.println("hibernate-local.cfg.xml is loaded");
		}

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// ************************************************************************************************************
		// ************* WARNING, THIS HIBERNATE CONFIG FILE WILL CLEAR
		// EVERYTHING IN DATABASE AND POPULATE WITH BELOW

		Company company2 = new Company("Eastman Chemicals", null, null, "ECM901");
		Company company = new Company("XiaoDingDang Co. (TEST)", null, null, "XDD123");
		Set<String> buildingList = new HashSet<String>();
		buildingList.add("SEMBCORP");
		buildingList.add("PSN");
		buildingList.add("CONTROL");
		buildingList.add("MAINTENANCE");

		AESAlgorithm aes = new AESAlgorithm();
		company2.setDeliveryPointSet(buildingList);
		company.setDeliveryPointSet(buildingList);
		Employee arnold = new Employee(aes.encrypt("chris.cheng.2013@sis.smu.edu.sg" + "1234567"),
				"Chris Cheng", "chris.cheng.2013@sis.smu.edu.sg", 98765432, company);

		arnold.setDeliveryPoint("CONTROL");

		session.save(company);
		session.save(company2);
		session.save(arnold);

		Admin admin = new Admin("admin", PasswordService.encryptPassword("1234567"), "admin1",
				45678925);
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
		Set<Food> foodListB11 = new HashSet<>();

		Stall stall1 = new Stall("Sliced Fish Beehoon Stall", 91379160, null, null,
				"resources/img/stall/img-tjslicefishbeehoon.jpg");
		Food food1 = new Food("Fish Slice Beehoon", "add veg $0.50", 3.70,
				"resources/img/food/img-fishslicebeehoon.jpg", stall1);
		Food food2 = new Food("Fish Soup and Rice", "add veg $0.50", 4.70,
				"resources/img/food/img-fishsoupwithrice.jpg", stall1);

		Food nfood1 = new Food("Fish Head Soup and Beehoon", "add veg $0.50", 4.20, null, stall1);
		Food nfood2 = new Food("Fish Soup", "add veg $0.50", 4.20, null, stall1);
		Food nfood3 = new Food("Fried Fish Soup and Beehoon", "add veg $0.50", 3.70, null, stall1);
		Food nfood4 = new Food("Fish Soup and Beehoon", "add veg $0.50", 3.70, null, stall1);

		Modifier modifierNew1 = new Modifier("Add Veg", "", 0.50, food1);
		Set<Modifier> modifierListNew1 = new HashSet<Modifier>();
		modifierListNew1.add(modifierNew1);
		food1.setModifierList(modifierListNew1);

		Modifier modifierNew2 = new Modifier("Add Veg", "", 0.50, food2);
		Set<Modifier> modifierListNew2 = new HashSet<Modifier>();
		modifierListNew2.add(modifierNew2);
		food2.setModifierList(modifierListNew2);

		Modifier modifierNew3 = new Modifier("Add Veg", "", 0.50, nfood1);
		Set<Modifier> modifierListNew3 = new HashSet<Modifier>();
		modifierListNew3.add(modifierNew3);
		nfood1.setModifierList(modifierListNew3);

		Modifier modifierNew4 = new Modifier("Add Veg", "", 0.50, nfood2);
		Set<Modifier> modifierListNew4 = new HashSet<Modifier>();
		modifierListNew4.add(modifierNew4);
		nfood2.setModifierList(modifierListNew4);

		Modifier modifierNew5 = new Modifier("Add Veg", "", 0.50, nfood3);
		Set<Modifier> modifierListNew5 = new HashSet<Modifier>();
		modifierListNew5.add(modifierNew5);
		nfood3.setModifierList(modifierListNew5);

		Modifier modifierNew6 = new Modifier("Add Veg", "", 0.50, nfood4);
		Set<Modifier> modifierListNew6 = new HashSet<Modifier>();
		modifierListNew6.add(modifierNew6);
		nfood4.setModifierList(modifierListNew6);

		foodListB1.add(food1);
		foodListB1.add(food2);
		foodListB1.add(nfood1);
		foodListB1.add(nfood2);
		foodListB1.add(nfood3);
		foodListB1.add(nfood4);

		stall1.setFoodList(foodListB1);

		Stall stall2 = new Stall("Malay Food Stall", 81145966, null, null,
				"resources/img/stall/img-tjmalaystall.jpg");
		Food food3 = new Food("Chilli Chicken + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malaychillichicken.jpg", stall2);
		Food food4 = new Food("Fried Chicken + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malayfriedchicken.jpg", stall2);
		Food food5 = new Food("Curry Fish + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malaycurryfish.jpg", stall2);
		Food foodMalay1 = new Food("Fried Fish + Veg", "upsize to $3.50", 3.00,
				"resources/img/food/img-malayfriedfish.jpg", stall2);
		// Food foodMalay2 = new Food("Mutton + Veg", "upsize to $3.50", 3.00,
		// "resources/img/food/img-malaymutton.jpg", stall2);

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

		// Modifier modifierAMalay2 = new Modifier("Add Veg", "", 0.50, foodMalay2);
		// Set<Modifier> modifierListAMalay2 = new HashSet<Modifier>();
		// modifierListAMalay1.add(modifierAMalay2);
		// foodMalay2.setModifierList(modifierListAMalay1);

		foodListB2.add(food3);
		foodListB2.add(food4);
		foodListB2.add(food5);
		foodListB2.add(foodMalay1);
		// foodListB2.add(foodMalay2);
		stall2.setFoodList(foodListB2);

		Stall stall3 = new Stall("Mixed Rice Stall", 93482772, null, null,
				"resources/img/stall/img-tjmixedrice.jpg");
		Food food6 = new Food("1 Meat + 2 Veg", "", 3.00, "resources/img/food/img-mixedrice.jpg",
				stall3);
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

		// Stall stall4 = new Stall("Wanton Mee Stall", 0, null, foodListB4,
		// "resources/img/stall/img-tjwantonmee.jpg");
		// Food food8 = new Food("Wanton Mee(Dry)", "", 3.00,
		// "resources/img/food/img-wantonmee.jpg",
		// stall4);
		//
		// Food nfood5 = new Food("Wanton Mee(Soup)", "", 3.00, null, stall4);
		//
		// foodListB4.add(food8);
		// foodListB4.add(nfood5);
		// stall4.setFoodList(foodListB4);

		Stall stall5 = new Stall("Indian Food Stall", 93841009, null, null,
				"resources/img/stall/img-indianstall.jpg");
		Food food9 = new Food("Curry Chicken Briyani", "", 3.50,
				"resources/img/food/img-currychickenbriyani.jpg", stall5);
		Food food10 = new Food("Fish Briyani", "", 3.50, "resources/img/food/img-fishbiryani.jpg",
				stall5);
		Food food11 = new Food("Vegetarian Briyani", "", 4.00,
				"resources/img/food/img-vegetablebriyani.jpg", stall5);
		Food food12 = new Food("Fried Chicken Briyani", "", 4.00,
				"resources/img/food/img-friedchickenbiryani.jpg", stall5);

		Food nfood6 = new Food("Mutton Briyani", "", 4.00, null, stall5);

		foodListB5.add(food9);
		foodListB5.add(food10);
		foodListB5.add(food11);
		foodListB5.add(food12);
		foodListB5.add(nfood6);
		stall5.setFoodList(foodListB5);

		Stall stall6 = new Stall("Roast Duck & Chicken Rice Stall", 98427347, null, null,
				"resources/img/stall/img-roastmeatstall.jpg");
		Food food13 = new Food("Roast Chicken Rice", "", 2.50,
				"resources/img/food/img-roastchickenrice.jpg", stall6);
		Food food14 = new Food("CharSiew Rice", "", 2.50,
				"resources/img/food/img-charsiewrice.jpg", stall6);
		Food food15 = new Food("Roast Meat Rice", "", 2.50,
				"resources/img/food/img-roastmeatrice.jpg", stall6);
		// Food foodC16 = new Food("CharSiew + Roast Meat Rice", "", 3.00,
		// "resources/img/food/img-charsiewrice.jpg", stall6);

		Modifier modifierNew14 = new Modifier("Add charsiew", "", 1.00, food13);
		Modifier modifierNew15 = new Modifier("Add roast meat", "", 1.00, food13);
		Set<Modifier> modifierListNew18 = new HashSet<Modifier>();
		modifierListNew18.add(modifierNew14);
		modifierListNew18.add(modifierNew15);
		food13.setModifierList(modifierListNew18);

		Modifier modifierNew16 = new Modifier("Add roast chicken", "", 1.00, food14);
		Modifier modifierNew17 = new Modifier("Add roast meat", "", 1.00, food14);
		Set<Modifier> modifierListNew19 = new HashSet<Modifier>();
		modifierListNew19.add(modifierNew16);
		modifierListNew19.add(modifierNew17);
		food14.setModifierList(modifierListNew19);

		Modifier modifierNew18 = new Modifier("Add roast chicken", "", 1.00, food15);
		Modifier modifierNew19 = new Modifier("Add charsiew", "", 1.00, food15);
		Set<Modifier> modifierListNew16 = new HashSet<Modifier>();
		modifierListNew16.add(modifierNew18);
		modifierListNew16.add(modifierNew19);
		food15.setModifierList(modifierListNew16);

		// Modifier modifierNew20 = new Modifier("Add roast chicken", "", 1.00, foodC16);
		// Set<Modifier> modifierListNew17 = new HashSet<Modifier>();
		// modifierListNew17.add(modifierNew20);
		// foodC16.setModifierList(modifierListNew17);
		//
		// foodListB6.add(foodC16);
		foodListB6.add(food13);
		foodListB6.add(food14);
		foodListB6.add(food15);
		stall6.setFoodList(foodListB6);

		Stall stall7 = new Stall("REX(Halal)", 62684806, null, null,
				"resources/img/stall/img-tj.jpg");
		Food food16 = new Food("Chicken Rice(Chicken Wing)", "", 3.00,
				"resources/img/food/img-friedchickenbiryani.jpg", stall7);
		Food food17 = new Food("Chicken Fried Rice", "", 3.00,
				"resources/img/food/img-chickenfriedrice.jpg", stall7);
		Food food18 = new Food("Seafood Fried Rice", "", 3.50,
				"resources/img/food/img-seafoodfriedrice.jpg", stall7);
		Food food19 = new Food("Beef Fried Rice", "", 4.00,
				"resources/img/food/img-beeffriedrice.jpg", stall7);
		Food food20 = new Food("Seafood Horfun(Wet)", "", 3.50,
				"resources/img/food/img-seafoodhorfun.jpg", stall7);
		Food food21 = new Food("Seafood Horfun(Dry)", "", 4.00,
				"resources/img/food/img-horfun.jpg", stall7);
		Food food22 = new Food("Chicken Porridge", "", 3.00,
				"resources/img/food/img-chickenporridge.jpg", stall7);
		Food food23 = new Food("Fish Porridge", "", 3.00,
				"resources/img/food/img-fishporridge.jpg", stall7);
		Food foodA24 = new Food("Chicken Horfun(Wet)", "", 3.50,
				"resources/img/food/img-chickenhorfunwet.jpg", stall7);
		Food foodA25 = new Food("Chicken Horfun(Dry)", "", 4.00,
				"resources/img/food/img-chickenhorfundry.jpg", stall7);

		Food nfood15 = new Food("Chicken Horfun(Gravy)", "", 3.50, null, stall7);
		Food nfood16 = new Food("Chicken Beehoon(Gravy)", "", 3.50, null, stall7);

		Food nfood17 = new Food("Chicken Horfun(Fried)", "", 4.00, null, stall7);
		Food nfood18 = new Food("Chicken Beehoon(Fried)", "", 4.00, null, stall7);

		Food nfood19 = new Food("Seafood Horfun(Gravy)", "", 3.50, null, stall7);
		Food nfood20 = new Food("Seafood Beehoon(Gravy)", "", 3.50, null, stall7);

		Food nfood21 = new Food("Seafood Horfun(Fried)", "", 4.00, null, stall7);
		Food nfood22 = new Food("Seafood Beehoon(Fried)", "", 4.00, null, stall7);

		Food nfood23 = new Food("Beef Horfun(Gravy)", "", 4.00, null, stall7);
		Food nfood24 = new Food("Beef Beehoon(Gravy)", "", 4.00, null, stall7);

		Food nfood25 = new Food("Beef Horfun(Fried)", "", 4.00, null, stall7);
		Food nfood26 = new Food("Beef Beehoon(Fried)", "", 4.00, null, stall7);

		Food nfood27 = new Food("Chicken Rice(Chicken Breast)", "", 3.00, null, stall7);

		Food nfood28 = new Food("Chicken Rice(Chicken Thigh)", "", 3.00, null, stall7);

		Food nfood29 = new Food("Chicken Rice(Drumstick)", "", 3.50, null, stall7);

		// Modifier modifierA16 = new Modifier("Change to drumstick", "", 0.50, food16);
		// Modifier modifierA17 = new Modifier("Chicken Wing", "", 0.0, food16);
		// Modifier modifierA18 = new Modifier("Chicken Breast", "", 0.0, food16);
		// Modifier modifierA19 = new Modifier("Chicken Thigh", "", 0.0, food16);
		// Set<Modifier> modifierListA16 = new HashSet<Modifier>();
		// modifierListA16.add(modifierA16);
		// modifierListA16.add(modifierA17);
		// modifierListA16.add(modifierA18);
		// modifierListA16.add(modifierA19);
		// food16.setModifierList(modifierListA16);

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

		foodListB7.add(nfood15);
		foodListB7.add(nfood16);
		foodListB7.add(nfood17);
		foodListB7.add(nfood18);
		foodListB7.add(nfood19);
		foodListB7.add(nfood20);
		foodListB7.add(nfood21);
		foodListB7.add(nfood22);
		foodListB7.add(nfood23);
		foodListB7.add(nfood24);
		foodListB7.add(nfood25);
		foodListB7.add(nfood26);
		foodListB7.add(nfood27);
		foodListB7.add(nfood28);
		foodListB7.add(nfood29);
		stall7.setFoodList(foodListB7);

		Stall stall8 = new Stall("Vegetarian Stall", 91182963, null, null,
				"resources/img/stall/img-tj.jpg");
		Food food24 = new Food("Vegetarian Rice", "add veg $0.50", 2.50,
				"resources/img/food/img-vegrice.jpg", stall8);
		Food food25 = new Food("Vegetarian Beehoon", "add veg $0.50", 2.50,
				"resources/img/food/img-vegetarianbeehoon.jpg", stall8);
		Food food26 = new Food("Vegetarian Mee", "add veg $0.50", 2.50,
				"resources/img/food/img-vergetarianmee.jpg", stall8);

		Food nfood7 = new Food("Vegetarian Kway Tiao", "add veg $0.50", 2.50, null, stall8);
		Food nfood8 = new Food("Vegetarian Beehoon Kway Tiao", "add veg $0.50", 2.50, null, stall8);

		Modifier modifierNew7 = new Modifier("Add Veg", "", 0.50, food24);
		Set<Modifier> modifierListNew7 = new HashSet<Modifier>();
		modifierListNew7.add(modifierNew7);
		food24.setModifierList(modifierListNew7);

		Modifier modifierNew8 = new Modifier("Add Veg", "", 0.50, food25);
		Set<Modifier> modifierListNew8 = new HashSet<Modifier>();
		modifierListNew8.add(modifierNew8);
		food25.setModifierList(modifierListNew8);

		Modifier modifierNew9 = new Modifier("Add Veg", "", 0.50, food26);
		Set<Modifier> modifierListNew9 = new HashSet<Modifier>();
		modifierListNew9.add(modifierNew9);
		food26.setModifierList(modifierListNew9);

		Modifier modifierNew10 = new Modifier("Add Veg", "", 0.50, nfood7);
		Set<Modifier> modifierListNew10 = new HashSet<Modifier>();
		modifierListNew10.add(modifierNew10);
		nfood7.setModifierList(modifierListNew10);

		Modifier modifierNew11 = new Modifier("Add Veg", "", 0.50, nfood8);
		Set<Modifier> modifierListNew11 = new HashSet<Modifier>();
		modifierListNew11.add(modifierNew11);
		nfood8.setModifierList(modifierListNew11);

		foodListB8.add(food24);
		foodListB8.add(food25);
		foodListB8.add(food26);
		foodListB8.add(nfood7);
		foodListB8.add(nfood8);
		stall8.setFoodList(foodListB8);

		Stall stall9 = new Stall("Minced Meat Noodles Stall(Closed On Tuesday)", 93686070, null,
				null, "resources/img/stall/img-tj.jpg");
		Food food27 = new Food("Minced Meat Noodles(Mee Kia)", "upsize to $3.20", 2.70,
				"resources/img/food/img-mincedmeatnoodles.jpg", stall9);

		Food nfood9 = new Food("Minced Meat Noodles(Mee Pok)", "upsize to $3.20", 2.70, null,
				stall9);
		Food nfood10 = new Food("Minced Meat Noodles(Mee Tai Mak)", "upsize to $3.20", 2.70, null,
				stall9);

		Modifier modifierA27 = new Modifier("Upsize to $3.20", "", 0.50, food27);
		Set<Modifier> modifierListA27 = new HashSet<Modifier>();
		modifierListA27.add(modifierA27);
		food27.setModifierList(modifierListA27);

		Modifier modifierNew12 = new Modifier("Upsize to $3.20", "", 0.50, nfood9);
		Set<Modifier> modifierListNew12 = new HashSet<Modifier>();
		modifierListNew12.add(modifierNew12);
		nfood9.setModifierList(modifierListNew12);

		Modifier modifierNew13 = new Modifier("Upsize to $3.20", "", 0.50, nfood10);
		Set<Modifier> modifierListNew13 = new HashSet<Modifier>();
		modifierListNew13.add(modifierNew13);
		nfood10.setModifierList(modifierListNew13);

		foodListB9.add(food27);
		foodListB9.add(nfood9);
		foodListB9.add(nfood10);

		stall9.setFoodList(foodListB9);

		Stall stall10 = new Stall("Noodle Stall", 96946576, null, null,
				"resources/img/stall/img-tj.jpg");
		Food food28 = new Food("Lor Mee(Thin Beehoon)", "", 3.00,
				"resources/img/food/img-lormee.jpg", stall10);
		Food food29 = new Food("Prawn Mee", "", 3.00, "resources/img/food/img-prawnnoodle.jpg",
				stall10);
		Food food30 = new Food("Fishball Noodles(Thin Beehoon)", "", 3.00,
				"resources/img/food/img-fishballnoodle.jpg", stall10);
		Food food31 = new Food("Laksa", "", 3.00, "resources/img/food/img-laksa.jpg", stall10);
		Food food32 = new Food("Hokkien Mee", "", 3.00,
				"resources/img/food/img-hokkiennoodles.jpg", stall10);
		Food food33 = new Food("Dumpling Noodles", "", 3.00,
				"resources/img/food/img-dumplingnoodle.jpg", stall10);

		Food nfood11 = new Food("Fishball Noodles(Thick Beehoon)", "", 3.00, null, stall10);
		Food nfood12 = new Food("Fishball Noodles(Yellow Noodles)", "", 3.00, null, stall10);
		Food nfood13 = new Food("Lor Mee(Thick Beehoon)", "", 3.00, null, stall10);
		Food nfood14 = new Food("Lor Mee(Yellow Noodles)", "", 3.00, null, stall10);

		foodListB10.add(food28);
		foodListB10.add(food29);
		foodListB10.add(food30);
		foodListB10.add(food31);
		foodListB10.add(food32);
		foodListB10.add(food33);
		foodListB10.add(nfood11);
		foodListB10.add(nfood12);
		foodListB10.add(nfood13);
		foodListB10.add(nfood14);
		stall10.setFoodList(foodListB10);

		Stall stall11 = new Stall("Fruits Stall", 91151608, null, null, null);

		Food food34 = new Food("Apple", "", 0.60, "resources/img/food/img-apple.jpg", stall11);

		Food food35 = new Food("Apple Juice", "", 2.50, null, stall11);

		Food food36 = new Food("Watermelon", "", 0.60, "resources/img/food/img-watermelon.jpg",
				stall11);

		Food food37 = new Food("Watermelon Juice", "", 2.50, null, stall11);

		Food food38 = new Food("Dragonfruit", "", 0.60, "resources/img/food/img-dragonfruit.jpg",
				stall11);

		Food food39 = new Food("Dragonfruit Juice", "", 2.50, null, stall11);

		Food food40 = new Food("Pear", "", 0.60, "resources/img/food/img-pear.jpg", stall11);

		Food food41 = new Food("Pear Juice", "", 2.50, null, stall11);

		Food food42 = new Food("Honeydew", "", 0.70, "resources/img/food/img-honeydew.jpg", stall11);

		Food food43 = new Food("Honeydew Juice", "", 2.50, null, stall11);

		Food food44 = new Food("Papaya", "", 0.70, "resources/img/food/img-papaya.jpg", stall11);

		Food food45 = new Food("Papaya Juice", "", 2.60, null, stall11);

		Food food46 = new Food("Pineapple", "", 0.60, "resources/img/food/img-pineapple.jpg",
				stall11);

		Food food47 = new Food("Pineapple Juice", "", 2.50, null, stall11);

		Food food48 = new Food("Banana", "", 0.70, "resources/img/food/img-banana.jpg", stall11);

		Food food49 = new Food("Banana Juice", "", 2.60, null, stall11);

		Food food50 = new Food("Agar Agar", "", 0.60, "resources/img/food/img-agaragar.jpg",
				stall11);

		Food food51 = new Food("Orange", "", 0.60, "resources/img/food/img-orange.jpg", stall11);

		Food food52 = new Food("Orange Juice", "", 2.50, null, stall11);

		Food food53 = new Food("Guava", "", 0.70, "resources/img/food/img-guava.jpg", stall11);

		Food food54 = new Food("Guava Juice", "", 2.50, null, stall11);

		Food food55 = new Food("Sarawak Pineapple", "", 1.20,
				"resources/img/food/img-sarawakpineapple.jpg", stall11);

		Food food56 = new Food("Sarawak Pineapple Juice", "", 3.10, null, stall11);

		Food food57 = new Food("Mixed Fruits", "upsize $3.50", 3.00,
				"resources/img/food/img-mixedfruits.jpg", stall11);

		Modifier modifierA28 = new Modifier("Mixed fruits upsize", "", 0.50, food57);
		Set<Modifier> modifierListA17 = new HashSet<Modifier>();
		modifierListA17.add(modifierA28);
		food57.setModifierList(modifierListA17);

		Modifier modifierNew23 = new Modifier("Add powder", "", 0.00, food53);
		Set<Modifier> modifierListNew21 = new HashSet<Modifier>();
		modifierListNew21.add(modifierNew23);
		food53.setModifierList(modifierListNew21);

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

		foodListB11.add(food47);
		foodListB11.add(food48);
		foodListB11.add(food49);
		foodListB11.add(food50);
		foodListB11.add(food51);
		foodListB11.add(food52);
		foodListB11.add(food53);
		foodListB11.add(food54);

		foodListB11.add(food55);
		foodListB11.add(food56);
		foodListB11.add(food57);

		stall11.setFoodList(foodListB11);

		Canteen canteen2 = new Canteen("Taman Jurong Market and Food Centre",
				"3 Yung Sheng Rd, Singapore 618499", null);
		stall1.setCanteen(canteen2);
		stall2.setCanteen(canteen2);
		stall3.setCanteen(canteen2);
		// stall4.setCanteen(canteen2);
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
		// stallList2.add(stall4);
		stallList2.add(stall5);
		stallList2.add(stall6);
		stallList2.add(stall7);
		stallList2.add(stall8);
		stallList2.add(stall9);
		stallList2.add(stall10);
		stallList2.add(stall11);

		canteen2.setStallList(stallList2);

		// oasis

		Canteen canteen1 = new Canteen("Oasis Food Centre", "3 Yung Sheng Rd, Singapore 618499",
				null);

		Stall kuehStall = new Stall("Oasis Kueh Stall", 90685620, canteen1, null,
				"resources/img/stall/img-oasiskuehstall.jpg");
		Food kuehfood1 = new Food("Chee Cheong Fun", "", 0.60,
				"resources/img/food/img-cheecheongfun.jpg", kuehStall);
		Food kuehfood2 = new Food("Yam cake", "", 1.20, "resources/img/food/img-yamcake.jpg",
				kuehStall);
		Food kuehfood3 = new Food("Dumpling", "", 0.90, "resources/img/food/img-dumpling.png",
				kuehStall);
		Food kuehfood4 = new Food("Pau", "", 0.80, "resources/img/food/img-pau.jpg", kuehStall);
		Food kuehfood5 = new Food("Lor Mai Kai", "", 1.50, "resources/img/food/img-lormaikai.jpg",
				kuehStall);
		Food kuehfood6 = new Food("Fan Choy", "", 1.80, "resources/img/food/img-fanchoy.jpg",
				kuehStall);
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
		Food mfood1 = new Food("Curry Chicken + Veg", "more vegs, less fried meat", 3.70,
				"resources/img/food/img-mixedvegricemalay.jpg", malayStall);
		Food mfood2 = new Food("Asam Fish + Veg", "more vegs, less fried meat", 3.70,
				"resources/img/food/img-malayasamfish.jpg", malayStall);
		Food mfood3 = new Food("Curry Fish + Veg", "more vegs, less fried meat", 3.70, null,
				malayStall);
		// Food mfood4 = new Food("Beef + Veg", "more vegs, less fried meat", 3.70,
		// "resources/img/food/img-malaybeef.jpg", malayStall);
		Food mfood5 = new Food("Fried Chicken + Veg", "more vegs, less fried meat", 3.70,
				"resources/img/food/img-malayfriedchicken.jpg", malayStall);
		Food mfood6 = new Food("Fried Fish + Veg", "more vegs, less fried meat", 3.70,
				"resources/img/food/img-malayfriedfish.jpg", malayStall);
		Food mfood7 = new Food("Stingray + Veg", "more vegs, less fried meat", 3.70,
				"resources/img/food/img-malaystingray.jpg", malayStall);
		// for mfood1 modifiers
		Modifier modifier1 = new Modifier("more vegetables", "", 0.00, mfood1);
		Modifier modifier2 = new Modifier("No egg", "", 0.00, mfood1);
		Set<Modifier> modifierList1 = new HashSet<Modifier>();
		modifierList1.add(modifier1);
		modifierList1.add(modifier2);

		mfood1.setModifierList(modifierList1);

		// for mfood2 modifiers
		Modifier modifierM21 = new Modifier("more vegetables", "", 0.00, mfood2);
		Modifier modifierM22 = new Modifier("No egg", "", 0.00, mfood2);
		Set<Modifier> modifierList2 = new HashSet<Modifier>();
		modifierList2.add(modifierM21);
		modifierList2.add(modifierM22);
		mfood2.setModifierList(modifierList2);

		// for mfood3 modifiers
		Modifier modifierM31 = new Modifier("more vegetables", "", 0.00, mfood3);
		Modifier modifierM32 = new Modifier("No egg", "", 0.00, mfood3);
		Set<Modifier> modifierList3 = new HashSet<Modifier>();
		modifierList3.add(modifierM31);
		modifierList3.add(modifierM32);
		mfood3.setModifierList(modifierList3);
		//
		// // for mfood4 modifiers
		// Modifier modifierM41 = new Modifier("more vegetables", "", 0.00, mfood4);
		// Modifier modifierM42 = new Modifier("No egg", "", 0.00, mfood4);
		// Set<Modifier> modifierListm4 = new HashSet<Modifier>();
		// modifierListm4.add(modifierM41);
		// modifierListm4.add(modifierM42);
		// mfood4.setModifierList(modifierListm4);

		Modifier modifierM51 = new Modifier("more vegetables", "", 0.00, mfood5);
		Modifier modifierM52 = new Modifier("No egg", "", 0.00, mfood5);
		Set<Modifier> modifierListm5 = new HashSet<Modifier>();
		modifierListm5.add(modifierM51);
		modifierListm5.add(modifierM52);
		mfood5.setModifierList(modifierListm5);

		Modifier modifierM61 = new Modifier("more vegetables", "", 0.00, mfood6);
		Modifier modifierM62 = new Modifier("No egg", "", 0.00, mfood6);
		Set<Modifier> modifierListm6 = new HashSet<Modifier>();
		modifierListm6.add(modifierM61);
		modifierListm6.add(modifierM62);
		mfood6.setModifierList(modifierListm6);

		Modifier modifierM71 = new Modifier("more vegetables", "", 0.00, mfood7);
		Modifier modifierM72 = new Modifier("No egg", "", 0.00, mfood7);
		Set<Modifier> modifierListm7 = new HashSet<Modifier>();
		modifierListm7.add(modifierM71);
		modifierListm7.add(modifierM72);
		mfood7.setModifierList(modifierListm7);

		Set<Food> foodList2 = new HashSet<Food>();
		foodList2.add(mfood1);
		foodList2.add(mfood2);
		foodList2.add(mfood3);
		// foodList2.add(mfood4);
		foodList2.add(mfood5);
		foodList2.add(mfood6);
		foodList2.add(mfood7);

		malayStall.setFoodList(foodList2);

		Stall indianStall = new Stall("Indian Stall", 98717752, canteen1, null,
				"resources/img/stall/img-indianstall.jpg");
		Food infood1 = new Food("White Rice(Chicken)", "Chicken/Fish/Mutton", 4.00,
				"resources/img/food/img-whiterice.jpg", indianStall);
		Food infood2 = new Food("White Rice(Fish)", "Chicken/Fish/Mutton", 4.00,
				"resources/img/food/img-whiterice.jpg", indianStall);
		Food infood3 = new Food("White Rice(Mutton)", "Chicken/Fish/Mutton", 4.00,
				"resources/img/food/img-whiterice.jpg", indianStall);
		Food infood4 = new Food("Vegetable White Rice", "", 3.00,
				"resources/img/food/img-vegetablewhiterice.jpg", indianStall);
		Food infood5 = new Food("Chicken Briyani", "Chicken", 5.00,
				"resources/img/food/img-currychickenbriyani.jpg", indianStall);
		Food infood6 = new Food("Fish Briyani", "Fish", 5.00,
				"resources/img/food/img-fishbiryani.jpg", indianStall);
		Food infood7 = new Food("Mutton Briyani", "Mutton", 5.00,
				"resources/img/food/img-muttonbriyani.jpg", indianStall);
		Food infood8 = new Food("Vegetarian Briyani", "Vegetarian", 5.00,
				"resources/img/food/img-vegetablebriyani.jpg", indianStall);

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
		Food mixVegRice1 = new Food("1 Meat 2 Veg",
				"more meat, less fried meat, or upsize to $3.50", 3.00,
				"resources/img/food/img-mixvegricechinese.jpg", chineseMixVegStall);
		Food mixVegRice2 = new Food("All Veg", "", 3.00,
				"resources/img/food/img-mixvegricechinese.jpg", chineseMixVegStall);
		// Food mixVegRice3 = new Food("Porridge", "", 3.20,
		// "resources/img/food/img-fishporridge.jpg", chineseMixVegStall);
		Food mixVegRice4 = new Food("Porridge(1 Meat + 2 Veg)", "", 3.70, null, chineseMixVegStall);

		Food mixVegRice5 = new Food("Porridge(All Veg)", "", 3.20, null, chineseMixVegStall);

		// for mixVegRice1 modifiers
		Modifier modifier9 = new Modifier("more vegetables", "", 0.00, mixVegRice1);
		Modifier modifier10 = new Modifier("no fried meat", "", 0.00, mixVegRice1);
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

		Modifier modifierNew21 = new Modifier("more vegetables", "", 0.00, mixVegRice4);
		Modifier modifierNew22 = new Modifier("no fried meat", "", 0.00, mixVegRice4);
		Set<Modifier> modifierListNew20 = new HashSet<Modifier>();
		modifierListNew20.add(modifierNew21);
		modifierListNew20.add(modifierNew22);

		mixVegRice4.setModifierList(modifierListNew20);

		Set<Food> foodList4 = new HashSet<Food>();
		foodList4.add(mixVegRice1);
		foodList4.add(mixVegRice2);
		// foodList4.add(mixVegRice3);
		foodList4.add(mixVegRice4);
		foodList4.add(mixVegRice5);
		chineseMixVegStall.setFoodList(foodList4);

		Stall roastMeatStall = new Stall("Roast Meat Stall", 123, canteen1, null,
				"resources/img/stall/img-roastmeatstall.jpg");
		Food roastfood1 = new Food("Roast Chicken Rice", "2 meat choices $4", 3.00,
				"resources/img/food/img-roastchickenrice.jpg", roastMeatStall);
		Food roastfood2 = new Food("Wanton Mee(Dry)", "", 3.20,
				"resources/img/food/img-wantonmee.jpg", roastMeatStall);
		Food roastfood3 = new Food("CharSiew Rice", "2 meat choices $4", 3.00,
				"resources/img/food/img-charsiewrice.jpg", roastMeatStall);
		Food roastfood4 = new Food("Roast Meat Rice", "2 meat choices $4", 3.00,
				"resources/img/food/img-roastmeatrice.jpg", roastMeatStall);

		Food roastfood5 = new Food("Wanton Mee(Soup)", "", 3.20, null, roastMeatStall);

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
		foodList5.add(roastfood5);
		roastMeatStall.setFoodList(foodList5);

		Stall seafoodTzeCharStall = new Stall("Seafood Tze Char Stall", 92262376, canteen1, null,
				"resources/img/stall/img-seafoodtzecharstall.jpg");

		Food seafood1 = new Food("Horfun (Dry)", "dry type $4.00", 4.00,
				"resources/img/food/img-horfun.jpg", seafoodTzeCharStall);
		Food seafood2 = new Food("Fried Rice", "", 4.00, "resources/img/food/img-friedrice.jpg",
				seafoodTzeCharStall);
		Food seafood3 = new Food("Horfun (Wet)", "", 3.70, null, seafoodTzeCharStall);
		Food seafood4 = new Food("Hokkien Noodle", "", 4.20, null, seafoodTzeCharStall);
		Food seafood5 = new Food("Mee Goreng", "", 4.20, "resources/img/food/img-meegoreng.jpg",
				seafoodTzeCharStall);
		Food seafood6 = new Food("Ginger & Onion Fish", "", 4.50,
				"resources/img/food/img-gingeronionfishrice.jpg", seafoodTzeCharStall);
		Food seafood7 = new Food("Dried Chilli Chicken", "", 4.50,
				"resources/img/food/img-driedchillichicken.jpg", seafoodTzeCharStall);
		Food seafood8 = new Food("Sweet & Sour Pork", "", 4.50,
				"resources/img/food/img-sweetandsourpork.jpg", seafoodTzeCharStall);
		Food seafood9 = new Food("Pork Rib Soup", "", 4.80,
				"resources/img/food/img-porkribsoup.jpg", seafoodTzeCharStall);
		Food seafood10 = new Food("Black Chicken Soup", "", 5.80,
				"resources/img/food/img-blackchickensoup.jpg", seafoodTzeCharStall);
		Food seafood11 = new Food("White Chicken Soup", "", 5.50,
				"resources/img/food/img-whitechickensoup.jpg", seafoodTzeCharStall);

		// Food seafood12 = new Food("Horfun(Gravy)", "", 3.70, null, seafoodTzeCharStall);
		// *****NOTE******
		// Food seafood13 = new Food("Chicken Horfun", "", 3.00, null, seafoodTzeCharStall);
		// Food seafood14 = new Food("Chicken Horfun(With Gravy)", "", 3.00, null,
		// seafoodTzeCharStall);
		// Food seafood15 = new Food("Chicken Beehoon", "", 3.00, null, seafoodTzeCharStall);
		// Food seafood16 = new Food("Chicken Beehoon(With Gravy)", "", 3.00, null,
		// seafoodTzeCharStall);
		// ****NOTE********
		// for seafood1 modifier
		// Modifier modifier18 = new Modifier("Dry type", "", 0.30, seafood1);
		// Modifier modifier19 = new Modifier("more vegetables", "", 0.00, seafood1);
		//
		// Set<Modifier> modifierList8 = new HashSet<Modifier>();
		// modifierList8.add(modifier18);
		// modifierList8.add(modifier19);
		// seafood1.setModifierList(modifierList8);

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
		Modifier modifier20 = new Modifier("more vegetables", "", 0.00, seafood4);

		Set<Modifier> modifierList9 = new HashSet<Modifier>();
		modifierList9.add(modifier20);
		seafood4.setModifierList(modifierList9);

		// for seafood5 modifier
		Modifier modifier21 = new Modifier("more vegetables", "", 0.00, seafood5);

		Set<Modifier> modifierList10 = new HashSet<Modifier>();
		modifierList10.add(modifier21);
		seafood5.setModifierList(modifierList10);

		Set<Food> foodList6 = new HashSet<Food>();
		foodList6.add(seafood1);
		foodList6.add(seafood2);
		foodList6.add(seafood3);
		foodList6.add(seafood4);
		foodList6.add(seafood5);
		foodList6.add(seafood6);
		foodList6.add(seafood7);
		foodList6.add(seafood8);
		foodList6.add(seafood9);
		foodList6.add(seafood10);
		foodList6.add(seafood11);
		// foodList6.add(seafood12);
		// foodList6.add(seafood13);
		// foodList6.add(seafood14);
		// foodList6.add(seafood15);
		// foodList6.add(seafood16);

		seafoodTzeCharStall.setFoodList(foodList6);

		Stall fishBeehoonStall = new Stall("Fish Beehoon Stall", 98367790, canteen1, null,
				"resources/img/stall/img-fishbeehoonstall.jpg");
		Food fishBeehoonfood1 = new Food("Fish Soup With Beehoon", "add bittergourd: $0.50", 3.50,
				"resources/img/food/img-fishsoupwithbeehoon.jpg", fishBeehoonStall);
		Food fishBeehoonfood2 = new Food("Fish Soup With Rice", "add bittergourd: $0.50", 4.00,
				"resources/img/food/img-fishsoupwithrice.jpg", fishBeehoonStall);
		Food fishBeehoonfood3 = new Food("Fried Fish Soup With Beehoon", "add bittergourd: $0.50",
				5.00, "resources/img/food/img-friedfishsoupwithbeehoon.jpg", fishBeehoonStall);

		Food fishBeehoonfood4 = new Food("Fish Soup", "add bittergourd: $0.50", 3.50, null,
				fishBeehoonStall);
		Food fishBeehoonfood5 = new Food("Fish Soup With Mee Sua", "add bittergourd: $0.50", 3.50,
				null, fishBeehoonStall);
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

		Modifier modifierNew25 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood4);
		Set<Modifier> modifierListNew14 = new HashSet<Modifier>();
		modifierListNew14.add(modifierNew25);
		fishBeehoonfood4.setModifierList(modifierListNew14);

		Modifier modifierNew26 = new Modifier("Add bittergourd", "", 0.50, fishBeehoonfood5);
		Set<Modifier> modifierListNew15 = new HashSet<Modifier>();
		modifierListNew15.add(modifierNew26);
		fishBeehoonfood5.setModifierList(modifierListNew15);

		Set<Food> foodList7 = new HashSet<Food>();
		foodList7.add(fishBeehoonfood1);
		foodList7.add(fishBeehoonfood2);
		foodList7.add(fishBeehoonfood3);
		foodList7.add(fishBeehoonfood4);
		foodList7.add(fishBeehoonfood5);

		fishBeehoonStall.setFoodList(foodList7);

		Stall fruitStall = new Stall("Fruit Stall", 91151608, canteen1, null,
				"resources/img/stall/img-fruitstall.jpg");

		Food apple = new Food("Apple", "", 0.60, "resources/img/food/img-apple.jpg", fruitStall);

		Food appleJuice = new Food("Apple Juice", "", 2.50, null, fruitStall);

		Food watermelon = new Food("Watermelon", "", 0.60, "resources/img/food/img-watermelon.jpg",
				fruitStall);

		Food watermelonJuice = new Food("Watermelon Juice", "", 2.50, null, fruitStall);

		Food dragonfruit = new Food("Dragonfruit", "", 0.60,
				"resources/img/food/img-dragonfruit.jpg", fruitStall);

		Food dragonfruitJuice = new Food("Dragonfruit Juice", "", 2.50, null, fruitStall);

		Food pear = new Food("Pear", "", 0.60, "resources/img/food/img-pear.jpg", fruitStall);

		Food pearJuice = new Food("Pear Juice", "", 2.50, null, fruitStall);

		Food honeydew = new Food("Honeydew", "", 0.70, "resources/img/food/img-honeydew.jpg",
				fruitStall);

		Food honeydewJuice = new Food("Honeydew Juice", "", 2.50, null, fruitStall);

		Food papaya = new Food("Papaya", "", 0.70, "resources/img/food/img-papaya.jpg", fruitStall);

		Food papayaJuice = new Food("Papaya Juice", "", 2.60, null, fruitStall);

		Food pineapple = new Food("Pineapple", "", 0.60, "resources/img/food/img-pineapple.jpg",
				fruitStall);

		Food pineappleJuice = new Food("Pineapple Juice", "", 2.50, null, fruitStall);

		Food banana = new Food("Banana", "", 0.70, "resources/img/food/img-banana.jpg", fruitStall);

		Food bananaJuice = new Food("Banana Juice", "", 2.60, null, fruitStall);

		Food agaragar = new Food("Agar Agar", "", 0.60, "resources/img/food/img-agaragar.jpg",
				fruitStall);

		Food orange = new Food("Orange", "", 0.60, "resources/img/food/img-orange.jpg", fruitStall);

		Food orangeJuice = new Food("Orange Juice", "", 2.50, null, fruitStall);

		Food guava = new Food("Guava", "", 0.70, "resources/img/food/img-guava.jpg", fruitStall);

		Food guavaJuice = new Food("Guava Juice", "", 2.50, null, fruitStall);

		Food sarawakPineapple = new Food("Sarawak Pineapple", "", 1.20,
				"resources/img/food/img-sarawakpineapple.jpg", fruitStall);

		Food sarawakPineappleJuice = new Food("Sarawak Pineapple Juice", "", 3.10, null, fruitStall);

		Food mixedFruits = new Food("Mixed Fruits", "upsize $3.50", 3.00,
				"resources/img/food/img-mixedfruits.jpg", fruitStall);

		// Modifier modifier25 = new Modifier("Change to juice", "", 1.90, apple);
		// Set<Modifier> modifierList14 = new HashSet<Modifier>();
		// modifierList14.add(modifier25);
		// apple.setModifierList(modifierList14);

		// Modifier modifier26 = new Modifier("Change to juice", "", 1.90, watermelon);
		// Set<Modifier> modifierList15 = new HashSet<Modifier>();
		// modifierList15.add(modifier26);
		// watermelon.setModifierList(modifierList14);

		// Modifier modifier27 = new Modifier("Change to juice", "", 1.90, dragonfruit);
		// Set<Modifier> modifierList16 = new HashSet<Modifier>();
		// modifierList16.add(modifier27);
		// dragonfruit.setModifierList(modifierList16);

		// Modifier modifier28 = new Modifier("Change to juice", "", 1.90, pear);
		// Set<Modifier> modifierList17 = new HashSet<Modifier>();
		// modifierList17.add(modifier28);
		// pear.setModifierList(modifierList17);

		// Modifier modifier29 = new Modifier("Change to juice", "", 1.80, honeydew);
		// Set<Modifier> modifierList18 = new HashSet<Modifier>();
		// modifierList18.add(modifier29);
		// honeydew.setModifierList(modifierList18);

		// Modifier modifier30 = new Modifier("Change to juice", "", 1.90, papaya);
		// Set<Modifier> modifierList19 = new HashSet<Modifier>();
		// modifierList19.add(modifier30);
		// papaya.setModifierList(modifierList19);

		// Modifier modifier31 = new Modifier("Change to juice", "", 1.90, pineapple);
		// Set<Modifier> modifierList20 = new HashSet<Modifier>();
		// modifierList20.add(modifier31);
		// pineapple.setModifierList(modifierList20);

		// Modifier modifier32 = new Modifier("Change to juice", "", 1.90, banana);
		// Set<Modifier> modifierList21 = new HashSet<Modifier>();
		// modifierList21.add(modifier32);
		// banana.setModifierList(modifierList21);
		//
		// Modifier modifier33 = new Modifier("Change to juice", "", 1.90, orange);
		// Set<Modifier> modifierList22 = new HashSet<Modifier>();
		// modifierList22.add(modifier33);
		// orange.setModifierList(modifierList22);
		//
		// Modifier modifier34 = new Modifier("Change to juice", "", 1.80, guava);
		// Set<Modifier> modifierList23 = new HashSet<Modifier>();
		// modifierList23.add(modifier34);
		// guava.setModifierList(modifierList23);
		//
		// Modifier modifier35 = new Modifier("Change to juice", "", 1.90, sarawakPineapple);
		// Set<Modifier> modifierList24 = new HashSet<Modifier>();
		// modifierList24.add(modifier35);
		// sarawakPineapple.setModifierList(modifierList24);

		Modifier modifier36 = new Modifier("Mixed fruits upsize", "", 0.50, mixedFruits);
		Set<Modifier> modifierList25 = new HashSet<Modifier>();
		modifierList25.add(modifier36);
		mixedFruits.setModifierList(modifierList25);

		Modifier modifierNew24 = new Modifier("Add powder", "", 0.00, guava);
		Set<Modifier> modifierListNew22 = new HashSet<Modifier>();
		guava.setModifierList(modifierListNew22);

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

		foodList8.add(appleJuice);
		foodList8.add(watermelonJuice);
		foodList8.add(dragonfruitJuice);
		foodList8.add(pearJuice);
		foodList8.add(honeydewJuice);
		foodList8.add(papayaJuice);
		foodList8.add(pineappleJuice);
		foodList8.add(bananaJuice);

		foodList8.add(orangeJuice);
		foodList8.add(guavaJuice);
		foodList8.add(sarawakPineappleJuice);

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

		// friday canteen
		Set<Stall> stallList3 = new HashSet<>();
		Set<Food> freefoodListB1 = new HashSet<>();
		Set<Food> freefoodListB2 = new HashSet<>();
		Set<Food> freefoodListB3 = new HashSet<>();
		Set<Food> freefoodListB4 = new HashSet<>();
		Set<Food> freefoodListB5 = new HashSet<>();
		Set<Food> freefoodListB6 = new HashSet<>();
		Set<Food> freefoodListB7 = new HashSet<>();
		Set<Food> freefoodListB8 = new HashSet<>();
		Set<Food> freefoodListB9 = new HashSet<>();
		Set<Food> freefoodListB10 = new HashSet<>();

		Stall fstall1 = new Stall("Sliced Fish Beehoon Stall", 91379160, null, null,
				"resources/img/stall/img-tjslicefishbeehoon.jpg");
		Food ffood1 = new Food("Fish Slice Beehoon", "", 0,
				"resources/img/food/img-fishslicebeehoon.jpg", fstall1);
		Food ffood2 = new Food("Fish Soup and Rice", "", 0,
				"resources/img/food/img-fishsoupwithrice.jpg", fstall1);

		freefoodListB1.add(ffood1);
		freefoodListB1.add(ffood2);
		fstall1.setFoodList(freefoodListB1);

		Stall fstall2 = new Stall("Malay food Stall", 81145966, null, null,
				"resources/img/stall/img-tjmalaystall.jpg");
		Food ffood3 = new Food("Chilli Chicken + Veg", "upsize to $3.50", 0,
				"resources/img/food/img-malaychillichicken.jpg", fstall2);
		Food ffood4 = new Food("Fried Chicken + Veg", "upsize to $3.50", 0,
				"resources/img/food/img-malayfriedchicken.jpg", fstall2);
		Food ffood5 = new Food("Curry Fish + Veg", "upsize to $3.50", 0,
				"resources/img/food/img-malaycurryfish.jpg", stall2);
		Food ffoodMalay1 = new Food("Fried Fish + Veg", "upsize to $3.50", 0,
				"resources/img/food/img-malayfriedfish.jpg", fstall2);
		Food ffoodMalay2 = new Food("Mutton + Veg", "upsize to $3.50", 0,
				"resources/img/food/img-malaymutton.jpg", fstall2);

		Modifier fmodifierA3 = new Modifier("Add Veg", "", 0, ffood3);
		Set<Modifier> fmodifierListA1 = new HashSet<Modifier>();
		fmodifierListA1.add(fmodifierA3);
		ffood3.setModifierList(fmodifierListA1);

		Modifier fmodifierA4 = new Modifier("Add Veg", "", 0, ffood4);
		Set<Modifier> fmodifierListA2 = new HashSet<Modifier>();
		fmodifierListA2.add(fmodifierA4);
		ffood4.setModifierList(fmodifierListA2);

		Modifier fmodifierA5 = new Modifier("Add Veg", "", 0, ffood5);
		Set<Modifier> fmodifierListA3 = new HashSet<Modifier>();
		fmodifierListA3.add(fmodifierA5);
		ffood5.setModifierList(fmodifierListA3);

		Modifier fmodifierAMalay1 = new Modifier("Add Veg", "", 0, ffoodMalay1);
		Set<Modifier> fmodifierListAMalay1 = new HashSet<Modifier>();
		fmodifierListAMalay1.add(fmodifierAMalay1);
		ffoodMalay1.setModifierList(fmodifierListAMalay1);

		Modifier fmodifierAMalay2 = new Modifier("Add Veg", "", 0, ffoodMalay2);
		Set<Modifier> fmodifierListAMalay2 = new HashSet<Modifier>();
		fmodifierListAMalay2.add(fmodifierAMalay2);
		ffoodMalay2.setModifierList(fmodifierListAMalay2);

		freefoodListB2.add(ffood3);
		freefoodListB2.add(ffood4);
		freefoodListB2.add(ffood5);
		freefoodListB2.add(ffoodMalay1);
		freefoodListB2.add(ffoodMalay2);
		fstall2.setFoodList(freefoodListB2);

		Stall fstall3 = new Stall("Mixed Rice Stall", 93482772, null, null,
				"resources/img/stall/img-tjmixedrice.jpg");
		Food ffood6 = new Food("1 Meat + 2 Veg", "", 0, "resources/img/food/img-mixedrice.jpg",
				fstall3);
		Food ffood7 = new Food("All Veg", "", 0, "resources/img/food/img-porridge.jpg", fstall3);

		Modifier fmodifierMixed1 = new Modifier("Add Veg", "", 0, ffood6);
		Set<Modifier> fmodifierListMixed1 = new HashSet<Modifier>();
		fmodifierListMixed1.add(fmodifierMixed1);
		ffood6.setModifierList(fmodifierListMixed1);

		Modifier fmodifierMixed2 = new Modifier("Add Veg", "", 0, ffood7);
		Set<Modifier> fmodifierListMixed2 = new HashSet<Modifier>();
		fmodifierListMixed2.add(fmodifierMixed2);
		ffood7.setModifierList(fmodifierListMixed2);

		freefoodListB3.add(ffood6);
		freefoodListB3.add(ffood7);
		fstall3.setFoodList(freefoodListB3);

		Stall fstall4 = new Stall("Wanton Mee Stall", 0, null, null,
				"resources/img/stall/img-tjwantonmee.jpg");
		Food ffood8 = new Food("Wanton Mee", "", 0, "resources/img/food/img-wantonmee.jpg", fstall4);

		freefoodListB4.add(ffood8);
		fstall4.setFoodList(freefoodListB4);

		Stall fstall5 = new Stall("Indian Food Stall", 93841009, null, null,
				"resources/img/stall/img-indianstall.jpg");
		Food ffood9 = new Food("Curry Chicken Briyani", "", 0,
				"resources/img/food/img-currychickenbriyani.jpg", fstall5);
		Food ffood10 = new Food("Fish Briyani", "", 0, "resources/img/food/img-fishbiryani.jpg",
				fstall5);
		Food ffood11 = new Food("Vegetable Briyani", "", 0,
				"resources/img/food/img-vegetablebriyani.jpg", fstall5);
		Food ffood12 = new Food("Fried Chicken Briyani", "", 0,
				"resources/img/food/img-friedchickenbiryani.jpg", fstall5);

		freefoodListB5.add(ffood9);
		freefoodListB5.add(ffood10);
		freefoodListB5.add(ffood11);
		freefoodListB5.add(ffood12);
		fstall5.setFoodList(freefoodListB5);

		Stall fstall6 = new Stall("Roast Duck & Chicken Rice Stall", 98427347, null, null,
				"resources/img/stall/img-roastmeatstall.jpg");
		Food ffood13 = new Food("Roast Chicken Rice", "", 0,
				"resources/img/food/img-roastchickenrice.jpg", fstall6);
		Food ffood14 = new Food("CharSiew Rice", "", 0, "resources/img/food/img-charsiewrice.jpg",
				fstall6);
		Food ffood15 = new Food("Roast Meat Rice", "", 0,
				"resources/img/food/img-roastmeatrice.jpg", fstall6);
		Food ffoodC16 = new Food("CharSiew + Roast Meat Rice", "", 0,
				"resources/img/food/img-charsiewrice.jpg", fstall6);

		freefoodListB6.add(ffoodC16);
		freefoodListB6.add(ffood13);
		freefoodListB6.add(ffood14);
		freefoodListB6.add(ffood15);
		fstall6.setFoodList(freefoodListB6);

		Stall fstall7 = new Stall("REX(Halal)", 62684806, null, null,
				"resources/img/stall/img-tj.jpg");
		Food ffood16 = new Food("Chicken Rice", "", 0,
				"resources/img/food/img-friedchickenbiryani.jpg", fstall7);
		Food ffood17 = new Food("Chicken Fried Rice", "", 0,
				"resources/img/food/img-chickenfriedrice.jpg", fstall7);
		Food ffood18 = new Food("Seafood Fried Rice", "", 0,
				"resources/img/food/img-seafoodfriedrice.jpg", fstall7);
		Food ffood19 = new Food("Beef Fried Rice", "", 0,
				"resources/img/food/img-beeffriedrice.jpg", fstall7);
		Food ffood20 = new Food("Seafood Horfun(Wet)", "", 0,
				"resources/img/food/img-seafoodhorfun.jpg", fstall7);
		Food ffood21 = new Food("Seafood Horfun(Dry)", "", 0, "resources/img/food/img-horfun.jpg",
				fstall7);
		Food ffood22 = new Food("Chicken Porridge", "", 0,
				"resources/img/food/img-chickenporridge.jpg", fstall7);
		Food ffood23 = new Food("Fish Porridge", "", 0, "resources/img/food/img-fishporridge.jpg",
				fstall7);
		Food ffoodA24 = new Food("Chicken Horfun(Wet)", "", 0,
				"resources/img/food/img-chickenhorfunwet.jpg", fstall7);
		Food ffoodA25 = new Food("Chicken Horfun(Dry)", "", 0,
				"resources/img/food/img-chickenhorfundry.jpg", fstall7);

		Modifier fmodifierA16 = new Modifier("Change to drumstick", "", 00, ffood16);
		Modifier fmodifierA17 = new Modifier("Chicken Wing", "", 0, ffood16);
		Modifier fmodifierA18 = new Modifier("Chicken Breast", "", 0, ffood16);
		Modifier fmodifierA19 = new Modifier("Chicken Thigh", "", 0, ffood16);
		Set<Modifier> fmodifierListA16 = new HashSet<Modifier>();
		fmodifierListA16.add(fmodifierA16);
		fmodifierListA16.add(fmodifierA17);
		fmodifierListA16.add(fmodifierA18);
		fmodifierListA16.add(fmodifierA19);
		ffood16.setModifierList(fmodifierListA16);

		freefoodListB7.add(ffood16);
		freefoodListB7.add(ffood17);
		freefoodListB7.add(ffood18);
		freefoodListB7.add(ffood19);
		freefoodListB7.add(ffood20);
		freefoodListB7.add(ffood21);
		freefoodListB7.add(ffood22);
		freefoodListB7.add(ffood23);
		freefoodListB7.add(ffoodA24);
		freefoodListB7.add(ffoodA25);
		fstall7.setFoodList(freefoodListB7);

		Stall fstall8 = new Stall("Vegetarian Stall", 91182963, null, null,
				"resources/img/stall/img-tj.jpg");
		Food ffood24 = new Food("Vegetarian Rice", "", 0, "resources/img/food/img-vegrice.jpg",
				fstall8);
		Food ffood25 = new Food("Vegetarian Beehoon", "", 0,
				"resources/img/food/img-vegetarianbeehoon.jpg", fstall8);
		Food ffood26 = new Food("Vegetarian Mee", "", 0,
				"resources/img/food/img-vergetarianmee.jpg", fstall8);

		freefoodListB8.add(ffood24);
		freefoodListB8.add(ffood25);
		freefoodListB8.add(ffood26);
		fstall8.setFoodList(freefoodListB8);

		Stall fstall9 = new Stall("Minced Meat Noodles Stall(Closed On Tuesday)", 93686070, null,
				null, "resources/img/stall/img-tj.jpg");
		Food ffood27 = new Food("Minced Meat Noodles", "upsize to $3.20", 0,
				"resources/img/food/img-mincedmeatnoodles.jpg", fstall9);

		Modifier fmodifierA27 = new Modifier("Upsize to $3.20", "", 0, ffood27);
		Set<Modifier> fmodifierListA27 = new HashSet<Modifier>();
		fmodifierListA16.add(fmodifierA27);
		ffood5.setModifierList(fmodifierListA27);

		freefoodListB9.add(ffood27);
		fstall9.setFoodList(freefoodListB9);

		Stall fstall10 = new Stall("Noodle Stall", 96946576, null, null,
				"resources/img/stall/img-tj.jpg");
		Food ffood28 = new Food("Lor Mee", "", 0, "resources/img/food/img-lormee.jpg", fstall10);
		Food ffood29 = new Food("Prawn Mee", "", 0, "resources/img/food/img-prawnnoodle.jpg",
				fstall10);
		Food ffood30 = new Food("Fishball Noodles", "", 0,
				"resources/img/food/img-fishballnoodle.jpg", fstall10);
		Food ffood31 = new Food("Laksa", "", 0, "resources/img/food/img-laksa.jpg", fstall10);
		Food ffood32 = new Food("Hokkien Mee", "", 0, "resources/img/food/img-hokkiennoodles.jpg",
				fstall10);
		Food ffood33 = new Food("Dumpling Noodles", "", 0,
				"resources/img/food/img-dumplingnoodle.jpg", fstall10);

		freefoodListB10.add(ffood28);
		freefoodListB10.add(ffood29);
		freefoodListB10.add(ffood30);
		freefoodListB10.add(ffood31);
		freefoodListB10.add(ffood32);
		freefoodListB10.add(ffood33);
		fstall10.setFoodList(freefoodListB10);

		Canteen canteen3 = new Canteen("Taman Jurong Market and Food Centre(Friday)",
				"3 Yung Sheng Rd, Singapore 618499", null);
		fstall1.setCanteen(canteen3);
		fstall2.setCanteen(canteen3);
		fstall3.setCanteen(canteen3);
		fstall4.setCanteen(canteen3);
		fstall5.setCanteen(canteen3);
		fstall6.setCanteen(canteen3);
		fstall7.setCanteen(canteen3);
		fstall8.setCanteen(canteen3);
		fstall9.setCanteen(canteen3);
		fstall10.setCanteen(canteen3);

		stallList3.add(fstall1);
		stallList3.add(fstall2);
		stallList3.add(fstall3);
		stallList3.add(fstall4);
		stallList3.add(fstall5);
		stallList3.add(fstall6);
		stallList3.add(fstall7);
		stallList3.add(fstall8);
		stallList3.add(fstall9);
		stallList3.add(fstall10);

		canteen3.setStallList(stallList3);

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
		// session.save(mfood4);
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
		// session.save(mixVegRice3);
		session.save(mixVegRice4);
		session.save(mixVegRice5);

		session.save(roastfood1);
		session.save(roastfood2);
		session.save(roastfood3);
		session.save(roastfood4);
		session.save(roastfood5);

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
		// session.save(seafood12);
		// session.save(seafood13);
		// session.save(seafood14);

		session.save(fishBeehoonfood1);
		session.save(fishBeehoonfood2);
		session.save(fishBeehoonfood3);
		session.save(fishBeehoonfood4);
		session.save(fishBeehoonfood5);

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

		// saving of fruitJuices
		session.save(appleJuice);
		session.save(watermelonJuice);
		session.save(dragonfruitJuice);
		session.save(pearJuice);
		session.save(honeydewJuice);
		session.save(papayaJuice);
		session.save(pineappleJuice);
		session.save(bananaJuice);

		session.save(orangeJuice);
		session.save(guavaJuice);
		session.save(sarawakPineappleJuice);

		// saving of the modifiers into database
		session.save(modifier1);
		session.save(modifier2);
		session.save(modifierM21);
		session.save(modifierM22);
		session.save(modifierM31);
		session.save(modifierM32);
		// session.save(modifierM41);
		// session.save(modifierM42);
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
		// session.save(modifier18);
		// session.save(modifier19);
		session.save(modifier20);
		session.save(modifier21);
		session.save(modifier22);
		session.save(modifier23);
		session.save(modifier24);
		session.save(modifierNew25);
		session.save(modifierNew26);
		// session.save(modifier25);
		// session.save(modifier26);
		// session.save(modifier27);
		// session.save(modifier28);
		// session.save(modifier29);
		// session.save(modifier30);
		// session.save(modifier31);
		// session.save(modifier32);
		// session.save(modifier33);
		// session.save(modifier34);
		// session.save(modifier35);
		session.save(modifier36);

		session.save(modifierA3);
		session.save(modifierA4);
		session.save(modifierA5);
		session.save(modifierAMalay1);
		// session.save(modifierAMalay2);
		session.save(modifierMixed1);
		session.save(modifierMixed2);
		// session.save(modifierA16);
		// session.save(modifierA17);
		// session.save(modifierA18);
		// session.save(modifierA19);

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

		session.save(canteen2);
		session.save(stall1);
		session.save(stall2);
		session.save(stall3);
		// session.save(stall4);
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
		// session.save(food8);
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
		session.save(food42);
		session.save(food43);
		session.save(food44);
		session.save(food45);
		session.save(food46);
		session.save(food47);
		session.save(food48);
		session.save(food49);
		session.save(food50);
		session.save(food51);
		session.save(food52);
		session.save(food53);
		session.save(food54);
		session.save(food55);
		session.save(food56);
		session.save(food57);

		session.save(foodA24);
		session.save(foodA25);
		// session.save(foodC16);
		session.save(foodMalay1);
		// session.save(foodMalay2);

		session.save(nfood1);
		session.save(nfood2);
		session.save(nfood3);
		session.save(nfood4);
		// session.save(nfood5);
		session.save(nfood6);
		session.save(nfood7);
		session.save(nfood8);
		session.save(nfood9);
		session.save(nfood10);
		session.save(nfood11);
		session.save(nfood12);
		session.save(nfood13);
		session.save(nfood14);
		session.save(nfood15);
		session.save(nfood16);
		session.save(nfood17);
		session.save(nfood18);
		session.save(nfood19);
		session.save(nfood20);
		session.save(nfood21);
		session.save(nfood22);
		session.save(nfood23);
		session.save(nfood24);
		session.save(nfood25);
		session.save(nfood26);
		session.save(nfood27);
		session.save(nfood28);
		session.save(nfood29);

		session.save(modifierNew1);
		session.save(modifierNew2);
		session.save(modifierNew3);
		session.save(modifierNew4);
		session.save(modifierNew5);
		session.save(modifierNew6);
		session.save(modifierNew7);
		session.save(modifierNew8);
		session.save(modifierNew9);
		session.save(modifierNew10);
		session.save(modifierNew11);
		session.save(modifierNew12);
		session.save(modifierNew13);
		session.save(modifierNew14);
		session.save(modifierNew15);
		session.save(modifierNew16);
		session.save(modifierNew17);
		session.save(modifierNew18);
		session.save(modifierNew19);
		// session.save(modifierNew20);
		session.save(modifierNew21);
		session.save(modifierNew22);
		session.save(modifierNew23);
		session.save(modifierNew24);
		session.save(modifierNew25);
		session.save(modifierNew26);

		// //Save friday
		// session.save(canteen3);
		// session.save(fstall1);
		// session.save(fstall2);
		// session.save(fstall3);
		// session.save(fstall4);
		// session.save(fstall5);
		// session.save(fstall6);
		// session.save(fstall7);
		// session.save(fstall8);
		// session.save(fstall9);
		// session.save(fstall10);
		// session.save(ffood1);
		// session.save(ffood2);
		// session.save(ffood3);
		// session.save(ffood4);
		// session.save(ffood5);
		// session.save(ffood6);
		// session.save(ffood7);
		// session.save(ffood8);
		// session.save(ffood9);
		// session.save(ffood10);
		// session.save(ffood11);
		// session.save(ffood12);
		// session.save(ffood13);
		// session.save(ffood14);
		// session.save(ffood15);
		// session.save(ffood16);
		// session.save(ffood17);
		// session.save(ffood18);
		// session.save(ffood19);
		// session.save(ffood20);
		// session.save(ffood21);
		// session.save(ffood22);
		// session.save(ffood23);
		// session.save(ffood24);
		// session.save(ffood25);
		// session.save(ffood26);
		// session.save(ffood27);
		// session.save(ffood28);
		// session.save(ffood29);
		// session.save(ffood30);
		// session.save(ffood31);
		// session.save(ffood32);
		// session.save(ffood33);
		// session.save(ffoodA24);
		// session.save(ffoodA25);
		// session.save(ffoodC16);
		// session.save(ffoodMalay1);
		// session.save(ffoodMalay2);
		// session.save(fmodifierA3);
		// session.save(fmodifierA4);
		// session.save(fmodifierA5);
		// session.save(fmodifierAMalay1);
		// session.save(fmodifierAMalay2);
		// session.save(fmodifierMixed1);
		// session.save(fmodifierMixed2);
		// session.save(fmodifierA16);
		// session.save(fmodifierA17);
		// session.save(fmodifierA18);
		// session.save(fmodifierA19);
		// session.save(fmodifierA27);
		//
		// **************************************** End insert of mock data
		// ****************************************

		OrderWindow window = new OrderWindow(new DateTime(2015, 12, 2, 16, 36, 0), new DateTime(
				2016, 12, 5, 16, 36, 0), company, canteen1, 0, "This is a testing order window.",
				null);
		session.save(window); // arnold test data

		session.getTransaction().commit();
		session.flush();
		session.close();
		// QuartzTest quartzTest = new QuartzTest();
		// quartzTest.doProcess();
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
