package Model;

public class StudentCourse {
	int Sid;
	int Cid;
	String Cscore;
	String Cname;

	public StudentCourse() {

	}

	public void setSid(int Sid) {
		this.Sid = Sid;
	}

	public void setCid(int Cid) {
		this.Cid = Cid;
	}

	public void setCscore(String Cscore) {
		this.Cscore = Cscore;
	}

	public void setCname(String Cname) {
		this.Cname = Cname;
	}

	public int getSid() {
		return Sid;
	}

	public int getCid() {
		return Cid;
	}

	public String getCscore() {
		return Cscore;
	}

	public String getCname() {
		return Cname;
	}
}