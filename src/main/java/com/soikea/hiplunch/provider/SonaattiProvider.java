package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;

public abstract class SonaattiProvider extends Provider {

    private static final String GRILL_SEPARATOR = "<a><i>Paistopisteeltä:</i></a> ";

    private static final String GRILL_PATTERN = "Paistopiste(el[lt]ä)?:";

    public static final String HTTP_WWW_SONAATTI_FI_RSSFEED = "http://www.sonaatti.fi/rssfeed/";

    protected abstract Boolean hasGrill();

    public String processFeed() {
        StringBuilder stringBuilder = new StringBuilder();

        String sonaattiRssFeedResult = ContentUtil.getRSSFeedResults(HTTP_WWW_SONAATTI_FI_RSSFEED, getId());

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

    private StringBuilder formatGrillString(StringBuilder raw) {
        return new StringBuilder(raw.toString().replaceAll(GRILL_PATTERN, GRILL_SEPARATOR));
    }
}
