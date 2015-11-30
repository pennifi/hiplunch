package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public abstract class FazerProvider extends Provider {

    protected abstract String getFazerId();

    protected String getRssUrl() {  return "http://www.fazer.fi/api/location/menurss/current?pageId="+getFazerId()+"&language=fi"; }

    public String readRawFeed() {
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
