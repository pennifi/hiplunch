package com.soikea.hiplunch;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 04/11/14.
 */
public class ContentUtil {
	static final Logger log = LoggerFactory.getLogger(ContentUtil.class);

    public static final String ERROR_NOT_AVAILABLE = "Tietoja ei saatavilla.";

	public static String getJSONContent(String urlString) {
		StringBuilder stringBuffer = new StringBuilder();

		try {

			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			String result;
			while (( result = br.readLine()) != null) {
				stringBuffer.append(result);
			}

			return stringBuffer.toString();

		} catch (MalformedURLException e) {
			log.error("Error getting json content: " + e.getMessage(), e);
		} catch (IOException e) {
			log.error("Error getting json content: " + e.getMessage(), e);
		}
		return null;
	}


    public static JSONArray getJsonResult(String url, String key) throws JSONException {
        return getJsonResult(url, key, null);
    }

    public static JSONArray getJsonResult(String url, String key, String subkey) throws JSONException {
        JSONArray results;

        try {
            JSONObject json = new JSONObject(getJSONContent(url));

            results = ( subkey != null ? json.getJSONObject(key).getJSONArray(subkey) : json.getJSONArray(key) );

        } catch (JSONException e) {
            throw new JSONException("Unable to get Kimono result for "+url+" . " + e.getMessage());
        }
        return results;
    }

    public static StringBuilder processJsonArray(JSONArray results, String key) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            for (int i = 0; i < results.length(); i++) {
                JSONObject course = results.getJSONObject(i);
                stringBuilder.append(course.getString(key));
                if (i < results.length() - 1) {
                    stringBuilder.append(", ");
                } else {
                    stringBuilder.append(".");
                }
            }

        } catch (JSONException e) {
            log.error(" ", e);
        }

        return stringBuilder;
    }


    public static String getRSSFeedResults(String url, String prefix) {
        StringBuilder stringBuilder = new StringBuilder();

        List<SyndEntry> entryList = getRssEntryList(url);

        if (entryList != null) {
            for (SyndEntry entry : entryList) {
                if (entry.getTitle().startsWith(prefix)) {
                    String feedResult = cleanupRSSFeed(entry.getDescription().getValue());

                    if (feedResult.isEmpty() || feedResult.length() < 10) {
                        stringBuilder.append(ERROR_NOT_AVAILABLE);
                    } else {
                        stringBuilder.append(feedResult);
                    }
                }
            }
        }

        return stringBuilder.toString();
    }


    private static List<SyndEntry> getRssEntryList(String url) {
        List<SyndEntry> entryList = null;

        try {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));

            if (feed != null) {
                entryList = (List<SyndEntry>) feed.getEntries();
            }
        } catch (Exception e) {
            log.error("Error getting feed.", e.getMessage());
        }
        return entryList;
    }

    private static String cleanupRSSFeed(String value) {
        StringBuilder stringBuilder = new StringBuilder();

        value = value.trim();
        value = value.split("\n")[0]; // remove all after row change
        String[] array = value.split("\\),");

        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i].split("[#\\(\\d]")[0].trim());
            if (i < array.length - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString().trim();
    }

}
