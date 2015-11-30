package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.Provider;

import java.util.List;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19/08/14.
 */
public class Hiplunch {

    public static void main(String[] args) {

        HipChatter hipChatter = new HipChatter();
        List<Provider> enabledProviders = new ProviderStorage().getEnabledProviders();

        for (Provider provider : enabledProviders) {
            hipChatter.sendMessage(provider.processMessage());
        }
    }
}
