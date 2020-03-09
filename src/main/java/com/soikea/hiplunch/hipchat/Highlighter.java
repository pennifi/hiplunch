package com.soikea.hiplunch.hipchat;

import com.soikea.hiplunch.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Highlighter {

    private String[] hilights;

    public void checkForHighlights(HipchatMessage hipchatMessage) {
        for (String hilight : getHilights()) {
            if (StringUtils.containsIgnoreCase(hipchatMessage.getMessage(), hilight)) {
                hipchatMessage.setColor(HipchatEnums.Color.red);
                hipchatMessage.setMessage(
                    hipchatMessage.getMessage().replaceAll("(?i)(" + Pattern.quote(hilight) + ")", "<b>$1</b>"));
                hipchatMessage.setNotify(true);
            }
        }
    }

    public String hilight(String string) {
        for (String hilight : getHilights()) {
            if (StringUtils.containsIgnoreCase(string, hilight)) {
                return string.replaceAll("(?i)(" + Pattern.quote(hilight) + ")", "**$1**");
            }
        }
        return string;
    }

    public String hilightSlack(String string) {
        for (String hilight : getHilights()) {
            if (StringUtils.containsIgnoreCase(string, hilight)) {
                return string.replaceAll("(?i)(" + Pattern.quote(hilight) + ")", "＊$1＊");
            }
        }
        return string;
    }

    private String[] getHilights() {
        if (hilights == null) {
            hilights = Constants.HIGHLIGHTS;
        }
        return hilights;
    }

    public void setHilights(String[] hilights) {
        this.hilights = hilights;
    }
}
