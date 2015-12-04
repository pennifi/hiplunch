package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SodexoProvider;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19.8.15.
 */
public class DynamoProvider extends SodexoProvider {

    @Override
    protected String getMessageUrl() {
        return "http://www.sodexo.fi/jamk-dynamo/";
    }

    @Override
    public String getName() {
        return "Sodexo Dynamo";
    }

    @Override
    public String getId() {
        return "dynamo";
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
