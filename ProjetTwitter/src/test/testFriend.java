package test;

import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Services.ServiceFriend;
import bd.MessageDB;
import tools.RandomString;

public class testFriend {

	public static void main(String[] args) throws Exception{

//		JSONObject log = ServiceFriend.addFriend("qibYJt1a6Aax5KVP8arCyUAhBIbODpcM", "PAUL");
//		System.out.println(log);
		//		JSONObject log2 = ServiceFriend.removeFriend("LopzO1YsOWsRfhZQlDDSy3TirEPtPTbe","john");
		//		System.out.println(log2);

		//MessageDB.addMessage(new RandomString(32).nextString(), "MongoDB is awesome!");
		//MessageDB.addComment("5c77fb28de3b1a2f97818161", new RandomString(32).nextString(), "me toseqrqezro!");
		//List<ObjectId> jhg = MessageDB.searchMessages("MongoDB");
		//System.out.println(jhg);
		//System.out.println(mc.find().first());		
		//System.out.println("ObjectId(\"5c76c897c2cbd157f56ab1d6\")");
		JSONObject log = ServiceFriend.getListFriends("KP87bs1dzPTwmZQSndwHgV0TQCDxXJ4S");
		System.out.println(log);
		System.out.println(ServiceFriend.addFriend("KP87bs1dzPTwmZQSndwHgV0TQCDxXJ4S", "PAUL"));
	}
}
