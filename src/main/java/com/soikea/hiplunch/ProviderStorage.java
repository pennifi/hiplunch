package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.BaseProvider;
import com.soikea.hiplunch.provider.impl.DynamoProvider;
import com.soikea.hiplunch.provider.impl.MattilanniemiProvider;
import com.soikea.hiplunch.provider.impl.PiatoProvider;
import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class ProviderStorage {

    private List<BaseProvider> providers = new ArrayList<BaseProvider>();

    public ProviderStorage() {
        providers.add(new MattilanniemiProvider());
        providers.add(new DynamoProvider());
        providers.add(new PiatoProvider());
        providers.add(new WilhelmiinaProvider());
    }

    public List<BaseProvider> getEnabledProviders() {
        List<BaseProvider> enabledProviders = new ArrayList<BaseProvider>();

        String[] propertyEnabledProvidersList = Constants.ENABLED_PROVIDERS;
        for (String providerId : propertyEnabledProvidersList) {
            for (BaseProvider provider : providers) {
                if (provider.getId().equals(providerId)) {
                    enabledProviders.add(provider);
                }
            }
        }
        return enabledProviders;
    }

    public List<BaseProvider> getAllProviders() {
        return providers;
    }
}
