package dao;

import model.Company;
import connection.MyConnection;

public class CompanyDAO {

	public CompanyDAO() {
	}

	// Retrieve Company from the DB with companyID
	public static Company getCompany(int companyId) {
		return (Company) MyConnection.get(Company.class, companyId);
	}
	
	public static Company getCompanyByCompanyCode(String companyCode) {
		return (Company) MyConnection.getCompanyByCompanyCode(companyCode);
	}

	// Save new Company to the DB
	public static void saveCompany(Company c) {
		MyConnection.save(c);
	}

	// Update existing Company in the DB
	public static void updateCompany(Company c) {
		MyConnection.update(c);
	}

	// Delete Company from the DB
	public static void deleteCompany(Company c) {
		MyConnection.delete(c);
	}
}
