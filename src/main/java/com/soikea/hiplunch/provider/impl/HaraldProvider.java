package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;
import org.apache.commons.lang3.StringUtils;

public class HaraldProvider extends Provider {

    @Override
    protected String processFeed() {

        String today = StringUtils.lowerCase(StringHelper.getWeekdayName(0));
        String tomorrow = StringUtils.lowerCase(StringHelper.getWeekdayName(1));

        return FeedCutter.builder(ContentUtil.getUrlContents(getMessageUrl(), "iso-8859-1"))
            .withStartPoints(today, "lounaslistaTable\">")
            .withEndPoints(tomorrow, "Pidätämme oikeudet muutoksiin")
            .withRemovables("<.+?>", "\\w*[0-9,\\.]+\\w*", "&euro;")
            .withSpaceables("\\s\\s*")
            .fullProcess().trim();
    }

    @Override
    public String getId() {
        return "harald";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www2.ravintolaharald.fi/ruoka--ja-juomalistat/lounas";
    }

    @Override
    public String getName() {
        return "Harald";
    }
}
