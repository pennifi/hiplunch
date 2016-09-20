package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SonaattiProvider;

public class WilhelmiinaProvider extends SonaattiProvider {

    @Override
    protected String getMessageUrl() {
        return "http://www.sonaatti.fi/wilhelmiina/";
    }

    @Override
    public String getName() {
        return "Sonaatti Wilhelmiina";
    }

    @Override
    public String getId() {
        return "wilhelmiina";
    }

    @Override
    protected Boolean hasGrill() {
        return false;
    }
}
