package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.SemmaProvider;

@MenuProvider
public class MaijaProvider extends SemmaProvider {

    @Override
    protected String getMessageUrl() {
        return "https://www.semma.fi/ravintolat2/mattilanniemi/maija/";
    }

    @Override
    public String getName() {
        return "Semma Maija";
    }

    @Override
    public String getId() {
        return "maija";
    }

    @Override
    protected String getSemmaId() {
        return "1402";
    }
}
