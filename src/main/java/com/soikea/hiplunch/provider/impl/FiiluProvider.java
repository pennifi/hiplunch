package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.FazerProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public class FiiluProvider extends FazerProvider {

    @Override
    public String getId() {
        return "fiilu";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www.fazer.fi/kahvilat-ja-leipomot/kahvilat--ravintolat/byfazer/ravintola-fiilu/";
    }

    @Override
    protected String getPrefix() {
        return "Fazer Fiilu";
    }

    @Override
    protected String getFazerId() {
        return "29801";
    }
}
