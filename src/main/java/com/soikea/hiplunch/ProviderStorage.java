package com.soikea.hiplunch;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProviderStorage {

    private final List<Provider> providers = new ArrayList<>();

    public ProviderStorage() {

        try (ScanResult result = new ClassGraph().enableAllInfo()
                .whitelistPackages(getClass().getPackage().getName()).scan()) {

            ClassInfoList classInfos = result.getClassesWithAnnotation(MenuProvider.class.getName());
            for (final ClassInfo info : classInfos) {
                final Class<?> clazz = info.loadClass();
                providers.add((Provider) clazz.getDeclaredConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
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
