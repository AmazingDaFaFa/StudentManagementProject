package Model;

//教务处老师类，教务处Model
public class AdminTeacher{
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