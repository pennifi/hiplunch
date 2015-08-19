package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.impl.MattilanniemiProvider;
import com.soikea.hiplunch.provider.impl.PiatoProvider;
import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class HiplunchTest {
    static final Logger log = LoggerFactory.getLogger(HiplunchTest.class);

    @Test
    public void sanityCheck() {
        assertTrue(true);
    }

    @Test
    public void testAllMessages() {

        MattilanniemiProvider mattilanniemiProvider = new MattilanniemiProvider();
        PiatoProvider piatoProvider = new PiatoProvider();
        WilhelmiinaProvider wilhelmiinaProvider = new WilhelmiinaProvider();

        ObjectMapper mapper = new ObjectMapper();
        try {

            log.debug(mapper.writeValueAsString(mattilanniemiProvider.processMessage()));
            log.debug(mapper.writeValueAsString(wilhelmiinaProvider.processMessage()));
            log.debug(mapper.writeValueAsString(piatoProvider.processMessage()));

        } catch (Exception e) {
            log.error("Error parsing hipchat message: ", e);
        }
    }

}
