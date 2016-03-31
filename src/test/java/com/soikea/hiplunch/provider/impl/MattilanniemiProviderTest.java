package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.ProviderTest;
import com.soikea.hiplunch.provider.SodexoProvider;
import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class MattilanniemiProviderTest extends ProviderTest<MattilanniemiProvider> {

    @Test
    public void testUrl() {
        String url = ((MattilanniemiProvider) getProvider()).getUrl();
        log.debug(url);
        Assert.assertEquals(MessageFormat.format("http://www.sodexo.fi/ruokalistat/output/daily_json/{0}/{1}/fi",
                ((MattilanniemiProvider) getProvider()).getSodexoId(),
                SodexoProvider.SODEXO_URL_DATEFORMAT.format(Calendar.getInstance().getTime())), url);
    }

}
