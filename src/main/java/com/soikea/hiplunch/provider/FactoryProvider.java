package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;

public abstract class FactoryProvider extends Provider {

    protected abstract String getFactoryIdentifier();

    protected final String FACTORY_BASEURL = "http://www.ravintolafactory.com/lounasravintolat/ravintolat/" + getFactoryIdentifier() + "/";

    String today = StringHelper.getWeekdayName(1) + "";
    String tomorrow = StringHelper.getWeekdayName(2) + "";

    protected abstract String getStartPointOverride();

    protected abstract String getEndPointOverride();

    protected String processFeed() {
        return FeedCutter.builder(ContentUtil.getUrlContents(FACTORY_BASEURL))
                .withStartPoints(today, getStartPointOverride())
                .withEndPoints(tomorrow, getEndPointOverride())
                .withRemovables("€",
                        "\\(.+\\)",
                        "\\w+[0-9,]+\\w+",
                        "<.+?>",
                        "\\d\\d?\\.\\d\\d?\\.\\:?"
                )
                .withSpaceables("\\s+")
                .fullProcess().trim();
    }
}
