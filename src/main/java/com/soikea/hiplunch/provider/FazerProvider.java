package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.StringHelper;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public abstract class FazerProvider extends Provider {

    protected abstract String getFazerId();

    protected String getRssUrl() {  return "http://www.fazer.fi/api/location/menurss/current?pageId="+getFazerId()+"&language=fi"; }

    public String processFeed() {
        StringBuilder stringBuilder = new StringBuilder();

        String feedResults = ContentUtil.getRSSFeedResults(getRssUrl());

        if (feedResults.length() < 10) {
            stringBuilder.append(ContentUtil.ERROR_NOT_AVAILABLE);

        } else {
            stringBuilder.append(feedResults);
        }

        return stripTodayFromFazerFeed(stringBuilder.toString());
    }

    private String stripTodayFromFazerFeed(String s) {
        String result = StringHelper.stripOneDayFromMenu(s, false, "Kela tukee");
        result = result.replaceAll("<.+?>", "");
        result = result.replaceAll("Lounas 7,10 opiskelijakortilla 2.60Kela tukee korkeakouluopiskelijoiden ruokailua 1,94 euroa/ ateria.", "");

        result = result.replaceAll("<br />", "");
        result = result.replaceAll("&nbsp;", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\s\\d+?,\\d+", ":</strong> ");
        result = result.replaceAll("\\n", " <strong>");
        result = result.replaceAll("  ", ", ");
        result = result.replaceAll(">,", ">");
        result = result.replaceAll(",\\s?<", " <");

        result = result.replaceAll("<strong>Deli salaattibaari:</strong>", "");
        result = result.replaceAll("Päivän keittolounas", "Keitto");
        result = result.replaceAll("Nordic Buffet", "Buffet");
        result = result.replaceAll("Street gourmet grilli", "Grilli");

        return result;
    }

}
