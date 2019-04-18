package test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Services.ServiceMessages;
import bd.MessageDB;
import tools.RandomString;

public class TestMessage {

	public static void main(String[] args) {
		System.out.println(ServiceMessages.searchMessages("86vrst5LOWNIJHPRuizaXRUIbwxcCBTK", "PIZZA"));
		System.out.println(ServiceMessages.getListAllMessages("86vrst5LOWNIJHPRuizaXRUIbwxcCBTK"));
	}
}
