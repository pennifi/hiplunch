package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.hipchat.Highlighter;
import com.soikea.hiplunch.hipchat.HipchatMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Provider {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Highlighter hilighter = new Highlighter();

    public HipchatMessage processMessageForHipchat() {
        HipchatMessage hipchatMessage = new HipchatMessage(
            "<a href=\"" + getMessageUrl() + "\">" + getName() + "</a>: " + processFeed());

        hilighter.checkForHighlights(hipchatMessage);
        return hipchatMessage;
    }

    public String processMessageForTeams() {
        String s = String.format("* [%s](%S): %s", getName(), getMessageUrl(), StringUtils.trim(processFeed()));
        return hilighter.hilight(s);
    }


    protected abstract String processFeed();

    public abstract String getId();

    protected abstract String getMessageUrl();

    public abstract String getName();
}
