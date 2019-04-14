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
			Connection com = Database.getMySQLConnection(0);
			String q = "Select user_id from user where (user_login='"+login+"')";
			java.sql.Statement s = com.createStatement();
			ResultSet rs = s.executeQuery(q);
			if(rs.next()) {
				rest=true;
			}
			else {rest=false;}
			s.close();
			com.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rest;
	}

	public static boolean checkPasswordCorrect(String login, String password) {
		boolean resu=false;
		try {
			Connection com = Database.getMySQLConnection(0);
			String query = "SELECT user_id FROM user WHERE (user_login='"+login+"' AND user_password='"+password+"')";
			java.sql.Statement s = com.createStatement();
			ResultSet rs = s.executeQuery(query);
			while(rs.next()) {
				resu = true;
			}
			s.close();
			com.close();
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
				Connection com = Database.getMySQLConnection(0);
				String g = "INSERT INTO user (user_id,user_login,user_password,user_prenom,user_nom,user_sexe,user_age) "
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
			Connection com = Database.getMySQLConnection(0);
			String g = "DELETE FROM sessions WHERE session_key='"+key+"'";
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
			Connection com = Database.getMySQLConnection(0);
			String g = "INSERT INTO sessions (user_id, session_key,expiration_date) VALUES ((Select user_id from User where user_login='"+login+"'),'"+key+"','"+new java.sql.Timestamp(new Date().getTime()+10800000)+"')";
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
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT session_key from sessions WHERE (session_key='"+key+"')";
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
		int id = -1;
		try {
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT user_id from sessions WHERE (session_key='"+key+"')";
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


	public static boolean checkKeyValid(String key) {
		boolean resu = false;
		try {
			Connection com = Database.getMySQLConnection(0);
			String q = "SELECT expiration_date from sessions WHERE session_key='"+key+"'";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()) {
				java.sql.Timestamp js = new java.sql.Timestamp(new Date().getTime());
				resu=js.before(rs.getTimestamp("expiration_date"));
			}
			st.close();
			com.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resu;
	}

	public static int getUserIDFromLogin(String login) {
		int id = -1;
		try {
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT user_id from user WHERE (user_login='"+login+"')";
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
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT session_key from sessions WHERE (user_id=(Select user_id from User where user_login='"+login+"'))";
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
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT user_nom from user WHERE (user_id='"+user_id+"')";
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
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT user_prenom from user WHERE (user_id='"+user_id+"')";
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

	public static int getAgeFromUserId(int user_id) {
		int age = -1;
		try {
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT user_age from user WHERE (user_id='"+user_id+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				age = rs.getInt("user_age");
			}
			st.close();
			com.close();
		}catch(Exception e) {e.printStackTrace();}
		return age;
	}

	public static String getSexeFromUserId(int user_id) {
		String sexe ="";
		try {
			Connection com = Database.getMySQLConnection(0);
			String g = "SELECT user_sexe from user WHERE (user_id='"+user_id+"')";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				sexe = rs.getString("user_sexe");
			}
			st.close();
			com.close();
		}catch(Exception e) {e.printStackTrace();}
		return sexe;
	}

}
