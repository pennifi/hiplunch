package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.Constants;
import com.soikea.hiplunch.ContentUtil;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by penni on 18/08/14.
 */
public class SonaattiProvider {
    static final Logger log = LoggerFactory.getLogger(SonaattiProvider.class);

	private List<SyndEntry> entryList;

	public SonaattiProvider() {
		try {
			SyndFeed feed = ContentUtil.getRSSFeedForUrl(Constants.URL_SONAATTI);
			if (feed != null) {
				entryList = (List<SyndEntry>) feed.getEntries();
			}
		} catch (Exception e) {
			log.error("Error getting feed.", e);
		}
	}

	public String processFeed(String prefix) {
		return processFeed(prefix, false);
	}

	public String processFeed(String prefix, boolean includeGrill) {
		StringBuilder stringBuffer = new StringBuilder();

		String sonaattiRssFeedResult = getSonaattiFeedFromRSS(prefix);

		if (sonaattiRssFeedResult.length() < 10) {
			log.debug("Using backup source for " + prefix + ".");
			stringBuffer.append(getSonaattiFeedFromKimonoAPI(prefix));

			if (stringBuffer.length() < 10) {
				log.debug("Backup source for " + prefix + " failed also.");
				stringBuffer.append(Constants.ERROR_NOT_AVAILABLE);
			}

		} else {
			stringBuffer.append(sonaattiRssFeedResult);
		}

		if (includeGrill) {
            stringBuffer.append(" ").append(processGrill());
        }

		return stringBuffer.toString();
	}

    public String getSonaattiFeedFromRSS(String prefix) {
        StringBuilder stringBuilder = new StringBuilder();
		if (entryList != null) {
			for (SyndEntry entry : entryList) {
				if (entry.getTitle().startsWith(prefix)) {
					String sonaattifeedResult = cleanUpSonaattiFeed(entry.getDescription().getValue());

					if (sonaattifeedResult.isEmpty() || sonaattifeedResult.length() < 10) {
						stringBuilder.append(Constants.ERROR_NOT_AVAILABLE);
					} else {
						stringBuilder.append(sonaattifeedResult);
					}
				}
			}
		}
        return stringBuilder.toString();
    }

	private String getSonaattiFeedFromKimonoAPI(String prefix) {

		StringBuilder stringBuilder = new StringBuilder();
		String kimonoUrl = "";
		if (prefix.equals(Constants.PREFIX_PIATO)) {
			kimonoUrl = Constants.KIMONO_URL_PIATO;
 		} else if (prefix.equals(Constants.PREFIX_WILHELMIINA)) {
			kimonoUrl = Constants.KIMONO_URL_WILHELMIINA;
		}

		try {
			JSONArray results = getKimonoApiResult(kimonoUrl, "results", "menu");

				for (int i = 0; i < results.length(); i++) {
				JSONObject course = results.getJSONObject(i);
				stringBuilder.append(course.getString("course"));
				if (i < results.length() - 1) {
					stringBuilder.append(", ");
				} else {
					stringBuilder.append(".");
				}
			}

		} catch (JSONException e) {
			log.error(" ", e);
			return "";
		}

		return stringBuilder.toString();

	}



	public String processGrill() {
		return cleanGrillResult(getGrillApiString("grill"));
	}

	public JSONArray getKimonoApiResult(String url, String key, String subkey) throws JSONException{
		JSONArray results = null;

		try {
			JSONObject json = new JSONObject(ContentUtil.getJSONContent(url));
			results = json.getJSONObject(key).getJSONArray(subkey);
		} catch (JSONException e) {
			throw new JSONException("Unable to get Kimono result for "+url+" . " + e.getMessage());
		}
		return results;

	}

	public String getGrillApiString(String key) {
		StringBuilder stringBuilder = new StringBuilder();

		try {
			JSONArray results = getKimonoApiResult(Constants.KIMONO_URL_PIATO, "results", "special");

			for (int i = 0; i < results.length(); i++) {
				JSONObject course = results.getJSONObject(i);
				stringBuilder.append(course.getString(key));
				if (i < results.length() - 1) {
					stringBuilder.append("");
				}
			}
		} catch (JSONException e) {
			return Constants.GRILL_SEPARATOR + " " + Constants.ERROR_NOT_AVAILABLE;
		}

		return stringBuilder.toString();
	}

    public String cleanGrillResult(String raw) {

        String cleaned = raw.trim();

        if (raw.contains("tauolla") || raw.contains("suljettu") || raw.contains("seuraavan kerran")) {

			cleaned = raw.replaceAll("\n", "---").split("---")[0];
			cleaned = Constants.GRILL_SEPARATOR + "Suljettu. (" + cleaned + "...)";
			log.debug(cleaned);
		} else {

			cleaned = cleaned.replaceAll("\n", " ");

			cleaned = cleaned.replaceAll("[\\.,\\s]*[Pp]aistopiste palvelee [a-z]*\\s?-\\s?[a-z]* klo .*\\d*", "");
			log.debug(cleaned);
			cleaned = cleaned.replaceAll("PAISTOPISTEELTÄ ", Constants.GRILL_SEPARATOR);
			cleaned = cleaned.replaceAll("Paistopisteellä viikolla \\d* ", Constants.GRILL_SEPARATOR);
			cleaned = cleaned.replaceAll("Paistopisteeltä\\s?: ", Constants.GRILL_SEPARATOR);
			log.debug(cleaned);
			cleaned = cleaned.replaceAll("\\s?\\([A-Z0-9,\\s]*\\)\\s?\\d*,\\d*\\s?€\\s?/\\s?\\d*,\\d*\\s?€", ".");
			cleaned = cleaned.replaceAll("  ", " ");
			log.debug(cleaned);
		}
        return cleaned.trim();
    }

	private String cleanUpSonaattiFeed(String value) {
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
