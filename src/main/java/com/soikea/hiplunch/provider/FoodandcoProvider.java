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

public abstract class FoodandcoProvider extends Provider {

    protected abstract String getFazerId();

    private String getUrl() {
        return String
            .format("https://www.compass-group.fi/menuapi/feed/json?costNumber=%s&language=fi",
                    getFazerId());
    }

    protected String readRawFeed() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            JSONArray menus = new JSONObject(ContentUtil.getUrlContents(getUrl()))
                    .getJSONArray("MenusForDays");
            for (int i = 0; i < menus.length(); i++) {
                JSONObject menu = (JSONObject) menus.get(i);
                if (menu.getString("Date")
                        .startsWith(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()))
                ){
                    JSONArray setMenus = menu.getJSONArray("SetMenus");
                    for (int k = 0; k < setMenus.length(); k++) {
                        JSONObject setMenu = (JSONObject) setMenus.get(k);

                        String cat = setMenu.getString("Name");

                        List<String> mealStrings = new ArrayList<>();
                        JSONArray meals = setMenu.getJSONArray("Components");
                        for (int j = 0; j < meals.length(); j++) {
                            String meal = (String) meals.get(j);
                            mealStrings.add(meal.replaceAll("\\(.+?\\)", "").trim());
                        }
                        if (StringUtils.isBlank(cat) || "null".equals(cat)) {
                            cat = null;
                        }
                        if (cat != null && !mealStrings.isEmpty()) {
                            String toAppend = String.format("%s: %s. ", cat, StringUtils.join(mealStrings, ", "));
                            stringBuilder.append(toAppend);
                        }
                    }
                }
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
