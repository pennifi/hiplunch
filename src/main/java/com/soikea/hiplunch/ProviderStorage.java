package com.soikea.hiplunch;

import com.google.common.reflect.ClassPath;
import com.soikea.hiplunch.provider.Provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProviderStorage {

    private final List<Provider> providers = new ArrayList<>();

    public ProviderStorage() {

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
                if (info.getName().startsWith("com.soikea.hiplunch.provider.impl")
                    && !info.getName().endsWith("Test")) {
                    final Class<?> clazz = info.load();
                    providers.add((Provider) clazz.newInstance());
                }
            }
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<Provider> getConfiguredDefaultProviders() {
        final List<Provider> defaultProviders = new ArrayList<>();
        final List<String> propertyDefaultProvidersList = Arrays.asList(Constants.DEFAULT_PROVIDERS);

        providers.stream().filter(p -> propertyDefaultProvidersList.contains(p.getId())).forEach(defaultProviders::add);

        return defaultProviders;
    }

    public List<Provider> getAllProviders() {
        return providers;
    }

    public Provider getProviderById(String id) {
        return getAllProviders().stream().filter(f -> (f.getId().equals(id))).findAny().get();
    }
}
