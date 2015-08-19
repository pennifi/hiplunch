package com.soikea.hiplunch;

import com.soikea.hiplunch.domain.HipchatEnums;

/**
 * Created by penni on 19/08/14.
 */
public class Constants {

	public static final String KIMONO_APIKEY = "88YhRC4s1GBK2FlBN32W9xOksgo01sHs";
	public static final String KIMONO_BASEURL = "https://www.kimonolabs.com/api/json/ondemand/";
	public static final String KIMONO_BASEOPTS = "?apikey=";

	public static final String URL_SONAATTI = "http://www.sonaatti.fi/rssfeed/";

	public static final String PREFIX_WILHELMIINA = "Wilhelmiina";
	public static final String MESSAGE_URL_WILHELMIINA = "http://www.sonaatti.fi/wilhelmiina/";
	public static final String KIMONO_API_WILHELMIINA = "2n104pnw";
	public static final String KIMONO_URL_WILHELMIINA = KIMONO_BASEURL + KIMONO_API_WILHELMIINA + KIMONO_BASEOPTS + KIMONO_APIKEY;

    public static final String PREFIX_PIATO = "Piato";
	public static final String MESSAGE_URL_PIATO = "http://www.sonaatti.fi/piato/";
	public static final String KIMONO_API_PIATO = "e6rwhgda";
	public static final String KIMONO_URL_PIATO = KIMONO_BASEURL + KIMONO_API_PIATO + KIMONO_BASEOPTS + KIMONO_APIKEY;

    public static final String URL_SODEXO = "http://www.sodexo.fi/ruokalistat/output/daily_json/66/";
	public static final String PREFIX_SODEXO = "Sodexo";
	public static final String MESSAGE_URL_SODEXO = "http://www.sodexo.fi/mattilanniemi";

	public static final String ERROR_NOT_AVAILABLE = "Tietoja ei saatavilla.";
	public static final String GRILL_SEPARATOR = "<a><i>Paistopisteeltä:</i></a> ";

	public static final String HIP_ROOM = "263211"; // Soikea
	public static final String HIP_API_KEY = "UBZKUrMSMlSv4PjCfJceixwOkR2sdByirfaRNtco"; // Soikea

	public static final String HIP_FROM = "LunchBot";
	public static final HipchatEnums.Format HIP_MESSAGE_FORMAT = HipchatEnums.Format.html;
	public static final boolean HIP_NOTIFY = false;
	public static final HipchatEnums.Color HIP_COLOR = HipchatEnums.Color.green;
	public static final String HIP_APIURL_PREFIX = "https://api.hipchat.com/v2/";
	public static final String HIP_APIURL_METHOD = "room/" + HIP_ROOM + "/notification";
	public static final String HIP_APIURL_PARAMS = "?auth_token=" + HIP_API_KEY;
	public static final String HIP_HEADER_MIME = "application/json";

	public static final String[] HIGHLIGHTS = {"pekoni", "olut", "kalja", "pizza"};

}
