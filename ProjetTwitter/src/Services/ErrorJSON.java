package Services;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	
	/**
	 * Uses the error code to output an error message
	 * @param message
	 * @param codeErreur
	 * @return
	 */
	public static JSONObject serviceRefused(String message,int codeErreur) {
		try {
			return new JSONObject().put(message,codeErreur);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject serviceAccepted() {
		try {
			return new JSONObject().put("OK", true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
