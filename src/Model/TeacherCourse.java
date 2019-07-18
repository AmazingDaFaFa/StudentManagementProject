package Model;



//TID int(8),CID int(8),Cname varchar(20),Ccapacity varchar(10),Cterm varchar(10)
public class TeacherCourse {
	int Tid;
	int Cid;
	String Cname;
	String Ccapacity;
	String Cterm;
	
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

	public void setCcapacity(String capacity) {
		this.Ccapacity = capacity;
	}
	
	public String getCcapacity() {
		return Ccapacity;
	}
	
	public void setCterm(String term) {
		this.Cterm = term;
	}
	
	public String getCterm() {
		return Cterm;
	}

}
