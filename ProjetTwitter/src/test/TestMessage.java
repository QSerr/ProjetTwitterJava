package test;

import org.json.JSONException;

import bd.MessageDB;
import tools.RandomString;

public class TestMessage {

	public static void main(String[] args) {
//		RandomString session = new RandomString(32);
//		MessageDB.addMessage(session.nextString(), "AAAAAAAAAAAAAAAAAAA");
//		try {
//			System.out.println(MessageDB.searchMessages("AAAAAAAAAAA"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String my_key = "qibYJt1a6Aax5KVP8arCyUAhBIbODpcM";
		System.out.println(Services.ServiceMessages.getListMessages(my_key));
	}

}
