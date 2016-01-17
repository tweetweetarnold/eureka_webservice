package test;

import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDAO;
import dao.FoodOrderDAO;
import model.Employee;
import model.FoodOrder;

public class Testtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.getEmployeeByEmail("chris.cheng.2013@sis.smu.edu.sg");
		FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
		List<FoodOrder> foodOrderList = foodOrderDAO.getFoodOrderSet(employee);
		for(FoodOrder foodOrder : foodOrderList){
			System.out.println(foodOrder.getCreateDate());
		}
	}

}
