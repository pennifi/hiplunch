package com.soikea.hiplunch;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
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
import java.util.List;

/**

 */
public class Hiplunch {

	private static final String URL_SONAATTI = "http://www.sonaatti.fi/rssfeed/";
	private static final String URL_SODEXO = "http://www.sodexo.fi/ruokalistat/output/daily_json/66/";
	private static final String PREFIX_WILHELMIINA = "Wilhelmiina";
	private static final String PREFIX_PIATO = "Piato";
	private static final String PREFIX_SODEXO = "Sodexo";

	private static SyndFeed sonaattiFeed = null;

	private static void initFeed() {
		FeedProvider feedProvider = new FeedProvider();
		try {
			sonaattiFeed = feedProvider.getSyndFeedForUrl(URL_SONAATTI);
		} catch (Exception e) {
			System.out.println("Error getting feed");
		}
	}

	private static String getSodexo() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(PREFIX_SODEXO + ": " + getSodexoFeed());
		return stringBuffer.toString();
	}

	private static String getWilhelmiina() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(PREFIX_WILHELMIINA + ": " + getSonaattiFeed(PREFIX_WILHELMIINA));
		return stringBuffer.toString();
	}

	private static String getPiato() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(PREFIX_PIATO + ": " + getSonaattiFeed(PREFIX_PIATO));
		return stringBuffer.toString();
	}

	private static String getSonaattiFeed(String prefix) {
		StringBuffer stringBuffer = new StringBuffer();

		for (SyndEntry entry : (List<SyndEntry>) sonaattiFeed.getEntries()) {
			if (entry.getTitle().startsWith(prefix)) {
				stringBuffer.append(cleanUpCourse(entry.getDescription().getValue()));
			}
		}
		return stringBuffer.toString();
	}

	private static String cleanUpCourse(String value) {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(value.replaceAll("\n", ""));

		return stringBuffer.toString();
	}

	private static String getUrlSodexo() {
		Calendar cal = Calendar.getInstance();

		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(URL_SODEXO);
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

	private static String getSodexoFeed() {
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

	private static String getUrlContent(String urlString) {
		URL url;

		try {
			// get URL content
			url = new URL(urlString);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			String content = br.readLine();

			return content;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main( String[] args ) {
		initFeed();

		System.out.println(getSodexo());
		System.out.println(getWilhelmiina());
//		System.out.println(getPiato());

//		HipChatter hipChatter = new HipChatter();
//		hipChatter.sendMessage(getWilhelmiina());
    }
}
