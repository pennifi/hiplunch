package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.provider.impl.PiatoProvider;
import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class BaseSonaattiKimonoProviderTest {
    static final Logger log = LoggerFactory.getLogger(BaseSonaattiKimonoProviderTest.class);

    PiatoProvider piatoProvider = new PiatoProvider();
    WilhelmiinaProvider wilhelmiinaProvider = new WilhelmiinaProvider();

    @Test
    public void testGrillParsing() {
        String raw = "Paistopisteellä viikolla 12\nSalaatti annos: Ma-Ke \nPaistettua lohta (VL,G) 4,95 € / 10,00 €\nTo-Pe (VL,G) 4,95 € / 10,00 €\nBroilerinfilettä \nPaistopiste palvelee ma-pe klo 11.00-14.00";
        String raw2 = "Paistopisteellä viikolla 10\nKebab annos (L,M,G) 4,95 € / 10,00 €\nJättikatkarawokkia (L, M) 4,95€ / 9,40€\nPaistopiste palvelee ma-pe klo 11.00-14.30";
        String raw3 = "Paistopiste on pääsiäistauolla!\nSonaatti ottaa ylpeänä osaa KANSAINVÄLISEEN PUPUTUSVIIKKOON! Pääsiäisen läheisyys ja kevätauringon lisäämä karoteenin tarve mielessä haastamme teidät kaikki puputtamaan! Ohjelmassa päivittäin: Vaihtuvia puputusaiheisia tarjouksia ja yllätyksiä Yllätysarpajaiset Jokaisessa Sonaatin ravintolassa on myös virallinen PUPUTUSPÖYTÄ, joka on avoin kaikille puputtajille. Tervetuloa! P.S. Täydellä vatsalla ei sitten saa pomppia! Poing, Poing, Poing! Viestiäksesi puputusmielialasi, etene koko ruokajono pomppimalla kuin pupu ja lupaamme palkita sinut kahvilipulla!";

        String parsed = piatoProvider.cleanGrillResult(raw);
        String parsed2 = piatoProvider.cleanGrillResult(raw2);
        String parsed3 = piatoProvider.cleanGrillResult(raw3);

        Assert
            .assertEquals(BaseSonaattiKimonoProvider.GRILL_SEPARATOR + "Salaatti annos: Ma-Ke Paistettua lohta. To-Pe. Broilerinfilettä",
                parsed);
        Assert.assertEquals(BaseSonaattiKimonoProvider.GRILL_SEPARATOR + "Kebab annos. Jättikatkarawokkia.", parsed2);
        Assert.assertEquals(BaseSonaattiKimonoProvider.GRILL_SEPARATOR + "Suljettu. (Paistopiste on pääsiäistauolla!...)", parsed3);

    }

    @Test
    public void testGrillApi() {
        String grillString = piatoProvider.cleanGrillResult(piatoProvider.getGrillApiString());
        log.debug(grillString);
        Assert.assertTrue(grillString.startsWith(BaseSonaattiKimonoProvider.GRILL_SEPARATOR));
    }

    @Test
    public void testWilhelmiinaFeed() {
        String wString = wilhelmiinaProvider.processFeed();
        log.debug(wString);
        Assert.assertNotNull(wString);
    }

    @Test
    public void testPiatoFeed() {
        String pString = piatoProvider.processFeed();
        log.debug(pString);
        Assert.assertNotNull(pString);
    }
}
