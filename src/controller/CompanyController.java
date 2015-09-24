package controller;

import model.*;
import dao.*;

public class CompanyController {
	
	public void addNewCompany(Company c) {
		CompanyDAO.saveCompany(c);
	}
	
	public void updateCompany(Company c) {
		CompanyDAO.updateCompany(c);
	}
	
	public void removeCompany(Company c) {
		CompanyDAO.deleteCompany(c);
	}
	
	public Company retrieveCompany(int companyId) {
		Company c = CompanyDAO.getCompany(companyId);
		return c;
	}
}
