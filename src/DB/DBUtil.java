package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import Constans.AppConstants;

public class DBUtil {

	private static DBUtil db;

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public DBUtil() {

	}

	// 创建数据表
	public void createTable() throws Exception {
		String sql[] = new String[5];

		Statement statement = getConn().createStatement();
		sql[0] = "create table User(userID int(8),userName varchar(20),userPassword varchar(10),userType varchar(7))";
		sql[1] = "create table Student(SID int(8),Sname varchar(20),Ssex varchar(4),Sgrade varchar(4),Smajor varchar(20),Shometown varchar(20),Stel varchar(20),Semail varchar(20))";
		sql[2] = "create table StudentCourse(SID int(8),CID int(8),Cname varchar(10),Cscore varchar(4))";
		sql[3] = "create table Teacher(TID int(8),Tname varchar(20),Tsex varchar(4))";
		sql[4] = "create table TeacherCourse(TID int(8),CID int(8),Cname varchar(20),Ccapacity varchar(10),Cterm varchar(10))";
		for (int i = 0; i < sql.length; i++) {
			int result = statement.executeUpdate(sql[i]);
			if (result != -1) {
				System.out.println("创建表成功！");
			}
		}
	}

	// 插入数据方法，开文本流插入数据集入库
	
	//"data.txt"
	public void readFile(String filepath) throws SQLException, IOException {
		Statement statement = getConn().createStatement();
		int result;

		try {
			FileReader fReader = new FileReader(filepath);
			BufferedReader bufferedReader = new BufferedReader(fReader);
			String sql;
			while ((sql = bufferedReader.readLine()) != null) {
				result = statement.executeUpdate(sql);
				if (result != -1) {
					System.out.println("插入数据成功！");
				} else {
					System.out.println("插入数据失败！");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取数据库工具方法
	// 新建数据库工具对象
	public static DBUtil getDBUtil() {
		if (db == null) {
			db = new DBUtil();
		}
		return db;
	}

	// 执行更新-SQL语句，（无参数）返回受影响行数
	public int executeUpdate(String sql) {
		int result = -1;
		if (getConn() == null) {
			return result;
		}
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 执行更新-SQL语句，（带参数数组）返回受影响行数
	public int executeUpdate(String sql, Object[] obj) {
		int result = -1;
		if (getConn() == null) {
			return result;
		}
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			result = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 执行查询-SQL语句，（无参数）返回受影响行数
	public ResultSet executeQuery(String sql) {
		if (getConn() == null) {
			return null;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// 执行查询-SQL语句，（含参数数组）返回受影响行数
	public ResultSet executeQuery(String sql, Object[] obj) {
		if (getConn() == null) {
			return null;
		}
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	// 执行SQL语句，返回成功|失败
	public boolean exeute(String sql) {
		if (getConn() == null) {
			return false;
		}
		try {
			Statement statement = conn.createStatement();
			statement.execute(sql);
			statement.close();
			return true;
		} catch (SQLException e) {
//   e.printStackTrace();
			return false;
		}
	}

	// 连接数据库，返回连接对象
	private Connection getConn() {
		try {
			if (conn == null || conn.isClosed()) {
//    Class.forName(AppConstants.JDBC_DRIVER);
				conn = DriverManager.getConnection(AppConstants.JDBC_URL, AppConstants.JDBC_USERNAME,
						AppConstants.JDBC_PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭数据库
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}