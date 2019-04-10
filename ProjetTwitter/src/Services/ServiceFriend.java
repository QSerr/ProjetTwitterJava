package Services;

import org.json.JSONException;
import org.json.JSONObject;

import bd.FriendBD;
import bd.UserBD;

public class ServiceFriend {

	public static JSONObject addFriend(String my_key, String login) {
		int friend_id = UserBD.getUserIDFromLogin(login);
		if(friend_id != -1) {
			try{
				if(FriendBD.followFriend(my_key, friend_id)) {

					return ErrorJSON.serviceAccepted();
				}
			}catch(Exception e) {
				return ErrorJSON.serviceRefused("Error", 555);}
		}
		return ErrorJSON.serviceRefused("Error", 55);
	}

	public static JSONObject removeFriend(String my_key, String login) {
		int friend_id = UserBD.getUserIDFromLogin(login);
		if(friend_id != -1) {
			try{
				if(FriendBD.removeFriend(my_key, friend_id)) {
					return ErrorJSON.serviceAccepted();
				}
			}catch(Exception e) {		
				return ErrorJSON.serviceRefused("Error", 666);}
		}
		return ErrorJSON.serviceRefused("Error", 66);
	}
	
	public static JSONObject getListFriends(String my_key) {
		//if(!(checkKeyValid(my_key))) return ErrorJSON.serviceRefused();
		JSONObject js = new JSONObject();
		try {
			js.put("listeFriend",FriendBD.listFriends(my_key));
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Error", 777);
		}
		return js;
	}
}
