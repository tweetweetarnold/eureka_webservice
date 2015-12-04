package controller;

import model.*;
import dao.*;

public class CompanyController {
	CompanyDAO companyDAO = new CompanyDAO();

	// Add a new Company into the DB
	public void addNewCompany(Company c) {
		companyDAO.saveCompany(c);
	}

	// Update a existing Company in the DB
	public void updateCompany(Company c) {
		companyDAO.updateCompany(c);
	}

	// Remove a Company from the DB
	public void removeCompany(Company c) {
		companyDAO.deleteCompany(c);
	}

	// Retrieve a Company from the DB by companyID
	public Company getCompany(int companyId) {
		return companyDAO.getCompany(companyId);
	}

	public Company getCompanyByCompanyCode(String companyCode) {
		return companyDAO.getCompanyByCompanyCode(companyCode.toUpperCase());
	}
}
