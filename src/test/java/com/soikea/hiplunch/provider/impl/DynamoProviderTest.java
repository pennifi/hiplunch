package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.ProviderTest;
import com.soikea.hiplunch.provider.SodexoProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class DynamoProviderTest extends ProviderTest<DynamoProvider> {

    @Test
    public void testUrl() {
        String url = ((DynamoProvider) getProvider()).getUrl();
        log.debug(url);
        Assert.assertEquals(
            "http://www.sodexo.fi/ruokalistat/output/daily_json/"
                +((DynamoProvider) getProvider()).getSodexoId()
                + "/" + SodexoProvider.SODEXO_URL_DATEFORMAT
                .format(Calendar.getInstance().getTime()) + "/fi", url);
    }
}
