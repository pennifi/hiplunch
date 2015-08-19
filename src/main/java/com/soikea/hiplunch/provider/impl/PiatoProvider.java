package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.BaseSonaattiKimonoProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class PiatoProvider extends BaseSonaattiKimonoProvider {

    @Override
    protected String getKimonoApikey() {
        return "e6rwhgda";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www.sonaatti.fi/piato/";
    }

    @Override
    protected String getPrefix() {
        return "Piato";
    }

    @Override
    protected Boolean hasGrill() {
        return true;
    }
}
