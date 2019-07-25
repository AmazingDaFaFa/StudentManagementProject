package Model;

public class StudentCourse {
	int Sid;
	int Cid;
	String Cscore;
	String Cname;
	
//	sql[2]="create table StudentCourse(SID int(8),CID int(8),Cname varchar(10),Cscore varchar(4))";


	public StudentCourse() {	}

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
	
	@Override
	public String toString() {
		return this.getSid() + '\t' + this.getCid() + '\t' + this.getCname() + '\t' + this.getCscore();
	}
}