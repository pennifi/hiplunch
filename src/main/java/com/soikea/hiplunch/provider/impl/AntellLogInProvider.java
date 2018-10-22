package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.AntellProvider;

public class AntellLogInProvider extends AntellProvider {

    @Override
    protected String getAntellIdentifier() {
        return "198";
    }

    @Override
    protected String getStartPointOverride() {
        return "SALAATTIPÖYTÄ";
    }

    @Override
    protected String getEndPointOverride() {
        return "BISTRON BUFFET";
    }

    @Override
    public String getId() {
        return "antell-login";
    }

    @Override
    protected String getMessageUrl() {
        return ANTELL_BASEURL;
    }

    @Override
    public String getName() {
        return "Antell Log in Bistro";
    }
}
