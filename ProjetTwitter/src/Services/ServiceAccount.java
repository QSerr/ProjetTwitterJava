package Services;

import org.json.JSONException;
import org.json.JSONObject;

import bd.UserBD;
import tools.RandomString;

public class ServiceAccount {

	public static JSONObject CreateUser(String prenom, String nom, String login, String password,String sexe,int age) {
		if(prenom==null || nom==null || login==null || password==null) 
			return ErrorJSON.serviceRefused("Error",1);
		if(UserBD.userExists(login)) return ErrorJSON.serviceRefused("Error",2);
		UserBD.createUser(login, password, prenom, nom, sexe, age);
		return ErrorJSON.serviceAccepted();
	}

	public static JSONObject Login(String login, String password) {
		if(login==null || password==null) 
			return ErrorJSON.serviceRefused("Error",1);
		String key;
		try {
			if(UserBD.userExists(login)) {
				if(UserBD.checkPasswordCorrect(login, password)) {
					if(UserBD.checkLog(login)) {
						do {
							key = generateKey();
						}while(UserBD.checkKeyUnique(key)==false);
						if(UserBD.addSession(key,login)) {
							int id = UserBD.getUserIDFromKey(key);
							return new JSONObject().put("id", id).put("login", login).put("key", key).put("user_nom", UserBD.getNomFromUserID(id)).put("user_prenom",UserBD.getPrenomFromUserId(id));
						}
					}
				}
			}
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Error", 11);
		}
		return ErrorJSON.serviceRefused("Error", 6789);
	}

	public static JSONObject Logout(String key) {
		if(UserBD.deleteKey(key))
			return ErrorJSON.serviceAccepted();
		return ErrorJSON.serviceRefused("Error", 4);
	}

	public static JSONObject getProfileOfOther(String key, int user_id) {
		if(key==null || user_id < 1) {
			return ErrorJSON.serviceRefused("Error", 1);
		}
		if(!(UserBD.checkKeyValid(key))) return ErrorJSON.serviceRefused("Error", 2);
		try {
			JSONObject resu = new JSONObject().put("user_nom", UserBD.getNomFromUserID(user_id)).put("prenom", UserBD.getPrenomFromUserId(user_id)).put("age", UserBD.getAgeFromUserId(user_id)).put("sexe", UserBD.getSexeFromUserId(user_id));
			return resu;
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Error", 11);
		}
	}

	private static String generateKey() {
		RandomString session = new RandomString(32);
		return (session.nextString());
	}

}
