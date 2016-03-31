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

    private final List<Provider> providers = new ArrayList<>();

    public ProviderStorage() {
        providers.add(new MattilanniemiProvider());
        providers.add(new DynamoProvider());
        providers.add(new PiatoProvider());
        providers.add(new WilhelmiinaProvider());
        providers.add(new FiiluProvider());
        providers.add(new NurkkaProvider());
        providers.add(new TrattoriaProvider());
    }

    public List<Provider> getConfiguredDefaultProviders() {
        final List<Provider> defaultProfiders = new ArrayList<>();
        final List<String> propertyDefaultProvidersList = Arrays.asList(Constants.DEFAULT_PROVIDERS);

        providers.stream()
            .filter(p -> propertyDefaultProvidersList.contains(p.getId()))
            .forEach(defaultProfiders::add);

        return defaultProfiders;
    }

    public List<Provider> getAllProviders() {
        return providers;
    }

    public Provider getProviderById(String id) {
       return getAllProviders().stream()
            .filter(f -> (f.getId().equals(id)))
            .findAny().get();
    }
}
