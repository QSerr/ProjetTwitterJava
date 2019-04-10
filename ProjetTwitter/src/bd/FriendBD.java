package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import tools.DBStatic;

public class FriendBD {

	public static boolean followFriend(String my_key,int friend_id) throws Exception{

		int my_id = UserBD.getUserIDFromKey(my_key);
		boolean resu = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "INSERT INTO Follows (my_id, friend_id) VALUES ('"+my_id+"','"+friend_id+"')";
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

	public static boolean removeFriend(String my_key,int friend_id) {

		int my_id = UserBD.getUserIDFromKey(my_key);
		boolean resu = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "DELETE FROM Follows WHERE my_id='"+my_id+"' AND friend_id='"+friend_id+"'";
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
	
	public static List<Integer> listFriends(String my_key) {
		int my_id = UserBD.getUserIDFromKey(my_key);
		List<Integer> ls = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection com = DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":3308/" + DBStatic.mysql_db,  DBStatic.mysql_username,  DBStatic.mysql_password);
			String g = "Select friend_id FROM Follows WHERE my_id='"+my_id+"'";
			Statement st = com.createStatement();
			ResultSet rs = st.executeQuery(g);
			while(rs.next()) {
				ls.add(rs.getInt("friend_id"));
			}
			st.close();
			com.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}


}