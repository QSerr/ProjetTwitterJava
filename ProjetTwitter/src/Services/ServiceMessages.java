package Services;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import bd.MessageDB;
import bd.UserBD;

public class ServiceMessages {

	public static JSONObject addMessage(String key, String text) {
		if(key == null || text == null) return ErrorJSON.serviceRefused("Error", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		if(UserBD.getUserIDFromKey(key) == -1) return ErrorJSON.serviceRefused("Error", 46);
		Document mes = MessageDB.addMessage(key,text);
		JSONObject ob;
		try {
			ob = new JSONObject().put("_id", mes.get("_id")).put("author", mes.get("author")).put("user_id", mes.get("user_id")).put("text", mes.get("text")).put("timestamp", mes.get("timestamp")).put("comments", mes.get("comments"));
			return ob;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ErrorJSON.serviceRefused("Error", 46);
	}
	
	public static JSONObject addComment(String key, String id, String text) {
		if(key == null || text == null) return ErrorJSON.serviceRefused("Error", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		int user_id = UserBD.getUserIDFromKey(key);
		if( user_id == -1) return ErrorJSON.serviceRefused("Error", 46);
		Document ret = MessageDB.addComment(user_id, id, text);
		try {
			JSONObject ob = new JSONObject().put("author", ret.get("author")).put("text", ret.get("comment")).put("timestamp", ret.get("timestamp"));
			return ob;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ErrorJSON.serviceRefused("Error", 46);
	}
	
	public static JSONObject searchMessages(String key,String query) {
		if(key == null || query == null) return ErrorJSON.serviceRefused("Erreur", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		if(UserBD.getUserIDFromKey(key)==-1) return ErrorJSON.serviceRefused("Erreur", 46);
		try {
			JSONObject resu = new JSONObject();
			List<ObjectId> idt = MessageDB.searchMessages(query);
			for(int i = 0;i<idt.size();i++) {
				resu.append(""+i, idt.get(i)); 
			}
			return resu;
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Error", 50);
		}
	}

	public static JSONObject getListMessages(String key) {
		if(key==null) return ErrorJSON.serviceRefused("Error", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		else {
			try {
				JSONObject resu = new JSONObject();
				List<Document> ld = MessageDB.getListMessages(key);
				resu.put("ListMessages", ld);
				return resu;
			}catch (JSONException e) {
				return ErrorJSON.serviceRefused("Error", 50);
			}
		}
	}
	
	public static JSONObject getListMessagesOfFriends(String key) {
		if(key==null) return ErrorJSON.serviceRefused("Error", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		else {
			try {
				JSONObject resu = new JSONObject();
				List<Document> ld2 = MessageDB.getListeMessagesOfFriends(key);
				resu.put("ListMessagesFriend", ld2);
				return resu;
			}catch (JSONException e) {
				return ErrorJSON.serviceRefused("Error", 50);
			}
		}
	}
	
	public static JSONObject getListMessagesOf(String key,int user_id) {
		if(key==null || user_id < 1) return ErrorJSON.serviceRefused("Error", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		else {
			try {
				JSONObject resu = new JSONObject();
				List<Document> ld = MessageDB.getListMessagesOf(user_id);
				resu.put("ListMessages", ld);
				return resu;
			}catch (JSONException e) {
				return ErrorJSON.serviceRefused("Error", 50);
			}
		}
	}
	
	public static JSONObject removeMessage(String key, String mess_id) {
		if(key==null || mess_id==null) return ErrorJSON.serviceRefused("Error", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		try {
			JSONObject resu = new JSONObject();
			if(MessageDB.removeMessage(key, mess_id)) return ErrorJSON.serviceAccepted();
			else return ErrorJSON.serviceRefused("Error", 3);
			return resu;
		}catch (JSONException e) {
			return ErrorJSON.serviceRefused("Error", 50);
		}
	}

	public static JSONObject getListAllMessages(String key) {
		if(key==null) return ErrorJSON.serviceRefused("Error", 1);
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		else {
			try {
				JSONObject resu = new JSONObject();
				List<Document> ld = MessageDB.getListAllMessages();
				resu.put("ListAllMessages", ld);
				return resu;
			}catch (JSONException e) {
				return ErrorJSON.serviceRefused("Error", 50);
			}
		}
	}
}
