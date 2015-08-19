package com.soikea.hiplunch;

import com.soikea.hiplunch.domain.HipchatEnums;
import com.soikea.hiplunch.domain.HipchatMessage;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class Highlighter {

    public static void checkForHighlights(HipchatMessage hipchatMessage) {
        String[] highlights = Constants.HIGHLIGHTS;
        for (String hilight : highlights) {
            if (hipchatMessage.getMessage().contains(hilight)) {
                hipchatMessage.setColor(HipchatEnums.Color.red);
                hipchatMessage.setMessage(hipchatMessage.getMessage().replaceAll(hilight, "<b>" + hilight + "</b>"));
                hipchatMessage.setNotify(true);
            }
        }
    }
}
