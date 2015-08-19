package com.soikea.hiplunch;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 04/11/14.
 */
public class ContentUtil {
	static final Logger log = LoggerFactory.getLogger(ContentUtil.class);

	public static SyndFeed getRSSFeedForUrl(String url) {
		try {
			URL feedUrl = new URL(url);

			SyndFeedInput input = new SyndFeedInput();

			return input.build(new XmlReader(feedUrl));

		} catch (Exception e) {
			log.error("Error getting rss feed: " + e.getMessage(), e);
		}
		return null;
	}


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
}
