package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.BaseSonaattiProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class WilhelmiinaProvider extends BaseSonaattiProvider {

    @Override
    protected String getMessageUrl() {
        return "http://www.sonaatti.fi/wilhelmiina/";
    }

    @Override
    protected String getPrefix() {
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

    @Override
    protected String getRSSTitleId() {
        return "Wilhelmiina";
    }

}
