package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;

@MenuProvider
public class JuvenesCubeProvider extends Provider {

    @Override
    protected String processFeed() {
        // Vaadin app. Ei sourcea nähtävillä...
        return "Katso sivuilta.";
    }

    @Override
    public String getId() {
        return "cube";
    }

    @Override
    protected String getMessageUrl() {
        return "https://fi.jamix.cloud/apps/menu/?anro=12347&k=61&mt=103";
    }

    @Override
    public String getName() {
        return "Juvenes Cube";
    }
}
