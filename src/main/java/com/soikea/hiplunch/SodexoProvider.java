package com.soikea.hiplunch;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

/**
 * Created by penni on 19/08/14.
 */
public class SodexoProvider {

	private String getUrlSodexo() {
		Calendar cal = Calendar.getInstance();

		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(Constants.URL_SODEXO);
		stringBuffer.append(cal.get(Calendar.YEAR));
		stringBuffer.append("/");
		if (cal.get(Calendar.MONTH) < 10) {
			stringBuffer.append("0");
		}
		stringBuffer.append((cal.get(Calendar.MONTH) + 1));
		stringBuffer.append("/");
		stringBuffer.append(cal.get(Calendar.DAY_OF_MONTH));
		stringBuffer.append("/fi");

		return stringBuffer.toString();
	}

	public String processFeed() {
		StringBuffer stringBuffer = new StringBuffer();

		try {
			JSONObject json = new JSONObject(getUrlContent(getUrlSodexo()));

			JSONArray array = json.getJSONArray("courses");

			for (int i = 0; i < array.length(); i++) {
				JSONObject course = array.getJSONObject(i);
				stringBuffer.append(course.getString("title_fi"));
				if (i < array.length() - 1) {
					stringBuffer.append(", ");
				}
			}
			return stringBuffer.toString();

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getUrlContent(String urlString) {
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			return br.readLine();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
