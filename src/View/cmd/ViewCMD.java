package View.cmd;

import java.util.Scanner;

import Model.Student;
import Model.StudentCourse;
import Model.Teacher;
import Model.TeacherCourse;

public class ViewCMD implements View{
	
	public Scanner Scanner = new Scanner(System.in);
	
	@Override
	public int MainMenu(int type) {
		// TODO Auto-generated method stub
		int cmd;
		
		switch (type) {
		case 0:
			System.out.println("=====主菜单=====");
			System.out.println("    1.个人信息");
			System.out.println("    2.个人成绩");
			System.out.println("    3.修改密码");
			cmd = Integer.parseInt(Scanner.nextLine());
			
			return cmd;
		case 1:
			System.out.println("=====主菜单=====");
			System.out.println("    4.设置课程");
			System.out.println("    5.查询课程");
			System.out.println("    6.添加成绩");
			System.out.println("    7.修改成绩");
			System.out.println("    8.删除成绩");
			System.out.println("    9.查询成绩");
			cmd = Integer.parseInt(Scanner.nextLine());
			
			return cmd;
		case 2:
			System.out.println("=====主菜单=====");
			System.out.println("    10.设置课程");
			System.out.println("    11.更新课程");
			System.out.println("    12.查询课程");
			System.out.println("    13.更新学生");
			System.out.println("    14.查询成绩");
			cmd = Integer.parseInt(Scanner.nextLine());
			
			return cmd;
		default:
			System.out.println("请先登录！");
			return 0;
		}
	}

	@Override
	public String[] Login() {
		// TODO Auto-generated method stub
		System.out.println("请输入用户名和密码，以空格区分：");
		String temp = Scanner.nextLine();
		String[] data = temp.split(" ");
		
		return data;
	}

	@Override
	public String[] InputMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showStuInfo(Student student) {
		// TODO Auto-generated method stub
		
		System.out.println(student);
	}

	@Override
	public void showStuCourse(String[][] scs) {
		// TODO Auto-generated method stub
		for (String[] strings : scs) {
			System.out.println("课程：" + strings);
		}
	}

	@Override
	public void showChangePswd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TeacherCourse AddCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showQueryTCourse(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StudentCourse AddGradeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentCourse UpdateGradeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentCourse DeleteGradeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentCourse TQueryGradeView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeacherCourse UpdateCourseView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student UpdateStudentView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student SQueryGradeView() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
