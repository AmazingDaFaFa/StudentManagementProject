package View.cmd;

import Model.Student;
import Model.StudentCourse;
import Model.Teacher;
import Model.TeacherCourse;

public interface View {
	/**
	 * 
	 * @author 18077
	 */
	
	//主菜单
	public int MainMenu(int type);
	
	//登陆页面
	public String[] Login();
	
	//读取信息
	public String[] InputMessage();
	
	//学生信息
	public void showStuInfo(Student student);
	
	//学生成绩表
	public void showStuCourse(String[][] scs);
	
	//
	public void showChangePswd();
	
	
	
	
	public TeacherCourse AddCourse();
	
	public void showQueryTCourse(Teacher teacher);
	
	public StudentCourse AddGradeView();
	
	public StudentCourse UpdateGradeView();
	
	public StudentCourse DeleteGradeView();
	
	public StudentCourse TQueryGradeView();
	
	
	
	public TeacherCourse UpdateCourseView();
	
	public Student UpdateStudentView();
	
	public Student SQueryGradeView();
	
}
