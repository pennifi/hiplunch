package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.util.ContentUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class FazerProvider extends Provider {

    protected abstract String getFazerId();

    private String getUrl() {
        return String
            .format("https://www.fazerfoodco.fi/api/restaurant/menu/day?date=%s&language=fi&restaurantPageId=%s",
                    new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),
                    getFazerId());
    }

    protected String readRawFeed() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            JSONArray menus = new JSONObject(ContentUtil.getUrlContents(getUrl()))
                    .getJSONObject("LunchMenu")
                    .getJSONArray("SetMenus");
            for (int i = 0; i < menus.length(); i++) {

                JSONObject menu = (JSONObject) menus.get(i);
                String cat = menu.getString("Name");

                List<String> mealStrings = new ArrayList<>();
                JSONArray meals = menu.getJSONArray("Meals");
                for (int j = 0; j < meals.length(); j++) {
                    JSONObject meal = (JSONObject) meals.get(j);
                    mealStrings.add(meal.getString("Name"));
                }
                stringBuilder.append(String.format("%s: %s. ", cat, StringUtils.join(mealStrings, ", ")));
            }

        } catch (JSONException e) {
            log.error("Unable to process feed " + e.getMessage(), e);
        }

        if (stringBuilder.length() < 10) {
            stringBuilder.append(ContentUtil.ERROR_NOT_AVAILABLE);
        }
        return stringBuilder.toString();
    }
}
