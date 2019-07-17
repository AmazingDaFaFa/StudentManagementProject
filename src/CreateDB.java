import java.sql.*;


public class CreateDB {

	public static void main(String[] args) throws Exception {
		String sql[]=new String[5];
		
		
		try {
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementDemo?serverTimezone=UTC", "root","fengfan17");
			System.out.println("连接MySql server 成功!"); //Connect DB
			
			Statement statement=connection.createStatement();
			sql[0]="create table User(userID int(8),userName varchar(8),userPassword varchar(10),userType varchar(7))";
			sql[1]="create table Student(SID int(8),Sname varchar(8),Ssex varchar(4),Sgrade	varchar(4),Smajor varchar(20),Shometown	varchar(20),Stel varchar(20),Semail varchar(20))";
			sql[2]="create table StudentCourse(SID int(8),CID int(8),Cscore varchar(4))";
			sql[3]="create table Teacher(TID int(8),Tname varchar(8),Tsex varchar(4))";
			sql[4]="create table TeacherCourse(TID int(8),CID int(8),Cname varchar(20),Ccapacity varchar(10),Cterm varchar(10))";
			for(int i=0;i<sql.length;i++) {
				int result=statement.executeUpdate(sql[i]);
				if(result!=-1) {
					System.out.println("创建表成功！");
				}
			}
			
		}catch (Exception e) {
	    	System.out.println("连接Mysql server 失败!");
	    	System.out.println(e.toString());
	    }
  }
}
