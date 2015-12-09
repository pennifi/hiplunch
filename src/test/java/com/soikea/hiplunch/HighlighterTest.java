package com.soikea.hiplunch;


import com.soikea.hiplunch.domain.HipchatMessage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 9.12.15.
 */
public class HighlighterTest {
    static final Logger log = LoggerFactory.getLogger(HighlighterTest.class);


    @Test
    public void testHightLights() {
        HipchatMessage message = new HipchatMessage();

        message.setMessage("Buffet: Pekoni-tomaattikuorrutettuja KALJApihvejä, Säräjuureksia Juusto-kasvispizzaa Keitto: Täyteläistä mustajuurisosekeittoa Deli salaattibaari: Grilli: Kanaa Kiovan tapaan, Tartarkastiketta, Paahdettuja porkkanoita, Seesammaustettuja ranskalaisia, Falafel pitaleipää ja tsatsikia Puolukkapiirakka, Vaniljakastiketta");
        Highlighter.checkForHighlights(message);
        log.debug(message.getMessage());
        assertTrue(message.getMessage().contains("<b>pizza</b>"));
        assertTrue(message.getMessage().contains("<b>pekoni</b>"));
        assertTrue(message.getMessage().contains("<b>kalja</b>"));

    }
}
