package Model;

// 教师类，教师Model，负责教师对象对数据库的增删改查操作，调用数据库操作类
public class Teacher {
	int id;
	String name;
	String password;
	String sex;
	
	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	
	@Override
	public String toString() {
		return this.getID() + '\t' + this.getName() + '\t' + this.getSex();
	}
}
