package test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Services.ServiceMessages;
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
//		Instant instant = Instant.now();
//		java.sql.Timestamp ts = java.sql.Timestamp.valueOf( instant );
//		System.out.println(ts);
//		ZoneId zoneId = ZoneId.of( "Europe/Paris" );
//		ZonedDateTime zdt = ZonedDateTime.ofInstant( instant , zoneId );
//		System.out.println(zdt);
//		java.sql.Timestamp js = new java.sql.Timestamp(new Date().getTime()+50000);
//		System.out.println(js.after(new java.sql.Timestamp(new Date().getTime())));
//		System.out.println(js);
//		System.out.println(new java.sql.Timestamp(new Date().getTime()));
//		String my_key = "qibYJt1a6Aax5KVP8arCyUAhBIbODpcM";
//		System.out.println(Services.ServiceMessages.getListMessages(my_key));
		//MessageDB.removeMessage("5cacb7b91f905574c93a0f27");
//		JSONObject ob = ServiceMessages.getListMessagesOfFriends("L9QskGoqyQmQ31PKBuRDrQ9BCX54NVGJ");
//		try {
//			//List<Document> ld = (List<Document>) ob.get("ListMessagesFriend");
//			System.out.println(ob.get("ListMessagesFriend"));
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		ServiceMessages.addMessage("L9QskGoqyQmQ31PKBuRDrQ9BCX54NVGJ", "aaaasertr(etaaaaaaaaaaaaafjihsdgvdyi");
		System.out.println(ServiceMessages.getListMessages("L9QskGoqyQmQ31PKBuRDrQ9BCX54NVGJ"));
	}

}
