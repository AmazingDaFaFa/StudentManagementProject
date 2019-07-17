package Model;

// 教师类，教师Model，负责教师对象对数据库的增删改查操作，调用数据库操作类
public class Teacher {
	int id;
	String name;
	String password;

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
}
