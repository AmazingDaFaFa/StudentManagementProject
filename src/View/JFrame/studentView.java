package View.JFrame;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Student student=this.student;
			StudentDAO studentDAO=this.studentDAO;
			public void run() {
				try {
					studentView window = new studentView(student,studentDAO);
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
		
		//���滮��
		j1.setLayout(null);//ע��˴���null,�����϶��ָ���
		j3.setLayout(null);//ע��˴���null�������϶��ָ���
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
		JLabel label_1 = new JLabel("ѧ��");
		label_1.setBounds(40, 50, 72, 18);
		j1.add(label_1);
		frame.setContentPane(splitPane1);
		JLabel label_2 = new JLabel(String.valueOf(this.student.getID()));
		label_2.setBounds(40, 70, 72, 18);
		j1.add(label_2);
		frame.setContentPane(splitPane1);
		JLabel label_3 = new JLabel("����");
		label_3.setBounds(40, 120, 72, 18);
		j1.add(label_3);
		frame.setContentPane(splitPane1);
		JLabel label_4 = new JLabel(this.student.getName());
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
		JLabel label_5 = new JLabel("ѧ����Ϣ����");
		label_5.setBounds(277, 40, 100, 18);
		j3.add(label_5);
		JButton getStudentInfo=new JButton("��Ϣ��ѯ");
		getStudentInfo.setBounds(75, 80, 100, 30);
		j3.add(getStudentInfo);
		getStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStudent(j2);
			}
		});
		JButton getGradeInfo=new JButton("�ɼ���ѯ");
		getGradeInfo.setBounds(275, 80, 100, 30);
		j3.add(getGradeInfo);
		getGradeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGrade(j2);
			}
		});
		JButton contactTeacher=new JButton("��ϵ��ʦ");
		contactTeacher.setBounds(475, 80, 100, 30);
		j3.add(contactTeacher);
		
	}
	
	public void changeP() {           //�޸�����
		String pswd = JOptionPane.showInputDialog("�����������룺");
		boolean b=this.studentDAO.updatePswd(this.student,pswd);
		if(b==false) {
			JOptionPane.showMessageDialog(null, "�޸�����ʧ��");  
		}
		else {
			JOptionPane.showMessageDialog(null, "�޸�����ɹ�");  
		}
	}
	public void getStudent(JPanel j) {         //��ȡѧ��������Ϣ
		String str[]=new String[8];
		str=this.studentDAO.querySelfInfo(this.student)[0];
		String[] columnNames = {"ѧ��","����","�Ա�","�꼶","רҵ","����","�绰","����"};
		Vector<String[]> v=new Vector<String[]>();
		v.add(str);
		JTable table=makeTable(columnNames,v,j);
		j.add(table);
	}
	public void getGrade(JPanel j) {         //��ȡѧ���ɼ�
		int numOfCourse=this.studentDAO .queryAllScore(student).length;
		String[] columnNames = {"�γ̺�","�γ���","�ɼ�"};
		Vector<String[]> v=new Vector<String[]>();
		for(int i=0;i<numOfCourse;i++) {
			v.add(this.studentDAO .queryAllScore(student)[i]);
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
	public void setName() {
		String name=this.studentDAO.querySelfInfo(this.student)[0][1];
		this.student.setName(name);
	}
}
