package test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import model.Admin;
import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.Modifier;
import model.Stall;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

		Company company = new Company("XiaoDingDang Co.", null, null, "XDD123");
		Employee arnold = new Employee("arnold", PasswordService.encryptPassword("1234567"),
				"Arnold Lee", "arnold.lee.2013@sis.smu.edu.sg", 85698565, company);

		session.save(company);
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

		Stall stall1 = new Stall("Sliced Fish Bee Hoon Stall", 91379160, null, null);
		Food food1 = new Food("Fish Slice Bee Hoon", "", 3.70, null, stall1);
		Food food2 = new Food("Fish Soup and Rice", "", 4.70, null, stall1);
		
		File file37 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile37 = new byte[(int) file37.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file37);
	     //convert file into array of bytes
	     fileInputStream.read(bFile37);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food1.setImage(bFile37);
        
        
        File file38 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile38 = new byte[(int) file38.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file38);
	     //convert file into array of bytes
	     fileInputStream.read(bFile38);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food2.setImage(bFile38);
        
        
		foodListB1.add(food1);
		foodListB1.add(food2);
		stall1.setFoodList(foodListB1);

		Stall stall2 = new Stall("Malay food Stall", 81145966, null, null);
		Food food3 = new Food("Malay Chicken List", "upsize to $3.50", 3.00, null, stall2);
		Food food4 = new Food("Malay Fish List", "upsize to $3.50", 3.00, null, stall2);
		Food food5 = new Food("Malay Mutton List", "upsize to $3.50", 3.00, null, stall2);
		
		File file39 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile39 = new byte[(int) file39.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file39);
	     //convert file into array of bytes
	     fileInputStream.read(bFile39);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food3.setImage(bFile39);
        
        File file40 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile40 = new byte[(int) file40.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file40);
	     //convert file into array of bytes
	     fileInputStream.read(bFile40);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food4.setImage(bFile40);
        
        
        File file41 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile41 = new byte[(int) file41.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file41);
	     //convert file into array of bytes
	     fileInputStream.read(bFile41);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food5.setImage(bFile41);

		Modifier modifierA3 = new Modifier("Upsize to $3.50", "", 0.50, food3);
		Set<Modifier> modifierListA1 = new HashSet<Modifier>();
		modifierListA1.add(modifierA3);
		food3.setModifierList(modifierListA1);

		Modifier modifierA4 = new Modifier("Upsize to $3.50", "", 0.50, food4);
		Set<Modifier> modifierListA2 = new HashSet<Modifier>();
		modifierListA2.add(modifierA4);
		food4.setModifierList(modifierListA2);

		Modifier modifierA5 = new Modifier("Upsize to $3.50", "", 0.50, food5);
		Set<Modifier> modifierListA3 = new HashSet<Modifier>();
		modifierListA3.add(modifierA5);
		food5.setModifierList(modifierListA3);

		foodListB2.add(food3);
		foodListB2.add(food4);
		foodListB2.add(food5);
		stall2.setFoodList(foodListB2);

		Stall stall3 = new Stall("Mixed Rice Stall", 93482772, null, null);
		Food food6 = new Food("Mixed Rice", "", 3.00, null, stall3);
		Food food7 = new Food("Porridge", "", 3.00, null, stall3);
		
		File file42 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile42 = new byte[(int) file42.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file42);
	     //convert file into array of bytes
	     fileInputStream.read(bFile42);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food6.setImage(bFile42);
        
        File file43 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile43 = new byte[(int) file43.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file43);
	     //convert file into array of bytes
	     fileInputStream.read(bFile43);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food7.setImage(bFile43);
		
		foodListB3.add(food6);
		foodListB3.add(food7);
		stall3.setFoodList(foodListB3);

		Stall stall4 = new Stall("Wanton Mee Stall", 0, null, foodListB4);
		Food food8 = new Food("Wanton Mee", "", 3.00, null, stall4);
		
		File file44 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile44 = new byte[(int) file44.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file44);
	     //convert file into array of bytes
	     fileInputStream.read(bFile44);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food8.setImage(bFile44);
        
		foodListB4.add(food8);
		stall4.setFoodList(foodListB4);

		Stall stall5 = new Stall("Indian Food Stall", 93841009, null, null);
		Food food9 = new Food("Indian Chicken List", "", 3.50, null, stall5);
		Food food10 = new Food("Indian Fish List", "", 3.50, null, stall5);
		Food food11 = new Food("Mutton Briyani", "", 4.00, null, stall5);
		Food food12 = new Food("Ayam Penyat", "", 4.00, null, stall5);
		
		File file45 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile45 = new byte[(int) file45.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file45);
	     //convert file into array of bytes
	     fileInputStream.read(bFile45);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food9.setImage(bFile45);
        
        
        File file46 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile46 = new byte[(int) file46.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file46);
	     //convert file into array of bytes
	     fileInputStream.read(bFile46);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food10.setImage(bFile46);
        
        
        File file47 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile47 = new byte[(int) file47.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file47);
	     //convert file into array of bytes
	     fileInputStream.read(bFile47);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food11.setImage(bFile47);
        
        File file48 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile48 = new byte[(int) file48.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file48);
	     //convert file into array of bytes
	     fileInputStream.read(bFile48);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food12.setImage(bFile48);
		
		
		foodListB5.add(food9);
		foodListB5.add(food10);
		foodListB5.add(food11);
		foodListB5.add(food12);
		stall5.setFoodList(foodListB5);

		Stall stall6 = new Stall("Roast Duck & Chicken Rice Stall", 98427347, null, null);
		Food food13 = new Food("Roast Chicken Rice", "", 2.50, null, stall6);
		Food food14 = new Food("CharSiew Rice", "", 2.50, null, stall6);
		Food food15 = new Food("Roast Meat Rice", "", 2.50, null, stall6);
		Food foodC16 = new Food("CharSiew + Roast Meat Rice", "", 3.00, null, stall6);
		
		File file49 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile49 = new byte[(int) file49.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file49);
	     //convert file into array of bytes
	     fileInputStream.read(bFile49);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food13.setImage(bFile49);
        
        
        File file50 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile50 = new byte[(int) file50.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file50);
	     //convert file into array of bytes
	     fileInputStream.read(bFile50);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food14.setImage(bFile50);
        
        File file51 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile51 = new byte[(int) file51.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file51);
	     //convert file into array of bytes
	     fileInputStream.read(bFile51);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food15.setImage(bFile51);
        
        File file52 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile52 = new byte[(int) file52.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file52);
	     //convert file into array of bytes
	     fileInputStream.read(bFile52);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        foodC16.setImage(bFile52);
        
        
		foodListB6.add(foodC16);
		foodListB6.add(food13);
		foodListB6.add(food14);
		foodListB6.add(food15);
		stall6.setFoodList(foodListB6);

		Stall stall7 = new Stall("REX(Halal)", 62684806, null, null);
		Food food16 = new Food("Chicken Rice", "", 3.00, null, stall7);
		Food food17 = new Food("Chicken Fried Rice", "", 3.00, null, stall7);
		Food food18 = new Food("Seafood Fried Rice", "", 3.50, null, stall7);
		Food food19 = new Food("Beef Fried Rice", "", 4.00, null, stall7);
		Food food20 = new Food("Seafood Horfun(wet)", "", 3.50, null, stall7);
		Food food21 = new Food("Seafood Horfun(dry)", "", 4.00, null, stall7);
		Food food22 = new Food("Chicken Porridge", "", 3.00, null, stall7);
		Food food23 = new Food("Fish Porridge", "", 3.00, null, stall7);

		File file53 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile53 = new byte[(int) file53.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file53);
	     //convert file into array of bytes
	     fileInputStream.read(bFile53);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food16.setImage(bFile53);
        
        
        File file54 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile54 = new byte[(int) file54.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file54);
	     //convert file into array of bytes
	     fileInputStream.read(bFile54);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food17.setImage(bFile54);
        
        
        File file55 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile55 = new byte[(int) file55.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file55);
	     //convert file into array of bytes
	     fileInputStream.read(bFile55);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food18.setImage(bFile55);
        
        
        File file56 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile56 = new byte[(int) file56.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file56);
	     //convert file into array of bytes
	     fileInputStream.read(bFile56);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food19.setImage(bFile56);
        
        File file57 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile57 = new byte[(int) file57.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file57);
	     //convert file into array of bytes
	     fileInputStream.read(bFile57);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food20.setImage(bFile57);
        
        
        File file58 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile58 = new byte[(int) file58.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file58);
	     //convert file into array of bytes
	     fileInputStream.read(bFile58);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food21.setImage(bFile58);
        
        
        File file59 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile59 = new byte[(int) file59.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file59);
	     //convert file into array of bytes
	     fileInputStream.read(bFile59);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food22.setImage(bFile59);
        
        
        File file60 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile60 = new byte[(int) file60.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file60);
	     //convert file into array of bytes
	     fileInputStream.read(bFile60);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food23.setImage(bFile60);
		
		
		Modifier modifierA16 = new Modifier("Change to drumstick", "", 0.50, food16);
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

		Stall stall8 = new Stall("Vegetarian Stall", 91182963, null, null);
		Food food24 = new Food("Vegetarian Rice", "", 2.50, null, stall8);
		Food food25 = new Food("Vegetarian Beehoon", "", 2.50, null, stall8);
		Food food26 = new Food("Vegetarian Mee", "", 2.50, null, stall8);
		
		File file61 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile61 = new byte[(int) file61.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file61);
	     //convert file into array of bytes
	     fileInputStream.read(bFile61);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food24.setImage(bFile61);
        
        File file62 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile62 = new byte[(int) file62.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file62);
	     //convert file into array of bytes
	     fileInputStream.read(bFile62);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food25.setImage(bFile62);
        
        
        File file63 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile63 = new byte[(int) file63.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file63);
	     //convert file into array of bytes
	     fileInputStream.read(bFile63);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food26.setImage(bFile63);
        
        
		foodListB8.add(food24);
		foodListB8.add(food25);
		foodListB8.add(food26);
		stall8.setFoodList(foodListB8);

		Stall stall9 = new Stall("Minced Meat Noodles Stall(Closed On Tuesday)", 93686070, null,
				null);
		Food food27 = new Food("Minced Meat Noodles", "upsize to $3.20", 2.70, null, stall9);
		
		File file64 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile64 = new byte[(int) file64.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file64);
	     //convert file into array of bytes
	     fileInputStream.read(bFile64);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food27.setImage(bFile64);

		Modifier modifierA27 = new Modifier("Upsize to $3.20", "", 0.50, food27);
		Set<Modifier> modifierListA27 = new HashSet<Modifier>();
		modifierListA16.add(modifierA27);
		food5.setModifierList(modifierListA27);

		foodListB9.add(food27);
		stall9.setFoodList(foodListB9);

		Stall stall10 = new Stall("Noodle Stall", 96946576, null, null);
		Food food28 = new Food("Lor Mee", "", 3.00, null, stall10);
		Food food29 = new Food("Prawn Mee", "", 3.00, null, stall10);
		Food food30 = new Food("Fishball Noodles", "", 3.00, null, stall10);
		Food food31 = new Food("Laksa", "", 3.00, null, stall10);
		Food food32 = new Food("Hokkien Mee", "", 3.00, null, stall10);
		Food food33 = new Food("Dumpling Noodles", "", 3.00, null, stall10);
		
		File file65 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile65 = new byte[(int) file65.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file65);
	     //convert file into array of bytes
	     fileInputStream.read(bFile65);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food28.setImage(bFile65);
        
        File file66 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile66 = new byte[(int) file66.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file66);
	     //convert file into array of bytes
	     fileInputStream.read(bFile66);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food29.setImage(bFile66);
        
        
        File file67 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile67 = new byte[(int) file67.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file67);
	     //convert file into array of bytes
	     fileInputStream.read(bFile67);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food30.setImage(bFile67);
        
        File file68 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile68 = new byte[(int) file68.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file68);
	     //convert file into array of bytes
	     fileInputStream.read(bFile68);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food31.setImage(bFile68);
        
        
        File file69 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile69 = new byte[(int) file69.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file69);
	     //convert file into array of bytes
	     fileInputStream.read(bFile69);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food32.setImage(bFile69);
        
        File file70 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile70 = new byte[(int) file70.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file70);
	     //convert file into array of bytes
	     fileInputStream.read(bFile70);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        food33.setImage(bFile70);
		
		foodListB10.add(food28);
		foodListB10.add(food29);
		foodListB10.add(food30);
		foodListB10.add(food31);
		foodListB10.add(food32);
		foodListB10.add(food33);
		stall10.setFoodList(foodListB10);

		Canteen canteen2 = new Canteen("Bedok", "123", null);
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

		canteen2.setStallList(stallList2);

		Canteen canteen1 = new Canteen("Jurong Canteen", "123", null);

		Stall kuehStall = new Stall("Oasis Kueh Stall", 90685620, canteen1, null);
		Food kuehfood1 = new Food("Chee Cheong Fun", "", 0.60, null, kuehStall);
		Food kuehfood2 = new Food("Yam cake", "", 1.20, null, kuehStall);
		Food kuehfood3 = new Food("Dumpling", "", 0.90, null, kuehStall);
		Food kuehfood4 = new Food("Pau", "", 0.80, null, kuehStall);
		Food kuehfood5 = new Food("Lor Mai Kai", "", 1.50, null, kuehStall);
		Food kuehfood6 = new Food("Fan Choy", "", 1.80, null, kuehStall);
		// add food to list to the stall
		
		File file1 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile1 = new byte[(int) file1.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file1);
	     //convert file into array of bytes
	     fileInputStream.read(bFile1);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        kuehfood1.setImage(bFile1);
		
        File file2 = new File("WebContent\\assets\\images\\img-noodles.jpg");
        byte[] bFile2 = new byte[(int) file2.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file2);
	     //convert file into array of bytes
	     fileInputStream.read(bFile2);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        kuehfood2.setImage(bFile2);
        
        
        File file3 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile3 = new byte[(int) file3.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file3);
	     //convert file into array of bytes
	     fileInputStream.read(bFile3);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        kuehfood3.setImage(bFile3);
		
        
        File file4 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile4 = new byte[(int) file4.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file4);
	     //convert file into array of bytes
	     fileInputStream.read(bFile4);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        kuehfood4.setImage(bFile4);
        
        
        File file5 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile5 = new byte[(int) file1.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file5);
	     //convert file into array of bytes
	     fileInputStream.read(bFile5);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        kuehfood5.setImage(bFile5);
        
        
        File file6 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile6 = new byte[(int) file6.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file6);
	     //convert file into array of bytes
	     fileInputStream.read(bFile6);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        kuehfood6.setImage(bFile6);
		
		
		Set<Food> foodList1 = new HashSet<Food>();

		foodList1.add(kuehfood1);
		foodList1.add(kuehfood2);
		foodList1.add(kuehfood3);
		foodList1.add(kuehfood4);
		foodList1.add(kuehfood5);
		foodList1.add(kuehfood6);
		kuehStall.setFoodList(foodList1);

		Stall malayStall = new Stall("Oasis Malay Stall", 93848341, canteen1, null);
		Food mfood1 = new Food("Mixed Veg Rice", "ask for more vegs, less fried meat", 3.70, null,
				malayStall);
		
		File file7 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile7 = new byte[(int) file7.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file7);
	     //convert file into array of bytes
	     fileInputStream.read(bFile7);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        mfood1.setImage(bFile7);
        
		// for mfood1 modifiers
		Modifier modifier1 = new Modifier("Ask for more vegetables", "", 0.00, mfood1);
		Modifier modifier2 = new Modifier("Ask for less fried meat", "", 0.00, mfood1);

		Set<Modifier> modifierList1 = new HashSet<Modifier>();
		modifierList1.add(modifier1);
		modifierList1.add(modifier2);
		mfood1.setModifierList(modifierList1);

		Set<Food> foodList2 = new HashSet<Food>();
		foodList2.add(mfood1);

		malayStall.setFoodList(foodList2);

		Stall indianStall = new Stall("Indian Stall", 98717752, canteen1, null);
		Food infood1 = new Food("White Rice", "Chicken/Fish/Mutton", 4.00, null, indianStall);
		Food infood2 = new Food("Vegetable White Rice", "", 3.00, null, indianStall);
		Food infood3 = new Food("Briyani", "Chicken/Fish/Mutton", 5.00, null, indianStall);
		 
		File file8 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
	        byte[] bFile8 = new byte[(int) file8.length()];
	        
	        try {
		     FileInputStream fileInputStream = new FileInputStream(file8);
		     //convert file into array of bytes
		     fileInputStream.read(bFile8);
		     fileInputStream.close();
	        } catch (Exception e) {
		     e.printStackTrace();
	        }
	        infood1.setImage(bFile8);
	        
	        File file9 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
	        byte[] bFile9 = new byte[(int) file9.length()];
	        
	        try {
		     FileInputStream fileInputStream = new FileInputStream(file9);
		     //convert file into array of bytes
		     fileInputStream.read(bFile9);
		     fileInputStream.close();
	        } catch (Exception e) {
		     e.printStackTrace();
	        }
	        infood2.setImage(bFile9);
	        
	        
	        File file10 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
	        byte[] bFile10 = new byte[(int) file10.length()];
	        
	        try {
		     FileInputStream fileInputStream = new FileInputStream(file10);
		     //convert file into array of bytes
		     fileInputStream.read(bFile10);
		     fileInputStream.close();
	        } catch (Exception e) {
		     e.printStackTrace();
	        }
	        infood3.setImage(bFile10);

		// for infood1 modifiers
		Modifier modifier3 = new Modifier("Chicken", "", 0.00, infood1);
		Modifier modifier4 = new Modifier("Fish", "", 0.00, infood1);
		Modifier modifier5 = new Modifier("Mutton", "", 0.00, infood1);

		Set<Modifier> modifierList2 = new HashSet<Modifier>();
		modifierList2.add(modifier3);
		modifierList2.add(modifier4);
		modifierList2.add(modifier5);
		infood1.setModifierList(modifierList2);

		// for infood3 modifiers
		Modifier modifier6 = new Modifier("Chicken", "", 0.00, infood3);
		Modifier modifier7 = new Modifier("Fish", "", 0.00, infood3);
		Modifier modifier8 = new Modifier("Mutton", "", 0.00, infood3);

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

		Stall chineseMixVegStall = new Stall("Oasis Chinese Mix Veg Stall", 93848341, canteen1,
				null);
		Food mixVegRice1 = new Food("Mix Veg Rice",
				"ask for more meat, less fried meat, or upsize to $3.50", 3.00, null,
				chineseMixVegStall);
		
		File file11 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile11 = new byte[(int) file11.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file11);
	     //convert file into array of bytes
	     fileInputStream.read(bFile11);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        mixVegRice1.setImage(bFile11);

		// for mixVegRice1 modifiers
		Modifier modifier9 = new Modifier("Ask for more vegetables", "", 0.00, mixVegRice1);
		Modifier modifier10 = new Modifier("Ask for less fried meat", "", 0.00, mixVegRice1);
		Modifier modifier11 = new Modifier("Upsize to $3.50", "", 0.50, mixVegRice1);

		Set<Modifier> modifierList4 = new HashSet<Modifier>();
		modifierList4.add(modifier9);
		modifierList4.add(modifier10);
		modifierList4.add(modifier11);
		mixVegRice1.setModifierList(modifierList4);

		Set<Food> foodList4 = new HashSet<Food>();
		foodList4.add(mixVegRice1);
		chineseMixVegStall.setFoodList(foodList4);

		Stall roastMeatStall = new Stall("Roast Meat Stall", 123, canteen1, null);
		Food roastfood1 = new Food("Roast Chicken Rice", "2 meat choices $4", 3.00, null,
				roastMeatStall);
		Food roastfood2 = new Food("Wanton Mee", "", 3.20, null, roastMeatStall);
		Food roastfood3 = new Food("CharSiew Rice", "2 meat choices $4", 3.00, null, roastMeatStall);
		Food roastfood4 = new Food("Roast Meat Rice", "2 meat choices $4", 3.00, null,
				roastMeatStall);
		
		File file12 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile12 = new byte[(int) file12.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file12);
	     //convert file into array of bytes
	     fileInputStream.read(bFile12);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        roastfood1.setImage(bFile12);
        
        File file13 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile13 = new byte[(int) file13.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file13);
	     //convert file into array of bytes
	     fileInputStream.read(bFile13);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        roastfood2.setImage(bFile13);
        
        
        File file14 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile14 = new byte[(int) file14.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file14);
	     //convert file into array of bytes
	     fileInputStream.read(bFile14);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        roastfood3.setImage(bFile14);
        
        
        File file15 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile15 = new byte[(int) file15.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file15);
	     //convert file into array of bytes
	     fileInputStream.read(bFile15);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        roastfood4.setImage(bFile15);

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

		Stall seafoodTzeCharStall = new Stall("Seafood Tze Char Stall", 92262376, canteen1, null);

		Food seafood1 = new Food("Horfun", "dry type $4.00", 3.70, null, seafoodTzeCharStall);
		Food seafood2 = new Food("Fried Rice", "", 4.00, null, seafoodTzeCharStall);
		Food seafood3 = new Food("Daily Soup With Rice And Egg", "", 4.80, null,
				seafoodTzeCharStall);
		Food seafood4 = new Food("Hokkien Noodle", "", 4.20, null, seafoodTzeCharStall);
		Food seafood5 = new Food("Mee Goreng", "", 4.20, null, seafoodTzeCharStall);
		
		File file16 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile16 = new byte[(int) file16.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file16);
	     //convert file into array of bytes
	     fileInputStream.read(bFile16);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        seafood1.setImage(bFile16);
        
        File file17 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile17 = new byte[(int) file17.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file17);
	     //convert file into array of bytes
	     fileInputStream.read(bFile17);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        seafood2.setImage(bFile17);
        
        
        File file18 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile18 = new byte[(int) file18.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file18);
	     //convert file into array of bytes
	     fileInputStream.read(bFile18);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        seafood3.setImage(bFile18);
        
        File file19 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile19 = new byte[(int) file19.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file19);
	     //convert file into array of bytes
	     fileInputStream.read(bFile19);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        seafood4.setImage(bFile19);
        
        
        File file20 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile20 = new byte[(int) file20.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file20);
	     //convert file into array of bytes
	     fileInputStream.read(bFile20);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        seafood5.setImage(bFile20);
		
		// for seafood1 modifier
		Modifier modifier18 = new Modifier("Dry type", "", 0.30, seafood1);
		Modifier modifier19 = new Modifier("Ask for more vegetables", "", 0.00, seafood1);

		Set<Modifier> modifierList8 = new HashSet<Modifier>();
		modifierList8.add(modifier18);
		modifierList8.add(modifier19);
		seafood1.setModifierList(modifierList8);

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

		Stall fishBeehoonStall = new Stall("Fish Beehoon Stall", 98367790, canteen1, null);
		Food fishBeehoonfood1 = new Food("Fish Soup With Bee Hoon", "add bittergourd: $0.50", 3.50,
				null, fishBeehoonStall);
		Food fishBeehoonfood2 = new Food("Fish Soup With Rice", "add bittergourd: $0.50", 4.00,
				null, fishBeehoonStall);
		Food fishBeehoonfood3 = new Food("Fried Fish Soup With Bee Hoon", "add bittergourd: $0.50",
				5.00, null, fishBeehoonStall);
		
		//save image into database
    	File file21 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile21 = new byte[(int) file21.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file21);
	     //convert file into array of bytes
	     fileInputStream.read(bFile21);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        fishBeehoonfood1.setImage(bFile21);
        
        File file22 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile22 = new byte[(int) file22.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file22);
	     //convert file into array of bytes
	     fileInputStream.read(bFile22);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        fishBeehoonfood2.setImage(bFile22);
        
        File file23 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile23 = new byte[(int) file23.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file23);
	     //convert file into array of bytes
	     fileInputStream.read(bFile23);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        fishBeehoonfood3.setImage(bFile23);

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

		Stall fruitStall = new Stall("Fruit Stall", 91151608, canteen1, null);
		Food apple = new Food("Apple", "change to juice $2.50", 0.60, null, fruitStall);
		Food watermelon = new Food("Watermelon", "change to juice $2.50", 0.60, null, fruitStall);
		Food dragonfruit = new Food("Dragonfruit", "change to juice $2.50", 0.60, null, fruitStall);
		Food pear = new Food("Pear", "change to juice $2.50", 0.60, null, fruitStall);
		Food honeydew = new Food("Honeydew", "change to juice $2.50", 0.70, null, fruitStall);
		Food papaya = new Food("Papaya", "change to juice $2.50", 0.60, null, fruitStall);
		Food pineapple = new Food("Pineapple", "change to juice $2.50", 0.60, null, fruitStall);
		Food banana = new Food("Banana", "change to juice $2.50", 0.60, null, fruitStall);
		Food agaragar = new Food("Agar Agar", "", 0.60, null, fruitStall);
		Food orange = new Food("Orange", "change to juice $2.50", 0.60, null, fruitStall);
		Food guava = new Food("Guava", "change to juice $2.50", 0.70, null, fruitStall);
		Food sarawakPineapple = new Food("Sarawak Pineapple", "change to juice $2.50", 1.00, null,
				fruitStall);
		Food mixedFruits = new Food("Mixed Fruits", "upsize $3.50", 3.00, null, fruitStall);

		
		File file24 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile24 = new byte[(int) file24.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file24);
	     //convert file into array of bytes
	     fileInputStream.read(bFile24);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        apple.setImage(bFile24);
        
        File file25 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile25 = new byte[(int) file25.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file25);
	     //convert file into array of bytes
	     fileInputStream.read(bFile25);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        watermelon.setImage(bFile25);
        
        
        File file26 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile26 = new byte[(int) file26.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file26);
	     //convert file into array of bytes
	     fileInputStream.read(bFile26);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        dragonfruit.setImage(bFile26);
        
        
        File file27 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile27 = new byte[(int) file27.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file27);
	     //convert file into array of bytes
	     fileInputStream.read(bFile27);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        pear.setImage(bFile27);
        
        
        File file28 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile28 = new byte[(int) file1.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file28);
	     //convert file into array of bytes
	     fileInputStream.read(bFile28);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        honeydew.setImage(bFile28);
        
        File file29 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile29 = new byte[(int) file29.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file29);
	     //convert file into array of bytes
	     fileInputStream.read(bFile29);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        papaya.setImage(bFile29);
        
        
        File file30 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile30 = new byte[(int) file30.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file30);
	     //convert file into array of bytes
	     fileInputStream.read(bFile30);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        pineapple.setImage(bFile30);
        
        File file31 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile31 = new byte[(int) file31.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file31);
	     //convert file into array of bytes
	     fileInputStream.read(bFile31);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        banana.setImage(bFile31);
        
        File file32 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile32 = new byte[(int) file32.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file32);
	     //convert file into array of bytes
	     fileInputStream.read(bFile32);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        agaragar.setImage(bFile32);
        
        
        File file33 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile33 = new byte[(int) file33.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file33);
	     //convert file into array of bytes
	     fileInputStream.read(bFile33);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        orange.setImage(bFile33);
        
        
        File file34 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile34 = new byte[(int) file34.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file34);
	     //convert file into array of bytes
	     fileInputStream.read(bFile34);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        guava.setImage(bFile34);
        
        File file35 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile35 = new byte[(int) file35.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file35);
	     //convert file into array of bytes
	     fileInputStream.read(bFile35);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        sarawakPineapple.setImage(bFile35);
        
        File file36 = new File("WebContent\\assets\\images\\img-friedrice.jpg");
        byte[] bFile36 = new byte[(int) file36.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file36);
	     //convert file into array of bytes
	     fileInputStream.read(bFile36);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }
        mixedFruits.setImage(bFile36);
		
		
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

		// **************************************** End insert of mock data
		// ****************************************

		session.getTransaction().commit();
		session.flush();
		session.close();

		System.out.println("Test.java completed");

	}
}
