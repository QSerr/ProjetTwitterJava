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
	public static Document addMessage(String key,String text) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		Document mes= new Document();
		int user_id = UserBD.getUserIDFromKey(key);
		GregorianCalendar gc = new GregorianCalendar();
		String nom = UserBD.getNomFromUserID(user_id);
		String prenom = UserBD.getPrenomFromUserId(user_id);
		String author = UserBD.getLoginFromUserID(user_id);
		mes.append("author",author).append("user_id", user_id).append("timestamp", gc.getTime()).append("text", text).append("comments", new ArrayList<Document>());
		mc.insertOne(mes);
		MongoCursor<Document> cur = mc.find(mes).iterator();
		Document dfg = new Document();
		while(cur.hasNext()) {
			dfg = cur.next();
		}
		mongo.close();
		return dfg;
	}

	public static Document addComment(int user_id, String message_id, String text) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		Document query= new Document();
		ObjectId id = new ObjectId(message_id);
		query.append("_id", id);
		Document setData = new Document().append("comment", text).append("author", UserBD.getLoginFromUserID(user_id)).append("timestamp", new GregorianCalendar().getTime());
		mc.updateOne(query, Updates.addToSet("comments", setData));
		mongo.close();
		return setData;
	}

	public static List<Document> searchMessages( String query) throws JSONException {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		List<Document> id = new ArrayList<>();
		String pattern = ".*" + query + ".*";
		Document search = new Document().append("$regex", pattern);
		MongoCursor<Document> cur = mc.find(new Document().append("text", search)).iterator();
		while(cur.hasNext()) {
			Document dfg = cur.next();
			id.add(dfg);
		}
		mongo.close();
		return id;
	}
	
	/**
	 * Goes through all the messages of the user_id
	 * and puts them in a list
	 * @param user_id
	 * @return the list of messages of self and friends
	 */
	public static List<Document> getListMessages(String key){
		int user_id = UserBD.getUserIDFromKey(key);
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		List<Document> id = new ArrayList<>();
		MongoCursor<Document> cur = mc.find(new Document().append("user_id", user_id)).iterator();
		while(cur.hasNext()) {
			Document dfg = cur.next();
			id.add(dfg);
		}
		mongo.close();
		return id;
	}
	
	public static List<Document> getListeMessagesOfFriends(String key){
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		List<Document> id = new ArrayList<>();
		List<Integer> li = FriendBD.listFriends(key);
		for(int user_id : li) {
			MongoCursor<Document> cur = mc.find(new Document().append("user_id", user_id)).iterator();
			while(cur.hasNext()) {
				Document dfg = cur.next();
				id.add(dfg);
			}
		}
		mongo.close();
		return id;
	}

	public static List<Document> getListMessagesOf(int user_id) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		List<Document> id = new ArrayList<>();
		MongoCursor<Document> cur = mc.find(new Document().append("user_id", user_id)).iterator();
		while(cur.hasNext()) {
			Document dfg = cur.next();
			id.add(dfg);
		}
		mongo.close();
		return id;
	}
	
	/**
	 * deletes your own message, user_id from the key
	 * has to match the user_id of the poster
	 * cant delete messages from others
	 * @param key
	 * @param mess_id
	 * @return true if the selected message was deleted
	 */
	public static boolean removeMessage(String key, String mess_id) {
		boolean resu = false;
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		int user_id = UserBD.getUserIDFromKey(key);
		Document cur = mc.findOneAndDelete(new Document().append("_id", new ObjectId(mess_id)).append("user_id", user_id));
		System.out.println(cur);
		mongo.close();
		return resu;
	}

	public static List<Document> getListAllMessages() {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mDB= mongo.getDatabase("ProjetTwitter");
		MongoCollection<Document> mc = mDB.getCollection("Messages");
		List<Document> documents = (List<Document>) mc.find().into(new ArrayList<Document>());
		mongo.close();
		return documents;
	}
}