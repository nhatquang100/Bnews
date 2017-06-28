package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
	private static Connection conn;
	private static String url;
	private static String user;
	private static String password;
	private static ReadPropertiesLibrary propertiesLibrary;
	private static Properties properties=null;
	public static Connection getConnectMySql(){
		propertiesLibrary=new ReadPropertiesLibrary();
		properties=propertiesLibrary.readProperties();
		url=properties.getProperty("url");
		user=properties.getProperty("username");
		password=properties.getProperty("password");
		//nạp driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//tạo chuỗi conn
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Không thể nạp driver");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		DatabaseConnection lDb = new DatabaseConnection();
		System.out.println(lDb.getConnectMySql());
	}
}
