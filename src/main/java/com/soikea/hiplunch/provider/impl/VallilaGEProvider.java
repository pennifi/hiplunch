package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SodexoProvider;

public class VallilaGEProvider extends SodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "http://www.sodexo.fi/en/ge";
    }

    @Override
    public String getName() {
        return "GE Healthcare";
    }

    @Override
    public String getId() {
        return "sodexo-ge";
    }

    @Override
    public String getSodexoId() {
        return "538";
    }
}
