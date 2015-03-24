package com.soikea.hiplunch;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class SodexoProviderTest extends TestCase {
    static final Logger log = LoggerFactory.getLogger(SodexoProviderTest.class);

    SodexoProvider sodexoProvider = new SodexoProvider();

    public void testUrl() {
        String url = sodexoProvider.getUrlSodexo();

        log.debug(url);

        assertEquals("http://www.sodexo.fi/ruokalistat/output/daily_json/66/"+SodexoProvider.SODEXO_URL_DATEFORMAT.format(Calendar.getInstance().getTime())+"/fi", url);

    }

    public void testFeed() {
        String feed = sodexoProvider.processFeed();

        log.debug(feed);

        assertNotNull(feed);

    }
}
