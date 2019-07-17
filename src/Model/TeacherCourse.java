package Model;

public class TeacherCourse {
	int Tid;
	int Cid;
	String Cname;
	
	public void setTid(int tid) {
		this.Tid = tid;
	}
	
	public int getTid() {
		return Tid;
	}
	
	public void setCid(int cid) {
		this.Cid = cid;
	}
	
	public int getCid() {
		return Cid;
	}
	
	public void setCname(String cname) {
		this.Cname = cname;
	}
	
	public String getCname() {
		return Cname;
	}
}
