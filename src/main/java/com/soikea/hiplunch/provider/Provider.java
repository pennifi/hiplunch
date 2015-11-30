package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.Highlighter;
import com.soikea.hiplunch.domain.HipchatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public abstract class Provider {
    public final Logger log = LoggerFactory.getLogger(this.getClass());

    public HipchatMessage processMessage() {
        HipchatMessage hipchatMessage = new HipchatMessage(
            "<a href=\"" + getMessageUrl() + "\">" + getPrefix() + "</a>: " + processFeed());

        Highlighter.checkForHighlights(hipchatMessage);
        return hipchatMessage;
    }

    protected abstract String processFeed();

    public abstract String getId();

    protected abstract String getMessageUrl();

    protected abstract String getPrefix();

}
