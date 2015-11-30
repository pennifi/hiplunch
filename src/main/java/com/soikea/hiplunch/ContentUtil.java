package com.soikea.hiplunch;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 04/11/14.
 */
public class ContentUtil {
    static final Logger log = LoggerFactory.getLogger(ContentUtil.class);

    public static final String ERROR_NOT_AVAILABLE = "Tietoja ei saatavilla.";

    public static JSONArray getJsonResult(String url, String key) throws JSONException {
        return getJsonResult(url, key, null);
    }

    public static JSONArray getJsonResult(String url, String key, String subkey) throws JSONException {
        JSONArray results;

        try {
            JSONObject json = new JSONObject(getUrlContents(url));

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
            entryList.stream()
                .filter(entry -> matchesTitle(entry.getTitle(), prefix))
                .forEach(entry -> {
                    String feedResult = cleanupRSSFeed(entry.getDescription().getValue());

                    if (feedResult.isEmpty() || feedResult.length() < 10) {
                        stringBuilder.append(ERROR_NOT_AVAILABLE);
                    } else {
                        stringBuilder.append(feedResult);
                    }
                });
        }
        log.debug(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String getRSSFeedResults(String url) {
        StringBuilder stringBuilder = new StringBuilder();

        List<SyndEntry> entryList = getRssEntryList(url);

        if (entryList != null) {
            entryList.stream()
                .forEach(entry -> {
                    String feedResult = entry.getDescription().getValue();

                    if (feedResult.isEmpty() || feedResult.length() < 10) {
                        stringBuilder.append(ERROR_NOT_AVAILABLE);
                    } else {
                        stringBuilder.append(feedResult);
                    }
                });
        }

        return stringBuilder.toString();
    }

    private static boolean matchesTitle(String feed, String pattern) {
        return feed.toLowerCase().startsWith(pattern.toLowerCase());
    }

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {

            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            log.error("Error getting url content for feed: {}", e.getMessage());
        }
        return content.toString();
    }

    private static List<SyndEntry> getRssEntryList(String url) {
        List<SyndEntry> entryList = null;

        try {
            String content = cleanupRawURLFeed(getUrlContents(url));

            InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            XmlReader reader = new XmlReader(stream);
            SyndFeedInput input = new SyndFeedInput();

            SyndFeed feed = input.build(reader);

            if (feed != null) {
                entryList = feed.getEntries();
            }

        } catch (IOException e) {
            log.error("Error getting feed: IOException: {}", e.getMessage());
        } catch (FeedException e) {
            log.error("Error getting feed: FeedException: {}", e.getMessage());
        }

        return entryList;
    }

    private static String cleanupRawURLFeed(String value) {
        return value.replaceAll("</br>", "");
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