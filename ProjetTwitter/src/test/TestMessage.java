package test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

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
		Instant instant = Instant.now();
//		java.sql.Timestamp ts = java.sql.Timestamp.valueOf( instant );
//		System.out.println(ts);
		ZoneId zoneId = ZoneId.of( "Europe/Paris" );
		ZonedDateTime zdt = ZonedDateTime.ofInstant( instant , zoneId );
		System.out.println(zdt);
		java.sql.Timestamp js = new java.sql.Timestamp(new Date().getTime()+50000);
		System.out.println(js.after(new java.sql.Timestamp(new Date().getTime())));
		System.out.println(js);
		System.out.println(new java.sql.Timestamp(new Date().getTime()));
		//String my_key = "qibYJt1a6Aax5KVP8arCyUAhBIbODpcM";
		//System.out.println(Services.ServiceMessages.getListMessages(my_key));
	}

}
