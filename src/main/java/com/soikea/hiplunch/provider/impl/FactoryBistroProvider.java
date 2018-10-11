package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.FactoryProvider;

public class FactoryBistroProvider extends FactoryProvider {

    @Override
    protected String getFactoryIdentifier() {
        return "bistro";
    }

    @Override
    protected String getStartPointOverride() {
        return "</h3>";
    }

    @Override
    protected String getEndPointOverride() {
        return "Monday";
    }

    @Override
    public String getId() {
        return "factory-bistro";
    }

    @Override
    protected String getMessageUrl() {
        return FACTORY_BASEURL;
    }

    @Override
    public String getName() {
        return "Factory Bistro";
    }
}
