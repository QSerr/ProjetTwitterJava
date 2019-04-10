package Services;

import org.json.JSONException;
import org.json.JSONObject;

import bd.UserBD;
import tools.RandomString;

public class ServiceAccount {

	public static JSONObject CreateUser(String prenom, String nom, String login, String password,String sexe,int age) {
		if(prenom==null || nom==null || login==null || password==null) 
			return ErrorJSON.serviceRefused("param null",1);
		if(UserBD.userExists(login)) return ErrorJSON.serviceRefused("log exist",2);
		UserBD.createUser(login, password, prenom, nom, sexe, age);
		return ErrorJSON.serviceAccepted();
	}

	public static JSONObject Login(String login, String password) {
		if(login==null || password==null) 
			return ErrorJSON.serviceRefused("param null",1);
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
		return ErrorJSON.serviceRefused("key invalid", 4);
	}

	private static String generateKey() {
		RandomString session = new RandomString(32);
		return (session.nextString());
	}
}
