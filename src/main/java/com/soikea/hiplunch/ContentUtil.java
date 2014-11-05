package com.soikea.hiplunch;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

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

	public static SyndFeed getRSSFeedForUrl(String url) {
		try {
			URL feedUrl = new URL(url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));

			return feed;

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}


	public static String getJSONContent(String urlString) {
		StringBuffer stringBuffer = new StringBuffer();

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
			System.out.println("ERROR: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}
}
