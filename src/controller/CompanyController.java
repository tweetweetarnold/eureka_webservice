package controller;

import java.util.ArrayList;

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
	 * @param c The Company object to be added in
	 */
	public void addNewCompany(Company c) {
		companyDAO.saveCompany(c);
	}

	/**
	 * Updates the designated Company object in the Database
	 * 
	 * @param c The Company object to be updated
	 */
	public void updateCompany(Company c) {
		companyDAO.updateCompany(c);
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
	 * Retrieve a Company from the database by an ID
	 * 
	 * @param companyId The ID that belongs to the Company
	 * @return A Company object that has the specified ID
	 */
	public Company getCompany(int companyId) {
		return companyDAO.getCompany(companyId);
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
	
	/**
	 * Retrieve all the Companies from the Database
	 * @return An ArrayList of Company objects stored in the Database
	 */
	public ArrayList<Company> getAllCompany() {
		return companyDAO.getAllCompany();
	}
}
