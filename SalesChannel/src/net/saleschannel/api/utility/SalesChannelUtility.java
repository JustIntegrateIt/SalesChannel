package net.saleschannel.api.utility;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class SalesChannelUtility {

	private static final Logger LOGGERS = Logger.getLogger(SalesChannelUtility.class);
	
	/**
	 * This member function will generate UUID.
	 *
	 * @param isHiphenRequired the is hiphen required
	 * @return String
	 */
	public static String getUUID(final boolean isHiphenRequired) {
		final String uuid = UUID.randomUUID().toString();
		if (!isHiphenRequired) {
			final String[] tokenizer = uuid.split("-");
			final StringBuffer uuidString = new StringBuffer();
			for (int i = 0; i < tokenizer.length; i++) {
				final String uid = tokenizer[i].toString();
				uuidString.append(uid);
			}
			return uuidString.toString();
		} else {
			return uuid;
		}
	}
	
	/**
	 * Method used to validate parameter values.
	 *
	 * @param paramName the param name
	 * @param paramValue the param value
	 * @return JSONObject
	 */
	public static JSONObject validateParameters(final String paramName, final String paramValue) {
		final JSONObject jsonObject = new JSONObject();
		if (paramValue == null) {
			try {
				jsonObject.put("1004", paramName + " value is empty.@#" + paramName + "#@");
			} catch (final JSONException e) {
				LOGGERS.error("Exception in forming the JSON: " + e.getMessage());
			}
		}
		return jsonObject;
	}
}
