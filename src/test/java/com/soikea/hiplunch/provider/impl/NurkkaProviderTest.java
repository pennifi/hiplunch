package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.provider.ProviderTest;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public class NurkkaProviderTest extends ProviderTest {

    @Override
    protected Provider getProvider() {
        return new NurkkaProvider();
    }

}
