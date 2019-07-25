package run;

import DB.DBUtil;
import View.Frame.*;

public class MainApp {
	 AppState curState;
	
	public static void main(String[] args) {
//		DBUtil db = new DBUtil();
//		try {
//			db.createTable();
//			db.readFile("data.txt");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Create Table Failed.");
//		}
//		db.close(); 
		Login.main();
		
	}
	
	public void changeCurState(AppState nextState) {
		this.curState = nextState;
		
	}
	
}
