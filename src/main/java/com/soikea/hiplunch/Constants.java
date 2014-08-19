package com.soikea.hiplunch;

/**
 * Created by penni on 19/08/14.
 */
public class Constants {

	public static final String URL_SONAATTI = "http://www.sonaatti.fi/rssfeed/";
	public static final String URL_SODEXO = "http://www.sodexo.fi/ruokalistat/output/daily_json/66/";
	public static final String PREFIX_WILHELMIINA = "Wilhelmiina";
	public static final String PREFIX_PIATO = "Piato";
	public static final String PREFIX_SODEXO = "Sodexo";
	public static final String ERROR_NOT_AVAILABLE = "Tietoja ei saatavilla.";

	public static final String HIP_ROOM = "748322"; //Bottitesti
	public static final String HIP_API_KEY = "kVrbFSVQXkjV4nY2rhMnpEM2SgO5NVV5YTxi5xPX"; //Bottitesti
//	public static final String HIP_ROOM = "263211"; //Soikea
//	public static final String HIP_API_KEY = "UBZKUrMSMlSv4PjCfJceixwOkR2sdByirfaRNtco"; //Soikea -room

	public static final String HIP_FROM = "LunchBot";
	public static final String HIP_MESSAGE_FORMAT = "html";
	public static final boolean HIP_NOTIFY = false;
	public static final String HIP_COLOR = "green";
	public static final String HIP_APIURL_PREFIX = "https://api.hipchat.com/v2/";
	public static final String HIP_APIURL_METHOD = "room/" + HIP_ROOM + "/notification";
	public static final String HIP_APIURL_PARAMS = "?auth_token=" + HIP_API_KEY;
	public static final String HIP_HEADER_MIME = "application/json";


}
