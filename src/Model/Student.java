package Model;

// 学生Model
public class Student{
	int id;
	String name;
	String password;
	String sex;
	String grade;
	String major;
	String hometown;
	String tel;
	String email;
	
	public Student() {	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public String getMajor() {
		return major;
	}
	
	public String getHometown() {
		return hometown;
	}
	
	public String getTel() {
		return tel;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return this.getID() + '\t' + this.getName() + '\t' + this.getSex() + '\t' + this.getGrade() + '\t' + this.getMajor() + '\t' + 
				this.getHometown() + '\t' + this.getTel() + '\t' + this.getEmail();
	}
	
}
