package com.soikea.hiplunch;

/**
 *
 */
public class Hiplunch {

	private static SonaattiProvider sonaattiProvider = new SonaattiProvider();
	private static SodexoProvider sodexoProvider = new SodexoProvider();

	private static String getSodexo() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(Constants.PREFIX_SODEXO + ": " + sodexoProvider.processFeed());
		return stringBuffer.toString();
	}

	private static String getWilhelmiina() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(Constants.PREFIX_WILHELMIINA + ": " + sonaattiProvider.processFeed(Constants.PREFIX_WILHELMIINA));
		return stringBuffer.toString();
	}

	private static String getPiato() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(Constants.PREFIX_PIATO + ": " + sonaattiProvider.processFeed(Constants.PREFIX_PIATO));
		return stringBuffer.toString();
	}


	public static void main( String[] args ) {

		System.out.println(getSodexo());
		System.out.println(getWilhelmiina());
		System.out.println(getPiato());

//		HipChatter hipChatter = new HipChatter();
//		hipChatter.sendMessage(getWilhelmiina());
    }
}
