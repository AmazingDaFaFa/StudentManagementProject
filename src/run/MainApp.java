package run;

import View.JFrame.*;
import DB.DBUtil;

public class MainApp {
	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		try {
			db.createTable();
			db.readFile("data.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Create Table Failed.");
		}
		db.close(); 
		new Login(); 
		
	} 
}
