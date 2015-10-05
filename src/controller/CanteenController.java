package controller;

import java.util.List;

import dao.CanteenDAO;

public class CanteenController {
	CanteenDAO canteenDao = new CanteenDAO();
	//Retrieve all Canteens From the DB
	public List retrieveAll(){
		return canteenDao.getAllCanteens();
	}
	
}
