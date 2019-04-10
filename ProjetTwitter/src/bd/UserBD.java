package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import tools.DBStatic;
import tools.Database;

public class UserBD {

	public static boolean userExists(String login) {

		boolean rest = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection c = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String q = "Select user_id from User where (user_login='"+login+"')";
			java.sql.Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(q);
			if(rs.next()) {
				rest=true;
			}
			else {rest=false;}
			s.close();
			c.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rest;
	}

	public static boolean checkPasswordCorrect(String login, String password) {
		boolean resu=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection c = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String query = "SELECT user_id FROM User WHERE (user_login='"+login+"' AND user_password='"+password+"')";
			java.sql.Statement s = c.createStatement();
			//System.out.println("OUI");
			ResultSet rs = s.executeQuery(query);
			//System.out.println("OUI2");
			while(rs.next()) {
				resu = true;
			}
			s.close();
			c.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resu;
	}

	public static boolean createUser(String login, String password, String prenom, String nom, String sexe, int age) {
		boolean resu = false;
		try {
			if(!(userExists(login))) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
				String g = "INSERT INTO User (user_id,user_login,user_password,user_prenom,user_nom,user_sexe,user_age) "
						+ "VALUES (null,'"+login+"','"+password+"','"+prenom+"','"+password+"','"+sexe+"','"+age+"')";
				Statement st = com.createStatement();
				int rs = st.executeUpdate(g);
				st.close();
				com.close();
				if(rs==1)
					resu = true;
				else
					resu = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resu;
	}

	public static boolean deleteKey(String key) {
		boolean resu = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "DELETE FROM Sessions WHERE session_key='"+key+"'";
			Statement st = com.createStatement();
			int rs = st.executeUpdate(g);
			st.close();
			com.close();
			if(rs==1)
				resu = true;
			else
				resu = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resu;
	}


	public static boolean addSession(String key, String login) {
		boolean resu = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "INSERT INTO sessions (user_id, session_key,expiration_date) VALUES ((Select user_id from User where user_login='"+login+"'),'"+key+"','"+new java.sql.Timestamp(new Date().getTime()+1800000)+"')";
			Statement st = com.createStatement();
			int rs = st.executeUpdate(g);
			st.close();
			com.close();
			if(rs==1)
				resu = true;
			else
				resu = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resu;
	}

	public static boolean checkKeyUnique(String key) {
		String session_key = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "SELECT session_key from Sessions WHERE (session_key='"+key+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				session_key = rs.getString("session_key");
			}
			st.close();
			com.close();
			if(session_key==null) return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
	public static int getUserIDFromKey(String key) {
		//if(!(checkKeyValid(key))) return -1;
		int id = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "SELECT user_id from Sessions WHERE (session_key='"+key+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				id = rs.getInt("user_id");
			}
			st.close();
			com.close();
		}catch(Exception e) {e.printStackTrace();}
		return id;
	}
	
	
//	private static boolean if(checkKeyValid(String key) {
//		boolean resu = false;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
//			//String q = "SELECT session_key from Sessions WHERE (session_key='"+key+"' AND expiration_date="')";
//			Statement st = com.createStatement();
//			ResultSet rs = st.executeQuery(g);
//			while(rs.next()) {
//				resu= rs.getString("user_id");
//			}
//			st.close();
//			com.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

	public static int getUserIDFromLogin(String login) {
		int id = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "SELECT user_id from User WHERE (user_login='"+login+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				id = rs.getInt("user_id");
			}
			st.close();
			com.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public static boolean checkLog(String login) {
		String resu = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "SELECT session_key from Sessions WHERE (user_id=(Select user_id from User where user_login='"+login+"'))";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				resu = rs.getString("session_key");
			}
			st.close();
			com.close();
			if(resu==null) return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getNomFromUserID(int user_id) {
		String nom ="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "SELECT user_nom from User WHERE (user_id='"+user_id+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				nom = rs.getString("user_nom");
			}
			st.close();
			com.close();
		}catch(Exception e) {e.printStackTrace();}
		return nom;
	}

	public static String getPrenomFromUserId(int user_id) {
		String prenom ="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "SELECT user_prenom from User WHERE (user_id='"+user_id+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				prenom = rs.getString("user_prenom");
			}
			st.close();
			com.close();
		}catch(Exception e) {e.printStackTrace();}
		return prenom;
	}
	
	public static String getAgeFromUserId(int user_id) {
		int age = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "SELECT user_prenom from User WHERE (user_id='"+user_id+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				age = rs.getString("user_prenom");
			}
			st.close();
			com.close();
		}catch(Exception e) {e.printStackTrace();}
		return age;
	}
}
