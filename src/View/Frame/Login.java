package View.Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.AdminDAO;
import DAO.BaseDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.AdminTeacher;
import Model.Student;
import Model.Teacher;
import run.MainApp;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {
	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private BaseDAO bDAO;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					window.frame.setTitle("请登录");
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
		bDAO = new BaseDAO() {
		};
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
				String pswd = new String(passwordField.getPassword());
				int identity = bDAO.queryForLogin(Integer.parseInt(textField_1.getText()), pswd);
				switch(identity) {
				case 0:
					Student student=new Student();
					student.setID(Integer.parseInt(textField_1.getText()));
					frame.dispose();
					studentView.main(student,new StudentDAO());
					break;
				case 1:
					Teacher teacher=new Teacher();
					teacher.setID(Integer.parseInt(textField_1.getText()));
					frame.dispose();
					normalTeacherView.main(teacher,new TeacherDAO());
					break;
				case 2:
					AdminTeacher adminTeacher=new AdminTeacher();
					adminTeacher.setID(Integer.parseInt(textField_1.getText()));
					frame.dispose();
					JwTecherView.main(adminTeacher,new AdminDAO());
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
					break;
				}
			}
		});
		button.setBounds(151, 213, 113, 27);
		frame.getContentPane().add(button);
	}
}
