package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.Highlighter;
import com.soikea.hiplunch.domain.HipchatMessage;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public abstract class BaseProvider {

    public HipchatMessage processMessage() {
        HipchatMessage hipchatMessage = new HipchatMessage(
            "<a href=\"" + getMessageUrl() + "\">" + getPrefix() + "</a>: " + processFeed());
        Highlighter.checkForHighlights(hipchatMessage);
        return hipchatMessage;
    }

    public abstract String processFeed();

    protected abstract String getMessageUrl();

    protected abstract String getPrefix();


}
