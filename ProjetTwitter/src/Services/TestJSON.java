package Services;

import org.json.JSONException;
import org.json.JSONObject;

public class TestJSON {

	public static void main(String[] args) {
		
		try {
			JSONObject jsjs = new JSONObject().put("JSON","HELLO WORLD");

			System.out.println(jsjs.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
