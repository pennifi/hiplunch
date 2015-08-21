package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 18/08/14.
 */
public abstract class BaseSonaattiKimonoProvider extends BaseKimonoProvider {
    static final Logger log = LoggerFactory.getLogger(BaseSonaattiKimonoProvider.class);

    public static final String GRILL_SEPARATOR = "<a><i>Paistopisteeltä:</i></a> ";

    protected abstract Boolean hasGrill();

    protected abstract String getRssUrl();

	public String processFeed() {
		StringBuilder stringBuilder = new StringBuilder();

        String sonaattiRssFeedResult = ContentUtil.getRSSFeedResults(getRssUrl(), getPrefix());

        if (sonaattiRssFeedResult.length() < 10) {
            log.debug("Using backup source for " + getPrefix() + ".");
            stringBuilder.append(getSonaattiFeedFromKimonoAPI());

            if (stringBuilder.length() < 10) {
                log.debug("Backup source for " + getPrefix() + " failed also.");
                stringBuilder.append(ContentUtil.ERROR_NOT_AVAILABLE);
            }

        } else {
            stringBuilder.append(sonaattiRssFeedResult);
        }

        if (hasGrill()) {
            stringBuilder.append(" ").append(cleanGrillResult(getGrillApiString()));
        }

		return stringBuilder.toString();
	}

	private String getSonaattiFeedFromKimonoAPI() {

		StringBuilder stringBuilder = new StringBuilder();

		try {
            stringBuilder.append(
                ContentUtil.processJsonArray(
                    ContentUtil.getJsonResult(KIMONO_URL, "results", "menu")
                    , "course"));

        } catch (JSONException e) {
            log.error(" ", e);
            return "";
        }

		return stringBuilder.toString();
	}

	public String getGrillApiString() {
		StringBuilder stringBuilder = new StringBuilder();

		try {
            stringBuilder.append(
                ContentUtil.processJsonArray(
                    ContentUtil.getJsonResult(KIMONO_URL, "results", "special")
                    , "grill"));

		} catch (JSONException e) {
			return GRILL_SEPARATOR + " " + ContentUtil.ERROR_NOT_AVAILABLE;
		}

		return stringBuilder.toString();
	}


    public String cleanGrillResult(String raw) {

        String cleaned = raw.trim();

        if (raw.contains("tauolla") || raw.contains("suljettu") || raw.contains("seuraavan kerran")) {

			cleaned = raw.replaceAll("\n", "---").split("---")[0];
			cleaned = GRILL_SEPARATOR + "Suljettu. (" + cleaned + "...)";
			log.debug(cleaned);

		} else {

			cleaned = cleaned.replaceAll("\n", " ");

			cleaned = cleaned.replaceAll("[\\.,\\s]*[Pp]aistopiste palvelee [a-z]*\\s?-\\s?[a-z]* klo .*\\d*", "");
			log.debug(cleaned);
			cleaned = cleaned.replaceAll("PAISTOPISTEELTÄ ", GRILL_SEPARATOR);
			cleaned = cleaned.replaceAll("Paistopisteellä viikolla \\d* ", GRILL_SEPARATOR);
			cleaned = cleaned.replaceAll("Paistopisteeltä\\s?: ", GRILL_SEPARATOR);
			log.debug(cleaned);
			cleaned = cleaned.replaceAll("\\s?\\([A-Z0-9,\\s]*\\)\\s?\\d*,\\d*\\s?€\\s?/\\s?\\d*,\\d*\\s?€", ".");
			cleaned = cleaned.replaceAll("\\s\\s?", " ");
			log.debug(cleaned);
		}
        return cleaned.trim();
    }


}
