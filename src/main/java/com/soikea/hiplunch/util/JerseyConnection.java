package com.soikea.hiplunch.util;

import com.soikea.hiplunch.hipchat.HipChatter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JerseyConnection {

    private static final Logger log = LoggerFactory.getLogger(HipChatter.class);

    private static final String HIP_HEADER_MIME = "application/json";

    public static void sendMessage(String message, String url) {
        try {

            log.debug(message);

            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

            Client client = Client.create(clientConfig);

            WebResource webResource = client.resource(url);

            ClientResponse response = webResource.accept(HIP_HEADER_MIME).type(HIP_HEADER_MIME)
                    .post(ClientResponse.class, message);
            log.debug(response.toString());
        } catch (Exception e) {
            log.error("Error sending message: {}", e.getMessage());
        }

    }

}
