package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SonaattiProvider;

public class PiatoProvider extends SonaattiProvider {

    @Override
    protected String getMessageUrl() {
        return "https://www.sonaatti.fi/piato/";
    }

    @Override
    public String getName() {
        return "Sonaatti Piato";
    }

    @Override
    public String getId() {
        return "piato";
    }

    @Override
    protected String getSonaattiId() {
        return "1408";
    }
}
