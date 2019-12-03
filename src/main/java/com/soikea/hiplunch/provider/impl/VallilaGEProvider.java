package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SodexoProvider;

public class VallilaGEProvider extends SodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "https://www.sodexo.fi/neliapila-lounas";
    }

    @Override
    public String getName() {
        return "Neliapila";
    }

    @Override
    public String getId() {
        return "sodexo-ge";
    }

    @Override
    public String getSodexoId() {
        return "173";
    }
}
