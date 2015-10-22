package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.BaseProvider;
import com.soikea.hiplunch.provider.impl.DynamoProvider;
import com.soikea.hiplunch.provider.impl.MattilanniemiProvider;
import com.soikea.hiplunch.provider.impl.PiatoProvider;
import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class ProviderStorage {

    private List<BaseProvider> providers = new ArrayList<>();

    public ProviderStorage() {
        providers.add(new MattilanniemiProvider());
        providers.add(new DynamoProvider());
        providers.add(new PiatoProvider());
        providers.add(new WilhelmiinaProvider());
    }

    public List<BaseProvider> getEnabledProviders() {
        final List<BaseProvider> enabledProviders = new ArrayList<>();
        final List<String> propertyEnabledProvidersList = Arrays.asList(Constants.ENABLED_PROVIDERS);

        providers.stream()
            .filter(p -> propertyEnabledProvidersList.contains(p.getId()))
            .forEach(enabledProviders::add);

        return enabledProviders;
    }

    public List<BaseProvider> getAllProviders() {
        return providers;
    }
}
