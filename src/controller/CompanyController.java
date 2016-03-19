package controller;

import java.util.ArrayList;
import java.util.Set;

import dao.CompanyDAO;
import model.Company;

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
	 * @param c The Company object to be added in
	 */
	// public void addNewCompany(Company c) {
	// companyDAO.saveCompany(c);
	// }

	// public void addNewCompany(String name, String companyCode) {
	// Company c = new Company(name, companyCode);
	// companyDAO.saveCompany(c);
	// }

	public void addNewCompany(String name, String companyCode, Set<String> buildings)
			throws Exception {
		String errorMessages = validateNewCompanyInputs(name, companyCode, buildings);
		if (!errorMessages.isEmpty()) {
			throw new Exception(errorMessages);
		}

		boolean companyCodeExists = checkCompanyCodeExists(companyCode);
		boolean companyNameExists = checkCompanyNameExists(name);

		if (companyCodeExists) {
			throw new Exception("Company Code already exists");
		}

		if (companyNameExists) {
			throw new Exception("Company Name already exists");
		}

		Company c = new Company(name, companyCode, buildings);
		companyDAO.saveCompany(c);
	}

	public boolean checkCompanyCodeExists(String companyCode) {
		Company c = getCompanyByCompanyCode(companyCode);
		if (c != null) {
			return true;
		}
		return false;

	}

	public boolean checkCompanyNameExists(String companyName) {
		Company c = getCompanyByName(companyName);
		if (c != null) {
			return true;
		}
		return false;
	}

	public void deleteDeliveryPoint(String companyId, String deliveryPoint) throws Exception {

		Company c = getCompany(Integer.parseInt(companyId));
		Set<String> deliverySet = c.getDeliveryPointSet();
		if (!deliverySet.contains(deliveryPoint)) {
			throw new Exception("Delivery point does not exist");
		} else {
			deliverySet.remove(deliveryPoint);
		}
		EmployeeController employeeController = new EmployeeController();
		employeeController.notifyAllEmployeesFromCompanyWithDeliveryPoint(c, deliveryPoint);

	}

	public void editCompany(int companyId, String name, String companyCode, Set<String> buildings)
			throws Exception {
		String errorMessages = validateNewCompanyInputs(name, companyCode, buildings);
		if (!errorMessages.isEmpty()) {
			throw new Exception(errorMessages);
		}

		boolean changesInName = false;
		boolean changesInCompanyCode = false;
		Company companyToEdit = null;
		try {
			companyToEdit = getCompany(companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!name.equals(companyToEdit.getName()) && !name.isEmpty()) {
			companyToEdit.setName(name);
			changesInName = true;
		}

		if (!companyCode.equals(companyToEdit.getCompanyCode()) && !name.isEmpty()) {
			companyToEdit.setCompanyCode(companyCode);
			changesInCompanyCode = true;
		}

		companyToEdit.setDeliveryPointSet(buildings);

		if (changesInName) {
			if (checkCompanyNameExists(name)) {
				throw new Exception("Company name already taken");
			}
		}

		if (changesInCompanyCode) {
			if (checkCompanyCodeExists(companyCode)) {
				throw new Exception("Company code already taken");
			}
		}
		updateCompany(companyToEdit);
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
	 * @param companyId The ID that belongs to the Company
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
	 * @param companyCode The company code which is used to identify a Company
	 * @return A Company object that has the specified company code
	 */
	public Company getCompanyByCompanyCode(String companyCode) {
		return companyDAO.getCompanyByCompanyCode(companyCode.toUpperCase());
	}

	public Company getCompanyByName(String companyName) {
		return companyDAO.getCompanyByName(companyName);
	}

	/**
	 * Removes the designated Company object from the Database
	 * 
	 * @param c The Company object to be removed
	 */
	public void removeCompany(Company c) {
		companyDAO.deleteCompany(c);
	}

	/**
	 * Updates the designated Company object in the Database
	 * 
	 * @param c The Company object to be updated
	 */
	public void updateCompany(Company c) {
		companyDAO.updateCompany(c);
	}

	public String validateNewCompanyInputs(String companyName, String companyCode,
			Set<String> buildings) {
		String errors = "";

		if (companyName == null || companyName.isEmpty()) {
			errors += "Company Name is Empty.\n";
		}

		if (companyCode == null || companyCode.isEmpty()) {
			errors += "Company code is Empty.\n";

		}

		if (buildings == null || buildings.isEmpty()) {
			errors += "Delivery Point is Empty.\n";
		}
		return errors;
	}

}
