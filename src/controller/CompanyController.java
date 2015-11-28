package controller;

import model.*;
import dao.*;

public class CompanyController {
	// Add a new Company into the DB
	public static void addNewCompany(Company c) {
		CompanyDAO.saveCompany(c);
	}

	// Update a existing Company in the DB
	public static void updateCompany(Company c) {
		CompanyDAO.updateCompany(c);
	}

	// Remove a Company from the DB
	public static void removeCompany(Company c) {
		CompanyDAO.deleteCompany(c);
	}

	// Retrieve a Company from the DB by companyID
	public static Company getCompany(int companyId) {
		return CompanyDAO.getCompany(companyId);
	}

	public static Company getCompanyByCompanyCode(String companyCode) {
		return CompanyDAO.getCompanyByCompanyCode(companyCode.toUpperCase());
	}
}
