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

        String parsed = sonaattiProvider.cleanGrillResult(raw);
        String parsed2 = sonaattiProvider.cleanGrillResult(raw2);

        assertEquals(SonaattiProvider.GRILL_SEPARATOR + "Salaatti annos: Ma-Ke Paistettua lohta. To-Pe. Broilerinfilettä", parsed);
        assertEquals(SonaattiProvider.GRILL_SEPARATOR + "Kebab annos. Jättikatkarawokkia.", parsed2);
    }

    public void testGrillApi() {
        String grillString = sonaattiProvider.processGrill();

        log.debug(grillString);

        assertTrue(grillString.startsWith(SonaattiProvider.GRILL_SEPARATOR));
    }

    public void testFeed() {
        String wString = sonaattiProvider.processFeed(Constants.PREFIX_WILHELMIINA);
        String pString = sonaattiProvider.processFeed(Constants.PREFIX_PIATO);

        log.debug(wString);
        log.debug(pString);

        assertNotNull(wString);
        assertNotNull(pString);

    }
}
