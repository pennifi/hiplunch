package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.FazerProvider;

import java.util.Arrays;

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
        String feed = readRawFeed();

        String today =  StringHelper.getWeekdayName(0);
        String tomorrow = StringHelper.getWeekdayName(1);

        feed = FeedCutter.builder(feed)
            .withStartPoints(today, "jälkiruoan ja kahvin.")
            .withEndPoints(tomorrow, "Lounas 7,20 opiskelijakortilla")
            .withSpaceables("\\s\\s+?")
            .withRemovables("\\n", "&nbsp;", "<.+?>", "\\(.+?\\)")
            .startProcess()
            .replace(":</i> ", Arrays.asList("\\s\\d+?,\\d+"))
            .replace(" <i>", Arrays.asList("\\n"))
            .replace(", ", Arrays.asList("  "))
            .replace(">", Arrays.asList(">,"))
            .replace(" <", Arrays.asList(",\\s?<"))
            .cleanUp()
            .toString();

        feed = feed.replaceAll("Deli salaattibaari:", "");
        feed = feed.replaceAll("Päivän keittolounas", "Keitto");
        feed = feed.replaceAll("Nordic Buffet", "Buffet");
        feed = feed.replaceAll("Street gourmet grilli", "Grilli");

        return feed;
    }
}
