package View.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.AdminDAO;
import DAO.TeacherDAO;
import Model.AdminTeacher;
import Model.Student;
import Model.Teacher;

public class JwTecherView {

	private JFrame frame;
	private AdminTeacher adminTeacher;
	private AdminDAO adminTeacherDAO;

	/**
	 * Launch the application.
	 */
	public static void main(AdminTeacher admin, AdminDAO ADAO) {
		EventQueue.invokeLater(new Runnable() {
			AdminDAO adminTeacherDAO = ADAO;
			AdminTeacher adminTeacher = ADAO.getTeacherByID(admin);

			public void run() {
				try {
					JwTecherView window = new JwTecherView(adminTeacher, adminTeacherDAO);
					window.frame.setVisible(true);
					window.frame.setTitle("学生信息管理系统");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JwTecherView(AdminTeacher teacher, AdminDAO adminTeacherDAO) {
		this.adminTeacher = teacher;
		this.adminTeacherDAO = adminTeacherDAO;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String id = "001";
		String name = "牛蛙克克";
		JPanel j1 = new JPanel();
		JPanel j2 = new JPanel();
		JPanel j3 = new JPanel();

		j1.setLayout(null);// 注意此处的null
		j3.setLayout(null);// 注意此处的null
		JSplitPane splitPane1 = new JSplitPane();// 创建一个分割容器类
		splitPane1.setOneTouchExpandable(true);// 让分割线显示出箭头
		splitPane1.setContinuousLayout(true);// 操作箭头，重绘图形
		splitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);// 设置分割线方向 纵向分布
		JSplitPane splitPane2 = new JSplitPane();// 创建一个分割容器类
		splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);// 设置分割线方向 纵向分布
		splitPane2.setLeftComponent(j2);// 设置左边的组件
		splitPane2.setLeftComponent(j3);// 设置左边的组件
		splitPane2.setRightComponent(j2);// 设置右边的组件
		splitPane1.setLeftComponent(j1);// 设置左边的组件
		splitPane1.setRightComponent(splitPane2);// 设置左边的组件
		splitPane1.setDividerSize(1);// 设置分割线的大小
		splitPane2.setDividerSize(1);// 设置分割线的大小
		splitPane1.setDividerLocation(150);// 设置分割线位于中央
		splitPane2.setDividerLocation(150);// 设置分割线位于中央
		splitPane1.setEnabled(false);
		splitPane2.setEnabled(false);

		// 左侧信息栏
		frame.setContentPane(splitPane1);
		JLabel label_1 = new JLabel("工号");
		label_1.setBounds(40, 50, 72, 18);
		j1.add(label_1);
		frame.setContentPane(splitPane1);
		JLabel label_2 = new JLabel(String.valueOf(adminTeacher.getID()));
		label_2.setBounds(40, 70, 72, 18);
		j1.add(label_2);
		frame.setContentPane(splitPane1);
		JLabel label_3 = new JLabel("姓名");
		label_3.setBounds(40, 120, 72, 18);
		j1.add(label_3);
		frame.setContentPane(splitPane1);
		JLabel label_4 = new JLabel(adminTeacher.getName());
		label_4.setBounds(40, 140, 72, 18);
		j1.add(label_4);
		JButton changePassword = new JButton("修改密码");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeP();
			}
		});
		changePassword.setBounds(25, 200, 100, 30);
		j1.add(changePassword);

		// 右上选择栏
		JLabel label_5 = new JLabel("课程管理");
		label_5.setBounds(50, 40, 100, 18);
		j3.add(label_5);
		JLabel label_6 = new JLabel("学生管理");
		label_6.setBounds(50, 100, 100, 18);
		j3.add(label_6);

		// 课程管理
		JButton addCourse = new JButton("添加课程");
		addCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourseInfo();
			}
		});
		addCourse.setBounds(125, 35, 100, 25);
		j3.add(addCourse);
		JButton changeCourse = new JButton("修改课程");
		changeCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCourseInfo();
			}
		});
		changeCourse.setBounds(250, 35, 100, 25);
		j3.add(changeCourse);
		JButton getCourse = new JButton("查询课程");
		getCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCourseInfo(j2);
			}
		});
		getCourse.setBounds(375, 35, 100, 25);
		j3.add(getCourse);

		// 学生管理
		JButton changeStudentInfo = new JButton("修改信息");
		changeStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStudent();
			}
		});
		changeStudentInfo.setBounds(125, 95, 100, 25);
		j3.add(changeStudentInfo);
		JButton getStudentGrade = new JButton("成绩查询");
		getStudentGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStudentGradeInfo(j2);
			}
		});
		getStudentGrade.setBounds(250, 95, 100, 25);
		j3.add(getStudentGrade);

		JButton logOut = new JButton("返回登录");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOutTo();
			}
		});
		logOut.setBounds(25, 300, 100, 30);
		j1.add(logOut);

	}

	public void changeP() { // 修改密码
		String pswd = JOptionPane.showInputDialog("请输入新密码：");
		boolean b = this.adminTeacherDAO.updatePswd(this.adminTeacher, pswd);
		if (b == false) {
			JOptionPane.showMessageDialog(null, "修改密码失败");
		} else {
			JOptionPane.showMessageDialog(null, "修改密码成功");
		}
	}

	public void addCourseInfo() {
		Teacher teacher = new Teacher();
		int sid = Integer.parseInt(JOptionPane.showInputDialog("请输入老师的工号："));
		teacher.setID(sid);
		int cid = Integer.parseInt(JOptionPane.showInputDialog("请输入要添加的课程号："));
		String cname = JOptionPane.showInputDialog("请输入要添加的课程名：");
		boolean b = this.adminTeacherDAO.addCourse(teacher, cid, cname);
		if (b == false) {
			JOptionPane.showMessageDialog(null, "添加失败");
		} else {
			JOptionPane.showMessageDialog(null, "添加成功");
		}
	}

	public void changeCourseInfo() {
		Teacher teacher = new Teacher();
		int sid = Integer.parseInt(JOptionPane.showInputDialog("请输入老师的工号："));
		teacher.setID(sid);
		int cid = Integer.parseInt(JOptionPane.showInputDialog("请输入要修改的课程号："));
		String cname = JOptionPane.showInputDialog("请输入要修改的课程名：");
		boolean b = this.adminTeacherDAO.updateCourse(teacher, cid, cname);
		if (b == false) {
			JOptionPane.showMessageDialog(null, "修改失败");
		} else {
			JOptionPane.showMessageDialog(null, "修改成功");
		}
	}

	public void getCourseInfo(JPanel j) {
		Teacher teacher = new Teacher();
		int sid = Integer.parseInt(JOptionPane.showInputDialog("请输入老师的工号："));
		teacher.setID(sid);
		this.adminTeacherDAO.queryCourse(teacher);
		int numOfCourse = this.adminTeacherDAO.queryCourse(teacher).length;
		String[] columnNames = { "教师工号", "课程号", "课程名" };
		Vector<String[]> v = new Vector<String[]>();
		for (int i = 0; i < numOfCourse; i++) {
			v.add(this.adminTeacherDAO.queryCourse(teacher)[i]);
		}
		JTable table = makeTable(columnNames, v, j);
		j.add(table);
	}

	public void changeStudent() {
		int sid = Integer.parseInt(JOptionPane.showInputDialog("请输入学号："));
		Student stu=new Student();
		stu.setID(sid);
		UpdateStudentInfo.main(stu,adminTeacherDAO);
	}

	public void getStudentGradeInfo(JPanel j) {
		int cid = Integer.parseInt(JOptionPane.showInputDialog("请输入要查询的课程号："));
		int numOfCourse = this.adminTeacherDAO.queryScore(cid).length;
		String[] columnNames = { "学号", "课程号", "课程名", "成绩" };
		Vector<String[]> v = new Vector<String[]>();
		for (int i = 0; i < numOfCourse; i++) {
			v.add(this.adminTeacherDAO.queryScore(cid)[i]);
		}
		JTable table = makeTable(columnNames, v, j);
		j.add(table);
	}

	public JTable makeTable(String[] columnNames, Vector<String[]> v, JPanel j) { // 制作表格
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		;
		JTable table = new JTable();
//	    tableModel = new DefaultTableModel(); 
		table = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}// 表格不允许被编辑
		};
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER); // 居中
		table.setDefaultRenderer(Object.class, r);
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));// 将表格加入到滚动条组件中
		JScrollPane scrollPane = new JScrollPane(table);
		for (String[] strings : v) {
			tableModel.addRow(strings);
		}
		j.add(scrollPane, BorderLayout.CENTER);// 再将滚动条组件添加到中间容器中
		j.updateUI();
		return table;
	}

	public void logOutTo() {
		frame.dispose();
		Login.main();
	}
}
