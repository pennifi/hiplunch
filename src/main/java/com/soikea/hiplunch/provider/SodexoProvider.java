package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;
import org.codehaus.jettison.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class SodexoProvider extends Provider {

    public static final SimpleDateFormat SODEXO_URL_DATEFORMAT = new SimpleDateFormat("yyyy/MM/dd");

    protected abstract String getSodexoId();

    private final String SODEXO_BASEURL = "http://www.sodexo.fi/ruokalistat/output/daily_json/" + getSodexoId() + "/";

    public String getUrl() {
        return SODEXO_BASEURL + SODEXO_URL_DATEFORMAT.format(Calendar.getInstance().getTime()) + "/fi";
    }

    protected String processFeed() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            stringBuilder
                .append(ContentUtil.processJsonArray(ContentUtil.getJsonResult(getUrl(), "courses"), "title_fi"));
        } catch (JSONException e) {
            log.error("Unable to process feed " + e.getMessage(), e);
        }
        return stringBuilder.toString();
    }
}
