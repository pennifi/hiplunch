package com.soikea.hiplunch;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.List;

/**
 * Created by penni on 18/08/14.
 */
public class SonaattiProvider {

	private SyndFeed feed = null;

	public SonaattiProvider() {
		try {
			feed = ContentUtil.getRSSFeedForUrl(Constants.URL_SONAATTI);
		} catch (Exception e) {
			System.out.println("Error getting feed");
		}
	}

	public String processFeed(String prefix) {
		return processFeed(prefix, false);
	}

	public String processFeed(String prefix, boolean includeGrill) {
		StringBuffer stringBuffer = new StringBuffer();

		for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
			if (entry.getTitle().startsWith(prefix)) {
				stringBuffer.append(cleanUpSonaattiFeed(entry.getDescription().getValue()));
			}
		}

		if (includeGrill) {
			stringBuffer.append(processGrill());
		}


		if (stringBuffer.length() == 0) {
			return Constants.ERROR_NOT_AVAILABLE;
		} else {
			return stringBuffer.toString();
		}
	}

	private String cleanUpSonaattiFeed(String value) {
		StringBuffer stringBuffer = new StringBuffer();

		value = value.trim();
		value = value.split("\n")[0]; // remove all after row change
		String[] array = value.split("\\),");

		for (int i = 0; i < array.length; i++) {

			stringBuffer.append(array[i].split("[#\\(\\d]")[0].trim());
			if (i < array.length - 1) {
				stringBuffer.append(", ");
			}
		}
		return stringBuffer.toString();
	}


	private String processGrill() {
		StringBuffer stringBuffer = new StringBuffer();

		try {
			JSONObject json = new JSONObject(ContentUtil.getJSONContent(Constants.GRILL_API));

			JSONArray results = json.getJSONObject("results").getJSONArray("collection1");

			for (int i = 0; i < results.length(); i++) {
				JSONObject course = results.getJSONObject(i);
				stringBuffer.append(course.getString("paistopiste"));
				if (i < results.length() - 1) {
					stringBuffer.append("");
				}
			}

			String cleaned = stringBuffer.toString();
			cleaned = cleaned.trim();
			cleaned = cleaned.replace("PAISTOPISTEELTÄ ", ", <a><i>Paistopisteeltä:</i></a> ");
			cleaned = cleaned.replaceAll("\n", "");
			cleaned = cleaned.split("[#\\(\\d]")[0];

			return cleaned;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
