package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
		TreeMap<String, ArrayList<FoodOrder>> monthToFoodOrders = new TreeMap<>(Collections.reverseOrder());
		System.out.println(list.size());

		List<String> week = foodOrderDAO.getUniqueWeekInFoodOrderForUser(employee);
		for (Object wk : week) {
			System.out.println(wk.toString());
		}
		ArrayList<ArrayList<Date>> wl = new ArrayList<>();
		for (Object o : week) {
			String d = (String) o;
			String[] arr = d.split("to");
			ArrayList<Date> dateRange = new ArrayList<>();
			for (int i = 0; i < arr.length; i++) {
				// Date.parse(sp);
				String sp = arr[i];
				if (i == 1) {
					sp = arr[i] + " 23:59:59";
				} else {
					sp = arr[i] + " 00:00:00";
				}
				 

				 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				try {
					Date date = formatter.parse(sp);
					System.out.println(date);
					dateRange.add(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// System.out.println(d);
			wl.add(dateRange);
			System.out.println("");
		}

		

		

		System.out.println("ATTENION");
		
//		for (FoodOrder fo : theList) {
//			Date fd = fo.getCreateDate();
//			System.out.println(fd);
//			for (ArrayList<Date> dl : wl) {
//				Date start = dl.get(0);
//				Date end = dl.get(1);
//
//				System.out.print(start + " and " + end);
//				System.out.println("");
//				if ((fd.after(start)) && (fd.before(end))) {
//					System.out.println("TRUE");
//				}
//			}
//		}
		
		TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrder = new TreeMap<>(Collections.reverseOrder());
		
		for (Object o : week) {
			String weekRange = (String) o;
			
			
			
		}
		
		
	
		
		
		
		
		
		
		for (int i = 0; i < wl.size(); i++) {
				ArrayList<Date> dlist = wl.get(i);
				Date start = dlist.get(0);
				Date end = dlist.get(1);
			
			System.out.println("====");
			ArrayList<FoodOrder> sortList = new ArrayList<>();
			for (FoodOrder fo: theList) {
				Date fd = fo.getCreateDate();
				if ((fd.after(start)) && (fd.before(end))) {
					sortList.add(fo);
				}
			}
			if (!sortList.isEmpty()) {
			weekToFoodOrder.put(week.get(i), sortList);
			}
		}
		
		
		
		System.out.println(weekToFoodOrder.size());
		
		
		
		TreeMap<String, Double> weekToTotalPrice = new TreeMap<>(Collections.reverseOrder());
		
		Set<String> keySet = weekToFoodOrder.keySet();
		Iterator iter = keySet.iterator();
		while(iter.hasNext()) {
			String weeklabel = (String)iter.next();
			System.out.println(weeklabel);
			System.out.println("-------");
			ArrayList<FoodOrder> fList = weekToFoodOrder.get(weeklabel);
			double sum = 0.0;
			for (FoodOrder fo : fList) {
				System.out.println(fo.getCreateDate());
				System.out.println(fo.getFinalPrice());
				double price = Math.round(fo.getFinalPrice() * 100.0) / 100.0;
				if (price < 0) {
					price = 0.0;
				}
				 sum += price;
			}
			System.out.println("======");
			System.out.println("Total price for the week: " + sum);
			System.out.println();
			weekToTotalPrice.put(weeklabel, sum);
		}
		
		
		
		
		
		
		Set<String> wkLabel = weekToTotalPrice.keySet();
		Iterator iterator = wkLabel.iterator();
		while(iterator.hasNext()) {
			String wklbl = (String) iterator.next();
			System.out.print(wklbl + " Price: ");
			double price = weekToTotalPrice.get(wklbl);
			System.out.print(price);
			System.out.println("");
		}
//			Date start = dlist.get(0);
//			Date end = dlist.get(1);
//			System.out.println(start + " and " + end);
//			for(FoodOrder fo: theList) {
//				Date cd = fo.getCreateDate();
//				if (cd.after(start) && cd.before(end)) {
//					//newList.add(fo);
//				}
//			}
//			//weekToFoodOrder.put(weekRange, newList);
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		TreeMap<String, ArrayList<String>> yearToMonthList = new TreeMap<>(Collections.reverseOrder());

		// for (Object o : yearList) {
		// String year = (String) o;
		// //System.out.println("LOOP 1st layer " + year);
		// ArrayList<String> l = new ArrayList<>();
		for (Object obj : list) {
			String monthYear = (String) obj;
			// System.out.println("LOOP 2nd layer ");

			ArrayList<FoodOrder> sortList = new ArrayList<>();
			// if (monthYear.contains(year)) {
			// l.add(monthYear);
			// System.out.println("**" + year + " and " + monthYear + " true");
			for (FoodOrder order : theList) {
				// System.out.println("LOOP 3rd layer ");
				String str = order.getCreateDate().toString();
				// System.out.println(str);
				if (str.contains(monthYear)) {
					sortList.add(order);
					// System.out.println(str + " added");
				}
				// System.out.println("End of 3rd Layer");
			}
			// System.out.println("Putting " + monthYear + " into Map");
			monthToFoodOrders.put(monthYear, sortList);
		}

		// System.out.println("Putting " + year + " into Map");
		// yearToMonthList.put(year, l);
		// }

		Set<String> keyList = monthToFoodOrders.keySet();
		Iterator itera = keyList.iterator();
		while (itera.hasNext()) {
			String str = (String) itera.next();
			// Set<String> key = yearToMonthList.keySet();
			// Iterator iter = key.iterator();
			// while (iter.hasNext()) {
			// String yr = (String) iter.next();
			// System.out.println("----------");
			// System.out.println("YEAR " + yr);
			// System.out.println("----------");
			// ArrayList<String> s = yearToMonthList.get(yr);
			// for (String str : s) {
			System.out.println("**********");
			System.out.println("MONTH " + str);
			System.out.println("**********");
			ArrayList<FoodOrder> tList = monthToFoodOrders.get(str);
			double sum = 0.0;
			for (FoodOrder fo : tList) {
				double price = Math.round(fo.getFinalPrice() * 100.0) / 100.0;
				if (price < 0) {
					price = 0.0;
				}
				sum += price;
				System.out.println(fo.getCreateDate().toString() + "\t Final Price:" + price);

				System.out.println("=======================");

			}
			System.out.println("Total price for the month: " + sum);
			System.out.println();
			//
		}
		// }

		// Set<String> keyYear = yearToMonthList.keySet();
		// Iterator iter = keyYear.iterator();
		// while(iter.hasNext()) {
		// String year = (String) iter.next();
		// System.out.println(year);
		// TreeMap<String, ArrayList<FoodOrder>> tm = yearToMonthList.get(year);
		// Set<String> keyMonth = tm.keySet();
		// Iterator iter1 = keyMonth.iterator();
		// while(iter1.hasNext()) {
		// String month = (String) iter1.next();
		// System.out.println(month);
		// ArrayList<FoodOrder> order = tm.get(month);
		// for (FoodOrder f : order) {
		// System.out.println(f.getCreateDate().toString());
		// }
		// }
		// }

		// for (Object o : list) {
		// String monthYear = (String) o;
		// ArrayList<FoodOrder> sortList = new ArrayList<>();
		// for(FoodOrder fo : theList) {
		// Date date = fo.getCreateDate();
		// String str = date.toString();
		// if (str.contains(monthYear)) {
		// sortList.add(fo);
		// }
		// }
		// map.put(monthYear, sortList);
		// }
		// Set<Object> key = list.keySet();
		// Iterator iter = key.iterator();
		// while (iter.hasNext()) {
		// String value = (String) iter.next();
		// ArrayList<FoodOrder> sortList = new ArrayList<>();
		// for(FoodOrder fo : theList) {
		// Date date = fo.getCreateDate();
		// String str = date.toString();
		// if (str.contains(value)) {
		// sortList.add(fo);
		// }
		// }
		// map.put(value, sortList);
		// }

		// System.out.println("*****Next*******");
		//
		// Set<String> keyInMap = map.keySet();
		// Iterator itera = keyInMap.iterator();
		// while(itera.hasNext()) {
		// String keykey = (String) itera.next();
		// System.out.println(keykey);
		// System.out.println("--------------");
		// ArrayList<FoodOrder> j = map.get(keykey);
		// for (FoodOrder f : j) {
		// System.out.println(f.getCreateDate().toString());
		// }
		// System.out.println("");
		// }

		// for (Object o : yearList) {
		// String year = (String) o;
		// System.out.println(year);
		// Set<Object> keyInMap1 = map.keySet();
		// Iterator itera1 = keyInMap1.iterator();
		// while(itera1.hasNext()) {
		// String keykey = (String) itera1.next();
		// if (keykey.contains(year)) {
		// System.out.println(keykey);
		// System.out.println("--------------");
		// ArrayList<FoodOrder> j = map.get(keykey);
		// for (FoodOrder f : j) {
		// System.out.println(f.getCreateDate().toString());
		// }
		// System.out.println("");
		// }
		// }
		// }

	}
}
