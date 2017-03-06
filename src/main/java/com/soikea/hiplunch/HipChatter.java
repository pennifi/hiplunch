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

public class HipChatter {

    private static final Logger log = LoggerFactory.getLogger(HipChatter.class);

    private static final String HIP_ROOM_VARNAME = "HIPCHAT_ROOM";

    private static final String HIP_TOKEN_VARNAME = "HIPCHAT_TOKEN";

    private static final String HIP_HEADER_MIME = "application/json";

    private String HIPCHAT_RESOURCE = null;

    private String HIPCHAT_ROOM = null;

    private String HIPCHAT_TOKEN = null;

    public HipChatter() {
        HIPCHAT_ROOM = Constants.getSystemVariable(HIP_ROOM_VARNAME);
        HIPCHAT_TOKEN = Constants.getSystemVariable(HIP_TOKEN_VARNAME);

        HIPCHAT_RESOURCE = "https://api.hipchat.com/v2/room/" + HIPCHAT_ROOM + "/notification?auth_token=" + HIPCHAT_TOKEN;
    }

    public void sendMessage(HipchatMessage hipchatMessage) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            log.debug(mapper.writeValueAsString(hipchatMessage));

            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

            Client client = Client.create(clientConfig);

            WebResource webResource = client.resource(HIPCHAT_RESOURCE);

            ClientResponse response = webResource.accept(HIP_HEADER_MIME).type(HIP_HEADER_MIME)
                .post(ClientResponse.class, mapper.writeValueAsString(hipchatMessage));
            log.debug(response.toString());
        } catch (Exception e) {
            log.error("Error sending hipchat message: {}", e.getMessage());
        }
    }

    public boolean canSendMessage() {
        return HIPCHAT_ROOM != null && HIPCHAT_TOKEN != null;
    }
}
