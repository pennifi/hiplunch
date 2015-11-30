package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.provider.impl.PiatoProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class PiatoProviderTest {
    static final Logger log = LoggerFactory.getLogger(PiatoProviderTest.class);

    final Provider provider = new PiatoProvider();

    @Test
    public void testPiatoFeed() {
        String pString = provider.processFeed();
        log.debug(pString);
        Assert.assertNotNull(pString);
    }
}
