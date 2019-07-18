package View.JFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.AdminDAO;
import DAO.BaseDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.AdminTeacher;
import Model.Student;
import Model.Teacher;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends BaseDAO{

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					window.frame.setTitle("��½");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7");
		label_1.setBounds(110, 58, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setBounds(110, 140, 72, 18);
		frame.getContentPane().add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(217, 137, 94, 24);
		frame.getContentPane().add(passwordField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(217, 55, 94, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u767B\u9646");
		//��½��ť���¼�
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pswd=new String(passwordField.getPassword());
				int identity=queryForLogin(Integer.parseInt(textField_1.getText()), pswd);
				switch(identity) {
				case 0:{
					Student student=new Student();
					student.setID(Integer.parseInt(textField_1.getText()));
					new studentView(student,new StudentDAO());break;
					}
				case 1:{
					Teacher teacher=new Teacher();
					teacher.setID(Integer.parseInt(textField_1.getText()));
					new normalTeacherView(teacher,new TeacherDAO());break;
				}
				case 2:{
					AdminTeacher adminTeacher=new AdminTeacher();
					adminTeacher.setID(Integer.parseInt(textField_1.getText()));
					new JwTecherView(adminTeacher,new AdminDAO());break;
				}
				}
			}
		});
		button.setBounds(151, 213, 113, 27);
		frame.getContentPane().add(button);
	}
}
