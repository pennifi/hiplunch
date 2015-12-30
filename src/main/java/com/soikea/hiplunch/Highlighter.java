package com.soikea.hiplunch;

import com.soikea.hiplunch.domain.HipchatEnums;
import com.soikea.hiplunch.domain.HipchatMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class Highlighter {

    private String[] hilights;

    public void checkForHighlights(HipchatMessage hipchatMessage) {
        for (String hilight : getHilights()) {
            if (StringUtils.containsIgnoreCase(hipchatMessage.getMessage(), hilight)) {
                hipchatMessage.setColor(HipchatEnums.Color.red);
                hipchatMessage.setMessage(hipchatMessage.getMessage().replaceAll("(?i)("+ Pattern.quote(hilight)+")", "<b>$1</b>"));
                hipchatMessage.setNotify(true);
            }
        }
    }

    public String[] getHilights() {
        if (hilights == null) {
            hilights = Constants.HIGHLIGHTS;
        }
        return hilights;
    }

    public void setHilights(String[] hilights) {
        this.hilights = hilights;
    }

}
