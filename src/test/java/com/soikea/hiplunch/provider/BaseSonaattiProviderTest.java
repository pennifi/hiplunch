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
public class BaseSonaattiProviderTest {
    static final Logger log = LoggerFactory.getLogger(BaseSonaattiProviderTest.class);

    final PiatoProvider piatoProvider = new PiatoProvider();
    final WilhelmiinaProvider wilhelmiinaProvider = new WilhelmiinaProvider();

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
