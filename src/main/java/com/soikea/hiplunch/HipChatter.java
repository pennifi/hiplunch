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
 * Created by penni on 18/08/14.
 */
public class HipChatter {
	static final Logger log = LoggerFactory.getLogger(HipChatter.class);

	public void sendMessage(HipchatMessage hipchatMessage) {

		try {
			ClientConfig clientConfig = new DefaultClientConfig();

			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			ObjectMapper mapper = new ObjectMapper();

			WebResource webResource = client
					.resource(Constants.HIP_APIURL_PREFIX
							+ Constants.HIP_APIURL_METHOD
							+ Constants.HIP_APIURL_PARAMS);

			ClientResponse response = webResource
					.accept(Constants.HIP_HEADER_MIME)
					.type(Constants.HIP_HEADER_MIME)
					.post(ClientResponse.class, mapper.writeValueAsString(hipchatMessage));

			log.debug(webResource.toString());
			log.debug(mapper.writeValueAsString(hipchatMessage));
			log.debug(response.toString());

		} catch (Exception e) {
			log.error("Error sending hipchat message: ", e);
		}
	}
}
