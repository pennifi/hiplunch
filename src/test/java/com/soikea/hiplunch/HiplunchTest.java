package com.soikea.hiplunch;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class HiplunchTest {
    static final Logger log = LoggerFactory.getLogger(HiplunchTest.class);

    @Test
    public void sanityCheck() {
        assertTrue(true);
    }

    @Test
    public void testAllMessages() {

        ObjectMapper mapper = new ObjectMapper();
        try {

            log.debug(mapper.writeValueAsString(Hiplunch.getSodexoMessage()));
            log.debug(mapper.writeValueAsString(Hiplunch.getWilhelmiinaMessage()));
            log.debug(mapper.writeValueAsString(Hiplunch.getPiatoMessage()));

        } catch (Exception e) {
            log.error("Error parsing hipchat message: ", e);
        }
    }

}
