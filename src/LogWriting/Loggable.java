package LogWriting;

public interface Loggable {
	
	// Log类型
	enum TYPE{
		INFORMATION, WARNING, ERROR, CREATE, UPDATE, DELETE, QUERY
	};
	
	
	void addLog(Loggable.TYPE type, String logContent);
	
	
	String readLog();

}
