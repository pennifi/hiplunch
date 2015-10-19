package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 18/08/14.
 */
public abstract class BaseSonaattiProvider extends BaseProvider {

    public static final String GRILL_SEPARATOR = "<a><i>Paistopisteeltä:</i></a> ";

    public static final String GRILL_PATTERN = "Paistopiste(el[lt]ä)?:";

    protected abstract Boolean hasGrill();

    protected String getRssUrl() {  return "http://www.sonaatti.fi/rssfeed/"; }

    protected abstract String getRSSTitleId();

    public String processFeed() {
		StringBuilder stringBuilder = new StringBuilder();

        String sonaattiRssFeedResult = ContentUtil.getRSSFeedResults(getRssUrl(), getId());

        if (sonaattiRssFeedResult.length() < 10) {
            stringBuilder.append(ContentUtil.ERROR_NOT_AVAILABLE);

        } else {
            stringBuilder.append(sonaattiRssFeedResult);
        }

        if (hasGrill()) {
            stringBuilder = formatGrillString(stringBuilder);
        }

        return stringBuilder.toString();
	}

    public StringBuilder formatGrillString(StringBuilder raw) {
        return new StringBuilder(raw.toString().replaceAll(GRILL_PATTERN, GRILL_SEPARATOR));
    }

}
