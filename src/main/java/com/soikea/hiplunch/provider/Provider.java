package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.Highlighter;
import com.soikea.hiplunch.domain.HipchatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Provider {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Highlighter hilighter = new Highlighter();

    public HipchatMessage processMessage() {
        HipchatMessage hipchatMessage = new HipchatMessage(
            "<a href=\"" + getMessageUrl() + "\">" + getName() + "</a>: " + processFeed());

        hilighter.checkForHighlights(hipchatMessage);
        return hipchatMessage;
    }

    protected abstract String processFeed();

    public abstract String getId();

    protected abstract String getMessageUrl();

    public abstract String getName();
}
