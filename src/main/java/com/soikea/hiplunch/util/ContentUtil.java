package com.soikea.hiplunch.util;

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

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ContentUtil {
    private static final Logger log = LoggerFactory.getLogger(ContentUtil.class);

    public static final String ERROR_NOT_AVAILABLE = "Tietoja ei saatavilla.";

    public static JSONArray getJsonResult(String url, String key) throws JSONException {
        JSONArray results;

        try {
            JSONObject json = new JSONObject(getUrlContents(url));
            results = json.getJSONArray(key);
        } catch (JSONException e) {
            throw new JSONException(String.format("Unable to get Json result for %s : %s", url, e.getMessage()));
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
            log.error("processJsonArray exception: {}", e.getMessage());
        }

        return stringBuilder;
    }

    public static String getRSSFeedResults(String url) {
        StringBuilder stringBuilder = new StringBuilder();

        List<SyndEntry> entryList = getRssEntryList(url);

        if (entryList != null) {
            entryList.stream().forEach(entry -> {
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

    public static String getUrlContents(String theUrl) {
        return getUrlContents(theUrl, "UTF-8");
    }

    public static String getUrlContents(String theUrl, String charsetName) {
        StringBuilder content = new StringBuilder();

        try {
            HttpURLConnection urlConnection;
            if (theUrl.startsWith("https")) {
                urlConnection = openHttpsConnection(theUrl);
            } else {
                urlConnection = openHttpConnection(theUrl);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), charsetName));
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

    private static HttpsURLConnection openHttpsConnection(String theUrl) throws Exception {
        URL url = new URL(theUrl);
        HttpsURLConnection urlConnection;
        SSLContext sc = SSLContext.getInstance("SSL");

        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                // do nothing
            }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                // do nothing
            }
        } };

        // Install the all-trusting trust manager
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setInstanceFollowRedirects(true);
        urlConnection.setSSLSocketFactory(sc.getSocketFactory());

        int status = urlConnection.getResponseCode();
        if (status != HttpsURLConnection.HTTP_OK) {
            if (status == HttpsURLConnection.HTTP_MOVED_TEMP
                || status == HttpsURLConnection.HTTP_MOVED_PERM
                || status == HttpsURLConnection.HTTP_SEE_OTHER) {

                String newUrl = urlConnection.getHeaderField("Location");
                log.debug("Redirect to URL : {}", newUrl);
                return openHttpsConnection(newUrl);
            }
        }
        return urlConnection;
    }

    private static HttpURLConnection openHttpConnection(String theUrl) throws Exception {
        URL url = new URL(theUrl);
        HttpURLConnection urlConnection;

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setInstanceFollowRedirects(true);

        int status = urlConnection.getResponseCode();
        if (status != HttpsURLConnection.HTTP_OK) {
            if (status == HttpsURLConnection.HTTP_MOVED_TEMP
                || status == HttpsURLConnection.HTTP_MOVED_PERM
                || status == HttpsURLConnection.HTTP_SEE_OTHER) {

                String newUrl = urlConnection.getHeaderField("Location");
                log.debug("Redirect to URL : {}", newUrl);
                return openHttpConnection(newUrl);
            }
        }
        return urlConnection;
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
        } catch (IOException | FeedException e) {
            log.error("Error getting feed: " + e.getClass().getSimpleName()+ " {}", e.getMessage());
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

    public static String formatDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
    }
}
