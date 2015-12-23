package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVReader;

import dao.CanteenDAO;
import dao.StallDAO;
import model.Canteen;
import model.Stall;

public class FileUploadController {
	StallDAO stallDAO = new StallDAO();
	CanteenDAO canteenDAO = new CanteenDAO();
	
	public void processStallUpload(InputStream is) {
		System.out.println("PROCESS_STALL_FILEUPLOAD");
		List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        //Set<Stall> stallList = new HashSet<Stall>();
        Canteen canteen = null;
		try {
		        CSVReader csvreader = new CSVReader(br);
				content = csvreader.readAll();
		
				Iterator iter = content.iterator();
	            iter.next();
	            while (iter.hasNext()) {
	                String[] row = (String[]) iter.next();
	                String stallName = row[0].trim();
	                String contactNum = row[1].trim();
	                long contactNumber = Long.parseLong(contactNum);
	                String canteenName = row[2].trim();
	                canteen = canteenDAO.getCanteenByName(canteenName);
	               
	                Stall newStall = new Stall(stallName, contactNumber, canteen, null, null);
	             //   stallList.add(newStall);
	                canteenDAO.addStallToCanteen(canteen, newStall);
	                stallDAO.saveStall(newStall);
	            }
	            
	          //  canteen.setStallList(stallList);
	          //  canteenDAO.updateCanteen(canteen);
	          //  for (Stall stall : stallList) {
	          //  	stallDAO.saveStall(stall);
	         //   }
	            
			
	            csvreader.close();
	     
	     
	     
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void processCanteenUpload(InputStream is) {
		System.out.println("PROCESS_CANTEEN_FILEUPLOAD");
		List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
		try {
		        CSVReader csvreader = new CSVReader(br);
				content = csvreader.readAll();
		
				Iterator iter = content.iterator();
	            iter.next();
	            while (iter.hasNext()) {
	                String[] row = (String[]) iter.next();
	                String canteenName = row[0].trim();
	                String address = row[1].trim();
	                
					Canteen newCanteen = new Canteen(canteenName, address, null);
					canteenDAO.saveCanteen(newCanteen);
	               // System.out.println(stallName + "<> " + contactNumber);
	            }
			
	            csvreader.close();
	     
	     
	     
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
