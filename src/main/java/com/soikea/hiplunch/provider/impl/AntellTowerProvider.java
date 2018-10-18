package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.AntellProvider;

public class AntellTowerProvider extends AntellProvider {

    @Override
    protected String getAntellIdentifier() {
        return "78";
    }

    @Override
    protected String getStartPointOverride() {
        return "Salaattilounas lämpimällä lisäkkeellä";
    }

    @Override
    protected String getEndPointOverride() {
        return "Buffetlounas";
    }

    @Override
    public String getId() {
        return "antell-tower";
    }

    @Override
    protected String getMessageUrl() {
        return ANTELL_BASEURL;
    }

    @Override
    public String getName() {
        return "Antell Tower";
    }
}
