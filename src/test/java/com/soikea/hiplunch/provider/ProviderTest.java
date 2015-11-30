package com.soikea.hiplunch.provider;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public abstract class ProviderTest {
    public final Logger log = LoggerFactory.getLogger(this.getClass());

    protected abstract Provider getProvider();

    @Test
    public void testFeed() {
        String feed = getProvider().processFeed();
        log.debug(feed);
        Assert.assertNotNull(feed);
    }
}
