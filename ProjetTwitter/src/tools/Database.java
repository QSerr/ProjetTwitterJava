package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Database {

	private DataSource dataSource;
	public static Database database;

	public Database(String jndiname) throws SQLException{
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			//Handle error that it's not configured in JNDI
			throw new SQLException(jndiname + " is missing in JNDI! : "+e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	/**
	 * return an sql connection to the castanet_serreau database
	 * mode 0 for Quentin's Laptop
	 * mode 1 for PPTI's computers
	 * @param mode
	 * @return an SQL connection
	 * @throws SQLException
	 */
	public static Connection getMySQLConnection(int mode) throws SQLException {
		
		if(mode == 0) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
		}
		if(mode == 1) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
		}
		return DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
	}
}


