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

            stringBuilder = formatGrillString(stringBuilder);
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

    public StringBuilder formatGrillString(StringBuilder raw) {
        return new StringBuilder(raw.toString().replaceAll("Paistopiste:", GRILL_SEPARATOR));
    }

}
