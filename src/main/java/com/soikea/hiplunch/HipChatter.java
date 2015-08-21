package com.soikea.hiplunch;

import com.soikea.hiplunch.domain.HipchatMessage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 18/08/14.
 */
public class HipChatter {
	static final Logger log = LoggerFactory.getLogger(HipChatter.class);

	public static final String HIP_APIURL_PREFIX = "https://api.hipchat.com/v2/";
	public static final String HIP_APIURL_METHOD = "room/" + Constants.HIP_ROOM + "/notification";
	public static final String HIP_APIURL_PARAMS = "?auth_token=" + Constants.HIP_API_KEY;
	public static final String HIP_HEADER_MIME = "application/json";

	public void sendMessage(HipchatMessage hipchatMessage) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			log.debug(mapper.writeValueAsString(hipchatMessage));

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(
				HIP_APIURL_PREFIX + HIP_APIURL_METHOD + HIP_APIURL_PARAMS);
			log.debug(webResource.toString());

			ClientResponse response = webResource
					.accept(HIP_HEADER_MIME)
					.type(HIP_HEADER_MIME)
					.post(ClientResponse.class, mapper.writeValueAsString(hipchatMessage));
			log.debug(response.toString());

		} catch (Exception e) {
			log.error("Error sending hipchat message: ", e);
		}
	}
}
