package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;

public abstract class LounaatInfoProvider extends Provider {

    protected abstract String getLounaatInfoIdentifier();

    private final String LOUNAAT_BASEURL = "https://www.lounaat.info/lounas/" + getLounaatInfoIdentifier() + "";

    String today = StringHelper.getWeekdayName(0) + "na";
    String tomorrow = StringHelper.getWeekdayName(1) + "na";

    protected abstract String getStartPointOverride();

    protected abstract String getEndPointOverride();

    protected String processFeed() {
        return FeedCutter.builder(ContentUtil.getUrlContents(LOUNAAT_BASEURL))
            .withStartPoints(today, getStartPointOverride())
            .withEndPoints(tomorrow, getEndPointOverride(), "<!-- Boksi -->")
            .withRemovables("€", "Alkuruokana",
                "\\w+[0-9,]+\\w+",
                "<a href=\"#[mlGg]\".+?</a>",
                "<div class=\"offer\">.+?</div>",
                "<div class=\"offer-block\".+?</div>",
                "<.+?>",
                "\\d\\d?\\.\\d\\d?\\.\\:?",
                "Lounas kello 11-14",
                "&euro;"
                )
            .withSpaceables("\\s+")
            .fullProcess().trim();
    }
}