package com.soikea.hiplunch;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class SonaattiProviderTest extends TestCase {
    static final Logger log = LoggerFactory.getLogger(SonaattiProviderTest.class);

    SonaattiProvider sonaattiProvider = new SonaattiProvider();

    public void testGrillParsing() {
        String raw = "Paistopisteellä viikolla 12\nSalaatti annos: Ma-Ke \nPaistettua lohta (VL,G) 4,95 € / 10,00 €\nTo-Pe (VL,G) 4,95 € / 10,00 €\nBroilerinfilettä \nPaistopiste palvelee ma-pe klo 11.00-14.00";
        String raw2 = "Paistopisteellä viikolla 10\nKebab annos (L,M,G) 4,95 € / 10,00 €\nJättikatkarawokkia (L, M) 4,95€ / 9,40€\nPaistopiste palvelee ma-pe klo 11.00-14.30";
        String raw3 = "Paistopiste on pääsiäistauolla!\nSonaatti ottaa ylpeänä osaa KANSAINVÄLISEEN PUPUTUSVIIKKOON! Pääsiäisen läheisyys ja kevätauringon lisäämä karoteenin tarve mielessä haastamme teidät kaikki puputtamaan! Ohjelmassa päivittäin: Vaihtuvia puputusaiheisia tarjouksia ja yllätyksiä Yllätysarpajaiset Jokaisessa Sonaatin ravintolassa on myös virallinen PUPUTUSPÖYTÄ, joka on avoin kaikille puputtajille. Tervetuloa! P.S. Täydellä vatsalla ei sitten saa pomppia! Poing, Poing, Poing! Viestiäksesi puputusmielialasi, etene koko ruokajono pomppimalla kuin pupu ja lupaamme palkita sinut kahvilipulla!";

        String parsed = sonaattiProvider.cleanGrillResult(raw);
        String parsed2 = sonaattiProvider.cleanGrillResult(raw2);
        String parsed3 = sonaattiProvider.cleanGrillResult(raw3);

        assertEquals(SonaattiProvider.GRILL_SEPARATOR + "Salaatti annos: Ma-Ke Paistettua lohta. To-Pe. Broilerinfilettä", parsed);
        assertEquals(SonaattiProvider.GRILL_SEPARATOR + "Kebab annos. Jättikatkarawokkia.", parsed2);
        assertEquals(SonaattiProvider.GRILL_SEPARATOR + "Suljettu. (Paistopiste on pääsiäistauolla!...)", parsed3);

    }

    public void testGrillApi() {
        String grillString = sonaattiProvider.processGrill();
        log.debug(grillString);
        assertTrue(grillString.startsWith(SonaattiProvider.GRILL_SEPARATOR));
    }

    public void testWilhelmiinaFeed() {
        String wString = sonaattiProvider.processFeed(Constants.PREFIX_WILHELMIINA);
        log.debug(wString);
        assertNotNull(wString);
    }

    public void testPiatoFeed() {
        String pString = sonaattiProvider.processFeed(Constants.PREFIX_PIATO);
        log.debug(pString);
        assertNotNull(pString);
    }
}
