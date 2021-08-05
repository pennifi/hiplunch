package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.FazerProvider;
import com.soikea.hiplunch.provider.MenuProvider;

@MenuProvider
public class FiiluProvider extends FazerProvider {

    @Override
    public String getId() {
        return "fiilu";
    }

    @Override
    protected String getMessageUrl() {
        return "https://www.foodandco.fi/ravintolat/Ravintolat-kaupungeittain/jyvaskyla/fiilu/";
    }

    @Override
    public String getName() {
        return "Fazer Fiilu";
    }

    @Override
    protected String getFazerId() {
        return "3364";
    }

    @Override
    protected String processFeed() {
        return readRawFeed();
    }
}
