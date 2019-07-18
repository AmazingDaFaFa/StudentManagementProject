package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import LogWriting.MyLog;
import Model.AdminTeacher;
import Model.Student;
import Model.StudentCourse;
import Model.Teacher;
import Model.TeacherCourse;

public class AdminDAO extends BaseDAO {
	private static AdminDAO ad = null;
	private final int fieldNum = 9;
	private static MyLog myLog = new MyLog("AdminTeacherLog.log");

	public static synchronized AdminDAO getInstance() {
		if (ad == null) {
			ad = new AdminDAO();
		}
		return ad;
	}
	
	public boolean updatePswd(AdminTeacher admin, String pswd) {
		// 修改密码
		boolean result = false;
		try {
			String sql = "update User set userPassword=? where userID=?";
			Object[] param = { pswd, admin.getID() };
			int Rowcount = db.executeUpdate(sql, param);
			if (Rowcount == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addCourse(Teacher teacher, int cid, String Cname) {
		boolean result = false;
		try {
			String sql = "SELECT * FROM TeacherCourse WHERE TID=? and CID=?";
			Object[] check = { teacher.getID(), cid };
			rs = db.executeQuery(sql, check);
			if (rs != null) { // 如果该学生的成绩已经存在，则无法再添加成绩
				//add ERROR to .Log
				String logContent = "ADD RECORD ERROR to TABLE:TeacherCourse";
				myLog.addLog(MyLog.TYPE.ERROR, logContent);
				return result;
			}

			// add
			sql = "INSERT INTO TeacherCourse(TID,CID,Cname) VALUES (?,?,?)";
			Object[] param = { teacher.getID(), cid, Cname };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			destroy();
		}
		String logContent = "ADD RECORD: \'" + teacher.getID() + "-" + cid + "-" + Cname + "\' to TABLE:TeacherCourse";
		myLog.addLog(MyLog.TYPE.CREATE, logContent);
		return result;
	}

	public boolean updateCourse(Teacher teacher, int cid, String Cname) {
		// 根据cid，在TC中找到记录，修改其Tid和Cname
		boolean result = false;
		try {
			String sql = "SELECT * FROM TeacherCourse WHERE CID=?";
			Object[] check = { teacher.getID(), cid };
			rs = db.executeQuery(sql, check);
			if (rs == null) { // 如果该课程不存在，则无法修改
				//add ERROR to .Log
				String logContent = "UPDATE RECORD ERROR to TABLE:TeacherCourse";
				myLog.addLog(MyLog.TYPE.ERROR, logContent);
				return result;
			}

			// add
			sql = "UPDATE TeacherCourse SET TID=?, Cname=? WHERE CID=?)";
			Object[] param = { teacher.getID(), Cname, cid };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			destroy();
		}
		String logContent = "UPDATE RECORD: \'" + teacher.getID() + "-" + cid + "-" + Cname
				+ "\' to TABLE:TeacherCourse";
		myLog.addLog(MyLog.TYPE.UPDATE, logContent);
		return result;
	}

	public String[][] queryCourse(Teacher teacher) {
		String[][] result = null;
		
		int i = 0;
		ArrayList<TeacherCourse> TCs = new ArrayList<TeacherCourse>();
		String sql = "SELECT * FROM TeacherCourse WHERE Tid=?";
		Object[] param = { teacher.getID() };

		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, TCs, i);
				i++;
			}
			if (TCs.size() > 0) {
				result = new String[TCs.size()][fieldNum];
				for (int j = 0; j < TCs.size(); j++) {
					buildResult(result, TCs, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		// LogWrite
		String logContent = "Admin: QUERY COURSE of \'" + teacher.getID() + "\' on TABLE:TeacherCourse";
		myLog.addLog(MyLog.TYPE.QUERY, logContent);
		return result;
	}

	public boolean updateStudent(Student student) {
		boolean result = false;
		if (student == null) {
			//add ERROR to .Log
			String logContent = "UPDATE RECORD ERROR to TABLE:Student";
			myLog.addLog(MyLog.TYPE.ERROR, logContent);
			return result;
		}
		try {
			// check
			if (queryByID(student.getID()) == 0) {
				return result;
			}
			// update
			String sql = "update student set Ssex=?,Sgrade=?,Smajor=?,Shometown=?,Stel=?,Semail=? where Sname=? and Sid=?";
			Object[] param = { student.getSex(), student.getGrade(), student.getMajor(), student.getHometown(),
					student.getEmail(), student.getTel(), student.getName(), student.getID() };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		String logContent = "UPDATE RECORD: \'" + student.getID() + "-" + student.getName() + "-" + student.getSex()
				+ "-" + student.getGrade() + "-" + student.getMajor() + "-" + student.getHometown() + "-"
				+ student.getEmail() + "-" + student.getTel() + "\' on TABLE:Student";
		myLog.addLog(MyLog.TYPE.UPDATE, logContent);
		return result;
	}

	public String[][] queryScore(Student student) {
		// 根据this.id查询SC表，返回保存了所有课程CID、课程名称Cname、成绩信息Cscore的String[][];
		String[][] result = null;
		if (student == null) {
			return result; // if student don't exist,return null
		}
		ArrayList<StudentCourse> stus = new ArrayList<StudentCourse>();
		int i = 0;
		String sql = "select * from StudentCourse where SID=?";
		Object[] param = { student.getID() };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList_SC(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult_SC(result, stus, j);
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
	private void buildList(ResultSet rs, ArrayList<TeacherCourse> list, int i) throws SQLException {
		TeacherCourse tc = new TeacherCourse();
		tc.setTid(rs.getInt("TID"));
		tc.setCid(rs.getInt("CID"));
		list.add(tc);
	}

	// 将list中记录添加到二维数组中
	private void buildResult(String[][] result, ArrayList<TeacherCourse> TCs, int j) {
		TeacherCourse tc = TCs.get(j);
		result[j][0] = String.valueOf(tc.getTid());
		result[j][1] = String.valueOf(tc.getCid());
		result[j][2] = tc.getCname();
	}

	// 将rs记录添加到list中
	private void buildList_SC(ResultSet rs, ArrayList<StudentCourse> list, int i) throws SQLException {
		StudentCourse sc = new StudentCourse();
		sc.setSid(rs.getInt("SID"));
		sc.setCid(rs.getInt("CID"));
		sc.setCname(rs.getString("Cname"));
		sc.setCscore(rs.getString("Cscore"));
		list.add(sc);
	}

	// 将list中记录添加到二维数组中
	private void buildResult_SC(String[][] result, ArrayList<StudentCourse> SCs, int j) {
		StudentCourse sc = SCs.get(j);
		result[j][0] = String.valueOf(sc.getSid());
		result[j][1] = String.valueOf(sc.getCid());
		result[j][2] = sc.getCname();
	}

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

}
