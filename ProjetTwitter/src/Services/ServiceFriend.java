package Services;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import bd.FriendBD;
import bd.UserBD;

public class ServiceFriend {

	public static JSONObject addFriend(String my_key, String login) {
		if(!(UserBD.checkKeyValid(my_key))) return ErrorJSON.serviceRefused("Error", 2);
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
		if(!(UserBD.checkKeyValid(my_key))) return ErrorJSON.serviceRefused("Error", 2);
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
		if(!(UserBD.checkKeyValid(my_key))) return ErrorJSON.serviceRefused("Error", 2);
		JSONObject js = new JSONObject();
		try {
			List<Integer> li = FriendBD.listFriends(my_key);
			for(int i=0; i<li.size();i++) {
				js.put(Integer.toString(i), new JSONObject().put("friend_id", li.get(i)).put("nom",UserBD.getNomFromUserID(li.get(i))).put("prenom", UserBD.getPrenomFromUserId(li.get(i))).put("age", UserBD.getAgeFromUserId(li.get(i))).put("sexe", UserBD.getSexeFromUserId(li.get(i))));
			}
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Error", 777);
		}
		return js;
	}
}
