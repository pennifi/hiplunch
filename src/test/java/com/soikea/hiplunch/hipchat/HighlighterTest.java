package com.soikea.hiplunch.hipchat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class HighlighterTest {
    private static final Logger log = LoggerFactory.getLogger(HighlighterTest.class);

    private static final String[] HIGHLIGHTS = { "pekoni", "olut", "kalja", "pizza" };

    private final Highlighter hilighter = new Highlighter();

    private final HipchatMessage message = new HipchatMessage("");

    @Test
    public void testHightLights() {

        hilighter.setHilights(HIGHLIGHTS);
        message.setMessage(
            "Buffet: Pekoni-tomaattikuorrutettuja KALJApihvejä, Säräjuureksia Juusto-kasvispizzaa Keitto: Täyteläistä mustajuurisosekeittoa Deli salaattibaari: Grilli: Kanaa Kiovan tapaan, Tartarkastiketta, Paahdettuja porkkanoita, Seesammaustettuja ranskalaisia, Falafel pitaleipää ja tsatsikia Puolukkapiirakka, Vaniljakastiketta");
        hilighter.checkForHighlights(message);

        log.debug(message.getMessage());
        assertTrue(message.getMessage().contains("<b>pizza</b>"));
        assertTrue(message.getMessage().contains("<b>Pekoni</b>"));
        assertTrue(message.getMessage().contains("<b>KALJA</b>"));
    }
}
