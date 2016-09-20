package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.Provider;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HiplunchTest {
    private static final Logger log = LoggerFactory.getLogger(HiplunchTest.class);

    @Test
    public void sanityCheck() {
        assertTrue(true);
    }

    @Test
    public void testAllMessages() {

        List<Provider> allProviders = new ProviderStorage().getAllProviders();

        ObjectMapper mapper = new ObjectMapper();
        try {
            for (Provider provider : allProviders) {
                String message = mapper.writeValueAsString(provider.processMessage());
                log.debug(message);
                assertNotNull(message);
            }
        } catch (Exception e) {
            log.error("Error parsing hipchat message: ", e);
        }
    }
}
