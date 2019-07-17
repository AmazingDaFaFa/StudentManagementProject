package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Student;

public class StudentDAO extends BaseDAO{
	
	private final int fieldNum = 9;
	private final int showNum = 15;
	private static StudentDAO sd = null;

	public static synchronized StudentDAO getInstance() {
		if (sd == null) {
			sd = new StudentDAO();
		}
		return sd;
	}

	// update
	public boolean update(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			// check
			if (queryByID(stu.getID()) == 0) {
				return result;
			}
			// update
			String sql = "update student set Ssex=?,Sgrade=?,Smajor=?,Shometown=?,Stel=?,Semail=? where Sname=? and Sid=?";
			Object[] param = { stu.getSex(), stu.getGrade(), stu.getMajor(), stu.getHometown(),stu.getEmail(), stu.getTel(),stu.getName(), stu.getID() };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// delete
	public boolean delete(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		String sql = "delete from student where Sname=? and SID=?";
		Object[] param = { stu.getName(), stu.getID() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}

	// add
	public boolean add(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			// check
			if (queryByID(stu.getID()) == 1) {
				return result;
			}
			// insert
			String sql = "insert into student(SID,Sname,Ssex,Sgrade,Smajor,Shometown,Stel,Semail) values(?,?,?,?,?,?,?,?)";
			Object[] param = { stu.getID(), stu.getName(), stu.getSex(), stu.getGrade(), stu.getMajor(), stu.getHometown(), stu.getTel(), stu.getEmail() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// query by name
	public String[][] queryByName(String name) {
		String[][] result = null;
		if (name.length() < 0) {
			return result;
		}
		ArrayList<Student> stus = new ArrayList<Student>();
		int i = 0;
		String sql = "select * from student where name like ?";
		String[] param = { "%" + name + "%" };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// query
	public String[][] list(int pageNum) {
		String[][] result = null;
		if (pageNum < 1) {
			return result;
		}
		ArrayList<Student> stus = new ArrayList<Student>();
		int i = 0;
		int beginNum = (pageNum - 1) * showNum;
		String sql = "select * from student limit ?,?";
		Integer[] param = { beginNum, showNum };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// 将rs记录添加到list中
	private void buildList(ResultSet rs, ArrayList<Student> list, int i) throws SQLException {
		Student stu = new Student();
		stu.setID(rs.getInt("SID"));
		stu.setName(rs.getString("Sname"));
		stu.setSex(rs.getString("Ssex"));
		stu.setGrade(rs.getString("Sgrade"));
		stu.setMajor(rs.getString("Smajor"));
		stu.setHometown(rs.getString("Shometown"));
		stu.setTel(rs.getString("Stel"));
		stu.setEmail(rs.getString("Semail"));
		list.add(stu);
	}

	// 将list中记录添加到二维数组中
	private void buildResult(String[][] result, ArrayList<Student> stus, int j) {
		Student stu = stus.get(j);
		result[j][0] = String.valueOf(stu.getID());
		result[j][1] = stu.getName();
		result[j][2] = stu.getSex();
		result[j][3] = stu.getGrade();
		result[j][4] = stu.getMajor();
		result[j][5] = stu.getHometown();
		result[j][6] = stu.getEmail();
		result[j][7] = stu.getTel();
	}

	// query by sno
	private int queryByID(int sno) throws SQLException {
		int result = 0;
		if (sno == 0) {
			return result;
		}
		String checkSql = "select * from student where sno=?";
		Object[] checkParam = { sno };
		rs = db.executeQuery(checkSql, checkParam);
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

	
	public String[][] queryAllScore(Student stu) {
		//根据this.id查询SC表，返回保存了所有课程CID、课程名称Cname、成绩信息Cscore的String[][];
	}
	
	public String[][] queryScore(Student stu, int CID) {
		//根据this.id和cid查询SC表，返回保存了指定课程的成绩信息的String[][];
	}
	
	public String[][] queryCourse(Student stu) {
		//根据this.id查询SC表，返回保存了所有课程名称Cname和Cid的String[][]
	}
	
	public String[] querySelfInfo(Student stu) {
		//根据this.id查询
	}
	
	public void updatePswd(String pswd) {
		//
	}
}
