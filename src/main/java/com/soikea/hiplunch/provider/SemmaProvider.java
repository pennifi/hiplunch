package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;

public abstract class SemmaProvider extends Provider {

    public final String SEMMA_FI_RSSFEED = "https://www.semma.fi/modules/MenuRss/MenuRss/CurrentDay?costNumber=" + getSemmaId() + "&language=fi";

    protected abstract String getSemmaId();

    public String processFeed() {
        return FeedCutter.builder(ContentUtil.getRSSFeedResults(SEMMA_FI_RSSFEED))
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
