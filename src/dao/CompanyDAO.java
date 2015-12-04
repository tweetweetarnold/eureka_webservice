package dao;

import model.Company;
import connection.MyConnection;

public class CompanyDAO {

	// Retrieve Company from the DB with companyID
	public Company getCompany(int companyId) {
		return (Company) MyConnection.get(Company.class, companyId);
	}

	public Company getCompanyByCompanyCode(String companyCode) {
		System.out.println(companyCode);
		return (Company) MyConnection.getCompanyByCompanyCode(companyCode);

	}

	// Save new Company to the DB
	public void saveCompany(Company c) {
		MyConnection.save(c);
	}

	// Update existing Company in the DB
	public void updateCompany(Company c) {
		MyConnection.update(c);
	}

	// Delete Company from the DB
	public void deleteCompany(Company c) {
		MyConnection.delete(c);
	}
}
