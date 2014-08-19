package com.soikea.hiplunch;

/**
 * Created by penni on 19/08/14.
 */
public class Hiplunch {

	private static SonaattiProvider sonaattiProvider = new SonaattiProvider();
	private static SodexoProvider sodexoProvider = new SodexoProvider();

	private static HipchatMessage getSodexoMessage() {
		HipchatMessage hipchatMessage = new HipchatMessage(Constants.PREFIX_SODEXO + ": " + sodexoProvider.processFeed());
		checkForHighlights(hipchatMessage);
		return hipchatMessage;
	}

	private static HipchatMessage getWilhelmiinaMessage() {
		HipchatMessage hipchatMessage = new HipchatMessage(Constants.PREFIX_WILHELMIINA + ": " + sonaattiProvider.processFeed(Constants.PREFIX_WILHELMIINA));
		checkForHighlights(hipchatMessage);
		return hipchatMessage;
	}

	private static HipchatMessage getPiatoMessage() {
		HipchatMessage hipchatMessage = new HipchatMessage(Constants.PREFIX_PIATO + ": " + sonaattiProvider.processFeed(Constants.PREFIX_PIATO));
		checkForHighlights(hipchatMessage);
		return hipchatMessage;
	}

	private static void checkForHighlights(HipchatMessage hipchatMessage) {
		String[] highlights = Constants.HIGHLIGHTS;
		for (String hilight : highlights) {
			if (hipchatMessage.getMessage().contains(hilight)) {
				hipchatMessage.setColor(HipchatEnums.Color.red);
				hipchatMessage.setMessage(hipchatMessage.getMessage()
						.replaceAll(hilight, "<b>" + hilight + "</b>"));
				hipchatMessage.setNotify(true);
			}
		}
	}

	public static void main( String[] args ) {

		HipChatter hipChatter = new HipChatter();
		hipChatter.sendMessage(getSodexoMessage());
		hipChatter.sendMessage(getWilhelmiinaMessage());
//		hipChatter.sendMessage(getPiatoMessage());
    }
}
