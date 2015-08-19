package com.soikea.hiplunch;

import com.soikea.hiplunch.domain.HipchatEnums;
import com.soikea.hiplunch.domain.HipchatMessage;
import com.soikea.hiplunch.provider.SodexoProvider;
import com.soikea.hiplunch.provider.SonaattiProvider;

/**
 * Created by penni on 19/08/14.
 */
public class Hiplunch {

    private static SonaattiProvider sonaattiProvider = new SonaattiProvider();
    private static SodexoProvider sodexoProvider = new SodexoProvider();

    public static HipchatMessage getSodexoMessage() {
        HipchatMessage hipchatMessage = new HipchatMessage(
                "<a href=\"" + Constants.MESSAGE_URL_SODEXO + "\">" + Constants.PREFIX_SODEXO + "</a>: "
                        + sodexoProvider.processFeed());
        checkForHighlights(hipchatMessage);
        return hipchatMessage;
    }

    public static HipchatMessage getWilhelmiinaMessage() {
        HipchatMessage hipchatMessage = new HipchatMessage(
                "<a href=\"" + Constants.MESSAGE_URL_WILHELMIINA + "\">" + Constants.PREFIX_WILHELMIINA + "</a>: "
                        + sonaattiProvider.processFeed(Constants.PREFIX_WILHELMIINA));
        checkForHighlights(hipchatMessage);
        return hipchatMessage;
    }

    public static HipchatMessage getPiatoMessage() {
        HipchatMessage hipchatMessage = new HipchatMessage(
                "<a href=\"" + Constants.MESSAGE_URL_PIATO + "\">" + Constants.PREFIX_PIATO + "</a>: "
                        + sonaattiProvider.processFeed(Constants.PREFIX_PIATO, true));

        checkForHighlights(hipchatMessage);
        return hipchatMessage;
    }

    private static void checkForHighlights(HipchatMessage hipchatMessage) {
        String[] highlights = Constants.HIGHLIGHTS;
        for (String hilight : highlights) {
            if (hipchatMessage.getMessage().contains(hilight)) {
                hipchatMessage.setColor(HipchatEnums.Color.red);
                hipchatMessage.setMessage(hipchatMessage.getMessage().replaceAll(hilight, "<b>" + hilight + "</b>"));
                hipchatMessage.setNotify(true);
            }
        }
    }

    public static void main(String[] args) {

        HipChatter hipChatter = new HipChatter();
        hipChatter.sendMessage(getSodexoMessage());
        hipChatter.sendMessage(getWilhelmiinaMessage());
        hipChatter.sendMessage(getPiatoMessage());

    }
}
