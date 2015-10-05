package controller;

import model.*;
import dao.*;

public class CompanyController {
	//Add a new Company into the DB
	public void addNewCompany(Company c) {
		CompanyDAO.saveCompany(c);
	}
	//Update a existing Company in the DB
	public void updateCompany(Company c) {
		CompanyDAO.updateCompany(c);
	}
	//Remove a Company from the DB
	public void removeCompany(Company c) {
		CompanyDAO.deleteCompany(c);
	}
	//Retrieve a Company from the DB by companyID
	public Company retrieveCompany(int companyId) {
		Company c = CompanyDAO.getCompany(companyId);
		return c;
	}
}
