package dao;

import java.util.ArrayList;
import java.util.List;

import model.Company;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;

import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the Company model
 * 
 * @author SMU Team Eureka
 */
public class CompanyDAO {
	
	/**
	 * Creates a default constructor for CompanyDAO
	 */
	public CompanyDAO() {
		
	}
	/**
	 * Retrieves the Company based on the provided ID
	 * @param companyId The ID used for retrieving the Company
	 * @return The Company object that has the provided ID
	 */
	public Company getCompany(int companyId) {
		return (Company) MyConnection.get(Company.class, companyId);
	}

	/**
	 * Retrieve the company from the Database
	 * 
	 * @param companyCode. The company code of the company to be retrieved
	 * @return The company whose company code matches the given parameters. Otherwise, returns null.
	 */
	public Company getCompanyByCompanyCode(String companyCode) {
		System.out.println(companyCode);
		return (Company) MyConnection.getCompanyByCompanyCode(companyCode);

	}

	/**
	 * Save company's details in Database
	 * 
	 * @param Company. The company whose details are to be saved in Database
	 */
	public void saveCompany(Company c) {
		MyConnection.save(c);
	}

	/**
	 * Update company's details in Database
	 * 
	 * @param Company. The company whose details are to be updated in Database
	 */
	public void updateCompany(Company c) {
		MyConnection.update(c);
	}

	/**
	 * Delete company from Database
	 */
	public void deleteCompany(Company c) {
		MyConnection.delete(c);
	}

	/**
	 * Retrieve all companies
	 * 
	 * @return An ArrayList of companies
	 */
	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> returnList = null;

		DetachedCriteria dc = DetachedCriteria.forClass(Company.class);
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		returnList = new ArrayList<Company>();

		for (Object o : l) {
			returnList.add((Company) o);
		}
		return returnList;
	}
}
