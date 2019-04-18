package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import tools.DBStatic;
import tools.Database;

public class FriendBD {

	public static boolean followFriend(String my_key,int friend_id) throws Exception{

		int my_id = UserBD.getUserIDFromKey(my_key);
		boolean resu = false;
		try {
			Connection com = Database.getMySQLConnection(1);
			String g = "INSERT INTO follows (my_id, friend_id) VALUES ('"+my_id+"','"+friend_id+"')";
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
			Connection com = Database.getMySQLConnection(1);
			String g = "DELETE FROM follows WHERE my_id='"+my_id+"' AND friend_id='"+friend_id+"'";
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
			Connection com = Database.getMySQLConnection(1);
			String g = "Select friend_id FROM follows WHERE my_id='"+my_id+"'";
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
