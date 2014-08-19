package com.soikea.hiplunch;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Created by penni on 18/08/14.
 */
public class HipChatter {

	public void sendMessage(String message) {

		try {
			ClientConfig clientConfig = new DefaultClientConfig();


			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource(Constants.HIP_APIURL_PREFIX + Constants.HIP_APIURL_METHOD + Constants.HIP_APIURL_PARAMS);

			ClientResponse response = webResource.accept("application/json")
					.type("application/json").post(ClientResponse.class, createMessageJsonString(message));

			System.out.println(createMessageJsonString(message));

			System.out.println(webResource);

			System.out.println(response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private String createMessageJsonString(String message) {

		return "{"
				+ "\"from\": \"" + Constants.HIP_FROM
				+ "\", \"message_format\": \"" + Constants.HIP_MESSAGE_FORMAT
				+ "\", \"notify\": " + Constants.HIP_NOTIFY
				+ ", \"color\": \"" + Constants.HIP_COLOR
				+ "\", \"message\": \"" + message
				+ "\"}";
	}

}
