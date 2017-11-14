package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SodexoProvider;

public class TietotaloProvider extends SodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "https://www.sodexo.fi/tietotalo";
    }

    @Override
    public String getName() {
        return "Sodexo Tietotalo";
    }

    @Override
    public String getId() {
        return "tietotalo";
    }

    @Override
    public String getSodexoId() {
        return "96";
    }

    @Override
    public String processFeed() {
        return super.processFeed();
    }
}
