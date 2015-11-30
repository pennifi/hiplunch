package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SodexoProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class MattilanniemiProvider extends SodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "http://www.sodexo.fi/mattilanniemi/";
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
        return "66";
    }
}
