package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;

import java.text.MessageFormat;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public abstract class FazerProvider extends Provider {

    protected abstract String getFazerId();

    private String getRssUrl() {  return MessageFormat
        .format("http://www.fazer.fi/api/location/menurss/current?pageId={0}&language=fi", getFazerId()); }

    protected String readRawFeed() {
        StringBuilder stringBuilder = new StringBuilder();

        String feedResults = ContentUtil.getRSSFeedResults(getRssUrl());

        if (feedResults.length() < 10) {
            stringBuilder.append(ContentUtil.ERROR_NOT_AVAILABLE);

        } else {
            stringBuilder.append(feedResults);
        }

        return stringBuilder.toString();
    }
}
