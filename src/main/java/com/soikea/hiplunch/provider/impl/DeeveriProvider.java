package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class DeeveriProvider extends Provider {

    @Override
    protected String processFeed() {

        String today = StringUtils.capitalize(StringUtils.lowerCase(StringHelper.getWeekdayName(0)));
        String tomorrow = StringUtils.capitalize(StringUtils.lowerCase(StringHelper.getWeekdayName(1)));

        return FeedCutter.builder(ContentUtil.getUrlContents(getMessageUrl(), "utf-8"))
            .withStartPoints(today, "Omasi varmistat tilaamalla edellisenä päivänä")
            .withEndPoints(tomorrow, "Aamupala")
            .withRemovables("<.+?>", "\\w*[0-9,\\.]+\\w*", "&euro;")
            .withSpaceables("\\s\\s*")
            .fullProcess().trim();
    }

    @Override
    public String getId() {
        return "deeveri";
    }

    @Override
    protected String getMessageUrl() {
        return "https://deeveri.fi/";
    }

    @Override
    public String getName() {
        return "Deeveri";
    }
}
