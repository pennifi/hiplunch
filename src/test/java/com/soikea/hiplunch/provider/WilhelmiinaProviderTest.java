package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class WilhelmiinaProviderTest {
    static final Logger log = LoggerFactory.getLogger(WilhelmiinaProviderTest.class);

    final Provider provider = new WilhelmiinaProvider();

    @Test
    public void testWilhelmiinaFeed() {
        String wString = provider.processFeed();
        log.debug(wString);
        Assert.assertNotNull(wString);
    }

}
