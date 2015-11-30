package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.provider.impl.MattilanniemiProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class MattilanniemiProviderTest extends ProviderTest {

    @Test
    public void testUrl() {
        String url = ((MattilanniemiProvider) getProvider()).getUrl();
        log.debug(url);
        Assert.assertEquals(
            "http://www.sodexo.fi/ruokalistat/output/daily_json/"
                +((MattilanniemiProvider) getProvider()).getSodexoId()
                + "/" + SodexoProvider.SODEXO_URL_DATEFORMAT
                .format(Calendar.getInstance().getTime()) + "/fi", url);
    }

    @Override
    protected Provider getProvider() {
        return new MattilanniemiProvider();
    }
}
