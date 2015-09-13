package controller;

import java.util.List;

import dao.CanteenDAO;

public class CanteenController {
	CanteenDAO canteenDao = new CanteenDAO();
	
	public List retrieveAll(){
		return canteenDao.getAllCanteens();
	}
	
}
