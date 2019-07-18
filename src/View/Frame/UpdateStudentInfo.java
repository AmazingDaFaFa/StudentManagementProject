package View.Frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class UpdateStudentInfo {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String id;
	private String name;
	private String sex;
	private String grade;
	private String major;
	private String hometown;
	private String tel;
	private String email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudentInfo window = new UpdateStudentInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateStudentInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 421, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u53F7");
		label.setBounds(77, 13, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u59D3\u540D");
		label_1.setBounds(77, 44, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(77, 75, 72, 18);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5E74\u7EA7");
		label_3.setBounds(77, 106, 72, 18);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u4E13\u4E1A");
		label_4.setBounds(77, 140, 72, 18);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u7C4D\u8D2F");
		label_5.setBounds(77, 171, 72, 18);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u7535\u8BDD");
		label_6.setBounds(77, 202, 72, 18);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u90AE\u7BB1");
		label_7.setBounds(77, 233, 72, 18);
		frame.getContentPane().add(label_7);
		
		textField = new JTextField();         //学号
		textField.setBounds(198, 10, 86, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		this.id=textField.getText();
		
		textField_1 = new JTextField();      //姓名
		textField_1.setBounds(198, 41, 86, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		this.name=textField_1.getText();
		
		JRadioButton radioButton = new JRadioButton("\u7537");      //性别
		radioButton.setBounds(193, 71, 43, 27);
		frame.getContentPane().add(radioButton);
		if(radioButton.isSelected()){
		     this.sex= radioButton.getText();
		} 
		
		JRadioButton radioButton_1 = new JRadioButton("\u5973");       
		radioButton_1.setBounds(242, 71, 50, 27);
		frame.getContentPane().add(radioButton_1);
		if(radioButton_1.isSelected()){
		     this.sex= radioButton.getText();
		}
		
		ButtonGroup gender=new ButtonGroup();
		gender.add(radioButton_1);
		gender.add(radioButton); 
		
		JComboBox comboBox = new JComboBox();            //年纪
		comboBox.setBounds(198, 103, 86, 24);
		frame.getContentPane().add(comboBox);
		for(int i=15;i<21;i++) {
			comboBox.addItem(String.valueOf(i));
		}
		int index=comboBox.getSelectedIndex();
		switch(index) {
		case 0:this.grade ="15";break;
		case 1:this.grade ="16";break;
		case 2:this.grade ="17";break;
		case 3:this.grade ="18";break;
		case 4:this.grade ="19";break;
		case 5:this.grade ="20";break;
		}
		
		textField_2 = new JTextField();            //专业
		textField_2.setBounds(198, 137, 86, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		this.major=textField_2.getText();
		
		textField_3 = new JTextField();           //籍贯
		textField_3.setBounds(198, 168, 86, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		this.hometown=textField_3.getText();
		
		textField_4 = new JTextField();           //电话
		textField_4.setBounds(198, 199, 86, 24);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		this.tel=textField_4.getText();
		
		textField_5 = new JTextField();          //邮箱
		textField_5.setBounds(198, 230, 86, 24);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		this.email=textField_5.getText();
		
		JButton button = new JButton("\u786E\u8BA4");     //确认
		button.setBounds(123, 265, 113, 27);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	public String[] getStudentInfo() {
		String studentInfo[]={this.id,this.name,this.sex,this.grade,this.major,this.hometown,this.tel,this.email};
		return studentInfo;
	}
}

