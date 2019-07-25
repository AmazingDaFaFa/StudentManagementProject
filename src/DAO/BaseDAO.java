package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBUtil;

//enum DAO{
// AdminDAO, StudentDAO, TeacherDAO
//};

public abstract class BaseDAO {
	protected final DBUtil db = DBUtil.getDBUtil();
	protected ResultSet rs;
	private static BaseDAO baseDAO;

	private static final int adminDAO = 2;
	private static final int teacherDAO = 1;
	private static final int studentDAO = 0;

	public BaseDAO() {
		init();
	}

	private void init() {
		// buildAbilityDAO();
	}

	// protected abstract void buildAbilityDAO();3

	public int queryForLogin(int userID, String password) {
		int result = -1;
		if (password.length() == 0) {
			return result;
		}
		String useDB = "USE studentmanagementdemo;";
		rs = db.executeQuery(useDB);
		String sql = "select usertype from User where UserID=? and Userpassword=?";
		Object[] param = { userID, password };
		rs = db.executeQuery(sql, param);
		try {
			if (rs.next()) {
				result = Integer.parseInt(rs.getString("UserType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	public static synchronized BaseDAO getAbilityDAO(int dao) {

		switch (dao) {
		case adminDAO:
			if (baseDAO == null || baseDAO.getClass() != AdminDAO.class) {
				baseDAO = AdminDAO.getInstance();
			}
			break;
		case studentDAO:
			if (baseDAO == null || baseDAO.getClass() != StudentDAO.class) {
				baseDAO = StudentDAO.getInstance();
			}
			break;
		case teacherDAO:
			if (baseDAO == null || baseDAO.getClass() != TeacherDAO.class) {
				baseDAO = TeacherDAO.getInstance();
			}
		default:
			break;
		}
		return baseDAO;
	}

	protected void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}