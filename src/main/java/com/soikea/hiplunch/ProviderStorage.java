package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.provider.impl.DynamoProvider;
import com.soikea.hiplunch.provider.impl.FiiluProvider;
import com.soikea.hiplunch.provider.impl.MattilanniemiProvider;
import com.soikea.hiplunch.provider.impl.NurkkaProvider;
import com.soikea.hiplunch.provider.impl.PiatoProvider;
import com.soikea.hiplunch.provider.impl.TrattoriaProvider;
import com.soikea.hiplunch.provider.impl.WilhelmiinaProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class ProviderStorage {

    private List<Provider> providers = new ArrayList<>();

    public ProviderStorage() {
        providers.add(new MattilanniemiProvider());
        providers.add(new DynamoProvider());
        providers.add(new PiatoProvider());
        providers.add(new WilhelmiinaProvider());
        providers.add(new FiiluProvider());
        providers.add(new NurkkaProvider());
        providers.add(new TrattoriaProvider());
    }

    public List<Provider> getEnabledProviders() {
        final List<Provider> enabledProviders = new ArrayList<>();
        final List<String> propertyEnabledProvidersList = Arrays.asList(Constants.ENABLED_PROVIDERS);

        providers.stream()
            .filter(p -> propertyEnabledProvidersList.contains(p.getId()))
            .forEach(enabledProviders::add);

        return enabledProviders;
    }

    public List<Provider> getAllProviders() {
        return providers;
    }
}
