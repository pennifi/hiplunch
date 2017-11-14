package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SodexoProvider;

public class BittipannuProvider extends SodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "https://www.sodexo.fi/jamk-dynamo/"; // url still with old name
    }

    @Override
    public String getName() {
        return "Sodexo Bittipannu";
    }

    @Override
    public String getId() {
        return "bittipannu";
    }

    @Override
    public String getSodexoId() {
        return "5865";
    }

    @Override
    public String processFeed() {
        String feed = super.processFeed();
        feed = feed.replaceAll("(, )?Iltaruokailu klo [\\d\\.\\s-]+", ".");
        return feed;
    }
}
