package com.soikea.hiplunch;


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

    public static final String GRILL_SEPARATOR = "<a><i>Paistopisteeltä:</i></a> ";

    private SyndFeed feed = null;

	public SonaattiProvider() {
		try {
			feed = ContentUtil.getRSSFeedForUrl(Constants.URL_SONAATTI);
		} catch (Exception e) {
			log.error("Error getting feed.");
		}
	}

	public String processFeed(String prefix) {
		return processFeed(prefix, false);
	}

	public String processFeed(String prefix, boolean includeGrill) {
		StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(getSonaattiFeed(prefix));

		if (includeGrill) {
            stringBuffer.append(" "+ processGrill());
        }

		return stringBuffer.toString();
	}

    public String getSonaattiFeed(String prefix) {
        StringBuffer stringBuffer = new StringBuffer();

        for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
            if (entry.getTitle().startsWith(prefix)) {
                String sonaattifeedResult = cleanUpSonaattiFeed(entry.getDescription().getValue());

                if (sonaattifeedResult.isEmpty() || sonaattifeedResult.length() < 10) {
                    stringBuffer.append(Constants.ERROR_NOT_AVAILABLE);
                } else {
                    stringBuffer.append(sonaattifeedResult);
                }
            }
        }
        if (stringBuffer.length() < 10) {
			if (prefix.equals(Constants.PREFIX_PIATO)) {
				String backup = getBackupPiatoFeed();
				stringBuffer.append(backup);
			}
			if (stringBuffer.length() < 10) {
				stringBuffer.append(Constants.ERROR_NOT_AVAILABLE);
			}
        }
        return stringBuffer.toString();
    }

	private String getBackupPiatoFeed() {
		return cleanBackupPiatoString(getGrillApiString("Huomio"));
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
		return stringBuffer.toString().trim();
	}


	public String processGrill() {
		return cleanGrillResult(getGrillApiString("paistopiste"));

	}

	public String getGrillApiString(String key) {
		StringBuffer stringBuffer = new StringBuffer();

		try {

			JSONObject json = new JSONObject(ContentUtil.getJSONContent(Constants.GRILL_API));

			JSONArray results = json.getJSONObject("results").getJSONArray("collection1");

			for (int i = 0; i < results.length(); i++) {
				JSONObject course = results.getJSONObject(i);
				stringBuffer.append(course.getString(key));
				if (i < results.length() - 1) {
					stringBuffer.append("");
				}
			}
		} catch (JSONException e) {
			log.error("Unable to process grill. " + e.getMessage(), e);
			return GRILL_SEPARATOR + " " + Constants.ERROR_NOT_AVAILABLE;
		}
		return stringBuffer.toString();

	}

    public String cleanGrillResult(String raw) {

        String cleaned = raw.trim();

        if (raw.contains("tauolla") || raw.contains("suljettu") || raw.contains("seuraavan kerran")) {

			cleaned = raw.replaceAll("\n", "---").split("---")[0];
			cleaned = GRILL_SEPARATOR + "Suljettu. (" + cleaned + "...)";
			log.debug(cleaned);
		} else {

			cleaned = cleaned.replaceAll("\n", " ");

			cleaned = cleaned.replaceAll("Paistopiste palvelee ma-pe klo .*\\d*", "");
			log.debug(cleaned);
			cleaned = cleaned.replaceAll("PAISTOPISTEELTÄ ", GRILL_SEPARATOR);
			cleaned = cleaned.replaceAll("Paistopisteellä viikolla \\d* ", GRILL_SEPARATOR);
			log.debug(cleaned);
			cleaned = cleaned.replaceAll(" \\([A-Z0-9,\\s]*\\) \\d*,\\d*\\s?€ / \\d*,\\d*\\s?€", ".");
			cleaned = cleaned.replaceAll("  ", " ");
			log.debug(cleaned);
		}
        return cleaned.trim();
    }

	public String cleanBackupPiatoString(String raw) {
		//Nettisivuillamme on ongelmia, pahoittelemme !\nLounaalla on tänään ti 31.3.\nJauhelihalasagnettea (L) \nBroileri-kookoskermakastiketta (L,G)\nJauhelihakeittoa (M,G)\nKikherne-bataattipataa (M,G)
		String cleaned = raw.trim();
		cleaned = cleaned.replaceAll("\n", " ");

		cleaned = cleaned.replaceAll("Nettisivuillamme on ongelmia, pahoittelemme ! Lounaalla on tänään", "");
		log.debug(cleaned);
		cleaned = cleaned.replaceAll(" \\([A-Z0-9,\\s]*\\)", ".");
		log.debug(cleaned);
		cleaned = cleaned.replaceAll("\\d*,\\d*\\s? / \\d*,\\d*\\s?", "");
		log.debug(cleaned);
		cleaned = cleaned.replaceAll("  ", " ");
		log.debug(cleaned);

		return cleaned.trim();
	}

}
