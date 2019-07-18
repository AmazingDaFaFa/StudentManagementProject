package View.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.Student;
import Model.Teacher;

public class normalTeacherView {

	private JFrame frame;
	private Teacher teacher;
	private TeacherDAO teacherDAO;

	/**
	 * Launch the application.
	 */
	public static void main(Teacher tch, TeacherDAO TDAO) {
		EventQueue.invokeLater(new Runnable() {
			TeacherDAO teacherDAO = TDAO;
			Teacher teacher = TDAO.getTeacherByID(tch);

			public void run() {
				try {
					normalTeacherView window = new normalTeacherView(teacher, teacherDAO);
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
	public normalTeacherView(Teacher teacher, TeacherDAO teacherDAO) {
		this.teacher = teacher;
		this.teacherDAO = teacherDAO;
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
		// j1.setBackground(Color.red);
		// j2.setBackground(Color.green);
		// j3.setBackground(Color.blue);
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
		JLabel label_2 = new JLabel(String.valueOf(teacher.getID()));
		label_2.setBounds(40, 70, 72, 18);
		j1.add(label_2);
		frame.setContentPane(splitPane1);
		JLabel label_3 = new JLabel("姓名");
		label_3.setBounds(40, 120, 72, 18);
		j1.add(label_3);
		frame.setContentPane(splitPane1);
		JLabel label_4 = new JLabel(teacher.getName());
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
		JLabel label_5 = new JLabel("课程查询");
		label_5.setBounds(50, 40, 100, 18);
		j3.add(label_5);
		JLabel label_6 = new JLabel("成绩管理");
		label_6.setBounds(50, 100, 100, 18);
		j3.add(label_6);

		// 课程查询
		JButton getCourseInfo = new JButton("课程查询");
		getCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCourse(j2);
			}
		});
		getCourseInfo.setBounds(125, 35, 100, 25);
		j3.add(getCourseInfo);

		// 成绩管理
		int coursenum = this.teacherDAO.queryCourse(this.teacher).length;
		String[] course = new String[coursenum];
		for (int i = 0; i < coursenum; i++) {
			course[i] = this.teacherDAO.queryCourse(this.teacher)[i][1];
		}
		JComboBox selectCourse = new JComboBox(); // 选择课程
		for (int i = 0; i < coursenum; i++) {
			selectCourse.addItem(course[i]);
		}
		int index = selectCourse.getSelectedIndex();
		int cid = Integer.parseInt(this.teacherDAO.queryCourse(this.teacher)[index][1]);

		selectCourse.setBounds(125, 95, 100, 25);
		j3.add(selectCourse);
		JButton addGrade = new JButton("添加");
		addGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = selectCourse.getSelectedIndex();
				int cid = Integer.parseInt(teacherDAO.queryCourse(teacher)[index][1]);
				addGradeInfo(j2, cid, course[index]);
			}
		});
		addGrade.setBounds(250, 95, 60, 25);
		j3.add(addGrade);
		JButton changeGrade = new JButton("修改");
		changeGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = selectCourse.getSelectedIndex();
				int cid = Integer.parseInt(teacherDAO.queryCourse(teacher)[index][1]);
				changeGradeInfo(j2, cid, course[index]);
			}
		});
		changeGrade.setBounds(335, 95, 60, 25);
		j3.add(changeGrade);
		JButton deleteGrade = new JButton("删除");
		deleteGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = selectCourse.getSelectedIndex();
				int cid = Integer.parseInt(teacherDAO.queryCourse(teacher)[index][1]);
				deleteGradeInfo(j2, cid);
			}
		});
		deleteGrade.setBounds(420, 95, 60, 25);
		j3.add(deleteGrade);
		JButton getGrade = new JButton("查询");
		getGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = selectCourse.getSelectedIndex();
				int cid = Integer.parseInt(teacherDAO.queryCourse(teacher)[index][1]);
				getGradeInfo(j2, cid);
			}
		});
		getGrade.setBounds(505, 95, 60, 25);
		j3.add(getGrade);

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

		if (pswd == null) {
			JOptionPane.showMessageDialog(null, "修改密码失败");
			return;
		}

		boolean b = this.teacherDAO.updatePswd(this.teacher, pswd);
		if (b == false) {
			JOptionPane.showMessageDialog(null, "修改密码失败");
		} else {
			JOptionPane.showMessageDialog(null, "修改密码成功");
		}
	}

	public void getCourse(JPanel j) { // 查询课程
		int numOfCourse = this.teacherDAO.queryCourse(teacher).length;
		String[] columnNames = { "课程号", "课程名" };
		Vector<String[]> v = new Vector<String[]>();
		for (int i = 0; i < numOfCourse; i++) {
			v.add(this.teacherDAO.queryCourse(teacher)[i]);
		}
		JTable table = makeTable(columnNames, v, j);
		j.add(table);
	}

	public void addGradeInfo(JPanel j, int cid, String cname) { // 添加
		String sid = JOptionPane.showInputDialog("请输入学生的学号：");
		if (sid == null) {
			return;
		}
		Student student = new Student();
		student.setID(Integer.parseInt(sid));
		String score = JOptionPane.showInputDialog("请输入学生的成绩：：");
		if (score == null) {
			return;
		}
		this.teacherDAO.addGrade(teacher, cid, student, score, cname);
	}

	public void changeGradeInfo(JPanel j, int cid, String cname) { // 修改
		String sid = JOptionPane.showInputDialog("请输入学生的学号：");
		if (sid == null) {
			return;
		}
		Student student = new Student();
		student.setID(Integer.parseInt(sid));
		String score = JOptionPane.showInputDialog("请输入学生的成绩：：");
		if (score == null) {
			return;
		}
		this.teacherDAO.updateGrade(teacher, cid, student, score, cname);
	}

	public void deleteGradeInfo(JPanel j, int cid) { // 删除
		String sid = JOptionPane.showInputDialog("请输入学生的学号：");
		if (sid == null) {
			return;
		}
		Student student = new Student();
		student.setID(Integer.parseInt(sid));
		this.teacherDAO.deleteGrade(teacher, cid, student);
	}

	public void getGradeInfo(JPanel j, int cid) { // 添加
		int numOfCourse = this.teacherDAO.queryGrade(teacher, cid).length;
		String[] columnNames = { "学号", "课程号", "课程名", "成绩" };
		Vector<String[]> v = new Vector<String[]>();
		for (int i = 0; i < numOfCourse; i++) {
			v.add(this.teacherDAO.queryGrade(teacher, cid)[i]);
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
