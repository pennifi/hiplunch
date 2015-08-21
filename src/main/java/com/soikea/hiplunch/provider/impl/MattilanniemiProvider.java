package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.BaseSodexoProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class MattilanniemiProvider extends BaseSodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "http://www.sodexo.fi/mattilanniemi/";
    }

    @Override
    protected String getPrefix() {
        return "Sodexo Mattilanniemi";
    }

    @Override
    public String getId() {
        return "mattilanniemi";
    }

    @Override
    protected String getSodexoId() {
        return "66";
    }
}
