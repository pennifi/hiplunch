package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.provider.ProviderTest;
import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 24.3.2015.
 */
public class WilhelmiinaProviderTest extends ProviderTest {

    @Override
    protected Provider getProvider() {
        return new WilhelmiinaProvider();
    }
}
