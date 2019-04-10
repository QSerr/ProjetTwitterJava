package test;

import tools.RandomString;

public class testRandStr {

	public static void main(String[] args) {
		
		RandomString session = new RandomString(32);
		System.out.println(session.nextString());
	}

}
