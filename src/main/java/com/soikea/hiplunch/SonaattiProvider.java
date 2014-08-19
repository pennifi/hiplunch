package com.soikea.hiplunch;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.List;

/**
 * Created by penni on 18/08/14.
 */
public class SonaattiProvider {

	private SyndFeed feed = null;

	public SonaattiProvider() {
		try {
			feed = getSyndFeedForUrl(Constants.URL_SONAATTI);
		} catch (Exception e) {
			System.out.println("Error getting feed");
		}
	}

	private SyndFeed getSyndFeedForUrl(String url) {
		try {
			URL feedUrl = new URL(url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));

			return feed;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}


	public String processFeed(String prefix) {
		StringBuffer stringBuffer = new StringBuffer();

		for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
			if (entry.getTitle().startsWith(prefix)) {
				stringBuffer.append(cleanUpSonaattiFeed(entry.getDescription().getValue()));
			}
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

			stringBuffer.append(array[i].split("#")[0].trim());
			if (i < array.length - 1) {
				stringBuffer.append(", ");
			}
		}
		return stringBuffer.toString();
	}

}
