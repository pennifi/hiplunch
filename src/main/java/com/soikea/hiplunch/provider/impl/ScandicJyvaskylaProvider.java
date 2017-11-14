package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.LounaatInfoProvider;

public class ScandicJyvaskylaProvider extends LounaatInfoProvider {
    @Override
    protected String getLounaatInfoIdentifier() {
        return "scandic-jyvaskyla/jyvaskyla";
    }

    @Override
    public String getId() {
        return "scandic-jkl";
    }

    @Override
    protected String getMessageUrl() {
        return "https://www.scandichotels.fi/hotellit/suomi/jyvaskyla/scandic-jyvaskyla/ravintola-ja-baari/ravintola-colonial";
    }

    @Override
    public String getName() {
        return "Scandic Jyväskylä";
    }
}
