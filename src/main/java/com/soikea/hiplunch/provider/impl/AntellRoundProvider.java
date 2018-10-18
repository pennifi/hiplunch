package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.AntellProvider;

public class AntellRoundProvider extends AntellProvider {

    @Override
    protected String getAntellIdentifier() {
        return "79";
    }

    @Override
    protected String getStartPointOverride() {
        return "SALAATTIPÖYTÄ, LÄMMIN LISÄKE";
    }

    @Override
    protected String getEndPointOverride() {
        return "ROUNDIN LOUNASBUFFET";
    }

    @Override
    public String getId() {
        return "antell-round";
    }

    @Override
    protected String getMessageUrl() {
        return ANTELL_BASEURL;
    }

    @Override
    public String getName() {
        return "Antell Round";
    }
}
