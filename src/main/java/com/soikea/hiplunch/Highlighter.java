package com.soikea.hiplunch;

import com.soikea.hiplunch.domain.HipchatEnums;
import com.soikea.hiplunch.domain.HipchatMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class Highlighter {

    public static void checkForHighlights(HipchatMessage hipchatMessage) {
        String[] highlights = Constants.HIGHLIGHTS;
        for (String hilight : highlights) {
            if (StringUtils.containsIgnoreCase(hipchatMessage.getMessage(), hilight)) {
                hipchatMessage.setColor(HipchatEnums.Color.red);
                hipchatMessage.setMessage(hipchatMessage.getMessage().replaceAll("(?i)"+ Pattern.quote(hilight), "<b>" + hilight + "</b>"));
                hipchatMessage.setNotify(true);
            }
        }
    }
}
