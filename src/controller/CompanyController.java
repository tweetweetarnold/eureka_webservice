package controller;

import java.util.ArrayList;
import java.util.Set;

import model.Canteen;
import model.Company;
import dao.CompanyDAO;

/**
 * Process the function of managing the Company's information
 * 
 * @author SMU Team Eureka
 * 
 */
public class CompanyController {
	CompanyDAO companyDAO = new CompanyDAO();

	/**
	 * Creates a default constructor for CompanyController
	 */
	public CompanyController() {

	}

	/**
	 * Adds a new Company object into the Database
	 * 
	 * @param c
	 *            The Company object to be added in
	 */
	public void addNewCompany(Company c) {
		companyDAO.saveCompany(c);
	}

	public void addNewCompany(String name, String companyCode) {
		Company c = new Company(name, companyCode);
		companyDAO.saveCompany(c);
	}
	
	public void addNewCompany(String name, String companyCode, Set<String> buildings) {
		Company c = new Company(name, companyCode);
		companyDAO.saveCompany(c);
	}

	public void editCompany(int companyId, String name, String companyCode, Set<String> buildings) {
		Company c = null;
		try {
			c = getCompany(companyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setName(name);
		c.setCompanyCode(companyCode);
		c.setDeliveryPointSet(buildings);
		updateCompany(c);
	}

	/**
	 * Retrieve all the Companies from the Database
	 * 
	 * @return An ArrayList of Company objects stored in the Database
	 */
	public ArrayList<Company> getAllCompany() {
		return companyDAO.getAllCompany();
	}

	/**
	 * Retrieve a Company from the database by an ID
	 * 
	 * @param companyId
	 *            The ID that belongs to the Company
	 * @return A Company object that has the specified ID
	 */
	public Company getCompany(int companyId) throws Exception {
		Company c = companyDAO.getCompany(companyId);
		if (c == null) {
			throw new Exception("Company does not exist");
		}
		return c;
	}

	/**
	 * Retrieve a Company based on a company code
	 * 
	 * @param companyCode
	 *            The company code which is used to identify a Company
	 * @return A Company object that has the specified company code
	 */
	public Company getCompanyByCompanyCode(String companyCode) {
		return companyDAO.getCompanyByCompanyCode(companyCode.toUpperCase());
	}

	/**
	 * Removes the designated Company object from the Database
	 * 
	 * @param c
	 *            The Company object to be removed
	 */
	public void removeCompany(Company c) {
		companyDAO.deleteCompany(c);
	}

	public void deleteDeliveryPoint(String companyId, String deliveryPoint) throws Exception{

		Company c = getCompany(Integer.parseInt(companyId));
		Set<String> deliverySet = c.getDeliveryPointSet();
		if (!deliverySet.contains(deliveryPoint)) {
			throw new Exception("Delivery point does not exist");
		} else {
			deliverySet.remove(deliveryPoint);
		}
		EmployeeController employeeController = new EmployeeController();
		employeeController.notifyAllEmployeesFromCompanyWithDeliveryPoint(c,deliveryPoint);
		
	}

	/**
	 * Updates the designated Company object in the Database
	 * 
	 * @param c
	 *            The Company object to be updated
	 */
	public void updateCompany(Company c) {
		companyDAO.updateCompany(c);
	}

}
