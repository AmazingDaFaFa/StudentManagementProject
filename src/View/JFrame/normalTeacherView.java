package View.JFrame;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Teacher teacher=this.teacher;
			TeacherDAO teacherDAO=this.teacherDAO;
			public void run() {
				try {
					normalTeacherView window = new normalTeacherView(teacher,teacherDAO);
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
	public normalTeacherView(Teacher teacher,TeacherDAO teacherDAO) {
		this.teacher=teacher;
		this.teacherDAO=teacherDAO;
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
		//j1.setBackground(Color.red);
		//j2.setBackground(Color.green);
		//j3.setBackground(Color.blue);
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
		JLabel label_5 = new JLabel("�γ̲�ѯ");
		label_5.setBounds(50, 40, 100, 18);
		j3.add(label_5);
		JLabel label_6 = new JLabel("�ɼ�����");
		label_6.setBounds(50, 100, 100, 18);
		j3.add(label_6);  
		
		//�γ̲�ѯ
		JButton getCourseInfo=new JButton("�γ̲�ѯ");
		getCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCourse(j2);
			}
		});
		getCourseInfo.setBounds(125, 35, 100, 25);
		j3.add(getCourseInfo);
		
		//�ɼ�����
		int coursenum=this.teacherDAO.queryCourse(this.teacher).length;
		String []course=new String[coursenum];
		for(int i=0;i<coursenum;i++) {
			course[i]=this.teacherDAO.queryCourse(this.teacher)[i][1];
		}
		JComboBox selectCourse=new JComboBox();    //ѡ��γ�
		for(int i=0;i<coursenum;i++) {
			selectCourse.addItem(course[i]);
		}
		int index=selectCourse.getSelectedIndex();
		int cid=Integer.parseInt(this.teacherDAO.queryCourse(this.teacher)[index][0]);
		selectCourse.setBounds(125, 95, 100, 25);
		j3.add(selectCourse);
		JButton addGrade=new JButton("���");
		addGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGradeInfo(j2,cid,course[index]);
			}
		});
		addGrade.setBounds(250, 95, 60, 25);
		j3.add(addGrade);
		JButton changeGrade=new JButton("�޸�");
		changeGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeGradeInfo(j2,cid,course[index]);
			}
		});
		changeGrade.setBounds(335, 95, 60, 25);
		j3.add(changeGrade);
		JButton deleteGrade=new JButton("ɾ��");
		deleteGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteGradeInfo(j2,cid);
			}
		});
		deleteGrade.setBounds(420, 95, 60, 25);
		j3.add(deleteGrade);
		JButton getGrade=new JButton("��ѯ");
		getGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGradeInfo(j2,cid);
			}
		});
		getGrade.setBounds(505, 95, 60, 25);
		j3.add(getGrade);
	}
	public void changeP() {           //�޸�����
		String pswd = JOptionPane.showInputDialog("�����������룺");
		boolean b=this.teacherDAO.updatePswd(this.teacher,pswd);
		if(b==false) {
			JOptionPane.showMessageDialog(null, "�޸�����ʧ��");  
		}
		else {
			JOptionPane.showMessageDialog(null, "�޸�����ɹ�");  
		}
	}
	public void getCourse(JPanel j) {    //��ѯ�γ�
		int numOfCourse=this.teacherDAO .queryCourse(teacher).length;
		String[] columnNames = {"�γ̺�","�γ���"};
		Vector<String[]> v=new Vector<String[]>();
		for(int i=0;i<numOfCourse;i++) {
			v.add(this.teacherDAO .queryCourse(teacher)[i]);
		}
		JTable table=makeTable(columnNames,v,j);
		j.add(table);
	}
	public void addGradeInfo(JPanel j,int cid,String cname) {   //���
		int sid = Integer.parseInt(JOptionPane.showInputDialog("������ѧ����ѧ�ţ�"));
		Student student=new Student();
		student.setID(sid);
		String score=JOptionPane.showInputDialog("������ѧ���ĳɼ�����");
		this.teacherDAO.addGrade(teacher, cid,student,score,cname);
	}
	public void changeGradeInfo(JPanel j,int cid,String cname) {   //�޸�
		int sid = Integer.parseInt(JOptionPane.showInputDialog("������ѧ����ѧ�ţ�"));
		Student student=new Student();
		student.setID(sid);
		String score=JOptionPane.showInputDialog("������ѧ���ĳɼ�����");
		this.teacherDAO.updateGrade(teacher, cid,student,score,cname);
	}
	public void deleteGradeInfo(JPanel j,int cid) {   //���
		int sid = Integer.parseInt(JOptionPane.showInputDialog("������ѧ����ѧ�ţ�"));
		Student student=new Student();
		student.setID(sid);
		this.teacherDAO.deleteGrade(teacher, cid,student);
	}
	public void getGradeInfo(JPanel j,int cid) {   //���
		int numOfCourse=this.teacherDAO .queryGrade(teacher,cid).length;
		String[] columnNames = {"ѧ��","�γ̺�","�γ���","�ɼ�"};
		Vector<String[]> v=new Vector<String[]>();
		for(int i=0;i<numOfCourse;i++) {
			v.add(this.teacherDAO .queryGrade(teacher,cid)[i]);
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
