package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.conf.StringProperty;

import LogWriting.MyLog;
import Model.Student;
import Model.StudentCourse;
import Model.Teacher;
import Model.TeacherCourse;

public class TeacherDAO extends BaseDAO{
	private final int fieldNum = 9;
	private final int showNum = 15;
	
	private static TeacherDAO td = null;
	private static MyLog myLog = new MyLog("teacherLog.log");
	
	public static synchronized TeacherDAO getInstance() {
		if (td == null) {
			td = new TeacherDAO();
		}
		return td;
	}
	
	public boolean updatePswd(Teacher teacher, String pswd) {
		// 修改密码
		boolean result = false;
		try {
			String sql = "update User set userPassword=? where userID=?";
			Object[] param = { pswd, teacher.getID() };
			int Rowcount = db.executeUpdate(sql, param);
			if (Rowcount == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addCourse(Teacher teacher, int cid, String cname) {
		boolean result = false;
		try {
			String sql = "SELECT * FROM TeacherCourse WHERE CID=?";
			Object[] check = { cid };
			rs = db.executeQuery(sql, check);
			if(rs != null) {	//如果该课程已经存在，则无法再添加该课程
				//add to .log
				String logContent = "Teacher: \'" + teacher.getName() + "\' ADD RECORD ERROR: \'" + teacher.getName() + 
						"-" + cid + "-" + cname + "\' to TABLE:TeacherCourse";
				myLog.addLog(MyLog.TYPE.ERROR, logContent);
				return result;
			}
			
			// add
			sql = "INSERT INTO TeacherCourse(TID,CID,Cname,Ccapacity,Cterm) VALUES (?,?,?,30,2019)";
			Object[] param = { teacher.getID(), cid, cname };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		
		String logContent = "Teacher: \'" + teacher.getName() + "\' ADD RECORD: \'" + teacher.getName() + 
				"-" + cid + "-" + cname + "\' to TABLE:TeacherCourse";
		myLog.addLog(MyLog.TYPE.CREATE, logContent);
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
		//LogWrite
		String logContent = "Teacher: \'" + teacher.getName() + "\' QUERY COURSE on TABLE:TeacherCourse";
		myLog.addLog(MyLog.TYPE.QUERY, logContent);
		return result;
	}
	
	public boolean addGrade(Teacher teacher, int cid, Student student, String score, String cname) {
		boolean result = false;
		try {
			String sql = "SELECT Cname FROM StudentCourse WHERE CID=? and SID=?";
			Object[] checkScore = { cid, student.getID() };
			rs = db.executeQuery(sql, checkScore);
			if(rs != null) {	//如果该学生的成绩已经存在，则无法再添加成绩
				//add ERROR to .Log
				String logContent = "Teacher: \'" + teacher.getName() + "\' ADD RECORD ERROR to TABLE:StudentCourse";
				myLog.addLog(MyLog.TYPE.ERROR, logContent);
				return result;
			}
			
			sql = "SELECT Cname FROM TeacherCourse WHERE CID=? and TID=?";
			Object[] checkCourse = { cid,teacher.getID() };
			rs = db.executeQuery(sql, checkCourse);
			if(rs == null) {
				//add ERROR to .Log
				String logContent = "Teacher: \'" + teacher.getName() + "\' ADD RECORD ERROR to TABLE:StudentCourse";
				myLog.addLog(MyLog.TYPE.ERROR, logContent);
				return result;
			}
			
			// add
			sql = "INSERT INTO StudentCourse(SID,CID,Cname,Cscore) VALUES (?,?,?,?)";
			Object[] param = { student.getID(), cid, rs.getString("Cname"), score };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch(Exception e){
			
		} finally {
			destroy();
		}
		
		String logContent = "Teacher: \'" + teacher.getName() + "\' ADD RECORD: \'" + student.getID() + 
				"-" + cid + "-" + cname + "-" + score + "\' to TABLE:StudentCourse";
		myLog.addLog(MyLog.TYPE.CREATE, logContent);	
		return result;
	}
	
	public boolean updateGrade(Teacher teacher, int cid, Student student, String cname, String score) {
		boolean result = false;
		try {
			String sql = "SELECT * FROM StudentCourse WHERE CID=? and SID=?";
			Object[] check = { cid, student.getID() };
			rs = db.executeQuery(sql, check);
			if(rs == null) {	//如果该学生的成绩不存在，则无法修改成绩
				//add ERROR to .Log
				String logContent = "Teacher: \'" + teacher.getName() + "\' UPDATE RECORD ERROR to TABLE:TeacherCourse";
				myLog.addLog(MyLog.TYPE.ERROR, logContent);
				return result;
			}
			
			// add
			sql = "UPDATE StudentCourse SET Cscore=? WHERE SID=? AND CID=?)";
			Object[] param = { score, student.getID(), cid };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		
		String logContent = "Teacher: \'" + teacher.getName() + "\' UPDATE RECORD: \'" + student.getID() + 
				"-" + cid + "-" + cname + "-" + score + "\' on TABLE:StudentCourse";
		myLog.addLog(MyLog.TYPE.UPDATE, logContent);
		return result;
	}
	
	public boolean deleteGrade(Teacher teacher, int cid, Student student) {
		boolean result = false;
		try {
			String sql = "SELECT * FROM StudentCourse WHERE CID=? and SID=?";
			Object[] check = { cid, student.getID() };
			rs = db.executeQuery(sql, check);
			if(rs == null) {	//如果该学生的成绩不存在，则无法删除
				return result;
			}
			
			// add
			sql = "UPDATE StudentCourse SET Cscore=0 WHERE SID=? AND CID=?)";
			Object[] param = { student.getID(), cid };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		
		String logContent = "Teacher: \'" + teacher.getName() + "\' DELETE SCORE on RECORD: \'" + student.getID() + 
				"-" + cid + "\' on TABLE:StudentCourse";
		myLog.addLog(MyLog.TYPE.DELETE, logContent);
		
		return result;
	}
	
	public String[][] queryGrade(Teacher teacher, int cid) {
		String[][] result = null;
		
		int i = 0;
		ArrayList<StudentCourse> TCs = new ArrayList<StudentCourse>();
		String sql = "SELECT * FROM TeacherCourse WHERE Tid=?";
		Object[] param = { teacher.getID() };
			
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList_SC(rs, TCs, i);
				i++;
			}
			if (TCs.size() > 0) {
				result = new String[TCs.size()][fieldNum];
				for (int j = 0; j < TCs.size(); j++) {
					buildResult_SC(result, TCs, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		//LogWrite
		String logContent = "Teacher: \'" + teacher.getName() + "\' QUERY SCGrade on TABLE:StudentCourse";
		myLog.addLog(MyLog.TYPE.QUERY, logContent);
		
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
	
}
