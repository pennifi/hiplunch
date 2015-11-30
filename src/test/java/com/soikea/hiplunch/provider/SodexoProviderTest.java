package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.provider.impl.MattilanniemiProvider;
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

    final Provider provider = new MattilanniemiProvider();

    @Test
    public void testUrl() {
        String url = ((MattilanniemiProvider) provider).getUrl();
        log.debug(url);
        Assert.assertEquals(
            "http://www.sodexo.fi/ruokalistat/output/daily_json/66/" + SodexoProvider.SODEXO_URL_DATEFORMAT
                .format(Calendar.getInstance().getTime()) + "/fi", url);
    }

    @Test
    public void testFeed() {
        String feed = provider.processFeed();
        log.debug(feed);
        Assert.assertNotNull(feed);
    }
}
