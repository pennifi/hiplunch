package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;

public abstract class SonaattiProvider extends Provider {

    public final String SONAATTI_FI_RSSFEED = "https://www.sonaatti.fi/modules/MenuRss/MenuRss/CurrentDay?costNumber=" + getSonaattiId() + "&language=fi";

    protected abstract String getSonaattiId();

    public String processFeed() {
        return FeedCutter.builder(ContentUtil.getRSSFeedResults(SONAATTI_FI_RSSFEED))
            .withRemovables("<br>",
                    "TEEMALOUNAS:",
                "KASVISLOUNAS:",
                "\\(.+?\\)",
                "KEITTOLOUNAS:",
                "SALAATTILOUNAS:",
                "JÄLKIRUOKA:", "LOUNAS:", "()"
            )
            .withSpaceables("\\s+")
            .fullProcess().trim();
    }
}
