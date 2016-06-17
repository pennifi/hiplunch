package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.ProviderTest;
import com.soikea.hiplunch.provider.SodexoProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class BittipannuProviderTest extends ProviderTest<BittipannuProvider> {

    @Test
    public void testUrl() {
        String url = ((BittipannuProvider) getProvider()).getUrl();
        Assert.assertEquals(
            "http://www.sodexo.fi/ruokalistat/output/daily_json/"
                +((BittipannuProvider) getProvider()).getSodexoId()
                + "/" + SodexoProvider.SODEXO_URL_DATEFORMAT
                .format(Calendar.getInstance().getTime()) + "/fi", url);
    }
}
