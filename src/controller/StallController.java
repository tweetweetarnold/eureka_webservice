package controller;

import java.util.ArrayList;

import model.Canteen;
import model.Stall;
import dao.StallDAO;

public class StallController {
	StallDAO stallDAO = new StallDAO();

	public StallController() {
	}

	public ArrayList<Stall> getAllStallsUnderCanteen(Canteen canteen) {
		return stallDAO.getAllStallsUnderCanteen(canteen);
	}

	public Stall getStall(int stallId) {
		return stallDAO.getStall(stallId);
	}
}
