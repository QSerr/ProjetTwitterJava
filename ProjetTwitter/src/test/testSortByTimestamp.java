package test;

import java.util.Date;
import java.util.List;

import org.bson.Document;

import Services.ServiceMessages;
import bd.MessageDB;

public class testSortByTimestamp {
	public static void main(String[] args) {

		List<Document> ld = MessageDB.getListMessagesOf(3);
		System.out.println(ld.get(0).get("timestamp").getClass());
		java.util.Date date = new Date();
		System.out.println(date.after(ld.get(0).getDate("timestamp")));
		System.out.println(ServiceMessages.getListAllMessages("1MhgDO5Hk34bC0mKKCIIPhn9hS251eJr"));
	}
}