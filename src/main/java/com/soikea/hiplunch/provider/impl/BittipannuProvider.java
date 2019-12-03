package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.SodexoProvider;
import com.soikea.hiplunch.util.ContentUtil;
import org.codehaus.jettison.json.JSONException;

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
        return "127";
    }

    @Override
    public String processFeed() {
        // bittipannu has no array in the courses section?

        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder
                    .append(ContentUtil.processJsonObjectArray(ContentUtil.getJsonObjectResult(getUrl(), "courses"), "title_fi"));
        } catch (JSONException e) {
            log.error("Unable to process feed " + e.getMessage(), e);
        }
        String feed = stringBuilder.toString();
        feed = feed.replaceAll("(, )?Iltaruokailu klo [\\d\\.\\s-]+", ".");
        return feed;
    }
}
