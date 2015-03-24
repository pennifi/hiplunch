package com.soikea.hiplunch;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by penni on 19/08/14.
 */
public class SodexoProvider {
    static final Logger log = LoggerFactory.getLogger(SodexoProvider.class);

    static final SimpleDateFormat SODEXO_URL_DATEFORMAT = new SimpleDateFormat("yyyy/MM/dd");

	public String getUrlSodexo() {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(Constants.URL_SODEXO);
        stringBuffer.append(SODEXO_URL_DATEFORMAT.format(Calendar.getInstance().getTime()));
		stringBuffer.append("/fi");

		return stringBuffer.toString();
	}

	public String processFeed() {
		StringBuffer stringBuffer = new StringBuffer();

		try {
			JSONObject json = new JSONObject(ContentUtil.getJSONContent(getUrlSodexo()));

			JSONArray array = json.getJSONArray("courses");

			for (int i = 0; i < array.length(); i++) {
				JSONObject course = array.getJSONObject(i);
				stringBuffer.append(course.getString("title_fi"));
				if (i < array.length() - 1) {
					stringBuffer.append(", ");
				}
			}
			return stringBuffer.toString();

		} catch (JSONException e) {
			log.error("Unable to process feed " + e.getMessage(), e);
		}
		return null;
	}


}
