package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;

public abstract class AntellProvider extends Provider {

    protected abstract String getAntellIdentifier();

    protected final String ANTELL_BASEURL = "https://www.antell.fi/lounaslistat/lounaslista.html?owner=" + getAntellIdentifier() + "";

    String today = StringHelper.getWeekdayName(0);
    String tomorrow = StringHelper.getWeekdayName(1);

    protected abstract String getStartPointOverride();

    protected abstract String getEndPointOverride();

    protected String processFeed() {
        return FeedCutter.builder(ContentUtil.getUrlContents(ANTELL_BASEURL))
            .withStartPoints(today, getStartPointOverride())
            .withEndPoints(tomorrow, getEndPointOverride())
            .withRemovables("€", "&euro;",
                    "\\w+[0-9,]+\\w+",
                    "\\(.+?\\),?",
                    "<.+?>",
                    "\\d\\d?\\.\\d\\d?\\.\\:?",
                    "#"
            )
            .withSpaceables("\\s+")
            .fullProcess().trim();
    }
}