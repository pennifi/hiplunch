package com.soikea.hiplunch.provider;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class SodexoProviderTest {
    static final Logger log = LoggerFactory.getLogger(SodexoProviderTest.class);

    SodexoProvider sodexoProvider = new SodexoProvider();

    @Test
    public void testUrl() {
        String url = sodexoProvider.getUrlSodexo();
        log.debug(url);
        Assert.assertEquals(
            "http://www.sodexo.fi/ruokalistat/output/daily_json/66/" + SodexoProvider.SODEXO_URL_DATEFORMAT
                .format(Calendar.getInstance().getTime()) + "/fi", url);
    }

    @Test
    public void testFeed() {
        String feed = sodexoProvider.processFeed();
        log.debug(feed);
        Assert.assertNotNull(feed);
    }
}
