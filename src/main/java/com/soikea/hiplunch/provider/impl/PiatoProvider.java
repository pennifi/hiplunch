package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SonaattiProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class PiatoProvider extends SonaattiProvider {

    @Override
    protected String getMessageUrl() {
        return "http://www.sonaatti.fi/piato/";
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
    protected Boolean hasGrill() {
        return true;
    }

    @Override
    protected String getRSSTitleId() {
        return "Piato";
    }

}
