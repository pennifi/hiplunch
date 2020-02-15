package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.SodexoProvider;

@MenuProvider
public class MattilanniemiProvider extends SodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "https://www.sodexo.fi/mattilanniemi/";
    }

    @Override
    public String getName() {
        return "Sodexo Mattilanniemi";
    }

    @Override
    public String getId() {
        return "mattilanniemi";
    }

    @Override
    public String getSodexoId() {
        return "124";
    }
}
