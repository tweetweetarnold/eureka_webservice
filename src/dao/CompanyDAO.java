package dao;

import model.Company;
import connection.MyConnection;

public class CompanyDAO {
	
	public CompanyDAO() {
		
	}
	
	public static Company getCompany(int companyId) {
		return (Company) MyConnection.get(Company.class, companyId);
	}
	
	public static void saveCompany(Company c) {
		MyConnection.save(c);
	}
	
	public static void updateCompany(Company c) {
		MyConnection.update(c);
	}
	
	public static void deleteCompany(Company c) {
		MyConnection.delete(c);
	}
}
