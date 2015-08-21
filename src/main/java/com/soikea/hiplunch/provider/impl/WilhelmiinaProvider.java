package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.BaseSonaattiKimonoProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class WilhelmiinaProvider extends BaseSonaattiKimonoProvider {

    @Override
    protected Boolean hasGrill() {
        return false;
    }

    @Override
    protected String getRssUrl() {
        return "http://www.sonaatti.fi/rssfeed/";
    }

    @Override
    protected String getKimonoApikey() {
        return "2n104pnw";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www.sonaatti.fi/wilhelmiina/";
    }

    @Override
    protected String getPrefix() {
        return "Wilhelmiina";
    }

    @Override
    public String getId() {
        return "wilhelmiina";
    }
}
