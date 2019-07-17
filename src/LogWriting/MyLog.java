package LogWriting;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyLog implements Loggable{
	
	String path;
	
	public MyLog(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}
	
	@Override
	public void addLog(TYPE type, String logContent) {
		// TODO Auto-generated method stub
		File logFile = new File(path);
		if(!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Log File created failed.");
			}
			logFile = new File(path);
		}
		

		String logTime = getCurrentYYYYMMDDHHMMSS();
		
		String logMessage = "****************\r\n" 
								+ logTime + "\r\n" 
				 + "# " + type.toString() + "\r\n"
							 + logContent + "\r\n"
						  + "****************\r\n";
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(logFile, true));
			bw.write(logMessage);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String readLog() {
		// TODO Auto-generated method stub
		String content = "";
		
		File logFile = new File(path);
		if(!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Log File created failed.");
			}
			logFile = new File(path);
		}
		
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(logFile)); // ����һ������������reader  
            BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
            String str = "StartReading";
            while ((str = br.readLine()) != null) {
            	content += str + '\n';
            }
            content.replace('\r', ' ');
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("can not open Log file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}

	
	public static String getCurrentYYYYMMDDHHMMSS() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date currTime = new Date();
		String thisTime = new String(formatter.format(currTime));
		return thisTime;
	}
}
