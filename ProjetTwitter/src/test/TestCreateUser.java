package test;

import org.json.JSONException;
import org.json.JSONObject;

import Services.ServiceAccount;
import bd.UserBD;

public class TestCreateUser {

	public static void main(String[] args) throws Exception{
		String login = "moei2";
		String password = "monmdp";
		String prenom = "Qtin";
		String nom = "Ser";
		String sexe = "male";
		int age = 22;
		if(UserBD.createUser(login, password, prenom, nom, sexe, age)) {
			System.out.println(UserBD.userExists(login));
			System.out.println("oui");
		}
		else {
			if(UserBD.userExists(login)) {
				System.out.println("User already exists");
			}
		}
		if(UserBD.checkPasswordCorrect(login, password)) {
			System.out.println("ca marche !");
		}
		else {
			System.out.println("probleme");
		}
		JSONObject log = ServiceAccount.Login(login, password);
		try {
			System.out.println(log.get("key"));
		}catch(JSONException e) { System.out.println("erreur");}
//		JSONObject log2 = ServiceAccount.Logout(log.getString("key"));
//		try {
//			System.out.println(log2.get("key invalid"));
//		}catch(JSONException e) {System.out.println("logout OK");}
	//	JSONObject log2b =ServiceAccount.Logout(log.getString("key"));
	//	System.out.println(log2b);
		//BqhpBZlHoyaPHE1hKlThYec2i4EN3V2B
//		UserBD.addSession(key, login);
//		UserBD.checkKeyUnique(key);
//		UserBD.checkPasswordCorrect(login, password);
//		UserBD.deleteKey(key);
//		UserBD.getUserIDFromKey(key);
//		UserBD.getUserIDFromLogin(login);
		
	}

}
