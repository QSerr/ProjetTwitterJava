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
		if(UserBD.getUserIDFromKey(key) == -1) return ErrorJSON.serviceRefused("Error", 46);
		MessageDB.addMessage(key,text);
		return ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject addComment(String key, String id, String text) {
		if(key == null || text == null) return ErrorJSON.serviceRefused("Error", 1);
		if(UserBD.getUserIDFromKey(key) == -1) return ErrorJSON.serviceRefused("Error", 46);
		MessageDB.addComment(id, text);
		return ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject searchMessages(String key,String query) {
		if(key == null || query == null) return ErrorJSON.serviceRefused("Erreur", 1);
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
		int user_id = UserBD.getUserIDFromKey(key);
		if(user_id==-1){
			return ErrorJSON.serviceRefused("Error", 46);
		}
		else {
			try {
				JSONObject resu = new JSONObject();
				List<Document> ld = MessageDB.getListMessages(user_id);
				resu.put("ListMessages", ld);
				return resu;
			}catch (JSONException e) {
				return ErrorJSON.serviceRefused("Error", 50);
			}
		}
	}
}