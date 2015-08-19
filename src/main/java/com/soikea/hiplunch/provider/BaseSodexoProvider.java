package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 19/08/14.
 */
public abstract class BaseSodexoProvider extends BaseProvider {
    static final Logger log = LoggerFactory.getLogger(BaseSodexoProvider.class);

    static final SimpleDateFormat SODEXO_URL_DATEFORMAT = new SimpleDateFormat("yyyy/MM/dd");

	protected abstract String getSodexoId();

	public final String SODEXO_BASEURL = "http://www.sodexo.fi/ruokalistat/output/daily_json/"+getSodexoId()+"/";

	public String getUrl() {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(SODEXO_BASEURL);
        stringBuffer.append(SODEXO_URL_DATEFORMAT.format(Calendar.getInstance().getTime()));
		stringBuffer.append("/fi");

		return stringBuffer.toString();
	}

	public String processFeed() {
		StringBuffer stringBuffer = new StringBuffer();

		try {
			JSONObject json = new JSONObject(ContentUtil.getJSONContent(getUrl()));

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
