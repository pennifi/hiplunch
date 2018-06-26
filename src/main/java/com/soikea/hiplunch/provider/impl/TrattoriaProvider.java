package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.LounaatInfoProvider;

public class TrattoriaProvider extends LounaatInfoProvider {

    @Override
    public String getId() {
        return "trattoria";
    }

    @Override
    protected String getMessageUrl() {
        return "https://www.raflaamo.fi/fi/jyvaskyla/trattoria-aukio-jyvaskyla";
    }

    @Override
    public String getName() {
        return "Trattoria Aukio";
    }

    @Override
    protected String getLounaatInfoIdentifier() {
        return "trattoria-aukio/jyvaskyla";
    }

    @Override
    protected String getStartPointOverride() {
        return "<div id=\"menu\"";
    }

    @Override
    protected String getEndPointOverride() {
        return "Lounas sisältää";
    }
}
