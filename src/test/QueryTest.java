package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import controller.FoodOrderController;
import dao.EmployeeDAO;
import dao.FoodOrderDAO;
import model.Employee;
import model.FoodOrder;

public class QueryTest {
	public static void main(String[] args) {
		FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
		FoodOrderController foodOrderController = new FoodOrderController();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.getEmployeeByEmail("chris.cheng.2013@sis.smu.edu.sg");
		List<Object> list = foodOrderDAO.getUniqueMonthYearInFoodOrderForUser(employee);
		List<Object> yearList = foodOrderDAO.getUniqueYearInFoodOrderForUser(employee);
		List<FoodOrder> theList = foodOrderController.getFoodOrderSet("chris.cheng.2013@sis.smu.edu.sg");
		TreeMap<String, ArrayList<FoodOrder>> map = new TreeMap<>(Collections.reverseOrder());
		System.out.println(list.size());
		
		TreeMap<String,ArrayList<String> > yearToMonthList = new TreeMap<>(Collections.reverseOrder());
		
		for (Object o : yearList) {
			String year = (String) o;
			//System.out.println("LOOP 1st layer " + year);
			ArrayList<String> l = new ArrayList<>();
			for (Object obj : list) {
				String monthYear = (String) obj;
				//System.out.println("LOOP 2nd layer ");
				
				
				
				ArrayList<FoodOrder> sortList = new ArrayList<>();
				if (monthYear.contains(year)) {
					l.add(monthYear);
					//System.out.println("**" + year + " and " + monthYear + " true");
					for (FoodOrder order : theList) {
						//System.out.println("LOOP 3rd layer ");
						String str = order.getCreateDate().toString();
						//System.out.println(str);
						if (str.contains(monthYear)) {
							sortList.add(order);
							//System.out.println(str + " added");
						}
						//System.out.println("End of 3rd Layer");
					}
				//	System.out.println("Putting " + monthYear + " into Map");
					map.put(monthYear, sortList);
				}
			}
		//	System.out.println("Putting " + year + " into Map");
			yearToMonthList.put(year, l);
		}
		
		
		
		
		
		
		
		
		Set<String> key = yearToMonthList.keySet();
		Iterator iter = key.iterator();
		while (iter.hasNext()) {
			String yr = (String) iter.next();
			System.out.println("----------");
			System.out.println("YEAR " + yr);
			System.out.println("----------");
			ArrayList<String> s = yearToMonthList.get(yr);
			for (String str : s) {
				System.out.println("**********");
				System.out.println("MONTH " + str);
				System.out.println("**********");
				ArrayList<FoodOrder> tList = map.get(str);
				for(FoodOrder fo : tList) {
					System.out.println(fo.getCreateDate().toString());
					System.out.println("=======================");
				}
				System.out.println();
//				
			}
		}
		
		
//		Set<String> keyYear = yearToMonthList.keySet();
//		Iterator iter = keyYear.iterator();
//		while(iter.hasNext()) {
//			String year = (String) iter.next();
//			System.out.println(year);
//			TreeMap<String, ArrayList<FoodOrder>> tm = yearToMonthList.get(year);
//			Set<String> keyMonth = tm.keySet();
//			Iterator iter1 = keyMonth.iterator();
//			while(iter1.hasNext()) {
//				String month = (String) iter1.next();
//				System.out.println(month);
//				ArrayList<FoodOrder> order = tm.get(month);
//				for (FoodOrder f : order) {
//					System.out.println(f.getCreateDate().toString());
//				}
//			}
//		}
		
		
		
		
		
		
		
//		for (Object o : list) {
//			String monthYear = (String) o;
//			ArrayList<FoodOrder> sortList = new ArrayList<>();
//			for(FoodOrder fo : theList) {
//				Date date = fo.getCreateDate();
//				String str = date.toString();
//				if (str.contains(monthYear)) {
//					sortList.add(fo);
//				}
//			}
//			map.put(monthYear, sortList);
//		}
//		Set<Object> key = list.keySet();
//		Iterator iter = key.iterator();
//		while (iter.hasNext()) {
//			String value = (String) iter.next();
//			ArrayList<FoodOrder> sortList = new ArrayList<>();
//			for(FoodOrder fo : theList) {
//				Date date = fo.getCreateDate();
//				String str = date.toString();
//				if (str.contains(value)) {
//					sortList.add(fo);
//				}
//			}
//			map.put(value, sortList);
//		}
		
		
//		System.out.println("*****Next*******");
//		
//		Set<String> keyInMap = map.keySet();
//		Iterator itera = keyInMap.iterator();
//		while(itera.hasNext()) {
//			String keykey = (String) itera.next();
//			System.out.println(keykey);
//			System.out.println("--------------");
//			ArrayList<FoodOrder> j = map.get(keykey);
//			for (FoodOrder f : j) {
//				System.out.println(f.getCreateDate().toString());
//			}
//			System.out.println("");
//		}
		
//		for (Object o : yearList) {
//			String year = (String) o;
//			System.out.println(year);
//			Set<Object> keyInMap1 = map.keySet();
//			Iterator itera1 = keyInMap1.iterator();
//			while(itera1.hasNext()) {
//				String keykey = (String) itera1.next();
//				if (keykey.contains(year)) {
//				System.out.println(keykey);
//				System.out.println("--------------");
//				ArrayList<FoodOrder> j = map.get(keykey);
//				for (FoodOrder f : j) {
//					System.out.println(f.getCreateDate().toString());
//				}
//				System.out.println("");
//				}
//			}
//		}
		
		
	}
}
