package View.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.StudentDAO;
import Model.Student;

public class studentView {

	private JFrame frame;
	private Student student;
	private StudentDAO studentDAO;

	/**
	 * Launch the application.
	 */
	public static void main(Student stu, StudentDAO SDAO) {
		EventQueue.invokeLater(new Runnable() {
			Student student = stu;
			StudentDAO studentDAO = SDAO;
			public void run() {
				try {
					studentView window = new studentView(student,studentDAO);
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
	public studentView(Student stu,StudentDAO studentDAO) {
		this.studentDAO=studentDAO;
		this.student=stu;
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
		JPanel j1= new JPanel();
		JPanel j2= new JPanel();
		JPanel j3=new JPanel();
		
		//界面划分
		j1.setLayout(null);//注意此处的null,不可拖动分隔栏
		j3.setLayout(null);//注意此处的null，不可拖动分隔栏
		JSplitPane splitPane1=new JSplitPane();//创建一个分割容器类
		splitPane1.setOneTouchExpandable(true);//让分割线显示出箭头
		splitPane1.setContinuousLayout(true);//操作箭头，重绘图形
		splitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//设置分割线方向 纵向分布
		JSplitPane splitPane2=new JSplitPane();//创建一个分割容器类
		splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);//设置分割线方向 纵向分布
		splitPane2.setLeftComponent(j2);//设置左边的组件
		splitPane2.setLeftComponent(j3);//设置左边的组件
		splitPane2.setRightComponent(j2);//设置右边的组件
		splitPane1.setLeftComponent(j1);//设置左边的组件
		splitPane1.setRightComponent(splitPane2);//设置左边的组件
		splitPane1.setDividerSize(1);//设置分割线的大小
		splitPane2.setDividerSize(1);//设置分割线的大小
		splitPane1.setDividerLocation(150);//设置分割线位于中央
		splitPane2.setDividerLocation(150);//设置分割线位于中央
		splitPane1.setEnabled(false);  
		splitPane2.setEnabled(false);  
		
		frame.setContentPane(splitPane1);
		JLabel label_1 = new JLabel("学号");
		label_1.setBounds(40, 50, 72, 18);
		j1.add(label_1);
		frame.setContentPane(splitPane1);
		JLabel label_2 = new JLabel(String.valueOf(this.student.getID()));
		label_2.setBounds(40, 70, 72, 18);
		j1.add(label_2);
		frame.setContentPane(splitPane1);
		JLabel label_3 = new JLabel("姓名");
		label_3.setBounds(40, 120, 72, 18);
		j1.add(label_3);
		frame.setContentPane(splitPane1);
		JLabel label_4 = new JLabel(this.student.getName());
		label_4.setBounds(40, 140, 72, 18);
		j1.add(label_4);
		JButton changePassword=new JButton("修改密码");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeP();
			}
		});
		changePassword.setBounds(25, 200, 100, 30);
		j1.add(changePassword);
		
		//右上选择栏
		JLabel label_5 = new JLabel("学生信息管理");
		label_5.setBounds(277, 40, 100, 18);
		j3.add(label_5);
		JButton getStudentInfo=new JButton("信息查询");
		getStudentInfo.setBounds(75, 80, 100, 30);
		j3.add(getStudentInfo);
		getStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStudent(j2);
			}
		});
		JButton getGradeInfo=new JButton("成绩查询");
		getGradeInfo.setBounds(275, 80, 100, 30);
		j3.add(getGradeInfo);
		getGradeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGrade(j2);
			}
		});
		JButton contactTeacher=new JButton("联系老师");
		contactTeacher.setBounds(475, 80, 100, 30);
		j3.add(contactTeacher);
		
	}
	
	public void changeP() {           //修改密码
		String pswd = JOptionPane.showInputDialog("请输入新密码：");
		
		if(pswd == null) {
			JOptionPane.showMessageDialog(null, "修改密码失败");
			return;
		}
		
		boolean b = this.studentDAO.updatePswd(this.student, pswd);
		if(b == false) {
			JOptionPane.showMessageDialog(null, "修改密码失败");  
		}
		else {
			JOptionPane.showMessageDialog(null, "修改密码成功");  
		}
	}
	public void getStudent(JPanel j) {         //获取学生个人信息
		String str[]=new String[8];
		str=this.studentDAO.querySelfInfo(this.student)[0];
		String[] columnNames = {"学号","姓名","性别","年级","专业","籍贯","电话","邮箱"};
		Vector<String[]> v=new Vector<String[]>();
		v.add(str);
		JTable table=makeTable(columnNames,v,j);
		j.add(table);
	}
	public void getGrade(JPanel j) {         //获取学生成绩
		int numOfCourse=this.studentDAO .queryAllScore(student).length;
		String[] columnNames = {"课程号","课程名","成绩"};
		Vector<String[]> v=new Vector<String[]>();
		for(int i=0;i<numOfCourse;i++) {
			v.add(this.studentDAO .queryAllScore(student)[i]);
		}
		JTable table=makeTable(columnNames,v,j);
		j.add(table);
	}
	public JTable makeTable(String[] columnNames,Vector<String[]> v,JPanel j) {    //制作表格
		DefaultTableModel tableModel= new DefaultTableModel(columnNames, 0);;
	    JTable table=new JTable();
	    tableModel = new DefaultTableModel(); 
	    table=new JTable(tableModel){
	        public boolean isCellEditable(int row, int column){
	            return false;
	        }//表格不允许被编辑
	     }; 
	     DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
	     r.setHorizontalAlignment(JLabel.CENTER);  //居中  
	     table.setDefaultRenderer(Object.class,r);
	     table.setPreferredScrollableViewportSize(new Dimension(550, 100));// 将表格加入到滚动条组件中
	     JScrollPane scrollPane = new JScrollPane(table);
		 tableModel.addRow(v);
		 j.add(scrollPane, BorderLayout.CENTER);// 再将滚动条组件添加到中间容器中
		 return table;
	}
	public void setName() {
		String name=this.studentDAO.querySelfInfo(this.student)[0][1];
		this.student.setName(name);
	}
}
