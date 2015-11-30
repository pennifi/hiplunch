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
    protected String getPrefix() {
        return "Sodexo Dynamo";
    }

    @Override
    public String getId() {
        return "dynamo";
    }

    @Override
    protected String getSodexoId() {
        return "5865";
    }
}