package bd;
import  com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import  com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import Services.ErrorJSON;
import tools.DBStatic;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClientURI;
import  com.mongodb.client.MongoClient;
public class MessageDB {


	/**
	 * Adds the text to the db, searches the sql database for
	 * the user_id by using the key (we assume that the parent
	 * already checked for the connection status)
	 * @param key the session_key stored by the client
	 * @param text the message to add to the db
	 * @return
	 */
	public static void addMessage(String key,String text) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		Document mes= new Document();
		int user_id = UserBD.getUserIDFromKey(key);
		GregorianCalendar gc = new GregorianCalendar();
		String nom = UserBD.getNomFromUserID(user_id);
		String prenom = UserBD.getPrenomFromUserId(user_id);
		String author = nom+" "+prenom;
		mes.append("author",author).append("user_id", user_id).append("timestamp", gc.getTime()).append("text", text).append("comments", new ArrayList<Document>());
		mc.insertOne(mes);
		mongo.close();
	}

	public static void addComment(String message_id, String text) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		Document query= new Document();
		ObjectId id = new ObjectId(message_id);
		query.append("_id", id);
		Document setData = new Document().append("comment", text).append("author", "john john").append("timestamp", new GregorianCalendar().getTime());
		mc.updateOne(query, Updates.addToSet("comments", setData));
		mongo.close();
	}

	public static List<ObjectId> searchMessages( String query) throws JSONException {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		List<ObjectId> id = new ArrayList<ObjectId>();
		String cpt = "0";
		String pattern = ".*" + query + ".*";
		Document search = new Document().append("$regex", pattern);
		MongoCursor<Document> cur = mc.find(new Document().append("text", search)).iterator();
		while(cur.hasNext()) {
			Document dfg = cur.next();
			id.add(dfg.getObjectId("_id"));
			cpt = String.valueOf(Integer.parseInt(cpt)+1);
		}
		mongo.close();
		return id;
	}
	
	public static List<Document> getListMessages(int user_id){
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		List<Document> id = new ArrayList<>();
		//String cpt = "0";
		String pattern = ".*" + user_id + ".*";
		Document search = new Document().append("$regex", pattern);
		MongoCursor<Document> cur = mc.find(new Document().append("user_id", user_id)).iterator();
		while(cur.hasNext()) {
			Document dfg = cur.next();
			id.add(dfg);
			//cpt = String.valueOf(Integer.parseInt(cpt)+1);
		}
		mongo.close();
		return id;
	}
}
//whatever pattern you need. But you do not need the "/" delimiters
//String pattern = ".*" + query.getString("equipment") + ".*";

//find(regex("field name", "pattern", "options"));
//collection.find(regex("equipment", pattern, "i"));

//public static void main(String[] args) throws Exception{
//
//MongoClient mongo = MongoClients.create("mongodb://localhost:27018");
//MongoDatabase mDB= mongo.getDatabase("Test");
//mDB.createCollection("coll");
//MongoCollection<Document> mc = mDB.getCollection("coll");
//Document query = new Document();
//String user_id = "1";
//query.append("user_id", user_id );
//mc.insertOne(query);		
//}