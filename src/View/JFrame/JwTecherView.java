package View.JFrame;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			AdminTeacher adminTeacher=this.adminTeacher;
			AdminDAO adminTeacherDAO=this.adminTeacherDAO;
			public void run() {
				try {
					JwTecherView window = new JwTecherView(adminTeacher,adminTeacherDAO);
					window.frame.setVisible(true);
					window.frame.setTitle("ѧ����Ϣ����ϵͳ");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JwTecherView(AdminTeacher teacher,AdminDAO adminTeacherDAO) {
		this.adminTeacher=teacher;
		this.adminTeacherDAO=adminTeacherDAO;
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
		String id="001";
		String name="ţ�ܿ˿�";
		JPanel j1= new JPanel();
		JPanel j2= new JPanel();
		JPanel j3=new JPanel();
		
		j1.setLayout(null);//ע��˴���null
		j3.setLayout(null);//ע��˴���null
		JSplitPane splitPane1=new JSplitPane();//����һ���ָ�������
		splitPane1.setOneTouchExpandable(true);//�÷ָ�����ʾ����ͷ
		splitPane1.setContinuousLayout(true);//������ͷ���ػ�ͼ��
		splitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//���÷ָ��߷��� ����ֲ�
		JSplitPane splitPane2=new JSplitPane();//����һ���ָ�������
		splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);//���÷ָ��߷��� ����ֲ�
		splitPane2.setLeftComponent(j2);//������ߵ����
		splitPane2.setLeftComponent(j3);//������ߵ����
		splitPane2.setRightComponent(j2);//�����ұߵ����
		splitPane1.setLeftComponent(j1);//������ߵ����
		splitPane1.setRightComponent(splitPane2);//������ߵ����
		splitPane1.setDividerSize(1);//���÷ָ��ߵĴ�С
		splitPane2.setDividerSize(1);//���÷ָ��ߵĴ�С
		splitPane1.setDividerLocation(150);//���÷ָ���λ������
		splitPane2.setDividerLocation(150);//���÷ָ���λ������
		splitPane1.setEnabled(false);  
		splitPane2.setEnabled(false);  
		
		//�����Ϣ��
		frame.setContentPane(splitPane1);
		JLabel label_1 = new JLabel("����");
		label_1.setBounds(40, 50, 72, 18);
		j1.add(label_1);
		frame.setContentPane(splitPane1);
		JLabel label_2 = new JLabel(id);
		label_2.setBounds(40, 70, 72, 18);
		j1.add(label_2);
		frame.setContentPane(splitPane1);
		JLabel label_3 = new JLabel("����");
		label_3.setBounds(40, 120, 72, 18);
		j1.add(label_3);
		frame.setContentPane(splitPane1);
		JLabel label_4 = new JLabel(name);
		label_4.setBounds(40, 140, 72, 18);
		j1.add(label_4);
		JButton changePassword=new JButton("�޸�����");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeP();
			}
		});
		changePassword.setBounds(25, 200, 100, 30);
		j1.add(changePassword);
		
		//����ѡ����
		JLabel label_5 = new JLabel("�γ̹���");
		label_5.setBounds(50, 40, 100, 18);
		j3.add(label_5);
		JLabel label_6 = new JLabel("ѧ������");
		label_6.setBounds(50, 100, 100, 18);
		j3.add(label_6);  
		
		//�γ̹���
		JButton addCourse=new JButton("��ӿγ�");
		addCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourseInfo();
			}
		});
		addCourse.setBounds(125, 35, 100, 25);
		j3.add(addCourse);
		JButton changeCourse=new JButton("�޸Ŀγ�");
		changeCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCourseInfo();
			}
		});
		changeCourse.setBounds(250, 35, 100, 25);
		j3.add(changeCourse);
		JButton getCourse=new JButton("��ѯ�γ�");
		getCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCourseInfo(j2);
			}
		});
		getCourse.setBounds(375, 35, 100, 25);
		j3.add(getCourse);
		
		//ѧ������
		JButton changeStudentInfo=new JButton("�޸���Ϣ");
		changeStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStudent();
			}
		});
		changeStudentInfo.setBounds(125, 95, 100, 25);
		j3.add(changeStudentInfo);
		JButton getStudentGrade=new JButton("�ɼ���ѯ");
		getStudentGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStudentGradeInfo(j2);
			}
		});
		getStudentGrade.setBounds(250, 95, 100, 25);
		j3.add(getStudentGrade);
	}
	public void changeP() {           //�޸�����
		String pswd = JOptionPane.showInputDialog("�����������룺");
		boolean b=this.adminTeacherDAO.updatePswd(this.adminTeacher,pswd);
		if(b==false) {
			JOptionPane.showMessageDialog(null, "�޸�����ʧ��");  
		}
		else {
			JOptionPane.showMessageDialog(null, "�޸�����ɹ�");  
		}
	}
	public void addCourseInfo() {
		Teacher teacher=new Teacher();
		int sid=Integer.parseInt(JOptionPane.showInputDialog("��������ʦ�Ĺ��ţ�"));
		teacher.setID(sid);
		int cid=Integer.parseInt(JOptionPane.showInputDialog("������Ҫ��ӵĿγ̺ţ�"));
		String cname=JOptionPane.showInputDialog("������Ҫ��ӵĿγ�����");
		boolean b=this.adminTeacherDAO.addCourse(teacher, cid,cname);
		if(b==false) {
			JOptionPane.showMessageDialog(null, "���ʧ��");  
		}
		else {
			JOptionPane.showMessageDialog(null, "��ӳɹ�");  
		}
	}
	public void changeCourseInfo() {
		Teacher teacher=new Teacher();
		int sid=Integer.parseInt(JOptionPane.showInputDialog("��������ʦ�Ĺ��ţ�"));
		teacher.setID(sid);
		int cid=Integer.parseInt(JOptionPane.showInputDialog("������Ҫ�޸ĵĿγ̺ţ�"));
		String cname=JOptionPane.showInputDialog("������Ҫ�޸ĵĿγ�����");
		boolean b=this.adminTeacherDAO.updateCourse(teacher, cid,cname);
		if(b==false) {
			JOptionPane.showMessageDialog(null, "�޸�ʧ��");  
		}
		else {
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�");  
		}
	}
	public void getCourseInfo(JPanel j) {
		Teacher teacher=new Teacher();
		int sid=Integer.parseInt(JOptionPane.showInputDialog("��������ʦ�Ĺ��ţ�"));
		teacher.setID(sid);
		this.adminTeacherDAO.queryCourse(teacher);
		int numOfCourse=this.adminTeacherDAO .queryCourse(teacher).length;
		String[] columnNames = {"��ʦ����","�γ̺�","�γ���"};
		Vector<String[]> v=new Vector<String[]>();
		for(int i=0;i<numOfCourse;i++) {
			v.add(this.adminTeacherDAO .queryCourse(teacher)[i]);
		}
		JTable table=makeTable(columnNames,v,j);
		j.add(table);
	}
	public void changeStudent() {
		UpdateStudentInfo u=new UpdateStudentInfo();
		String []studentInfo=u.getStudentInfo();
		Student stu=new Student();
		stu.setID(Integer.parseInt(studentInfo[0]));
		stu.setName(studentInfo[1]);
		stu.setSex(studentInfo[2]);
		stu.setGrade(studentInfo[3]);
		stu.setMajor(studentInfo[4]);
		stu.setHometown(studentInfo[5]);
		stu.setTel(studentInfo[6]);
		stu.setEmail(studentInfo[7]);
		this.adminTeacherDAO.updateStudent(stu);
	}
	public void getStudentGradeInfo(JPanel j) {
		int cid=Integer.parseInt(JOptionPane.showInputDialog("������Ҫ��ѯ�Ŀγ̺ţ�"));
		int numOfCourse=this.adminTeacherDAO .queryScore(cid).length;
		String[] columnNames = {"ѧ��","�γ̺�","�γ���","�ɼ�"};
		Vector<String[]> v=new Vector<String[]>();
		for(int i=0;i<numOfCourse;i++) {
			v.add(this.adminTeacherDAO .queryScore(cid)[i]);
		}
		JTable table=makeTable(columnNames,v,j);
		j.add(table);
	}
	public JTable makeTable(String[] columnNames,Vector<String[]> v,JPanel j) {    //�������
		DefaultTableModel tableModel= new DefaultTableModel(columnNames, 0);;
	    JTable table=new JTable();
	    tableModel = new DefaultTableModel(); 
	    table=new JTable(tableModel){
	        public boolean isCellEditable(int row, int column){
	            return false;
	        }//��������༭
	     }; 
	     DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
	     r.setHorizontalAlignment(JLabel.CENTER);  //����  
	     table.setDefaultRenderer(Object.class,r);
	     table.setPreferredScrollableViewportSize(new Dimension(550, 100));// �������뵽�����������
	     JScrollPane scrollPane = new JScrollPane(table);
		 tableModel.addRow(v);
		 j.add(scrollPane, BorderLayout.CENTER);// �ٽ������������ӵ��м�������
		 
		 return table;
	}
}
