package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.FazerProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public class FiiluProvider extends FazerProvider {

    @Override
    public String getId() {
        return "fiilu";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www.fazer.fi/kahvilat-ja-leipomot/ravintolat/byfazer/ravintola-fiilu/";
    }

    @Override
    public String getName() {
        return "Fazer Fiilu";
    }

    @Override
    protected String getFazerId() {
        return "29801";
    }

    @Override
    protected String processFeed() {
        String result = readRawFeed();

        String today = StringHelper.getWeekdayName(0);
        String tomorrow = StringHelper.getWeekdayName(1);

        result = StringHelper.stripOneDayFromMenu(result, today, tomorrow, "Lounas 7,10 opiskelijakortilla");
        result = result.replaceAll("<.+?>", "");

        result = result.replaceAll("<br />", "");
        result = result.replaceAll("&nbsp;", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\s\\d+?,\\d+", ":</i> ");
        result = result.replaceAll("\\n", " <i>");
        result = result.replaceAll("  ", ", ");
        result = result.replaceAll(">,", ">");
        result = result.replaceAll(",\\s?<", " <");

        result = result.replaceAll("<i>Deli salaattibaari:</i>", "");
        result = result.replaceAll("Päivän keittolounas", "Keitto");
        result = result.replaceAll("Nordic Buffet", "Buffet");
        result = result.replaceAll("Street gourmet grilli", "Grilli");

        return result;
    }
}
