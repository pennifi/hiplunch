package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.provider.impl.FiiluProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public class FiiluProviderTest {
    static final Logger log = LoggerFactory.getLogger(FiiluProviderTest.class);

    final Provider provider = new FiiluProvider();

    @Test
    public void testFeed() {
        String pString = provider.processFeed();
        log.debug(pString);
        Assert.assertNotNull(pString);
    }
}
